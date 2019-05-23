package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

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


public class MainGuestGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonQueryQueries = null;

    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	protected JLabel jLabelSelectOption;
	protected JLabel Icon;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton lblLogin;
	private JButton BackButton;
	
	/**
	 * This is the default constructor
	 */
	public MainGuestGUI() {
		super();
		
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
		this.setSize(495, 290);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
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
			jContentPane.setLayout(new GridLayout(6, 1, 5, 5));
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getBoton3());
			jContentPane.add(getLblLogin());
			jContentPane.add(getBackButton());
			jContentPane.add(getPanel());
			
			 
		}
		return jContentPane;
	}

	
	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (jButtonQueryQueries == null) {
			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.setFont(new Font("Roboto", Font.BOLD, 15));
			jButtonQueryQueries.setBackground(Color.DARK_GRAY);
			jButtonQueryQueries.setForeground(Color.ORANGE);
			ImageIcon imageIcon = new ImageIcon(new ImageIcon("img\\search.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
			jButtonQueryQueries.setIcon(imageIcon);//$NON-NLS-1$ //$NON-NLS-2$
			jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
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
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelSelectOption.setForeground(Color.ORANGE);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}
	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("English");
			rdbtnNewRadioButton.setBackground(Color.DARK_GRAY);
			rdbtnNewRadioButton.setForeground(Color.ORANGE);
			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton);
		}
		return rdbtnNewRadioButton;
	}
	private JRadioButton getRdbtnNewRadioButton_1() {
		if (rdbtnNewRadioButton_1 == null) {
			rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
			rdbtnNewRadioButton_1.setBackground(Color.DARK_GRAY);
			rdbtnNewRadioButton_1.setForeground(Color.ORANGE);
			rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton_1);
		}
		return rdbtnNewRadioButton_1;
	}
	private JRadioButton getRdbtnNewRadioButton_2() {
		if (rdbtnNewRadioButton_2 == null) {
			rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
			rdbtnNewRadioButton_2.setBackground(Color.DARK_GRAY);
			rdbtnNewRadioButton_2.setForeground(Color.ORANGE);
			rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: "+Locale.getDefault());
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
			panel.setBackground(Color.DARK_GRAY);
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setVgap(15);
			flowLayout.setHgap(15);
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
		}
		return panel;
	}
	
	private void redibujar() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		lblLogin.setText(ResourceBundle.getBundle("Etiquetas").getString("Account"));
		BackButton.setText(ResourceBundle.getBundle("Etiquetas").getString("Return"));
		
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}
	
	
	//If user is logged, show account settings button, if guest show login button
	
	private JButton getLblLogin() {
		//IF in facade, theres an user logged, display the label as "Account Settings"
		//and when the user clics redirect to editaccount gui
		//ELSE is in facade theres no user, display label as Login, and when user clics
		//redirect to login page
		
		if (lblLogin == null) {
			lblLogin = new JButton("Log In");
			lblLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					LogInGUI log = new LogInGUI();
					log.setLocationRelativeTo(null); //Centers window
					log.setVisible(true);
					dispose();
				}
			});

			lblLogin.setBackground(Color.DARK_GRAY);
			lblLogin.setFont(new Font("Roboto", Font.BOLD, 15));
			lblLogin.setForeground(Color.ORANGE);
			lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
			ImageIcon imageIcon = new ImageIcon(new ImageIcon("img\\user-3.png").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
			lblLogin.setIcon(imageIcon);//$NON-NLS-1$ //$NON-NLS-2$
		}
		return lblLogin;
	}
	private JButton getBackButton() {
		if (BackButton == null) {
			BackButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Return")); //$NON-NLS-1$ //$NON-NLS-2$
			BackButton.setForeground(Color.ORANGE);
			BackButton.setBackground(Color.DARK_GRAY);
			ImageIcon imageIcon = new ImageIcon(new ImageIcon("img\\return.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
			BackButton.setIcon(imageIcon);
			BackButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					HomepageGUI back = new HomepageGUI();
					back.setVisible(true);
					dispose();
				}
			});
			BackButton.setFont(new Font("Roboto", Font.BOLD, 15));
		}
		return BackButton;
	}
} // @jve:decl-index=0:visual-constraint="0,0"

