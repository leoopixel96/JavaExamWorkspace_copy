package grafica;

import java.awt.EventQueue;

import java.awt.CardLayout;



public class MyWin {

	private Finestra frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyWin window = new MyWin();
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
	public MyWin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new Finestra();
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
	}

}
