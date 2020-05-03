/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetcollision;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer; //le swing timer ne marche pas bien
import java.util.TimerTask;

import javax.swing.JPanel;

/**
 *
 * @author bluet
 */
public class GamePanel extends JPanel implements ActionListener {
    Joueur joueur;
    ArrayList<Mur> murs = new ArrayList<>(); //Pour stocker tous les murs dans un tableau
    
    public int camerax; // à setup dans le reset()
    
    Timer GameTimer;
    
    public int offset;
    
    public int score;
    
    public GamePanel(){
        
        joueur = new Joueur(0, 0, this); // this est le GamePanel dans lequel on est
        //fabriquerMurs(50);    //ON AJOUTE LE 50 OFFSET MAIS POUR GENERATION DE TERRAINS ALEATOIRE ON UTILISE RESET
        reset();
        GameTimer = new Timer(); // on a créé la variable en dehors pour que ce soit reconnu aussi en dehors du constructeur
        GameTimer.schedule(new TimerTask(){

            @Override
            public void run() {
                
                if(murs.get(murs.size() - 1).x<1100){
                    
                    offset +=1000; // c'est là que l'offset est vraiment utile sinon les mur générés quand on avance se mette les unes sur les autres et on ne les voit pas
                    fabriquerMurs(offset);
                    score+=1;
                    
                }
                joueur.set(); //met à jour la position du joueur basé sur les inputs (actions du joueur)
                for (Mur mur: murs){ // AJOUTE APRES lors du SETUP DE LA CAMERAX
                    mur.set(camerax);
                }
                
             
                repaint(); //paint ne fonctionne pas
                
                }
            
        }, 0, 17); // 0ms avant que le timer commence et 17 vient de 60fps (1000(ms/s)/60)
        
        
    }
    
    
    
    //reset de la partie quand on meurt : position initiale et vitesse nulle
    public void reset(){
        System.out.println("votre score est de : "+score+" "); // on affiche le score de la partie précédente
        score=0; // on reset le score
       /* if (vie == 0) {
                    System.out.println("GAME OVER NULLOS");
                    joueur
        }*/
        joueur.x=200;
        joueur.y=150;
        camerax=150; 
        joueur.xvitesse = 0; // vitesse à 0 sinon ça se cumule et tombe très très rapidement
        joueur.yvitesse=0;
        murs.clear(); // pour nettoyer à chaque fois sinon ça stock et ralenti le processus/programme, PB AVEC LE CLEAR ACTIVE LES TERRAINS NE SE GENERENT PAS BIEN
        offset = -50;
        fabriquerMurs(offset);// offset à ajouter à la fonction fabriquer Murs
    }
    
