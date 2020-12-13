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
    private static DataBase instance;

    private DataBase() {

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);
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
        String query = "select * from text";
        try {
            Notes NT = Notes.getInstance();
            Statement stmt;
            ResultSet rs;
            stmt = con.createStatement();

            rs = stmt.executeQuery(query);

            return rs;

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

        return null;
    }

    public void insertNote(Note N){

        try {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO text(`title`, `text`) VALUES (?,?);");
            pstmt.setBytes(1, N.title_crypt);
            pstmt.setBytes(2,N.text_crypt);
            pstmt.executeUpdate();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }


    public void updateNote(Note N){

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

    public  void deleteNote(Note N){
        try {
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM `text` WHERE `id` = ?;");
            pstmt.setInt(1, N.note_id);
            pstmt.execute();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }


}



