package com.averollc.backendreportingexericse.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Parent response class.
 *
 * @author Sambhav D Sethia
 * @version 1.0
 * @since 9/12/2018
 */
public class ReportingAttributes
{
    private final String report;
    private final String timeInterval;

    public ReportingAttributes(final String report, final String timeInterval)
    {
        super();
        this.report = report;
        this.timeInterval = timeInterval;
    }

    public String getReport()
    {
        return report;
    }

    public String getTimeInterval()
    {
        return timeInterval;
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper(this).add("report", report).add("timeInterval", timeInterval).toString();
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(report, timeInterval);
    }

    @Override
    public boolean equals(final Object object)
    {
        if (object instanceof ReportingAttributes) {
            final ReportingAttributes that = (ReportingAttributes) object;
            return Objects.equal(report, that.report) && Objects.equal(timeInterval, that.timeInterval);
        }
        return false;
    }

}
