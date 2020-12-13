package editor;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
*
* Класс для массива записей
*
* */
public class Notes {

    private static Notes instance;

    private ArrayList<Note> notes = new ArrayList<Note>();
    private DataBase DB = DataBase.getInstance();

    private Notes(){  }

    public static Notes getInstance() {
        if (instance == null) {
            instance = new Notes();
        }
        return instance;
    }

    // Получить все записи из базы
    public void getNoteDB() throws Exception{

        notes.clear();
        ResultSet rs;

        rs = DB.getNotes();

        while (rs.next()) {
            addNoteCrypts(
                    rs.getInt("id"),
                    rs.getBytes("title"),
                    rs.getBytes("text"),
                    rs.getString("Cipher")
            );
        }

    }


    // Добавить и расшифровать запись
    private void addNoteCrypts(int id, byte[] title, byte[] text, String Ciph){
        Note n = new Note();
        n.setEncrypt(  id,
                        title,
                        text,
                        Ciph
        );
        notes.add(n);
    }



    // Получить все заголовки и их id
    public Map<String, Integer>  getAllTitle(){
        Map<String, Integer> AllTitle = new HashMap<String, Integer>();

        int size = notes.size();

        for(int i = 0; i<size;i++){
            AllTitle.put(notes.get(i).title_decrypt,i);
        }

        return AllTitle;
    }

    // Получить запись по id(не note_id!)
    public Note getNote(int id){
        return notes.get(id);
    }

    // Добавление записи(наивный вариант)
    public void addNote(Note N) throws Exception{
        DataBase DB = DataBase.getInstance();
        DB.insertNote(N);
        getNoteDB();
    }

    public void updateNote(Note N) throws Exception{
        DB.updateNote(N);
        getNoteDB();
    }

    public void deleteNote(Note N) throws Exception{
        DB.deleteNote(N);
        getNoteDB();
    }
}
