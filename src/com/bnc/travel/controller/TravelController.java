package com.bnc.travel.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.text.CodePointIterator;

import com.bnc.common.model.CodeInfoDTO;
import com.bnc.props.CodeProperties;
import com.bnc.rentcar.model.CarInformationDTO;
import com.bnc.travel.model.TravelDAO;
import com.bnc.travel.model.TravelDTO;
import com.bnc.travel.model.TravelManager;
import com.bnc.util.Encoder;
import com.bnc.util.NumberCheck;
import com.bnc.util.PageNavi;
import com.bnc.util.StringCheck;

public class TravelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	TravelDAO travelDAO;
	TravelManager travelManager;
	
	public void init(){
		travelDAO = new TravelDAO();
		travelManager = new TravelManager();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		execute(request, response);
	}
	
	private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getContextPath();
		
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		if ( request.getProtocol().equals( "HTTP/1.1" ) ) 
			response.setHeader( "Cache-Control", "no-cache" ); 
		
		String act = request.getParameter("act");
		int pg = NumberCheck.nullToOne(request.getParameter("pg"));
		String key = StringCheck.nullToBlank(request.getParameter("key"));// �˻�����
		String wordOrign = StringCheck.nullToBlank(request.getParameter("word")); //�˻���
		String word = Encoder.isoToEuc( wordOrign ); //�˻���
//		String word = StringCheck.nullToBlank(request.getParameter("word")); //�˻���
		
		String qs = "?pg=" + pg + "&key=" + key + "&word=" + word;
		String path = "/index.jsp";
		boolean flag = true;
		
		////////������ ��忡�� ��ϵ�Ͻ�///////////////
		if("mvregister".equals(act)){
			path = root + "/travel/index.jsp" + qs;
			
		///////������ ��忡�� ��� ���  list.jsp/////////////////////
		}else if("list".equals(act)){	
			List<TravelDTO> list = travelManager.getList(pg, key, word);
			request.setAttribute("list", list);
			
			PageNavi navigator = travelManager.getPageNavi(pg, key, word);
			request.setAttribute("pageNavi", navigator);
			
			flag = false;
			path = "/admin/travel/list.jsp" + qs;
			
			
		/////// pages ���� �� list.jsp client �� ������ ��� ��//////////////////////
		}else if("page_list".equals(act)){			
			List<TravelDTO> list = travelManager.getList(pg, key, word);
			request.setAttribute("list", list);			
			
			PageNavi navigator = travelManager.getPageNavi(pg, key, word);
			request.setAttribute("pageNavi", navigator);
			
			flag = false;
			path = "/pages/travel/list.jsp" + qs;
				
		/////������ ��忡�� ��ϼ��� ��////////////////////////////////////
		}else if("mvmodify".equals(act)){
			int seq = NumberCheck.nullToZero(request.getParameter("seq"));
			TravelDTO travelDTO = travelDAO.getModiArticle(seq);
			request.setAttribute("modiArticle", travelDTO);
			
			flag = false;
			path = "/admin/travel/modify.jsp" + qs;
			
		//////////////������ ��忡�� �������� ����//////////////////////////
		}else if("detail".equals(act)){
			int seq = NumberCheck.nullToZero(request.getParameter("seq"));
			TravelDTO travelDTO = null;
			try {
				travelDTO = travelDAO.getDetailArticle(seq);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("detailArticle", travelDTO);			
			
			flag = false;
			path = "/admin/travel/detail.jsp" + qs;
			
		//////pages ���� �� list.jsp�� �������� ���� ////////////////////////
		}else if("page_detail".equals(act)){
			int seq = NumberCheck.nullToZero(request.getParameter("seq"));
			TravelDTO travelDTO = null;
			try {
				travelDTO = travelDAO.getDetailArticle(seq);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("detailArticle", travelDTO);			
			
			flag = false;
			path = "/pages/travel/detail.jsp" + qs;
		
		////////������ ��忡�� ���� ��////////////////////////////////////////
		}else if("delete".equals(act)){
			int seq = NumberCheck.nullToZero(request.getParameter("seq"));
			System.out.println("DELETE_seq  ===============>>>>>" + seq);
			int cnt = travelDAO.deleteArticle(seq);
			System.out.println("DELETE_cnt  ===============>>>>>" + cnt);
			
			if(cnt == 1){
				List<TravelDTO> list = travelManager.getList(pg, key, word);
				request.setAttribute("list", list);			
				
				PageNavi navigator = travelManager.getPageNavi(pg, key, word);
				request.setAttribute("pageNavi", navigator);				
			}
				
			flag = false;
			path = "/admin/travel/list.jsp"  + qs;
							
		//////// �ǽð����� ��ư Ŭ�� ��///////////////////////////////////////  
		}else if("carList".equals(act)){
			sentRentCarList(request, response);
		}
		
		if(flag){			
			response.sendRedirect(path);
		}else{
			RequestDispatcher disp = request.getRequestDispatcher(path);
			disp.forward(request, response);
		}
	}

	private void sentRentCarList(HttpServletRequest request, HttpServletResponse response) {
		int roffice = Integer.parseInt(request.getParameter("roffice"));
		List<CarInformationDTO> list = travelManager.getRentCarList(0, roffice, 0);
		
	}

}








