package com.averollc.backendreportingexericse.model;

public class EmployeeData extends Data {
	
    private final String employee;
      
 	public EmployeeData(TimeFrame timeFrame, double value, String employee) {
		super(timeFrame, value);
		this.employee = employee;
	}

	@Override
    public String toString()
    {
        return "EmployeeData [timeFrame = "+this.getTimeFrame()+", value = "+this.getValue()+ ", employee = "+employee+"]";
    }
}
