package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;


import domain.Account;
import domain.Event;
import businessLogic.BLFacade;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.GroupLayout.Alignment;

import com.objectdb.o.BTS;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;
import javax.swing.LayoutStyle.ComponentPlacement;


public class MainGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonCreateQuery = null;
	private JButton jButtonCreateEvent = null;
	private JButton jButtonQueryQueries = null;

    private static BLFacade appFacadeInterface;
    static Account currentUser = null;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	
	public static Account getCurrentUser() {
		try {
		updateCurrentUser(currentUser.getUsername());
		} catch (NullPointerException npe) {
			System.out.print("Current user is now set to NULL");
		}
		return currentUser;
	}
	public static void updateCurrentUser(String username) {
		BLFacade facade = getBusinessLogic();
		currentUser = facade.findAccount(username);
	}

	public static void setCurrentUser(Account curr) {
		currentUser = curr;
	}
	public static void deleteCurrentUser() {
			currentUser = null;

	}
	

	protected JLabel jLabelSelectOption;
	protected JLabel Icon;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton lblAccountSettings;
	private JButton btnBetHistorywButton;
	private JButton transactionsButton;
	private JSeparator separator_1;
	private JButton setResultButton;
	
	
	/**
	 * This is the default constructor
	 */
	public MainGUI() {
		super();
		setResizable(false);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(821, 527);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
		System.out.print(MainGUI.getCurrentUser().getUsername());
		
		if (MainGUI.getCurrentUser().getIsAdmin()) {
			System.out.print("Logged user is admin");
		} else {
			System.out.print("Logged user is NOT admin");
			setResultButton.setVisible(false);
			jButtonCreateQuery.setVisible(false);
			jButtonCreateEvent.setVisible(false);
		}
			
		
	}
	
	private void initialize(int width, int length) {
		this.setSize(width, length);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
		this.setVisible(true);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setBackground(Color.BLACK);
			
			JSeparator separator = new JSeparator();
			GroupLayout gl_jContentPane = new GroupLayout(jContentPane);
			gl_jContentPane.setHorizontalGroup(
				gl_jContentPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_jContentPane.createSequentialGroup()
						.addContainerGap(642, Short.MAX_VALUE)
						.addComponent(getPanel(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(52))
					.addGroup(gl_jContentPane.createSequentialGroup()
						.addGap(69)
						.addComponent(getTransactionButton(), GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
						.addGap(92))
					.addGroup(Alignment.LEADING, gl_jContentPane.createSequentialGroup()
						.addGap(10)
						.addGroup(gl_jContentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(getEventButton(), GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
							.addComponent(getBoton2(), GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE))
						.addGap(10)
						.addGroup(gl_jContentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(getBoton3(), GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
							.addComponent(getBtnBetHistorywButton(), GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE))
						.addGap(10))
					.addComponent(separator, GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
					.addComponent(getSeparator_1(), GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
					.addGroup(gl_jContentPane.createSequentialGroup()
						.addGap(63)
						.addComponent(getLblNewLabel(), GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
						.addGap(57))
					.addGroup(gl_jContentPane.createSequentialGroup()
						.addGap(255)
						.addComponent(getSetResultButton(), GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
						.addGap(247))
					.addGroup(gl_jContentPane.createSequentialGroup()
						.addGroup(gl_jContentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_jContentPane.createSequentialGroup()
								.addGap(241)
								.addComponent(getRdbtnNewRadioButton(), GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
								.addGap(64)
								.addComponent(getRdbtnNewRadioButton_2(), GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
								.addGap(65)
								.addComponent(getRdbtnNewRadioButton_1(), GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(gl_jContentPane.createSequentialGroup()
								.addGap(197)
								.addComponent(getLblAccountSettings(), GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)))
						.addGap(201))
			);
			gl_jContentPane.setVerticalGroup(
				gl_jContentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_jContentPane.createSequentialGroup()
						.addGap(32)
						.addComponent(getLblNewLabel(), GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addGroup(gl_jContentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(getEventButton(), GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addComponent(getBoton3(), GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_jContentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(getBtnBetHistorywButton(), GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addComponent(getBoton2(), GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_jContentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(getPanel(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(getSetResultButton(), GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(getTransactionButton(), GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(getSeparator_1(), GroupLayout.PREFERRED_SIZE, 9, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(getLblAccountSettings(), GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addGroup(gl_jContentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(getRdbtnNewRadioButton_2())
							.addComponent(getRdbtnNewRadioButton())
							.addComponent(getRdbtnNewRadioButton_1()))
						.addGap(26))
			);
			jContentPane.setLayout(gl_jContentPane);
			
			 
		}
		return jContentPane;
	}



	
		
	

	private Component getTransactionButton() {
		if (transactionsButton == null) {
			transactionsButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Transactions"));
			transactionsButton.setBackground(new Color(152, 251, 152));
			ImageIcon imageIcon = new ImageIcon(new ImageIcon("img\\betting_3.jpg").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
			transactionsButton.setIcon(imageIcon);
			transactionsButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFrame transactionsGUI = new TransactionsGUI();
					transactionsGUI.setLocationRelativeTo(null);
					transactionsGUI.setVisible(true);
				
				}
			});
		}
		return transactionsButton;
	}

	private JButton getEventButton() {
		if (jButtonCreateEvent == null) {
			jButtonCreateEvent = new JButton();
			jButtonCreateEvent.setBackground(new Color(240, 230, 140));
			jButtonCreateEvent.setForeground(Color.BLACK);
			jButtonCreateEvent.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateOrDeleteEvent"));
			ImageIcon imageIcon = new ImageIcon(new ImageIcon("img\\type.png").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
			jButtonCreateEvent.setIcon(imageIcon);
			jButtonCreateEvent.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					BLFacade facade=MainGUI.getBusinessLogic();
					//Vector<Event> events=facade.getAllEvents();
					JFrame CreateEventGUI = new CreateEventGUI(new Vector<Event>());
					CreateEventGUI.setLocationRelativeTo(null);
					CreateEventGUI.setVisible(true);
				}
			});
		}
		return jButtonCreateEvent;
	}

	/**
	 * This method initializes boton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton2() {
		if (jButtonCreateQuery == null) {
			jButtonCreateQuery = new JButton();
			jButtonCreateQuery.setBackground(new Color(240, 230, 140));
			jButtonCreateQuery.setForeground(Color.BLACK);
			jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
			ImageIcon imageIcon = new ImageIcon(new ImageIcon("img\\question.png").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
			jButtonCreateQuery.setIcon(imageIcon);
			jButtonCreateQuery.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					BLFacade facade=MainGUI.getBusinessLogic();
					//Vector<Event> events=facade.getAllEvents();
					JFrame CreateQuestionGUI = new CreateQuestionGUI(new Vector<Event>());
					CreateQuestionGUI.setLocationRelativeTo(null);
					CreateQuestionGUI.setVisible(true);
				}
			});
		}
		return jButtonCreateQuery;
	}
	
	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (jButtonQueryQueries == null) {
			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.setBackground(new Color(240, 230, 140));
			jButtonQueryQueries.setForeground(Color.BLACK);
			jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
			ImageIcon imageIcon = new ImageIcon(new ImageIcon("img\\download.png").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
			jButtonQueryQueries.setIcon(imageIcon);
			jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame FindQuestionsGUI = new FindQuestionsGUI();
					FindQuestionsGUI.setLocationRelativeTo(null);
					FindQuestionsGUI.setVisible(true);
				}
			});
		}
		return jButtonQueryQueries;
	}
	

	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 16));
			jLabelSelectOption.setForeground(Color.ORANGE);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}
	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("English");
			rdbtnNewRadioButton.setBackground(new Color(0, 0, 0));
			rdbtnNewRadioButton.setForeground(Color.ORANGE);
			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: "+Locale.getDefault());
					dispose();
					initialize(821, 527);
					redibujar();						}
			});
			buttonGroup.add(rdbtnNewRadioButton);
		}
		return rdbtnNewRadioButton;
	}
	private JRadioButton getRdbtnNewRadioButton_1() {
		if (rdbtnNewRadioButton_1 == null) {
			rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
			rdbtnNewRadioButton_1.setBackground(new Color(0, 0, 0));
			rdbtnNewRadioButton_1.setForeground(Color.ORANGE);
			rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: "+Locale.getDefault());
					dispose();
					initialize(800, 536);
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton_1);
		}
		return rdbtnNewRadioButton_1;
	}
	private JRadioButton getRdbtnNewRadioButton_2() {
		if (rdbtnNewRadioButton_2 == null) {
			rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
			rdbtnNewRadioButton_2.setBackground(new Color(0, 0, 0));
			rdbtnNewRadioButton_2.setForeground(Color.ORANGE);
			rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: "+Locale.getDefault());
					dispose();
					initialize(821, 527);
					redibujar();		
				}
			});
			buttonGroup.add(rdbtnNewRadioButton_2);
		}
		return rdbtnNewRadioButton_2;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.GRAY);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		}
		return panel;
	}
	
	private void redibujar() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
		lblAccountSettings.setText(ResourceBundle.getBundle("Etiquetas").getString("Account"));
		jButtonCreateEvent.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateOrDeleteEvent"));
		btnBetHistorywButton.setText(ResourceBundle.getBundle("Etiquetas").getString("BettingHistory"));
		setResultButton.setText(ResourceBundle.getBundle("Etiquetas").getString("SetEventResult"));
		transactionsButton.setText(ResourceBundle.getBundle("Etiquetas").getString("Transactions"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}
	
	//If user is logged, show account settings button, if guest show login button
	
	private JButton getLblAccountSettings() {	
		
		if (lblAccountSettings == null) {
			lblAccountSettings = new JButton("Account Settings");
			lblAccountSettings.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					EditAccountGUI EditAccountGUI = new EditAccountGUI();
					EditAccountGUI.setLocationRelativeTo(null);
					EditAccountGUI.setVisible(true);
					dispose();
				}
			
			});
			
	
			
		
	
		
		/*if (facade.getCurrentUser() == null) {
			
			if (lblAccountSettings == null) {
				lblAccountSettings = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Account"));
				lblAccountSettings.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						EditAccountGUI EditAccountGUI = new EditAccountGUI();
						EditAccountGUI.setLocationRelativeTo(null);
						EditAccountGUI.setVisible(true);
						dispose();
					}
				});
		} else {
			System.out.print(facade.getCurrentUser());
		}*/

			lblAccountSettings.setBackground(new Color(0, 255, 255));
			lblAccountSettings.setFont(new Font("Dialog", Font.BOLD, 12));
			lblAccountSettings.setForeground(Color.BLACK);
			ImageIcon imageIcon = new ImageIcon(new ImageIcon("img\\user-3.png").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
			lblAccountSettings.setIcon(imageIcon);//$NON-NLS-1$ //$NON-NLS-2$
		}
		return lblAccountSettings;
	}

		// TODO Auto-generated method stub
		
	private JButton getBtnBetHistorywButton() {
		if (btnBetHistorywButton == null) {
			btnBetHistorywButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BettingHistory"));
			btnBetHistorywButton.setBackground(new Color(240, 230, 140));
			ImageIcon imageIcon = new ImageIcon(new ImageIcon("img\\betting_3.jpg").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
			btnBetHistorywButton.setIcon(imageIcon);
			btnBetHistorywButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFrame bettingHistoryGUI = new BettingHistoryGUI();
					bettingHistoryGUI.setLocationRelativeTo(null);
					bettingHistoryGUI.setVisible(true);
					//dispose();
				
				}
			});
		}
		return btnBetHistorywButton;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}

	private JButton getSetResultButton() {

			if (setResultButton == null) {
				setResultButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SetEventResult")); //$NON-NLS-1$ //$NON-NLS-2$
				setResultButton.setBackground(new Color(240, 230, 140));
				ImageIcon imageIcon = new ImageIcon(new ImageIcon("img\\result.jpg").getImage().getScaledInstance(85, 65, Image.SCALE_SMOOTH));
				getSetResultButton().setIcon(imageIcon);
				setResultButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						JFrame setResultGUI	= new SetResultGUI();
						setResultGUI.setLocationRelativeTo(null);
						setResultGUI.setVisible(true);
					}
				});
		}
		return setResultButton;
	}
}
// @jve:decl-index=0:visual-constraint="0,0"

