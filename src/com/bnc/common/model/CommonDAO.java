package com.bnc.common.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bnc.db.DBClose;
import com.bnc.db.DBConnection;
import com.bnc.props.AppProperties;
import com.bnc.reserve.model.InsuranceDTO;
import com.bnc.sqlmap.SqlMapConfig;
import com.bnc.util.StringUtil;

public class CommonDAO
{
	private DBConnection dbInstance;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public CommonDAO()
	{
		dbInstance = DBConnection.getInstance();
	}
	
	/**
	 * ���� ��¥/�ð� ���� ��ȯ
	 */
	public String getCurrentDate( String formatDate )
	{
		String currentDate = "";
		conn = dbInstance.getConnection();
		
		String sql = "SELECT TO_CHAR(SYSDATE, '" + formatDate + "') FROM DUAL";
		
		try
		{
			pstmt = conn.prepareStatement( sql );
			rs = pstmt.executeQuery();
			rs.next();
			currentDate = rs.getString( 1 );
		} 
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		finally
		{
			DBClose.close( rs, pstmt, conn );
		}
		
		return currentDate;
	}
	
	/**
	 * ���� ��� �ڵ� ����Ʈ ��ȯ
	 */
	public List<CodeInfoDTO> getCodeCarGradeList()
	{
		List<CodeInfoDTO> list = null;
		conn = dbInstance.getConnection();
		String sql = "SELECT * FROM TBL_CODE_CARGRADE";
		
		try
		{
			pstmt = conn.prepareStatement( sql );
			rs = pstmt.executeQuery();
			
			list = new ArrayList<CodeInfoDTO>();
			CodeInfoDTO dto = null;
			while ( rs.next() )
			{
				dto = new CodeInfoDTO();
				dto.setCode( rs.getInt( "CARGRADE_CODE" ) );
				dto.setName( rs.getString( "CARGRADE_NAME" ) );
				
				list.add( dto );
			}
		} 
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		finally
		{
			DBClose.close( rs, pstmt, conn );
		}
		
		return list;
	}
	
	/**
	 * ���� ���� ����Ʈ ��ȯ
	 */
	public List<CodeInfoDTO> getCodeCarLoList()
	{
		List<CodeInfoDTO> list = null;
		conn = dbInstance.getConnection();
		String sql = "SELECT * FROM TBL_CODE_CARLO";
		
		try
		{
			pstmt = conn.prepareStatement( sql );
			rs = pstmt.executeQuery();
			
			list = new ArrayList<CodeInfoDTO>();
			CodeInfoDTO dto = null;
			while ( rs.next() )
			{
				dto = new CodeInfoDTO();
				dto.setCode( rs.getInt( "CARLO_CODE" ) );
				dto.setName( rs.getString( "CARLO_NAME" ) );
				
				list.add( dto );
			}
		} 
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		finally
		{
			DBClose.close( rs, pstmt, conn );
		}
		
		return list;
	}
	
	/**
	 * ���� ����Ʈ ��ȯ
	 */
	public List<CodeCarOfficeDTO> getCodeCarOfficeList()
	{
		List<CodeCarOfficeDTO> list = null;
		conn = dbInstance.getConnection();
		String sql = "SELECT * FROM TBL_CODE_CAROFFICE";
		
		try
		{
			pstmt = conn.prepareStatement( sql );
			rs = pstmt.executeQuery();
			
			list = new ArrayList<CodeCarOfficeDTO>();
			CodeCarOfficeDTO dto = null;
			while ( rs.next() )
			{
				dto = new CodeCarOfficeDTO();
				dto.setCode( rs.getInt( "CAROFFICE_CODE" ) );
				dto.setName( rs.getString( "CAROFFICE_NAME" ) );
				dto.setCarlo_code( rs.getInt( "CAROFFICE_CARLO_CODE" ) );
				
				list.add( dto );
			}
		} 
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		finally
		{
			DBClose.close( rs, pstmt, conn );
		}
		
		return list;
	}
	
