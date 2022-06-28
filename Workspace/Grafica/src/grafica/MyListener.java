package grafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class MyListener implements ActionListener {
	private JLabel lb1;

	public MyListener(JLabel label) {
		lb1 = label;
	}

	public void actionPerformed(ActionEvent e) {
		if (lb1.getText().equals("Tizio"))
			lb1.setText("Caio");
		else
			lb1.setText("Tizio");
	}
}
