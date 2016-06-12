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
	
	// ���� ��� �ڵ� ����Ʈ
	public static List<CodeInfoDTO> codeCarGradeList;
	
	// ���� ���� ����Ʈ
	public static List<CodeInfoDTO> codeCarLoList;
	
	// ���� ����Ʈ
	public static List<CodeCarOfficeDTO> codeCarOfficeList;
	
	// ����Ÿ�� ����Ʈ
	public static List<CodeInfoDTO> codeFuelTypeList;
	
	// ȸ�� ��� ����Ʈ
	public static List<CodeGradeDTO> codeGradeList;
	
	// ȸ�� ���� ����Ʈ
	public static List<CodeInfoDTO> codeLevelList;
	
	// ���� ������ ����Ʈ
	public static List<CodeInfoDTO> codeMakerList;
	
	// �����ǰ ���� ����Ʈ
	public static List<CodeInfoDTO> codeTourLoList;
	
	// �����ο� ����Ʈ
	public static List<CodeInfoDTO> codeNumOfPeopleList;
	
	// ������� ����Ʈ
	public static List<CodeInfoDTO> codeRoomSizeList;
	
	// ����Ÿ�� ����Ʈ
	public static List<CodeInfoDTO> codeRoomTypeList;
	
	// ����Ÿ�� ����Ʈ
	public static List<InsuranceDTO> codeInsuranceList;
	
	/**
	 * �ڵ忡 �ش��ϴ� �ڵ� �̸� ��ȯ
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
	 * ���� SEQ�� �ش� ���� Ÿ�� ��ȯ
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
	 * ���� SEQ�� �ش� ���� ���� ��ȯ
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
	 * �ڵ��̸����� �ش� �ڵ带 ��ȯ
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
	 * ������ ���� �ڵ� ��ȯ
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
	 * ȸ����޺� ������ ��� ��ȯ
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
	 * ȸ����޺� ������ ��� ��ȯ
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
	 * �ڵ� ������ ��� �ʱ�ȭ�Ǿ����� ���� ��ȯ
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
	 * �ڵ� ������ ���� ��� �ڵ� ���� ����
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
