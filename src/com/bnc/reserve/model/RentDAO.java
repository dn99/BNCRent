package com.bnc.reserve.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bnc.db.DBClose;
import com.bnc.db.DBConnection;
import com.bnc.rentcar.model.CarInformationDTO;
import com.bnc.sqlmap.SqlMapConfig;
import com.bnc.travel.model.TRentDTO;

public class RentDAO 
{
	private DBConnection dbInstance;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public RentDAO()
	{
		dbInstance = DBConnection.getInstance();
	}
	
	/**
	 * 예약 등록
	 * @param dto
	 * @return
	 */
	public boolean insertRent( RentDTO dto )
	{
		boolean isOK = false;
		conn = dbInstance.getConnection();
		
		String sql = "INSERT INTO TBL_RENT " +
					 "VALUES( TBL_RENT_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
		
		try
		{
			int idx = 0;
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( ++idx, dto.getRent_member_id() );
			pstmt.setInt( ++idx, dto.getRent_car_seq() );
			pstmt.setString( ++idx, dto.getRent_rday() );
			pstmt.setString( ++idx, dto.getRent_rtime() );
			pstmt.setString( ++idx, dto.getRent_retday() );
			pstmt.setString( ++idx, dto.getRent_rettime() );
			pstmt.setInt( ++idx, dto.getRent_rlocation() );
			pstmt.setInt( ++idx, dto.getRent_roffice() );
			pstmt.setInt( ++idx, dto.getRent_retlocation() );
			pstmt.setInt( ++idx, dto.getRent_retoffice() );
			pstmt.setString( ++idx, dto.getRent_navicheck() );
			pstmt.setInt( ++idx, dto.getRent_insurance_seq() );
			pstmt.setInt( ++idx, dto.getRent_totalday() );
			pstmt.setInt( ++idx, dto.getRent_totaltime() );
			pstmt.setInt( ++idx, dto.getRent_price() );
			pstmt.setInt( ++idx, dto.getRent_totalprice() );
			pstmt.setInt( ++idx, dto.getRent_dc() );
			
			int cnt = pstmt.executeUpdate();
			if ( cnt != 0 ) isOK = true;
		} 
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		finally
		{
			DBClose.close( pstmt, conn );
		}
		
		return isOK;
	}
	
	/**
	 * 예약 수정
	 * @param dto
	 * @return
	 */
	public boolean updateRent( RentDTO dto )
	{
		boolean isOK = false;
		conn = dbInstance.getConnection();
		
		String sql = "UPDATE TBL_RENT " +
		"SET RENT_CAR_SEQ=?, RENT_RDAY=?, RENT_RTIME=?, RENT_RETDAY=?, RENT_RETTIME=?, " +
			"RENT_RLOCATION=?, RENT_ROFFICE=?, RENT_RETLOCATION=?, RENT_RETOFFICE=?, " +
			"RENT_NAVICHECK=?, RENT_INSURANCE_SEQ=?, RENT_TOTALDAY=?, RENT_TOTALTIME=? " +
			"RENT_PRICE=?, RENT_TOTALPRICE=?, RENT_DC=?";
		
		try
		{
			int idx = 0;
			pstmt = conn.prepareStatement( sql );
			pstmt.setInt( ++idx, dto.getRent_car_seq() );
			pstmt.setString( ++idx, dto.getRent_rday() );
			pstmt.setString( ++idx, dto.getRent_rtime() );
			pstmt.setString( ++idx, dto.getRent_retday() );
			pstmt.setString( ++idx, dto.getRent_rettime() );
			pstmt.setInt( ++idx, dto.getRent_rlocation() );
			pstmt.setInt( ++idx, dto.getRent_roffice() );
			pstmt.setInt( ++idx, dto.getRent_retlocation() );
			pstmt.setInt( ++idx, dto.getRent_retoffice() );
			pstmt.setString( ++idx, dto.getRent_navicheck() );
			pstmt.setInt( ++idx, dto.getRent_insurance_seq() );
			pstmt.setInt( ++idx, dto.getRent_totalday() );
			pstmt.setInt( ++idx, dto.getRent_totaltime() );
			pstmt.setInt( ++idx, dto.getRent_price() );
			pstmt.setInt( ++idx, dto.getRent_totalprice() );
			pstmt.setInt( ++idx, dto.getRent_dc() );
			
			int cnt = pstmt.executeUpdate();
			if ( cnt != 0 ) isOK = true;
		} 
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
		finally
		{
			DBClose.close( pstmt, conn );
		}
		
		return isOK;
	}
	
