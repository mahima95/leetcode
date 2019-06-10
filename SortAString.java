package com.mahima;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SortAString {

    public static void main(String args[]) throws IOException {  
        
        String data = readfromfile("/Users/mahimagupta/Desktop/input005.txt");     

        sortString(data);
      
    }

    public static String readfromfile(String filename) throws IOException {
        
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(filename)));
        return data;
    }

    public static void sortString(String data)
    {
       char chars[] =  data.toCharArray();
       int charCount [] = new int[26];

       for(char i:chars)
       {
          if(Character.isLowerCase(i))
            charCount[i-'a']++;
       }

       for(int i=0; i<26;i++)
           for(int j=0;j<charCount[i];j++)
           {
               System.out.print((char)('a'+i));

           }

    }
}
