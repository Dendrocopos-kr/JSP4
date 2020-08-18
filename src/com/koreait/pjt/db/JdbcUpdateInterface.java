package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public interface JdbcUpdateInterface {
	public abstract int update(PreparedStatement ps) throws SQLException;
}
