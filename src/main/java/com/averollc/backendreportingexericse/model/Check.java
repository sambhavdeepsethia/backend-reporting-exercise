package com.averollc.backendreportingexericse.model;

import java.time.ZonedDateTime;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Represents a Check
 *
 * @author Sambhav D Sethia
 * @version 1.0
 * @since 9/12/2018
 */
public class Check
{
    private String id;
    private String business_id;
    private String employee_id;
    private String name;
    private boolean closed;
    private ZonedDateTime closed_at;
    private ZonedDateTime updated_at;
    private ZonedDateTime created_at;

    public Check()
    {
        super();
    }

    public Check(final String id, final String business_id, final String employee_id, final String name, final boolean closed, final ZonedDateTime closed_at,
        final ZonedDateTime updated_at, final ZonedDateTime created_at)
    {
        this.id = id;
        this.business_id = business_id;
        this.employee_id = employee_id;
        this.name = name;
        this.closed = closed;
        this.closed_at = closed_at;
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

    public boolean isClosed()
    {
        return closed;
    }

    public ZonedDateTime getClosed_at()
    {
        return closed_at;
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
            .add("closed", closed) //$NON-NLS-1$
            .add("closed_at", closed_at) //$NON-NLS-1$
            .add("updated_at", updated_at) //$NON-NLS-1$
            .add("created_at", created_at) //$NON-NLS-1$
            .toString();
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(id, business_id, employee_id, name, closed, closed_at, updated_at, created_at);
    }

    @Override
    public boolean equals(final Object object)
    {
        if (object instanceof Check) {
            final Check that = (Check) object;
            return Objects.equal(id, that.id) && Objects.equal(business_id, that.business_id) && Objects.equal(employee_id, that.employee_id)
                && Objects.equal(name, that.name) && (closed == that.closed) && Objects.equal(closed_at, that.closed_at)
                && Objects.equal(updated_at, that.updated_at) && Objects.equal(created_at, that.created_at);
        }
        return false;
    }

}
