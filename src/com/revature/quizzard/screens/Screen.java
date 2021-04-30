package com.revature.quizzard.screens;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class Screen{
    protected static BufferedReader consoleReader;
    protected static final String AppName = "Quizzard";

    public abstract void render();

    public Screen(){
        if(consoleReader == null) {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            setReader(consoleReader);
        }

    }

    public char promptChar(String prompText) throws IOException {
        System.out.print(prompText);
        return consoleReader.readLine().charAt(0);
    }


    public String promptStr(String prompText) throws IOException {

        System.out.print(prompText);
        return consoleReader.readLine();
    }

    public static void setReader(BufferedReader cr){
        consoleReader = cr;
    }

    public static void closeReader(){
        if(consoleReader != null) {
            try {
                consoleReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
