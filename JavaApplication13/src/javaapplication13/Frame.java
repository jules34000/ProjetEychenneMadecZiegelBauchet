/**
 * 
 */
package javaapplication13;

import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * @author A.B.
 *
 * Cette classe sert à tester la classe 'DraggableShape'.
 */
public class Frame extends JFrame {

	public Frame() {
		super("Draggable Shapes");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		getContentPane().add("Center", new DraggableShape());
		pack();
	}
	
	public static void main(String[] args) {
		Frame frame = new Frame();
		frame.setVisible(true);
	}
}