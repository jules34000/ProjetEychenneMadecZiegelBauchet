/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetcollision;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author bluet
 */
public class KeyChecker extends KeyAdapter {
    
    GamePanel panel;
    
    public KeyChecker(GamePanel panel){
        this.panel = panel;
        
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        panel.keyPressed(e); //clic droit création automatique de la méthode dans GamePanel
    }
    
    @Override
    public void keyReleased(KeyEvent e){
        panel.keyReleased(e); //clic droit création automatique de la méthode dans GamePanel
    }
}
