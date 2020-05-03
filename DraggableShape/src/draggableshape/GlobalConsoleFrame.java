/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draggableshape;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Ewen
 */

class GlobalConsoleFrame extends JFrame {

    GlobalConsoleFrame() {
        setSize(1280, 1024);
        setTitle("Test");
        setContentPane(new AfficheImage("C:\\Users\\mezou\\Documents\\NetBeansProjects\\DraggableShape\\src\\masquescreamer.jpg"));
        getContentPane().setLayout(new BorderLayout());
        this.setVisible(true);
    }
}

class AfficheImage extends JPanel {

    Image eau;

    AfficheImage(String s) {
        eau = getToolkit().getImage(s);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(eau, 0, 0, getWidth(), getHeight(), this);
    }
}


