package org.vaadin.alump.vibrate.demo;

import com.vaadin.server.BootstrapFragmentResponse;
import com.vaadin.server.BootstrapListener;
import com.vaadin.server.BootstrapPageResponse;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created by alump on 23/02/14.
 */
public class VibrateSettings implements BootstrapListener {
    @Override
    public void modifyBootstrapFragment(BootstrapFragmentResponse response) {
        // nada
    }

    @Override
    public void modifyBootstrapPage(BootstrapPageResponse response) {
        Document doc = response.getDocument();
        Element head = doc.getElementsByTag("head").get(0);

        // Inject viewport meta tag
        Element element = doc.createElement("meta");
        element.attr("name", "viewport");
        element.attr("content", "width=320, user-scalable=no");
        head.appendChild(element);
    }
}
