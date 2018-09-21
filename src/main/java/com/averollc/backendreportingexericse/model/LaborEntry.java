package com.averollc.backendreportingexericse.model;

import java.time.ZonedDateTime;

public class LaborEntry
{
    private String id;
    private String business_id;
    private String employee_id;
    private String name;
    private ZonedDateTime clock_in;
    private ZonedDateTime clock_out;
    private double pay_rate;
    private ZonedDateTime updated_at;
    private ZonedDateTime created_at;

    public LaborEntry()
    {
        super();
    }

    public LaborEntry(final String id, final String business_id, final String employee_id, final String name, final ZonedDateTime clock_in,
        final ZonedDateTime clock_out, final double pay_rate, final ZonedDateTime updated_at, final ZonedDateTime created_at)
    {
        this.id = id;
        this.business_id = business_id;
        this.employee_id = employee_id;
        this.name = name;
        this.clock_in = clock_in;
        this.clock_out = clock_out;
        this.pay_rate = pay_rate;
        this.updated_at = updated_at;
        this.created_at = created_at;
    }

    public String getId()
    {
        return id;
    }

    public String getBusiness_id()
    {
        return business_id;
    }

    public String getEmployee_id()
    {
        return employee_id;
    }

    public String getName()
    {
        return name;
    }

    public ZonedDateTime getClock_in()
    {
        return clock_in;
    }

    public ZonedDateTime getClock_out()
    {
        return clock_out;
    }

    public double getPay_rate()
    {
        return pay_rate;
    }

    public ZonedDateTime getUpdated_at()
    {
        return updated_at;
    }

    public ZonedDateTime getCreated_at()
    {
        return created_at;
    }

    @Override
    public String toString()
    {
        return "LaborEntry [id=" + id + ", business_id=" + business_id + ", employee_id=" + employee_id + ", name=" + name + ", clock_in=" + clock_in
            + ", clock_out=" + clock_out + ", pay_rate=" + pay_rate + ", updated_at=" + updated_at + ", created_at=" + created_at + "]";
    }

}
