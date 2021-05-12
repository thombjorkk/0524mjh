package com.cookandroid.splash_pleazz;

import java.util.ArrayList;

public class position {

    public String position;
    public ArrayList<String> comments = new ArrayList<String>();

    public position(String position){
        this.position = position;
    }

    public String toString(){
        return position;
    }

}
