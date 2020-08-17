package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public interface JdbcUpdateInterface {
	int executeUpdate(PreparedStatement ps) throws SQLException;
}
