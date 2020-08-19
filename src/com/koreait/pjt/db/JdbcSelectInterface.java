package com.koreait.pjt.db;

import java.sql.*;
import java.util.List;

public interface JdbcSelectInterface {
	int executeQuery(ResultSet rs) throws SQLException;
	void prepard(PreparedStatement ps) throws SQLException;
}
