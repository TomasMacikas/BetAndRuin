package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Account;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JRadioButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.SystemColor;

public class EditAccountGUI extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> GenderDB;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditAccountGUI frame = new EditAccountGUI();
					//frame.setTitle("My Information");
					frame.setVisible(true);
					

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	/**
	 * Create the frame.
	 */
	public EditAccountGUI() {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("EditAccountGUITitle")); //$NON-NLS-1$ //$NON-NLS-2$
		final JLabel CreditValue = new JLabel(MainGUI.getCurrentUser().getWalletFunds() + " Betcoins");
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				MainGUI.getCurrentUser().getWalletFunds();
				CreditValue.setText(Float.toString(MainGUI.getCurrentUser().getWalletFunds()));
			}
		});
		
		
		
		GenderDB = new JComboBox<String>();
		GenderDB.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				MainGUI.getCurrentUser().getGender();	
			}
		});
		/*GenderDB.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				GenderDB.getSelectedItem();
			}
		});*/

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 698, 647);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		

		final JLabel Genderlbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Gender"));
		Genderlbl.setFont(new Font("Roboto", Font.PLAIN, 15));
		Genderlbl.setForeground(Color.ORANGE);

		
		
		//GenderDBaddItem(facade.getCurrentUser().getGender());
		if (MainGUI.getCurrentUser().getGender().toString().toLowerCase() != "female") {
			GenderDB.addItem("male");
			GenderDB.addItem("female");
		}
		else if (MainGUI.getCurrentUser().getGender().toString().toLowerCase() != "male") {
			GenderDB.addItem("female");
			GenderDB.addItem("male");
		}

		final JLabel WarningLabel = new JLabel("");
		WarningLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		WarningLabel.setForeground(Color.RED);

		final JLabel titleNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EditAccountTitle"));
		titleNewLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		titleNewLabel.setForeground(Color.ORANGE);

		final JLabel Usernamelbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Username"));
		Usernamelbl.setForeground(Color.ORANGE);
		Usernamelbl.setFont(new Font("Roboto", Font.PLAIN, 15));

		final JTextField Usernamefield = new JTextField(MainGUI.getCurrentUser().getUsername());
		Usernamefield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Component frame = null;
				JOptionPane.showMessageDialog(frame,
					    "Not editable because it is a primary key");
			}
		});
		
		Usernamefield.setEditable(false);
		Usernamefield.setFont(new Font("Roboto", Font.PLAIN, 12));

		final JLabel Passwordlbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Password"));
		Passwordlbl.setForeground(Color.ORANGE);
		Passwordlbl.setFont(new Font("Roboto", Font.PLAIN, 15));

		
		final JTextField PasswordDB = new JTextField(MainGUI.getCurrentUser().getPassword());
		PasswordDB.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				
				if (PasswordDB.getText().length() < 4) 
					WarningLabel.setText("Password should be at least 5 characters long");
				else
					WarningLabel.setText("");
			}
		});

		final JLabel Birthdatelbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Birthdate"));
		Birthdatelbl.setForeground(Color.ORANGE);
		Birthdatelbl.setFont(new Font("Roboto", Font.PLAIN, 15));

		JTextField BirthdateDB = new JTextField(MainGUI.getCurrentUser().getBirthday());
		BirthdateDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		final JLabel EmailAddresslbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EmailAddress"));
		EmailAddresslbl.setForeground(Color.ORANGE);
		EmailAddresslbl.setFont(new Font("Roboto", Font.PLAIN, 15));

		final JTextField EmailAddressDB = new JTextField(MainGUI.getCurrentUser().getEmail());
		EmailAddressDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		final JLabel Firstnamelbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Firstname"));
		Firstnamelbl.setForeground(Color.ORANGE);
		Firstnamelbl.setFont(new Font("Roboto", Font.PLAIN, 15));

		final JTextField FirstnameDB = new JTextField(MainGUI.getCurrentUser().getFirstname());
		FirstnameDB.addFocusListener(new FocusAdapter() {
			
		});

		final JLabel Lastnamelbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Lastname"));
		Lastnamelbl.setForeground(Color.ORANGE);
		Lastnamelbl.setFont(new Font("Roboto", Font.PLAIN, 15));

		final JTextField LastnameDB = new JTextField(MainGUI.getCurrentUser().getLastname());
		LastnameDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String lname = LastnameDB.getText();
			}
		});

		final JLabel CreditLbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreditAvailable"));
		CreditLbl.setFont(new Font("Roboto", Font.PLAIN, 15));
		CreditLbl.setForeground(Color.ORANGE);

		
		

	
		CreditValue.setFont(new Font("SansSerif", Font.PLAIN, 18));
		CreditValue.setForeground(Color.WHITE);

		final JButton FinishButton = new JButton(" Update and Finish");
		FinishButton.setForeground(new Color(0, 0, 0));
		FinishButton.setBackground(Color.ORANGE);
		FinishButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				BLFacade facade = MainGUI.getBusinessLogic();
				Account a = MainGUI.getCurrentUser();
				/*if (Usernamefield.getText() != facade.getCurrentUser().getUsername()) {
					facade.UpdateUsername(Usernamefield.getText(), a.getUsername());              THIS IS JUST IN CASE BUT NOT USED BECAUSE USERNAME IS PRIMARY KEY AND CANNOT BE OVERWRITTEN
				}
				*/
				if (FirstnameDB.getText() != MainGUI.getCurrentUser().getFirstname()) {
					 facade.UpdateFirstname(FirstnameDB.getText(), a.getUsername());
				 }
				
				 if (LastnameDB.getText() != MainGUI.getCurrentUser().getLastname()) {
					facade.UpdateLastname(LastnameDB.getText(), a.getUsername());
				 }
				
				if (PasswordDB.getText() != MainGUI.getCurrentUser().getPassword()) {
					facade.UpdatePassword(PasswordDB.getText(), a.getUsername());
				}
				
				 if (EmailAddressDB.getText() != MainGUI.getCurrentUser().getEmail()) {
					facade.UpdateEmailAddress(EmailAddressDB.getText(), a.getUsername());
				 }
					
				 if (GenderDB.getSelectedItem() != MainGUI.getCurrentUser().getGender().toString())
					facade.UpdateGender(GenderDB.getSelectedItem().toString(), a.getUsername());
				 	

				System.out.println("Information Updated");
				WarningLabel.setForeground(Color.GREEN);
				WarningLabel.setText("Changed Saved");
				MainGUI.getCurrentUser().getGender();
				GenderDB.removeAllItems();
				GenderDB.addItem(MainGUI.getCurrentUser().getGender());
				MainGUI back = new MainGUI(); //
				back.setVisible(true);
				back.setLocationRelativeTo(null);
				dispose();
				
			}
			
		});

		final JButton btnGetMoreCoins = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Getmorecoins"));
		btnGetMoreCoins.setBackground(new Color(0, 255, 0));
		btnGetMoreCoins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WalletGUI go = new WalletGUI();
				go.setLocationRelativeTo(null);
				go.setVisible(true);
				dispose();
			}
		});
		
		final JButton btnLogOut = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Logout"));
		btnLogOut.setBackground(Color.WHITE);
		btnLogOut.setForeground(Color.BLACK);
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGUI.deleteCurrentUser();	
				System.out.print("After logout current user is: " + MainGUI.getCurrentUser());
				HomepageGUI home = new HomepageGUI();
				home.setVisible(true);
				dispose();
			}
		});
		
		JButton btnBack = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Return")); //$NON-NLS-1$ //$NON-NLS-2$
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainGUI back = new MainGUI();
				back.setLocationRelativeTo(null);
				back.setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.setBackground(Color.ORANGE);
		
		
		
		JRadioButton englishButton = new JRadioButton("English");
		englishButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Locale.setDefault(new Locale("en"));
				System.out.println("Locale: "+Locale.getDefault());
				Usernamelbl.setText(ResourceBundle.getBundle("Etiquetas").getString("Username"));
				Firstnamelbl.setText(ResourceBundle.getBundle("Etiquetas").getString("Firstname"));
				EmailAddresslbl.setText(ResourceBundle.getBundle("Etiquetas").getString("EmailAddress"));
				Genderlbl.setText(ResourceBundle.getBundle("Etiquetas").getString("Gender"));
				FinishButton.setText(ResourceBundle.getBundle("Etiquetas").getString("UpdateAndFinish"));
				CreditLbl.setText(ResourceBundle.getBundle("Etiquetas").getString("CreditAvailable"));
				titleNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("EditAccountTitle"));
				Passwordlbl.setText(ResourceBundle.getBundle("Etiquetas").getString("Password"));
				btnLogOut.setText(ResourceBundle.getBundle("Etiquetas").getString("Logout"));
				Lastnamelbl.setText(ResourceBundle.getBundle("Etiquetas").getString("Lastname"));
				Birthdatelbl.setText(ResourceBundle.getBundle("Etiquetas").getString("Birthdate"));
				btnBack.setText(ResourceBundle.getBundle("Etiquetas").getString("Return"));
				btnGetMoreCoins.setText(ResourceBundle.getBundle("Etiquetas").getString("Getmorecoins"));
				
			}
		});
		buttonGroup.add(englishButton);
		englishButton.setForeground(Color.ORANGE);
		englishButton.setBackground(Color.BLACK);
		
	
		
		
		
		JRadioButton EuskaraButton = new JRadioButton("Euskara");
		EuskaraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("eus"));
				System.out.println("Locale: "+Locale.getDefault());
				
				Usernamelbl.setText(ResourceBundle.getBundle("Etiquetas").getString("Username"));
				Firstnamelbl.setText(ResourceBundle.getBundle("Etiquetas").getString("Firstname"));
				EmailAddresslbl.setText(ResourceBundle.getBundle("Etiquetas").getString("EmailAddress"));
				Genderlbl.setText(ResourceBundle.getBundle("Etiquetas").getString("Gender"));
				FinishButton.setText(ResourceBundle.getBundle("Etiquetas").getString("UpdateAndFinish"));
				CreditLbl.setText(ResourceBundle.getBundle("Etiquetas").getString("CreditAvailable"));
				Passwordlbl.setText(ResourceBundle.getBundle("Etiquetas").getString("Password"));
				titleNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("EditAccountTitle"));
				Birthdatelbl.setText(ResourceBundle.getBundle("Etiquetas").getString("Birthdate"));
				Lastnamelbl.setText(ResourceBundle.getBundle("Etiquetas").getString("Lastname"));
				btnLogOut.setText(ResourceBundle.getBundle("Etiquetas").getString("Logout"));
				btnGetMoreCoins.setText(ResourceBundle.getBundle("Etiquetas").getString("Getmorecoins"));
				btnBack.setText(ResourceBundle.getBundle("Etiquetas").getString("Return"));
				
			}
		});
		buttonGroup.add(EuskaraButton);
		EuskaraButton.setBackground(Color.BLACK);
		EuskaraButton.setForeground(Color.ORANGE);
		
		final JRadioButton EspanolButton = new JRadioButton("Castellano");
		EspanolButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("es"));
				System.out.println("Locale: "+Locale.getDefault());
				Usernamelbl.setText(ResourceBundle.getBundle("Etiquetas").getString("Username"));
				Firstnamelbl.setText(ResourceBundle.getBundle("Etiquetas").getString("Firstname"));
				EmailAddresslbl.setText(ResourceBundle.getBundle("Etiquetas").getString("EmailAddress"));
				Genderlbl.setText(ResourceBundle.getBundle("Etiquetas").getString("Gender"));
				FinishButton.setText(ResourceBundle.getBundle("Etiquetas").getString("UpdateAndFinish"));
				CreditLbl.setText(ResourceBundle.getBundle("Etiquetas").getString("CreditAvailable"));
				titleNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("EditAccountTitle"));
				Passwordlbl.setText(ResourceBundle.getBundle("Etiquetas").getString("Password"));
				btnGetMoreCoins.setText(ResourceBundle.getBundle("Etiquetas").getString("Getmorecoins"));
				btnLogOut.setText(ResourceBundle.getBundle("Etiquetas").getString("Logout"));
				Birthdatelbl.setText(ResourceBundle.getBundle("Etiquetas").getString("Birthdate"));
				Lastnamelbl.setText(ResourceBundle.getBundle("Etiquetas").getString("Lastname"));
				btnBack.setText(ResourceBundle.getBundle("Etiquetas").getString("Return"));
				
			}
		});
		buttonGroup.add(EspanolButton);
		EspanolButton.setBackground(Color.BLACK);
		EspanolButton.setForeground(Color.ORANGE);
		
		
		
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(45)
							.addComponent(titleNewLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(53)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(Birthdatelbl)
										.addComponent(EmailAddresslbl)
										.addComponent(Usernamelbl)
										.addComponent(Genderlbl)
										.addComponent(Lastnamelbl)
										.addComponent(CreditLbl)
										.addComponent(Firstnamelbl)
										.addComponent(Passwordlbl))
									.addPreferredGap(ComponentPlacement.RELATED))
								.addComponent(englishButton)
								.addComponent(FinishButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(44)
									.addComponent(btnGetMoreCoins, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(10)
											.addComponent(CreditValue, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(26)
											.addComponent(btnLogOut, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
											.addGap(27)
											.addComponent(btnBack)
											.addGap(16))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(45)
									.addComponent(EuskaraButton)
									.addGap(18)
									.addComponent(EspanolButton)
									.addGap(18)
									.addComponent(WarningLabel, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(69)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(PasswordDB, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE)
										.addComponent(Usernamefield, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE)
										.addComponent(BirthdateDB, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE)
										.addComponent(EmailAddressDB, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE)
										.addComponent(FirstnameDB, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE)
										.addComponent(LastnameDB, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE)
										.addComponent(GenderDB, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE))))))
					.addGap(88))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(titleNewLabel)
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Usernamelbl)
						.addComponent(Usernamefield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Passwordlbl)
						.addComponent(PasswordDB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Birthdatelbl)
						.addComponent(BirthdateDB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(EmailAddresslbl)
						.addComponent(EmailAddressDB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Firstnamelbl)
						.addComponent(FirstnameDB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Lastnamelbl)
						.addComponent(LastnameDB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Genderlbl)
						.addComponent(GenderDB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(CreditValue)
						.addComponent(CreditLbl))
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(FinishButton)
						.addComponent(btnGetMoreCoins)
						.addComponent(btnLogOut)
						.addComponent(btnBack))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(30)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(EuskaraButton)
								.addComponent(EspanolButton)
								.addComponent(englishButton)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(WarningLabel, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
		

	}
}
