package com.bnc.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bnc.member.model.*;
import com.bnc.props.AppProperties;
import com.bnc.reserve.model.RentDTO;
import com.bnc.travel.model.TRentDTO;
import com.bnc.travel.model.TravelDTO;
import com.bnc.util.Encoder;
import com.bnc.util.NumberCheck;
import com.bnc.util.PageNavi;
import com.bnc.util.StringCheck;


public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MemberDAO memberDAO;
	MemberManager memberManager;
	
	public void init(){
		memberDAO = new MemberDAO();
		memberManager = new MemberManager();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	execute(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding( AppProperties.CHAR_SET );
		execute(request,response);
	}

	private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root= request.getContextPath();
		
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		if ( request.getProtocol().equals( "HTTP/1.1" ) ) 
			response.setHeader( "Cache-Control", "no-cache" ); 
		
		String act = request.getParameter("act");
		int pg = NumberCheck.nullToOne(request.getParameter("pg"));
		String key = StringCheck.nullToBlank(request.getParameter("key"));// 검색조건
		String wordOrign = StringCheck.nullToBlank(request.getParameter("word")); //검색어
		String word = Encoder.isoToEuc( wordOrign ); //검색어
		String id = StringCheck.nullToBlank(request.getParameter("id"));
		
		System.out.println(act);
		
		String qs = "?pg=" + pg + "&key=" + key + "&word=" + word + "&id=" +id;
		
		String path=  root +"/index.jsp";
		boolean flag= true;
		if("mvjoin".equals(act)){
			path= root +"/pages/member/join.jsp";
		
		}else if("mvlogin".equals(act)){
			path= root +"/pages/member/login.jsp";
		
		}else if("register".equals(act)){
			MemberDTO memberDTO = new MemberDTO();
			
			memberDTO.setMember_id(request.getParameter("id"));
			memberDTO.setMember_name(request.getParameter("name"));
			memberDTO.setMember_pass(request.getParameter("pass"));
			memberDTO.setMember_gender(request.getParameter("gender"));
			memberDTO.setMember_email1(request.getParameter("email1"));
			memberDTO.setMember_email2(request.getParameter("email2"));
			memberDTO.setMember_zip1(request.getParameter("zip1"));
			memberDTO.setMember_zip2(request.getParameter("zip2"));
			memberDTO.setMember_addr1(request.getParameter("addr1"));
			memberDTO.setMember_addr2(request.getParameter("addr2"));
			memberDTO.setMember_tel1(request.getParameter("tel1"));
			memberDTO.setMember_tel2(request.getParameter("tel2"));
			memberDTO.setMember_tel3(request.getParameter("tel3"));
			memberDTO.setMember_phone1(request.getParameter("phone1"));
			memberDTO.setMember_phone2(request.getParameter("phone2"));
			memberDTO.setMember_phone3(request.getParameter("phone3"));
			memberDTO.setMember_pnum1(request.getParameter("pnum1"));
			memberDTO.setMember_pnum2(request.getParameter("pnum2"));
			memberDTO.setMember_dlnum1(request.getParameter("dlnum1"));
			memberDTO.setMember_dlnum2(request.getParameter("dlnum2"));
			memberDTO.setMember_dlnum3(request.getParameter("dlnum3"));
			memberDTO.setMember_dltype(request.getParameter("dltype"));
			memberDTO.setMember_dlyear(request.getParameter("dlyear"));
			memberDTO.setMember_dlmonth(request.getParameter("dlmonth"));
			memberDTO.setMember_dlday(request.getParameter("dlday"));
			memberDTO.setMember_grade(0);
			memberDTO.setMember_level(1);
			
			int cnt= memberDAO.register(memberDTO);
			
			if(cnt!=0)	
				path= root +"/pages/member/success.jsp";
			else
				path= root +"/pages/member/fail.jsp";
		}else if("mvzip".equals(act)){
			path= root + "/pages/member/zipsearch.jsp";
		
		}else if("zipsearch".equals(act)){
			String dong= Encoder.isoToEuc(request.getParameter("dong"));
			System.out.println(dong);
			List<ZipDTO> list=memberDAO.getZipList(dong);
			request.setAttribute("zipList",list);
			flag= false;
			path= "/pages/member/zipsearch.jsp";
		
		}else if("mvidcheck".equals(act)){
			path= root+ "/pages/member/idcheck.jsp";
		
		}else if("idcheck".equals(act)){
			
			int result= memberDAO.idcheck(id);
		
			path=root+"/pages/member/idcheck.jsp?id="+id+"&result="+result;
		}else if ("login".equals(act)){
			
			String pass= request.getParameter("pass");
			
			MemberDTO memberDTO = memberDAO.login(id,pass);
			if(memberDTO == null)
				path= root+"/pages/member/fail.jsp";
			else{
				///////////////cookie setting///////////
				String sv= request.getParameter("idsave");
				if("save".equals(sv)){
					Cookie c= new Cookie("nid_sid",id);
					c.setMaxAge(60*60);
					c.setPath(root);
					
					response.addCookie(c);
				}else{
					Cookie c[]= request.getCookies();
					if(c!=null){
						int len=c.length;
						for(int i=0;i<len;i++){
							if("nid_sid".equals(c[i].getName())){
								c[i].setMaxAge(0); //쿠키의 만료날짜를 '0'으로 넣어라.
								c[i].setPath(root);
								response.addCookie(c[i]);
								break;
							}
						}
					}
				}
				///////////////cookie setting 끝! ///////////
				
				///////////////Session setting/////////////
				HttpSession session= request.getSession();
				session.setAttribute("userInfo", memberDTO);
			
				///////////////Session setting 끝!/////////////
				//request.setAttribute("userInfo", memberDTO);
				flag= false;
				path="/index.jsp";
			}
		}else if("logout".equals(act)){
			HttpSession session= request.getSession();
		//	session.setAttribute("userInfo", null);
		//	session.removeAttribute("userInfo"); //하나씩 제거시키겠다는 거! 
			session.invalidate(); // 다 제거시켜버리겠어! 
		}else if("modify".equals(act)){
			MemberDTO memberDTO = new MemberDTO();
			
			memberDTO.setMember_id(request.getParameter("id"));
			memberDTO.setMember_name(request.getParameter("name"));
			memberDTO.setMember_pass(request.getParameter("pass"));
			memberDTO.setMember_gender(request.getParameter("gender"));
			memberDTO.setMember_email1(request.getParameter("email1"));
			memberDTO.setMember_email2(request.getParameter("email2"));
			memberDTO.setMember_zip1(request.getParameter("zip1"));
			memberDTO.setMember_zip2(request.getParameter("zip2"));
			memberDTO.setMember_addr1(request.getParameter("addr1"));
			memberDTO.setMember_addr2(request.getParameter("addr2"));
			memberDTO.setMember_tel1(request.getParameter("tel1"));
			memberDTO.setMember_tel2(request.getParameter("tel2"));
			memberDTO.setMember_tel3(request.getParameter("tel3"));
			memberDTO.setMember_phone1(request.getParameter("phone1"));
			memberDTO.setMember_phone2(request.getParameter("phone2"));
			memberDTO.setMember_phone3(request.getParameter("phone3"));
			memberDTO.setMember_pnum1(request.getParameter("pnum1"));
			memberDTO.setMember_pnum2(request.getParameter("pnum2"));
			memberDTO.setMember_dlnum1(request.getParameter("dlnum1"));
			memberDTO.setMember_dlnum2(request.getParameter("dlnum2"));
			memberDTO.setMember_dlnum3(request.getParameter("dlnum3"));
			memberDTO.setMember_dltype(request.getParameter("dltype"));
			memberDTO.setMember_dlyear(request.getParameter("dlyear"));
			memberDTO.setMember_dlmonth(request.getParameter("dlmonth"));
			memberDTO.setMember_dlday(request.getParameter("dlday"));
			memberDTO.setMember_grade(0);
			memberDTO.setMember_level(0);
			
			int cnt= memberDAO.modify(memberDTO);
			
			if(cnt!=0)	
				path= root +"/pages/member/modifysuccess.jsp";
			else
				path= root +"/pages/member/modifyfail.jsp";
		}else if("modify_adm".equals(act)){
			MemberDTO memberDTO = new MemberDTO();
			
			memberDTO.setMember_id(request.getParameter("id"));
			memberDTO.setMember_name(request.getParameter("name"));
			memberDTO.setMember_pass(request.getParameter("pass"));
			memberDTO.setMember_gender(request.getParameter("gender"));
			memberDTO.setMember_email1(request.getParameter("email1"));
			memberDTO.setMember_email2(request.getParameter("email2"));
			memberDTO.setMember_zip1(request.getParameter("zip1"));
			memberDTO.setMember_zip2(request.getParameter("zip2"));
			memberDTO.setMember_addr1(request.getParameter("addr1"));
			memberDTO.setMember_addr2(request.getParameter("addr2"));
			memberDTO.setMember_tel1(request.getParameter("tel1"));
			memberDTO.setMember_tel2(request.getParameter("tel2"));
			memberDTO.setMember_tel3(request.getParameter("tel3"));
			memberDTO.setMember_phone1(request.getParameter("phone1"));
			memberDTO.setMember_phone2(request.getParameter("phone2"));
			memberDTO.setMember_phone3(request.getParameter("phone3"));
			memberDTO.setMember_pnum1(request.getParameter("pnum1"));
			memberDTO.setMember_pnum2(request.getParameter("pnum2"));
			memberDTO.setMember_dlnum1(request.getParameter("dlnum1"));
			memberDTO.setMember_dlnum2(request.getParameter("dlnum2"));
			memberDTO.setMember_dlnum3(request.getParameter("dlnum3"));
			memberDTO.setMember_dltype(request.getParameter("dltype"));
			memberDTO.setMember_dlyear(request.getParameter("dlyear"));
			memberDTO.setMember_dlmonth(request.getParameter("dlmonth"));
			memberDTO.setMember_dlday(request.getParameter("dlday"));
			memberDTO.setMember_grade(Integer.parseInt(request.getParameter("grade")));
			memberDTO.setMember_level(Integer.parseInt(request.getParameter("level")));
			
			int cnt= memberDAO.modify(memberDTO);
			
			if(cnt!=0)	
				path= root +"/memberctrl?act=list_admin&pg=1";
			else
				path= root +"/pages/member/modifyfail.jsp";
			
		}else if("delete".equals(act)){
			HttpSession session= request.getSession();
			//	session.setAttribute("userInfo", null);
			//	session.removeAttribute("userInfo"); //하나씩 제거시키겠다는 거! 
			session.invalidate(); // 다 제거시켜버리겠어! 
		
			String id2=request.getParameter("id");
			String pass= request.getParameter("pass");
			String name = request.getParameter("name");
			String pnum1 = request.getParameter("pnum1");
			String pnum2 = request.getParameter("pnum2");
			System.out.println(pnum2);
			
			int cnt = memberDAO.delete(id2, pass, name, pnum1, pnum2);
		
			if(cnt != 0)
				path = root + "/pages/member/delsuccess.jsp";
			else
				path = root + "/pages/member/delfail.jsp";
				
//		}else if("membermodi".equals(act)){
//			
//			String pass= request.getParameter("pass");
//			String grade= request.getParameter("grade");
//			String level= request.getParameter("level");
//			
//			int cnt= memberDAO.membermodi(id, pass, grade,level);
//			
//			if(cnt != 0)
//				path= root + "/admin/member/memberlist.jsp";
//			
//			else 
//				path= root + "/admin/member/memberlist.jsp";
		}else if("list".equals(act)){
		
			System.out.println(id);
			List<RentDTO> list = memberManager.bookinglist(id, pg, key, word);
			request.setAttribute("list", list);
			
			PageNavi navigator = memberManager.getPageNavi(id, pg, key, word);
			request.setAttribute("pageNavi", navigator);
			
			flag = false;
			path = "/pages/member/booking.jsp" + qs;
			
		}else if("list_admin".equals(act)){
			
			List<MemberDTO> list = memberManager.list_admin(id, pg, key, word);
			request.setAttribute("list", list);
			
			PageNavi navigator = memberManager.getPageNavi2(id, pg, key, word);
			request.setAttribute("pageNavi", navigator);
			
			flag = false;
			path = "/admin/member/memberlist.jsp" + qs;
			
		}else if("list_trent".equals(act)){
		
			System.out.println(id);
			List<TRentDTO> list = memberManager.list_trent(id, pg, key, word);
			request.setAttribute("list", list);
			
			PageNavi navigator = memberManager.getPageNavi3(id, pg, key, word);
			request.setAttribute("pageNavi", navigator);
			
			flag = false;
			path = "/pages/member/trentbooking.jsp" + qs;
			
		}else if("mvmembermodi".equals(act)){
			String id2= Encoder.isoToEuc(request.getParameter("id"));
			
			System.out.println(id2);
			MemberDTO memberDTO = memberDAO.getMemberdto(id2);
			request.setAttribute("memberdto", memberDTO);
			
			flag =false;
			path= "/admin/member/modify.jsp";
			
		}else if("admdel".equals(act)){
			String id3= Encoder.isoToEuc(request.getParameter("id"));
			System.out.println(id3);
			
			int cnt = memberDAO.deletemember(id3);
			
			if(cnt != 0)
				path= root +"/memberctrl?act=list_admin&pg=1";
			else
				path = root + "/pages/member/delete.jsp";
			
		}else if("reservecheck".equals(act)){
			String seq= Encoder.isoToEuc(request.getParameter("seq"));
			
			BookingDTO bookingDTO = memberDAO.reservecheck(seq);
			request.setAttribute("rentcheckdto", bookingDTO);
			
			flag =false;
			path = "/pages/member/reservecheck.jsp";
		}else if("travelreservecheck".equals(act)){
			String seq= Encoder.isoToEuc(request.getParameter("seq"));
			
			TravelbookingDTO travelbookingDTO = memberDAO.trentreservecheck(seq);
			request.setAttribute("trentcheckdto", travelbookingDTO);
			
			flag =false;
			path = "/pages/member/trentreservecheck.jsp";
		}
		
		if(flag)
			response.sendRedirect(path);
		else{
			RequestDispatcher disp= request.getRequestDispatcher(path);
			disp.forward(request, response); //root사용 안됨.
		}
	}	
}
