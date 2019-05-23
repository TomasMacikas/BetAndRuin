package domain;

import javax.persistence.Entity;
import javax.swing.JOptionPane;

@Entity
public class Wallet {

	private float availablecredit;
	private float bettingcost;

	
	
	public Wallet(float cred, float cost) {
		availablecredit = cred;
		bettingcost = cost;
	}
	
	public float getCredit() {
		return availablecredit;
		
	}
	
	
	public float Update() {
		float balance = availablecredit - bettingcost;
		if (balance >= 0)
			return balance;
		else {
			JOptionPane.showMessageDialog(null, "INSUFFICIENT credit", "You have a negative balance, get more credits!", JOptionPane.WARNING_MESSAGE);
			return balance;
		}
}
	
}
	