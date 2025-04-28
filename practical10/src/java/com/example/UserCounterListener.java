package com.example;

import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class UserCounterListener implements HttpSessionListener {

    private static final int MAX_USERS = 3; 
    private static AtomicInteger activeUsers = new AtomicInteger(0);

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        int count = activeUsers.incrementAndGet();
        ServletContext context = event.getSession().getServletContext();
        context.setAttribute("activeUsers", count);
        System.out.println("Session Created. Active Users: " + count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        int count = activeUsers.decrementAndGet();
        ServletContext context = event.getSession().getServletContext();
        context.setAttribute("activeUsers", count);
        System.out.println("Session Destroyed. Active Users: " + count);
    }
}
