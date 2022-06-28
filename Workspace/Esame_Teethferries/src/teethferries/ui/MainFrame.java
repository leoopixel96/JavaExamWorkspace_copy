package teethferries.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import teethferries.model.Porto;
import teethferries.model.Servizio;
import teethferries.model.Tratta;

public class MainFrame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controller controller;

	private JComboBox<Porto> partenza;
	private JComboBox<Porto> arrivo;
	private JComboBox<Tratta> tratte;
	private JTextArea result;

	public MainFrame(MainController controller) {
		this.controller = controller;

		initGUI();
		pack();
	}

	private void initGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		this.setLocation(200, 100);

		JPanel first = new JPanel();
		{
			first.setLayout(new BoxLayout(first, BoxLayout.LINE_AXIS));

			JLabel da = new JLabel("Da: ");
			partenza = new JComboBox<>(this.controller.elencoPorti().toArray(new Porto[0]));
			partenza.addActionListener(this);

			JLabel a = new JLabel("A: ");
			arrivo = new JComboBox<>(this.controller.elencoPorti().toArray(new Porto[0]));
			arrivo.addActionListener(this);

			first.add(da);
			first.add(partenza);
			first.add(a);
			first.add(arrivo);
		}
		this.getContentPane().add(first);

		JPanel second = new JPanel();
		{
			second.setLayout(new BoxLayout(second, BoxLayout.LINE_AXIS));

			JLabel label = new JLabel("Tratte servite: ");
			tratte = new JComboBox<>();
			tratte.addActionListener(this);

			second.add(label);
			second.add(tratte);
		}
		this.getContentPane().add(second);

		JPanel third = new JPanel();
		{
			third.setLayout(new BoxLayout(third, BoxLayout.PAGE_AXIS));

			JLabel label = new JLabel("Servizi disponibili:");
			label.setAlignmentX(CENTER_ALIGNMENT);

			result = new JTextArea(5, 10);
			result.setEditable(false);

			third.add(label);
			third.add(result);
		}
		this.getContentPane().add(third);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == partenza) {
			// Porto lastSelectedPartenza =
			// partenza.getItemAt(partenza.getSelectedIndex());
			refreshComboTratte();
		} else if (source == arrivo) {
			// Porto lastSelectedArrivo =
			// arrivo.getItemAt(arrivo.getSelectedIndex());
			refreshComboTratte();
		} else if (source == tratte) {
			Tratta lastSelectedTratta = tratte.getItemAt(tratte.getSelectedIndex());
			/*
			 * if (lastSelectedTratta == null) return;
			 */
			mostraPercorsi(lastSelectedTratta);
		}
	}

	public void mostraPercorsi(Tratta t) {
		result.setText("");

		for (Servizio s : controller.listaServizi()) {
			if (s.getTratta().getId().equals(t.getId()))
				result.append(s.toString() + "\n");
		}
	}

	public void refreshComboTratte() {
		this.tratte.removeAllItems();
		Porto partenzaSelected = (Porto) this.partenza.getSelectedItem();
		Porto arrivoSelected = (Porto) this.arrivo.getSelectedItem();

		for (Tratta tratta : controller.listaTratte()) {
			if (tratta.getPortoPartenza().equalsOrANY(partenzaSelected)
					&& tratta.getPortoArrivo().equalsOrANY(arrivoSelected))
				this.tratte.addItem(tratta);
		}
	}
}
