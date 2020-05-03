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
class Joueur {
    
    GamePanel panel;
    
    int x;
    int y;
    
    double xvitesse;
    double yvitesse;
    
    int largeur;
    int hauteur;
    
    Rectangle hitBox;
    
    boolean toucheHaut;
    boolean toucheBas;
    boolean toucheDroite;
    boolean toucheGauche;
    
    public boolean stop;
    
    public Joueur(int x, int y, GamePanel panel){
        
        this.panel=panel; // le panel vide qu'on a créé doit être reset pour être le panel qui sera envoyé au joueur
        this.x= x;
        this.y= y;
        
        largeur = 50;
        hauteur = 50;
        hitBox = new Rectangle(x, y, largeur, hauteur);
        
        stop = false;
    }
    
    public void set(){
        if(toucheGauche && toucheDroite || !toucheGauche && !toucheDroite) { 
            xvitesse *=0.8; //si on presse les 2 touches en même temps ou pas de touche : on bouge pas du tout et baisse la vitesse horizontale (doucement)
        }
        else if(toucheGauche && !toucheDroite){
            xvitesse --; //on baisse la vitesse horizontale de 1 (xspeed = xspeed - 1)
        } 
        else if (toucheDroite && !toucheGauche){
            xvitesse ++; //on augmente la vitesse horizontale de 1
        }
        if(xvitesse > 0 && xvitesse<0.75) {
            xvitesse=0;
        } //on veut que le joueur s'arrête complétement quand la vitesse est trop faible (avec le multiplicateur)
        if(xvitesse < 0 && xvitesse>-0.75) {
            xvitesse=0;
        }
        if(xvitesse > 5) {
            xvitesse=5;
        } // on impose une vitesse maximale
        if(xvitesse < -5){
            xvitesse=-5;
        }
        
        if(toucheHaut){ //permet de sauter MAIS on va definir la COLLISION avec le sol pour pas que l'on puisse double jump DESACTIVER POUR FLAPPY BIRD
            hitBox.y ++;
            /*for (Mur mur: panel.murs){
                if(mur.hitBox.intersects(hitBox)){ //COLLISION des hitbox avec fonction INTERSECTS qui detecte la collision DONC PEUT SAUTER QUI SI PROCHE DU SOL
                    yvitesse=-6; //A SORTIR DU IF si on veut pouvoir faire autant de sauts que l'on veut
                }*/
            yvitesse=-4; // a régler pour régler la hauteur d'un "saut"
            }
            hitBox.y --;
        
       
        yvitesse += 0.3;
        
        
        
        //Collision horizontale
        hitBox.x+=xvitesse;
        for (Mur mur: panel.murs){
            if(hitBox.intersects(mur.hitBox)){
                hitBox.x -= xvitesse; //faire revenir la hitbox à son état original, mouvement de la hitbox vers la position projeté puis revient au joueur
                //bouger le plus prés du mur sans le toucher
                while(!mur.hitBox.intersects(hitBox)){
                    hitBox.x +=Math.signum(xvitesse); // signum prend la valeur (positive ou négative, si xvitesse >0 Math.sgnium sera 1 et sinon -1    
                } 
                hitBox.x -= Math.signum(xvitesse);// on veut ensuite rebouger de 1 en arrière juste avant de toucher
                xvitesse=0;
                x= hitBox.x;
                stop=true;
                
            }
        }
        
        //Collision verticale
        hitBox.y+=yvitesse;
        for (Mur mur: panel.murs){
            if(hitBox.intersects(mur.hitBox)){
                hitBox.y -= yvitesse; //faire revenir la hitbox à son état original, mouvement de la hitbox vers la position projeté puis revient au joueur
                //bouger le plus prés du mur sans le toucher
                while(!mur.hitBox.intersects(hitBox)){
                    hitBox.y +=Math.signum(yvitesse); // signum prend la valeur (positive ou négative, si xvitesse >0 Math.signum sera 1 et sinon -1    
                } 
                hitBox.y -= Math.signum(yvitesse);// on veut ensuite rebouger de 1 en arrière juste avant de toucher
                yvitesse=0;
                y= hitBox.y;
                stop=true;
                
            }
        }
        
        
        
        
        //x += xvitesse;   //ici c'est le joueur qui bouge selon x, pour setup la caméra on va faire bouger les murs selon x et donc :
        panel.camerax -= xvitesse; // on fait bouger les murs et on va donc setup la CAMERAX dans la classe mur et faire suivres la hitbox AVEC - CAR SINON COMMANDES INVERSEES 
        y += yvitesse; // bouge le joueur basé sur la vitesse
        
        hitBox.x = x;
        hitBox.y = y; // à chaque fois qu'on bouge le joueur, la hitbox bouge en le suivant
        
        
        
        // Code game over 
        if (y>1000) panel.reset();// game over si on sort de l'écran en bas
        if (y<0) panel.reset();// game over si on sort de l'écran en haut
        /*for (Mur mur: panel.murs){ // game over si on touche un bloc
            if(hitBox.intersects(mur.hitBox)){
                panel.reset();
            }
        }*/
        if (stop==true) {
            panel.reset();
            stop=false;
        }
        
    }
    
    public void gameover(){
        
    }
    
    public void draw(Graphics2D g2D) {
        g2D.setColor(Color.RED);
        g2D.fillRect(x,y,largeur,hauteur); //va dessiner un rectangle à la position (x,y) avec comme taile largeur et la hauteur qu'on a défini 
    }
    
}
