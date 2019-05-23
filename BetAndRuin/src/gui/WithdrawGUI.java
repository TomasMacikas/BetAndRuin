package gui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;

import businessLogic.BLFacade;
import domain.Account;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;

public class WithdrawGUI extends JFrame {

	private JPanel contentPane;
	private JTextField AmountChosen;
	private JLabel logo;
	private JLabel fundsLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WithdrawGUI frame = new WithdrawGUI();
					
					frame.pack();
					frame.setLocationRelativeTo(null);
					frame.setTitle("Withdraw");
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
	public WithdrawGUI() {
		setTitle("Withdraw Betcoins");
		fundsLabel = new JLabel(MainGUI.getCurrentUser().getWalletFunds()+ " Betcoins");
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				MainGUI.getCurrentUser().getWalletFunds();
				fundsLabel.setText(Float.toString(MainGUI.getCurrentUser().getWalletFunds()));
			}
		});
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 684, 398);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	
		
		logo = new JLabel("");
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setVerticalAlignment(SwingConstants.CENTER);
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("img\\ATG.png").getImage().getScaledInstance(250, 140, Image.SCALE_SMOOTH));
		logo.setIcon(imageIcon);
		
		JLabel lblNewLabel = new JLabel("Amount");
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("Roboto", Font.PLAIN, 26));
		
		AmountChosen = new JTextField();
		AmountChosen.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		AmountChosen.setText("0");
		AmountChosen.setToolTipText("0");
		AmountChosen.setColumns(10);
		
		JLabel lblBalance = new JLabel("Balance");
		lblBalance.setFont(new Font("Roboto", Font.PLAIN, 26));
		lblBalance.setForeground(Color.ORANGE);
		
		
		fundsLabel.setForeground(Color.RED);
		fundsLabel.setFont(new Font("Roboto", Font.BOLD, 24));
		
		JLabel lblChooseAmountTo = new JLabel("Choose amount to withdraw and confirm");
		lblChooseAmountTo.setFont(new Font("Roboto", Font.BOLD, 29));
		lblChooseAmountTo.setForeground(Color.WHITE);
		
		JButton Confirm = new JButton("Confirm");
		Confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float funds = Float.parseFloat(AmountChosen.getText().toString());
				BLFacade facade = MainGUI.getBusinessLogic();
				Account b = MainGUI.getCurrentUser();
				funds = facade.withdrawFunds(b, funds);
				fundsLabel.setText(Float.toString(funds) + " Betcoins");
			}
		});
		Confirm.setFont(new Font("Roboto Lt", Font.PLAIN, 24));
		Confirm.setBackground(Color.ORANGE);
		
		JButton btnNewButton_1 = new JButton("Cancel and Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WalletGUI back = new WalletGUI();
				back.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Roboto Lt", Font.PLAIN, 16));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(119)
					.addComponent(Confirm, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
					.addComponent(btnNewButton_1)
					.addGap(119))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(16, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(AmountChosen, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addComponent(lblBalance)
					.addGap(18)
					.addComponent(fundsLabel, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
					.addGap(29))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(67)
					.addComponent(lblChooseAmountTo)
					.addContainerGap(79, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(logo, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(logo, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addComponent(lblChooseAmountTo)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(fundsLabel, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBalance)
						.addComponent(AmountChosen, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(Confirm, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
					.addGap(30))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
