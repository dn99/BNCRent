package com.bnc.member.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.bnc.db.DBClose;
import com.bnc.db.DBConnection;
import com.bnc.reserve.model.RentDTO;
import com.bnc.travel.model.TRentDTO;


public class MemberDAO {
	
	Connection conn;
	PreparedStatement pstmt; //인터페이스는 PreparedStatement "d"
	ResultSet rs;

	
	public MemberDAO(){
		
		
	}

	public int register(MemberDTO memberDTO) {
		int cnt=0;
		conn= DBConnection.getInstance().getConnection();
		String sql = "insert into tbl_member(member_id, member_name, member_pass, member_gender, member_zip1, member_zip2, member_phone1, member_phone2, member_phone3, \n" ;
		sql += "member_tel1, member_tel2, member_tel3, member_addr1, member_addr2, member_email1, member_email2, member_pnum1, member_pnum2, \n";
		sql += "member_dltype, member_dlnum1, member_dlnum2, member_dlnum3, member_dlyear, member_dlmonth, member_dlday, member_grade, member_level,member_joindate) \n";
		sql +=       "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)"; 
		try {
			pstmt= conn.prepareStatement(sql);//PrepareStatement 'd'가 빠졌음.
			int idx=0;
			pstmt.setString(++idx, memberDTO.getMember_id()); //id
			pstmt.setString(++idx, memberDTO.getMember_name()); //이름
			pstmt.setString(++idx, memberDTO.getMember_pass());//비밀번호
			pstmt.setString(++idx, memberDTO.getMember_gender()); //성별
			pstmt.setString(++idx, memberDTO.getMember_zip1()); //우편번호1
			pstmt.setString(++idx, memberDTO.getMember_zip2()); //우편번호2
			pstmt.setString(++idx, memberDTO.getMember_phone1()); // 핸드폰1
			pstmt.setString(++idx, memberDTO.getMember_phone2()); // 핸드폰2
			pstmt.setString(++idx, memberDTO.getMember_phone3()); // 핸드폰3
			pstmt.setString(++idx, memberDTO.getMember_tel1()); //전화1
			pstmt.setString(++idx, memberDTO.getMember_tel2()); //전화2
			pstmt.setString(++idx, memberDTO.getMember_tel3()); //전화3
			pstmt.setString(++idx, memberDTO.getMember_addr1()); //주소1
			pstmt.setString(++idx, memberDTO.getMember_addr2()); //주소2
			pstmt.setString(++idx, memberDTO.getMember_email1()); //이메일1
			pstmt.setString(++idx, memberDTO.getMember_email2()); //이메일2
			pstmt.setString(++idx, memberDTO.getMember_pnum1()); // 주민등록앞자리
			pstmt.setString(++idx, memberDTO.getMember_pnum2()); // 주민등록 뒷자리
			pstmt.setString(++idx, memberDTO.getMember_dltype()); // 면허종류
			pstmt.setString(++idx, memberDTO.getMember_dlnum1()); //면허번호 앞자리 2개
			pstmt.setString(++idx, memberDTO.getMember_dlnum2()); //면허번호 중간자리 6개
			pstmt.setString(++idx, memberDTO.getMember_dlnum3()); //면허번호 뒷자리 2개
			pstmt.setString(++idx, memberDTO.getMember_dlyear()); //면허취득년
			pstmt.setString(++idx, memberDTO.getMember_dlmonth()); //면허취득월
			pstmt.setString(++idx, memberDTO.getMember_dlday());//면허취득일
			pstmt.setInt(++idx, memberDTO.getMember_grade()); //면허취득월
			pstmt.setInt(++idx, memberDTO.getMember_level());//면허취득일

			cnt=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBClose.close(pstmt, conn);
		}
		return cnt;
	}

