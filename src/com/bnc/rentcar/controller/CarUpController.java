package com.bnc.rentcar.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bnc.props.AppProperties;
import com.bnc.rentcar.model.CarInformationDAO;
import com.bnc.rentcar.model.CarInformationDTO;
import com.bnc.travel.model.TravelDTO;
import com.bnc.util.NumberCheck;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class CarUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String saveDir = null;
	CarInformationDAO carinformationDAO;
	
	public void init(ServletConfig conf){
		carinformationDAO = new CarInformationDAO();
		saveDir = conf.getServletContext().getRealPath("/upload/rentcar");
	}
	////////////////admin 차량 등록하기///////////////////////////
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String encoding = "euc-kr";
		int maxsize = 3 * 1024 * 1024; //사진 싸이즈
		
		MultipartRequest multi = new MultipartRequest(request, saveDir, maxsize, encoding, new DefaultFileRenamePolicy());
		
		String act = multi.getParameter("act");
		String root = request.getContextPath();
		String path = root + "/index.jsp";
		if("register".equals(act)){
			CarInformationDTO carinformationDTO = new CarInformationDTO();
			
			carinformationDTO.setCar_name(multi.getParameter("car_name"));
			carinformationDTO.setCar_grade(NumberCheck.nullToZero(multi.getParameter("car_grade")));
			carinformationDTO.setCar_maker(NumberCheck.nullToZero(multi.getParameter("car_maker")));
			carinformationDTO.setCar_disvolume(multi.getParameter("car_disvolume"));
			carinformationDTO.setCar_mile(multi.getParameter("car_mile"));
			carinformationDTO.setCar_fueltype(NumberCheck.nullToZero(multi.getParameter("car_fueltype")));
			carinformationDTO.setCar_numofpeople(NumberCheck.nullToZero(multi.getParameter("car_numofpeople")));
			carinformationDTO.setCar_rentprice(NumberCheck.nullToZero(multi.getParameter("car_rentprice")));
			carinformationDTO.setCar_roffice(NumberCheck.nullToZero(multi.getParameter("car_roffice")));
			carinformationDTO.setCar_content(multi.getParameter("content"));
			File f = multi.getFile("car_pic");
			System.out.println("f"+f);
			if(f.exists()){
				carinformationDTO.setCar_opic(multi.getOriginalFileName("car_pic"));
				carinformationDTO.setCar_spic(multi.getFilesystemName("car_pic"));
			}
			File f1 = multi.getFile("car_detailpic");
			if(f1.exists()){
				carinformationDTO.setCar_detailopic(multi.getOriginalFileName("car_detailpic"));
				carinformationDTO.setCar_detailspic(multi.getFilesystemName("car_detailpic"));	
			}
			int cnt = carinformationDAO.register(carinformationDTO);
			
			if(cnt != 0)
				path = root + "/carlist?act=list";
		//////////////////admin 차량 목록 수정///////////////////////////
		}else if("modify".equals(act)){
			int seq = NumberCheck.nullToZero(multi.getParameter("seq"));
			CarInformationDTO carinformationDTO = new CarInformationDTO();
			
			carinformationDTO.setCar_seq(seq);
			carinformationDTO.setCar_name(multi.getParameter("car_name"));
			carinformationDTO.setCar_grade(NumberCheck.nullToZero(multi.getParameter("car_grade")));
			carinformationDTO.setCar_maker(NumberCheck.nullToZero(multi.getParameter("car_maker")));
			carinformationDTO.setCar_disvolume(multi.getParameter("car_disvolume"));
			carinformationDTO.setCar_mile(multi.getParameter("car_mile"));
			carinformationDTO.setCar_fueltype(NumberCheck.nullToZero(multi.getParameter("car_fueltype")));
			carinformationDTO.setCar_numofpeople(NumberCheck.nullToZero(multi.getParameter("car_numofpeople")));
			carinformationDTO.setCar_rentprice(NumberCheck.nullToZero(multi.getParameter("car_rentprice")));
			carinformationDTO.setCar_roffice(NumberCheck.nullToZero(multi.getParameter("car_roffice")));
			carinformationDTO.setCar_content(multi.getParameter("content"));
			
			File f = multi.getFile("car_pic");
			System.out.println("f"+f);
			if(f.exists()){
				carinformationDTO.setCar_opic(multi.getOriginalFileName("car_pic"));
				carinformationDTO.setCar_spic(multi.getFilesystemName("car_pic"));
			}
			File f1 = multi.getFile("car_detailpic");
			if(f1.exists()){
				carinformationDTO.setCar_detailopic(multi.getOriginalFileName("car_detailpic"));
				carinformationDTO.setCar_detailspic(multi.getFilesystemName("car_detailpic"));	
			}
			int cnt = carinformationDAO.update(carinformationDTO);
			
			if(cnt != 0)
				path = root + "/carlist?act=list";
		}		
		response.sendRedirect(path);
	}
}