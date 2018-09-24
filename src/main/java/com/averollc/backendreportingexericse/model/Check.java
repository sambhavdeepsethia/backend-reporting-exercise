package com.averollc.backendreportingexericse.model;

import java.time.ZonedDateTime;

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
        return "Check [id=" + id + ", business_id=" + business_id + ", employee_id=" + employee_id + ", name=" + name + ", closed=" + closed + ", closed_at="
            + closed_at + ", updated_at=" + updated_at + ", created_at=" + created_at + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((business_id == null) ? 0 : business_id.hashCode());
        result = (prime * result) + (closed ? 1231 : 1237);
        result = (prime * result) + ((closed_at == null) ? 0 : closed_at.hashCode());
        result = (prime * result) + ((created_at == null) ? 0 : created_at.hashCode());
        result = (prime * result) + ((employee_id == null) ? 0 : employee_id.hashCode());
        result = (prime * result) + ((id == null) ? 0 : id.hashCode());
        result = (prime * result) + ((name == null) ? 0 : name.hashCode());
        result = (prime * result) + ((updated_at == null) ? 0 : updated_at.hashCode());
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
        final Check other = (Check) obj;
        if (business_id == null) {
            if (other.business_id != null) {
                return false;
            }
        }
        else if (!business_id.equals(other.business_id)) {
            return false;
        }
        if (closed != other.closed) {
            return false;
        }
        if (closed_at == null) {
            if (other.closed_at != null) {
                return false;
            }
        }
        else if (!closed_at.equals(other.closed_at)) {
            return false;
        }
        if (created_at == null) {
            if (other.created_at != null) {
                return false;
            }
        }
        else if (!created_at.equals(other.created_at)) {
            return false;
        }
        if (employee_id == null) {
            if (other.employee_id != null) {
                return false;
            }
        }
        else if (!employee_id.equals(other.employee_id)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        }
        else if (!id.equals(other.id)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        }
        else if (!name.equals(other.name)) {
            return false;
        }
        if (updated_at == null) {
            if (other.updated_at != null) {
                return false;
            }
        }
        else if (!updated_at.equals(other.updated_at)) {
            return false;
        }
        return true;
    }

}
