package editor.controller;

import editor.Note;

public class Edit {
    public Note Note;


    private static Edit instanse;

    private Edit() { }

    public static Edit getInstanse(){
        if(instanse == null){
            instanse = new Edit();
        }
        return instanse;
    }

    public void editNote(Note N){
        Note = N;
    }
}
