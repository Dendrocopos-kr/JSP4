package com.koreait.pjt.db;

import java.sql.*;
import java.sql.SQLException;
import java.util.List;

public interface JdbcSelectInterface {
	List<?> excuteQuery(PreparedStatement ps) throws SQLException;
	ResultSet prepared(PreparedStatement ps) throws SQLException;
}