	/**
	 * 예약 목록 반환
	 * @param end
	 * @param start
	 * @return
	 */
	public List<RentDTO> getRentList( int end, int start  )
	{
		List<RentDTO> list = new ArrayList<RentDTO>();
		conn = dbInstance.getConnection();
		
		String sql = "";
		sql += "SELECT B.* \n";
		sql += "FROM \n";
		sql += "( \n";
		sql += "    SELECT ROWNUM RN, A.* \n";
		sql += "    FROM \n";
		sql += "    ( \n";
		sql += "        SELECT * \n";
		sql += "        FROM TBL_RENT \n";
		sql += "        ORDER BY RENT_SEQ DESC \n";
		sql += "    ) A \n";
		sql += "    WHERE ROWNUM <= ? \n";
		sql += ") B \n";
		sql += "WHERE B.RN > ? \n";
		try
		{
			int idx = 0;
			pstmt = conn.prepareStatement( sql );
			pstmt.setInt( ++idx, end );
			pstmt.setInt( ++idx, start );
			rs = pstmt.executeQuery();
			
			RentDTO rentDTO = null;
			while ( rs.next() )
			{
				rentDTO = new RentDTO();
				rentDTO.setRent_seq( rs.getInt( "RENT_SEQ" ) );
				rentDTO.setRent_member_id( rs.getString( "RENT_MEMBER_ID" ) );
				rentDTO.setRent_car_seq( rs.getInt( "RENT_CAR_SEQ" ) );
				rentDTO.setRent_rday( rs.getString( "RENT_RDAY" ) );
				rentDTO.setRent_rtime( rs.getString( "RENT_RTIME" ) );
				rentDTO.setRent_retday( rs.getString( "RENT_RETDAY" ) );
				rentDTO.setRent_rettime( rs.getString( "RENT_RETTIME" ) );
				rentDTO.setRent_rlocation( rs.getInt( "RENT_RLOCATION" ) );
				rentDTO.setRent_roffice( rs.getInt( "RENT_ROFFICE" ) );
				rentDTO.setRent_retlocation( rs.getInt( "RENT_RETLOCATION" ) );
				rentDTO.setRent_retoffice( rs.getInt( "RENT_RETOFFICE" ) );
				rentDTO.setRent_navicheck( rs.getString( "RENT_NAVICHECK" ) );
				rentDTO.setRent_insurance_seq( rs.getInt( "RENT_INSURANCE_SEQ" ) );
				rentDTO.setRent_totalday( rs.getInt( "RENT_TOTALDAY" ) );
				rentDTO.setRent_totaltime( rs.getInt( "RENT_TOTALTIME" ) );
				rentDTO.setRent_price( rs.getInt( "RENT_PRICE" ) );
				rentDTO.setRent_totalprice( rs.getInt( "RENT_TOTALPRICE" ) );
				rentDTO.setRent_dc( rs.getInt( "RENT_DC" ) );
				
				list.add( rentDTO );
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
	 * 지점에 해당하는 차량 목록 반환
	 * @param end
	 * @param start
	 * @param roffice
	 * @param grade 
	 * @return
	 * @throws SQLException 
	 */
	public List<CarInformationDTO> getRentCarList( Map<String, Integer> carListInfo ) throws SQLException
	{
		return SqlMapConfig.getSqlMapClient().queryForList( "reserve.getRentCarList", carListInfo );
	}
	/*
	public List<CarInformationDTO> getRentCarList( int end, int start, int roffice, int grade )
	{
		List<CarInformationDTO> list = new ArrayList<CarInformationDTO>();
		conn = dbInstance.getConnection();
		
		String sql = "";
		sql += "SELECT B.* \n";
		sql += "FROM \n";
		sql += "( \n";
		sql += "    SELECT ROWNUM RN, A.* \n";
		sql += "    FROM \n";
		sql += "    ( \n";
		sql += "        SELECT * \n";
		sql += "        FROM TBL_CAR \n";
		sql += "        WHERE CAR_ROFFICE = ? \n";
		sql += "        AND CAR_RENTCHECK = '0' \n";
//		sql += "        AND CAR_GRADE = ? \n";
		sql += "        ORDER BY CAR_SEQ \n";
		sql += "    ) A \n";
//		sql += "    WHERE ROWNUM <= ? \n";
		sql += ") B \n";
//		sql += "WHERE B.RN > ? \n";
		try
		{
			int idx = 0;
			pstmt = conn.prepareStatement( sql );
			pstmt.setInt( ++idx, roffice );
//			pstmt.setInt( ++idx, grade );
//			pstmt.setInt( ++idx, end );
//			pstmt.setInt( ++idx, start );
			rs = pstmt.executeQuery();
			
			CarInformationDTO carinformationDTO = null;
			while ( rs.next() )
			{
				carinformationDTO = new CarInformationDTO();
				carinformationDTO.setCar_seq( rs.getInt("car_seq") );
				carinformationDTO.setCar_name( rs.getString("car_name") );
				carinformationDTO.setCar_opic( rs.getString("car_opic") );
				carinformationDTO.setCar_spic( rs.getString("car_spic") );
				carinformationDTO.setCar_grade( rs.getInt("car_grade") );
				carinformationDTO.setCar_maker( rs.getInt("car_maker") );
				carinformationDTO.setCar_disvolume( rs.getString("car_disvolume") );
				carinformationDTO.setCar_mile( rs.getString("car_mile") );
				carinformationDTO.setCar_fueltype( rs.getInt("car_fueltype") );
				carinformationDTO.setCar_numofpeople( rs.getInt("car_numofpeople") );
				carinformationDTO.setCar_rentprice( rs.getInt("car_rentprice") );
				carinformationDTO.setCar_roffice( rs.getInt("car_roffice") );
				carinformationDTO.setCar_detailopic( rs.getString("car_detailopic") );
				carinformationDTO.setCar_detailspic( rs.getString("car_detailspic") );
				
				list.add( carinformationDTO );
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
	*/
	
	/**
	 * 보험 목록 반환
	 * @return
	 * @throws SQLException 
	 */
	public List<InsuranceDTO> getInsuranceList() throws SQLException
	{
//		return SqlMapConfig.getSqlMapClient().queryForList( "reserve.getInsuranceList" );
		return null;
	}
	/*
	public List<InsuranceDTO> getInsuranceList()
	{
		List<InsuranceDTO> list = new ArrayList<InsuranceDTO>();
		conn = dbInstance.getConnection();
		
		String sql = "SELECT * FROM TBL_INSURANCE ORDER BY INSURANCE_SEQ ASC";
		
		try
		{
			pstmt = conn.prepareStatement( sql );
			rs = pstmt.executeQuery();
			
			InsuranceDTO insuranceDTO = null;
			while ( rs.next() )
			{
				insuranceDTO = new InsuranceDTO();
				insuranceDTO.setInsurance_seq( rs.getInt( "INSURANCE_SEQ" ) );
				insuranceDTO.setInsurance_type( rs.getString( "INSURANCE_TYPE" ) );
				insuranceDTO.setInsurance_price( rs.getInt( "INSURANCE_PRICE" ) );
				
				list.add( insuranceDTO );
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
	*/

	public int getTotalCarCount( int roffice ) throws NumberFormatException, SQLException
	{
		return Integer.parseInt( SqlMapConfig.getSqlMapClient().queryForObject( "reserve.getTotalCarCount", roffice ).toString() );
	}

	public int getTotalRentCount() throws NumberFormatException, SQLException
	{
		return Integer.parseInt( SqlMapConfig.getSqlMapClient().queryForObject( "reserve.getTotalRentCount" ).toString() );
	}

	public int getRentSeq() throws NumberFormatException, SQLException
	{
		return Integer.parseInt( SqlMapConfig.getSqlMapClient().queryForObject( "reserve.getRentSeq" ).toString() );
	}

	public int insertTour( TRentDTO trentDTO ) throws SQLException
	{
		return SqlMapConfig.getSqlMapClient().update( "reserve.insertTour", trentDTO );
	}
}
