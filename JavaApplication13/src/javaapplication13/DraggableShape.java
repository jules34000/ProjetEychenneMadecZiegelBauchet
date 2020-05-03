/**
 *
 */
package javaapplication13;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Polygon;

/**
 * @author A.B.
 *
 */
public class DraggableShape extends Canvas implements MouseMotionListener, MouseListener {

    /**
     * Largeur du carré
     */
    public static final int SIDE = 30;

    /**
     * Indique si le carré est en mouvement ou non
     */
    private boolean moving = false;

    /**
     * Abscisse du coin supérieur-gauche
     */
    private int xPosition;

    /**
     * Ordonnée du coin supérieur-gauche
     */
    private int yPosition;

    /**
     * Abscisse du point où on fait glisser le carré
     */
    private int xDragg;

    /**
     * Ordonnée du point où on fait glisser le carré
     */
    private int yDragg;

    /**
     * Rectangle qui représente le périmètre de la fenêtre. Cette attribut sert
     * essentiellement à capter si la fenêtre a été redimensionner.
     */
    private Rectangle frameRect;

    /**
     * Pour assurer la fluidité du mouvement du carré sans qu'il aura des
     * troubles On se sert de cet attribut pour simuler la technique du
     * double-buffering.
     */
    private Image doubleBufferImage;
    private int compteurVie = 5 ;

    /**
     * Crée une nouvelle instance de la classe 'DraggableShape'.
     */
    public DraggableShape() {
        setSize(400, 400);
        frameRect = new Rectangle(400, 401);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    /**
     * Quand on maintient une touche de la souris enfoncée et on fait glisser le
     * curseur.
     */
    public void mouseDragged(MouseEvent e) {
        if (moving) {
            xPosition = e.getX() - xDragg;
            yPosition = e.getY() - yDragg;
            repaint();
        }
    }
    int[] listeXbordsLabyrinthe = { 30,0,100,30,100,180,300};    // coordonnées X des rectangles composants le labytinthe
    int[] listeYbordsLabyrinthe = { 80,80,0,230,150,230,150};    // coordonnées Y des rectangles composants le labytinthe
    int[] listeLargeurLabyrinthe = {20,30,20,150,200,20,20};     // largeur rectangle composant le labyrinthe
    int[] listeLongueurLabyrinthe = { 150,20,150,20,20,200,270};    // longueur rectangle composant le labyrinthe

    @Override
    public void paint(Graphics g) {
        // Créer un objet de type Graphics2D
        Graphics2D g2 = (Graphics2D) g;

        // Colorer le fond en blanc
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, frameRect.width, frameRect.height);
        
        // Dessiner le carré en rouge
        g2.setColor(Color.RED);
        g2.fillRect(xPosition, yPosition, SIDE, SIDE);
        g2.setColor(Color.BLUE);

        for (int i = 0; i < listeXbordsLabyrinthe.length; i++) {
            g2.fillRect(listeXbordsLabyrinthe[i], listeYbordsLabyrinthe[i], listeLargeurLabyrinthe[i], listeLongueurLabyrinthe[i]);
        }
        g2.setColor(Color.GREEN);
        g2.fillRect(230,420,30,30);
        

    }

    @Override
    public void update(Graphics g) {
        if (!frameRect.equals(getBounds())) {
            frameRect = getBounds();
            doubleBufferImage = createImage(frameRect.width, frameRect.height);
        }

        // Ne pas dépasser les bords de la fenêtre
        if (xPosition < 0) {
            xPosition = 0;
        } else if (xPosition > (frameRect.width - SIDE)) {
            xPosition = frameRect.width - SIDE;
        }

        // Ne pas dépasser les bords de la fenêtre
        if (yPosition < 0) {
            yPosition = 0;
        } else if (yPosition > (frameRect.height - SIDE)) {
            yPosition = frameRect.height - SIDE;
        }
        if (xPosition > 1920) {
            xPosition = 1920;
        } else if (xPosition > (frameRect.width - SIDE)) {
            xPosition = frameRect.width - SIDE;
        }

        // Ne pas dépasser les bords de la fenêtre
        if (yPosition > 1080) {
            yPosition = 1080;
        } else if (yPosition > (frameRect.height - SIDE)) {
            yPosition = frameRect.height - SIDE;
        }
        if (xPosition >230-SIDE && xPosition < 260 && yPosition > 420-SIDE && yPosition < 450 ){
            System.out.println("Bien Ouej Beau Gosse");
            System.exit(0);
        }
        for (int j = 0; j < listeXbordsLabyrinthe.length; j++) {
            if ( xPosition <= listeXbordsLabyrinthe[j] + listeLargeurLabyrinthe[j]  && xPosition + SIDE> listeXbordsLabyrinthe[j]  && yPosition + SIDE > listeYbordsLabyrinthe[j] && yPosition < listeYbordsLabyrinthe[j] + listeLongueurLabyrinthe[j]) {
                xPosition = 0;
                yPosition = 0;
                moving = false ;
                compteurVie = compteurVie -1;
                System.out.println("il ne te reste plus que "+compteurVie+"vie");
                if (compteurVie == 0){
                    System.out.println("game over NULLOS");
                    System.exit(0);
                }
            }
        }
        
        
        // Dessiner sur l'image intermédiaire
        Graphics imageGraphics = doubleBufferImage.getGraphics();
        paint(imageGraphics);

        // Afficher l'image intermédiaire
        g.drawImage(doubleBufferImage, 0, 0, this);
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        if ((x < xPosition) || (x > (xPosition + SIDE))) {
            return;
        }

        int y = e.getY();
        if ((y < yPosition) || (y > (yPosition + SIDE))) {
            return;
        }

        xDragg = x - xPosition;
        yDragg = y - yPosition;
        moving = true;
    }

    public void mouseReleased(MouseEvent e) {
        if (moving) {
            xPosition = e.getX() - xDragg;
            yPosition = e.getY() - yDragg;
            moving = false;
            repaint();
        }
    }

}