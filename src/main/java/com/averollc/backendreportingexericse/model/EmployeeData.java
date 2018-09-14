package com.averollc.backendreportingexericse.model;

public class EmployeeData extends Data {
	
    private final String employee;
    
    
      
    public EmployeeData(String value, TimeFrame timeFrame, String employee) {
		super(value, timeFrame);
		this.employee = employee;
	}



	@Override
    public String toString()
    {
        return "ClassPojo [value = "+this.getValue()+", employee = "+employee+", timeFrame = "+this.getTimeFrame()+"]";
    }
}
