package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import model.tableModels.OpdrachtTableModel;
import enumerations.Leraar;
import enumerations.OpdrachtCategorie;

public class BeheerOpdrachten extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblOpdrachtOverview;
	private final ButtonGroup buttonGroupOpdrachtType = new ButtonGroup();
	private JTextField txtVraag;
	private JTextField txtJuisteAntwoord;
	private JTextField txtMaxPogingen;
	private JTextField txtMaxTijd;
	private JTable tblKeuzes;
	private JTextField txtKeuze;
	private JTable tblHints;
	private JTextField txtHint;
	private JPanel panelOverview;
	private JButton btnToevoegenOpdracht;
	private JButton btnWijzigenOpdracht;
	private JButton btnVerwijderenOpdracht;
	private JButton btnTerug;
	private JPanel panelDetail;
	private JRadioButton rdbtnOpdracht ;
	private JRadioButton rdbtnMeerkeuze;
	private JRadioButton rdbtnOpsomming;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbLeerkracht;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbCategorie;
	private JButton btnToevoegenKeuze;
	private JButton btnToevoegenHint;
	private JPanel panelOpdrachtType;
	private JPanel panelMeerkeuze;
	private JButton btnBewaar;
	private JButton btnTerugNaarOverview ;
	private JPanel panelOpsomming;
	private JPanel panelDefault;
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BeheerOpdrachten() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		panelOverview = new JPanel();
		panelOverview.setBackground(Color.WHITE);
		contentPane.add(panelOverview, "name_291009286564945");
		panelOverview.setLayout(null);
		
		tblOpdrachtOverview = new JTable();
		tblOpdrachtOverview.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK,
						null, null, null));
		tblOpdrachtOverview.setBounds(10, 11, 384, 330);
		panelOverview.add(tblOpdrachtOverview);
		
		btnToevoegenOpdracht = new JButton("Toevoegen opdracht");
		btnToevoegenOpdracht.setBounds(411, 156, 391, 38);
		panelOverview.add(btnToevoegenOpdracht);
		
		btnWijzigenOpdracht = new JButton("Wijzigen opdracht");
		btnWijzigenOpdracht.setBounds(411, 205, 391, 38);
		panelOverview.add(btnWijzigenOpdracht);
		
		btnVerwijderenOpdracht = new JButton("Verwijderen opdracht");
		btnVerwijderenOpdracht.setBounds(411, 254, 391, 38);
		panelOverview.add(btnVerwijderenOpdracht);
		
		btnTerug = new JButton("Terug");
		btnTerug.setBounds(411, 303, 391, 38);
		panelOverview.add(btnTerug);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(BeheerOpdrachten.class.getResource("/view/ace.jpg")));
		label.setBounds(652, 10, 150, 50);
		panelOverview.add(label);
		
		panelDetail = new JPanel();
		contentPane.add(panelDetail, "name_291021685853994");
		panelDetail.setLayout(null);
		
		JLabel lblVraag = new JLabel("Vraag:");
		lblVraag.setBounds(10, 10, 99, 20);
		panelDetail.add(lblVraag);
		
		rdbtnOpdracht = new JRadioButton("Opdracht");
		buttonGroupOpdrachtType.add(rdbtnOpdracht);
		rdbtnOpdracht.setBounds(10, 89, 99, 23);
		panelDetail.add(rdbtnOpdracht);
		
		rdbtnMeerkeuze = new JRadioButton("Meerkeuze");
		buttonGroupOpdrachtType.add(rdbtnMeerkeuze);
		rdbtnMeerkeuze.setBounds(10, 141, 109, 23);
		panelDetail.add(rdbtnMeerkeuze);
		
		rdbtnOpsomming = new JRadioButton("Opsomming");
		buttonGroupOpdrachtType.add(rdbtnOpsomming);
		rdbtnOpsomming.setBounds(10, 115, 109, 23);
		panelDetail.add(rdbtnOpsomming);
		
		txtVraag = new JTextField();
		txtVraag.setBounds(119, 10, 683, 20);
		panelDetail.add(txtVraag);
		txtVraag.setColumns(10);
		
		JLabel lblJuisteAntwoord = new JLabel("Juiste antwoord:");
		lblJuisteAntwoord.setBounds(10, 36, 99, 20);
		panelDetail.add(lblJuisteAntwoord);
		
		txtJuisteAntwoord = new JTextField();
		txtJuisteAntwoord.setBounds(119, 36, 683, 20);
		panelDetail.add(txtJuisteAntwoord);
		txtJuisteAntwoord.setColumns(10);
		
		JLabel lblMaxPogingen = new JLabel("Max pogingen:");
		lblMaxPogingen.setBounds(10, 62, 99, 20);
		panelDetail.add(lblMaxPogingen);
		
		txtMaxPogingen = new JTextField();
		txtMaxPogingen.setBounds(119, 62, 86, 20);
		panelDetail.add(txtMaxPogingen);
		txtMaxPogingen.setColumns(10);
		
		JLabel lblMaxTijd = new JLabel("Max tijd:");
		lblMaxTijd.setBounds(211, 62, 47, 20);
		panelDetail.add(lblMaxTijd);
		
		txtMaxTijd = new JTextField();
		txtMaxTijd.setBounds(268, 62, 86, 20);
		panelDetail.add(txtMaxTijd);
		txtMaxTijd.setColumns(10);
		
		JLabel lblLeerkracht = new JLabel("Leerkracht:");
		lblLeerkracht.setBounds(364, 62, 99, 20);
		panelDetail.add(lblLeerkracht);
		
		cmbLeerkracht = new JComboBox(new DefaultComboBoxModel(Leraar.values()));
		cmbLeerkracht.setBounds(434, 61, 134, 22);
		panelDetail.add(cmbLeerkracht);
		
		cmbCategorie = new JComboBox(new DefaultComboBoxModel(OpdrachtCategorie.values()));
		cmbCategorie.setBounds(668, 61, 134, 22);
		panelDetail.add(cmbCategorie);
		
		JLabel lblCategorie = new JLabel("Categorie:");
		lblCategorie.setBounds(574, 62, 99, 20);
		panelDetail.add(lblCategorie);
		
		panelOpdrachtType = new JPanel();
		panelOpdrachtType.setBounds(10, 221, 792, 131);
		panelDetail.add(panelOpdrachtType);
		panelOpdrachtType.setLayout(new CardLayout(0, 0));
		
		panelMeerkeuze = new JPanel();
		panelOpdrachtType.add(panelMeerkeuze, "name_293394013786776");
		panelMeerkeuze.setLayout(null);
		
		tblKeuzes = new JTable();
		tblKeuzes.setBounds(395, 11, 387, 109);
		panelMeerkeuze.add(tblKeuzes);
		
		txtKeuze = new JTextField();
		txtKeuze.setBounds(85, 11, 300, 20);
		panelMeerkeuze.add(txtKeuze);
		txtKeuze.setColumns(10);
		
		btnToevoegenKeuze = new JButton("Toevoegen");
		btnToevoegenKeuze.setBounds(10, 42, 375, 78);
		panelMeerkeuze.add(btnToevoegenKeuze);
		
		JLabel lblKeuze = new JLabel("Keuze:");
		lblKeuze.setBounds(10, 11, 63, 20);
		panelMeerkeuze.add(lblKeuze);
		
		panelOpsomming = new JPanel();
		panelOpdrachtType.add(panelOpsomming, "name_298955176939251");
		panelOpsomming.setLayout(null);
		
		panelDefault = new JPanel();
		panelOpdrachtType.add(panelDefault, "name_298982649070556");
		panelDefault.setLayout(null);
		
		tblHints = new JTable();
		tblHints.setBounds(119, 119, 449, 91);
		panelDetail.add(tblHints);
		
		txtHint = new JTextField();
		txtHint.setBounds(193, 90, 256, 20);
		panelDetail.add(txtHint);
		txtHint.setColumns(10);
		
		btnToevoegenHint = new JButton("Toevoegen");
		btnToevoegenHint.setBounds(459, 89, 109, 23);
		panelDetail.add(btnToevoegenHint);
		
		btnBewaar = new JButton("Bewaar");
		btnBewaar.setBounds(574, 89, 228, 49);
		panelDetail.add(btnBewaar);
		
		btnTerugNaarOverview = new JButton("Terug");
		btnTerugNaarOverview.setBounds(574, 161, 228, 49);
		panelDetail.add(btnTerugNaarOverview);
	}

	public void displayErrorMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	public void addToevoegenOpdrachtActionListener(ActionListener actionListener){
		btnToevoegenOpdracht.addActionListener(actionListener);
	}
	public void addWijzigenOpdrachtActionListener(ActionListener actionListener){
		btnWijzigenOpdracht.addActionListener(actionListener);
	}
	public void addVerwijderenActionListener(ActionListener actionListener){
		btnVerwijderenOpdracht.addActionListener(actionListener);
	}
	public void addTerugToMainMenuActionListener(ActionListener actionListener){
		btnTerug.addActionListener(actionListener);
	}
	public void addToevoegenKeuzeActionListener(ActionListener actionListener){
		btnToevoegenKeuze.addActionListener(actionListener);
	}
	public void addToevoegenHintActionListener(ActionListener actionListener){
		btnToevoegenHint.addActionListener(actionListener);
	}
	public void addBewaarOpdrachtActionListener(ActionListener actionListener){
		btnBewaar.addActionListener(actionListener);
	}
	public void addTerugNaarOverviewActionListener(ActionListener actionListener){
		btnTerugNaarOverview.addActionListener(actionListener);
	}
	public void addRadioButtonOpdrachtItemListener(ItemListener itemListener){
		rdbtnOpdracht.addItemListener(itemListener);
	}
	public void addRadioButtonMeerkeuzeItemListener(ItemListener itemListener){
		rdbtnMeerkeuze.addItemListener(itemListener);
	}
	public void addRadioButtonOpsommingItemListener(ItemListener itemListener){
		rdbtnOpsomming.addItemListener(itemListener);
	}
	//Getters
	public void setVraag(String vraag){
		txtVraag.setText(vraag);
	}
	public void setAntwoord(String antwoord){
		txtJuisteAntwoord.setText(antwoord);
	}
	public void setMaxPogingen(Integer pogingen){
		txtMaxPogingen.setText(pogingen.toString());
	}
	public void setMaxTijd(Integer tijd){
		txtMaxTijd.setText(tijd.toString());
	}
	public void setOpdrachtTableModel(OpdrachtTableModel model){
		tblOpdrachtOverview.setModel(model);
	}
	public void setRadioButtonDefault(){
		rdbtnOpdracht.setSelected(true);
	}
	public void setRadioButtonMeerkeuze(){
		rdbtnMeerkeuze.setSelected(true);
	}
	public void setRadioButtonOpsomming(){
		rdbtnOpsomming.setSelected(true);
	}
	//Setters
	public String getVraag(){
		return txtVraag.getText();
	}
	public String getAntwoord(){
		return txtJuisteAntwoord.getText();
	}
	public Integer getMaxPogingen(){
		return Integer.parseInt(txtMaxPogingen.getText());
	}
	public Integer getMaxTijd(){
		return Integer.parseInt(txtMaxTijd.getText());
	}
	
	//Methods
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
	public void activeerMeerkeuzePanel(){
		this.panelMeerkeuze.setVisible(true);
		this.panelOpsomming.setVisible(false);
		this.panelDefault.setVisible(false);
	}
	public void activeerOpsommingPanel(){
		this.panelMeerkeuze.setVisible(false);
		this.panelOpsomming.setVisible(true);
		this.panelDefault.setVisible(false);
	}
	public void activeerDefaultPanel(){
		this.panelMeerkeuze.setVisible(false);
		this.panelOpsomming.setVisible(false);
		this.panelDefault.setVisible(true);
	}
}
