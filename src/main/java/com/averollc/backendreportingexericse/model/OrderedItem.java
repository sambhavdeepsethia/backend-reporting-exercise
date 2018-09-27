package com.averollc.backendreportingexericse.model;

import java.time.ZonedDateTime;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Represents an OrderedItem
 *
 * @author Sambhav D Sethia
 * @version 1.0
 * @since 9/12/2018
 */
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
        return MoreObjects.toStringHelper(this).add("id", id).add("business_id", business_id).add("employee_id", employee_id).add("check_id", check_id)
            .add("item_id", item_id).add("name", name).add("cost", cost).add("price", price).add("voided", voided).add("updated_at", updated_at)
            .add("created_at", created_at).toString();
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(id, business_id, employee_id, check_id, item_id, name, cost, price, voided, updated_at, created_at);
    }

    @Override
    public boolean equals(final Object object)
    {
        if (object instanceof OrderedItem) {
            final OrderedItem that = (OrderedItem) object;
            return Objects.equal(id, that.id) && Objects.equal(business_id, that.business_id) && Objects.equal(employee_id, that.employee_id)
                && Objects.equal(check_id, that.check_id) && Objects.equal(item_id, that.item_id) && Objects.equal(name, that.name) && (cost == that.cost)
                && (price == that.price) && (voided == that.voided) && Objects.equal(updated_at, that.updated_at) && Objects.equal(created_at, that.created_at);
        }
        return false;
    }

}
