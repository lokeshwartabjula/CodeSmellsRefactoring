package com.ASDCAss2.DbOperationsPrototype.query;

public class QueryChild extends QueryParent {

	@Override
	void printQueryDetails() {
		String queryDetails = getQueryDetails(
				this.getQuery(),
				this.getWrittenBy(),
				this.getDatabaseName(),
				this.getQueryType(),true,true);
		System.out.println(queryDetails);
	}
	
	String getQueryDetails(String query, String writtenBy, String databaseName, String queryType, Boolean appendMode, Boolean exitMode ) {
		return appendMode? ("Query : "+query+
				"Written By: "+writtenBy+
				"Database Name: "+databaseName+
				"Query Type: "+queryType):(exitMode?null:"invalid parameters");
	}

}
