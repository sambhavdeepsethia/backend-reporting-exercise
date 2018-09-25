package com.averollc.backendreportingexericse.model;

import java.util.List;

public class EmployeeGrossSales extends ReportingAttributes
{

    private final List<EmployeeData> employeeData;

    public EmployeeGrossSales(final String report, final String timeInterval, final List<EmployeeData> employeeData)
    {
        super(report, timeInterval);
        this.employeeData = employeeData;
    }

    public List<EmployeeData> getEmployeeData()
    {
        return employeeData;
    }

}