    public void fabriquerMurs(int offset){ // on va fabriquer pleins de mur pour créer un terrain de jeu
        
        //génération aléatoire de terrains
        Random rand = new Random();
        int index = rand.nextInt(4);
        
        if(index==0){
            for (int i=5; i<19; i++){
                murs.add(new Mur(offset+i*50,800, 50, 50));
                murs.add(new Mur(offset+i*50,200, 50, 50));
            }
            for (int i=1; i<5;i++){
                murs.add(new Mur(offset+5*50, 800 +i*50, 50, 50));
                murs.add(new Mur(offset+18*50, 800 +i*50, 50, 50));
                murs.add(new Mur(offset+5*50, 200 -i*50, 50, 50));
                murs.add(new Mur(offset+18*50, 200 -i*50, 50, 50));
            }
            murs.add(new Mur(offset+5*50, 700, 50, 50));
            murs.add(new Mur(offset+5*50, 650, 50, 50));
            murs.add(new Mur(offset+5*50, 600, 50, 50));
            murs.add(new Mur(offset+5*50, 400, 50, 50));
            murs.add(new Mur(offset+5*50, 350, 50, 50));
            murs.add(new Mur(offset+18*50, 700, 50, 50));
            murs.add(new Mur(offset+18*50, 650, 50, 50));
            murs.add(new Mur(offset+18*50, 600, 50, 50));
            murs.add(new Mur(offset+18*50, 400, 50, 50));
            murs.add(new Mur(offset+18*50, 350, 50, 50));
            murs.add(new Mur(offset+10*50, 400, 50, 50));
        }
        else if (index == 1) {
            for (int i=5; i<19; i++){
                murs.add(new Mur(offset+i*50,800, 50, 50));
                murs.add(new Mur(offset+i*50,200, 50, 50));
            }
            for (int i=1; i<5;i++){
                murs.add(new Mur(offset+5*50, 800 +i*50, 50, 50));
                murs.add(new Mur(offset+18*50, 800 +i*50, 50, 50));
                murs.add(new Mur(offset+5*50, 200 -i*50, 50, 50));
                murs.add(new Mur(offset+18*50, 200 -i*50, 50, 50));
            }
            murs.add(new Mur(offset+7*50, 700, 50, 50));
            murs.add(new Mur(offset+8*50, 650, 50, 50));
            murs.add(new Mur(offset+5*50, 600, 50, 50));
            murs.add(new Mur(offset+8*50, 400, 50, 50));
            murs.add(new Mur(offset+12*50, 350, 50, 50));
            murs.add(new Mur(offset+9*50, 700, 50, 50));
            murs.add(new Mur(offset+18*50, 650, 50, 50));
            murs.add(new Mur(offset+15*50, 600, 50, 50));
            murs.add(new Mur(offset+18*50, 400, 50, 50));
            murs.add(new Mur(offset+16*50, 350, 50, 50));
            murs.add(new Mur(offset+10*50, 400, 50, 50));
        }
        
        else if (index == 2) {
            for (int i=5; i<19; i++){
                murs.add(new Mur(offset+i*50,800, 50, 50));
                murs.add(new Mur(offset+i*50,200, 50, 50));
            }
            for (int i=1; i<5;i++){
                murs.add(new Mur(offset+5*50, 800 +i*50, 50, 50));
                murs.add(new Mur(offset+18*50, 800 +i*50, 50, 50));
                murs.add(new Mur(offset+5*50, 200 -i*50, 50, 50));
                murs.add(new Mur(offset+18*50, 200 -i*50, 50, 50));
            }
            murs.add(new Mur(offset+9*50, 650, 50, 50));
            murs.add(new Mur(offset+12*50, 350, 50, 50));
            murs.add(new Mur(offset+6*50, 650, 50, 50));
            murs.add(new Mur(offset+7*50, 600, 50, 50));
            murs.add(new Mur(offset+8*50, 400, 50, 50));
            murs.add(new Mur(offset+9*50, 350, 50, 50));
            murs.add(new Mur(offset+10*50, 400, 50, 50));
            murs.add(new Mur(offset+18*50, 400, 50, 50));
            murs.add(new Mur(offset+16*50, 350, 50, 50));
            murs.add(new Mur(offset+10*50, 400, 50, 50));
        }
        
        else if (index == 3) {
            for (int i=5; i<19; i++){
                murs.add(new Mur(offset+i*50,800, 50, 50));
                murs.add(new Mur(offset+i*50,200, 50, 50));
            }
            for (int i=1; i<5;i++){
                murs.add(new Mur(offset+5*50, 800 +i*50, 50, 50));
                murs.add(new Mur(offset+18*50, 800 +i*50, 50, 50));
                murs.add(new Mur(offset+5*50, 200 -i*50, 50, 50));
                murs.add(new Mur(offset+18*50, 200 -i*50, 50, 50));
            }
            murs.add(new Mur(offset+7*50, 750, 50, 50));
            murs.add(new Mur(offset+8*50, 400, 50, 50));
            murs.add(new Mur(offset+5*50, 250, 50, 50));
            murs.add(new Mur(offset+8*50, 600, 50, 50));
            murs.add(new Mur(offset+12*50, 350, 50, 50));
            murs.add(new Mur(offset+9*50, 700, 50, 50));
            murs.add(new Mur(offset+16*50, 400, 50, 50));
            murs.add(new Mur(offset+10*50, 400, 50, 50));
            murs.add(new Mur(offset+18*50, 400, 50, 50));
            murs.add(new Mur(offset+16*50, 350, 50, 50));
            murs.add(new Mur(offset+10*50, 400, 50, 50));
        }
        
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D= (Graphics2D) g; //cast le Graphics g en un Graphics2D
        joueur.draw(g2D);
        for (Mur mur: murs) mur.draw(g2D); //Pour chaque mur dans l'array list, dessine le mur correspondant
        
    }
    @Override // implémentation de la méthode abstract pour utiliser ActionListener(implémentation automatique)
    public void actionPerformed(ActionEvent ae) {
    }

    void keyPressed(KeyEvent e) { // on enlève les throws automatiques
        if(e.getKeyChar()=='q')joueur.toucheGauche=true; //si la touche sur laquelle on appuie est q, active une fonctionnalité qui est déplacement à gauche.
        if(e.getKeyChar()=='d')joueur.toucheDroite=true;
        if(e.getKeyChar()=='s')joueur.toucheBas=true;
        if(e.getKeyChar()=='z')joueur.toucheHaut=true;
    }

    void keyReleased(KeyEvent e) { // on enlève les throws automatiques
        if(e.getKeyChar()=='q')joueur.toucheGauche=false; //si on relache q, active une fonctionnalité qui est interromp le déplacement à gauche.
        if(e.getKeyChar()=='d')joueur.toucheDroite=false;
        if(e.getKeyChar()=='s')joueur.toucheBas=false;
        if(e.getKeyChar()=='z')joueur.toucheHaut=false;
    }
    
    
    
}
