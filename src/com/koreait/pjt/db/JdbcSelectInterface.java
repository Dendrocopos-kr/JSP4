package com.koreait.pjt.db;

import java.sql.*;
import java.util.List;

public interface JdbcSelectInterface {
	void prepard(PreparedStatement ps) throws SQLException;
	int executeQuery(ResultSet rs) throws SQLException;

}
