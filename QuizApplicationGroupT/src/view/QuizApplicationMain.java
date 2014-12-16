package view;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class QuizApplicationMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPaneMain;
	private JPanel panelMain;
	private JLabel lblImage;
	private JButton btnBeherenOpdrachten;
	private JButton btnBeherenVanQuizzen;
	private JButton btnDeelnemenAanQuiz;
	private JButton btnOverzichtScores;
	private JButton btnQuizRapport;
	private JButton btnQuizLijsten;
	private JButton btnInstellingen;
	private JButton btnAfsluiten;

	/**
	 * Create the frame.
	 */
	public QuizApplicationMain() {
		setTitle("Groep T QuizApplication");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 400);
		contentPaneMain = new JPanel();
		contentPaneMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneMain);
		contentPaneMain.setLayout(null);

		panelMain = new JPanel();
		panelMain.setBackground(Color.WHITE);
		panelMain.setBounds(10, 11, 805, 352);
		contentPaneMain.add(panelMain);
		panelMain.setLayout(null);

		lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(QuizApplicationMain.class
				.getResource("/view/ace.jpg")));
		lblImage.setBounds(10, 11, 150, 50);
		panelMain.add(lblImage);

		btnBeherenOpdrachten = new JButton("Beheren van opdrachten");
		btnBeherenOpdrachten.setBounds(404, 11, 391, 38);
		panelMain.add(btnBeherenOpdrachten);

		btnBeherenVanQuizzen = new JButton("Beheren van quizzen/testen");
		btnBeherenVanQuizzen.setBounds(404, 60, 391, 38);
		panelMain.add(btnBeherenVanQuizzen);

		btnDeelnemenAanQuiz = new JButton("Deelnemen aan quiz");
		btnDeelnemenAanQuiz.setBounds(404, 109, 391, 38);
		panelMain.add(btnDeelnemenAanQuiz);

		btnOverzichtScores = new JButton("Overzicht scores");
		btnOverzichtScores.setBounds(404, 158, 391, 38);
		panelMain.add(btnOverzichtScores);

		btnQuizRapport = new JButton("Quiz rapport");
		btnQuizRapport.setBounds(404, 207, 391, 38);
		panelMain.add(btnQuizRapport);

		btnQuizLijsten = new JButton("Quiz lijsten");
		btnQuizLijsten.setBounds(404, 256, 391, 38);
		panelMain.add(btnQuizLijsten);

		btnInstellingen = new JButton("Instellingen van de quizApplicatie");
		btnInstellingen.setBounds(404, 303, 391, 38);
		panelMain.add(btnInstellingen);

		btnAfsluiten = new JButton("Afsluiten");
		btnAfsluiten.setBounds(10, 303, 391, 38);
		panelMain.add(btnAfsluiten);

		JLabel lblNewLabel = new JLabel(
				"<html>Quiz applicatie (V1.0) <br><br>Gemaakt door:<br><br>\r\n      - Bart Blondeel<br>\r\n\t- Gavin Bogaerts<br>\r\n\t- Eli van Duffel<br>\r\n\t- Brecht Richard</html>");
		lblNewLabel.setBounds(10, 60, 384, 234);
		panelMain.add(lblNewLabel);
	}

	public void displayErrorMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	public void addBeherenVanQuizzenListener(
			ActionListener listenForBeherenQuizzenButton) {
		btnBeherenVanQuizzen.addActionListener(listenForBeherenQuizzenButton);
	}
	public void addAfsluitenListener( ActionListener listenForAfsluitenButton){
		btnAfsluiten.addActionListener(listenForAfsluitenButton);
	}
	public void addBeherenVanOpdrachtenListener(ActionListener actionListener){
		btnBeherenOpdrachten.addActionListener(actionListener);
	}
	
}
