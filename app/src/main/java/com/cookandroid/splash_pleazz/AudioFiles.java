package com.cookandroid.splash_pleazz;

import java.util.ArrayList;

public class AudioFiles {

    public String Voice;
    public ArrayList<String> AudioComment = new ArrayList<String>();

    public AudioFiles(String Voice){
        this.Voice = Voice;
    }

    public String toString(){
        return Voice;
    }



}

