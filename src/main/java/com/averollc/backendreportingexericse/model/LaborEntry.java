package com.averollc.backendreportingexericse.model;

import java.time.ZonedDateTime;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Represents a LaborEntry
 *
 * @author Sambhav D Sethia
 * @version 1.0
 * @since 9/12/2018
 */
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

    /**
     * @param id
     * @param business_id
     * @param employee_id
     * @param name
     * @param clock_in
     * @param clock_out
     * @param pay_rate
     * @param updated_at
     * @param created_at
     */
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
        return MoreObjects.toStringHelper(this).add("id", id) //$NON-NLS-1$
            .add("business_id", business_id) //$NON-NLS-1$
            .add("employee_id", employee_id) //$NON-NLS-1$
            .add("name", name) //$NON-NLS-1$
            .add("clock_in", clock_in) //$NON-NLS-1$
            .add("clock_out", clock_out) //$NON-NLS-1$
            .add("pay_rate", pay_rate) //$NON-NLS-1$
            .add("updated_at", updated_at) //$NON-NLS-1$
            .add("created_at", created_at) //$NON-NLS-1$
            .toString();
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(id, business_id, employee_id, name, clock_in, clock_out, pay_rate, updated_at, created_at);
    }

    @Override
    public boolean equals(final Object object)
    {
        if (object instanceof LaborEntry) {
            final LaborEntry that = (LaborEntry) object;
            return Objects.equal(id, that.id) && Objects.equal(business_id, that.business_id) && Objects.equal(employee_id, that.employee_id)
                && Objects.equal(name, that.name) && Objects.equal(clock_in, that.clock_in) && Objects.equal(clock_out, that.clock_out)
                && (pay_rate == that.pay_rate) && Objects.equal(updated_at, that.updated_at) && Objects.equal(created_at, that.created_at);
        }
        return false;
    }

}
