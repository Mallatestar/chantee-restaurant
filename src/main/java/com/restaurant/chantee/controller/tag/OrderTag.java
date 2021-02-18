package com.restaurant.chantee.controller.tag;

import com.restaurant.chantee.model.domain.entity.ShoppingCart;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
/**
 * Custom JSP tag which generate order element on manager.jsp
 */
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
        String localeName = (String) pageContext.getSession().getAttribute("locale");
        Locale locale = new Locale(localeName);
        ResourceBundle bundle = ResourceBundle.getBundle("locale", locale);
        try {
            out.print("<table class=\"body-wrap\">\n" +
                    "    <tbody>\n" +
                    "        <tr>\n" +
                    "            <td></td>\n" +
                    "            <td class=\"container\" width=\"600\">\n" +
                    "                <div class=\"content\">\n" +
                    "                    <table class=\"main\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                    "                        <tbody>\n" +
                    "                            <tr>\n" +
                    "                                <td class=\"content-wrap aligncenter\">\n" +
                    "                                    <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                    "                                        <tbody>\n" +
                    "                                            <tr>\n" +
                    "                                                <td class=\"content-block\">\n" +
                    "                                                    <table class=\"invoice\">\n" +
                    "                                                        <tbody>\n" +
                    "                                                            <tr>\n" +
                    "                                                                <td>\n" +
                    "                                                                    "+ bundle.getString("manager_order_id") +" " + getOrder_id() + "<br>\n" + "<hr>" +
                    "                                                                    "+ bundle.getString("manager_phone") + " " + getPhone() +"<br>\n" + "<hr>" +
                    "                                                                    "+ bundle.getString("manager_da") +"<br>\n" +
                    "                                                                    " + getDelivery_address() + "<br>\n" + "<hr>" +
                    "                                                                    "+ bundle.getString("manager_comm") +"<br>\n" +
                    "                                                                    " + getComment() + "<br>\n" + "<hr>" +
                    "                                                                    "+ bundle.getString("manager_product_list") +"                                                                    \n" +
                    "                                                                </td>\n" +
                    "                                                            </tr>\n" +
                    "                                                            <tr>\n" +
                    "                                                                <td>\n" +
                    "                                                                    <table class=\"invoice-items\" cellpadding=\"0\"\n" +
                    "                                                                        cellspacing=\"0\">\n" +
                    "                                                                        <tbody>\n" +
                                                                                                cart.generateProductList() +
                    "                                                                        </tbody>\n" +
                    "                                                                    </table>\n" +
                    "                                                                </td>\n" +
                    "                                                            </tr>\n" +
                    "                                                            <tr>\n" +
                    "                                                                <td>\n" +
                    "                                                                    "+ bundle.getString("manager_total") +"" + cart.getTotalPrice() +" UAH<br>\n" +
                    "                                                                </td>\n" +
                    "                                                            </tr>\n" +
                    "                                                        </tbody>\n" +
                    "                                                    </table>\n" +
                    "                                                </td>\n" +
                    "                                            </tr>\n" +
                    "                                        </tbody>\n" +
                    "                                    </table>\n" +
                    "                                </td>\n" +
                    "                            </tr>\n" +
                    "                        </tbody>\n" +
                    "                    </table>\n" +
                    "                </div>\n" +
                    "            </td>\n" +
                    "            <td></td>\n" +
                    "        </tr>\n" +
                    "    </tbody>\n" +
                    "</table>");
        } catch (IOException e) {
            throw new JspException(e);
        }

        return SKIP_BODY;
    }


}
