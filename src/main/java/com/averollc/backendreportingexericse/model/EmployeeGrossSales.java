package com.averollc.backendreportingexericse.model;

import java.util.List;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Represents EmployeeGrossSales response object
 * 
 * @author Sambhav D Sethia
 * @version 1.0
 * @since 9/12/2018
 */
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

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper(this).add("super", super.toString()).add("employeeData", employeeData) //$NON-NLS-2$
            .toString();
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(super.hashCode(), employeeData);
    }

    @Override
    public boolean equals(final Object object)
    {
        if (object instanceof EmployeeGrossSales) {
            if (!super.equals(object)) {
                return false;
            }
            final EmployeeGrossSales that = (EmployeeGrossSales) object;
            return Objects.equal(employeeData, that.employeeData);
        }
        return false;
    }

}