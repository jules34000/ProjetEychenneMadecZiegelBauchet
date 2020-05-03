/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetcollision;

import java.awt.Dimension;
import java.awt.Toolkit;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author bluet
 */
public class Projetcollision {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        projetcollisionframe  fenetre = new projetcollisionframe ();
        fenetre.setSize(1000, 1000);
        Dimension TailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
        fenetre.setLocationRelativeTo(null);
        fenetre.setResizable(false);
        fenetre.setTitle("Jeu projet détection collision");
        fenetre.setVisible(true);
        fenetre.setDefaultCloseOperation(EXIT_ON_CLOSE); //arrêt de l'éxécution quand fenetre fermée
    }
    
}
