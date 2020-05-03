/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetcollision;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author bluet
 */
public class Mur {
    int x;
    int debutx; // si on ne met pas début x les murs sortent de la fenêtre
    int y;
    int largeur;
    int hauteur;
    
    
    
    
    Rectangle hitBox;
    
    public Mur(int x, int y, int largeur, int hauteur){
        this.x=x;
        this.y=y;
        debutx = x;
        this.largeur=largeur;
        this.hauteur=hauteur;
        
        hitBox = new Rectangle(x, y, largeur, hauteur);
    }
    
    public void draw (Graphics2D g2D){
        g2D.setColor(Color.GRAY);
        g2D.fillRect(x, y, hauteur, largeur);
        
    }
    
    public int set(int camerax){ //set la caméra dans la classe mur car on fait bouger les murs au lieu du joueur
        x= debutx + camerax; // on met 1 - et pas + ensuite dans classe joueur sinon commandes inversées
        hitBox.x = x; //pour la collision
        return x;
    }
}
