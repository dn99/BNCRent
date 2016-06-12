package com.bnc.community.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bnc.member.model.*;
import com.bnc.community.model.*;
import com.bnc.util.*;

/**
 * Servlet implementation class comctrl
 */
public class CommunityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	CommunityManager communityManager;
	
	public void init(){
		communityManager = new CommunityManager();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		execute(request, response);
	}

	private void execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		String root = request.getContextPath();

		String act = request.getParameter("act");
		int btype = NumberCheck.nullToZero(request.getParameter("btype"));
		int pg = NumberCheck.nullToOne(request.getParameter("pg"));
		String key = StringCheck.nullToBlank(request.getParameter("key"));// 검색조건
		String wordOrign = StringCheck.nullToBlank(request.getParameter("word")); //검색어
		String word = Encoder.isoToEuc( wordOrign ); //검색어
		String myid = StringCheck.nullToBlank(request.getParameter("myid"));
		
		String qs = "?btype=" + btype + "&pg=" + pg + "&key=" + key + "&word=" + word + "&myid="+myid;
		System.out.println("act == " + act + "\tqs == " + qs);

		String path = root + "/index.jsp";
		boolean flag = true;

		if ("boardtype".equals(act)) {
			List<CommunityTypeDTO> list = communityManager.getBoardType();
			request.setAttribute("boardtype", list);
			flag = false;
			path = "/pages/community/list.jsp";
		} else if ("mvnew".equals(act)) {
			path = root + "/pages/community/write.jsp" + qs;
		} else if ("writenew".equals(act)) {
			int seq = communityManager.getNextSeq();

			HttpSession session = request.getSession();
			MemberDTO memberDTO = (MemberDTO) session.getAttribute("userInfo");

			CommunityDTO communityDTO = new CommunityDTO();
			communityDTO.setSeq(seq);
			communityDTO.setId(memberDTO.getMember_id());
			communityDTO.setName(memberDTO.getMember_name());
			communityDTO.setEmail(memberDTO.getMember_email1() + "@" + memberDTO.getMember_email2());
			communityDTO.setSubject(request.getParameter("subject"));
			communityDTO.setContent(request.getParameter("content"));
			communityDTO.setBtype(btype);
			communityDTO.setRef(seq);

			int cnt = communityManager.newArticle(communityDTO);

			if (cnt == 0)
				path = root + "/pages/community/writeFail.jsp" + qs;
			else
				path = root + "/pages/community/writeOk.jsp" + qs + "&seq=" + seq;

		} else if ("mvreply".equals(act)) {
			int seq = NumberCheck.nullToZero(request.getParameter("seq"));
			CommunityDTO communityDTO = communityManager.getArticle(seq, "", btype); // 답글쓸때의 원글
			request.setAttribute("pDTO", communityDTO);
			flag = false;
			path = "/pages/community/reply.jsp" + qs;
		} else if ("writereply".equals(act)) {
			int seq = communityManager.getNextSeq();

			HttpSession session = request.getSession();
			MemberDTO memberDTO = (MemberDTO) session.getAttribute("userInfo");

			CommunityDTO communityDTO = new CommunityDTO();
			communityDTO.setSeq(seq);
			communityDTO.setId(memberDTO.getMember_id());
			communityDTO.setName(memberDTO.getMember_name());
			communityDTO.setEmail(memberDTO.getMember_email1() + "@" + memberDTO.getMember_email2());
			communityDTO.setSubject(request.getParameter("subject"));
			communityDTO.setContent(request.getParameter("content"));
			communityDTO.setBtype(btype);
			communityDTO.setRef(NumberCheck.nullToZero(request.getParameter("ref")));
			communityDTO.setLev(NumberCheck.nullToZero(request.getParameter("lev")));
			communityDTO.setStep(NumberCheck.nullToZero(request.getParameter("step")));
			communityDTO.setPseq(NumberCheck.nullToZero(request.getParameter("pseq")));

			int cnt = communityManager.replyArticle(communityDTO);

			if (cnt == 0)
				path = root + "/pages/community/writeFail.jsp" + qs;
			else
				path = root + "/pages/community/writeOk.jsp" + qs + "&seq=" + seq;

		}else if ("list".equals(act)) {
			
			List<CommunityDTO> list = communityManager.getList(btype, pg, key, word, myid);
			request.setAttribute("boardList", list);

			int newcnt = communityManager.getNewArticleCount(btype);
			request.setAttribute("newCount", newcnt + "");

			PageNavi navigator = communityManager.getPageNavi(btype, pg, key, word, myid);
			request.setAttribute("pageNavi", navigator);

			flag = false;
			path = "/pages/community/list.jsp" + qs;
		}else if ("view".equals(act)) {
			int seq = NumberCheck.nullToZero(request.getParameter("seq"));
			String func = StringCheck.nullToBlank(request.getParameter("func"));

			CommunityDTO communityDTO = communityManager.getArticle(seq, func, btype);
			if (communityDTO != null)
				communityManager.updateHit(seq);

			request.setAttribute("boardView", communityDTO);
			flag = false;
			path = "/pages/community/view.jsp" + qs;
		} else if ("mvmodify".equals(act)) {
			int seq = NumberCheck.nullToZero(request.getParameter("seq"));
			CommunityDTO communityDTO = communityManager.getModiArticle(seq);

			request.setAttribute("modiArticle", communityDTO);
			flag = false;
			path = "/pages/community/modify.jsp" + qs;
		} else if ("delete".equals(act)) {
			int seq = NumberCheck.nullToZero(request.getParameter("seq"));
			int cnt = communityManager.deleteArticle(seq);
			System.out.println("cnt" + cnt);

			if (cnt == 2) {
				
				List<CommunityDTO> list = communityManager.getList(btype, pg, key, word, myid);
				request.setAttribute("boardList", list);

				int newcnt = communityManager.getNewArticleCount(btype);
				request.setAttribute("newCount", newcnt + "");

				PageNavi navigator = communityManager.getPageNavi(btype, pg, key, word, myid);
				request.setAttribute("pageNavi", navigator);
			}

			flag = false;
			path = "/pages/community/list.jsp" + qs;
		} else if ("modify".equals(act)) {
			int seq = NumberCheck.nullToZero(request.getParameter("seq"));
			CommunityDTO communityDTO = new CommunityDTO();
			communityDTO.setSeq(seq);
			communityDTO.setSubject(request.getParameter("subject"));
			communityDTO.setContent(request.getParameter("content"));
			int cnt = communityManager.updateArticle(communityDTO);

			if (cnt == 0)
				path = root + "/pages/community/writeFail.jsp" + qs;
			else
				path = root + "/pages/community/modification.jsp" + qs + "&seq=" + seq;
		}  

		if (flag)
			response.sendRedirect(path);
		else {
			RequestDispatcher disp = request.getRequestDispatcher(path);
			disp.forward(request, response);
		}
	}
}
