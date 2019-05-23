package gui;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Question;
import exceptions.EventFinished;
import exceptions.InsuficientFunds;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class BetGUI extends JFrame {

	private JPanel contentPane;
	private JTextField BetAmountField;

	public BetGUI(final Question question) {
		setTitle("Make your bet!");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// Getting info from given question

		JLabel questionLabel = new JLabel(" Question:  " + question.getQuestion());
		questionLabel.setForeground(Color.ORANGE);

		JLabel lblEnterYourBet = new JLabel("Enter your bet:");
		lblEnterYourBet.setForeground(Color.ORANGE);

		JLabel lblCalculatedWin = new JLabel(
				"If you win, you'll get " + String.format("%.02f", question.getOdds()) + " times of what you bet!");
		lblCalculatedWin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCalculatedWin.setForeground(Color.ORANGE);

		BetAmountField = new JTextField();
		BetAmountField.setColumns(10);

		class MyDocumentListener implements DocumentListener {
			// String newline = "\n";

			public void insertUpdate(DocumentEvent e) {
				updateLog(e, "inserted into");
			}

			public void removeUpdate(DocumentEvent e) {
				updateLog(e, "removed from");
			}

			public void changedUpdate(DocumentEvent e) {
				// Plain text components do not fire these events
			}

			public void updateLog(DocumentEvent e, String action) {

				try {
					lblCalculatedWin.setText("If you win you'll get "
							+ String.format("%.2f", Float.parseFloat(BetAmountField.getText()) * question.getOdds())
							+ " Betcoins!");
				} catch (NumberFormatException nfe) {
					lblCalculatedWin.setText("Please enter a valid bet amount");
				}

			}

		}

		// BetAmountField.addActionListener(new MyTextActionListener());
		BetAmountField.getDocument().addDocumentListener(new MyDocumentListener());
		BetAmountField.getDocument().putProperty("name", "Text Field");

		JLabel minBetLabel = new JLabel("Minimum Bet: " + question.getBetMinimum());
		minBetLabel.setForeground(Color.ORANGE);
		final JLabel warningLabel = new JLabel("");
		warningLabel.setFont(new Font("Roboto Cn", Font.BOLD, 15));
		warningLabel.setHorizontalAlignment(SwingConstants.CENTER);
		warningLabel.setForeground(Color.WHITE);

		JButton betButton = new JButton("Place Bet");
		betButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!BetAmountField.getText().isEmpty()) {
					if (Float.parseFloat(BetAmountField.getText()) < question.getBetMinimum()) {
						warningLabel.setText("Your bet has to be bigger");
					} else {
						BLFacade facade = MainGUI.getBusinessLogic();

						try {
							facade.placeBet(MainGUI.getCurrentUser(), Float.parseFloat(BetAmountField.getText()), question);
							// JLabel lblCalculatedWin = new JLabel("If you win, you'll get " +
							// String.format("%.02f", betAmount) + " of what you bet!");
							// Bet amount
							warningLabel.setText("Bet placed");
						} catch (InsuficientFunds e1) {
							warningLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("InsuficientFunds"));
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							System.out.println("This is not a number");
							System.out.println(e1.getMessage());
							lblCalculatedWin.setText("You didn't entered a valid number!");
						} catch (EventFinished e1) {
							warningLabel
									.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
							e1.printStackTrace();
						}
					}
				} else {
					warningLabel.setText("Enter your bet");
				}
			}
		});

		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		closeButton.setBounds(new Rectangle(138, 420, 130, 30));

		JLabel Symbol = new JLabel("");
		ImageIcon imageIcon = new ImageIcon(
				new ImageIcon("img\\putmoney.png").getImage().getScaledInstance(150, 75, Image.SCALE_DEFAULT));
		Symbol.setIcon(imageIcon);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(29)
							.addComponent(Symbol))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblEnterYourBet, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(67)
							.addComponent(BetAmountField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(minBetLabel)
					.addGap(95))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(225)
					.addComponent(questionLabel, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
					.addGap(34))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(91)
					.addComponent(warningLabel, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(239, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCalculatedWin)
					.addContainerGap(254, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(50)
					.addComponent(closeButton, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addGap(37)
					.addComponent(betButton, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(186, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(14)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(Symbol)
						.addComponent(questionLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterYourBet, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(BetAmountField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(minBetLabel))
					.addGap(16)
					.addComponent(lblCalculatedWin)
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(closeButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(betButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(warningLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(49, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
