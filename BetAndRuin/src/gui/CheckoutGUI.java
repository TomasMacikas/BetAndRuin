package gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import domain.Account;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
 
public class CheckoutGUI
{
  private JPanel mainPanel = new JPanel();
  
  private JLabel firstlbl = new JLabel("Card's numbers: ");
  private JLabel secondlbl = new JLabel("CVV: ");
  private JLabel thirdlbl = new JLabel("Name on the card:  ");
  private JButton cancelBtn = new JButton("Cancel");
  private JButton confirmBtn = new JButton("Confirm");
  private JTextField cardNumberField = new JTextField(20);
  private JTextField cvvField = new JTextField(3);
  private JTextField cardNameField = new JTextField(50);
  private List<JTextField> fieldList = new ArrayList<JTextField>();
  public static boolean ok = false;
  public static boolean getOk() {
	  return ok;
  }
  
  public CheckoutGUI()
  {
    fieldList.add(cardNumberField);
    fieldList.add(cvvField);
    fieldList.add(cardNameField);
    DocumentListener myDocListener = new MyDocListener();
    mainPanel.setBackground(Color.BLACK);
    firstlbl.setForeground(Color.ORANGE);
    mainPanel.add(firstlbl);
    mainPanel.add(cardNumberField);
    secondlbl.setForeground(Color.ORANGE);
    mainPanel.add(secondlbl);
    mainPanel.add(cvvField);
    thirdlbl.setForeground(Color.ORANGE);
    mainPanel.add(thirdlbl);
    mainPanel.add(cardNameField);
    for (JTextField field : fieldList)
    {
     // mainPanel.add(field);
      field.getDocument().addDocumentListener(myDocListener);
    }
    confirmBtn.setBackground(Color.ORANGE);
    confirmBtn.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		 ok = true;
    		WalletGUI finish = new WalletGUI();
    		finish.setLocationRelativeTo(null);
    		finish.setVisible(true);
    		JOptionPane.showMessageDialog(null, "Accepted");
    		mainPanel.setVisible(false);
    		frame.dispose();
    		
    	}
    });
     
    confirmBtn.setEnabled(false);
    mainPanel.add(confirmBtn);
    cancelBtn.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		WalletGUI back = new WalletGUI();
    		back.setVisible(true);
    		mainPanel.setVisible(false);
    	}
    });
    cancelBtn.setEnabled(true);
    mainPanel.add(cancelBtn);
  }
 
  public JComponent getComponent()
  {
    return mainPanel;
  }
   
  private void checkFieldsFull()
  {
    for (JTextField field : fieldList)
    {
      if (field.getText().trim().isEmpty())
      {
        confirmBtn.setEnabled(false);
        return;
      }
    }
    confirmBtn.setEnabled(true);
  }
 
  private class MyDocListener implements DocumentListener
  {
 
    public void changedUpdate(DocumentEvent e)
    {
      checkFieldsFull();
    }
 
    public void insertUpdate(DocumentEvent e)
    {
      checkFieldsFull();
    }
 
    public void removeUpdate(DocumentEvent e)
    {
      checkFieldsFull();
    }
 
  }
  
  static JFrame frame = new JFrame("TextFieldEnablesButton");
 
  static void createAndShowUI()
  {
    
    frame.getContentPane().add(new CheckoutGUI().getComponent());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
  static void closeFrame() {
	  frame.dispose();
  }
}
 
