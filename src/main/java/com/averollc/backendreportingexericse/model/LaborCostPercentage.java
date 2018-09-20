package com.averollc.backendreportingexericse.model;

import java.util.List;

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
}