	public MemberDTO login(String id, String pass) {
		MemberDTO memberDTO= null;
		conn= DBConnection.getInstance().getConnection();
		String sql= "select member_id, member_name, member_pass, member_gender, member_zip1, member_zip2, member_phone1, member_phone2, member_phone3, \n" ;
		sql += "member_tel1, member_tel2, member_tel3, member_addr1, member_addr2, member_email1, member_email2, member_pnum1, member_pnum2, \n";
		sql += "member_dltype, member_dlnum1, member_dlnum2, member_dlnum3, member_dlyear, member_dlmonth, member_dlday, member_grade, member_level, member_joindate \n";
		sql += "from tbl_member \n";
		sql += "	where member_id=? and member_pass=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			rs= pstmt.executeQuery();
			if(rs.next()){
				memberDTO= new MemberDTO();
				memberDTO.setMember_name(rs.getString("member_name"));
				memberDTO.setMember_id(rs.getString("member_id"));
				memberDTO.setMember_pass(rs.getString("member_pass"));
				memberDTO.setMember_pnum1(rs.getString("member_pnum1"));
				memberDTO.setMember_pnum2(rs.getString("member_pnum2"));
				memberDTO.setMember_gender(rs.getString("member_gender"));
				memberDTO.setMember_zip1(rs.getString("member_zip1"));
				memberDTO.setMember_zip2(rs.getString("member_zip2"));
				memberDTO.setMember_addr1(rs.getString("member_addr1"));
				memberDTO.setMember_addr2(rs.getString("member_addr2"));
				memberDTO.setMember_email1(rs.getString("member_email1"));
				memberDTO.setMember_email2(rs.getString("member_email2"));
				memberDTO.setMember_tel1(rs.getString("member_tel1"));
				memberDTO.setMember_tel2(rs.getString("member_tel2"));
				memberDTO.setMember_tel3(rs.getString("member_tel3"));
				memberDTO.setMember_phone1(rs.getString("member_phone1"));
				memberDTO.setMember_phone2(rs.getString("member_phone2"));
				memberDTO.setMember_phone3(rs.getString("member_phone3"));
				memberDTO.setMember_dltype(rs.getString("member_dltype"));
				memberDTO.setMember_dlnum1(rs.getString("member_dlnum1"));
				memberDTO.setMember_dlnum2(rs.getString("member_dlnum2"));
				memberDTO.setMember_dlnum3(rs.getString("member_dlnum3"));
				memberDTO.setMember_dlyear(rs.getString("member_dlyear"));
				memberDTO.setMember_dlmonth(rs.getString("member_dlmonth"));
				memberDTO.setMember_dlday(rs.getString("member_dlday"));
				memberDTO.setMember_grade(rs.getInt("member_grade"));
				memberDTO.setMember_level(rs.getInt("member_level"));
				
			
			}
		} catch (SQLException e){
			e.printStackTrace();
		} finally{
			DBClose.close(rs,pstmt,conn);
		}
		
