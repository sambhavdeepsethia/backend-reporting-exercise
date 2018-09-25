package com.averollc.backendreportingexericse.model;

import java.time.ZonedDateTime;

public class OrderedItem
{
    private String id;
    private String business_id;
    private String employee_id;
    private String check_id;
    private String item_id;
    private String name;
    private double cost;
    private double price;
    private boolean voided;
    private ZonedDateTime updated_at;
    private ZonedDateTime created_at;

    public OrderedItem()
    {}

    public OrderedItem(final String id, final String business_id, final String employee_id, final String check_id, final String item_id, final String name,
        final double cost, final double price, final boolean voided, final ZonedDateTime updated_at, final ZonedDateTime created_at)
    {
        this.id = id;
        this.business_id = business_id;
        this.employee_id = employee_id;
        this.check_id = check_id;
        this.item_id = item_id;
        this.name = name;
        this.cost = cost;
        this.price = price;
        this.voided = voided;
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

    public String getCheck_id()
    {
        return check_id;
    }

    public String getItem_id()
    {
        return item_id;
    }

    public String getName()
    {
        return name;
    }

    public double getCost()
    {
        return cost;
    }

    public double getPrice()
    {
        return price;
    }

    public boolean isVoided()
    {
        return voided;
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
        return "OrderedItem [id=" + id + ", business_id=" + business_id + ", employee_id=" + employee_id + ", check_id=" + check_id + ", item_id=" + item_id
            + ", name=" + name + ", cost=" + cost + ", price=" + price + ", voided=" + voided + ", updated_at=" + updated_at + ", created_at=" + created_at
            + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((business_id == null) ? 0 : business_id.hashCode());
        result = (prime * result) + ((check_id == null) ? 0 : check_id.hashCode());
        long temp;
        temp = Double.doubleToLongBits(cost);
        result = (prime * result) + (int) (temp ^ (temp >>> 32));
        result = (prime * result) + ((created_at == null) ? 0 : created_at.hashCode());
        result = (prime * result) + ((employee_id == null) ? 0 : employee_id.hashCode());
        result = (prime * result) + ((id == null) ? 0 : id.hashCode());
        result = (prime * result) + ((item_id == null) ? 0 : item_id.hashCode());
        result = (prime * result) + ((name == null) ? 0 : name.hashCode());
        temp = Double.doubleToLongBits(price);
        result = (prime * result) + (int) (temp ^ (temp >>> 32));
        result = (prime * result) + ((updated_at == null) ? 0 : updated_at.hashCode());
        result = (prime * result) + (voided ? 1231 : 1237);
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
        final OrderedItem other = (OrderedItem) obj;
        if (business_id == null) {
            if (other.business_id != null) {
                return false;
            }
        }
        else if (!business_id.equals(other.business_id)) {
            return false;
        }
        if (check_id == null) {
            if (other.check_id != null) {
                return false;
            }
        }
        else if (!check_id.equals(other.check_id)) {
            return false;
        }
        if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost)) {
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
        if (item_id == null) {
            if (other.item_id != null) {
                return false;
            }
        }
        else if (!item_id.equals(other.item_id)) {
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
        if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price)) {
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
        if (voided != other.voided) {
            return false;
        }
        return true;
    }

}
