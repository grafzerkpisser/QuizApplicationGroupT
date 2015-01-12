package view;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class BeheerInstellingen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5439618479773552697L;
	private JButton OKbtn;
	private JPanel contentPane;

	public JPanel getContentPane() {
		return contentPane;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox comboBox;

	@SuppressWarnings("rawtypes")
	public JComboBox getComboBox() {
		return comboBox;
	}

	@SuppressWarnings("rawtypes")
	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}

	@SuppressWarnings({ "rawtypes" })
	public BeheerInstellingen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, "name_9357758660760");
		panel.setLayout(null);

		comboBox = new JComboBox();
		comboBox.setBounds(103, 93, 197, 24);
		panel.add(comboBox);

		JTextArea txtrKies = new JTextArea();
		txtrKies.setText("Kies Databse / schrijver lezer");
		txtrKies.setBounds(103, 52, 547, 15);
		panel.add(txtrKies);

		OKbtn = new JButton("OK");
		OKbtn.setBounds(103, 151, 117, 25);
		panel.add(OKbtn);

	}

	public void changeStrategyKeuzeActionListner(ActionListener actionListener) {
		OKbtn.addActionListener(actionListener);
	}

	public void displayErrorMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
}
