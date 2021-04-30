package com.revature.quizzard.IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class ConsoleReader extends BufferedReader{
    public ConsoleReader(Reader in){
        super(in);
    }

    public String promptIn(String prompText) throws IOException {
        System.out.print(prompText);
        return this.readLine(); // throws Exception here
    }
}
