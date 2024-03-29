package com.restaurant.chantee.controller.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Custom JSP tag which generate shopping cart element on cart.jsp
 */
public class CartElementTag extends TagSupport {
    private int productId;
    private String imgPath;
    private String title;
    private int price;
    private String description;
    private int quantity;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgPath() {
        return imgPath;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        String localeName = (String) pageContext.getSession().getAttribute("locale");
        Locale locale = new Locale(localeName);
        ResourceBundle bundle = ResourceBundle.getBundle("locale", locale);
        try {
            out.print("<div style=\"border: 1px solid\" class=\"row\">\n" +
                    "            <div class=\"col-md-6 \">\n" +
                    "                <img style=\"height: 200px;\" src=\"" + pageContext.getServletContext().getContextPath() + imgPath + "\" alt=\"#\">\n" +
                    "            </div>\n" +
                    "            <div class=\"col-md-6  container display-flex\">\n" +
                    "                <div class=\"p-4\">\n" +
                    "                    <div class=\"mb-3\">\n" +
                    "                        <span class=\"badge purple mr-1\">" + title + "</span>\n" +
                    "                    </div>\n" +
                    "                    <p class=\"lead\">\n" +
                    "                        <span class=\"mr-1\">" + price +"</span>\n" +
                    "                    </p>\n" +
                    "                    <form method=\"post\" action=\"" + pageContext.getServletContext().getContextPath() + "/Servlet\" class=\"d-flex justify-content-left\">\n" +
                    "                        <input name=\"productId\" type=\"hidden\" value=\"" + productId + "\">\n" +
                    "                        <input name=\"productQuantity\" type=\"number\" class=\"form-control\" value=\"" + quantity + "\" aria-label=\"Search\" style=\"width:100px;\" min=\"1\">\n" +
                    "                        <input type=\"hidden\" name=\"command\" value=\"changeQuantityCommand\">" +
                    "                        <button type=\"submit\" class=\"btn btn-primary btn-md my-0 p\">\n" +
                    "                            "+ bundle.getString("cart_change_button") +"\n" +
                    "                        </button>\n" +
                    "                    </form>\n" +
                    "                    <form method=\"post\" action=\"" + pageContext.getServletContext().getContextPath() + "/Servlet\">" +
                    "                        <input type=\"hidden\" name=\"command\" value=\"removeProductCommand\">" +
                    "                        <input name=\"productId\" type=\"hidden\" value=\"" + productId + "\">\n" +
                    "                        <button type=\"submit\" class=\"btn btn-primary btn-md my-0 p\">\n" +
                    "                            "+ bundle.getString("cart_remove") +"\n" +
                    "                        </button>\n" +
                                        "</form>" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "     </div>");
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }


}
