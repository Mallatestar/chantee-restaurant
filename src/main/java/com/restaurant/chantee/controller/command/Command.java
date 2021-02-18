package com.restaurant.chantee.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface for Command pattern implementation
 */
public interface Command{

    String execute(HttpServletRequest request, HttpServletResponse response);
}
