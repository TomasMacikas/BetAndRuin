package domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Bet {
	
	@Id @GeneratedValue
	private int betNumber;
	private float betAmount;
	private boolean isFinished;
	private Date betDate;
	
	private Account account;
	private Question question;
	
	public Bet() {
		super();
	}
	
	public Bet(float amount, Date date, Question quest, Account acc) {
		betAmount = amount;
		betDate = date;
		question = quest;
		account = acc;
	}

	public int getBetNumber() {
		return betNumber;
	}

	public void setBetNumber(int betNumber) {
		this.betNumber = betNumber;
	}

	public float getBetAmount() {
		return betAmount;
	}

	public void setBetAmount(float betAmount) {
		this.betAmount = betAmount;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public Date getBetDate() {
		return betDate;
	}

	public void setBetDate(Date betDate) {
		this.betDate = betDate;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	
	
}
