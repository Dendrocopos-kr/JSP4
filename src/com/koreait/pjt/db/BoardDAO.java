package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.pjt.vo.BoardDomain;
import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;

public class BoardDAO {
	public static BoardVO selectBoard(BoardVO param) {
		BoardDomain e = new BoardDomain();
		String sql = " SELECT a.i_board, a.title,a.ctnt, a.hits, a.i_user, to_char(a.r_dt, 'YYYY/MM/DD HH24:MI') AS r_dt, b.user_nm "
				+ " FROM t_board4 a JOIN t_user b ON a.i_user = b.i_user where i_board=?";
		//System.out.println("selectBoard");
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepard(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_board());
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if (rs.next()) {
					e.setI_board(rs.getInt("i_board"));
					e.setTitle(rs.getNString("title"));
					e.setCtnt(rs.getNString("ctnt"));
					e.setHits(rs.getInt("hits"));
					e.setI_user(rs.getInt("i_user"));
					e.setR_dt(rs.getNString("r_dt"));
					e.setUser_nm(rs.getNString("user_nm"));
					return 1;
				}
				return 0;
			}
		});
		return e;
	}

	public static List<BoardVO> selectBoardList() {
		String sql = " SELECT a.i_board, a.title, a.hits, a.i_user, to_char(a.r_dt, 'YYYY/MM/DD HH24:MI') AS r_dt, b.user_nm "
				+ " FROM t_board4 a JOIN t_user b ON a.i_user = b.i_user ORDER BY i_board DESC";
		List<BoardVO> list = new ArrayList();

		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				int total = 0;
				while (rs.next()) {
					BoardDomain e = new BoardDomain();

					e.setI_board(rs.getInt("i_board"));
					e.setTitle(rs.getNString("title"));
					// e.setCtnt( rs.getNString("ctnt") );
					e.setHits(rs.getInt("hits"));
					e.setI_user(rs.getInt("i_user"));
					e.setR_dt(rs.getNString("r_dt"));
					// e.setM_dt( rs.getNString("m_dt") );

					e.setUser_nm(rs.getNString("user_nm"));
					list.add(e);
					total++;
				}
				return total;
			}

			@Override
			public void prepard(PreparedStatement ps) throws SQLException {
			}
		});
		return list;
	}

	public static int insertBoard(BoardVO e) {
		String sql = " insert into t_board4 " + " (i_board, title, ctnt ,i_user ) " + "values"
				+ " (seq_board4.nextval , ? , ? ,?) ";
		//System.out.println("insertBoard");

		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public int update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, e.getTitle());
				ps.setNString(2, e.getCtnt());
				ps.setInt(3, e.getI_user());
				return ps.executeUpdate();
			}
		});
	}
	
	public static int updateBoardPK(BoardVO e) {
		String sql = " select MAX(i_board) from t_board4 where i_user = ? ";
		return JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
			
			@Override
			public void prepard(PreparedStatement ps) throws SQLException {
				ps.setInt(1, e.getI_user());				
			}
			
			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if(rs.next()) {
					e.setI_board(rs.getInt(1));
					return 1;
				}
				return 0;
			}
		});
	}

	public static int updateBoard(BoardVO e) {
		String sql =  " update  t_board4  set  title = ?, ctnt = ?"
				+ "where"
				+ " i_board = ? and i_user = ? ";
		//System.out.println("updateBoard");
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			
			@Override
			public int update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, e.getTitle());
				ps.setNString(2, e.getCtnt());
				ps.setInt(3, e.getI_board());
				ps.setInt(4, e.getI_user());
				return ps.executeUpdate();
			}
		});
	}
	
	public static int deleteBoard(BoardVO e) {
		String sql = " delete t_board4 where i_board=? AND i_user=?";
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			
			@Override
			public int update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, e.getI_board());
				ps.setInt(2, e.getI_user());
				return ps.executeUpdate();
			}
		});
	}
}
