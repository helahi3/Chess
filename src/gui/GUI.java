package gui;

import java.awt.event.ActionEvent;
import java.awt.*;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

public class GUI {

	private JFrame frame;
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardSquares = new JButton[8][8];
    private Image[][] chessPieceImages = new Image[2][6];
    private JPanel chessBoard;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	private final void setupNewGame() {
		
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		gui.setBorder(new EmptyBorder(5,5,5,5));
		JToolBar tools = new JToolBar();
		tools.setFloatable(false);
		gui.add(tools,BorderLayout.PAGE_START);
		Action newGameAction = new AbstractAction("New") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setupNewGame();
			}
		};
		tools.add(newGameAction);
		tools.add(new JButton("New"));
		

		chessBoard = new JPanel(new GridLayout(0,9)) {
            @Override
            public final Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                Dimension prefSize = null;
                Component c = getParent();
                if (c == null) {
                    prefSize = new Dimension(
                            (int)d.getWidth(),(int)d.getHeight());
                } else if (c!=null &&
                        c.getWidth()>d.getWidth() &&
                        c.getHeight()>d.getHeight()) {
                    prefSize = c.getSize();
                } else {
                    prefSize = d;
                }
                int w = (int) prefSize.getWidth();
                int h = (int) prefSize.getHeight();
                // the smaller of the two sizes
                int s = (w>h ? h : w);
                return new Dimension(s,s);
            }
        };

        Color ochre = new Color(204,119,34);
        chessBoard.setBackground(ochre);
        
	
	}

}
