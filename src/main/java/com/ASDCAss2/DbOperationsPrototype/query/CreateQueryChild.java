package com.ASDCAss2.DbOperationsPrototype.query;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class CreateQueryChild extends QueryParent {
	
	String createTableName;

	@Override
	void printQueryDetails() {
		throw new UnsupportedOperationException("to demonstrate Rebellious Hierarchy Smell");
	}

	public String getCreateTableName() {
		return createTableName;
	}

	public void setCreateTableName(String createTableName) {
		this.createTableName = createTableName;
	}
	
	public void processCreateTableQuery(String createTableQuery, String userName) {
		Scanner scanner = new Scanner(System.in);

        // Get the CREATE TABLE query from the user
//        System.out.print("Enter the CREATE TABLE query: ");
//        String createTableQuery = scanner.nextLine();

        // Parse the query to get the table name and column names
        String[] tokens = createTableQuery.split("\\s+");
        String tableName = tokens[2] + "_by_" + userName;
        String columns = createTableQuery.substring(createTableQuery.indexOf("(") + 1, createTableQuery.indexOf(";")-1);

        // Create an arraylist of column names
        ArrayList<String> columnNames = new ArrayList<String>();
        String[] columnTokens = columns.split(",");
        for (String columnToken : columnTokens) {
            String columnName = columnToken.trim().split("\\s+")[0];
            columnNames.add(columnName);
        }

        // Create a file with the table name as the file name and write the column names to the file
        try {
            FileWriter fileWriter = new FileWriter(tableName + ".txt");
            for (String columnName : columnNames) {
                fileWriter.write(columnName + "-");
            }
            fileWriter.close();
            System.out.println("Table created successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while creating the table.");
            e.printStackTrace();
        }

        scanner.close();
    }
	

}
