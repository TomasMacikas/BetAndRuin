package gui;

import java.awt.*;
import javax.swing.table.TableCellRenderer;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.Vector;
import javax.swing.UIManager;
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
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

/* MenuDemo.java requires images/middle.gif. */

/*
 * This class is just like MenuLookDemo, except the menu items
 * actually do something, thanks to event listeners.
 */
public class BettingHistoryGUI extends JFrame {
	JTextArea output;
	JScrollPane scrollPaneBets = new JScrollPane();
	private static JTable tableBets = new JTable();
	private static DefaultTableModel tableModelBets;
	private static final JButton backbutton = new JButton("Back");

	private static String[] columnNamesBets = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("BetAmount"),
			// ResourceBundle.getBundle("Etiquetas").getString("Event"),
			ResourceBundle.getBundle("Etiquetas").getString("Question"),
			ResourceBundle.getBundle("Etiquetas").getString("Date"),

	};

	String newline = "\n";

	/**
	 * @wbp.nonvisual location=147,239
	 */

	public static JMenuBar createMenuBar() {
		JMenuBar menuBar;
		JMenu menu, submenu;
		JMenuItem menuItem;
		JRadioButtonMenuItem rbMenuItem;
		JCheckBoxMenuItem cbMenuItem;

		// Create the menu bar.
		menuBar = new JMenuBar();
		menuBar.setBackground(Color.black);

		// Build the first menu.
		menu = new JMenu("Bet Status");
		menu.setForeground(Color.ORANGE);
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		menuBar.add(menu);

		// a group of JMenuItems
		menuItem = new JMenuItem("Past bets", KeyEvent.VK_T);
		// menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
		menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		// menuItem.addActionListener(null);
		menu.add(menuItem);

		// Build second menu in the menu bar.
		menu = new JMenu("Date filter");
		menu.setForeground(Color.ORANGE);
		menu.setMnemonic(KeyEvent.VK_N);
		menu.getAccessibleContext().setAccessibleDescription("This menu does nothing");
		menuBar.add(menu);

		menuItem = new JMenuItem("This year", KeyEvent.VK_I);
		menu.getAccessibleContext();
		menu.add(menuItem);
		menuItem = new JMenuItem("This Month", KeyEvent.VK_X);
		menu.getAccessibleContext();
		menu.add(menuItem);
		menuItem = new JMenuItem("This Week", KeyEvent.VK_9);
		menu.getAccessibleContext();
		menu.add(menuItem);

		return menuBar;
	}

