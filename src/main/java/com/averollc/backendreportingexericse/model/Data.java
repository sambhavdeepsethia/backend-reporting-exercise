package com.averollc.backendreportingexericse.model;

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
        return "Data [timeFrame=" + timeFrame + ", value=" + value + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((timeFrame == null) ? 0 : timeFrame.hashCode());
        long temp;
        temp = Double.doubleToLongBits(value);
        result = (prime * result) + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Data other = (Data) obj;
        if (timeFrame == null) {
            if (other.timeFrame != null) {
                return false;
            }
        }
        else if (!timeFrame.equals(other.timeFrame)) {
            return false;
        }
        if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value)) {
            return false;
        }
        return true;
    }

}
