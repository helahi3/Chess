package gui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.GridLayout;

public class Tutorial {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tutorial window = new Tutorial();
					window.frame.setVisible(true);
					JButton b = new JButton("Click");
					b.setBounds(200,200,40,10);
					window.frame.add(b);
					window.frame.setSize(500, 400);
					window.frame.setLayout(null);//using no layout managers  
					window.frame.setVisible(true);//making the frame visible  

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tutorial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
