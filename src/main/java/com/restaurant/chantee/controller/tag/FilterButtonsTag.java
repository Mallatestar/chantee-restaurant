package com.restaurant.chantee.controller.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

/**
 * Custom JSP tag which generate filter category button on category-page.jsp
 */
public class FilterButtonsTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        String localeName = (String) pageContext.getSession().getAttribute("locale");
        Locale locale = new Locale(localeName);
        ResourceBundle bundle = ResourceBundle.getBundle("locale", locale);
        Map<String, String> categories = new TreeMap<>();
        categories.put(bundle.getString("index_category_cake"),"cakes");
        categories.put(bundle.getString("index_category_drink"), "drinks");
        categories.put(bundle.getString("index_category_tarts"), "tarts");
        categories.put(bundle.getString("index_category_eklers"), "eklers");
        try {
            for (String s : categories.keySet()) {
                out.print("<div class=\"col\">\n" +
                        "        <form action=\"" + pageContext.getServletContext().getContextPath() +"/Servlet\" method=\"post\">\n" +
                        "            <input type=\"hidden\" name=\"command\" value=\"generateProductList\">\n" +
                        "            <input type=\"hidden\" name=\"category\" value=\""+ categories.get(s) +"\">\n" +
                        "            <input type=\"hidden\" name=\"sortParam\" value=\""+ pageContext.getSession().getAttribute("sortParam") +"\">\n" +
                        "            <button class=\"btn btn-primary btn-md my-0 p\" type=\"submit\">" +
                                                s +
                        "              </button>\n" +
                        "        </form>\n" +
                        "    </div>");
            }
        } catch (IOException e) {
            throw new JspException();
        }
        return SKIP_BODY;
    }
}
