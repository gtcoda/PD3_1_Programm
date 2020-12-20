package editor;

import java.sql.*;
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

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

// Получение всех записей
    public ResultSet getNotes(){
        Crypto CR = (new CryptoFactory()).Activity();

        String query = "select * from text Where Cipher = '" + CR.CipherMethod()+"'";
        try {
            Notes NT = Notes.getInstance();
            System.out.println(query);
            // executing SELECT query
            rs = stmt.executeQuery(query);

            return rs;

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

        return null;

    }


    public void insert_note(Note N){
        try {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO text(`title`, `text`, `Cipher`) VALUES (?,?,?);");
            pstmt.setBytes(1, N.title_crypt);
            pstmt.setBytes(2,N.text_crypt);
            pstmt.setString(3,N.Cipher);
            pstmt.executeUpdate();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public void update_note(Note N){
        try {
            PreparedStatement pstmt = con.prepareStatement("UPDATE text SET `title` = ?, `text`=? WHERE id = ?;");
            pstmt.setBytes(1, N.title_crypt);
            pstmt.setBytes(2, N.text_crypt);
            pstmt.setInt(3, N.note_id);
            pstmt.executeUpdate();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public  void delite_note(Note N){
        try {
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM `text` WHERE `id` = ?;");
            pstmt.setInt(1, N.note_id);
            pstmt.execute();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }


}



