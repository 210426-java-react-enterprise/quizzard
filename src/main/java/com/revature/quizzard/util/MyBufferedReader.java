package com.revature.quizzard.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

public class MyBufferedReader extends BufferedReader {

    private static MyBufferedReader instance;

    private MyBufferedReader(Reader in) {
        super(in);
    }

    public static MyBufferedReader getInstance()
    {
        if (instance == null)
        {
            instance = new MyBufferedReader(new InputStreamReader(System.in));
        }
        return instance;
    }
}
