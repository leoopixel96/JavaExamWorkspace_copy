package grafica;

import java.awt.*;

import javax.swing.*;

public class Finestra extends JFrame { // è anche un JFrame
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JTextArea ta;

	public Finestra(String titolo) {
		super(titolo);

		// JFrame f = new JFrame("Esempio 1");
		// this.setLayout(new FlowLayout()); // cambia il layout della finestra
		this.setSize(500, 500); // dimensioni finestra
		this.setLocation(200, 100); // localizzazione dall'angolo sinistro in
									// alto dello schermo

		JLabel l = new JLabel("Area di output");
		ta = new JTextArea(8, 40);
		ta.setEditable(false);
		Container container = this.getContentPane(); // recupera un container,
														// che sta
		// sotto al JFrame
		// JPanel panel = new JPanel(); // crea un pannello, qui quello di
		// default
		// panel.setBackground(Color.white);
		JPanel p = new MyPanel();
		p.add(l);
		p.add(ta);
		container.add(p); // aggiunge il pannello al container

		this.pack(); // dimensiona il frame in modo da contenere esattamente il
		// pannello
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // chiusura
																// automatica
	}

	public Finestra() {
		this("Senza titolo");
	}

	public void print(String txt) {
		ta.append(txt);
	}
}
