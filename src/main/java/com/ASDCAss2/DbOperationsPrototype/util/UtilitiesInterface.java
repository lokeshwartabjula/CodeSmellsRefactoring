package com.ASDCAss2.DbOperationsPrototype.util;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.ASDCAss2.DbOperationsPrototype.RecordsContainer;



public interface UtilitiesInterface {
	
	RecordsContainer readRecordsFromFile(String fileName) throws FileNotFoundException;
	
	void printRecordsToFile(RecordsContainer recordsContainer, String fileName);
	
	public void updateRecordInFile(FileUpdateInfo fileUpdateInfo) throws IOException;
}
