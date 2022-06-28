package impostezannonia.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import impostezannonia.model.Imposte;
import impostezannonia.model.TipologiaContribuente;
import impostezannonia.ui.controller.Controller;

public class MainFrame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Controller controller;

	private JComboBox<TipologiaContribuente> boxTipologie;
	private JEuroTextField redditoLordo;
	private JTextField redditoImponibile;
	private JEuroTextField speseMediche;
	private JTextField impostaLorda;
	private JTextField impostaNetta;

	public MainFrame(Controller controller) {
		this.controller = controller;

		initGUI();
		bindData();
	}

	private void bindData() {
		for (TipologiaContribuente tipologia : controller.getTipologie())
			boxTipologie.addItem(tipologia);
	}

	private void initGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

		this.boxTipologie = new JComboBox<>();
		this.add(boxTipologie);

		JPanel first = new JPanel();
		first.setLayout(new BoxLayout(first, BoxLayout.LINE_AXIS));
		{
			JLabel label = new JLabel("Reddito lordo ");

			first.add(label);

			redditoLordo = new JEuroTextField(0.0);
			this.redditoLordo.addActionListener(this);
			first.add(redditoLordo);
		}
		this.getContentPane().add(first);

		JPanel second = new JPanel();
		second.setLayout(new BoxLayout(second, BoxLayout.LINE_AXIS));
		{
			JLabel label = new JLabel("Reddito imponibile ");

			second.add(label);

			redditoImponibile = new JTextField(15);
			redditoImponibile.setEditable(false);
			second.add(redditoImponibile);
		}
		this.getContentPane().add(second);

		JPanel third = new JPanel();
		third.setLayout(new BoxLayout(third, BoxLayout.LINE_AXIS));
		{
			JLabel label = new JLabel("Spese Mediche ");

			third.add(label);

			speseMediche = new JEuroTextField(0.0);
			this.speseMediche.addActionListener(this);
			third.add(speseMediche);
		}
		this.getContentPane().add(third);

		JPanel fourth = new JPanel();
		fourth.setLayout(new BoxLayout(fourth, BoxLayout.LINE_AXIS));
		{
			JLabel label = new JLabel("Imposta lorda ");

			fourth.add(label);

			impostaLorda = new JTextField(15);
			impostaLorda.setEditable(false);
			fourth.add(impostaLorda);
		}
		this.getContentPane().add(fourth);

		JPanel fifty = new JPanel();
		fifty.setLayout(new BoxLayout(fifty, BoxLayout.LINE_AXIS));
		{
			JLabel label = new JLabel("Imposta netta ");

			fifty.add(label);

			impostaNetta = new JTextField(15);
			impostaNetta.setEditable(false);
			fifty.add(impostaNetta);
		}
		this.getContentPane().add(fifty);

		this.pack();
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		TipologiaContribuente selected = (TipologiaContribuente) this.boxTipologie.getSelectedItem();
		this.boxTipologie.addActionListener(this);
		
		Imposte imposte = controller.calcolaImposte(redditoLordo.getValueAsDouble(), speseMediche.getValueAsDouble(),
				selected);
		
		redditoImponibile.setText(controller.formatta(imposte.getRedditoImponibile()));
		impostaLorda.setText(controller.formatta(imposte.getImpostaLorda()));
		impostaNetta.setText(controller.formatta(imposte.getImpostaNetta()));
	}

}
