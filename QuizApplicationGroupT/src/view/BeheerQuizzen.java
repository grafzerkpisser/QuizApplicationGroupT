package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.tableModels.OpdrachtTableModel;
import model.tableModels.QuizTableModel;

import javax.swing.JTextField;
import javax.swing.JComboBox;

import enumerations.Leerjaar;
import enumerations.Leraar;
import enumerations.QuizStatus;

import java.awt.CardLayout;

import javax.swing.JToggleButton;

public class BeheerQuizzen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblQuizzen;
	private JButton btnTerug;
	private JButton btnVerwijderenQuiz;
	private JButton btnWijzigenQuiz;
	private JButton btnToevoegenQuiz;
	@SuppressWarnings("unused")
	private JTextField textField;
	private JTable tblOpdrachtenLijst;
	private JTable tblToegevoegdeOpdrachten;

	private JPanel panelDetail;
	private JTextField txtOnderwerp;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbKlas;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbAuteur;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbStatus;
	private JButton btnRegistreer;
	private JButton btnTerugNaarOverzicht;
	private JButton btnRangschik;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbFilter;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbSorteer;
	private JButton btnToevoegenOpdracht;
	private JButton btnVerwijderOpdracht;
	private JPanel panelOverview;
	private JToggleButton tglBtnIsTest;
	private JToggleButton tglBtnUniekeDeelname;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BeheerQuizzen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		panelOverview = new JPanel();
		panelOverview.setBackground(Color.WHITE);
		contentPane.add(panelOverview, "name_127249328177351");
		panelOverview.setLayout(null);

		tblQuizzen = new JTable();
		tblQuizzen.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK,
				null, null, null));
		tblQuizzen.setBounds(10, 11, 384, 330);
		panelOverview.add(tblQuizzen);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(BeheerQuizzen.class
				.getResource("/view/ace.jpg")));
		label.setBounds(642, 11, 150, 50);
		panelOverview.add(label);

		btnToevoegenQuiz = new JButton("Toevoegen quiz");
		btnToevoegenQuiz.setBounds(404, 156, 391, 38);
		panelOverview.add(btnToevoegenQuiz);

		btnWijzigenQuiz = new JButton("Wijzigen quiz");
		btnWijzigenQuiz.setBounds(404, 205, 391, 38);
		panelOverview.add(btnWijzigenQuiz);

		btnVerwijderenQuiz = new JButton("Verwijderen quiz");
		btnVerwijderenQuiz.setBounds(404, 254, 391, 38);
		panelOverview.add(btnVerwijderenQuiz);

		btnTerug = new JButton("Terug");
		btnTerug.setBounds(404, 303, 391, 38);
		panelOverview.add(btnTerug);

		panelDetail = new JPanel();
		contentPane.add(panelDetail, "name_127249299873250");
		panelDetail.setLayout(null);

		JLabel lblOnderwerp = new JLabel("Onderwerp:");
		lblOnderwerp.setBounds(10, 10, 99, 20);
		panelDetail.add(lblOnderwerp);

		JLabel lblKlas = new JLabel("Klas:");
		lblKlas.setBounds(10, 41, 99, 20);
		panelDetail.add(lblKlas);

		JLabel lblAuteur = new JLabel("Auteur:");
		lblAuteur.setBounds(10, 72, 99, 20);
		panelDetail.add(lblAuteur);

		txtOnderwerp = new JTextField();
		txtOnderwerp.setBounds(89, 10, 407, 20);
		panelDetail.add(txtOnderwerp);
		txtOnderwerp.setColumns(10);

		cmbKlas = new JComboBox(new DefaultComboBoxModel(Leerjaar.values()));
		cmbKlas.setBounds(89, 41, 133, 22);
		panelDetail.add(cmbKlas);

		cmbAuteur = new JComboBox(new DefaultComboBoxModel(Leraar.values()));
		cmbAuteur.setBounds(89, 72, 133, 22);
		panelDetail.add(cmbAuteur);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(237, 41, 99, 20);
		panelDetail.add(lblStatus);

		cmbStatus = new JComboBox(new DefaultComboBoxModel(QuizStatus.values()));
		cmbStatus.setBounds(321, 40, 175, 22);
		panelDetail.add(cmbStatus);

		btnRegistreer = new JButton("Registreer quiz");
		btnRegistreer.setBounds(528, 40, 264, 23);
		panelDetail.add(btnRegistreer);

		btnTerugNaarOverzicht = new JButton("Terug naar overzicht");
		btnTerugNaarOverzicht.setBounds(528, 9, 264, 23);
		panelDetail.add(btnTerugNaarOverzicht);

		JPanel panelOpdrachten = new JPanel();
		panelOpdrachten.setBounds(10, 103, 782, 237);
		panelDetail.add(panelOpdrachten);
		panelOpdrachten.setLayout(null);

		tblOpdrachtenLijst = new JTable();
		tblOpdrachtenLijst.setBounds(10, 69, 345, 157);
		panelOpdrachten.add(tblOpdrachtenLijst);

		tblToegevoegdeOpdrachten = new JTable();
		tblToegevoegdeOpdrachten.setBounds(427, 69, 345, 157);
		panelOpdrachten.add(tblToegevoegdeOpdrachten);

		btnRangschik = new JButton("^^^");
		btnRangschik.setBounds(427, 35, 345, 23);
		panelOpdrachten.add(btnRangschik);

		JLabel lblAantalToegevoegdeOpdrachten = new JLabel(
				"Aantal toegevoegde opdrachten:");
		lblAantalToegevoegdeOpdrachten.setBounds(427, 10, 178, 20);
		panelOpdrachten.add(lblAantalToegevoegdeOpdrachten);

		JLabel lblCounter = new JLabel("0");
		lblCounter.setBounds(673, 10, 99, 20);
		panelOpdrachten.add(lblCounter);

		JLabel lblToonOpdrachten = new JLabel("Toon opdrachten van categorie:");
		lblToonOpdrachten.setBounds(10, 10, 157, 20);
		panelOpdrachten.add(lblToonOpdrachten);

		JLabel lblSorteerOp = new JLabel("Sorteer opdrachten op:");
		lblSorteerOp.setBounds(10, 36, 157, 20);
		panelOpdrachten.add(lblSorteerOp);

		cmbFilter = new JComboBox();
		cmbFilter.setBounds(177, 9, 178, 22);
		panelOpdrachten.add(cmbFilter);

		cmbSorteer = new JComboBox();
		cmbSorteer.setBounds(177, 35, 178, 22);
		panelOpdrachten.add(cmbSorteer);

		btnToevoegenOpdracht = new JButton(">>");
		btnToevoegenOpdracht.setBounds(365, 112, 52, 23);
		panelOpdrachten.add(btnToevoegenOpdracht);

		btnVerwijderOpdracht = new JButton("<<");
		btnVerwijderOpdracht.setBounds(365, 146, 52, 23);
		panelOpdrachten.add(btnVerwijderOpdracht);

		tglBtnIsTest = new JToggleButton("Is test?");
		tglBtnIsTest.setBounds(232, 71, 121, 23);
		panelDetail.add(tglBtnIsTest);

		tglBtnUniekeDeelname = new JToggleButton("Unieke deelname");
		tglBtnUniekeDeelname.setBounds(375, 71, 121, 23);
		panelDetail.add(tglBtnUniekeDeelname);

	}

	public void displayErrorMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	// ActionCallers
	public void addTerugActionListener(ActionListener listenForTerugButton) {
		btnTerug.addActionListener(listenForTerugButton);
	}

	public void addToevoegenQuizActionListener(
			ActionListener listenForToevoegenbutton) {
		btnToevoegenQuiz.addActionListener(listenForToevoegenbutton);
	}

	public void addWijzigenQuizActionListener(
			ActionListener listenForWijzigenButton) {
		btnWijzigenQuiz.addActionListener(listenForWijzigenButton);
	}

	public void addVerwijderenQuizActionListener(
			ActionListener listenForVerwijderButton) {
		btnVerwijderenQuiz.addActionListener(listenForVerwijderButton);
	}

	public void addTerugNaarQuizOverviewActionListener(
			ActionListener listenerForTerugNaarOverzichtButton) {
		btnTerugNaarOverzicht
				.addActionListener(listenerForTerugNaarOverzichtButton);
	}

	public void addRegistreerQuizActionListener(
			ActionListener listenForRegistreerButton) {
		btnRegistreer.addActionListener(listenForRegistreerButton);
	}

	public void addVoegOpdrachtToActionListener(
			ActionListener listenForToevoegenButton) {
		btnToevoegenOpdracht.addActionListener(listenForToevoegenButton);
	}

	public void addVerwijderOpdrachtActionListener(
			ActionListener listenForVerwijderOpdrachtButton) {
		btnVerwijderOpdracht
				.addActionListener(listenForVerwijderOpdrachtButton);
	}

	// Getters
	public Integer getSelectedRowFromTable() {
		return tblQuizzen.getSelectedRow();
	}

	public Integer getSelectedRowFromOpdrachtTable() {
		return tblOpdrachtenLijst.getSelectedRow();
	}

	public Integer getSelectedRowFromQuizOpdrachtTable() {
		return tblToegevoegdeOpdrachten.getSelectedRow();
	}

	public Integer getRowCountQuizOpdrachtTable() {
		return tblToegevoegdeOpdrachten.getRowCount();
	}

	public Leraar getLeraar() {
		return Leraar.valueOf(cmbAuteur.getSelectedItem().toString());
	}

	public Leerjaar getLeerjaar() {
		return Leerjaar.valueOf(cmbKlas.getSelectedItem().toString());
	}

	public String getOnderwerp() {
		return txtOnderwerp.getText();
	}

	public QuizStatus getQuizStatus() {
		return QuizStatus.valueOf(cmbStatus.getSelectedItem().toString());
	}

	public Boolean getIsTest() {
		return tglBtnIsTest.isSelected();
	}

	public Boolean getIsUniekeDeelname() {
		return tglBtnUniekeDeelname.isSelected();
	}

	// Setters
	public void setQuizOverviewTable(QuizTableModel quizTableModel) {
		this.tblQuizzen.setModel(quizTableModel);
	}

	public void setOpdrachtTable(OpdrachtTableModel opdrachtTableModel) {
		this.tblOpdrachtenLijst.setModel(opdrachtTableModel);
	}

	public void setOpdrachtAddedTable(OpdrachtTableModel opdrachtTableModel) {
		this.tblToegevoegdeOpdrachten.setModel(opdrachtTableModel);
	}

	public void setCmbLeerjaar(Leerjaar leerjaar) {
		cmbKlas.setSelectedItem(leerjaar);
	}

	public void setCmbLeraar(Leraar leraar) {
		cmbAuteur.setSelectedItem(leraar);
	}

	public void setOnderwerp(String onderwerp) {
		txtOnderwerp.setText(onderwerp);
	}

	public void setStatus(QuizStatus quizStatus) {
		cmbStatus.setSelectedItem(quizStatus);
	}

	public void setIsTest(Boolean isTest) {
		tglBtnIsTest.setSelected(isTest);
	}

	public void setIsUniekeDeelname(Boolean isUniekeDeelname) {
		tglBtnUniekeDeelname.setSelected(isUniekeDeelname);
	}

	// Methods
	public void sluitFrame() {
		this.dispose();
	}

	public void activeerDetailPanel() {
		panelOverview.setVisible(false);
		panelDetail.setVisible(true);
	}

	public void activeerOverview() {
		panelDetail.setVisible(false);
		panelOverview.setVisible(true);
	}

	public void resetScreen() {
		txtOnderwerp.setText("");
		cmbAuteur.setSelectedItem(Leerjaar.eerste);
		cmbKlas.setSelectedItem(Leerjaar.eerste);
		cmbStatus.setSelectedItem(QuizStatus.IN_CONSTRUCTIE);
	}

	public void removeActionListenerFromRegistreer() {
		for (ActionListener a : btnRegistreer.getActionListeners()) {
			btnRegistreer.removeActionListener(a);
		}
	}

	public Integer showUserMessageYesNo(String messageText, String title) {
		return JOptionPane.showConfirmDialog(null, messageText, title,
				JOptionPane.YES_NO_OPTION);
	}
}