	/**
	 * ����Ÿ�� ����Ʈ ��ȯ
	 */
	public List<CodeInfoDTO> getCodeFuelTypeList()
	{
		List<CodeInfoDTO> list = null;
		conn = dbInstance.getConnection();
		String sql = "SELECT * FROM TBL_CODE_FUELTYPE";
		
		try
		{
			pstmt = conn.prepareStatement( sql );
			rs = pstmt.executeQuery();
			
			list = new ArrayList<CodeInfoDTO>();
			CodeInfoDTO dto = null;
			while ( rs.next() )
			{
				dto = new CodeInfoDTO();
				dto.setCode( rs.getInt( "FUELTYPE_CODE" ) );
				dto.setName( rs.getString( "FUELTYPE_NAME" ) );
				
				list.add( dto );
			}
		} 
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		finally
		{
			DBClose.close( rs, pstmt, conn );
		}
		
		return list;
	}
	
	/**
	 * ȸ����� ����Ʈ ��ȯ
	 */
	public List<CodeGradeDTO> getCodeGradeList()
	{
		List<CodeGradeDTO> list = null;
		conn = dbInstance.getConnection();
		String sql = "SELECT * FROM TBL_CODE_GRADE";
		
		try
		{
			pstmt = conn.prepareStatement( sql );
			rs = pstmt.executeQuery();
			
			list = new ArrayList<CodeGradeDTO>();
			CodeGradeDTO dto = null;
			while ( rs.next() )
			{
				dto = new CodeGradeDTO();
				dto.setCode( rs.getInt( "GRADE_CODE" ) );
				dto.setName( rs.getString( "GRADE_NAME" ) );
				dto.setGrade_icon( rs.getString( "GRADE_ICON" ) );
				dto.setGrade_dc( rs.getInt( "GRADE_DC" ) );
				
				list.add( dto );
			}
		} 
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		finally
		{
			DBClose.close( rs, pstmt, conn );
		}
		
		return list;
	}
	
	/**
	 * ȸ�� ���� ����Ʈ ��ȯ
	 */
	public List<CodeInfoDTO> getCodeLevelList()
	{
		List<CodeInfoDTO> list = null;
		conn = dbInstance.getConnection();
		String sql = "SELECT * FROM TBL_CODE_LEVEL";
		
		try
		{
			pstmt = conn.prepareStatement( sql );
			rs = pstmt.executeQuery();
			
			list = new ArrayList<CodeInfoDTO>();
			CodeInfoDTO dto = null;
			while ( rs.next() )
			{
				dto = new CodeInfoDTO();
				dto.setCode( rs.getInt( "LEVEL_CODE" ) );
				dto.setName( rs.getString( "LEVEL_NAME" ) );
				
				list.add( dto );
			}
		} 
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		finally
		{
			DBClose.close( rs, pstmt, conn );
		}
		
		return list;
	}
	
	/**
	 * ���� ������ ����Ʈ ��ȯ
	 */
	public List<CodeInfoDTO> getCodeMakerList()
	{
		List<CodeInfoDTO> list = null;
		conn = dbInstance.getConnection();
		String sql = "SELECT * FROM TBL_CODE_MAKER";
		
		try
		{
			pstmt = conn.prepareStatement( sql );
			rs = pstmt.executeQuery();
			
			list = new ArrayList<CodeInfoDTO>();
			CodeInfoDTO dto = null;
			while ( rs.next() )
			{
				dto = new CodeInfoDTO();
				dto.setCode( rs.getInt( "MAKER_CODE" ) );
				dto.setName( rs.getString( "MAKER_NAME" ) );
				
				list.add( dto );
			}
		} 
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		finally
		{
			DBClose.close( rs, pstmt, conn );
		}
		
		return list;
	}
	
	/**
	 * �����ǰ ���� ����Ʈ ��ȯ
	 */
	public List<CodeInfoDTO> getCodeTourLoList()
	{
		List<CodeInfoDTO> list = null;
		conn = dbInstance.getConnection();
		String sql = "SELECT * FROM TBL_CODE_TOURLO";
		
		try
		{
			pstmt = conn.prepareStatement( sql );
			rs = pstmt.executeQuery();
			
			list = new ArrayList<CodeInfoDTO>();
			CodeInfoDTO dto = null;
			while ( rs.next() )
			{
				dto = new CodeInfoDTO();
				dto.setCode( rs.getInt( "TOURLO_CODE" ) );
				dto.setName( rs.getString( "TOURLO_NAME" ) );
				
				list.add( dto );
			}
		} 
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		finally
		{
			DBClose.close( rs, pstmt, conn );
		}
		
		return list;
	}
	
