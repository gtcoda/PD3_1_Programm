package editor;

import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

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
    public String value;

    private DataBase(String value) {

    }

    public static DataBase getInstance(String value) {
        if (instance == null) {
            instance = new DataBase(value);
        }
        return instance;
    }


    public Map<String, Integer> get(){
        String query = "select id, title from text";

        ArrayList<String> list = new ArrayList<String>();
        Map<String, Integer> Map_list = new HashMap<String, Integer>();

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                //list.add(rs.getString("title"));
                Map_list.put(rs.getString("title"), rs.getInt("id"));
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

        return Map_list;
    }

    public String get_id(int id){
        String query = "select text from text where id = " + id;
        System.out.printf(query);


        ArrayList<String> list = new ArrayList<String>();
        Map<Integer, String> Map_list = new HashMap<Integer, String>();

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            rs = stmt.executeQuery(query);


            while (rs.next()) {
                return rs.getString("text");
            }


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

        return "";
    }

    public Map<String, String> get_by_id(int id){
        String query = "select * from text where id = " + id;
        System.out.printf(query);


        ArrayList<String> list = new ArrayList<String>();

        Map<String, String> Map_list = new HashMap<String, String>();

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            rs = stmt.executeQuery(query);


            while (rs.next()) {
                Map_list.put("text",  rs.getString("text"));
                Map_list.put("title", rs.getString("title"));
            }


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

        return Map_list;
    }

    public void insert(Map<String, String> q){
        String qe = "INSERT INTO text(`title`, `text`) VALUES ('"+q.get("title") +"','" + q.get("text") + "');";
        System.out.println(qe);
        try {
            stmt.executeUpdate(qe);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }


    public void update_by_id(Map<String, String> q, int id){
        String qe = "UPDATE text SET `title` ='"+q.get("title") +"', `text` = '" + q.get("text") + "' WHERE id = " + id +";";
        System.out.println(qe);

        try {
            stmt.executeUpdate(qe);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }


}



