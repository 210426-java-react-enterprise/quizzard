package com.revature.quizzard.screens;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class Screen{
    protected static BufferedReader consoleReader;

    public Screen(){
        if(consoleReader == null) {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            setReader(consoleReader);
        }

    }

    public String promptIn(String prompText) throws IOException {

        System.out.print(prompText);
        String in = consoleReader.readLine(); // throws Exception here
        return  in;
    }

    public static void setReader(BufferedReader cr){
        consoleReader = cr;
    }
}
