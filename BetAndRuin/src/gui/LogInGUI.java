package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Account;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JPasswordField;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class LogInGUI extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInGUI frame = new LogInGUI();
					frame.pack();
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
	public LogInGUI() {
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 363);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		final JLabel lblUsername = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Username"));
		lblUsername.setForeground(new Color(255, 255, 255));
		lblUsername.setBounds(77, 97, 89, 13);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		final JLabel lblPassword = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Password"));
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setBackground(new Color(255, 255, 255));
		lblPassword.setBounds(77, 145, 100, 20);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		final JLabel label = new JLabel("");
		label.setBounds(77, 274, 0, 0);
		
		final JButton btnLogIn = new JButton("Log In\r\n");
		btnLogIn.setBackground(new Color(255, 215, 0));
		btnLogIn.setBounds(77, 210, 133, 41);
		btnLogIn.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = textField.getText();
				String password = new String(passwordField.getPassword());
				
				BLFacade facade=MainGUI.getBusinessLogic();
				
				if (username.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill in all the fields", "Error", JOptionPane.WARNING_MESSAGE);
				}
				else {
					if (facade.isValidUser(username, password)) {
						MainGUI.setCurrentUser(facade.findAccount(username));
						System.out.print(MainGUI.getCurrentUser() == null);
						MainGUI enter = new MainGUI(); /* OPENING THE MAIN GUI*/
						enter.setLocationRelativeTo(null);
						enter.setVisible(true);
						SwingUtilities.windowForComponent(contentPane).dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Invalid Login Details", "Login Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(181, 90, 177, 26);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField.setBounds(181, 142, 177, 26);
		
		final JLabel lblPleaseLogIn = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Login1"));
		lblPleaseLogIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseLogIn.setForeground(new Color(255, 255, 255));
		lblPleaseLogIn.setBounds(15, 16, 409, 49);
		lblPleaseLogIn.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		final JButton btnReturn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Return"));
		btnReturn.setBackground(new Color(255, 255, 255));
		btnReturn.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnReturn.setBounds(225, 210, 133, 41);
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HomepageGUI e = new HomepageGUI();
				e.setVisible(true);
				dispose();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(lblUsername);
		contentPane.add(lblPassword);
		contentPane.add(passwordField);
		contentPane.add(textField);
		contentPane.add(btnLogIn);
		contentPane.add(label);
		contentPane.add(btnReturn);
		contentPane.add(lblPleaseLogIn);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("English");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("en"));
				System.out.println("Locale: "+Locale.getDefault());
				lblUsername.setText(ResourceBundle.getBundle("Etiquetas").getString("Username"));
				lblPassword.setText(ResourceBundle.getBundle("Etiquetas").getString("Password"));
				btnLogIn.setText(ResourceBundle.getBundle("Etiquetas").getString("Login1"));
				btnReturn.setText(ResourceBundle.getBundle("Etiquetas").getString("Return"));
				lblPleaseLogIn.setText(ResourceBundle.getBundle("Etiquetas").getString("Login"));
			}
		});
		rdbtnNewRadioButton.setBackground(Color.BLACK);
		rdbtnNewRadioButton.setFont(new Font("Roboto", Font.PLAIN, 13));
		rdbtnNewRadioButton.setForeground(Color.ORANGE);
		rdbtnNewRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(41, 281, 109, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Castellano");
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("es"));
				System.out.println("Locale: "+Locale.getDefault());
				lblUsername.setText(ResourceBundle.getBundle("Etiquetas").getString("Username"));
				lblPassword.setText(ResourceBundle.getBundle("Etiquetas").getString("Password"));
				btnLogIn.setText(ResourceBundle.getBundle("Etiquetas").getString("Login1"));
				btnReturn.setText(ResourceBundle.getBundle("Etiquetas").getString("Return"));
				lblPleaseLogIn.setText(ResourceBundle.getBundle("Etiquetas").getString("Login"));
			}
		});
		rdbtnNewRadioButton_1.setBackground(Color.BLACK);
		rdbtnNewRadioButton_1.setFont(new Font("Roboto", Font.PLAIN, 13));
		rdbtnNewRadioButton_1.setForeground(Color.ORANGE);
		rdbtnNewRadioButton_1.setHorizontalAlignment(SwingConstants.CENTER);
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(176, 281, 109, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Euskara");
		rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("eus"));
				System.out.println("Locale: "+Locale.getDefault());
				lblUsername.setText(ResourceBundle.getBundle("Etiquetas").getString("Username"));
				lblPassword.setText(ResourceBundle.getBundle("Etiquetas").getString("Password"));
				btnLogIn.setText(ResourceBundle.getBundle("Etiquetas").getString("Login1"));
				btnReturn.setText(ResourceBundle.getBundle("Etiquetas").getString("Return"));
				lblPleaseLogIn.setText(ResourceBundle.getBundle("Etiquetas").getString("Login"));
			}
		});
		rdbtnNewRadioButton_2.setBackground(Color.BLACK);
		rdbtnNewRadioButton_2.setFont(new Font("Roboto", Font.PLAIN, 13));
		rdbtnNewRadioButton_2.setForeground(Color.ORANGE);
		rdbtnNewRadioButton_2.setHorizontalAlignment(SwingConstants.CENTER);
		buttonGroup.add(rdbtnNewRadioButton_2);
		rdbtnNewRadioButton_2.setBounds(315, 281, 109, 23);
		contentPane.add(rdbtnNewRadioButton_2);
	}
}
