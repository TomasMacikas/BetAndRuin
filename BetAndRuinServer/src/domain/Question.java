package domain;

import java.io.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Question implements Serializable {
	
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer questionNumber;
	private String question; 
	private float betMinimum;
	private boolean correct;
	private String correct1;
	private float odds; 
	
	
	public boolean isCorrect() {
		return correct;
	}
	
	public String isCorrect1() {
		return correct1;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	
	public void setCorrect1(String correct) {
		this.correct1 = correct;
	}

	@XmlIDREF
	private Event event;

	public Question(){
		super();
	}
	
	public Question(Integer queryNumber, String query, float betMinimum, Event event) {
		super();
		this.questionNumber = queryNumber;
		this.question = query;
		this.betMinimum=betMinimum;
		this.event = event;
		this.correct = false;
		this.correct1 = null;
	}
	
	public Question(String query, float betMinimum,  Event event, float odds) {
		super();
		this.question = query;
		this.betMinimum=betMinimum;
		this.correct = false;
		this.correct1 = null;
		this.event = event;
		this.odds = odds;
	}

	
	public Integer getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public float getBetMinimum() {
		return betMinimum;
	}

	public void setBetMinimum(float betMinimum) {
		this.betMinimum = betMinimum;
	}
	
	public float getOdds() {
		return odds;
	}

	public void setOdds(float odds) {
		this.odds = odds;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String toString(){
		return questionNumber+";"+question+";"+Float.toString(betMinimum);
	}
	
}