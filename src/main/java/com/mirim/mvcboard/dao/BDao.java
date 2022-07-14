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
	}
	
	public void write(String bname, String btitle, String bcontent) {  // 사용자가 입력한 3개의 값을 받음
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO mvc_board(bid, bname, btitle, bcontent, bhit, bgroup, bstep, bindent) "
					+ "VALUES (mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0)";
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
	}
	
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
	}
}
