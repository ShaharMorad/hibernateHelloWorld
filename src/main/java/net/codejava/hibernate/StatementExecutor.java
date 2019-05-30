package net.codejava.hibernate;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementExecutor {

    void execute(PreparedStatement st) throws SQLException;

}