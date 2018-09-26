package com.averollc.backendreportingexericse.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Represents EmployeeData, and is part of EmployeeGrossSales response.
 *
 * @author Sambhav D Sethia
 * @version 1.0
 * @since 9/12/2018
 */
public class EmployeeData extends Data
{
    private final String employee;

    public EmployeeData(final TimeFrame timeFrame, final double value, final String employee)
    {
        super(timeFrame, value);
        this.employee = employee;
    }

    public String getEmployee()
    {
        return employee;
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper(this).add("super", super.toString()).add("employee", employee) //$NON-NLS-2$
            .toString();
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(super.hashCode(), employee);
    }

    @Override
    public boolean equals(final Object object)
    {
        if (object instanceof EmployeeData) {
            if (!super.equals(object)) {
                return false;
            }
            final EmployeeData that = (EmployeeData) object;
            return Objects.equal(employee, that.employee);
        }
        return false;
    }

}
