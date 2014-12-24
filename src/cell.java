/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ttsgenerator;

import java.awt.Dimension;
import javax.swing.JButton;

/**
 *
 * @author icha
 */
public class cell extends JButton{
    public char huruf;
    
    public cell(char alfabet){
        huruf = alfabet;
        setPreferredSize(new Dimension(25,25));
        if ((huruf != '#') && (huruf != ' ') && (huruf != '*')){
            setBackground(new java.awt.Color(51, 0, 255));
            setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
            setForeground(new java.awt.Color(255, 255, 255));
            setText("" + huruf);
            setAlignmentY(0.0F);
            setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
            setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            setIconTextGap(0);
            setMargin(new java.awt.Insets(0, 0, 0, 0));
            setMaximumSize(new java.awt.Dimension(25, 25));
            setMinimumSize(new java.awt.Dimension(25, 25));
            setPreferredSize(new java.awt.Dimension(25, 25));
        }
        
    }
}
