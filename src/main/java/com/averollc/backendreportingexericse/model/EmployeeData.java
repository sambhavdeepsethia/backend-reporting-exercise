package com.averollc.backendreportingexericse.model;

public class EmployeeData extends Data
{
    private final String employee;

    public EmployeeData(final TimeFrame timeFrame, final double value, final String employee)
    {
        super(timeFrame, value);
        this.employee = employee;
        System.out.println("this.employess: " + employee);
    }

    public String getEmployee()
    {
        return employee;
    }

    @Override
    public String toString()
    {
        return "EmployeeData [employee=" + employee + "]";
    }

}