//    public static Container createContentPane() {
//        //Create the content-pane-to-be.
//        JPanel contentPane = new JPanel(new BorderLayout());
//        contentPane.setOpaque(true);
//
//        //Create a scrolled text area.
//        output = new JTextArea(5, 30);
//        output.setEditable(false);
//        scrollPaneBets = new JScrollPane(output);
//
//        //Add the text area to the content pane.
//        contentPane.add(scrollPaneBets, BorderLayout.CENTER);
//
//        return contentPane;
//    }

	public void actionPerformed(
			ActionEvent e) { /******************************************************************************************************************************************    */
		JMenuItem source = (JMenuItem) (e.getSource());

		String s = "Action event detected." + newline ///////////////////// IMPLEMENT THE BET HISTORY HERE
														///////////////////// /////////////////////
				+ "    Event source: " + source.getText() + " (an instance of " + getClassName(source) + ")";
		output.append(s + newline);
		output.setCaretPosition(output.getDocument()
				.getLength()); /*********************************************************************************************************************************************** */
	}

	public void itemStateChanged(ItemEvent e) {
		JMenuItem source = (JMenuItem) (e.getSource());
		String s = "Item event detected." + newline + "    Event source: " + source.getText() + " (an instance of "
				+ getClassName(source) + ")" + newline + "    New state: "
				+ ((e.getStateChange() == ItemEvent.SELECTED) ? "selected" : "unselected");
		output.append(s + newline);
		output.setCaretPosition(output.getDocument().getLength());
	}

	// Returns just the class name -- no package info.
	protected String getClassName(Object o) {
		String classString = o.getClass().getName();
		int dotIndex = classString.lastIndexOf(".");
		return classString.substring(dotIndex + 1);
	}

	/** Returns an ImageIcon, or null if the path was invalid. */

	public BettingHistoryGUI() throws HeadlessException {
		super();

		// Create the content-pane-to-be.
		JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.setOpaque(true);
		setTitle("Betting History");

		// Create a scrolled text area.
		output = new JTextArea(5, 30);
		output.setEditable(false);
		scrollPaneBets = new JScrollPane(output);

		// Add the text area to the content pane.
		contentPane.add(scrollPaneBets, BorderLayout.CENTER);

		// Create and set up the window.
		// JFrame frame = new JFrame("My Betting History");
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setForeground(Color.ORANGE);
		setBackground(Color.BLACK);

		// Create and set up the content pane.
		// BettingHistoryGUI demo = new BettingHistoryGUI();
		setJMenuBar(createMenuBar());
		setContentPane(contentPane);
		setIconImage(new ImageIcon("/img/betting_3.jpg").getImage());

		// Getting Data
		scrollPaneBets.setViewportView(tableBets);
		tableModelBets = new DefaultTableModel(null, columnNamesBets);

		tableBets.setModel(tableModelBets);
		tableBets.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableBets.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableBets.getColumnModel().getColumn(2).setPreferredWidth(100);
		// tableBets.getColumnModel().getColumn(3).setPreferredWidth(200);

		tableModelBets.setDataVector(null, columnNamesBets);
		tableModelBets.setColumnCount(4); // another column added to allocate ev objects

		BLFacade facade = MainGUI.getBusinessLogic();
		Vector<domain.Bet> bets = facade.getAllBets(MainGUI.getCurrentUser());

		// if (bets.isEmpty() )
		// jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+
		// ": "+dateformat1.format(calendarMio.getTime()));
		// else
		// jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+
		// ": "+dateformat1.format(calendarMio.getTime()));
		for (domain.Bet bet : bets) {
			Vector<Object> row = new Vector<Object>();

			System.out.println("Bets " + bet);

			row.add(bet.getBetAmount());

			// TODO: FIX RELATION Bet -> Question -> Event

			// row.add(bet.getQuestion().getEvent().getDescription());
			row.add(bet.getQuestion().getQuestion());
			final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			row.add(sdf.format(bet.getBetDate()));
			row.add("Cancel Bet");
			// row.add(bet); // ev object added in order to obtain it with
			// tableModelEvents.getValueAt(i,2)
			tableModelBets.addRow(row);
		}

		/**
		 *  The ButtonColumn class provides a renderer and an editor that looks like a
		 *  JButton. The renderer and editor will then be used for a specified column
		 *  in the table. The TableModel will contain the String to be displayed on
		 *  the button.
		 *
		 *  The button can be invoked by a mouse click or by pressing the space bar
		 *  when the cell has focus. Optionally a mnemonic can be set to invoke the
		 *  button. When the button is invoked the provided Action is invoked. The
		 *  source of the Action will be the table. The action command will contain
		 *  the model row number of the button that was clicked.
		 *
		 */
		class ButtonColumn extends AbstractCellEditor
			implements TableCellRenderer, TableCellEditor, ActionListener, MouseListener
		{
			/**
			 * 
			 */
			//private static final long serialVersionUID = 1L;
			private JTable table;
			private Action action;
			private int mnemonic;
			private Border originalBorder;
			private Border focusBorder;

			private JButton renderButton;
			private JButton editButton;
			private Object editorValue;
			private boolean isButtonColumnEditor;

			/**
			 *  Create the ButtonColumn to be used as a renderer and editor. The
			 *  renderer and editor will automatically be installed on the TableColumn
			 *  of the specified column.
			 *
			 *  @param table the table containing the button renderer/editor
			 *  @param action the Action to be invoked when the button is invoked
			 *  @param column the column to which the button renderer/editor is added
			 */
			public ButtonColumn(JTable table, Action action, int column)
			{
				this.table = table;
				this.action = action;

				renderButton = new JButton();
				editButton = new JButton();
				editButton.setFocusPainted( false );
				editButton.addActionListener( this );
				originalBorder = editButton.getBorder();
				setFocusBorder( new LineBorder(Color.BLUE) );

				TableColumnModel columnModel = table.getColumnModel();
				columnModel.getColumn(column).setCellRenderer( this );
				columnModel.getColumn(column).setCellEditor( this );
				table.addMouseListener( this );
			}


			/**
			 *  Get foreground color of the button when the cell has focus
			 *
			 *  @return the foreground color
			 */
			public Border getFocusBorder()
			{
				return focusBorder;
			}

			/**
			 *  The foreground color of the button when the cell has focus
			 *
			 *  @param focusBorder the foreground color
			 */
			public void setFocusBorder(Border focusBorder)
			{
				this.focusBorder = focusBorder;
				editButton.setBorder( focusBorder );
			}

			public int getMnemonic()
			{
				return mnemonic;
			}

			/**
			 *  The mnemonic to activate the button when the cell has focus
			 *
			 *  @param mnemonic the mnemonic
			 */
			public void setMnemonic(int mnemonic)
			{
				this.mnemonic = mnemonic;
				renderButton.setMnemonic(mnemonic);
				editButton.setMnemonic(mnemonic);
			}

			@Override
			public Component getTableCellEditorComponent(
				JTable table, Object value, boolean isSelected, int row, int column)
			{
				if (value == null)
				{
					editButton.setText( "" );
					editButton.setIcon( null );
				}
				else if (value instanceof Icon)
				{
					editButton.setText( "" );
					editButton.setIcon( (Icon)value );
				}
				else
				{
					editButton.setText( value.toString() );
					editButton.setIcon( null );
				}

				this.editorValue = value;
				return editButton;
			}

			@Override
			public Object getCellEditorValue()
			{
				return editorValue;
			}

		//
		//  Implement TableCellRenderer interface
		//
			public Component getTableCellRendererComponent(
				JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
			{
				if (isSelected)
				{
					renderButton.setForeground(table.getSelectionForeground());
			 		renderButton.setBackground(table.getSelectionBackground());
				}
				else
				{
					renderButton.setForeground(table.getForeground());
					renderButton.setBackground(UIManager.getColor("Button.background"));
				}

				if (hasFocus)
				{
					renderButton.setBorder( focusBorder );
				}
				else
				{
					renderButton.setBorder( originalBorder );
				}

//				renderButton.setText( (value == null) ? "" : value.toString() );
				if (value == null)
				{
					renderButton.setText( "" );
					renderButton.setIcon( null );
				}
				else if (value instanceof Icon)
				{
					renderButton.setText( "" );
					renderButton.setIcon( (Icon)value );
				}
				else
				{
					renderButton.setText( value.toString() );
					renderButton.setIcon( null );
				}

				return renderButton;
			}

		//
		//  Implement ActionListener interface
		//
			/*
			 *	The button has been pressed. Stop editing and invoke the custom Action
			 */
			public void actionPerformed(ActionEvent e)
			{
				int row = table.convertRowIndexToModel( table.getEditingRow() );
				fireEditingStopped();

				//  Invoke the Action

				ActionEvent event = new ActionEvent(
					table,
					ActionEvent.ACTION_PERFORMED,
					"" + row);
				action.actionPerformed(event);
			}

		//
		//  Implement MouseListener interface
		//
			/*
			 *  When the mouse is pressed the editor is invoked. If you then then drag
			 *  the mouse to another cell before releasing it, the editor is still
			 *  active. Make sure editing is stopped when the mouse is released.
			 */
		    public void mousePressed(MouseEvent e)
		    {
		    	if (table.isEditing()
				&&  table.getCellEditor() == this)
					isButtonColumnEditor = true;
		    }

		    public void mouseReleased(MouseEvent e)
		    {
		    	if (isButtonColumnEditor
		    	&&  table.isEditing())
		    		table.getCellEditor().stopCellEditing();

				isButtonColumnEditor = false;
		    }

		    public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
		    public void mouseExited(MouseEvent e) {}
		}

		tableBets.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableBets.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableBets.getColumnModel().getColumn(3).setPreferredWidth(250);
		
		Action delete = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	BLFacade facade = MainGUI.getBusinessLogic();
		        JTable table = (JTable)e.getSource();
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		        // Deletes the bet if event date hasn't passed
		        if (facade.isEventOver(facade.getEventFromQuestion(bets.elementAt(modelRow).getQuestion()))) {
		        	JOptionPane.showMessageDialog(rootPane, "You cannot cancel the bet, the event is already over!","Event has ended", JOptionPane.ERROR_MESSAGE);
		        } else {
		        	facade.deleteBet(bets.elementAt(modelRow));
			        ((DefaultTableModel)table.getModel()).removeRow(modelRow);
			        JOptionPane.showMessageDialog(rootPane, "You have successfully canceled your bet!", "Bet canceled", JOptionPane.INFORMATION_MESSAGE);
			        facade.addFunds(MainGUI.getCurrentUser(), bets.elementAt(modelRow).getBetAmount());
		        }

		    }
		};

		ButtonColumn buttonColumn = new ButtonColumn(tableBets, delete, 3);
		//tableBets.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
		//tableBets.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JTextField()));
		/*
		 * tableBets.getColumnModel().getColumn(3).setCellRenderer(new
		 * ButtonRenderer()); tableBets.getColumnModel().getColumn(3).setCellEditor( new
		 * ButtonEditor(new JCheckBox()));
		 */
		// tableBets.getColumnModel().getColumn(3).setPreferredWidth(200);
		// tableBets.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2));
		// // not shown in JTable
		// Display the window.
		setSize(625, 300);
		setVisible(true);

		backbutton.setVerticalTextPosition(AbstractButton.BOTTOM);
		backbutton.setHorizontalTextPosition(AbstractButton.CENTER);
		contentPane.add(backbutton, BorderLayout.CENTER);

	}
}
