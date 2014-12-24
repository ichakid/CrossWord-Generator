/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ttsgenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author icha
 */
public class fileReader {
    private String path;
    
    public fileReader(String file_path){
        path = file_path;
    }
    
    public ArrayList<String> OpenFile() throws IOException{
        FileReader fr = new FileReader(path);
        BufferedReader textReader = new BufferedReader(fr);
        
        int numberOfLines = readLines();
        ArrayList<String> textData = new ArrayList();
        
        int lineCount;
        
        String line;
        
        for (lineCount=0; lineCount < numberOfLines; lineCount++){
            line = textReader.readLine();
            textData.add(line.toUpperCase());
        }
        
        textReader.close();
        return textData;
    }
    
    public int readLines() throws IOException{
        FileReader file_to_read = new FileReader (path);
        BufferedReader bf = new BufferedReader(file_to_read);
        
        String aLine;
        int numberOfLines = 0;
        
        while ((aLine = bf.readLine()) != null){
            numberOfLines++;
        }
        
        bf.close();
        return numberOfLines;
    }
}
