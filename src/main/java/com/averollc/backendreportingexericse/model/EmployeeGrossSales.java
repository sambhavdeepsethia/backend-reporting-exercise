package com.averollc.backendreportingexericse.model;

public class EmployeeGrossSales extends ReportingAttributes{

		private EmployeeData[] employeeData;
		
		public EmployeeGrossSales(String report, String timeInterval, EmployeeData[] employeeData) {
			super(report,timeInterval);
			this.employeeData = employeeData;
		}
		
		public EmployeeData[] getEmployeeData() {
			return employeeData;
		}

}