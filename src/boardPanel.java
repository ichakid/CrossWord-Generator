/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ttsgenerator;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author icha
 */
public class boardPanel extends JPanel{
    public cell[][] boardGrid= new cell[2][2];
    private int boardSize=2;
    
    public boardPanel(int n, char[][] arr){
        GridBagConstraints c = new GridBagConstraints();
        ResetConstraints(c);
        boardSize = n;
        boardGrid = new cell[boardSize][boardSize];
        setLayout(new GridBagLayout());
        for (int i=0; i < boardSize; i++){
            c.gridy = i;
            for (int j=0; j < boardSize; j++){
                boardGrid[i][j] = new cell(arr[i][j]);
                c.gridx = j;
                add(boardGrid[i][j], c);
            }
        }
    }
    
    private void ResetConstraints(GridBagConstraints c) {
       c.weightx = 0.0;
       c.weighty = 0.0;
       c.ipadx = 0;
       c.ipady = 0;
       c.gridheight = 1;
       c.gridwidth = 1;
       c.anchor = GridBagConstraints.CENTER;
       c.fill = GridBagConstraints.NONE;
       c.insets = new Insets(0, 0, 0, 0);
    }
}
