package com.bnc.customer.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.bnc.db.DBClose;
import com.bnc.db.DBConnection;
import com.bnc.util.StringUtil;

public class CustomerDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	

	public List<CustomerTypeDTO> getBoardType() {
		List<CustomerTypeDTO> list = new ArrayList<CustomerTypeDTO>();
		conn = DBConnection.getInstance().getConnection();
		String sql = "select * from tbl_boardtype";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				CustomerTypeDTO boardType = new CustomerTypeDTO();
				boardType.setBtype(rs.getInt("seq"));
				boardType.setBtitle(rs.getString("btitle"));
				list.add(boardType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, conn);
		}
		return list;
	}
	
	public int getNextSeq() {
		int seq = 0;
		conn = DBConnection.getInstance().getConnection();
		String sql = "select tbl_board_seq.nextval from dual";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			seq = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, conn);
		}
		return seq;
	}

	public int newArticle(CustomerDTO customerDTO) {
		int cnt = 0;
		conn = DBConnection.getInstance().getConnection();
		String sql = "";
		sql +="insert all \n";
		sql +="       into tbl_board (seq, id, name, email,subject, content, hit, logtime, btype) \n";
		sql +="       values (?,?,?,?,?,?,0,sysdate,?) \n";
		sql +="       into tbl_reboard(seq, ref, lev, step, pseq, reply) \n";
		sql +="       values (?,?,0,0,0,0) \n";
		sql +="select * from dual \n";
		try {
			pstmt = conn.prepareStatement(sql);
			int idx = 0;
			pstmt.setInt(++idx, customerDTO.getSeq());
			pstmt.setString(++idx, customerDTO.getId());
			pstmt.setString(++idx, customerDTO.getName());
			pstmt.setString(++idx, customerDTO.getEmail());
			pstmt.setString(++idx, customerDTO.getSubject());
			pstmt.setString(++idx, customerDTO.getContent());
			pstmt.setInt(++idx, customerDTO.getBtype());
			pstmt.setInt(++idx, customerDTO.getSeq());
			pstmt.setInt(++idx, customerDTO.getRef());
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, conn);
		}
		return cnt;
	}

	public List<CustomerDTO> getList(int btype, int end, int start, String key, String word, String myid) {
		List<CustomerDTO> list = new ArrayList<CustomerDTO>();
		conn = DBConnection.getInstance().getConnection();
		
		
		if(key.equals("subject"))
			key="b.subject";
		else if(key.equals("writer"))
			key="b.name";
		else if(key.equals("no"))
			key="b.seq";
		else
			key="";
		
			
		String sql = "";
		sql +="select b.*\n";
		sql +="from( ";
		sql +="        select rownum rn, a.*\n";
		sql +="        from(\n";
		sql +="                select b.seq, b.id, b.name, b.subject,\n ";
		sql +="                       b.content, b.hit, r.lev, b.email,\n";
		sql +="                       decode(to_char(b.logtime, 'yyyymmdd'),\n";
		sql +="                              to_char(sysdate, 'yyyymmdd'),\n";
		sql +="                              to_char(b.logtime, 'hh24:mi:ss'),\n";
		sql +="                              to_char(b.logtime, 'yy.mm.dd')) as logtime\n";
		sql +="                from tbl_board b join tbl_reboard r\n";
		sql +="                on b.seq=r.seq\n";
		sql +="                where btype = ?\n";
		if(!StringUtil.hasNull(word) && (key.equals("b.subject") || key.equals("b.name"))){
			sql+="          and "+key+" like '%'||?||'%' \n";
		}
		else if( !StringUtil.hasNull(word) && key.equals("b.seq")){
			sql+="          and "+key+"=? \n";
		}
		if( !StringUtil.hasNull(myid)){
			sql+="          and b.id=? \n";
		}
		sql +="                order by r.ref desc, r.step\n";
		sql +="            ) a\n";
		sql +="        where rownum <= ?\n";
		sql +="    ) b\n";
		sql +="where b.rn > ?\n";
		try {
			pstmt = conn.prepareStatement(sql);
			int idx = 0;
			pstmt.setInt(++idx, btype);
			if( !StringUtil.hasNull(word) && (key.equals("b.subject") || key.equals("b.name")))
				pstmt.setString(++idx, word);
			else if( !StringUtil.hasNull(word) && key.equals("b.seq"))
				pstmt.setString(++idx, word);
			if( !StringUtil.hasNull(myid) )
				pstmt.setString(++idx, myid);
			pstmt.setInt(++idx, end);
			pstmt.setInt(++idx, start);
			rs = pstmt.executeQuery();
			while(rs.next()){
				CustomerDTO customerDTO = new CustomerDTO();
				customerDTO.setSeq(rs.getInt("seq"));
				customerDTO.setId(rs.getString("id"));
				customerDTO.setName(rs.getString("name"));
				customerDTO.setEmail(rs.getString("email"));
				customerDTO.setSubject(rs.getString("subject"));
				customerDTO.setContent(rs.getString("content"));
				customerDTO.setLogtime(rs.getString("logtime"));
				customerDTO.setHit(rs.getInt("hit"));
				customerDTO.setLev(rs.getInt("lev"));
				
				list.add(customerDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBClose.close(rs, pstmt, conn);
		}
		return list;
	}

	public CustomerDTO getArticle(int seq, String func, int btype) {
		CustomerDTO bbsDTO = null;
		  conn = DBConnection.getInstance().getConnection();
		  String sql = "";
		  sql +="select b.seq, b.id, b.name, b.email, b.subject, \n";
		  sql +="b.content, b.hit, b.logtime, r.ref, r.step, r.lev, r.pseq, r.reply \n";
		  sql +="from tbl_board b join tbl_reboard r \n";
		  sql +="on b.seq=r.seq \n";
		  if(func.length() == 0){
		   sql +="where b.seq=? \n";
		  }else{
		   if(func.equals("N")){
		    sql +="where b.seq=(select min(seq) from tbl_board where seq>? and btype=?) \n";
		   }else if(func.equals("P")){
		    sql +="where b.seq=(select max(seq) from tbl_board where seq<? and btype=?) \n";
		   }
		  }
		  try {
		   pstmt = conn.prepareStatement(sql);
		   pstmt.setInt(1, seq);
		   if(func.length() != 0)
		    pstmt.setInt(2, btype);
		   rs = pstmt.executeQuery();
		   if(rs.next()){
		    bbsDTO = new CustomerDTO();
		    bbsDTO.setSeq(rs.getInt("seq"));
		    bbsDTO.setId(rs.getString("id"));
		    bbsDTO.setName(rs.getString("name"));
		    bbsDTO.setSubject(rs.getString("subject"));
		    bbsDTO.setContent(rs.getString("content"));
		    bbsDTO.setEmail(rs.getString("email"));
		    bbsDTO.setLogtime(rs.getString("logtime"));
		    bbsDTO.setHit(rs.getInt("hit"));
		    bbsDTO.setRef(rs.getInt("ref"));
		    bbsDTO.setLev(rs.getInt("lev"));
		    bbsDTO.setStep(rs.getInt("step"));
		    bbsDTO.setPseq(rs.getInt("pseq"));
		    bbsDTO.setReply(rs.getInt("reply"));
		   }
		  } catch (SQLException e) {
		   
		   e.printStackTrace();
		  }finally{
		   DBClose.close(rs, pstmt, conn);
		  }
		  return bbsDTO;
		 }

	public void updateHit(int seq) {
		conn = DBConnection.getInstance().getConnection();
		String sql = "update tbl_board set hit=hit+1 \n";
		sql += "where seq=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBClose.close(pstmt, conn);
		}
	}

	public int getNewArticleCount(int btype) {
		int newcnt = 0; 
		conn = DBConnection.getInstance().getConnection();
		String sql = "select count(*) from tbl_board \n";
		sql += "      where btype=? \n";
		sql += "      and trunc(logtime, 'dd')=trunc(sysdate, 'dd')";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, btype);
			rs = pstmt.executeQuery();
			rs.next();
			newcnt = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBClose.close(rs, pstmt, conn);
		}
		return newcnt;
	}

	public int getTotlaArticle(int btype , String key, String word, String myid) {
		int totA = 0;
		conn = DBConnection.getInstance().getConnection();
		
		if(key.equals("subject"))
			key="subject";
		else if(key.equals("writer"))
			key="name";
		else if(key.equals("no"))
			key="seq";
		else
			key="";
		
		String sql = "select count(*) from tbl_board \n";
		sql += "      where btype=? \n";
		if(!StringUtil.hasNull(word) && (key.equals("subject") || key.equals("name"))){
			sql+="          and "+key+" like '%'||?||'%' \n";
		}
		else if( !StringUtil.hasNull(word) && key.equals("seq")){
			sql+="          and "+key+"=? \n";
		}
		if( !StringUtil.hasNull(myid)){
			sql+="          and id=? \n";
		}
		try {
			pstmt = conn.prepareStatement(sql);
			int idx = 0;
			pstmt.setInt(++idx, btype);
			if(!StringUtil.hasNull(word) && (key.equals("subject") || key.equals("name")))
				pstmt.setString(++idx, word);
			else if( !StringUtil.hasNull(word) && key.equals("seq"))
				pstmt.setString(++idx, word);
			if( !StringUtil.hasNull(myid))
				pstmt.setString(++idx, myid);
			rs = pstmt.executeQuery();
			rs.next();
			totA = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBClose.close(rs, pstmt, conn);
		}
		return totA;
	}

	public int deleteArticle(int seq) {
		int cnt = 0;
		int count = 0;
		conn = DBConnection.getInstance().getConnection();
		String sql = "	delete from tbl_reboard \n";
		sql += "		where seq = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);

			cnt = pstmt.executeUpdate();
			if (cnt != 0)
				count++;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, conn);
		}

		if (count != 0) {
			conn = DBConnection.getInstance().getConnection();
			sql = "				DELETE FROM tbl_BOARD \n";
			sql += "			WHERE SEQ = ?";

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, seq);

				cnt = pstmt.executeUpdate();
				if (cnt != 0)
					count++;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBClose.close(pstmt, conn);
			}
		}
		return count;
	}

	public CustomerDTO getModiArticle(int seq) {
		CustomerDTO customerDTO = null;
		conn = DBConnection.getInstance().getConnection();
		String sql = "	SELECT SEQ, SUBJECT, CONTENT FROM tbl_BOARD \n";
		sql += "			WHERE SEQ = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				customerDTO = new CustomerDTO();
				customerDTO.setSeq(rs.getInt("SEQ"));
				customerDTO.setSubject(rs.getString("SUBJECT"));
				customerDTO.setContent(rs.getString("CONTENT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(rs, pstmt, conn);
		}
		return customerDTO;
	}

	public int updateArticle(CustomerDTO customerDTO) {
		int cnt = 0;
		conn = DBConnection.getInstance().getConnection();
		String sql = "	UPDATE tbl_BOARD \n";
		sql += "			SET SUBJECT = ?, CONTENT = ? \n";
		sql += "			WHERE SEQ = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, customerDTO.getSubject());
			pstmt.setString(2, customerDTO.getContent());
			pstmt.setInt(3, customerDTO.getSeq());
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(pstmt, conn);
		}
		return cnt;
	}

	public int replyArticle(CustomerDTO customerDTO) {
		int cnt = 0;
		conn = DBConnection.getInstance().getConnection();
		try {
			conn.setAutoCommit(false);
			String update_step = "";
			update_step += "update tbl_reboard set step=step+1 \n";
			update_step += "where ref=? and step>?";
			System.out.println(update_step);
			
			pstmt = conn.prepareStatement(update_step);
			pstmt.setInt(1, customerDTO.getRef());
			pstmt.setInt(2, customerDTO.getStep());
			pstmt.executeUpdate();
						
			String insert_reply = "";
			insert_reply  +="insert all \n";
			insert_reply  +="       into tbl_board (seq, id, name, email, subject, content, hit, logtime, btype) \n";
			insert_reply  +="       values (?,?,?,?,?,?,0,sysdate,?) \n";
			insert_reply  +="       into tbl_reboard(seq, ref, lev, step, pseq, reply) \n";
			insert_reply  +="       values (?,?,?,?,?,0) \n";
			insert_reply  +="select * from dual \n";
			System.out.println(insert_reply);
			
			pstmt = conn.prepareStatement(insert_reply);
			int idx = 0;
			pstmt.setInt(++idx, customerDTO.getSeq());
			pstmt.setString(++idx, customerDTO.getId());
			pstmt.setString(++idx, customerDTO.getName());
			pstmt.setString(++idx, customerDTO.getEmail());
			pstmt.setString(++idx, customerDTO.getSubject());
			pstmt.setString(++idx, customerDTO.getContent());
			pstmt.setInt(++idx, customerDTO.getBtype());
			pstmt.setInt(++idx, customerDTO.getSeq());
			pstmt.setInt(++idx, customerDTO.getRef());
			pstmt.setInt(++idx, customerDTO.getLev()+1);
			pstmt.setInt(++idx, customerDTO.getStep()+1);
			pstmt.setInt(++idx, customerDTO.getPseq());
			pstmt.executeUpdate();			
			
			String update_reply = "";
			update_reply += "update tbl_reboard set reply=reply+1 \n";
			update_reply += "where seq=?";
			System.out.println(update_reply);
			pstmt = conn.prepareStatement(update_reply);
			pstmt.setInt(1, customerDTO.getPseq()); //원글의 글번호
			pstmt.executeUpdate();
			
			conn.commit();
			cnt = 1;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			DBClose.close(pstmt, conn);
		}
		return cnt;
	}

}
