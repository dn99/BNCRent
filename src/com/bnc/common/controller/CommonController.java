package com.bnc.common.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bnc.common.model.CommonManager;
import com.bnc.props.AppProperties;

public class CommonController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private CommonManager manager;
	
	public void init()
	{
		manager = new CommonManager();
	}
	
	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
	{
		execute( request, response );
	}

	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
	{
		request.setCharacterEncoding( AppProperties.CHAR_SET );
		execute( request, response );
	}
	
	protected void execute( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
	{
		String root = request.getContextPath();
		String act = request.getParameter( "act" );
		String path = "";
		
		boolean flag = true;
		System.out.println( "act : " + act );
		
		if ( "".equals( act ) )
		{
			
		}
		
		if( flag )
			response.sendRedirect( path );
		else{
			RequestDispatcher disp = request.getRequestDispatcher( path );
			disp.forward(request, response);
		}
	}
}
