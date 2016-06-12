package com.bnc.props;

import java.sql.SQLException;
import java.util.List;

import com.bnc.common.model.CodeCarOfficeDTO;
import com.bnc.common.model.CodeGradeDTO;
import com.bnc.common.model.CodeInfoDTO;
import com.bnc.common.model.CommonDAO;
import com.bnc.common.model.ICode;
import com.bnc.reserve.model.InsuranceDTO;

public class CodeProperties
{
	public static final int CAR_GRADE 		= 0;
	public static final int CAR_LO 			= 1;
	public static final int CAR_OFFICE 		= 2;
	public static final int FUEL_TYPE 		= 3;
	public static final int GRADE 			= 4;
	public static final int LEVEL 			= 5;
	public static final int MAKER 			= 6;
	public static final int TOUR_LO 		= 7;
	public static final int NUM_OF_PEOPLE 	= 8;
	public static final int ROOM_SIZE 		= 9;
	public static final int ROOM_TYPE 		= 10;
	public static final int INSURANCE 		= 11;
	
	// 차량 등급 코드 리스트
	public static List<CodeInfoDTO> codeCarGradeList;
	
	// 지점 지역 리스트
	public static List<CodeInfoDTO> codeCarLoList;
	
	// 지점 리스트
	public static List<CodeCarOfficeDTO> codeCarOfficeList;
	
	// 연료타입 리스트
	public static List<CodeInfoDTO> codeFuelTypeList;
	
	// 회원 등급 리스트
	public static List<CodeGradeDTO> codeGradeList;
	
	// 회원 레벨 리스트
	public static List<CodeInfoDTO> codeLevelList;
	
	// 차량 제조사 리스트
	public static List<CodeInfoDTO> codeMakerList;
	
	// 여행상품 지역 리스트
	public static List<CodeInfoDTO> codeTourLoList;
	
	// 숙박인원 리스트
	public static List<CodeInfoDTO> codeNumOfPeopleList;
	
	// 숙박평수 리스트
	public static List<CodeInfoDTO> codeRoomSizeList;
	
	// 숙박타입 리스트
	public static List<CodeInfoDTO> codeRoomTypeList;
	
	// 보험타입 리스트
	public static List<InsuranceDTO> codeInsuranceList;
	
	/**
	 * 코드에 해당하는 코드 이름 반환
	 * @param codeType
	 * @param code
	 * @return
	 */
	public static String getCodeName( int codeType, int code )
	{
		String name = "";
		switch ( codeType )
		{
			case CAR_GRADE :
				name = getName( codeCarGradeList, code );
				break;
			case CAR_LO :
				name = getName( codeCarLoList, code );
				break;
			case CAR_OFFICE :
				name = getNameOffice( codeCarOfficeList, code );
				break;
			case FUEL_TYPE :
				name = getName( codeFuelTypeList, code );
				break;
			case GRADE :
				name = getNameGrade( codeGradeList, code );
				break;
			case LEVEL :
				name = getName( codeLevelList, code );
				break;
			case MAKER :
				name = getName( codeMakerList, code );
				break;
			case TOUR_LO :
				name = getName( codeTourLoList, code );
				break;
			case NUM_OF_PEOPLE :
				name = getName( codeNumOfPeopleList, code );
				break;
			case ROOM_SIZE :
				name = getName( codeRoomSizeList, code );
				break;
			case ROOM_TYPE :
				name = getName( codeRoomTypeList, code );
				break;
		}
		
		return name.trim();
	}
	
	private static String getName( List<CodeInfoDTO> list, int code )
	{
		String name = "";
		for ( CodeInfoDTO dto : list )
		{
			if ( dto.getCode() == code )
			{
				name = dto.getName();
				break;
			}
		}
		
		return name.trim();
	}
	
	private static String getNameOffice( List<CodeCarOfficeDTO> list, int code )
	{
		String name = "";
		for ( CodeCarOfficeDTO dto : list )
		{
			if ( dto.getCode() == code )
			{
				name = dto.getName();
				break;
			}
		}
		
		return name.trim();
	}
	
	private static String getNameGrade( List<CodeGradeDTO> list, int code )
	{
		String name = "";
		for ( CodeGradeDTO dto : list )
		{
			if ( dto.getCode() == code )
			{
				name = dto.getName();
				break;
			}
		}
		
		return name.trim();
	}
	
	/**
	 * 보험 SEQ로 해당 보험 타입 반환
	 * @param seq
	 * @return
	 */
	public static String getTypeInsurance( int seq )
	{
		String type = "";
		for ( InsuranceDTO dto : codeInsuranceList )
		{
			if ( dto.getInsurance_seq() == seq )
			{
				type = dto.getInsurance_type();
				break;
			}
		}
		
		return type.trim();
	}
	
	/**
	 * 보험 SEQ로 해당 보험 가격 반환
	 * @param seq
	 * @return
	 */
	public static int getPriceInsurance( int seq )
	{
		int price = 0;
		for ( InsuranceDTO dto : codeInsuranceList )
		{
			if ( dto.getInsurance_seq() == seq )
			{
				price = dto.getInsurance_price();
				break;
			}
		}
		
		return price;
	}
	
