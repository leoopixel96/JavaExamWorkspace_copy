package impostezannonia.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public class JEuroTextField extends JFormattedTextField implements PropertyChangeListener, ActionListener {
	private static final long serialVersionUID = 1L;
	
	private static NumberFormatter amountDisplayFormat = getNewDisplayFormat();
	private static NumberFormatter amountEditFormat = getNewEditFormat();
	
	public JEuroTextField(double initialValue) { 
		super(new DefaultFormatterFactory(amountDisplayFormat, amountDisplayFormat, amountEditFormat));
		//this.setFormatter(formatter);
		this.setFont(new Font("Courier New", Font.BOLD, 12));
		this.setValue(initialValue);
		this.setHorizontalAlignment(JTextField.RIGHT);
		this.addPropertyChangeListener("value", this);
	}
	
	private static NumberFormatter getNewDisplayFormat() {
		NumberFormat format = NumberFormat.getCurrencyInstance();
		format.setMaximumFractionDigits(2);
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setMinimum(5.0);
		formatter.setMaximum(10000000.0);
		formatter.setAllowsInvalid(false);
		formatter.setOverwriteMode(true);
		return formatter;
	}
	
	private static NumberFormatter getNewEditFormat() {
		return new NumberFormatter(NumberFormat.getNumberInstance());
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		JEuroTextField source = (JEuroTextField) evt.getSource();
        double amount = ((Number) source.getValue()).doubleValue();
        source.setValue(amount);
    }

	@Override
	public void actionPerformed(ActionEvent evt) {
		JEuroTextField source = (JEuroTextField) evt.getSource();
        double amount = ((Number) source.getValue()).doubleValue();
        source.setValue(amount);	
    }
	
	public double getValueAsDouble() {
		return (double) super.getValue();
	}

}


