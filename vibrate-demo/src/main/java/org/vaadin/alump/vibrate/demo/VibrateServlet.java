package org.vaadin.alump.vibrate.demo;

import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.VaadinServlet;

import javax.servlet.ServletException;

public class VibrateServlet extends VaadinServlet {

    private final VibrateSettings settings = new VibrateSettings();

    @Override
    protected void servletInitialized() throws ServletException {
        super.servletInitialized();

        getService().addSessionInitListener(new SessionInitListener() {
            @Override
            public void sessionInit(SessionInitEvent event) {
                event.getSession().addBootstrapListener(settings);
            }
        });
    }
}