package editor;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;
// класс работы с БД

public class DataBase {

    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://gtcoda.ru:3306/editor?characterEncoding=utf8";
    private static final String user = "editor";
    private static final String password = "3o4GCHvPM6E";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    private static DataBase instance;

    private DataBase() {

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

    }

    public static DataBase getInstance(String value) {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

// получим id и название
    public Map<byte[], Integer> get(){
        String query = "select id, title from text";

        ArrayList<String> list = new ArrayList<String>();
        Map<byte[], Integer> Map_list = new HashMap<byte[], Integer>();

        try {
            // executing SELECT query
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                Map_list.put(rs.getBytes("title"), rs.getInt("id"));
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

        return Map_list;
    }

// Получение записи по id
    public Map<String, byte[]> get_by_id(int id){
        String query = "select * from text where id = " + id;
        System.out.printf(query);

        ArrayList<String> list = new ArrayList<String>();

        Map<String, byte[]> Map_list = new HashMap<String, byte[]>();

        try {
            // executing SELECT query
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                Map_list.put("text", rs.getBytes("text"));
                Map_list.put("title", rs.getBytes("title"));
            }


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

        return Map_list;
    }


    public void insert(Map<String, byte[]> q){
        try {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO text(`title`, `text`) VALUES (?,?);");
            pstmt.setBytes(1, q.get("title"));
            pstmt.setBytes(2, q.get("text"));
            pstmt.executeUpdate();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public void update_by_id(Map<String, byte[]> q, int id){
        try {
            PreparedStatement pstmt = con.prepareStatement("UPDATE text SET `title` = ?, `text`=? WHERE id = ?;");
            pstmt.setBytes(1, q.get("title"));
            pstmt.setBytes(2, q.get("text"));
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

}



