package com.bnc.reserve.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.bnc.props.AppProperties;
import com.bnc.props.CodeProperties;
import com.bnc.rentcar.model.CarInformationDTO;
import com.bnc.reserve.model.RentDTO;
import com.bnc.reserve.model.RentManager;
import com.bnc.travel.model.TRentDTO;
import com.bnc.util.DateUtil;
import com.bnc.util.NumberCheck;
import com.bnc.util.PageNavi;
import com.bnc.util.PageSize;

public class ReserveController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	private RentManager manager;
	private String root;
	
	public void init()
	{
		manager = new RentManager();
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
		response.setHeader( "Cache-Control", "no-store" );   
		response.setHeader( "Pragma", "no-cache" );   
		response.setDateHeader( "Expires", 0 );
		if ( request.getProtocol().equals( "HTTP/1.1" ) ) 
			response.setHeader( "Cache-Control", "no-cache" ); 
		
		root = request.getContextPath();
		String act = request.getParameter( "act" );
		String path = "";
		
		boolean flag = true;
		System.out.println( "act : " + act );
		
		if ( "getCurrentDay".equals( act ) )
		{
			getCurrentDay( request, response );
		}
		else if ( "carPageNavi".equals( act ) )
		{
			getCarPageNavi( request, response );
		}
		else if ( "carList".equals( act ) )
		{
			getRentCarList( request, response );
		}
		else if ( "getCodeName".equals( act ) )
		{
			getCodeName( request, response );
		}
		else if ( "setReserve".equals( act ) )
		{
			setReserve( request, response );
		}
		else if ( "setTravelReserve".equals( act ) )
		{
			setTravelReserve( request, response );
		}
		else if ( "getAdminRentList".equals( act ) )
		{
			getAdminRentList( request, response );
		}
	}
	
	private void getAdminRentList( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		int pg = NumberCheck.nullToOne(request.getParameter("pg"));
		List<RentDTO> list = manager.getRentList( pg );
		
		request.setAttribute( "rentList", list );
		
		boolean flag = false;
		String path = root + "/admin/reserve/index.jsp";
		processSend( request, response, flag, path );
	}

	private void setReserve( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
	{
		int carSeq = Integer.parseInt( request.getParameter( "hdCarSeq" ) );
		
		boolean reserveOK = getResultRent( request, response );
		if ( reserveOK ) manager.updateRentCheck( carSeq );
		
		String path = root + "/pages/reserve/reserveAjax.jsp?reserveOK=" + reserveOK;
		processSend( request, response, true, path );
	}

	private void setTravelReserve( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		int carSeq = Integer.parseInt( request.getParameter( "hdCarSeq" ) );
		
		int rentSeq = 0;
		boolean reserveOK = getResultRent( request, response );
		if ( reserveOK ) 
		{
			manager.updateRentCheck( carSeq );
			rentSeq = manager.getRentSeq();
			
			System.out.println( "RENT SEQ : " + rentSeq );
			
			reserveOK = getResultTour( request, response, rentSeq );
		}
		
		String path = root + "/pages/travel/travelReserve.jsp?reserveOK=" + reserveOK;
		processSend( request, response, true, path );
	}
	
	private boolean getResultRent( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
	{
//		<input type="hidden" id="hdMemberId" value="<%=memberDTO.getMember_id()%>" />
//		<input type="hidden" id="hdCarSeq" />
//		<input type="hidden" id="hdRday" />
//		<input type="hidden" id="hdRtime" />
//		<input type="hidden" id="hdRetday" />
//		<input type="hidden" id="hdRettime" />
//		<input type="hidden" id="hdRlocation" />
//		<input type="hidden" id="hdRoffice" />
//		<input type="hidden" id="hdRetlocation" />
//		<input type="hidden" id="hdRetoffice" />
//		<input type="hidden" id="hdNaviCheck" />
//		<input type="hidden" id="hdInsuranceSeq" />
//		<input type="hidden" id="hdTotalDay" />
//		<input type="hidden" id="hdTotalTime" />
//		<input type="hidden" id="hdPrice" />
//		<input type="hidden" id="hdTotalPrice" />
//		<input type="hidden" id="hdDc" />
		
		String memberId = request.getParameter( "hdMemberId" );
		int carSeq = Integer.parseInt( request.getParameter( "hdCarSeq" ) );
		String rday = request.getParameter( "hdRday" );
		String rtime = request.getParameter( "hdRtime" );
		String retday = request.getParameter( "hdRetday" );
		String rettime = request.getParameter( "hdRettime" );
		int rlocation = Integer.parseInt( request.getParameter( "hdRlocation" ) );
		int roffice = Integer.parseInt( request.getParameter( "hdRoffice" ) );
		int retlocation = Integer.parseInt( request.getParameter( "hdRetlocation" ) );
		int retoffice = Integer.parseInt( request.getParameter( "hdRetoffice" ) );
		String naviCheck = request.getParameter( "hdNaviCheck" );
		int insuranceSeq = Integer.parseInt( request.getParameter( "hdInsuranceSeq" ) );
		int totalDay = Integer.parseInt( request.getParameter( "hdTotalDay" ) );
		int totalTime = Integer.parseInt( request.getParameter( "hdTotalTime" ) );
		int price = Integer.parseInt( request.getParameter( "hdPrice" ) );
		int totalPrice = Integer.parseInt( request.getParameter( "hdTotalPrice" ) );
		int dc = Integer.parseInt( request.getParameter( "hdDc" ) );
		
		RentDTO rentDTO = new RentDTO();
		rentDTO.setRent_member_id( memberId );
		rentDTO.setRent_car_seq( carSeq );
		rentDTO.setRent_rday( rday );
		rentDTO.setRent_rtime( rtime );
		rentDTO.setRent_retday( retday );
		rentDTO.setRent_rettime( rettime );
		rentDTO.setRent_rlocation( rlocation );
		rentDTO.setRent_roffice( roffice );
		rentDTO.setRent_retlocation( retlocation );
		rentDTO.setRent_retoffice( retoffice );
		rentDTO.setRent_navicheck( naviCheck );
		rentDTO.setRent_insurance_seq( insuranceSeq );
		rentDTO.setRent_totalday( totalDay );
		rentDTO.setRent_totaltime( totalTime );
		rentDTO.setRent_price( price );
		rentDTO.setRent_totalprice( totalPrice );
		rentDTO.setRent_dc( dc );
		
		return manager.insertRent( rentDTO );
	}
	
	private boolean getResultTour( HttpServletRequest request, HttpServletResponse response, int rentSeq ) throws ServletException, IOException 
	{
//		<input type="hidden" id="hdMemberId" name="hdMemberId" value="<%=memberDTO.getMember_id()%>" />
//		<input type="hidden" id="hdTourSeq" name="hdTourSeq" />
//		<input type="hidden" id="hdRentSeq" name="hdRentSeq" />
//		<input type="hidden" id="hdCarSeq" name="hdCarSeq" />
//		<input type="hidden" id="hdCheckInDay" name="hdCheckInDay" />
//		<input type="hidden" id="hdCheckOutDay" name="hdCheckOutDay" />
//		<input type="hidden" id="hdSumPeople" name="hdSumPeople" />
//		<input type="hidden" id="hdTourTotalPrice" name="hdTourTotalPrice" />
//		<input type="hidden" id="hdNote" name="hdNote" />
		
		String memberId = request.getParameter( "hdMemberId" );
		int tourSeq = Integer.parseInt( request.getParameter( "hdTourSeq" ) );
		
		System.out.println( "TOUR SEQ : " + tourSeq );
		
		int carSeq = Integer.parseInt( request.getParameter( "hdCarSeq" ) );
		String checkInDay = request.getParameter( "hdCheckInDay" );
		String checkOutDay = request.getParameter( "hdCheckOutDay" );
		int sumPeople = Integer.parseInt( request.getParameter( "hdSumPeople" ) );
		int tourTotalPrice = Integer.parseInt( request.getParameter( "hdTourTotalPrice" ) );
		String note = request.getParameter( request.getParameter( "hdNote" ) );
		
		TRentDTO trentDTO = new TRentDTO();
		trentDTO.setTrent_member_id( memberId );
		trentDTO.setTrent_tour_seq( tourSeq );
		trentDTO.setTrent_rent_seq( rentSeq );
		trentDTO.setTrent_car_seq( carSeq );
		trentDTO.setTrent_checkinday( checkInDay );
		trentDTO.setTrent_checkoutday( checkOutDay );
		trentDTO.setTrent_sumpeople( sumPeople );
		trentDTO.setTrent_totalprice( tourTotalPrice );
		trentDTO.setTrent_note( note );
		
		int cnt = manager.insertTour( trentDTO );
		
		return cnt == 1 ? true : false;
	}
	
	private void getCurrentDay( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
	{
		String currentDay = DateUtil.getCurrentDate( DateUtil.FORMAT_DATE_DAY );
		
		response.setContentType( "text/plain;charset=EUC-KR" );
		PrintWriter out = response.getWriter();

		out.println( currentDay );
		System.out.println( "currentDay : " + currentDay );
	}

	private void getCarPageNavi( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		int pg = NumberCheck.nullToOne( request.getParameter( "pg" ) );
		int roffice = NumberCheck.nullToZero( request.getParameter( "roffice" ) );
		PageNavi navigator = manager.getPageNavi( pg, roffice );
		
		response.setContentType( "text/plain;charset=EUC-KR" );
		PrintWriter out = response.getWriter();
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put( "no", navigator.No() );
		jsonObj.put( "totalPage", navigator.getTotalPage() );
		jsonObj.put( "pageNavi", navigator.pageNavi().toString() );
		
		out.println( jsonObj.toString() );
		out.flush();
		out.close();
	}
	
	private void getRentCarList( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
	{
		int pg = NumberCheck.nullToOne( request.getParameter( "pg" ) );
		int roffice = NumberCheck.nullToZero( request.getParameter( "roffice" ) );
		List<CarInformationDTO> list = null;
		try
		{
			list = manager.getRentCarList( pg, roffice );
		} 
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		
		System.out.println( "CAR TOTAL : " + list.size() );
		
		response.setContentType( "text/xml;charset=EUC-KR" );
		PrintWriter out = response.getWriter();
		
//		private int car_seq;
//		private String car_name;
//		private String car_opic;
//		private String car_spic;
//		private int car_grade;
//		private int car_maker;
//		private String car_disvolume;
//		private String car_mile;
//		private int car_fueltype;
//		private int car_numofpeople;
//		private int car_rentprice;
//		private int car_roffice;
//		private String car_detailopic;
//		private String car_detailspic;
		
		out.println("<?xml version='1.0' encoding='euc-kr'?>");
		out.println("<carlist>");
		for ( CarInformationDTO dto : list )
		{
			out.println("<car>");
			out.println("	<car_seq>" + dto.getCar_seq() + "</car_seq>");
			out.println("	<car_name>" + dto.getCar_name() + "</car_name>");
			out.println("	<car_opic>" + dto.getCar_opic() + "</car_opic>");
			out.println("	<car_spic>" + dto.getCar_spic() + "</car_spic>");
			out.println("	<car_grade>" + dto.getCar_grade() + "</car_grade>");
			out.println("	<car_maker>" + dto.getCar_maker() + "</car_maker>");
			out.println("	<car_disvolume>" + dto.getCar_disvolume() + "</car_disvolume>");
			out.println("	<car_mile>" + dto.getCar_mile() + "</car_mile>");
			out.println("	<car_fueltype>" + dto.getCar_fueltype() + "</car_fueltype>");
			out.println("	<car_numofpeople>" + dto.getCar_numofpeople() + "</car_numofpeople>");
			out.println("	<car_rentprice>" + dto.getCar_rentprice() + "</car_rentprice>");
			out.println("	<car_roffice>" + dto.getCar_roffice() + "</car_roffice>");
			out.println("	<car_detailopic>" + dto.getCar_detailopic() + "</car_detailopic>");
			out.println("	<car_detailspic>" + dto.getCar_detailspic() + "</car_detailspic>");
			out.println("</car>");
		}
		out.println("</carlist>");
		out.flush();
		out.close();
		
		String str = "";
		str += "<?xml version='1.0' encoding='euc-kr'?>\n";
		str += "<carlist>\n";
		for ( CarInformationDTO dto : list )
		{
			str += "	<car>\n";
			str += "	<car_seq>" + dto.getCar_seq() + "</car_seq>\n";
			str += "	<car_name>" + dto.getCar_name() + "</car_name>\n";
			str += "	<car_opic>" + dto.getCar_opic() + "</car_opic>\n";
			str += "	<car_spic>" + dto.getCar_spic() + "</car_spic>\n";
			str += "	<car_grade>" + dto.getCar_grade() + "</car_grade>\n";
			str += "	<car_maker>" + dto.getCar_maker() + "</car_maker>\n";
			str += "	<car_disvolume>" + dto.getCar_disvolume() + "</car_disvolume>\n";
			str += "	<car_mile>" + dto.getCar_mile() + "</car_mile>\n";
			str += "	<car_fueltype>" + dto.getCar_fueltype() + "</car_fueltype>\n";
			str += "	<car_numofpeople>" + dto.getCar_numofpeople() + "</car_numofpeople>\n";
			str += "	<car_rentprice>" + dto.getCar_rentprice() + "</car_rentprice>\n";
			str += "	<car_roffice>" + dto.getCar_roffice() + "</car_roffice>\n";
			str += "	<car_detailopic>" + dto.getCar_detailopic() + "</car_detailopic>\n";
			str += "	<car_detailspic>" + dto.getCar_detailspic() + "</car_detailspic>\n";
			str += "	</car>\n";
		}
		str += "</carlist>\n";
		
		System.out.println( str );
	}
	
	private void getCodeName( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException 
	{
		int codeType = Integer.parseInt( request.getParameter( "codeType" ) );
		String codeValues = request.getParameter( "codeValues" ).trim();
		
		System.out.println( "codeValues : " + codeValues );
		
		int code = -1;
		String codeName = "";
		String codeNameValues = "";
		String codeStr[] = codeValues.split( "\\|" );
		int len = codeStr.length;
		System.out.println( "LIST CNT : " + len );
		for ( int i=0; i<len; i++ )
		{
			code = Integer.parseInt( codeStr[i] );
			System.out.println( "code : " + code );
			codeName = CodeProperties.getCodeName( codeType, code );
			codeNameValues += codeName;
			if ( i != len - 1 ) codeNameValues += "|";
		}
		
		response.setContentType( "text/plain;charset=EUC-KR" );
		PrintWriter out = response.getWriter();

		out.println( codeNameValues );
		System.out.println( "codeNameValues : " + codeNameValues );
	}
	
	private void processSend( HttpServletRequest request, HttpServletResponse response, boolean flag, String path ) throws ServletException, IOException 
	{
		if( flag )
			response.sendRedirect( path );
		else{
			RequestDispatcher disp = request.getRequestDispatcher( path );
			disp.forward(request, response);
		}
	}
}
