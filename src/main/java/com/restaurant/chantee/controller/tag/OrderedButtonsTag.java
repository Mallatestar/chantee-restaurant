package com.restaurant.chantee.controller.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class OrderedButtonsTag extends TagSupport {
    private int order_id;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.print("<form action=\"" + pageContext.getServletContext().getContextPath() + "/Servlet\" method=\"post\">\n" +
                    "          <input type=\"hidden\" name=\"command\" value=\"acceptOrderCommand\">\"\n" +
                    "          <input type=\"hidden\" name=\"orderId\" value=" + order_id + ">\"\n" +
                    "          <button class=\"btn btn-primary btn-md my-0 p\" type=\"submit\">Accept order</button>\"\n" +
                    "      </form>\n" +
                    "      <form action=\""+ pageContext.getServletContext().getContextPath() +"/Servlet\" method=\"post\">\"\n" +
                    "          <input type=\"hidden\" name=\"command\" value=\"discardOrderCommand\">\"\n" +
                    "          <input type=\"hidden\" name=\"orderId\" value="+ order_id +">\"\n" +
                    "          <button class=\"btn btn-primary btn-md my-0 p\" type=\"submit\">Discard order</button>\"\n" +
                    "      </form>\n");
        } catch (IOException e) {
            throw new JspException();
        }
        return SKIP_BODY;
    }
}
