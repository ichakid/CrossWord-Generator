/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ttsgenerator;

import java.io.IOException;
import java.util.*;


/**
 *
 * @author icha
 */
public class tts {
    public static String file_name;
    public int boardSize;
    private ArrayList<String> words;
    public String[] vWords;
    public String[] hWords;
    public Vector<String> unusedWords;
    private int nWords;
    private int wordCount;
    public char[][] board;
    public int[][] emptyBoard;
    public String mode;
    private Random rand;
    
    public tts(){
        words = new ArrayList();
    }
    
    public void generate() throws IOException{
        initialize();
        int count = nWords;
        for (int i=count-1; i >= 0; i--){
            String word = words.remove(count-1);
            count--;
            Integer[] pos = getPosition(word);
            if (pos != null){
                putWord(word, pos[0], pos[1], pos[2]);
            } else {
                unusedWords.add(word);
            }
        }
        refine();
    }
    
    private void initialize(){
        try {
            fileReader file = new fileReader(file_name);
            nWords = file.readLines();
            wordCount = 0;
            words = file.OpenFile();
            board = new char[boardSize][boardSize];
            emptyBoard = new int[boardSize][boardSize];
            rand = new Random();
            vWords = new String[nWords+1];
            hWords = new String[nWords+1];
            for (int i=0; i < nWords+1; i++){
                vWords[i] = "-";
                hWords[i] = "-";
            }
            unusedWords = new Vector();
            for (int i = 0; i < boardSize; i++){
                for (int j = 0; j < boardSize; j++){
                    board[i][j] = ' ';
                    emptyBoard[i][j] = -1;
                }
            }
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    
    private Integer[] getPosition(String word){
        ArrayList<Integer[]> temp = findPositions(word);
        if (temp.isEmpty()){
            return null;
        } else {
            int idx = rand.nextInt(temp.size());
            return temp.get(idx);
        }
    }
    
    private ArrayList<Integer[]> findPositions(String word){
        int maks=0;
        ArrayList<Integer[]> positions = new ArrayList<>();
        for (int x = 0; x < boardSize; x++){
            for (int y = 0; y < boardSize; y++){
                for (int i=0; i < 2; i++){
                    int dir = i;
                    if (canBePlaced(word, x, y, dir) != -1){
                        int val = canBePlaced(word, x, y, dir);
                        if (val >= maks){
                            maks = val;
                            //positions.clear();
                            Integer[] pos = new Integer[3];
                            pos[0] = x; pos[1] = y; pos[2] = dir;
                            positions.add(pos);
                        }
                    }
                }
            }
        }
        return positions;
    }
    
    private int canBePlaced(String word, int x, int y, int dir){
        int result = 0, countstar = 0;
        char[] wordchars = new char[word.length()]; wordchars = word.toCharArray();
        if (dir == 1){  //menurun/vertikal
            for (int offset = 0; offset < word.length(); offset++){
                if (!(isValidPosition(x + offset, y) && ((board[x+offset][y] == ' ') || (board[x+offset][y] == wordchars[offset]) || (board[x+offset][y] == '*')))){
                    return -1;
                }
                if (board[x+offset][y] == '*'){countstar++;}
                if (board[x+offset][y] == wordchars[offset]){
                    result++; countstar--; countstar--;
                }
            }
            if (isValidPosition(x-1, y)){
                if (!((board[x-1][y] == ' ') || (board[x-1][y] == '#') || (board[x-1][y] == '*'))){
                    return -1;
                }
            }
            if (isValidPosition(x + word.length(), y)){
                if (!((board[x + word.length()][y] == ' ') || (board[x + word.length()][y] == '#') || (board[x + word.length()][y] == '*'))){
                    return -1;
                }
            }
            if (countstar > 0){return -1;}
        } else {        //mendatar/horizontal
            for (int offset = 0; offset < word.length(); offset++){
                if (!(isValidPosition(x, y + offset) && ((board[x][y+offset] == ' ') || (board[x][y+offset] == wordchars[offset]) || (board[x][y+offset] == '*')))){
                    return -1;
                }
                if (board[x][y+offset] == '*'){countstar++;}
                if (board[x][y+offset] == wordchars[offset]){
                    result++; countstar--; countstar--;
                } 
            }
            if (isValidPosition(x, y-1)){
                if (!((board[x][y-1] == ' ') || (board[x][y-1] == '#') || (board[x][y-1] == '*'))){
                    return -1;
                }
            }
            if (isValidPosition(x, y + word.length())){
                if (!((board[x][y + word.length()] == ' ') || (board[x][y + word.length()] == '#') || (board[x][y + word.length()] == '*'))){
                    return -1;
                }
            }
            if (countstar > 0){return -1;}
        }
        if (result == word.length()){return -1;}
        return result;
    }
    
    private boolean isValidPosition(int x, int y){
        return (x >= 0) && (y >= 0) && (x < boardSize) && (y < boardSize);
    }
    
    private void putWord(String word, int x, int y, int dir){
        char[] wordchars = word.toCharArray();
        wordCount++;
        if (dir == 0){      //mendatar/horizontal
            for (int i=0; i < word.length(); i++){
                board[x][y+i] = wordchars[i];
                if (emptyBoard[x][y+i] < 1){emptyBoard[x][y+i] = 0;}
            }
            hWords[wordCount] = word;
            if ("mode 1".equals(mode)){
                for (int i=0; i<word.length(); i++){
                    if ((isValidPosition(x-1, y+i)) && (board[x-1][y+i] == ' ')){board[x-1][y+i] = '*';}
                    if ((isValidPosition(x+1, y+i)) && (board[x+1][y+i] == ' ')){board[x+1][y+i] = '*';}
                }
            }
            if (isValidPosition(x, y-1)){board[x][y-1] = '#';}
            if (isValidPosition(x, y+word.length())){board[x][y+word.length()] = '#';}
        } else {            //menurun/vertikal
            for (int i=0; i < word.length(); i++){
                board[x+i][y] = wordchars[i];
                if (emptyBoard[x+i][y] < 1){emptyBoard[x+i][y] = 0;}
            }
            vWords[wordCount] = word;
            if ("mode 1".equals(mode)){
                for (int i=0; i<word.length(); i++){
                    if ((isValidPosition(x+i, y-1)) && (board[x+i][y-1] == ' ')){board[x+i][y-1] = '*';}
                    if ((isValidPosition(x+i, y+1)) && (board[x+i][y+1] == ' ')){board[x+i][y+1] = '*';}
                }
            }
            if (isValidPosition(x-1, y)){board[x-1][y] = '#';}
            if (isValidPosition(x+word.length(), y)){board[x+word.length()][y] = '#';}
        }
        emptyBoard[x][y] = wordCount;
    }
    
    private void refine(){
        String[] temp1 = new String[nWords+1]; int vCount=0;
        String[] temp2 = new String[nWords+1]; int hCount=0;
        for (int i=1; i < nWords+1; i++){
            if (vWords[i] != "-"){vCount++; temp1[i] = i + ". " + vWords[i];} else {temp1[i] = "";} 
            if (hWords[i] != "-"){hCount++; temp2[i] = i + ". " + hWords[i];} else {temp2[i] = "";}
        }
        vWords = new String[vCount]; int iv=0, ih=0;
        hWords = new String[hCount];
        for (int i=1; i < nWords+1; i++){
            if (temp1[i] != ""){
                vWords[iv] = temp1[i];
                iv++;
            }
        }
        for (int i=1; i < nWords+1; i++){
            if (temp2[i] != ""){
                hWords[ih] = temp2[i];
                ih++;
            }
        }
    }
    
}
