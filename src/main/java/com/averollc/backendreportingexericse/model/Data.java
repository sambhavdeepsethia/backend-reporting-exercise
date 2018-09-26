package com.averollc.backendreportingexericse.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Represent the data array of the response.
 *
 * @author Sambhav D Sethia
 * @version 1.0
 * @since 9/12/2018
 */
public class Data
{

    private final TimeFrame timeFrame;
    private final double value;

    public Data(final TimeFrame timeFrame, final double value)
    {
        this.timeFrame = timeFrame;
        this.value = value;
    }

    public TimeFrame getTimeFrame()
    {
        return timeFrame;
    }

    public double getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper(this).add("timeFrame", timeFrame) //$NON-NLS-1$
            .add("value", value) //$NON-NLS-1$
            .toString();
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(timeFrame, value);
    }

    @Override
    public boolean equals(final Object object)
    {
        if (object instanceof Data) {
            final Data that = (Data) object;
            return Objects.equal(timeFrame, that.timeFrame) && (value == that.value);
        }
        return false;
    }

}
