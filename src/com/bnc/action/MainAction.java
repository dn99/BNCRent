package com.bnc.action;

import java.util.ArrayList;
import java.util.List;

import com.bnc.community.model.NoticeDTO;
import com.bnc.props.BestItemProperties;
import com.bnc.props.CodeProperties;
import com.bnc.rentcar.model.CarInformationDTO;
import com.bnc.reserve.model.RentDTO;
import com.bnc.travel.model.TravelDTO;
import com.bnc.util.DAOFactory;
import com.bnc.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class MainAction extends ActionSupport
{
	// 공지 사항 리스트
	private List<NoticeDTO> noticeList;
	
	// 베스트 렌트카
	private List<CarInformationDTO> bestCarEventList;
	private List<CarInformationDTO> bestCarNormalList;
	private List<CarInformationDTO> bestCarLineList;
	
	// 베스트 여행상품
	private List<TravelDTO> bestTravelEventList;
	private List<TravelDTO> bestTravelNormalList;
	private List<TravelDTO> bestTravelLineList;
	
	// 렌트카 / 여행상품 리스트
	private List<CarInformationDTO> carList;
	private List<TravelDTO> travelList;
	
	private List<String> rofficeList;
	private List<String> makerList;
	
	public List<NoticeDTO> getNoticeList()
	{
		return noticeList;
	}

	public List<CarInformationDTO> getBestCarEventList()
	{
		return bestCarEventList;
	}
	public List<CarInformationDTO> getBestCarNormalList()
	{
		return bestCarNormalList;
	}
	public List<CarInformationDTO> getBestCarLineList()
	{
		return bestCarLineList;
	}
	
	public List<TravelDTO> getBestTravelEventList()
	{
		return bestTravelEventList;
	}

	public List<TravelDTO> getBestTravelNormalList()
	{
		return bestTravelNormalList;
	}

	public List<TravelDTO> getBestTravelLineList()
	{
		return bestTravelLineList;
	}
	
	public List<CarInformationDTO> getCarList()
	{
		return carList;
	}
	public List<TravelDTO> getTravelList()
	{
		return travelList;
	}
	
	public List<String> getRofficeList()
	{
		return rofficeList;
	}
	public List<String> getMakerList()
	{
		return makerList;
	}
	
	public String executeNoticeList() throws Exception
	{
		noticeList = DAOFactory.getMainDAO().executeNoticeList();
		
		String subject = "";
		for ( NoticeDTO dto : noticeList )
		{
			subject = StringUtil.lengthLimit( dto.getSubject(), 40, ".." );
			dto.setSubject( subject );
		}
		
		System.out.println( "NOTICE TOTAL : " + noticeList.size() + "\t" + noticeList.get( 0 ).getSubject() );
		
		return SUCCESS;
	}
	
	public String executeBestCarList() throws Exception
	{
		bestCarEventList = new ArrayList<CarInformationDTO>();
		bestCarNormalList = new ArrayList<CarInformationDTO>();
		bestCarLineList = new ArrayList<CarInformationDTO>();
		
		String name = "";
		String content = "";
		CarInformationDTO dto = null;
		for ( int seq : BestItemProperties.BEST_CAR_EVENT )
		{
			dto = DAOFactory.getMainDAO().executeBestCar( seq );
			name = StringUtil.lengthLimit( dto.getCar_name(), 10, ".." );
			content = StringUtil.lengthLimit( dto.getCar_content(), 60, ".." );
			dto.setCar_name( name );
			dto.setCar_content( content );
			
			bestCarEventList.add( dto );
		}
		System.out.println( "BEST CAR EVENT TOTAL : " + bestCarEventList.size() );
		
		for ( int seq : BestItemProperties.BEST_CAR_NORMAL )
		{
			dto = DAOFactory.getMainDAO().executeBestCar( seq );
			name = StringUtil.lengthLimit( dto.getCar_name(), 10, ".." );
			content = StringUtil.lengthLimit( dto.getCar_content(), 60, ".." );
			dto.setCar_name( name );
			dto.setCar_content( content );
			
			bestCarNormalList.add( dto );
		}
		System.out.println( "BEST CAR NORMAL TOTAL : " + bestCarNormalList.size() );
		
		for ( int seq : BestItemProperties.BEST_CAR_LINE )
		{
			dto = DAOFactory.getMainDAO().executeBestCar( seq );
			name = StringUtil.lengthLimit( dto.getCar_name(), 10, ".." );
			content = StringUtil.lengthLimit( dto.getCar_content(), 60, ".." );
			dto.setCar_name( name );
			dto.setCar_content( content );
			
			bestCarLineList.add( dto );
		}
		System.out.println( "BEST CAR LINE TOTAL : " + bestCarLineList.size() );
		
		return SUCCESS;
	}
	
	public String executeBestTravelList() throws Exception
	{
		bestTravelEventList = new ArrayList<TravelDTO>();
		bestTravelNormalList = new ArrayList<TravelDTO>();
		bestTravelLineList = new ArrayList<TravelDTO>();
		
		String name = "";
		String content = "";
		TravelDTO dto = null;
		for ( int seq : BestItemProperties.BEST_TRAVEL_EVENT )
		{
			dto = DAOFactory.getMainDAO().executeBestTravel( seq );
			name = StringUtil.lengthLimit( dto.getTour_name(), 10, ".." );
			content = StringUtil.lengthLimit( dto.getTour_content(), 60, ".." );
			dto.setTour_name( name );
			dto.setTour_content( content );
			
			bestTravelEventList.add( dto );
		}
		System.out.println( "BEST TRAVEL EVENT TOTAL : " + bestTravelEventList.size() );
		
		for ( int seq : BestItemProperties.BEST_TRAVEL_NORMAL )
		{
			dto = DAOFactory.getMainDAO().executeBestTravel( seq );
			name = StringUtil.lengthLimit( dto.getTour_name(), 10, ".." );
			content = StringUtil.lengthLimit( dto.getTour_content(), 60, ".." );
			dto.setTour_name( name );
			dto.setTour_content( content );
			
			bestTravelNormalList.add( dto );
		}
		System.out.println( "BEST TRAVEL NORMAL TOTAL : " + bestTravelNormalList.size() );
		
		for ( int seq : BestItemProperties.BEST_TRAVEL_LINE )
		{
			dto = DAOFactory.getMainDAO().executeBestTravel( seq );
			name = StringUtil.lengthLimit( dto.getTour_name(), 10, ".." );
			content = StringUtil.lengthLimit( dto.getTour_content(), 60, ".." );
			dto.setTour_name( name );
			dto.setTour_content( content );
			
			bestTravelLineList.add( dto );
		}
		System.out.println( "BEST TRAVEL LINE TOTAL : " + bestTravelLineList.size() );
		
		return SUCCESS;
	}
	
	public String executeCarList() throws Exception
	{
		carList = DAOFactory.getMainDAO().executeCarList();
		setCarCodeNameList( carList );
		
		System.out.println( "CAR TOTAL : " + carList.size() );
		
		return SUCCESS;
	}
	
	public String executeTravelList() throws Exception
	{
		travelList = DAOFactory.getMainDAO().executeTravelList();
//		setTravelCodeNameList( travelList );
		
		System.out.println( "TRAVEL TOTAL : " + travelList.size() );
		
		return SUCCESS;
	}

	private boolean hasExistCodeNameList()
	{
		if ( rofficeList == null ) return false;
		if ( makerList == null ) return false;
		
		return true;
	}
	
	private void setCarCodeNameList( List<CarInformationDTO> list )
	{
		rofficeList = new ArrayList<String>();
		makerList = new ArrayList<String>();
		
		String roffice = "";
		String maker = "";
		
		for ( CarInformationDTO dto : list )
		{
			roffice = CodeProperties.getCodeName( CodeProperties.CAR_OFFICE, dto.getCar_roffice() );
			rofficeList.add( roffice );
			maker = CodeProperties.getCodeName( CodeProperties.CAR_OFFICE, dto.getCar_maker() );
			makerList.add( maker );
		}
	}
	
	private void setTravelCodeNameList( List<TravelDTO> list )
	{
		rofficeList = new ArrayList<String>();
		makerList = new ArrayList<String>();
		
		String roffice = "";
		String maker = "";
		
		for ( TravelDTO dto : list )
		{
//			roffice = CodeProperties.getCodeName( CodeProperties.CAR_OFFICE, dto.getCar_roffice() );
//			rofficeList.add( roffice );
//			maker = CodeProperties.getCodeName( CodeProperties.CAR_OFFICE, dto.getCar_maker() );
//			makerList.add( maker );
		}
	}

	
}
