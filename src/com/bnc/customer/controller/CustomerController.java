package com.bnc.customer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bnc.member.model.*;
import com.bnc.customer.model.*;
import com.bnc.util.*;

/**
 * Servlet implementation class cusctrl
 */
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	CustomerManager customerManager;
	
	public void init(){
		customerManager = new CustomerManager();
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
			List<CustomerTypeDTO> list = customerManager.getBoardType();
			request.setAttribute("boardtype", list);
			flag = false;
			path = "/pages/customer/list.jsp";
		} else if ("mvnew".equals(act)) {
			path = root + "/pages/customer/write.jsp" + qs;
		} else if ("writenew".equals(act)) {
			int seq = customerManager.getNextSeq();

			HttpSession session = request.getSession();
			MemberDTO memberDTO = (MemberDTO) session.getAttribute("userInfo");

			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setSeq(seq);
			customerDTO.setId(memberDTO.getMember_id());
			customerDTO.setName(memberDTO.getMember_name());
			customerDTO.setEmail(memberDTO.getMember_email1() + "@" + memberDTO.getMember_email2());
			customerDTO.setSubject(request.getParameter("subject"));
			customerDTO.setContent(request.getParameter("content"));
			customerDTO.setBtype(btype);
			customerDTO.setRef(seq);

			int cnt = customerManager.newArticle(customerDTO);

			if (cnt == 0)
				path = root + "/pages/customer/writeFail.jsp" + qs;
			else
				path = root + "/pages/customer/writeOk.jsp" + qs + "&seq=" + seq;

		} else if ("mvreply".equals(act)) {
			int seq = NumberCheck.nullToZero(request.getParameter("seq"));
			CustomerDTO customerDTO = customerManager.getArticle(seq, "", btype); // 답글쓸때의 원글
			request.setAttribute("pDTO", customerDTO);
			flag = false;
			path = "/pages/customer/reply.jsp" + qs;
		} else if ("writereply".equals(act)) {
			int seq = customerManager.getNextSeq();

			HttpSession session = request.getSession();
			MemberDTO memberDTO = (MemberDTO) session.getAttribute("userInfo");

			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setSeq(seq);
			customerDTO.setId(memberDTO.getMember_id());
			customerDTO.setName(memberDTO.getMember_name());
			customerDTO.setEmail(memberDTO.getMember_email1() + "@" + memberDTO.getMember_email2());
			customerDTO.setSubject(request.getParameter("subject"));
			customerDTO.setContent(request.getParameter("content"));
			customerDTO.setBtype(btype);
			customerDTO.setRef(NumberCheck.nullToZero(request.getParameter("ref")));
			customerDTO.setLev(NumberCheck.nullToZero(request.getParameter("lev")));
			customerDTO.setStep(NumberCheck.nullToZero(request.getParameter("step")));
			customerDTO.setPseq(NumberCheck.nullToZero(request.getParameter("pseq")));

			int cnt = customerManager.replyArticle(customerDTO);

			if (cnt == 0)
				path = root + "/pages/customer/writeFail.jsp" + qs;
			else
				path = root + "/pages/customer/writeOk.jsp" + qs + "&seq=" + seq;

		}else if ("list".equals(act)) {
			
			List<CustomerDTO> list = customerManager.getList(btype, pg, key, word, myid);
			request.setAttribute("boardList", list);

			int newcnt = customerManager.getNewArticleCount(btype);
			request.setAttribute("newCount", newcnt + "");

			PageNavi navigator = customerManager.getPageNavi(btype, pg, key, word, myid);
			request.setAttribute("pageNavi", navigator);

			flag = false;
			path = "/pages/customer/list.jsp" + qs;
		}else if ("view".equals(act)) {
			int seq = NumberCheck.nullToZero(request.getParameter("seq"));
			String func = StringCheck.nullToBlank(request.getParameter("func"));

			CustomerDTO customerDTO = customerManager.getArticle(seq, func, btype);
			if (customerDTO != null)
				customerManager.updateHit(seq);

			request.setAttribute("boardView", customerDTO);
			flag = false;
			path = "/pages/customer/view.jsp" + qs;
		} else if ("mvmodify".equals(act)) {
			int seq = NumberCheck.nullToZero(request.getParameter("seq"));
			CustomerDTO customerDTO = customerManager.getModiArticle(seq);

			request.setAttribute("modiArticle", customerDTO);
			flag = false;
			path = "/pages/customer/modify.jsp" + qs;
		} else if ("delete".equals(act)) {
			int seq = NumberCheck.nullToZero(request.getParameter("seq"));
			int cnt = customerManager.deleteArticle(seq);
			System.out.println("cnt" + cnt);

			if (cnt == 2) {
	
				List<CustomerDTO> list = customerManager.getList(btype, pg, key, word, myid);
				request.setAttribute("boardList", list);

				int newcnt = customerManager.getNewArticleCount(btype);
				request.setAttribute("newCount", newcnt + "");

				PageNavi navigator = customerManager.getPageNavi(btype, pg, key, word, myid);
				request.setAttribute("pageNavi", navigator);
			}

			flag = false;
			path = "/pages/customer/list.jsp" + qs;
		} else if ("modify".equals(act)) {
			int seq = NumberCheck.nullToZero(request.getParameter("seq"));
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setSeq(seq);
			customerDTO.setSubject(request.getParameter("subject"));
			customerDTO.setContent(request.getParameter("content"));
			int cnt = customerManager.updateArticle(customerDTO);

			if (cnt == 0)
				path = root + "/pages/customer/writeFail.jsp" + qs;
			else
				path = root + "/pages/customer/modification.jsp" + qs + "&seq=" + seq;
		}  

		if (flag)
			response.sendRedirect(path);
		else {
			RequestDispatcher disp = request.getRequestDispatcher(path);
			disp.forward(request, response);
		}
	}
}
