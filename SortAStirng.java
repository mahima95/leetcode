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

      // Hash array to keep count of characters.
          // Initially count of all charters is  0
       int charCount [] = new int[26];


          // Traverse string and increment  
           // count of characters
       for(char i:chars)
       {

            // 'a'-'a' will be 0, 'b'-'a' will be 1, 
                   // so for location of character in count  
                   // array we wil do str[i]-'a'.if(Character.isLowerCase(i))
            charCount[i-'a']++;
       }

        // Traverse the hash array and print  
           // characters
       for(int i=0; i<26;i++)
           for(int j=0;j<charCount[i];j++)
           {
               System.out.print((char)('a'+i));

           }

    }
}
