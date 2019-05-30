package net.codejava.hibernate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.List;

public class Database {

    private static String pass = "p97JdfbCFE";
    private static String user =  "jTZKcxgq1a";
    private static String web = getWeb();
    private static final String URL = "jdbc:mysql://" + web + ":3306/"+user;
    private static final String initializer = System.getProperty("user.dir") + "\\src\\main\\java\\net\\codejava\\hibernate\\Initializer.sql";
    private static Connection conn = connect();

    private static Connection connect() {
        try {
//            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(URL, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    static String getWeb() {
        try {
            return InetAddress.getByName("remotemysql.com").getHostAddress();
        } catch (UnknownHostException e) {
            return "fuck";
        }
    }

    static Connection getConnection() {
        return conn;
    }

    static PreparedStatement getStatement(String query, StatementExecutor s) {
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            s.execute(ps);
            return ps;
        } catch (SQLException e) {
            return null;
        }
    }

    public static void initDatabase() {
        try (Statement st = getConnection().createStatement()) {
            BufferedReader in = new BufferedReader(new FileReader(initializer));
            String str;
            StringBuilder sb = new StringBuilder();
            while ((str = in.readLine()) != null) {
                sb.append(str).append("\n");
                if(str.endsWith(";")){
                    //System.out.println(sb.toString());
                    st.executeUpdate(sb.toString());
                    sb = new StringBuilder();
                }
            }
            in.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static boolean updateTable(String query, StatementExecutor func) {
        try (PreparedStatement ps = Database.getConnection().prepareStatement(query)) {
            func.execute(ps);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    static boolean makeTransaction(List<PreparedStatement> pstmtFuncList) {
        try {
            conn.setAutoCommit(false);
            for (PreparedStatement item : pstmtFuncList)
                item.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ignored) {
            }
            return false;
        }
    }

    public static void main(String[] args) {
        Database.initDatabase();
        System.out.println("succ");
        Database.close();
    }
}