package com.restaurant.chantee.controller.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
/**
 * Custom JSP tag which generate product element on category-page.jsp
 */
public class ProductTag extends TagSupport {
    private String imgPath;
    private String title;
    private int price;
    private String description;

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

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        String localeName = (String) pageContext.getSession().getAttribute("locale");
        Locale locale = new Locale(localeName);
        ResourceBundle bundle = ResourceBundle.getBundle("locale", locale);

        try {
            out.print("<div class=\"row\">\n" +
                    "            <div class=\"col-md-6 md-4\">\n" +
                    "                <img src=\"" + imgPath + "\"\n" +
                    "                     alt=\"" + title + "\" class=\"img-fluid\" style=\"height: 300px;\">\n" +
                    "            </div>\n" +
                    "            <div class=\"col-md-6 md-4\">\n" +
                    "                <div class=\"p-4\">\n" +
                    "                    <div class=\"mb-3\">\n" +
                    "                        <span class=\"badge purple mr-1\">" + title + "</span>\n" +
                    "                    </div>\n" +
                    "                    <p class=\"lead\">\n" +
                    "                        <span class=\"mr-1\">"+ price + "</span>\n" +
                    "                    </p>\n" +
                    "                    <p class=\"lead font-weight-bold\">"+ bundle.getString("category_page_description") +"</p>\n" +
                    "                    <p>" + description + "</p>\n" +
                    "                    <form method=\"post\" action=\"" + pageContext.getServletContext().getContextPath() + "/Servlet\" class=\"d-flex justify-content-left\">\n" +
                                                "<input type=\"hidden\" name=\"command\" value=\"addToCartCommand\">" +
                    "                        <input name=\"productTitle\" type=\"hidden\" value=\"" + title + "\">\n" +
                    "                        <input name=\"productQuantity\" type=\"number\" class=\"form-control\" value=\"0\" min=\"1\" aria-label=\"Search\" style=\"width:100px;\">\n" +
                    "                        <button type=\"submit\" class=\"btn btn-primary btn-md my-0 p\">\n" +
                    "                            " + bundle.getString("category_page_add") +" <i class=\"fa fa-shopping-cart ml-1\"></i>\n" +
                    "                        </button>\n" +
                    "                    </form>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "        <hr>");
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
