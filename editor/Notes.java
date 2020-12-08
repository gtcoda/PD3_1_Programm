package editor;


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

    ArrayList<Note> notes = new ArrayList<Note>();

    private Notes(){  }

    public static Notes getInstance() {
        if (instance == null) {
            instance = new Notes();
        }
        return instance;
    }

    // Добавить и расшифровать запись
    public void addNoteCrypts(int id, byte[] title, byte[] text, String Ciph){
        Note n = new Note();
        n.Set_encrypt(  id,
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

}
