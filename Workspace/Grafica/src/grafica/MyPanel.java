package grafica;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



public class MyPanel extends JPanel implements ActionListener, DocumentListener, ItemListener, ListSelectionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lb1;
	JButton b, b1, b2, b3;
	JTextField txt1, txt2, txt3, txt4, txt5, txt6, txt7;

	JCheckBox ck1, ck2; // opzione

	JRadioButton r1, r2, r3;
	ButtonGroup grp;

	JList<String> list;
	
	JComboBox<String> combo;

	public MyPanel() {
		lb1 = new JLabel("Tizio");
		lb1.setFont(new Font("Courier New", Font.BOLD, 24));
		add(lb1);
		b = new JButton("Tizio/Caio");
		b.addActionListener(new MyListener(lb1));
		add(b);

		b1 = new JButton("Rosso");
		b2 = new JButton("Azzurro");
		add(b1);
		add(b2);
		b1.addActionListener(this); // tutti e due i bottoni associati allo
									// stesso ascoltatore
		b2.addActionListener(this);

		b3 = new JButton("Aggiorna");
		txt1 = new JTextField("Scrivere qui il testo", 25);
		txt2 = new JTextField(25);
		txt2.setEditable(false);
		// b3.addActionListener(this);
		// txt1.addActionListener(this); // actionListener qui è in ascolto sul
		// campo di testo per intercettare il tasto invio
		txt1.getDocument().addDocumentListener(this);
		add(txt1);
		add(txt2);
		add(b3);

		txt3 = new JTextField(15);
		txt3.setEditable(false);
		txt4 = new JTextField(15);
		txt4.setEditable(false);
		ck1 = new JCheckBox("Mele");
		ck2 = new JCheckBox("Pere");
		ck1.addItemListener(this);
		ck2.addItemListener(this);
		add(ck1);
		add(ck2);
		add(txt3);
		add(txt4);

		txt5 = new JTextField(15);
		txt5.setEditable(false);
		r1 = new JRadioButton("Mele");
		add(r1);
		r2 = new JRadioButton("Pere");
		add(r2);
		r3 = new JRadioButton("Arance");
		add(r3);
		add(txt5);
		grp = new ButtonGroup();
		grp.add(r1);
		grp.add(r2);
		grp.add(r3);
		r1.addActionListener(this);
		r2.addActionListener(this);
		r3.addActionListener(this);

		txt6 = new JTextField(15);
		txt6.setEditable(false);
		String voci[] = { "Rosso", "Giallo", "Verde", "Blu" };
		list = new JList<String>(voci);
		JScrollPane pane = new JScrollPane(list);
		list.setVisibleRowCount(3);
		list.addListSelectionListener(this);
		add(pane); // invece che add(list)
		add(txt6);
		
		txt7 = new JTextField(15);
		txt7.setEditable(false);
		combo = new JComboBox<String>();
		combo.setEditable(true);
		combo.addItem("Rosso"); combo.addItem("Giallo"); // aggiunge scelte
		combo.addItem("Verde"); combo.addItem("Blu");
		combo.addActionListener(this);
		add(combo);
		add(txt7);
	}

	/*
	 * public void paintComponent(Graphics g){ super.paintComponent(g)
	 * g.setColor(Color.red); g.fillRect(20,20, 100,80); g.setColor(Color.blue);
	 * g.drawRect(30,30, 80,60); Font f = new Font("Times New Roman", Font.BOLD,
	 * 20); g.setFont(f); g.setColor(Color.black);g.drawString("Ciao",50,60); }
	 */
	public void actionPerformed(ActionEvent e) { // per JBotton, JTextField e
													// JRadioButton, JCombo
		Object pulsantePremuto = e.getSource(); // da mettere solo se il tutto è
												// appartenente alla stessa
												// classe
		if (pulsantePremuto == b1)
			this.setBackground(Color.red); // del panel stesso
		if (pulsantePremuto == b2)
			this.setBackground(Color.cyan);

		// txt2.setText( txt1.getText() );

		String scelta1 = e.getActionCommand(); // per JRadioButton
		txt5.setText("Scelta corrente: " + scelta1);
		
		String scelta2 = (String) combo.getSelectedItem(); //per JCombo... occhio al cast!!! metodo restituisce Object
		txt7.setText("Scelta: " + scelta2);
	}

	public void insertUpdate(DocumentEvent e) {
		txt2.setText(txt1.getText());
	}

	public void removeUpdate(DocumentEvent e) {
		txt2.setText(txt1.getText());
	}

	public void changedUpdate(DocumentEvent e) {
	}

	public void itemStateChanged(ItemEvent e) { // evento è il cambio di stato
												// (JCheckBox)
		Object source = e.getItemSelectable(); // cambio di stato
		if (source == ck1)
			txt3.setText("Sono cambiate le mele");
		else
			txt3.setText("Sono cambiate le pere");
		// ora si controlla lo stato globale
		String frase = (ck1.isSelected() ? "Mele " : "") + (ck2.isSelected() ? "Pere" : "");
		txt4.setText(frase);
	}

	public void valueChanged(ListSelectionEvent e) { // per JList
		List<String> scelte = list.getSelectedValuesList();
		StringBuffer s = new StringBuffer();
		for (String scelta : scelte)
			s.append(scelta + " ");

		txt6.setText("Scelte: " + s);
	}
}
