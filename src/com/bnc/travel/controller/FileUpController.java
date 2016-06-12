package com.bnc.travel.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bnc.travel.model.TravelDAO;
import com.bnc.travel.model.TravelDTO;
import com.bnc.util.NumberCheck;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


public class FileUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String saveDir = null;
	TravelDAO travelDAO;
	
	public void init(ServletConfig conf){
		saveDir = conf.getServletContext().getRealPath("/upload/travel");
		travelDAO = new TravelDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String saveDir = "/upfolder";
		String encoding = "euc-kr";
		int maxsize = 3 * 1024 *1024;
		String root = request.getContextPath();
		
		MultipartRequest multi = new MultipartRequest(request, saveDir, maxsize, encoding, new DefaultFileRenamePolicy());
		
		String act = multi.getParameter("act");
		
		String path = root + "/index.jsp";
		
		/////index.jsp 목록등록 시 //////
		if("register".equals(act)){
			TravelDTO travelDTO = new TravelDTO();
			travelDTO.setTour_location(Integer.parseInt(multi.getParameter("tour_location")));
			travelDTO.setTour_name(multi.getParameter("tour_name"));
			travelDTO.setTour_tel(multi.getParameter("tour_tel"));
			travelDTO.setTour_addr(multi.getParameter("tour_addr"));
			travelDTO.setTour_roomtype(Integer.parseInt(multi.getParameter("tour_roomtype")));
			travelDTO.setTour_roomsize(Integer.parseInt(multi.getParameter("tour_roomsize")));
			travelDTO.setTour_numofpeople(Integer.parseInt(multi.getParameter("tour_numofpeople")));
			travelDTO.setTour_price(Integer.parseInt(multi.getParameter("tour_price")));
			travelDTO.setTour_content(multi.getParameter("content"));
//			System.out.println("fileupcontroller === >>> " + );
			File f = multi.getFile("tour_pic");
			File df1 = multi.getFile("tour_detail_pic1");
			File df2 = multi.getFile("tour_detail_pic2");
			
			if(f.exists()){
				travelDTO.setTour_opic(multi.getOriginalFileName("tour_pic"));
				travelDTO.setTour_spic(multi.getFilesystemName("tour_pic"));
			}
			if(df1.exists()){
				travelDTO.setTour_detailopic1(multi.getOriginalFileName("tour_detail_pic1"));
				travelDTO.setTour_detailspic1(multi.getFilesystemName("tour_detail_pic1"));
			}if(df2.exists()){
				travelDTO.setTour_detailopic2(multi.getOriginalFileName("tour_detail_pic2"));
				travelDTO.setTour_detailspic2(multi.getFilesystemName("tour_detail_pic2"));
			}
			int cnt = travelDAO.register(travelDTO);
			
			if(cnt != 0)
				path = root + "/travel?act=list";
			
			
		/////modify.jsp 목록수정 시//////////////////////////
		}else if("modify".equals(act)){
			
			int seq = NumberCheck.nullToZero(multi.getParameter("seq"));
			System.out.println("수정한다..........." + seq);
			TravelDTO travelDTO = new TravelDTO();
			travelDTO.setTour_seq(seq);
			travelDTO.setTour_location(Integer.parseInt(multi.getParameter("tour_location")));
			travelDTO.setTour_name(multi.getParameter("tour_name"));
			travelDTO.setTour_tel(multi.getParameter("tour_tel"));
			travelDTO.setTour_addr(multi.getParameter("tour_addr"));
			travelDTO.setTour_roomtype(Integer.parseInt(multi.getParameter("tour_roomtype")));
			travelDTO.setTour_roomsize(Integer.parseInt(multi.getParameter("tour_roomsize")));
			travelDTO.setTour_numofpeople(Integer.parseInt(multi.getParameter("tour_numofpeople")));
			travelDTO.setTour_price(Integer.parseInt(multi.getParameter("tour_price")));
			travelDTO.setTour_content(multi.getParameter("content"));
			System.out.println("fileController == " + travelDTO.getTour_content() + "\n aa" + travelDTO.getTour_detailopic1());
			File f = multi.getFile("tour_pic");
			if(f == null){
				
			}
			File df1 = multi.getFile("tour_detail_pic1");
			File df2 = multi.getFile("tour_detail_pic2");
			
			if(f.exists()){
				travelDTO.setTour_opic(multi.getOriginalFileName("tour_pic"));
				travelDTO.setTour_spic(multi.getFilesystemName("tour_pic"));
			}
			if(df1.exists()){
				travelDTO.setTour_detailopic1(multi.getOriginalFileName("tour_detail_pic1"));
				travelDTO.setTour_detailspic1(multi.getFilesystemName("tour_detail_pic1"));
			}if(df2.exists()){
				travelDTO.setTour_detailopic2(multi.getOriginalFileName("tour_detail_pic2"));
				travelDTO.setTour_detailspic2(multi.getFilesystemName("tour_detail_pic2"));
			}
			int cnt = travelDAO.update(travelDTO);
			System.out.println("cnt =====>>> " + cnt);
						
			if(cnt != 0)
				path = root + "/travel?act=list";
		}
		
		response.sendRedirect(path);
		
	}

}
