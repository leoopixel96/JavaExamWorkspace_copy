package zs.ui;

import javax.swing.JOptionPane;

public class SwingUserInteractor implements UserInteractor {

	public SwingUserInteractor() {
	}

	@Override
	public String requestRecipientSign() {
		return JOptionPane.showInputDialog(null, "Enter Recipient Sign");
	}

	@Override
	public String requestUserNotes() {
		return JOptionPane.showInputDialog(null, "Enter notes");
	}

	@Override
	public void somethingWentWrongWhileSaving(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

}
