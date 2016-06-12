package com.bnc.rentcar.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bnc.props.AppProperties;
import com.bnc.props.CodeProperties;
import com.bnc.rentcar.model.CarInformationDAO;
import com.bnc.rentcar.model.CarInformationDTO;
import com.bnc.rentcar.model.CarManager;
import com.bnc.travel.model.TravelDTO;
import com.bnc.util.Encoder;
import com.bnc.util.NumberCheck;
import com.bnc.util.PageNavi;
import com.bnc.util.StringCheck;

public class CarListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CarInformationDAO carinformationDAO;
	CarManager carmanager;

	public void init(){
		carinformationDAO = new CarInformationDAO();
		carmanager = new CarManager();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding( AppProperties.CHAR_SET );
		execute(request, response);
	}
	
	private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getContextPath();
		
		String act = request.getParameter("act");
		int pg = NumberCheck.nullToOne(request.getParameter("pg"));
		String tab = StringCheck.nullToBlank(request.getParameter("tab"));
		String key = StringCheck.nullToBlank(request.getParameter("key"));
		String wordOrign = StringCheck.nullToBlank(request.getParameter("word"));
		String word = Encoder.isoToEuc( wordOrign ); //검색어
		
		String qs = "?pg=" + pg + "&key=" + key + "&word=" + word + "&tab=" + tab;
		//String qs = "?pg=" + pg;
		String path = "/index.jsp";
		boolean flag = true;
        ///////////관리자 자동차 등록페이지////////////
		if("mvregister".equals(act)){
			path = root + "/admin/rentcar/index.jsp";
	    ///////////관리자 자동차 리스트페이지////////////
		}else if("list".equals(act)){
			List<CarInformationDTO> list = carmanager.getList(pg);
			request.setAttribute("list", list);
			
			PageNavi navigator = carmanager.getPageNavi(pg);
			request.setAttribute("pageNavi", navigator);
			
			flag = false;
			path = "/admin/rentcar/list.jsp" +qs;
		///////////사용자 자동차 상세페이지//////////////
		}else if("detail".equals(act)){
			int seq = NumberCheck.nullToZero(request.getParameter("seq"));
			CarInformationDTO carinformationDTO = carinformationDAO.getDetailArticle(seq);
			request.setAttribute("detailArticle", carinformationDTO);    
			flag = false;
			path = "/pages/rentcar/detail.jsp";
		///////////사용자 자동차 리스트페이지/////////////
		}else if("page_list".equals(act)){
			//String tab = StringCheck.nullToBlank(request.getParameter("tab"));
			//String key = StringCheck.nullToBlank(request.getParameter("key"));
			//String word = StringCheck.nullToBlank(request.getParameter("word"));
			System.out.println("tab=====>" + tab);
			System.out.println("key=====>" + key);
			System.out.println("word=====>" + word);
			
			List<CarInformationDTO> plist = carmanager.getPlist(pg, tab, key, word);
			request.setAttribute("plist", plist);
			
			PageNavi navigator = carmanager.getPageNavi(pg, tab, key, word);
			request.setAttribute("pageNavi", navigator);
			
			flag = false;
			path = "/pages/rentcar/list.jsp" +qs;
		///////////관리자 자동차 리스트 삭제//////////////
		}else if("delete".equals(act)){
			int seq = NumberCheck.nullToZero(request.getParameter("seq"));
			int cnt = carmanager.deleteArticle(seq);
			
			if(cnt == 1){
				List<CarInformationDTO> list = carmanager.getList(pg);
				request.setAttribute("list", list);
				
				PageNavi navigator = carmanager.getPageNavi(pg);
				request.setAttribute("pageNavi", navigator);
			}
			flag = false;
			path = "/admin/rentcar/list.jsp" +qs;
		///////////////관리자 차동차 리스트 수정//////////
		}else if("mvmodify".equals(act)){
			int seq = NumberCheck.nullToZero(request.getParameter("seq"));
			CarInformationDTO carinformationDTO = carinformationDAO.getModiArticle(seq);
			request.setAttribute("modiArticle", carinformationDTO);
			System.out.println("ddddd" + seq);
			
			flag = false;
			path = "/admin/rentcar/modify.jsp" + qs;
		}else if("".equals(act)){
			
		}
		
		if(flag)
			response.sendRedirect(path);
		else{
			RequestDispatcher disp = request.getRequestDispatcher(path);
			disp.forward(request, response);
		}
	}


}



