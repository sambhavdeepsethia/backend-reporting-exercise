package com.averollc.backendreportingexericse.model;

import java.util.List;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Represents LaborCostPercentage response object
 *
 * @author Sambhav D Sethia
 * @version 1.0
 * @since 9/12/2018
 */
public class LaborCostPercentage extends ReportingAttributes
{

    private final List<Data> data;

    public LaborCostPercentage(final String report, final String timeInterval, final List<Data> data)
    {
        super(report, timeInterval);
        this.data = data;
    }

    public List<Data> getData()
    {
        return data;
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper(this).add("super", super.toString()).add("data", data) //$NON-NLS-2$
            .toString();
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(super.hashCode(), data);
    }

    @Override
    public boolean equals(final Object object)
    {
        if (object instanceof LaborCostPercentage) {
            if (!super.equals(object)) {
                return false;
            }
            final LaborCostPercentage that = (LaborCostPercentage) object;
            return Objects.equal(data, that.data);
        }
        return false;
    }

}
