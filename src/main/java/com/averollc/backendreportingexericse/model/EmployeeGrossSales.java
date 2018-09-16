package com.averollc.backendreportingexericse.model;

import java.util.ArrayList;

public class EmployeeGrossSales extends ReportingAttributes{

		private ArrayList<EmployeeData> employeeData;
		
		public EmployeeGrossSales(String report, String timeInterval, ArrayList<EmployeeData> employeeData) {
			super(report,timeInterval);
			this.employeeData = employeeData;
		}
		
		public ArrayList<EmployeeData> getEmployeeData() {
			return employeeData;
		}

}