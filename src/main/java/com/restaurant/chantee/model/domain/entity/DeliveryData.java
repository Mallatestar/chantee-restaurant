package com.restaurant.chantee.model.domain.entity;

import java.io.Serializable;
import java.util.Objects;

public class DeliveryData implements Serializable {
    private int user_id;
    private String phone;
    private String address;

    public DeliveryData() {
    }

    @Override
    public String toString() {
        return "DeliveryData{" +
                ", user_id=" + user_id +
                ", phone='" + phone + '\'' +
                ", street='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryData that = (DeliveryData) o;
        return user_id == that.user_id && Objects.equals(phone, that.phone) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, phone, address);
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
