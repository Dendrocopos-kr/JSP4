package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.pjt.vo.BoardCmtVO;
import com.koreait.pjt.vo.BoardVO;

public class BoardCmtDAO {
	public static List<BoardCmtVO> selectBoardCmtList(BoardVO param) {
		String sql = "	SELECT c.i_board, b.user_nm,a.i_user, a.i_cmt, a.cmt, a.is_del, to_char(a.r_dt, 'YYYY/MM/DD HH24:MI')as r_dt "
				+ " FROM t_board4_cmt a "
				+ " JOIN t_user b ON a.i_user = b.i_user "
				+ " JOIN t_board4  c ON c.i_board = a.i_board "
				+ " where a.i_board = ? "
				+ " ORDER BY a.i_cmt ";
		List<BoardCmtVO> list = new ArrayList<>();

		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				int total = 0;
				while (rs.next()) {
					BoardCmtVO e = new BoardCmtVO();
					e.setI_board(rs.getInt("i_board"));
					e.setI_user(rs.getInt("i_user"));
					e.setUser_nm(rs.getNString("user_nm"));
					e.setI_cmt(rs.getInt("i_cmt"));
					e.setCmt(rs.getNString("cmt"));
					e.setIs_del(rs.getInt("is_del"));
					e.setR_dt(rs.getNString("r_dt"));
					list.add(e);
					total++;
				}
				return total;
			}

			@Override
			public void prepard(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_board());
			}
		});
		return list;
	}

	public static int insertCmt(BoardCmtVO param) {
		String sql = " insert into t_board4_cmt (i_cmt,i_board,i_user,cmt ) values "
				+ " (seq_board4_cmt.nextval,?,?,?) ";
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public int update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_board());
				ps.setInt(2, param.getI_user());
				ps.setNString(3, param.getCmt());
				return ps.executeUpdate();
			}
		});
	}

	public static int updateCmt(BoardCmtVO param) {
		String sql = " update  t_board4_cmt  set cmt = ? where i_cmt =? and i_board = ? and i_user = ? ";
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public int update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getCmt());
				ps.setInt(2, param.getI_cmt());
				ps.setInt(3, param.getI_board());
				ps.setInt(4, param.getI_user());
				return ps.executeUpdate();
			}
		});
	}

	public static int deleteCmt2(BoardCmtVO param) {
		String sql = " delete t_board4_cmt where i_user=? and i_cmt = ?";
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public int update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_user());
				ps.setInt(2, param.getI_cmt());
				return ps.executeUpdate();
			}
		});
	}

	public static int deleteCmt(BoardCmtVO param) {
		String sql = " update  t_board4_cmt  set is_del = ? where i_cmt =? and i_user = ? ";
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public int update(PreparedStatement ps) throws SQLException {
				param.setIs_del(1);
				ps.setInt(1, param.getIs_del());
				ps.setInt(2, param.getI_cmt());
				ps.setInt(3, param.getI_user());
				return ps.executeUpdate();
			}
		});
	}
}
