package com.ASDCAss2.DbOperationsPrototype.query;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public abstract class QueryParent {
	
	public String query;
	public String writtenBy;
	public String databaseName;
	public String queryType;
	

	
	void printQueryDetails() {
		System.out.println("Query : "+this.query+
				"Written By: "+this.writtenBy+
				"Database Name: "+this.databaseName+
				"Query Type: "+this.queryType);
	};



	public String getQuery() {
		return query;
	}



	public void setQuery(String query) {
		this.query = query;
	}



	public String getWrittenBy() {
		return writtenBy;
	}



	public void setWrittenBy(String writtenBy) {
		this.writtenBy = writtenBy;
	}



	public String getDatabaseName() {
		return databaseName;
	}



	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}



	public String getQueryType() {
		return queryType;
	}



	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	
public void processUpdateQuery(String updateQuery, String userName) {
		
		Scanner scanner = new Scanner(System.in);

        // Get the UPDATE query from the user
//        System.out.print("Enter the UPDATE query: ");
//        String updateQuery = scanner.nextLine();

        // Parse the query to get the table name, set values and where condition
        String[] tokens = updateQuery.split("\\s+");
        String tableName = tokens[1]+ "_by_" + userName;
        String setValues = updateQuery.substring(updateQuery.indexOf("SET") + 4, updateQuery.indexOf("WHERE")).trim();
        String whereCondition = updateQuery.substring(updateQuery.indexOf("WHERE") + 6).trim();

        // Create an arraylist of set values
        ArrayList<String> setValuesList = new ArrayList<String>();
        String[] setTokens = setValues.split(",");
        for (String setToken : setTokens) {
            String setValue = setToken.trim();
            setValuesList.add(setValue);
        }

        // Create a hashmap of where conditions
        String[] whereTokens = whereCondition.split("\\s+");
        String whereColumnName = whereTokens[0];
        String whereOperator = whereTokens[1];
        String whereValue = whereTokens[2];

        // Update the matching rows in the file
        try {
            File file = new File(tableName + ".txt");
            Scanner fileScanner = new Scanner(file);
            ArrayList<String> fileContents = new ArrayList<String>();
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] lineTokens = line.split("-");
                if (lineTokens[0].equalsIgnoreCase(whereValue)) {
                    for (String setValue : setValuesList) {
                        String[] setValueTokens = setValue.split("=");
                        String columnName = setValueTokens[0].trim();
                        String columnValue = setValueTokens[1].trim();
                        UpdateQueryChild updateQueryChild = new UpdateQueryChild();
                        int columnIndex =  UpdateQueryChild.getColumnIndex(columnName, fileContents.get(0));
                        lineTokens[columnIndex] = columnValue;
                    }
                }
                fileContents.add(String.join("-", lineTokens));
            }
            fileScanner.close();

            FileWriter fileWriter = new FileWriter(tableName + ".txt");
            for (String line : fileContents) {
                fileWriter.write(line + "\n");
            }
            fileWriter.close();
            System.out.println("Data updated successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("The specified file does not exist.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An error occurred while updating the data.");
            e.printStackTrace();
        }

        scanner.close();
		
	}


}