	/**
	 * 코드이름으로 해당 코드를 반환
	 * @param codeType
	 * @param name
	 * @return
	 */
	public static int getCodeInt( int codeType, String name )
	{
		int code = -1;
		switch ( codeType )
		{
			case CAR_GRADE :
				code = getCode( codeCarGradeList, name );
				break;
			case CAR_LO :
				code = getCode( codeCarLoList, name );
				break;
			case CAR_OFFICE :
				code = getCodeOffice( codeCarOfficeList, name );
				break;
			case FUEL_TYPE :
				code = getCode( codeFuelTypeList, name );
				break;
			case GRADE :
				code = getCodeGrade( codeGradeList, name );
				break;
			case LEVEL :
				code = getCode( codeLevelList, name );
				break;
			case MAKER :
				code = getCode( codeMakerList, name );
				break;
			case TOUR_LO :
				code = getCode( codeTourLoList, name );
				break;
			case NUM_OF_PEOPLE :
				code = getCode( codeNumOfPeopleList, name );
				break;
			case ROOM_SIZE :
				code = getCode( codeRoomSizeList, name );
				break;
			case ROOM_TYPE :
				code = getCode( codeRoomTypeList, name );
				break;
		}
		
		return code;
	}
	
	private static int getCode( List<CodeInfoDTO> list, String name )
	{
		int code = -1;
		for ( CodeInfoDTO dto : list )
		{
			if ( dto.getName().equals( name ) )
			{
				code = dto.getCode();
				break;
			}
		}
		
		return code;
	}
	
	private static int getCodeOffice( List<CodeCarOfficeDTO> list, String name )
	{
		int code = -1;
		for ( CodeCarOfficeDTO dto : list )
		{
			if ( dto.getName().equals( name ) )
			{
				code = dto.getCode();
				break;
			}
		}
		
		return code;
	}
	
	private static int getCodeGrade( List<CodeGradeDTO> list, String name )
	{
		int code = -1;
		for ( CodeGradeDTO dto : list )
		{
			if ( dto.getName().equals( name ) )
			{
				code = dto.getCode();
				break;
			}
		}
		
		return code;
	}
	
	/**
	 * 지점별 지역 코드 반환
	 */
	public static int getCodeCarLo( int codeOffice )
	{
		int codeCarLo = -1;
		for ( CodeCarOfficeDTO dto : codeCarOfficeList )
		{
			if ( dto.getCode() == codeOffice )
			{
				codeCarLo = dto.getCarlo_code();
				break;
			}
		}
		
		return codeCarLo;
	}
	
	/**
	 * 회원등급별 아이콘 경로 반환
	 */
	public static String getGradeIcon( int codeGrade )
	{
		String gradeIcon = "";
		for ( CodeGradeDTO dto : codeGradeList )
		{
			if ( dto.getCode() == codeGrade )
			{
				gradeIcon = dto.getGrade_icon();
				break;
			}
		}
		
		return gradeIcon.trim();
	}
	
	/**
	 * 회원등급별 아이콘 경로 반환
	 */
	public static int getGradeDC( int codeGrade )
	{
		int gradeDC = 0;
		for ( CodeGradeDTO dto : codeGradeList )
		{
			if ( dto.getCode() == codeGrade )
			{
				gradeDC = dto.getGrade_dc();
				break;
			}
		}
		
		return gradeDC;
	}
	
	/**
	 * 코드 정보가 모두 초기화되었는지 여부 반환
	 */
	public static boolean hasCodeInit()
	{
		if ( codeCarGradeList == null ) return false;
		if ( codeCarLoList == null ) return false;
		if ( codeCarOfficeList == null ) return false;
		if ( codeFuelTypeList == null ) return false;
		if ( codeGradeList == null ) return false;
		if ( codeLevelList == null ) return false;
		if ( codeMakerList == null ) return false;
		if ( codeNumOfPeopleList == null ) return false;
		if ( codeRoomSizeList == null ) return false;
		if ( codeRoomTypeList == null ) return false;
		if ( codeInsuranceList == null ) return false;
		
		return true;
	}
	
	/**
	 * 코드 정보가 없을 경우 코드 정보 셋팅
	 * @throws SQLException 
	 */
	public static void setCodeList() throws SQLException
	{
		if ( hasCodeInit() ) return;
		
		System.out.println( "--------------------> SET CODE LIST" );
		
		CommonDAO dao = new CommonDAO();
		
		if ( codeCarGradeList == null ) 
			codeCarGradeList = dao.getCodeCarGradeList();
		
		if ( codeCarLoList == null ) 
			codeCarLoList = dao.getCodeCarLoList();
		
		if ( codeCarOfficeList == null ) 
			codeCarOfficeList = dao.getCodeCarOfficeList();
		
		if ( codeFuelTypeList == null ) 
			codeFuelTypeList = dao.getCodeFuelTypeList();
		
		if ( codeGradeList == null ) 
			codeGradeList = dao.getCodeGradeList();
		
		if ( codeLevelList == null ) 
			codeLevelList = dao.getCodeLevelList();
		
		if ( codeMakerList == null ) 
			codeMakerList = dao.getCodeMakerList();
		
		if ( codeTourLoList == null ) 
			codeTourLoList = dao.getCodeTourLoList();
		
		if ( codeNumOfPeopleList == null ) 
			codeNumOfPeopleList = dao.getCodeNumOfPeopleList();
		
		if ( codeRoomSizeList == null ) 
			codeRoomSizeList = dao.getCodeRoomSizeList();
		
		if ( codeRoomTypeList == null ) 
			codeRoomTypeList = dao.getCodeRoomTypeList();
		
		if ( codeInsuranceList == null ) 
			codeInsuranceList = dao.getInsuranceList();
		
		System.out.println( "--------------------> SET CODE LIST COMPLETE" );
	}
}
