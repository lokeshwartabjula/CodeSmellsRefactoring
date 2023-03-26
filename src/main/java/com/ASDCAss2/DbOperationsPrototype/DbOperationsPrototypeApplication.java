package com.ASDCAss2.DbOperationsPrototype;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;

import javax.xml.bind.DatatypeConverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ASDCAss2.DbOperationsPrototype.query.CreateQueryChild;
import com.ASDCAss2.DbOperationsPrototype.query.InsertQueryChild;
import com.ASDCAss2.DbOperationsPrototype.query.QueryChild;
import com.ASDCAss2.DbOperationsPrototype.query.QueryParent;
import com.ASDCAss2.DbOperationsPrototype.query.UpdateQueryChild;
import com.ASDCAss2.DbOperationsPrototype.util.FileUpdateInfo;
import com.ASDCAss2.DbOperationsPrototype.util.Utilities;
import com.ASDCAss2.DbOperationsPrototype.util.UtilitiesInterface;


@SpringBootApplication
public class DbOperationsPrototypeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbOperationsPrototypeApplication.class, args);
		
		Scanner inputScanner = new Scanner(System.in);
		UtilitiesInterface util = new Utilities();
		UserInfo userInfo = new UserInfo();
    	RecordsContainer recordsContainer = new RecordsContainer();

		
	    
	    System.out.println("########### WELCOME TO LOKI'S DBMS ###########");
	    System.out.println("Please press 1 or 2 based on the following options: ");
	    System.out.println("1. Sign In");
	    System.out.println("2. Sign Up");
	    
	    Integer authPageOptionsInput = inputScanner.nextInt();
	    inputScanner.nextLine();
		UserInfo foundUserInfo = new UserInfo();
	    
	    switch(authPageOptionsInput) {
	    case 1:
	    	System.out.println("Welcome to Sign In Section");
	    	System.out.println("Please enter your username: ");
	    	userInfo.setUserName(inputScanner.nextLine());
//	    	System.out.println("Please enter your password: ");
//	    	userInfo.setPassword(inputScanner.nextLine());
		    //read the current userNamePwd from file and load into recordsContainer
	    	try {
				 recordsContainer = util.readRecordsFromFile("newFileName.txt");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
	    	//check the existence
	    	Boolean recordExists = false;
	    	Boolean doesPasswordMatch = false;
	    	Integer recordNumber = 0;
	    	String matchedUserRecord = null;
	    	for(List<String> record : recordsContainer.getRecordsList()) {
	    		recordNumber++;
//	    		String[] rowData = matchedUserRecord.split("-");
	    		for(String cell : record) {
//	    			System.out.println(cell);
	    			if(userInfo.getUserName().equals(cell)) {
	    				recordExists = true;
	    	    		matchedUserRecord = String.join("-", record);
	    			}
	    		}
		
	    	}
	    	
	    	if(recordExists==false) {
				System.out.println("You are not an user in our database. Please sign up first before you sign in");
				System.exit(-1);
			}else { //RECORD EXISTS
				System.out.println("Please enter your password: ");
				
				
			    String myHash = convertToHash(inputScanner.nextLine());
		    	userInfo.setPassword(myHash);
		    	
				Scanner passwordScanner = new Scanner(matchedUserRecord);
				passwordScanner.useDelimiter("-");
				while(passwordScanner.hasNext()) {

					foundUserInfo.setUserId(Integer.valueOf(passwordScanner.next()));
					foundUserInfo.setFirstName(passwordScanner.next());
					foundUserInfo.setLastName(passwordScanner.next());
					foundUserInfo.setUserName(passwordScanner.next());
				   // String passwordHash = convertToHash(passwordScanner.next());

					foundUserInfo.setPassword(passwordScanner.next());
					foundUserInfo.setQuestion(passwordScanner.next());
					foundUserInfo.setAnswer(passwordScanner.next());
					foundUserInfo.setDatabaseName(passwordScanner.next());
					
					if(userInfo.getPassword().equals(foundUserInfo.getPassword()))
						doesPasswordMatch = true;
				}
		    	}
	    	if(doesPasswordMatch==false) {
				System.out.println("You are not authenticated to use the database. Please enter a valid password next time");
				System.exit(-1);
	    	}else {
	    		System.out.println("Password matched successfully! Please answer the following question to sign in");
	    		System.out.println(foundUserInfo.getQuestion());
	    		if(inputScanner.nextLine().equalsIgnoreCase(foundUserInfo.getAnswer())) {
	    			System.out.println("Congratulations! You have logged in successfully");
	    		}else {
	    			System.out.println("You have not answered your security question correctly, Please enter correct answer next time");
	    		}
	    		//store answer into userInfo object
	    		//compare the answer from userInfo object to the answer in the foundUserInfo object
	    		// then proceed for the second factor authentication
	    	}
	    	
	    	
	    	break;
	    case 2:
	    	System.out.println("Welcome to Sign Up Section");
	    	System.out.println("Please enter your firstName: ");
	    	userInfo.setFirstName(inputScanner.nextLine());
	    	System.out.println("Please enter your lastName: ");
	    	userInfo.setLastName(inputScanner.nextLine());
	    	System.out.println("Please enter your username: ");
	    	userInfo.setUserName(inputScanner.nextLine());
	    	System.out.println("Please enter your password: ");
		    String myHash = convertToHash(inputScanner.nextLine());

	    	userInfo.setPassword(myHash);
	    	System.out.println("Please enter your security question");
	    	userInfo.setQuestion(inputScanner.nextLine());
	    	System.out.println("Please enter your security answer");
	    	userInfo.setAnswer(inputScanner.nextLine());
	    	
	    	//SIGN UP PROCESS
		    //read the current userNamePwd from file and load into recordsContainer
	    	try {
				 recordsContainer = util.readRecordsFromFile("newFileName.txt");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
	    	
			//ADD THE NEW RECORD TAKEN FROM THE USER
	    	recordsContainer.addNewRecord(userInfo, recordsContainer);
			
			//WRITE THE UPDATED RECORDS BACK INTO THE FILE
			util.printRecordsToFile(recordsContainer, "newFileName.txt");

			System.out.println("You are registered successfully in our database");
	    	break;
	    }
	    
//	    QueryClass userQuery = new QueryClass();
//		QueryParsers queryParser = new QueryParsers();
	    
	    QueryChild userQuery = new QueryChild();
	    CreateQueryChild createQuery = new CreateQueryChild();
	    InsertQueryChild insertQuery = new InsertQueryChild();
	    UpdateQueryChild updateQuery = new UpdateQueryChild();
	    System.out.println("Please enter the query you want to execute:");
	    String queryToExecute = inputScanner.nextLine();
	    userQuery.setQuery(queryToExecute);
	    userQuery.setWrittenBy(userInfo.getFirstName());
	    
	    String queryArr[] = userQuery.getQuery().split(" ");
	    switch(queryArr[0]) {
	    case "create":
	    case "CREATE":
	    	if(queryArr[1].equals("database")) {
	    		userInfo.setDatabaseName(queryArr[2]);
	    		 //read the current userNamePwd from file and load into recordsContainer
		    	try {
					 recordsContainer = util.readRecordsFromFile("newFileName.txt");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
		    	Integer recordNumber = 0;
		    	String matchedUserRecord = null;
		    	for(List<String> record : recordsContainer.getRecordsList()) {
		    		recordNumber++;
		    		matchedUserRecord = String.join("-", record);
		    		String[] rowData = matchedUserRecord.split("-");
//		    		System.out.println("log: rowData[1] is"+rowData[1]);
//		    		System.out.println("log: rowData[7] is"+rowData[7]);
		    		if(rowData[3].equals(userInfo.getUserName())) {
		    			if(rowData[7].equalsIgnoreCase("DATABASE_NAME")) {
		    				rowData[7].replace("DATABASE_NAME", userInfo.getDatabaseName());
		    				String newRecord = String.join("-", rowData);
		    				FileUpdateInfo fileUpdateInfo = new FileUpdateInfo();
		    				fileUpdateInfo.setLineNo(recordNumber);
		    				fileUpdateInfo.setOldWord("DATABASE_NAME");
		    				fileUpdateInfo.setNewWord(userInfo.getDatabaseName());
		    				fileUpdateInfo.setUpdateStrategy(1);
		    				fileUpdateInfo.setFileName("newFileName.txt");
		    				
		    				try {
								util.updateRecordInFile(fileUpdateInfo);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		    				System.out.println("Database created successfully");
		    				
		    			}
		    			
		    			else {
		    				System.out.println("You have already created a database which is "+rowData[7]);
		    			}
		    		}
			
		    	}
		    	
	    	}else if(queryArr[1].equalsIgnoreCase("table")) {
	    		
	    		createQuery.processCreateTableQuery(queryToExecute, userInfo.getUserName());
	    		
	    	}else {
	    		System.out.println("this kind of create query is not supported in this dbms. Please try again");
	    		System.exit(-1);
	    	}
	    	break;
	    case "select":
	    	break;
	    case "update":
	    case "UPDATE":
	    	updateQuery.processUpdateQuery(queryToExecute, userInfo.getUserName());
	    	break;
	    case "insert":
	    case "INSERT":
	    	insertQuery.processInsertQuery(queryToExecute, userInfo.getUserName());
	    	break;
	    default :
	    	System.out.println("This query is currently not supported in our database management system. Please try a different query");
	    	System.exit(-1);
	    }
	    
	    

	}
	
	public static String convertToHash(String rawPassword) {
		
		String hashedPassword = null;
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    md.update(rawPassword.getBytes());
	    byte[] digest = md.digest();
	    hashedPassword = DatatypeConverter
	      .printHexBinary(digest).toUpperCase();
		
		return hashedPassword ;
	}

}
