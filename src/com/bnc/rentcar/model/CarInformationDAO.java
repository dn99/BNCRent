package com.bnc.rentcar.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bnc.common.model.CodeInfoDTO;
import com.bnc.db.DBClose;
import com.bnc.db.DBConnection;
import com.bnc.props.CodeProperties;
import com.bnc.travel.model.TravelDTO;

public class CarInformationDAO {
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	CarInformationDTO carinformationDTO;
	public static List<CarInformationDTO> list;
	
	public CarInformationDAO(){
		list = new ArrayList<CarInformationDTO>();
	}
	/////////////관리자 모드 index.jsp 목록 등록 method/////////////////
	public int register(CarInformationDTO carinformationDTO){
		int cnt = 0;
		conn = DBConnection.getInstance().getConnection();
		String sql = "";
		sql +="insert into tbl_car(car_seq, car_name, car_opic, car_spic, car_grade, car_maker, car_disvolume, car_mile, car_fueltype, car_numofpeople, car_rentprice, car_roffice, car_detailopic, car_detailspic, car_content) \n";
		sql +="       values (tbl_car_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) \n";
		try {
			pstmt = conn.prepareStatement(sql);
			int idx = 0;
			pstmt.setString(++idx, carinformationDTO.getCar_name());
			pstmt.setString(++idx, carinformationDTO.getCar_opic());
			pstmt.setString(++idx, carinformationDTO.getCar_spic());
			pstmt.setInt(++idx, carinformationDTO.getCar_grade());
			pstmt.setInt(++idx, carinformationDTO.getCar_maker());
			pstmt.setString(++idx, carinformationDTO.getCar_disvolume());
			pstmt.setString(++idx, carinformationDTO.getCar_mile());
			pstmt.setInt(++idx, carinformationDTO.getCar_fueltype());
			pstmt.setInt(++idx, carinformationDTO.getCar_numofpeople());
			pstmt.setInt(++idx, carinformationDTO.getCar_rentprice());
			pstmt.setInt(++idx, carinformationDTO.getCar_roffice());
			pstmt.setString(++idx, carinformationDTO.getCar_detailopic());
			pstmt.setString(++idx, carinformationDTO.getCar_detailspic());
			pstmt.setString(++idx, carinformationDTO.getCar_content());
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, conn);
		}
		return cnt;
	}
	/////////////관리자 모드 list.jsp 목록 출력 method/////////////////
	public List<CarInformationDTO> getlist(int end, int start) {
		List<CarInformationDTO> list = new ArrayList<CarInformationDTO>();
		conn = DBConnection.getInstance().getConnection();
		String sql = "";
		sql += "select b.* from (select rownum rn, a.* from(select * from tbl_car where car_use='Y' order by car_seq)a where rownum <= ?)b where b.rn >?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			rs = pstmt.executeQuery();
			while(rs.next()){
				CarInformationDTO carinformationDTO = new CarInformationDTO();
				carinformationDTO.setCar_seq(rs.getInt("car_seq"));
				carinformationDTO.setCar_name(rs.getString("car_name"));
				carinformationDTO.setCar_opic(rs.getString("car_opic"));
				carinformationDTO.setCar_spic(rs.getString("car_spic"));
				carinformationDTO.setCar_grade(rs.getInt("car_grade"));
				carinformationDTO.setCar_maker(rs.getInt("car_maker"));
				carinformationDTO.setCar_disvolume(rs.getString("car_disvolume"));
				carinformationDTO.setCar_mile(rs.getString("car_mile"));
				carinformationDTO.setCar_fueltype(rs.getInt("car_fueltype"));
				carinformationDTO.setCar_numofpeople(rs.getInt("car_numofpeople"));
				carinformationDTO.setCar_rentprice(rs.getInt("car_rentprice"));
				carinformationDTO.setCar_roffice(rs.getInt("car_roffice"));
				carinformationDTO.setCar_detailopic(rs.getString("car_detailopic"));
				carinformationDTO.setCar_detailspic(rs.getString("car_detailspic"));
				
				list.add(carinformationDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBClose.close(rs, pstmt, conn);
		}
		return list;
	}
	/////////////사용자 모드 list.jsp 목록 출력 method/////////////////
	public List<CarInformationDTO> getplist(int end, int start, String tab, String key, String word) {
		List<CarInformationDTO> plist = new ArrayList<CarInformationDTO>();
		int word_1 = 0;
		CodeInfoDTO dto = null;
		
		if(key.equals("car_maker")){
		int size = CodeProperties.codeMakerList.size();
		for(int i=0; i<size; i++){
			dto = CodeProperties.codeMakerList.get(i);
			if(dto.getName().trim().equals(word)){
				word_1 = dto.getCode();
				break;
			}
		}
		}else if(key.equals("car_fueltype")){
			int size = CodeProperties.codeFuelTypeList.size();
			for(int i=0; i<size; i++){
				dto = CodeProperties.codeFuelTypeList.get(i);
				if(dto.getName().trim().equals(word)){
					word_1 = dto.getCode();
					break;
				}
			}
		}
		
		
		
		if(key.equals("car_maker")){
			key = "car_maker";
		}else if(key.equals("car_name")){
			key = "car_name";
		}else if(key.equals("car_fueltype")){
			key = "car_fueltype";
		}else if(tab != ""){
			key = "car_grade";
		}
		conn = DBConnection.getInstance().getConnection();
		
		String sql = "";
		sql += "select b.* from (select rownum rn, a.* from(select distinct * from tbl_car where rowid in (select max(rowid) from tbl_car where car_use='Y' group by car_name) \n";
		if(word != "" && key.equals("car_maker")){
			sql += "and "+key+" = ? \n";
		}else if(word != "" && key.equals("car_fueltype")){
			sql += "and "+key+" = ? \n";
		}else if(word != "" && key.equals("car_name")){
			sql += "and upper("+key+") like upper('%'||?||'%') \n";
		}else if(tab != ""){
			sql += "and "+key+" = ? \n";
		}
		sql += ")a where rownum <= ?)b where b.rn >?"; 
		
		System.out.println("사용자>" + sql);
		System.out.println( end + "\t" + start + "\t" + tab + "\t" + key + "\t" + word );
		
		try {
			pstmt = conn.prepareStatement(sql);
			int idx = 1;
			if(word != null && key.equals("car_maker") || word != null && key.equals("car_fueltype")){
				pstmt.setInt(idx++, word_1);
			}else if(word != null && key.equals("car_name")){
				pstmt.setString(idx++, word);
			}else if(tab != ""){
//				pstmt.setString(idx++, tab);
				pstmt.setInt(idx++, Integer.parseInt(tab));
			}
			pstmt.setInt(idx++, end);
			pstmt.setInt(idx++, start);
			rs = pstmt.executeQuery();
			while(rs.next()){
				CarInformationDTO carinformationDTO = new CarInformationDTO();
				carinformationDTO.setCar_seq(rs.getInt("car_seq"));
				carinformationDTO.setCar_name(rs.getString("car_name"));
				carinformationDTO.setCar_opic(rs.getString("car_opic"));
				carinformationDTO.setCar_spic(rs.getString("car_spic"));
				carinformationDTO.setCar_grade(rs.getInt("car_grade"));
				carinformationDTO.setCar_maker(rs.getInt("car_maker"));
				carinformationDTO.setCar_disvolume(rs.getString("car_disvolume"));
				carinformationDTO.setCar_mile(rs.getString("car_mile"));
				carinformationDTO.setCar_fueltype(rs.getInt("car_fueltype"));
				carinformationDTO.setCar_numofpeople(rs.getInt("car_numofpeople"));
				carinformationDTO.setCar_rentprice(rs.getInt("car_rentprice"));
				carinformationDTO.setCar_roffice(rs.getInt("car_roffice"));
				carinformationDTO.setCar_detailopic(rs.getString("car_detailopic"));
				carinformationDTO.setCar_detailspic(rs.getString("car_detailspic"));
				
				plist.add(carinformationDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBClose.close(rs, pstmt, conn);
		}
		return plist;
	}
	////////////////사용자모드 detail.jsp 출력///////////////////////
	public CarInformationDTO getDetailArticle(int seq) {
		CarInformationDTO carinformationDTO = null;
		conn = DBConnection.getInstance().getConnection();
		String sql = "select * from tbl_car \n";
		sql += "      where car_seq = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			if(rs.next()){
				carinformationDTO = new CarInformationDTO();
				carinformationDTO.setCar_seq(rs.getInt("car_seq"));
				carinformationDTO.setCar_name(rs.getString("car_name"));
				carinformationDTO.setCar_spic(rs.getString("car_spic"));
				carinformationDTO.setCar_grade(rs.getInt("car_grade"));
				carinformationDTO.setCar_maker(rs.getInt("car_maker"));
				carinformationDTO.setCar_disvolume(rs.getString("car_disvolume"));
				carinformationDTO.setCar_mile(rs.getString("car_mile"));
				carinformationDTO.setCar_fueltype(rs.getInt("car_fueltype"));
				carinformationDTO.setCar_numofpeople(rs.getInt("car_numofpeople"));
				carinformationDTO.setCar_rentprice(rs.getInt("car_rentprice"));
				carinformationDTO.setCar_roffice(rs.getInt("car_roffice"));
				carinformationDTO.setCar_detailspic(rs.getString("car_detailspic"));
				carinformationDTO.setCar_content(rs.getString("car_content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return carinformationDTO;
	}
	//////////////////관리자모드 삭제 method////////////////////////
	public int deleteArticle(int seq) {
		int cnt = 0;
		
		conn = DBConnection.getInstance().getConnection();
		String sql = "";
		sql += "update tbl_car \n";
		sql += "set car_use = 'N' \n";
		sql += "where car_seq = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);

			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, conn);
		}
		return cnt;
	}
	////////////////페이징 처리 사용자 모드////////////////////
	public int getTotalArticle(String tab, String key, String word) {
		int totA = 0;
		
		int word_1= 0;
		CodeInfoDTO dto = null;
		if(key.equals("car_maker")){
		int size = CodeProperties.codeMakerList.size();
		for(int i=0; i<size; i++){
			dto = CodeProperties.codeMakerList.get(i);
			if(dto.getName().trim().equals(word)){
				word_1 = dto.getCode();
				break;
			}
		}
		}else if(key.equals("car_fueltype")){
			int size = CodeProperties.codeFuelTypeList.size();
			for(int i=0; i<size; i++){
				dto = CodeProperties.codeFuelTypeList.get(i);
				if(dto.getName().trim().equals(word)){
					word_1 = dto.getCode();
					break;
				}
			}
		}
		
		if(key.equals("car_maker")){
			key = "car_maker";
		}else if(key.equals("car_name")){
			key = "car_name";
		}else if(key.equals("car_fueltype")){
			key = "car_fueltype";
		}else if(tab != ""){
			key = "car_grade";
		}
		
		System.out.println( "dao tab : " + tab );
		
		conn = DBConnection.getInstance().getConnection();
		String sql = "select count(*) from (select distinct * from tbl_car where rowid in (select max(rowid) from tbl_car where car_use='Y' group by car_name)) \n";
		if(word != "" && key.equals("car_maker")){
			sql += "where "+key+" = ? \n";
		}else if(word != "" && key.equals("car_fueltype")){
			sql += "where "+key+" = ? \n";
		}else if(word != "" && key.equals("car_name")){
			sql += "where upper("+key+") like upper('%'||?||'%') \n";
		}else if(tab != ""){
			sql += "where "+key+" = ? \n";
		}
		
		System.out.println("사용자페이징>" + sql);
		try {
			pstmt = conn.prepareStatement(sql);
			int idx = 1;
			if(word != null && key.equals("car_maker") || word != null && key.equals("car_fueltype")){
				pstmt.setInt(idx++, word_1);
			}else if(word != null && key.equals("car_name")){
				pstmt.setString(idx++, word);
			}else if(tab != ""){
				pstmt.setInt(idx++, Integer.parseInt(tab));
			}
			rs = pstmt.executeQuery();
			rs.next();
			totA = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totA;
	}
	/////////////////페이징처리 관리자 모드///////////////////
	public int getTotalArticleu() {
		int totA = 0;
		
		conn = DBConnection.getInstance().getConnection();
		String sql = "select count(*) from tbl_car \n";
		sql += "      where car_use = 'Y'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			totA = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totA;
	}
	/////// 관리자 모드 modify.jsp 관련 수정 method //////////
	public CarInformationDTO getModiArticle(int seq) {
		CarInformationDTO carinformationDTO = null;
		conn = DBConnection.getInstance().getConnection();
		String sql = "select * \n";
		sql += "      from tbl_car \n";
		sql += "      where car_seq = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			if(rs.next()){
				carinformationDTO = new CarInformationDTO();
				carinformationDTO.setCar_seq(rs.getInt("car_seq"));
				carinformationDTO.setCar_name(rs.getString("car_name"));
				carinformationDTO.setCar_opic(rs.getString("car_opic"));
				carinformationDTO.setCar_spic(rs.getString("car_spic"));
				carinformationDTO.setCar_grade(rs.getInt("car_grade"));
				carinformationDTO.setCar_maker(rs.getInt("car_maker"));
				carinformationDTO.setCar_disvolume(rs.getString("car_disvolume"));
				carinformationDTO.setCar_mile(rs.getString("car_mile"));
				carinformationDTO.setCar_fueltype(rs.getInt("car_fueltype"));
				carinformationDTO.setCar_numofpeople(rs.getInt("car_numofpeople"));
				carinformationDTO.setCar_rentprice(rs.getInt("car_rentprice"));
				carinformationDTO.setCar_roffice(rs.getInt("car_roffice"));
				carinformationDTO.setCar_detailopic(rs.getString("car_detailopic"));
				carinformationDTO.setCar_detailspic(rs.getString("car_detailspic"));
				carinformationDTO.setCar_content(rs.getString("car_content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, conn);
		}
		return carinformationDTO;
	}
	/////// 관리자 모드 modify.jsp 관련 수정 method //////////
	public int update(CarInformationDTO carinformationDTO) {
		int cnt = 0;
		conn = DBConnection.getInstance().getConnection();
		String sql = "update tbl_car \n";
		sql += "	  set car_name = ?, car_opic = ?, car_spic = ?, car_grade = ?, car_maker = ?, car_disvolume = ?, car_mile = ?, car_fueltype = ?, car_numofpeople = ?, car_rentprice = ?, car_roffice = ?, car_detailopic = ?, car_detailspic = ?, car_content = ? \n";
		sql += "	  where car_seq = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			int idx = 0;
			pstmt.setString(++idx, carinformationDTO.getCar_name());
			pstmt.setString(++idx, carinformationDTO.getCar_opic());
			pstmt.setString(++idx, carinformationDTO.getCar_spic());
			pstmt.setInt(++idx, carinformationDTO.getCar_grade());
			pstmt.setInt(++idx, carinformationDTO.getCar_maker());
			pstmt.setString(++idx, carinformationDTO.getCar_disvolume());
			pstmt.setString(++idx, carinformationDTO.getCar_mile());
			pstmt.setInt(++idx, carinformationDTO.getCar_fueltype());
			pstmt.setInt(++idx, carinformationDTO.getCar_numofpeople());
			pstmt.setInt(++idx, carinformationDTO.getCar_rentprice());
			pstmt.setInt(++idx, carinformationDTO.getCar_roffice());
			pstmt.setString(++idx, carinformationDTO.getCar_detailopic());
			pstmt.setString(++idx, carinformationDTO.getCar_detailspic());
			pstmt.setString(++idx, carinformationDTO.getCar_content());
			pstmt.setInt(++idx, carinformationDTO.getCar_seq());
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBClose.close(pstmt, conn);
		}
		return cnt;
	}
}
