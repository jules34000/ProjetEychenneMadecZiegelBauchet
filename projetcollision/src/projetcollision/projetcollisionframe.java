/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetcollision;

import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author bluet
 */
public class projetcollisionframe extends JFrame {
    public projetcollisionframe (){
        GamePanel panel = new GamePanel();
        panel.setLocation(0,0);
        panel.setSize(this.getSize());
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setVisible(true);
        this.add(panel);
        
        addKeyListener(new KeyChecker(panel)); //on doit créer notre Keychecker pour pouvoir bouger le joueur, on ajoute un keylistener au panel !
    }
    
}
