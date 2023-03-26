package com.ASDCAss2.DbOperationsPrototype.query;


public class UpdateQueryChild extends QueryParent {
	
	String updateTableName;
	String setValueList;

	@Override
	void printQueryDetails() {
		// TODO Auto-generated method stub

	}

	public String getUpdateTableName() {
		return updateTableName;
	}

	public void setUpdateTableName(String updateTableName) {
		this.updateTableName = updateTableName;
	}

	public String getSetValueList() {
		return setValueList;
	}

	public void setSetValueList(String setValueList) {
		this.setValueList = setValueList;
	}
	
	// Helper function to get the index of a column in a row of the file contents
    public static int getColumnIndex(String columnName, String firstLine) {
        String[] columnNames = firstLine.split("-");
        for (int i = 0; i < columnNames.length; i++) {
            if (columnNames[i].equalsIgnoreCase(columnName)) {
                return i;
            }
        }
        return -1;
    }

}
