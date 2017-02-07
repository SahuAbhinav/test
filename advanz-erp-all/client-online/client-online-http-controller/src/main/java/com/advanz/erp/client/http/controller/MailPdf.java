package com.advanz.erp.client.http.controller;
       

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailPdf extends Thread{
	
   private static final String SMTP_HOST_NAME = "smtp.gmail.com";
   private static final String SMTP_PORT = "465";
   private static final String emailMsgTxt = "Test mail snt by nail dhakad";
   private static final String emailSubjectTxt = "Test mail!!";
   private static final String emailFromAddress = "ltsjasperserver@gmail.com";
   private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
   private static final String[] sendTo = { "anildhkd26@gmail.com"};
   private static final String filename = "sample.pdf";
   
   private String file = "c:/sample.pdf";
   private File pdfFile=null; 
   public MailPdf(){
	   
   }
 public MailPdf(File pdfFile,String file){
	 super();
		this.pdfFile = pdfFile;
		this.file = file;
		
   }
   
   
   public MailPdf(String file) {
	super();
	this.file = file;
    }

/*public static void main(String args[]) throws Exception {

       //Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

       new MailWithAttachment().sendSSLMessage(sendTo, emailSubjectTxt,emailMsgTxt, emailFromAddress);
       System.out.println("Sucessfully Sent mail to All Users");
      }
   */
   public void send(String sendTo) throws Exception{
	  // Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

	   String[] sendToArray  =new String[1];
	   sendToArray[0]=sendTo;
       sendSSLMessage(sendToArray, emailSubjectTxt,emailMsgTxt, emailFromAddress);
   }
   
   public void sendSSLMessage(String sendTo) throws Exception{
	   String[] sendToArray  =new String[1];
	   sendToArray[0]=sendTo;
	   sendSSLMessage(sendToArray, this.emailSubjectTxt,
			   this.emailMsgTxt, this.emailFromAddress); 
   }
   public void sendSSLMessage() throws Exception{
	   sendSSLMessage(sendTo, this.emailSubjectTxt,
			   this.emailMsgTxt, this.emailFromAddress); 
   }
   

   public void sendSSLMessage(String recipients[], String subject,
           String message, String from) throws MessagingException {
       boolean debug = false;
       
       Properties props = new Properties();
       props.put("mail.smtp.host", SMTP_HOST_NAME);
       props.put("mail.smtp.auth", "true");
       props.put("mail.debug", "true");
       props.put("mail.smtp.port", SMTP_PORT);
       props.put("mail.smtp.socketFactory.port", SMTP_PORT);
       props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
       props.put("mail.smtp.socketFactory.fallback", "false");

       Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {

                   protected PasswordAuthentication getPasswordAuthentication() {
                	   return new PasswordAuthentication("scf@shreecera.in", "erp@1234");
                   }
               });

       session.setDebug(debug);
       
       //String msgText1 = "This is message from Raunak to Raunak.\nHere's line two.";
	  //String msgText2 = "This is the text in the message attachment.";
	    
       Message msg = new MimeMessage(session);
       InternetAddress addressFrom = new InternetAddress(from);
       msg.setFrom(addressFrom);
try{
       InternetAddress[] addressTo = new InternetAddress[recipients.length];
      for (int i = 0; i < recipients.length; i++) {
       addressTo[i] = new InternetAddress(recipients[i]);
       }
       msg.setRecipients(Message.RecipientType.TO, addressTo);
}catch (Exception e) {
	// TODO: handle exception
}
       
       String [] bccEmailIds=new String[2]; 
       
    try{
    	
    	
       //TO add BCC emailIds
       bccEmailIds[0]="barkallesantosh@gmail.com";
      bccEmailIds[1]="anil.dkd@101bi.com";
       InternetAddress[] bcc = new InternetAddress[2];
       for (int i = 0; i < bccEmailIds.length; i++)
       {
    	bcc[i] = new InternetAddress(bccEmailIds[i]);
       }
       msg.addRecipients(Message.RecipientType.BCC, bcc);
       }catch (Exception e) {
    	   e.printStackTrace();
   	}
       
      try{
       //TO add cc emailIds
       String [] ccEmailId=new String[1]; 
       ccEmailId[0]="info@shreecera.com";
      // ccEmailId[0]="anildhakad26@yahoo.in";
       InternetAddress[] cc = new InternetAddress[1];
       cc[0]=new InternetAddress(ccEmailId[0]);
       msg.addRecipients(RecipientType.CC,cc);
       }catch (Exception e) {
	}
     // Setting the Subject and Content Type
       msg.setSubject(subject);
    // create and fill the first message part
	    MimeBodyPart messagePart= new MimeBodyPart();
	    messagePart.setText(message);

	    //create and fill the second message part
	    //MimeBodyPart mbp2 = new MimeBodyPart();
	    // Use setText(text, charset), to show it off !
	    MimeBodyPart attachmentPart = new MimeBodyPart();
        FileDataSource fileDataSource = new FileDataSource(pdfFile) {
            @Override
            public String getContentType() {
                return "application/octet-stream";
            }
        };
       attachmentPart.setDataHandler(new DataHandler(fileDataSource));
       attachmentPart.setFileName(file);
	    //mbp2.setText(msgText2, "us-ascii");

	    // create the Multipart and its parts to it
		Multipart mp = new MimeMultipart();

	    mp.addBodyPart(messagePart);
	    mp.addBodyPart(attachmentPart);

	    // add the Multipart to the message
	    msg.setContent(mp);
       //msg.setContent(message, "text/plain");
       Transport.send(msg);
   }
}