// 실질적으로 DB의 Data에 왔다갔다함
// Server => context.xml에 resource 작성

package com.mirim.mvcboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mirim.mvcboard.dto.BDto;

public class BDao {
	
	DataSource dataSource;		// Server의 context.xml 불러다 쓰겠다

	public BDao() {
		super();
		// TODO Auto-generated constructor stub
		try {			// 강제적 예외처리 해줘야함
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");	// java DateSource 초기화
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	} // BDao 종료
	
	
	public void write(String bname, String btitle, String bcontent) {  // 사용자가 입력한 3개의 값을 받음
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO mvc_board(bid, bname, btitle, bcontent, bhit, bgroup, bstep, bindent) "
					+ "VALUES (mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0)";  // bid는 시퀀스, bgroup은 현재 글의 값
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);
			
			pstmt.executeUpdate();		// integer 반환할 수 있음, 실헹문
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	} // write 종료
	
	
	public ArrayList<BDto> list() {  
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM mvc_board ORDER BY bid DESC"; // 내림차순 정렬, 덧글이 오면 나중에 순서 바꿔줘야함
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();		// 실헹문 (select문은 Query로 실행해야함)
			
			while (rs.next()) {			// 더이상 글이 없을때까지 반복해 내용을 뽑고 배열에 차례로 쌓임 (있으면 true, 없으면 false) , 글의 개수만큼 반복
				int bid = rs.getInt("bid");
				String bname = rs.getString("bname");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				Timestamp bdate = rs.getTimestamp("bdate");
				int bhit = rs.getInt("bhit");
				int bgroup = rs.getInt("bgroup");
				int bstep = rs.getInt("bstep");
				int bindent = rs.getInt("bindent");
				
				BDto dto = new BDto(bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent);
				dtos.add(dto);	// 한바퀴 돌 때마다 한번씩 들어감
			}
		}	
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null ) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;		// 만든 리스트를 반환
	}	// list 종료
	
	
	public BDto contentView(String strbid) {  	// 하나만 선택해서 보냄, 반환값 O
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BDto dto = null;			// 생성
		
		
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM mvc_board WHERE bid=?"; // 어느 값이 올지 모르므로 ?처리
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(strbid));// ? 셋팅 (String으로 들어온 strbid를 integer로 형변환=> weper class이용)
			
			rs = pstmt.executeQuery();		// 실헹문 (select문은 Query로 실행해야함)
			
			while (rs.next()) {			// 더이상 글이 없을때까지 반복해 내용을 뽑고 배열에 차례로 쌓임 (있으면 true, 없으면 false) 
				int bid = rs.getInt("bid");
				String bname = rs.getString("bname");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				Timestamp bdate = rs.getTimestamp("bdate");
				int bhit = rs.getInt("bhit");
				int bgroup = rs.getInt("bgroup");
				int bstep = rs.getInt("bstep");
				int bindent = rs.getInt("bindent");
				
				dto = new BDto(bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent);
			}
		}	
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null ) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dto;		
	}	// contentView 종료 
	
	
	public void delete(String strbid) {  	// 반환값 X
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "DELETE FROM mvc_board WHERE bid=?"; // 어느 값이 올지 모르므로 ?처리
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(strbid));// ? 셋팅 (String으로 들어온 strbid를 integer로 형변환=> weper class이용)
			
			pstmt.executeUpdate();		// 실헹문 (delete는 Update로 실행)
			
		}	
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}	// delete 종료 
	
	
	public void modify(String bid, String bname, String btitle, String bcontent) {  // 사용자가 입력한 3개의 값과 숨긴 bid 값을 받음
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE mvc_board SET bname=?, btitle=?, bcontent=? WHERE bid=?";  // 어느값이 들어올 지 모르므로 ? 처리함
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);
			pstmt.setInt(4, Integer.parseInt(bid));		// integer로 변경해서 값을 넣어줌
			
			pstmt.executeUpdate();		// integer 반환할 수 있음, 실헹문
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	} // modify 종료
}
