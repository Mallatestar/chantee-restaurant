package com.restaurant.chantee.controller.tag;

import com.restaurant.chantee.model.domain.entity.ShoppingCart;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class OrderTag extends TagSupport {
    private int order_id;
    private ShoppingCart cart;
    private String dateTime;
    private String delivery_address;
    private String phone;
    private String comment;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.print(
                    "                <div class=\"container\">\n" +
                    "                    Order ID: " + order_id +"<br>\n" +
                    "                    Phone number: "+ phone +"<br>\n" +
                    "                    Delivery address:<br>\n" +
                    "                   " + delivery_address + "<br>\n" +
                    "                    Product list:<br>\n" +
                    "                    <table>\n" +
                    "                        <tr>\n" +
                    "                            <td>Title</td>\n" +
                            "                    <td> </td>" +
                    "                            <td>Quantity</td>\n" +
                    "                        </tr>\n" +
                                                cart.generateProductList() +
                    "                    </table><br>\n" +
                    "                    Total price: " + cart.getTotalPrice() +"<br>\n" +
                    "                    Comment: " + comment + "<br>\n" +
                    "                    Date time: " + dateTime + "<br>\n" +
                    "                </div>\n");
        } catch (IOException e) {
            throw new JspException(e);
        }

        return SKIP_BODY;
    }


}
