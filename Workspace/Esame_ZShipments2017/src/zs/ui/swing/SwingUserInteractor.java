package zs.ui.swing;

import java.util.Optional;

import javax.swing.JOptionPane;

import zs.ui.UserInteractor;

public class SwingUserInteractor implements UserInteractor {

	public SwingUserInteractor() {
	}

	@Override
	public Optional<String> requestRecipientSign() {
		String result = JOptionPane.showInputDialog(null, "Enter Recipient Sign");
		return result != null
				? Optional.of(result)
				: Optional.empty();
		
	}

	@Override
	public Optional<String> requestUserNotes() {
		String result = JOptionPane.showInputDialog(null, "Enter notes");
		return result != null
				? Optional.of(result)
				: Optional.empty();
	}

	@Override
	public void somethingWentWrongWhileSaving(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

}
