package com.averollc.backendreportingexericse.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Represent the timeFrame in a response.
 *
 * @author Sambhav D Sethia
 * @version 1.0
 * @since 9/12/2018
 */
public class TimeFrame
{

    private final String start;
    private final String end;

    public String getStart()
    {
        return start;
    }

    public String getEnd()
    {
        return end;
    }

    public TimeFrame(final String start, final String end)
    {
        this.start = start;
        this.end = end;
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(start, end);
    }

    @Override
    public boolean equals(final Object object)
    {
        if (object instanceof TimeFrame) {
            final TimeFrame that = (TimeFrame) object;
            return Objects.equal(start, that.start) && Objects.equal(end, that.end);
        }
        return false;
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper(this).add("start", start).add("end", end).toString();
    }

}
