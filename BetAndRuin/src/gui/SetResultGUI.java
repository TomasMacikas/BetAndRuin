package gui;

import businessLogic.BLFacade;
import com.toedter.calendar.JCalendar;
import domain.Question;
import domain.Event;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;


public class SetResultGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 

	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));

	private JButton finalizeEventButton = new JButton("Finalize Event"); //$NON-NLS-1$ //$NON-NLS-2$
	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarMio = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();

	private JTable tableEvents= new JTable();
	private JTable tableQueries = new JTable();

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;

	
	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QueryN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Query"),
			ResourceBundle.getBundle("Etiquetas").getString("Answer")
	};
	private final JButton answerYesButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AnswerYes"));
	private final JButton answerNoButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AnswerNo")); 
	private final JButton btnCustomAnswer = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SetResult"));
	 // obtain ev object
	private final JLabel textResult = new JLabel();

	public SetResultGUI() {
		getContentPane().setBackground(Color.BLACK);
		setBackground(Color.WHITE);
		try {
			jbInit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception
	{

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		this.setTitle("Set Event Results");
		jLabelEventDate.setFont(new Font("Roboto Cn", Font.PLAIN, 15));
		jLabelEventDate.setForeground(Color.ORANGE);

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelQueries.setFont(new Font("Roboto Cn", Font.PLAIN, 15));
		jLabelQueries.setForeground(Color.ORANGE);
		jLabelQueries.setBounds(138, 248, 406, 14);
		jLabelEvents.setFont(new Font("Roboto Cn", Font.PLAIN, 15));
		jLabelEvents.setForeground(Color.ORANGE);
		jLabelEvents.setBounds(295, 19, 259, 16);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);
		finalizeEventButton.setBackground(Color.ORANGE);
		
		
		finalizeEventButton.setEnabled(false);
		finalizeEventButton.setBounds(548, 420, 126, 30);
		getContentPane().add(finalizeEventButton);

		jButtonClose.setBounds(new Rectangle(5, 420, 130, 30));

		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
			}
		});

		this.getContentPane().add(jButtonClose, null);

		jCalendar1.setBounds(new Rectangle(40, 50, 225, 150));

		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {

				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				}
				else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarMio = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
					jCalendar1.setCalendar(calendarMio);
					Date firstDay=trim(new Date(jCalendar1.getCalendar().getTime().getTime()));

					try {
						tableModelEvents.setDataVector(null, columnNamesEvents);
						tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

						BLFacade facade=MainGUI.getBusinessLogic();

						Vector<domain.Event> events=facade.getEvents(firstDay);

						if (events.isEmpty() ) {
							jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendarMio.getTime()));
							finalizeEventButton.setEnabled(false);
						}
						else {
							jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarMio.getTime()));
							finalizeEventButton.setEnabled(true);
						}
						for (domain.Event ev:events){
							Vector<Object> row = new Vector<Object>();

							System.out.println("Events "+ev);

							row.add(ev.getEventNumber());
							row.add(ev.getDescription());
							row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
							tableModelEvents.addRow(row);		
						}
						tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
						tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not shown in JTable
					} catch (Exception e1) {

						jLabelQueries.setText(e1.getMessage());
					}

				}
				CreateQuestionGUI.paintDaysWithEvents(jCalendar1);
			} 
		});

		this.getContentPane().add(jCalendar1, null);
		
		scrollPaneEvents.setBounds(new Rectangle(292, 50, 346, 150));
		scrollPaneQueries.setBounds(new Rectangle(138, 274, 406, 116));

		
		
		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableEvents.getSelectedRow();
				domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
				//Display answer
				textResult.setText(ev.getResult());
				
				Vector<Question> queries=ev.getQuestions();

				tableModelQueries.setDataVector(null, columnNamesQueries);
				tableModelQueries.setColumnCount(4);
				
				if (MainGUI.getCurrentUser() != null) {
					if (queries.isEmpty()) {
						answerNoButton.setEnabled(false);
						answerYesButton.setEnabled(false);
						jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries")+": "+ev.getDescription());
					}
					else {
						answerNoButton.setEnabled(true);
						answerYesButton.setEnabled(true);
						jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent")+" "+ev.getDescription());
					}
				}

				for (domain.Question q:queries){
					Vector<Object> row = new Vector<Object>();

					row.add(q.getQuestionNumber());
					row.add(q.getQuestion());
					row.add(q.isCorrect());
					row.add(q);
					tableModelQueries.addRow(row);	
				}
				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
				tableQueries.getColumnModel().getColumn(1).setPreferredWidth(250);
				tableQueries.getColumnModel().getColumn(2).setPreferredWidth(100);
				tableQueries.getColumnModel().removeColumn(tableQueries.getColumnModel().getColumn(3)); // not shown in JTable
			}
		});

		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);


		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(250);
		tableQueries.getColumnModel().getColumn(2).setPreferredWidth(100);


		finalizeEventButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=tableEvents.getSelectedRow();
				domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
				BLFacade facade = MainGUI.getBusinessLogic();
				//facade.updateData();
				facade.finalizeEvent(ev);
				//facade.updateEventFinished(ev, true);
				JOptionPane.showMessageDialog(rootPane, "The Event Finished with success");
			}
		});
		
		
		
		this.getContentPane().add(scrollPaneEvents, null);
		this.getContentPane().add(scrollPaneQueries, null);
		answerYesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=tableQueries.getSelectedRow();
				domain.Question question=(domain.Question)tableModelQueries.getValueAt(i,3);
				question.setCorrect(true);
				BLFacade facade = MainGUI.getBusinessLogic();
				facade.updateQuestionAnswer(question, true);
				tableModelQueries.setValueAt(true, i, 2);
			}
		});
		answerYesButton.setEnabled(false);
		answerYesButton.setBackground(Color.ORANGE);
		answerYesButton.setBounds(274, 420, 130, 30);
		
		getContentPane().add(answerYesButton);
		answerNoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=tableQueries.getSelectedRow();
				domain.Question question=(domain.Question)tableModelQueries.getValueAt(i,3);
				BLFacade facade = MainGUI.getBusinessLogic();
				facade.updateQuestionAnswer(question, false);
				question.setCorrect(false);
				tableModelQueries.setValueAt(false, i, 2);
			}
		});
		answerNoButton.setEnabled(false);
		answerNoButton.setBackground(Color.ORANGE);
		answerNoButton.setBounds(414, 420, 130, 30);
		
		getContentPane().add(answerNoButton);
		btnCustomAnswer.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		
		btnCustomAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BLFacade facade = MainGUI.getBusinessLogic();
				 String result = null;
				 int i=tableEvents.getSelectedRow();
					domain.Event num=(domain.Event)tableModelEvents.getValueAt(i,2);
				result = JOptionPane.showInputDialog("Please insert the result");
				
				System.out.println(result);
				domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2);
				textResult.setText(result);
				facade.setResultEven(result, num);
			}
		});
		btnCustomAnswer.setEnabled(true);
		btnCustomAnswer.setBackground(Color.ORANGE);
		btnCustomAnswer.setBounds(138, 420, 130, 30);
		getContentPane().add(btnCustomAnswer);
		
		JLabel lblFinalResult = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SetResultGUI.lblNewLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblFinalResult.setFont(new Font("Roboto Cn", Font.PLAIN, 15));
		lblFinalResult.setForeground(Color.ORANGE);
		lblFinalResult.setBounds(295, 211, 109, 14);
		getContentPane().add(lblFinalResult);
		
		
		
		textResult.setBackground(Color.WHITE);
		textResult.setForeground(Color.RED);
		int i=tableEvents.getSelectedRow();
		
		
		
		
		
		textResult.setBounds(395, 208, 106, 20);
		getContentPane().add(textResult);
		

	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

	private Date trim(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		return calendar.getTime();
	}
	
	private Date newDate(int year,int month,int day) {

	     Calendar calendar = Calendar.getInstance();
	     calendar.set(year, month, day,0,0,0);
	     calendar.set(Calendar.MILLISECOND, 0);
	     return calendar.getTime();
	}
}
