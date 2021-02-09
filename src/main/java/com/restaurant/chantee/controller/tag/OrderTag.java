package com.restaurant.chantee.controller.tag;

import com.restaurant.chantee.model.domain.entity.ShoppingCart;

import javax.servlet.jsp.tagext.TagSupport;

public class OrderTag extends TagSupport {

    private int id;
    private ShoppingCart cart;
    private String dateTime;
    private String delivery_address;
    private String comment;
}