	/**
	 * �����ο� ����Ʈ ��ȯ
	 */
	public List<CodeInfoDTO> getCodeNumOfPeopleList()
	{
		List<CodeInfoDTO> list = null;
		conn = dbInstance.getConnection();
		String sql = "SELECT * FROM TBL_CODE_NUMOFPEOPLE";
		
		try
		{
			pstmt = conn.prepareStatement( sql );
			rs = pstmt.executeQuery();
			
			list = new ArrayList<CodeInfoDTO>();
			CodeInfoDTO dto = null;
			while ( rs.next() )
			{
				dto = new CodeInfoDTO();
				dto.setCode( rs.getInt( "NUMOFPEOPLE_CODE" ) );
				dto.setName( rs.getString( "NUMOFPEOPLE_NAME" ) );
				
				list.add( dto );
			}
		} 
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		finally
		{
			DBClose.close( rs, pstmt, conn );
		}
		
		return list;
	}
	
	/**
	 * ������� ����Ʈ ��ȯ
	 */
	public List<CodeInfoDTO> getCodeRoomSizeList()
	{
		List<CodeInfoDTO> list = null;
		conn = dbInstance.getConnection();
		String sql = "SELECT * FROM TBL_CODE_ROOMSIZE";
		
		try
		{
			pstmt = conn.prepareStatement( sql );
			rs = pstmt.executeQuery();
			
			list = new ArrayList<CodeInfoDTO>();
			CodeInfoDTO dto = null;
			while ( rs.next() )
			{
				dto = new CodeInfoDTO();
				dto.setCode( rs.getInt( "ROOMSIZE_CODE" ) );
				dto.setName( rs.getString( "ROOMSIZE_NAME" ) );
				
				list.add( dto );
			}
		} 
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		finally
		{
			DBClose.close( rs, pstmt, conn );
		}
		
		return list;
	}
	
	/**
	 * ������� ����Ʈ ��ȯ
	 */
	public List<CodeInfoDTO> getCodeRoomTypeList()
	{
		List<CodeInfoDTO> list = null;
		conn = dbInstance.getConnection();
		String sql = "SELECT * FROM TBL_CODE_ROOMTYPE";
		
		try
		{
			pstmt = conn.prepareStatement( sql );
			rs = pstmt.executeQuery();
			
			list = new ArrayList<CodeInfoDTO>();
			CodeInfoDTO dto = null;
			while ( rs.next() )
			{
				dto = new CodeInfoDTO();
				dto.setCode( rs.getInt( "ROOMTYPE_CODE" ) );
				dto.setName( rs.getString( "ROOMTYPE_NAME" ) );
				
				list.add( dto );
			}
		} 
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		finally
		{
			DBClose.close( rs, pstmt, conn );
		}
		
		return list;
	}
	
	/**
	 * ���� ��� ��ȯ
	 * @return
	 */
	public List<InsuranceDTO> getInsuranceList() throws SQLException
	{
		return SqlMapConfig.getSqlMapClient().queryForList( "reserve.getInsuranceList" );
	}
	
//	public List<InsuranceDTO> getInsuranceList()
//	{
//		List<InsuranceDTO> list = new ArrayList<InsuranceDTO>();
//		conn = dbInstance.getConnection();
//		
//		String sql = "SELECT * FROM TBL_INSURANCE ORDER BY INSURANCE_SEQ ASC";
//		
//		try
//		{
//			pstmt = conn.prepareStatement( sql );
//			rs = pstmt.executeQuery();
//			
//			InsuranceDTO insuranceDTO = null;
//			while ( rs.next() )
//			{
//				insuranceDTO = new InsuranceDTO();
//				insuranceDTO.setInsurance_seq( rs.getInt( "INSURANCE_SEQ" ) );
//				insuranceDTO.setInsurance_type( rs.getString( "INSURANCE_TYPE" ) );
//				insuranceDTO.setInsurance_price( rs.getInt( "INSURANCE_PRICE" ) );
//				
//				list.add( insuranceDTO );
//			}
//		} 
//		catch ( SQLException e )
//		{
//			e.printStackTrace();
//		} 
//		finally
//		{
//			DBClose.close( rs, pstmt, conn );
//		}
//		
//		return list;
//	}
}
