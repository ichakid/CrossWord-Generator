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
public class boardLayout extends JPanel{
    GridLayout grid = new GridLayout (2,2);
    int gridSize = 2;
    int gap = 1;
    final JPanel boardGrid = new JPanel();
    
    public boardLayout(int size){
        gridSize = size;
        grid = new GridLayout(size, size);
    }
    
    public void addComponentsToPane(final Container pane){
        boardGrid.setLayout(grid);
        boardGrid.setPreferredSize(new Dimension(gridSize*25+(gridSize-1)*gap, gridSize*25+(gridSize-1)*gap));
        grid.setHgap(gap);
        grid.setVgap(gap);
        
        //membuat tiles
        JButton[][] tiles = new JButton[gridSize][gridSize];
        for (int i=0; i < gridSize; i++){
            for (int j=0; j < gridSize; j++){
                tiles[i][j] = new JButton();
                tiles[i][j].setPreferredSize(new Dimension(25,25));
                boardGrid.add(tiles[i][j]);
            }
        }
        grid.layoutContainer(boardGrid);
        boardGrid.setVisible(true);
        pane.add(boardGrid);
    }
    
    
}
