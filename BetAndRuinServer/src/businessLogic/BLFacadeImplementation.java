package businessLogic;

import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Account;
import domain.Bet;
import domain.Event;
import domain.Question;
import domain.Transaction;
import exceptions.EventFinished;
import exceptions.InsuficientFunds;
import exceptions.QuestionAlreadyExist;
/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	//Account currentUser = null;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
			DataAccess dbManager=new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
			dbManager.initializeDB();
			dbManager.close();
			}
		
	}

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
   @WebMethod
   public Question createQuestion(Event event, String question, float betMinimum, float odds) throws EventFinished, QuestionAlreadyExist{
	   
	    //The minimum bed must be greater than 0
	    DataAccess dBManager=new DataAccess();
		Question qry=null;
		
	    //TODO Uncomment after testing	
//		if(new Date().compareTo(event.getEventDate())>0)
//			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
		 
		odds = 1/odds*100;
		
		qry=dBManager.createQuestion(event,question,betMinimum, odds);
		 
		System.out.println(" The odds are: " + odds);

		dBManager.close();
		
		return qry;
   };
   @WebMethod
   public Event createEvent(Date date, String eventName, String result1) {
	    DataAccess dBManager=new DataAccess();
		Event qry=null;
		
		 qry=dBManager.createEvent(date, eventName, result1);		

		dBManager.close();
		
		return qry;
   }
	
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
    @WebMethod	
	public Vector<Event> getEvents(Date date)  {
		DataAccess dbManager=new DataAccess();
		Vector<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}
    

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
    @WebMethod	
	 public void initializeBD(){
		DataAccess dBManager=new DataAccess();
		dBManager.initializeDB();
		dBManager.close();
	}
    /*
     * 
     * This method gets information from GUI, and communicates with database
     * if created successfully returns true
     */
    @WebMethod	
	 public boolean Register(String fname, String lname, String em, String d, String uname, String passw, String gender, int funds){
		DataAccess dBManager=new DataAccess();
		boolean created = dBManager.createAccount(fname,lname,em,d,uname, passw, gender, funds);
		// Sets current user when registering a new account
		//currentUser = dBManager.findAccount(uname);;
		dBManager.close();
		return created;
	} 
    
   
    
  
    
    @WebMethod	
	 public boolean UsernameAvailable(String username){
		DataAccess dBManager=new DataAccess();
		boolean notAvailable = dBManager.usernameExists(username);
		dBManager.close();
		return !notAvailable;
	}
    
 
    
    @Override
	public boolean isValidUser(String username, String password) {
		DataAccess dBManager=new DataAccess();
		if (dBManager.findAccount(username) == null) {
			return false;
		} else {
			Account a = dBManager.findAccount(username);
			dBManager.close();
			return checkPassword(a, password);
		}
	}
        
    public boolean checkPassword(Account a, String password) {
    	if (a.getPassword().equals(password)) {
    		//currentUser = a;
    		//System.out.print("The user is " + currentUser);
    		return true;
    	} else {
    		return false;
    	}
    	
    }
    
  
    @WebMethod
	public Event deleteEvent(Event event) {
    	DataAccess dBManager = new DataAccess();
    	Event ev = dBManager.deleteEvent(event);
    	return ev;
    }
    
    @WebMethod 
    public boolean setResultEven(String result, Event object) {
    	DataAccess dBManager = new DataAccess();
    	boolean res = dBManager.setResult(result, object);
    	dBManager.close();
    	return res;
    }
    
    @WebMethod
	public Bet deleteBet(Bet bet) {
    	    DataAccess dBManager = new DataAccess();
    	    Bet b = dBManager.deleteBet(bet);
    	    return b;

    }

	@WebMethod 
	public boolean isEventOver(Event event) {
		Calendar calendarDate = Calendar.getInstance();
    	Date today = calendarDate.getTime();
    	Date eventDate = event.getEventDate();
    	if (today.before(eventDate)) {
    	    return false;
    	} else {
        	return true;
    	}
	}

	@Override
	public boolean UpdateFirstname(String firstname, String username) {
		DataAccess dBManager = new DataAccess();
    	boolean update2 = dBManager.updateFirstname(firstname, username);
    	dBManager.close();
    	//updateCurrentUser();
    	return update2;
		
	}

	@Override
	public boolean UpdateLastname(String lastname, String username) {
		DataAccess dBManager = new DataAccess();
    	boolean update3 = dBManager.updateLastname(lastname, username);
    	dBManager.close();
    	//updateCurrentUser();
    	return update3;
	}

	@Override
	public boolean UpdateGender(String gender, String username) {
		// TODO Auto-generated method stub
		DataAccess dBManager = new DataAccess();
    	boolean update6 = dBManager.updateGender(gender, username);
    	dBManager.close();
    	//updateCurrentUser();
    	return update6;
	}

	@Override
	public boolean UpdateUsername(String username, String usernamedefault) {
		// TODO Auto-generated method stub
		DataAccess dBManager = new DataAccess();
    	boolean update1 = dBManager.updateUsername(username, usernamedefault);
    	dBManager.close();
    	return update1;
	}

	@Override
	public Boolean UpdatePassword(String password, String username) {
		DataAccess dBManager = new DataAccess();
    	boolean update5 = dBManager.updatePassword(password, username);
    	dBManager.close();
    	//updateCurrentUser();
    	return update5;
	}

	@Override
	public boolean UpdateEmailAddress(String email, String username) {
		DataAccess dBManager = new DataAccess();
    	boolean update4 = dBManager.updateEmailAddress(email, username);
    	dBManager.close();
    	return update4;
	}
	
	@Override
	public float addFunds(Account acc, float funds) {
		DataAccess dBManager = new DataAccess();
		float update7 = dBManager.updateFunds(funds + acc.getWalletFunds(), acc);
		dBManager.createTransaction(funds, new Date(), "Added funds", acc);
		dBManager.close();
		//updateCurrentUser();
		return update7;
	}
	
	@Override
	public float withdrawFunds(Account acc, float funds) {
		DataAccess dBManager = new DataAccess();
		float update8 = dBManager.updateFunds(acc.getWalletFunds() - funds, acc);
		dBManager.createTransaction(funds, new Date(), "Withdrawn funds", acc);
		dBManager.close();
		//updateCurrentUser();
		return update8;
	}

	
    
    @WebMethod
    public Bet placeBet(Account acc, float amount, Question question) throws InsuficientFunds, EventFinished {
    	DataAccess dBManager = new DataAccess();
    	
    	//Account acc = getCurrentUser(); // Needs to update information
    	
    	//Checking if there is enough money on wallet
    	if(acc.getWalletFunds() < amount) {
    		throw new InsuficientFunds(ResourceBundle.getBundle("Etiquetas").getString("InsuficientFunds"));
    	}
    	
    	//TODO FIX RELATION HERE ALSO 
    	//System.out.println(question.getEvent().getEventDate());
    	//if(new Date().compareTo(question.getEvent().getEventDate())>0)
			//throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
    	System.out.println("*************" + new Date());
    	Bet bet = dBManager.createBet(amount, new Date(), question, acc);
    	dBManager.updateFunds(acc.getWalletFunds()-amount, acc);
    	dBManager.createTransaction(amount*(-1),new Date(), bet.getQuestion().getQuestion(), acc);
    	dBManager.close();
    	//updateCurrentUser();
    	return bet;
    	
    }
	
	@WebMethod public Vector<Bet> getAllBets(Account acc){
		DataAccess dBManager = new DataAccess();
    	Vector<Bet> bets = dBManager.getAllBets(acc);
    	dBManager.close();
    	return bets;
	}
	
	@WebMethod public void updateData() {
		DataAccess dBManager = new DataAccess();
		
		Vector<Event> events;
		
		events = dBManager.getPassedEvents(new Date());
		
		for(Event e : events) {
			finalizeEvent(e);
		}
		
		dBManager.close();
	}

	@WebMethod
   public Transaction createTransaction(Account acc, float amount, Date date, String description) {
	    DataAccess dBManager=new DataAccess();
		Transaction trans=null;
		
		trans = dBManager.createTransaction(amount, date, description, acc);		

		dBManager.close();
		
		return trans;
   }
	
	@WebMethod	
	public Vector<Transaction> getTransactions(Account acc)  {
		DataAccess dbManager=new DataAccess();
		Vector<Transaction>  trans=dbManager.getTransactions(acc);
		dbManager.close();
		return trans;
	}

	@WebMethod
	public boolean updateQuestionAnswer(Question question, boolean ans) {
		DataAccess dbManager=new DataAccess();
		boolean a=dbManager.updateQuestionAnswer(question, ans);
		dbManager.close();
		return a;
	}
	
	@WebMethod
	public boolean updateQuestionAnswer2(Question question, String ans) {
		DataAccess dbManager=new DataAccess();
		boolean a=dbManager.updateQuestionAnswer2(question, ans);
		dbManager.close();
		return a;
	}

	@WebMethod
	public boolean updateEventFinished(Event event, boolean ans) {
		DataAccess dbManager=new DataAccess();
		boolean ev=dbManager.updateEventFinished(event, ans);
		dbManager.close();
		return ev;
	}

	@Override
	public Account findAccount(String username) {
		DataAccess dbManager=new DataAccess();
		Account account = dbManager.findAccount(username);
		dbManager.close();
		return account;
	}

	@Override
	public void finalizeEvent(Event ev) {
		DataAccess dbManager=new DataAccess();
		Vector<Question> questions = ev.getQuestions();
		for(Question q : questions){
			System.out.println(q);
			//Account funds updated here
			if(q.isCorrect()) {
				Vector<Bet> bets = dbManager.getBetsByQuestion(q);
				for(Bet b: bets) {
					dbManager.updateFunds(b.getAccount().getWalletFunds() + b.getBetAmount()*q.getOdds(), b.getAccount());
					dbManager.createTransaction(b.getBetAmount()*q.getOdds(), new Date(), "Bet " + b.getQuestion().getQuestion(), b.getAccount());
				}
			}
		}
		dbManager.markEventAsFinished(ev);
	}
	
	@Override 
	public Event getEventFromQuestion(Question question) {
		DataAccess dbManager=new DataAccess();
		Event ev = dbManager.getEventFromQuestion(question);
		dbManager.close();
		return ev;
	}

	

}

