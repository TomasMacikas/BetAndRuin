package gui;



import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JFrame;

/* MenuDemo.java requires images/middle.gif. */

/*
 * This class is just like MenuLookDemo, except the menu items
 * actually do something, thanks to event listeners.
 */
public class TransactionsGUI extends JFrame {
    JTextArea output;
    static JScrollPane scrollPaneBets = new JScrollPane();
    private static JTable tableBets= new JTable();
	private static DefaultTableModel tableModelBets;
	private static final JButton backbutton = new JButton("Back");
	
	private static String[] columnNamesBets = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("Amount"), 
			ResourceBundle.getBundle("Etiquetas").getString("Date"),
			ResourceBundle.getBundle("Etiquetas").getString("Description"), 

	};
    
    public TransactionsGUI() throws HeadlessException {
		super();
		setTitle("History of Transactions");
		
		//Create the content-pane-to-be.
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);

        //Create a scrolled text area.
        output = new JTextArea(5, 30);
        output.setEditable(false);
        scrollPaneBets = new JScrollPane(output);

        //Add the text area to the content pane.
        contentPane.add(scrollPaneBets, BorderLayout.CENTER);
        
      //Create and set up the window.
        //JFrame frame = new JFrame("My Betting History");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setForeground(Color.ORANGE);
        setBackground(Color.BLACK);

        setContentPane(contentPane);
        setIconImage(new ImageIcon("/img/betting_3.jpg").getImage());
        
        
        //Getting Data
        scrollPaneBets.setViewportView(tableBets);
		tableModelBets = new DefaultTableModel(null, columnNamesBets);

		tableBets.setModel(tableModelBets);
		tableBets.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableBets.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableBets.getColumnModel().getColumn(2).setPreferredWidth(100);
		//tableBets.getColumnModel().getColumn(3).setPreferredWidth(100);
        
        tableModelBets.setDataVector(null, columnNamesBets);
		tableModelBets.setColumnCount(3); // another column added to allocate ev objects

		BLFacade facade=MainGUI.getBusinessLogic();
		Vector<domain.Transaction> transactions=facade.getTransactions(MainGUI.getCurrentUser());
		
		for (domain.Transaction trans:transactions){
			Vector<Object> row = new Vector<Object>();

			row.add(trans.getAmount());
			final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			row.add(sdf.format(trans.getDate()));
			row.add(trans.getDescription());
			tableModelBets.addRow(row);		
		}
		tableBets.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableBets.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableBets.getColumnModel().getColumn(2).setPreferredWidth(200);
        setSize(625, 300);
        setVisible(true);
        
        backbutton.setVerticalTextPosition(AbstractButton.BOTTOM);
        backbutton.setHorizontalTextPosition(AbstractButton.CENTER);
        contentPane.add(backbutton, BorderLayout.CENTER);
        
	}
}
