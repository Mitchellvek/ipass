package model;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;

public class Vraagstelling {
	private int ID;
	private String forname;
	private String lastname;
	private String vraag;


	public Vraagstelling(int iD, String forname, String lastname, String vraag) {
		super();
		ID = iD;
		this.forname = forname;
		this.lastname = lastname;
		this.vraag = vraag;
	}

	public void verstuurvraag() {
		System.out.println(ID + forname + lastname + vraag);
		System.out.println("disable lesssecureapps on gmail must be off!");
		
		
		String HOST = "smtp.gmail.com";
	    String USER = "mitchellvek@gmail.com";
	    String PASSWORD = "nextpassword";
	    String PORT = "465";
	    String FROM = "mitchellvek@gmail.com";
	    String TO = "mitchellvek@gmail.com";
	 
	    String STARTTLS = "true";
	    String AUTH = "true";
	    String DEBUG = "true";
	    String SOCKET_FACTORY = "javax.net.ssl.SSLSocketFactory";
	    String SUBJECT = "vraag van: " + forname + " " + lastname + " " + ID;
	    String TEXT = "vraag: " + vraag;
	 
	   
	        //Use Properties object to set environment properties
	        Properties props = new Properties();
	 
	        props.put("mail.smtp.host", HOST);
	        props.put("mail.smtp.port", PORT);
	        props.put("mail.smtp.user", USER);
	 
	        props.put("mail.smtp.auth", AUTH);
	        props.put("mail.smtp.starttls.enable", STARTTLS);
	        props.put("mail.smtp.debug", DEBUG);
	 
	        props.put("mail.smtp.socketFactory.port", PORT);
	        props.put("mail.smtp.socketFactory.class", SOCKET_FACTORY);
	        props.put("mail.smtp.socketFactory.fallback", "false");
	 
	        try {
	 
	            //Obtain the default mail session
	            Session session = Session.getDefaultInstance(props, null);
	            session.setDebug(true);
	 
	            //Construct the mail message
	            MimeMessage message = new MimeMessage(session);
	            message.setText(TEXT);
	            message.setSubject(SUBJECT);
	            message.setFrom(new InternetAddress(FROM));
	            message.addRecipient(RecipientType.TO, new InternetAddress(TO));
	            message.saveChanges();
	 
	            //Use Transport to deliver the message
	            Transport transport = session.getTransport("smtp");
	            transport.connect(HOST, USER, PASSWORD);
	            transport.sendMessage(message, message.getAllRecipients());
	            transport.close();
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 
	    }
	}



