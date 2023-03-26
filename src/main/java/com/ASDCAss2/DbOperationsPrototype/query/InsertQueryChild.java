package com.ASDCAss2.DbOperationsPrototype.query;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class InsertQueryChild extends QueryParent {
	
	String insertTableName;

	@Override
	void printQueryDetails() {
		// Empty Method -> to demonstrate Rebellious Hierarchy
	}

	public String getInsertTableName() {
		return insertTableName;
	}

	public void setInsertTableName(String insertTableName) {
		this.insertTableName = insertTableName;
	}
	
	public void processInsertQuery(String insertQuery, String userName) {
		Scanner scanner = new Scanner(System.in);

        // Get the INSERT query from the user
//        System.out.print("Enter the INSERT query: ");
//        String insertQuery = scanner.nextLine();

        // Parse the query to get the table name and column values
        String[] tokens = insertQuery.split("\\s+");
        String tableName = tokens[2] + "_by_" + userName;
        String values = insertQuery.substring(insertQuery.indexOf("(") + 1, insertQuery.indexOf(")"));

        // Create an arraylist of column values
        ArrayList<String> columnValues = new ArrayList<String>();
        String[] valueTokens = values.split(",");
        for (String valueToken : valueTokens) {
            String columnValue = valueToken.trim();
            columnValues.add(columnValue);
        }

        // Write the column values to the file
        try {
            FileWriter fileWriter = new FileWriter(tableName + ".txt", true);
            fileWriter.write("\n");
            for (String columnValue : columnValues) {
                fileWriter.write(columnValue + "-");
            }
            fileWriter.close();
            System.out.println("Data inserted successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while inserting the data.");
            e.printStackTrace();
        }

        scanner.close();
	}
	

}
