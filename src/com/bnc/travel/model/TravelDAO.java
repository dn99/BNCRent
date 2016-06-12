package com.bnc.travel.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bnc.common.model.CodeInfoDTO;
import com.bnc.db.DBClose;
import com.bnc.db.DBConnection;
import com.bnc.props.CodeProperties;
import com.bnc.rentcar.model.CarInformationDTO;
import com.bnc.sqlmap.SqlMapConfig;
import com.bnc.util.StringUtil;

public class TravelDAO {
	
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	////////관리자 모드 index.jsp 목록 등록 method/////////////////////////
	public int register(TravelDTO travelDTO) {
		String pic = travelDTO.getTour_opic();
		int cnt = 0;   
		conn = DBConnection.getInstance().getConnection();
		String sql = "	insert into tbl_tour (tour_seq, tour_location, tour_name, tour_tel, tour_addr, tour_roomtype, tour_roomsize, tour_numofpeople, tour_price, tour_opic, tour_spic, tour_detailopic1, tour_detailspic1, tour_detailopic2, tour_detailspic2, tour_content) \n";
		sql += "		values (tbl_tour_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			int idx = 0;
			pstmt.setInt(++idx, travelDTO.getTour_location());
			pstmt.setString(++idx, travelDTO.getTour_name());
			pstmt.setString(++idx, travelDTO.getTour_tel());
			pstmt.setString(++idx, travelDTO.getTour_addr());
			pstmt.setInt(++idx, travelDTO.getTour_roomtype());
			pstmt.setInt(++idx, travelDTO.getTour_roomsize());
			pstmt.setInt(++idx, travelDTO.getTour_numofpeople());
			pstmt.setInt(++idx, travelDTO.getTour_price());
			pstmt.setString(++idx, travelDTO.getTour_opic());
			pstmt.setString(++idx, travelDTO.getTour_spic());
			pstmt.setString(++idx, travelDTO.getTour_detailopic1());
			pstmt.setString(++idx, travelDTO.getTour_detailspic1());
			pstmt.setString(++idx, travelDTO.getTour_detailopic2());
			pstmt.setString(++idx, travelDTO.getTour_detailspic2());
			pstmt.setString(++idx, travelDTO.getTour_content());
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			DBClose.close(pstmt, conn);
		}
		return cnt;
	}
		
	//////// list.jsp 목록 출력 method/////////////////////
	public List<TravelDTO> getList(int end, int start, String key, String word) {
		List<TravelDTO> list = new ArrayList<TravelDTO>();
		int word_1= 0;
		CodeInfoDTO dto = null;
		int size = CodeProperties.codeTourLoList.size();
		for(int i=0; i<size; i++){
			dto = CodeProperties.codeTourLoList.get(i);
			if(dto.getName().trim().equals(word)){
				word_1 = dto.getCode();
				break;
			}
		}
		
		if(key.equals("tour_location")){
			key = "tour_location";
		}else if(key.equals("tour_name")){
			key = "tour_name";
		}else
			key = "";
		conn = DBConnection.getInstance().getConnection();
		
		//String sql = "select b.* from (select rownum rn, a.* from( select  * from tbl_tour order by tour_seq)a where rownum <= ?)b where b.rn >?";
		String sql = "";
		sql += "select b.* from (select rownum rn, a.* from( select  * from tbl_tour \n";
		sql += "where tour_use = 'Y' \n";
		if( !StringUtil.hasNull(word) && key.equals("tour_location")){
			sql += "and "+key+" = ? \n";
		}else if(!StringUtil.hasNull(word) && key.equals("tour_name")){
			sql += "and "+key+" like '%'||?||'%' \n";
		}
		sql += "order by tour_seq desc)a where rownum <= ?)b where b.rn >?";
		
		System.out.println( "SQL : " + sql );
		System.out.println( "word_1 : " + word_1 );
		
		try {
			int idx = 1;
			pstmt = conn.prepareStatement(sql);
			if( !StringUtil.hasNull(word) && key.equals("tour_location")){
				pstmt.setInt(idx++, word_1);
			}else if(!StringUtil.hasNull(word) && key.equals("tour_name")){
				pstmt.setString(idx++, word);
			}
			pstmt.setInt(idx++, end);
			pstmt.setInt(idx++, start);
			rs = pstmt.executeQuery();
			while(rs.next()){
				TravelDTO travelDTO = new TravelDTO();
				travelDTO.setTour_seq(rs.getInt("tour_seq"));
				travelDTO.setTour_location(rs.getInt("tour_location"));
				travelDTO.setTour_name(rs.getString("tour_name"));
				travelDTO.setTour_tel(rs.getString("tour_tel"));
				travelDTO.setTour_addr(rs.getString("tour_addr"));
				travelDTO.setTour_roomtype(rs.getInt("tour_roomtype"));
				travelDTO.setTour_roomsize(rs.getInt("tour_roomsize"));
				travelDTO.setTour_numofpeople(rs.getInt("tour_numofpeople"));
				travelDTO.setTour_price(rs.getInt("tour_price"));
				travelDTO.setTour_opic(rs.getString("tour_opic"));
				travelDTO.setTour_spic(rs.getString("tour_spic"));
				travelDTO.setTour_detailopic1(rs.getString("tour_detailopic1"));
				travelDTO.setTour_detailspic1(rs.getString("tour_detailspic1"));
				travelDTO.setTour_detailopic2(rs.getString("tour_detailopic2"));
				travelDTO.setTour_detailspic2(rs.getString("tour_detailspic2"));
				travelDTO.setTour_content(rs.getString("tour_content"));
				travelDTO.setTour_use(rs.getString("tour_use"));
				
				list.add(travelDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, conn);
		}
		return list;
	}
	
	
	/////// 관리자 모드 modify.jsp 관련 수정 method //////////
	public TravelDTO getModiArticle(int seq) {
		TravelDTO travelDTO = null;
		conn = DBConnection.getInstance().getConnection();
		String sql = "select * \n";
		sql += " from tbl_tour \n";
		sql += " where tour_seq = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			if(rs.next()){
				travelDTO = new TravelDTO();
				travelDTO.setTour_seq(rs.getInt("tour_seq"));
				travelDTO.setTour_location(rs.getInt("tour_location"));
				travelDTO.setTour_name(rs.getString("tour_name"));
				travelDTO.setTour_tel(rs.getString("tour_tel"));
				travelDTO.setTour_addr(rs.getString("tour_addr"));
				travelDTO.setTour_roomtype(rs.getInt("tour_roomtype"));
				travelDTO.setTour_roomsize(rs.getInt("tour_roomsize"));
				travelDTO.setTour_numofpeople(rs.getInt("tour_numofpeople"));
				travelDTO.setTour_price(rs.getInt("tour_price"));
				travelDTO.setTour_opic(rs.getString("tour_opic"));
				travelDTO.setTour_spic(rs.getString("tour_spic"));
				travelDTO.setTour_detailopic1(rs.getString("tour_detailopic1"));
				travelDTO.setTour_detailspic1(rs.getString("tour_detailspic1"));
				travelDTO.setTour_detailopic2(rs.getString("tour_detailopic2"));
				travelDTO.setTour_detailspic2(rs.getString("tour_detailspic2"));
				travelDTO.setTour_content(rs.getString("tour_content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, conn);
		}
		return travelDTO;
	}

	/////// 관리자 모드 modify.jsp 관련 수정 method //////////
	public TravelDTO getDetailArticle(int seq) throws SQLException {
		return ( TravelDTO )SqlMapConfig.getSqlMapClient().queryForObject("travel.getTravelDetail", seq);
	}
	/*
	public TravelDTO getDetailArticle(int seq) {
		TravelDTO travelDTO = null;
		conn = DBConnection.getInstance().getConnection();
		String sql = "select tour_detailspic from tbl_tour \n";
		sql += " where tour_seq = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			if(rs.next()){
				travelDTO = new TravelDTO();
				travelDTO.setTour_detailspic(rs.getString("tour_detailspic"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return travelDTO;
	}
	*/

/////// 관리자 모드 삭제 method //////////
	public int deleteArticle(int seq) {
		int cnt = 0;
				
		conn = DBConnection.getInstance().getConnection();
//		String sql = "delete from tbl_trent A \n";
//		sql += "	where exists (select * from tbl_tour B \n";
//		sql += "	where A.trent_tour_seq = ?)";
		
		try {
//			conn.setAutoCommit(false);
//			String rent_sql = "";
//			rent_sql += "";
//			
//			String trent_sql = "delete from tbl_trent \n";
//			trent_sql += "	where trent_tour_seq = ?";
//			pstmt = conn.prepareStatement(trent_sql);
//			pstmt.setInt(1, seq);
//
//			cnt = pstmt.executeUpdate();
//			
//			String tour_sql = "delete from tbl_tour \n";
//			tour_sql += "	where tour_seq = ?";
//			
//			pstmt = conn.prepareStatement(tour_sql);
//			pstmt.setInt(1, seq);
//			
//			conn.commit();
//			
//			cnt = 2;
			String sql = "UPDATE TBL_TOUR \n";
			sql += "	SET TOUR_USE = 'N' \n";
			sql += "	WHERE TOUR_SEQ=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			
			cnt = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, conn);
		}
		System.out.println("DAO delete sql ====>>>> \n count ===>>>" + cnt );
	
			return cnt;
	}
	
	/////// 관리자 모드 modify.jsp 관련 수정 method //////////
	public int update(TravelDTO travelDTO) {
		int cnt = 0;
		conn = DBConnection.getInstance().getConnection();
		String sql = "update tbl_tour \n";
		sql += "	set tour_location = ?, tour_name = ?, tour_tel = ?, tour_addr = ?, tour_roomtype = ?, tour_roomsize = ?, tour_numofpeople = ?, tour_price = ?, tour_opic = ?, tour_spic = ?, tour_detailopic1 = ?, tour_detailspic1 = ?,tour_detailopic2 = ?, tour_detailspic2 = ?, tour_content = ? \n";
		sql += "	where tour_seq = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			int idx = 0;
			pstmt.setInt(++idx, travelDTO.getTour_location());
			pstmt.setString(++idx, travelDTO.getTour_name());
			pstmt.setString(++idx, travelDTO.getTour_tel());
			pstmt.setString(++idx, travelDTO.getTour_addr());
			pstmt.setInt(++idx, travelDTO.getTour_roomtype());
			pstmt.setInt(++idx, travelDTO.getTour_roomsize());
			pstmt.setInt(++idx, travelDTO.getTour_numofpeople());
			pstmt.setInt(++idx, travelDTO.getTour_price());
			pstmt.setString(++idx, travelDTO.getTour_opic());
			pstmt.setString(++idx, travelDTO.getTour_spic());
			pstmt.setString(++idx, travelDTO.getTour_detailopic1());
			pstmt.setString(++idx, travelDTO.getTour_detailspic1());
			pstmt.setString(++idx, travelDTO.getTour_detailopic2());
			pstmt.setString(++idx, travelDTO.getTour_detailspic2());
			pstmt.setString(++idx, travelDTO.getTour_content());
			pstmt.setInt(++idx, travelDTO.getTour_seq());
			
			cnt = pstmt.executeUpdate();
			System.out.println("수정 cnt =======>>>>> " + cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBClose.close(pstmt, conn);
		}
		return cnt;
	}

	public int getTotalArticle(String key, String word) {
		int totA = 0;
		
		int word_1= 0;
		CodeInfoDTO dto = null;
		int size = CodeProperties.codeTourLoList.size();
		for(int i=0; i<size; i++){
			dto = CodeProperties.codeTourLoList.get(i);
			if(dto.getName().trim().equals(word)){
				word_1 = dto.getCode();
				break;
			}
		}
		if(key.equals("tour_location")){
			key = "tour_location";
		}else if(key.equals("tour_name")){
			key = "tour_name";
		}else 
			key = "";
		
		conn = DBConnection.getInstance().getConnection();
		String sql = "select count(*) from tbl_tour \n";
		sql += "where tour_use = 'Y' \n";
		if(!StringUtil.hasNull(word) && key.equals("tour_location")){
			sql += "and "+key+" = ? \n";
		}else if(!StringUtil.hasNull(word) && key.equals("tour_name")){
			sql += "and "+key+" like '%'||?||'%' \n";
		}
		try {
			pstmt = conn.prepareStatement(sql);
			int idx = 1;
			if(!StringUtil.hasNull(word) && key.equals("tour_location")){
				pstmt.setInt(idx++, word_1);
			}else if(!StringUtil.hasNull(word) && key.equals("tour_name")){
				pstmt.setString(idx++, word);
			}
			rs = pstmt.executeQuery();
			rs.next();
			totA = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBClose.close(rs, pstmt, conn);
		}
		return totA;
	}

	public List<CarInformationDTO> getRentCarList(int end, int start, int roffice, int grade) {
		List<CarInformationDTO> list = new ArrayList<CarInformationDTO>();
		conn = DBConnection.getInstance().getConnection();
		
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
		sql += "        ORDER BY CAR_SEQ \n";
		sql += "    ) A \n";
		sql += ") B \n";

		try
		{
			int idx = 0;
			pstmt = conn.prepareStatement( sql );
			pstmt.setInt( ++idx, roffice );

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

//	public int getTotalSearchArticleNo(String key, int word) {
//		int searchCount = 0;
//		conn = DBConnection.getInstance().getConnection();
//		String sql = "select count(*) from tbl_tour where "+key+" like '%"+word+"%'" ;
//		try {
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(sql);
//			rs.next();
//			searchCount = rs.getInt(1);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			DBClose.close(rs, pstmt, conn);
//		}		
//		return searchCount;
//	}
//
//	public int getTotalSearchArticleNumber(String key, String word) {
//		int searchCount = 0;
//		conn = DBConnection.getInstance().getConnection();
//		String sql = "select count(*) from tbl_tour where upper("+key+") like upper('%"+word+"%')" ;
//		try {
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(sql);
//			rs.next();
//			searchCount = rs.getInt(1);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			DBClose.close(rs, pstmt, conn);
//		}		
//		return searchCount;
//	}

//	public List<TravelDTO> getSearchArticleList(String key, String word, int currentPage, int pageArticleNo) {
//		List<TravelDTO> list = new ArrayList<TravelDTO>();
//		conn = DBConnection.getInstance().getConnection();
//		
//		String sql = "select * from tbl_tour where upper("+key+") like upper('%"+word+"%')" ;
//		int searchArticleNo = 0;
//		try {
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(sql);
//			while(rs.next()){
//				if((searchArticleNo/pageArticleNo+1)==currentPage){
//				TravelDTO travelDTO = new TravelDTO();
//				travelDTO.setSeq(rs.getInt("tour_seq"));
//				travelDTO.setTour_location(rs.getInt("tour_location"));
//				travelDTO.setTour_name(rs.getString("tour_name"));
//				travelDTO.setTour_tel(rs.getString("tour_tel"));
//				travelDTO.setTour_addr(rs.getString("tour_addr"));
//				travelDTO.setTour_roomtype(rs.getInt("tour_roomtype"));
//				travelDTO.setTour_roomsize(rs.getInt("tour_roomsize"));
//				travelDTO.setTour_numofpeople(rs.getInt("tour_numofpeople"));
//				travelDTO.setTour_price(rs.getInt("tour_price"));
//				travelDTO.setTour_opic(rs.getString("tour_opic"));
//				travelDTO.setTour_spic(rs.getString("tour_spic"));
//				travelDTO.setTour_detailopic(rs.getString("tour_detailopic"));
//				travelDTO.setTour_detailspic(rs.getString("tour_detailspic"));
//				
//				list.add(travelDTO);
//				}
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return list;
//	}
//
//	public List<TravelDTO> getSearchArticleListn(String key, int word_1, int currentPage, int pageArticleNo) {
//		List<TravelDTO> list = new ArrayList<TravelDTO>();
//		conn = DBConnection.getInstance().getConnection();
//		
//		String sql = "select * from tbl_tour where upper("+key+") like upper('%"+word_1+"%')" ;
//		int searchArticleNo = 0;
//		try {
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(sql);
//			while(rs.next()){
//				if((searchArticleNo/pageArticleNo+1)==currentPage){
//				TravelDTO travelDTO = new TravelDTO();
//				travelDTO.setSeq(rs.getInt("tour_seq"));
//				travelDTO.setTour_location(rs.getInt("tour_location"));
//				travelDTO.setTour_name(rs.getString("tour_name"));
//				travelDTO.setTour_tel(rs.getString("tour_tel"));
//				travelDTO.setTour_addr(rs.getString("tour_addr"));
//				travelDTO.setTour_roomtype(rs.getInt("tour_roomtype"));
//				travelDTO.setTour_roomsize(rs.getInt("tour_roomsize"));
//				travelDTO.setTour_numofpeople(rs.getInt("tour_numofpeople"));
//				travelDTO.setTour_price(rs.getInt("tour_price"));
//				travelDTO.setTour_opic(rs.getString("tour_opic"));
//				travelDTO.setTour_spic(rs.getString("tour_spic"));
//				travelDTO.setTour_detailopic(rs.getString("tour_detailopic"));
//				travelDTO.setTour_detailspic(rs.getString("tour_detailspic"));
//				
//				list.add(travelDTO);
//				}
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return list;
//	}

	

}














