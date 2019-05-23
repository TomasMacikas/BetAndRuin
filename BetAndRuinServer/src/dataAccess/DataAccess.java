package dataAccess;

import java.util.Calendar;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import domain.Account;
import domain.Bet;
import domain.Event;
import domain.Question;
import domain.Transaction;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess {
	protected static EntityManager db;
	protected static EntityManagerFactory emf;

	ConfigXML c;

	public DataAccess(boolean initializeMode) {

		c = ConfigXML.getInstance();

		System.out.println("Creating DataAccess instance => isDatabaseLocal: " + c.isDatabaseLocal()
				+ " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());

		String fileName = c.getDbFilename();
		if (initializeMode)
			fileName = fileName + ";drop";

		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory(
					"objectdb://" + c.getDatabaseNode() + ":" + c.getDatabasePort() + "/" + fileName, properties);

			db = emf.createEntityManager();
		}
	}

	public DataAccess() {
		new DataAccess(false);
	}

	/**
	 * This is the data access method that initializes the database with some events
	 * and questions. This method is invoked by the business logic (constructor of
	 * BLFacadeImplementation) when the option "initialize" is declared in the tag
	 * dataBaseOpenMode of resources/config.xml file
	 */
	public void initializeDB() {

		db.getTransaction().begin();
		try {

			Event ev1 = new Event(988, "Atletico-Athletic", newDate(2019, 1, 17));
			Event ev2 = new Event(916, "Eibar-Barcelona", newDate(2019, 1, 17));
			Event ev3 = new Event(841, "Getafe-Celta", newDate(2019, 1, 17));
			Event ev4 = new Event(836, "Alavés-Deportivo", newDate(2019, 1, 17));
			Event ev5 = new Event(546, "Español-Villareal", newDate(2019, 1, 17));
			Event ev6 = new Event(378, "Las Palmas-Sevilla", newDate(2019, 1, 17));
			Event ev7 = new Event(44, "Malaga-Valencia", newDate(2019, 1, 17));
			Event ev8 = new Event(695, "Girona-Leganés", newDate(2019, 1, 17));
			Event ev9 = new Event(885, "Real Sociedad-Levante", newDate(2019, 1, 17));
			Event ev10 = new Event(576, "Betis-Real Madrid", newDate(2019, 1, 17));

			Event ev11 = new Event(896, "Atletico-Athletic", newDate(2019, 2, 1));
			Event ev12 = new Event(670, "Eibar-Barcelona", newDate(2019, 2, 1));
			Event ev13 = new Event(500, "Getafe-Celta", newDate(2019, 2, 1));
			Event ev14 = new Event(9, "Alavés-Deportivo", newDate(2019, 2, 1));
			Event ev15 = new Event(276, "Español-Villareal", newDate(2019, 2, 1));
			Event ev16 = new Event(281, "Las Palmas-Sevilla", newDate(2019, 2, 1));

			Event ev17 = new Event(329, "Malaga-Valencia", newDate(2019, 2, 24));
			Event ev18 = new Event(313, "Girona-Leganés", newDate(2019, 2, 24));
			Event ev19 = new Event(144, "Real Sociedad-Levante", newDate(2019, 2, 24));
			Event ev20 = new Event(207, "Betis-Real Madrid", newDate(2019, 2, 24));
			
			System.out.println("Test event &&&&&&&&&&&&&&&&&&&&&&&&&&&");
			Event ev21 = new Event(887, "Test Event", newDate(2019, 3, 31));

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;

			if (Locale.getDefault().equals(new Locale("es"))) {
				q1 = ev1.addQuestion("¿Quien ganará el partido?", 1, 0);
				q2 = ev1.addQuestion("¿Quien meterá el primer gol?", 2, 0);
				q3 = ev11.addQuestion("¿Quien ganar? el partido?", 1, 0);
				q4 = ev11.addQuestion("¿Cuántos goles se marcarán?", 2, 0);
				q5 = ev17.addQuestion("¿Quien ganará el partido?", 1, 0);
				q6 = ev17.addQuestion("¿Habrá goles en la primera parte?", 2, 0);
			} else if (Locale.getDefault().equals(new Locale("en"))) {
				q1 = ev1.addQuestion("Who will win the match?", 1, 0);
				q2 = ev1.addQuestion("Who will score first?", 2, 0);
				q3 = ev11.addQuestion("Who will win the match?", 1, 0);
				q4 = ev11.addQuestion("How many goals will be scored in the match?", 2, 0);
				q5 = ev17.addQuestion("Who will win the match?", 1, 0);
				q6 = ev17.addQuestion("Will there be goals in the first half?", 2, 0);
			} else {
				q1 = ev1.addQuestion("Zeinek irabaziko du partidua?", 1, 0);
				q2 = ev1.addQuestion("Zeinek sartuko du lehenengo gola?", 2, 0);
				q3 = ev11.addQuestion("Zeinek irabaziko du partidua?", 1, 0);
				q4 = ev11.addQuestion("Zenbat gol sartuko dira?", 2, 0);
				q5 = ev17.addQuestion("Zeinek irabaziko du partidua?", 1, 0);
				q6 = ev17.addQuestion("Golak sartuko dira lehenengo zatian?", 2, 0);
			}

			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);

			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);
		
			db.persist(ev21); //Test

			db.getTransaction().commit();
			System.out.println("Db initialized");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event      to which question is added
	 * @param question   text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist if the same question already exists for the
	 *                              event
	 */
	public Question createQuestion(Event event, String question, float betMinimum, float odds) throws QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= " + event + " question= " + question + " betMinimum="
				+ betMinimum + " odds: " + odds);

		Event ev = db.find(Event.class, event.getEventNumber());

		if (ev.DoesQuestionExists(question))
			throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));

		db.getTransaction().begin();
		Question q = ev.addQuestion(question, betMinimum, odds);
		
		q.setEvent(ev); //No relation Question -> Event without this
		
		//db.persist(q);
		db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions
						// property of Event class
						// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return q;

	}

	/**
	 * This method retrieves from the database the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
//		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1", Event.class);
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
		for (Event ev : events) {
//			System.out.println(ev.toString());
			res.add(ev);
		}
		return res;

	}

	public void close() {
		db.close();
		System.out.println("DataBase closed");
	}

	private Date newDate(int year, int month, int day) {

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, day, 0, 0, 0); //Month needs to be -1, 
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	/*
	 * This method creates an account
	 * 
	 * 
	 */
	public boolean createAccount(String fname, String lname, String em, String d, String uname, String passw,
			String gender, int funds) {
		// System.out.println(">> DataAccess: createQuestion=> event= "+event+"
		// question= "+question+" betMinimum="+betMinimum);

		// Account ev = db.find(Account.class, a.getEventNumber());

		// if (db.find(Account.class, username) == null) throw new
		// QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));

		if (usernameExists(uname) == true)
			return false;
		db.getTransaction().begin();
		Account a = new Account(fname, lname, em, d, uname, passw, gender, funds, false, false);
		// db.persist(q);
		db.persist(a);
		db.getTransaction().commit();
		return true;
	}

	public boolean usernameExists(String username) {
		if (db.find(Account.class, username) == null) {
			System.out.println("NOT FOUND");
			return false;
		} else {
			return true;
		}
	}

	/**
	 * This method verifies that an user account exists on the database
	 * 
	 * @param username and password
	 * @return true if username and password exist in the database, false otherwise
	 */

	public Account findAccount(String username) {
		Account a = null;
		
		db.getTransaction().begin();
		
		try {
			TypedQuery<Account> q1 = db.createQuery(
					"SELECT a FROM Account a WHERE username = \"" + username,
					Account.class);
			a = q1.getSingleResult(); 	
			db.getTransaction().commit();
		} catch (Exception e) {
			return a;
		}
		
		return a; 

		// if (q1.getResultList().size() == 1) {

		// Query q2 = db.createQuery(
		// "UPDATE Account SET isLogged = true");

		// q2.executeUpdate();

		// user = q1.getResultList().size() == 1;
		// } else {
		// System.out.print("No user was found");
		// }

	}
	
	public Boolean updateUsername(String username, String usernamedefault) {
		boolean user = false;
		db.getTransaction().begin();
		Query q1 = db.createQuery("UPDATE Account SET username = \"" + username + "\" WHERE username = \"" + usernamedefault);
		q1.executeUpdate();
		db.getTransaction().commit();
		user = true;
		return user;
	}
		
	public Boolean updatePassword(String password, String username) {
		boolean user = false;
		db.getTransaction().begin();
		Query q1 = db.createQuery("UPDATE Account SET password = \"" + password + "\" WHERE username = \"" + username);
		q1.executeUpdate();
		db.getTransaction().commit();
		user = true;
		return user;
	}
	
	public boolean updateEmailAddress(String email, String username) {
		boolean user = false;
		db.getTransaction().begin();
		Query q1 = db.createQuery("UPDATE Account SET email = \"" + email + "\" WHERE username = \"" + username);
		q1.executeUpdate();
		db.getTransaction().commit();
		user = true;
		return user;
	}
	
	public boolean updateGender(String gender, String username) {
		boolean user = false;
		db.getTransaction().begin();
		Query q1 = db.createQuery("UPDATE Account SET gender = \"" + gender + "\" WHERE username = \"" + username);
		q1.executeUpdate();
		db.getTransaction().commit();
		user = true;
		return user;

	}

	public boolean updateFirstname(String firstname, String username) {

		db.getTransaction().begin();
		Boolean user = false;

		Query q2 = db
				.createQuery("UPDATE Account SET firstname = \"" + firstname + "\" WHERE username = \"" + username);

		q2.executeUpdate();
		db.getTransaction().commit();

		user = true;

		return user;
	}
	
	public boolean updateLastname(String lastname, String username) {

		db.getTransaction().begin();
		Boolean user = false;

		Query q2 = db
				.createQuery("UPDATE Account SET lastname = \"" + lastname + "\" WHERE username = \"" + username);

		q2.executeUpdate();
		db.getTransaction().commit();

		user = true;

		return user;
	}
			
		
	
	
	public Boolean verifyAccount(String username, String password) {

		db.getTransaction().begin();
		Boolean user = false;

			Query q1 = db.createQuery(
					"SELECT username FROM Account WHERE username = \"" + username + "\" AND password = \"" + password);
			
			if (q1.getResultList().size() == 1) {

				Query q2 = db.createQuery(
						"UPDATE Account SET isLogged = true");
		
				q2.executeUpdate();
				db.getTransaction().commit();
			
				user = q1.getResultList().size() == 1;
			} else {
				System.out.print("No user was found");
			}

		return user;
			
		}

	public String getUsername(String username) {
		db.getTransaction().begin();
		String usernamelbl = null;
		Query q3 = db.createQuery("SELECT username FROM Account WHERE username = \"" + username);
		db.getTransaction().commit();
		return usernamelbl;
	}

		public Event createEvent(Date date, String description, String result) {
//			System.out.println(">> DataAccess: createQuestion=> event= " + event + " question= " + question + " betMinimum="
//					+ betMinimum);

			db.getTransaction().begin();
			Event ev = new Event(description, date, result);
			// db.persist(q);
			db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions
							// property of Event class
							// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
			db.getTransaction().commit();
			return ev;

		}

		public Event deleteEvent(Event event) {
			Event ev = db.find(Event.class, event);
			
			db.getTransaction().begin();
			db.remove(ev);
			db.getTransaction().commit();
			return ev;
		}
		
		public boolean setResult(String result, Event object) {
			Event ev = db.find(Event.class, object);
			db.getTransaction().begin();

			ev.setResult(result);
			db.getTransaction().commit();
			return true;
		}
		

		public Bet deleteBet(Bet bet) {
			Bet b = db.find(Bet.class, bet);
			db.getTransaction().begin();
			db.remove(b);
			db.getTransaction().commit();
			System.out.print("Your bet " + b + " has been deleted");
			return b;
		}

		public Bet createBet(float amount, Date date, Question question, Account account) {
			
			db.getTransaction().begin();
			//create bet
			Bet b = new Bet(amount, date, question, account);
			//update account
			db.persist(b);
			db.getTransaction().commit();
			return b;
		}

		public float updateFunds(float funds, Account acc) {
			Account a = db.find(Account.class, acc.getUsername());
			db.getTransaction().begin();
			a.setWalletFunds(funds);
			
			db.getTransaction().commit();
			return funds;
		}

		public Vector<Bet> getAllBets(Account acc) {
			Vector<Bet> res = new Vector<Bet>();
			TypedQuery<Bet> query = db.createQuery("SELECT bets FROM Bet bets WHERE bets.account=?1", Bet.class);
			query.setParameter(1, acc);
			List<Bet> bets = query.getResultList();
			for (Bet bet : bets) {
				System.out.println("***" + bet.getBetAmount() + "***");
				res.add(bet);
			}
			return res;

		}

		public Vector<Event> getPassedEvents(Date date) {
			Vector<Event> res = new Vector<Event>();
			TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate<=?1 AND ev.finished ==?2", Event.class);
			query.setParameter(1, date);
			query.setParameter(2, false);
			List<Event> events = query.getResultList();
			for (Event ev : events) {
				System.out.println(ev.toString());
				res.add(ev);
			}
			return res;
		}

		public Vector<Bet> getBetsByQuestion(Question q) {
			Vector<Bet> res = new Vector<Bet>();
			TypedQuery<Bet> query = db.createQuery("SELECT bet FROM Bet bet WHERE bet.question==?1", Bet.class);
			query.setParameter(1, q);
			List<Bet> bets = query.getResultList();
			for (Bet b : bets) {
				System.out.println(b.toString());
				res.add(b);
			}
			return res;
		}

		public void markEventAsFinished(Event e) {
			db.getTransaction().begin();
//			Event ev = new Event(description, date);
//			db.persist(ev);
			e.setFinished(true);
			db.getTransaction().commit();
		}

		public Transaction createTransaction(float amount, Date date, String description, Account acc) {
			db.getTransaction().begin();
			Transaction trans = new Transaction(amount, date, description, acc);
			db.persist(trans); 
			db.getTransaction().commit();
			
			return trans;
		}

		public Vector<Transaction> getTransactions(Account currentUser) {
			System.out.println(">> DataAccess: getTransactions");
			Vector<Transaction> trans = new Vector<Transaction>();
			TypedQuery<Transaction> query = db.createQuery("SELECT trans FROM Transaction trans WHERE trans.account=?1", Transaction.class);
			query.setParameter(1, currentUser);
			List<Transaction> transactions = query.getResultList();
			for (Transaction t : transactions) {
//				System.out.println(ev.toString());
				trans.add(t);
			}
			return trans;
		}

		public boolean updateQuestionAnswer(Question question, boolean ans) {
			TypedQuery<Question> query = db.createQuery("SELECT q FROM Question q WHERE q.questionNumber=?1", Question.class);
			query.setParameter(1, question.getQuestionNumber());
			Question q = query.getSingleResult();
			db.getTransaction().begin();
			q.setCorrect(ans);
			System.out.println(q.toString() + "set" + ans);
			db.getTransaction().commit();
			return true;
		}
		
		public boolean updateQuestionAnswer2(Question question, String ans) {
			TypedQuery<Question> query = db.createQuery("SELECT q FROM Question q WHERE q.questionNumber=?1", Question.class);
			query.setParameter(1, question.getQuestionNumber());
			Question q = query.getSingleResult();
			db.getTransaction().begin();
			q.setCorrect1(ans);
			System.out.println(q.toString() + "set" + ans);
			db.getTransaction().commit();
			return true;
		}
		
		

		public boolean updateEventFinished(Event event, boolean ans) {
			Event ev = db.find(Event.class, event);
			db.getTransaction().begin();

			ev.setFinished(ans);
			db.getTransaction().commit();
			return true;
		}

		public Event getEventFromQuestion(Question question) {
			Event ev = null;
			db.getTransaction().begin();
			Query q1 = db.createQuery("Select q.event FROM Question "
					+ "As q WHERE q.questionNumber == " + question.getQuestionNumber());
			ev = (Event) q1.getSingleResult();
			db.getTransaction().commit();
			return ev;
		}

}