		return memberDTO;
	}

	public List<ZipDTO> getZipList(String dong) {
		List<ZipDTO> list= new ArrayList<ZipDTO>();
		conn= DBConnection.getInstance().getConnection();
		String sql= "";
		   sql = "select substr(zipcode, 1, instr(zipcode, '-')-1) as zip1, \n";
	       sql += "         substr(zipcode, instr(zipcode, '-')+1) as zip2, \n";
	       sql += "         sido, gugun, dong, nvl(bunji, ' ') as bunji \n";
	       sql += "from post \n";
	       sql += "where dong like '%'||?||'%'";
	       System.out.println(sql);
	       try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dong);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ZipDTO zipDTO= new ZipDTO();
				zipDTO.setZip1(rs.getString("zip1"));
				zipDTO.setZip2(rs.getString("zip2"));
				zipDTO.setSido(rs.getString("sido"));
				zipDTO.setGugun(rs.getString("gugun"));
				zipDTO.setDong(rs.getString("dong"));
				zipDTO.setBunji(rs.getString("bunji"));
				
				list.add(zipDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, conn);
		}
	      
	   return list;
	}

	public int idcheck(String id) {
		int cnt=1;
		conn= DBConnection.getInstance().getConnection();
		String sql="";
		sql+= "select count(member_id) from tbl_member \n";
		sql+= "	where member_id=?";
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			rs.next();
			cnt=rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, conn);
		}
		return cnt;
	}

	public int modify(MemberDTO memberDTO) {
		int cnt=0;
		conn= DBConnection.getInstance().getConnection();
		String sql = "update tbl_member \n" ;
		sql += "set member_name=?, member_pass=?, member_gender=?, member_zip1=?, member_zip2=?, member_phone1=?, member_phone2=?, member_phone3=?,\n"; 
		sql += "member_tel1=?, member_tel2=?, member_tel3=?, member_addr1=?, member_addr2=?, member_email1=?, member_email2=?, member_pnum1=?, member_pnum2=?, member_dltype=?,\n";
		sql += "member_dlnum1=?, member_dlnum2=?, member_dlnum3=?, member_dlyear=?, member_dlmonth=?, member_dlday=?, member_grade=?, member_level=? \n"; 
		sql += "where member_id=?";
		
		try {
			pstmt= conn.prepareStatement(sql);//PrepareStatement 'd'가 빠졌음.
			int idx=0;
			pstmt.setString(++idx, memberDTO.getMember_name()); //이름
			pstmt.setString(++idx, memberDTO.getMember_pass());//비밀번호
			pstmt.setString(++idx, memberDTO.getMember_gender()); //성별
			pstmt.setString(++idx, memberDTO.getMember_zip1()); //우편번호1
			pstmt.setString(++idx, memberDTO.getMember_zip2()); //우편번호2
			pstmt.setString(++idx, memberDTO.getMember_phone1()); // 핸드폰1
			pstmt.setString(++idx, memberDTO.getMember_phone2()); // 핸드폰2
			pstmt.setString(++idx, memberDTO.getMember_phone3()); // 핸드폰3
			pstmt.setString(++idx, memberDTO.getMember_tel1()); //전화1
			pstmt.setString(++idx, memberDTO.getMember_tel2()); //전화2
			pstmt.setString(++idx, memberDTO.getMember_tel3()); //전화3
			pstmt.setString(++idx, memberDTO.getMember_addr1()); //주소1
			pstmt.setString(++idx, memberDTO.getMember_addr2()); //주소2
			pstmt.setString(++idx, memberDTO.getMember_email1()); //이메일1
			pstmt.setString(++idx, memberDTO.getMember_email2()); //이메일2
			pstmt.setString(++idx, memberDTO.getMember_pnum1()); // 주민등록앞자리
			pstmt.setString(++idx, memberDTO.getMember_pnum2()); // 주민등록 뒷자리
			pstmt.setString(++idx, memberDTO.getMember_dltype()); // 면허종류
			pstmt.setString(++idx, memberDTO.getMember_dlnum1()); //면허번호 앞자리 2개
			pstmt.setString(++idx, memberDTO.getMember_dlnum2()); //면허번호 중간자리 6개
			pstmt.setString(++idx, memberDTO.getMember_dlnum3()); //면허번호 뒷자리 2개
			pstmt.setString(++idx, memberDTO.getMember_dlyear()); //면허취득년
			pstmt.setString(++idx, memberDTO.getMember_dlmonth()); //면허취득월
			pstmt.setString(++idx, memberDTO.getMember_dlday());//면허취득일
			pstmt.setInt(++idx, memberDTO.getMember_grade()); //면허취득월
			pstmt.setInt(++idx, memberDTO.getMember_level());//면허취득일
			pstmt.setString (++idx, memberDTO.getMember_id()); //id

			cnt=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBClose.close(pstmt, conn);
		}
		return cnt;
	}	
	
	
	
	public List<RentDTO> bookinglist(String id, int end, int start, String key, String word){
		List<RentDTO> list = new ArrayList<RentDTO>();
		conn= DBConnection.getInstance().getConnection();
//		String sql= "select r.rent_seq, r.rent_member_id, r.rent_car_seq, \n";
//		sql += "r.rent_rday, r.rent_roffice, r.rent_retoffice, r.rent_retday, r.rent_totalprice, e.car_name \n";
//		sql += "from tbl_rent r, tbl_car e\n";
//		sql += "where r.rent_car_seq=e.car_seq\n" ;
//		sql += "and r.rent_member_id=?";
		
		String sql= "select * from( \n";
		sql +=  "select b.* from (select rownum rn, a.* from( select  * from tbl_rent where rent_member_id=? \n";
		sql += "order by rent_seq desc)a where rownum <= ?)b where b.rn >? )\n";
		
		
		try {
			int idx= 1;
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(idx++, id);
			pstmt.setInt(idx++, end);
			pstmt.setInt(idx++, start);
			
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBClose.close(rs, pstmt, conn);
		}
		return list;
	}

	public int delete(String id, String pass, String name, String pnum1,String pnum2) {
		int cnt =0 ;
		
		conn= DBConnection.getInstance().getConnection();
		String sql= "delete from tbl_member\n";
		sql += "where member_id=? and member_pass=? and member_name=? and member_pnum1=? and member_pnum2=? \n";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1 ,id);
			pstmt.setString(2 ,pass);
			pstmt.setString(3 ,name);
			pstmt.setString(4 ,pnum1);
			pstmt.setString(5 ,pnum2);
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, conn);
		}
		return cnt;
	}
	
	public List<MemberDTO> list_admin(String id, int end, int start, String key, String word){
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		conn= DBConnection.getInstance().getConnection();
		String sql= "select * from(select b.* from (select rownum rn, a.* from( select  * from tbl_member order by member_id desc)a where rownum <= ?)b where b.rn >? )\n"; 
		
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			rs = pstmt.executeQuery();
		
			MemberDTO memberDTO = null;
			while ( rs.next() ){
				memberDTO= new MemberDTO();
				memberDTO.setMember_name(rs.getString("member_name"));
				memberDTO.setMember_id(rs.getString("member_id"));
				memberDTO.setMember_pass(rs.getString("member_pass"));
				memberDTO.setMember_pnum1(rs.getString("member_pnum1"));
				memberDTO.setMember_pnum2(rs.getString("member_pnum2"));
				memberDTO.setMember_gender(rs.getString("member_gender"));
				memberDTO.setMember_zip1(rs.getString("member_zip1"));
				memberDTO.setMember_zip2(rs.getString("member_zip2"));
				memberDTO.setMember_addr1(rs.getString("member_addr1"));
				memberDTO.setMember_addr2(rs.getString("member_addr2"));
				memberDTO.setMember_email1(rs.getString("member_email1"));
				memberDTO.setMember_email2(rs.getString("member_email2"));
				memberDTO.setMember_tel1(rs.getString("member_tel1"));
				memberDTO.setMember_tel2(rs.getString("member_tel2"));
				memberDTO.setMember_tel3(rs.getString("member_tel3"));
				memberDTO.setMember_phone1(rs.getString("member_phone1"));
				memberDTO.setMember_phone2(rs.getString("member_phone2"));
				memberDTO.setMember_phone3(rs.getString("member_phone3"));
				memberDTO.setMember_dltype(rs.getString("member_dltype"));
				memberDTO.setMember_dlnum1(rs.getString("member_dlnum1"));
				memberDTO.setMember_dlnum2(rs.getString("member_dlnum2"));
				memberDTO.setMember_dlnum3(rs.getString("member_dlnum3"));
				memberDTO.setMember_dlyear(rs.getString("member_dlyear"));
				memberDTO.setMember_dlmonth(rs.getString("member_dlmonth"));
				memberDTO.setMember_dlday(rs.getString("member_dlday"));
				memberDTO.setMember_grade(rs.getInt("member_grade"));
				memberDTO.setMember_level(rs.getInt("member_level"));
				list.add(memberDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBClose.close(rs, pstmt, conn);
		}
		return list;
	}

	public int membermodi(String id, String pass, String grade, String level) {
		int cnt=0;
		conn =DBConnection.getInstance().getConnection();
		String sql = "update tbl_member \n";
		sql += "set member_pass = ?,\n";
		sql += "member_grade=?,\n";
		sql += "member_level=?\n";
		sql += "where member_id=?";
		
		
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, pass);
			pstmt.setString(2, grade);
			pstmt.setString(3, level);
			pstmt.setString(4, id);
			
			cnt=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, conn);
		}
		
		return cnt;
	}

	public int getTotalArticle(String key, String word, String id) {
		int totA = 0;
		conn = DBConnection.getInstance().getConnection();
		String sql = "select count(*) from tbl_rent \n";
		sql += "where rent_member_id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
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

	public int getTotalArticle2(String key, String word, String id) {
		int totA = 0;
		conn = DBConnection.getInstance().getConnection();
		String sql = "select count(*) from tbl_member \n";
//		sql += "where rent_member_id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, id);
			
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

	public MemberDTO getMemberdto(String id2) {
		MemberDTO memberDTO = null;
		conn= DBConnection.getInstance().getConnection();
		String sql = "select * from tbl_member \n";
		sql += "where member_id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id2);
			rs= pstmt.executeQuery();
			while ( rs.next() ){
				memberDTO= new MemberDTO();
				memberDTO.setMember_name(rs.getString("member_name"));
				memberDTO.setMember_id(rs.getString("member_id"));
				memberDTO.setMember_pass(rs.getString("member_pass"));
				memberDTO.setMember_pnum1(rs.getString("member_pnum1"));
				memberDTO.setMember_pnum2(rs.getString("member_pnum2"));
				memberDTO.setMember_gender(rs.getString("member_gender"));
				memberDTO.setMember_zip1(rs.getString("member_zip1"));
				memberDTO.setMember_zip2(rs.getString("member_zip2"));
				memberDTO.setMember_addr1(rs.getString("member_addr1"));
				memberDTO.setMember_addr2(rs.getString("member_addr2"));
				memberDTO.setMember_email1(rs.getString("member_email1"));
				memberDTO.setMember_email2(rs.getString("member_email2"));
				memberDTO.setMember_tel1(rs.getString("member_tel1"));
				memberDTO.setMember_tel2(rs.getString("member_tel2"));
				memberDTO.setMember_tel3(rs.getString("member_tel3"));
				memberDTO.setMember_phone1(rs.getString("member_phone1"));
				memberDTO.setMember_phone2(rs.getString("member_phone2"));
				memberDTO.setMember_phone3(rs.getString("member_phone3"));
				memberDTO.setMember_dltype(rs.getString("member_dltype"));
				memberDTO.setMember_dlnum1(rs.getString("member_dlnum1"));
				memberDTO.setMember_dlnum2(rs.getString("member_dlnum2"));
				memberDTO.setMember_dlnum3(rs.getString("member_dlnum3"));
				memberDTO.setMember_dlyear(rs.getString("member_dlyear"));
				memberDTO.setMember_dlmonth(rs.getString("member_dlmonth"));
				memberDTO.setMember_dlday(rs.getString("member_dlday"));
				memberDTO.setMember_grade(rs.getInt("member_grade"));
				memberDTO.setMember_level(rs.getInt("member_level"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBClose.close(rs, pstmt, conn);
		}
		
		return memberDTO;
	}

	public int deletemember(String id3) {
		int cnt = 0;
		
		System.out.println(id3);
		conn= DBConnection.getInstance().getConnection();
				
		String sql= "delete from tbl_member \n";
		sql += "where member_id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id3);
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBClose.close(pstmt, conn);
		}
		System.out.println(cnt);
		
		return cnt;
	}

	public int getTotalArticle3(String key, String word, String id) {
		int totA = 0;
		conn = DBConnection.getInstance().getConnection();
		String sql = "select count(*) from tbl_trent \n";
		sql += "where trent_member_id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
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

	public List<TRentDTO> list_trent(String id, int end, int start, String key,
			String word) {
		List<TRentDTO> list = new ArrayList<TRentDTO>();
		conn= DBConnection.getInstance().getConnection();
		String sql= "select * from( \n";
		sql +=  "select b.* from (select rownum rn, a.* from( select  * from tbl_trent where trent_member_id=? \n";
		sql += "order by trent_seq desc)a where rownum <= ?)b where b.rn >? )\n";
		
		try {
			int idx= 1;
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(idx++, id);
			pstmt.setInt(idx++, end);
			pstmt.setInt(idx++, start);
			
			rs = pstmt.executeQuery();
			
			TRentDTO trentDTO = null;
			while ( rs.next() )
			{
				trentDTO = new TRentDTO();
				trentDTO.setTrent_seq(rs.getInt( "TRENT_SEQ" ) );
				trentDTO.setTrent_member_id( rs.getString( "TRENT_MEMBER_ID" ) );
				trentDTO.setTrent_tour_seq( rs.getInt( "TRENT_TOUR_SEQ" ) );
				trentDTO.setTrent_rent_seq(rs.getInt("TRENT_RENT_SEQ"));
				trentDTO.setTrent_car_seq(rs.getInt("trent_car_seq"));
				trentDTO.setTrent_checkinday(rs.getString("trent_checkinday"));
				trentDTO.setTrent_checkoutday(rs.getString("trent_checkoutday"));
				trentDTO.setTrent_sumpeople(rs.getInt("trent_sumpeople"));
				trentDTO.setTrent_totalprice(rs.getInt("trent_totalprice"));
				trentDTO.setTrent_note(rs.getString("trent_note"));
				
				list.add( trentDTO );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBClose.close(rs, pstmt, conn);
		}
		
		return list;
	}

	public BookingDTO reservecheck(String seq) {
		
		BookingDTO bookingDTO = null;
		
		System.out.println(seq);
		conn= DBConnection.getInstance().getConnection();
		String sql = "select * \n";
		sql+= "from tbl_rent a join tbl_car b \n";
		sql+= "on a.rent_car_seq = b.car_seq \n";
		sql+= "where a.rent_seq = ?";
		
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, seq);
			
			rs = pstmt.executeQuery();
			
			while ( rs.next() )
			{
				bookingDTO = new BookingDTO();
				bookingDTO.setRent_seq(rs.getInt( "RENT_SEQ" ) );
				bookingDTO.setRent_member_id( rs.getString( "RENT_MEMBER_ID" ) );
				bookingDTO.setRent_car_seq(rs.getInt("rent_car_seq"));
				bookingDTO.setRent_rday(rs.getString("rent_rday"));
				bookingDTO.setRent_rtime(rs.getString("rent_rtime"));
				bookingDTO.setRent_retday(rs.getString("rent_retday"));
				bookingDTO.setRent_rettime(rs.getString("rent_rettime"));
				bookingDTO.setRent_rlocation(rs.getInt("rent_rlocation"));
				bookingDTO.setRent_roffice(rs.getInt("rent_roffice"));
				bookingDTO.setRent_retlocation(rs.getInt("rent_retlocation"));
				bookingDTO.setRent_retoffice(rs.getInt("rent_retoffice"));
				bookingDTO.setRent_navicheck(rs.getString("rent_navicheck"));
				bookingDTO.setRent_insurance_seq(rs.getInt("rent_insurance_seq"));
				bookingDTO.setRent_totalday(rs.getInt("rent_totalday"));
				bookingDTO.setRent_totaltime(rs.getInt("rent_totaltime"));
				bookingDTO.setRent_price(rs.getInt("rent_price"));
				bookingDTO.setRent_totalprice(rs.getInt("rent_totalprice"));
				bookingDTO.setRent_dc(rs.getInt("rent_dc"));
				bookingDTO.setCar_seq(rs.getInt("car_seq"));
				bookingDTO.setCar_spic(rs.getString("car_spic"));
				bookingDTO.setCar_grade(rs.getInt("car_grade"));
				bookingDTO.setCar_maker(rs.getInt("car_maker"));
				bookingDTO.setCar_fueltype(rs.getInt("car_fueltype"));
				bookingDTO.setCar_disvolume(rs.getString("car_disvolume"));
				bookingDTO.setCar_numofpeople(rs.getInt("car_numofpeople"));
				bookingDTO.setCar_rentprice(rs.getInt("car_rentprice"));
				bookingDTO.setCar_name(rs.getString("car_name"));
				

				
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBClose.close(rs, pstmt, conn);
		}
		
		
		return bookingDTO;
	}

	public TravelbookingDTO trentreservecheck(String seq) {
		TravelbookingDTO travelbookingDTO = null;
		
		System.out.println(seq);
		conn= DBConnection.getInstance().getConnection();
		
		String sql = "select *\n";
		sql+="from (\n";
			sql+="select *\n";
				sql+="from (\n";
					sql+="select *\n ";
						sql+="from tbl_trent a join tbl_car b \n";
							sql+="on a.trent_car_seq = b.car_seq ) c\n";
								sql+="join tbl_tour d\n";
									sql+="on c.trent_tour_seq = d.tour_seq ) e\n";
										sql+="join tbl_rent f\n";
											sql+="on e.trent_rent_seq = f.rent_seq\n";
												sql+="where e.trent_seq = ?";

		
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, seq);
			
			rs = pstmt.executeQuery();
			
			while ( rs.next() )
			{
				travelbookingDTO = new TravelbookingDTO();
				travelbookingDTO.setTour_name(rs.getString( "tour_name" ) );
				travelbookingDTO.setTour_price(rs.getInt( "tour_price" ) );
				travelbookingDTO.setTour_location(rs.getInt( "tour_location" ) );
				travelbookingDTO.setTour_roomsize(rs.getInt( "tour_roomsize" ) );
				travelbookingDTO.setTrent_checkinday(rs.getString( "trent_checkinday" ) );
				travelbookingDTO.setTrent_checkoutday(rs.getString( "trent_checkoutday" ) );
				travelbookingDTO.setTour_numofpeople(rs.getInt( "tour_numofpeople" ) );
				travelbookingDTO.setRent_roffice(rs.getInt( "rent_roffice" ) );
				travelbookingDTO.setRent_retoffice(rs.getInt( "rent_retoffice" ) );
				travelbookingDTO.setRent_rday(rs.getString( "rent_rday" ) );
				travelbookingDTO.setRent_retday(rs.getString( "rent_retday" ) );
				travelbookingDTO.setCar_seq(rs.getInt( "car_seq" ) );
				travelbookingDTO.setCar_spic(rs.getString( "car_spic" ) );
				travelbookingDTO.setCar_name(rs.getString( "car_name" ) );
				travelbookingDTO.setCar_maker(rs.getInt( "car_maker" ) );
				travelbookingDTO.setCar_fueltype(rs.getInt( "car_fueltype" ) );
				travelbookingDTO.setCar_disvolume(rs.getString( "car_disvolume" ) );
				travelbookingDTO.setCar_numofpeople(rs.getInt( "car_numofpeople" ) );
				travelbookingDTO.setCar_rentprice(rs.getInt( "car_rentprice" ) );
				travelbookingDTO.setRent_totalday(rs.getInt( "rent_totalday" ) );
				travelbookingDTO.setRent_dc(rs.getInt( "rent_dc" ) );
				travelbookingDTO.setTrent_totalprice(rs.getInt( "trent_totalprice" ) );
				travelbookingDTO.setTour_spic(rs.getString("tour_spic"));
				
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBClose.close(rs, pstmt, conn);
		}
		
		
		return travelbookingDTO;
	}



}
