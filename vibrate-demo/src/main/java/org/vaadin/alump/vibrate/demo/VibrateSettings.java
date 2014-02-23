/**
 * VibrateSettings.java (Vibrate)
 *
 * Copyright 2013 Vaadin Ltd, Sami Viitanen <alump@vaadin.org>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.vaadin.alump.vibrate.demo;

import com.vaadin.server.BootstrapFragmentResponse;
import com.vaadin.server.BootstrapListener;
import com.vaadin.server.BootstrapPageResponse;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * BootstrapListener that injects required parts to HTML document given to client
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
