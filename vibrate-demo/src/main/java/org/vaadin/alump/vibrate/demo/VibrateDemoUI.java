/**
 * VibrateDemoUI.java (Vibrate)
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

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.alump.fancylayouts.FancyNotifications;
import org.vaadin.alump.vibrate.Vibrate;
import com.vaadin.annotations.Title;

@Title("Vibrate Demo")
@Theme("demo")
@Push
public class VibrateDemoUI extends UI {

    private FancyNotifications notifications;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        setContent(layout);

        notifications = new FancyNotifications();
        layout.addComponent(notifications);

        Label label = new Label("This demo works only with Android Chrome");
        layout.addComponent(label);

        Button singleButton = new Button("Single vibrate");
        singleButton.setWidth("100%");
        layout.addComponent(singleButton);
        singleButton.addClickListener(singleClickListener);

        Button patternButton = new Button("Pattern vibrate");
        patternButton.setWidth("100%");
        layout.addComponent(patternButton);
        patternButton.addClickListener(patternClickListener);

        Button stopButton = new Button("End vibrate");
        stopButton.setWidth("100%");
        layout.addComponent(stopButton);
        stopButton.addClickListener(stopClickListener);

        Button notificationButton = new Button("Delayed notification vibrate");
        notificationButton.setWidth("100%");
        layout.addComponent(notificationButton);
        notificationButton.addClickListener(notificationClickListener);

    }

    private Button.ClickListener singleClickListener = new Button.ClickListener() {

        @Override
        public void buttonClick(Button.ClickEvent clickEvent) {
            Vibrate.vibrate(500);
        }
    };

    private Button.ClickListener patternClickListener = new Button.ClickListener() {

        @Override
        public void buttonClick(Button.ClickEvent clickEvent) {
            Vibrate.vibrate(Vibrate.createPattern().vibrate(800).delay(400).vibrate(400).delay(200).vibrate(200).delay(100).vibrate(100));
        }
    };

    private Button.ClickListener stopClickListener = new Button.ClickListener() {

        @Override
        public void buttonClick(Button.ClickEvent clickEvent) {
            Vibrate.stopVibrate();
        }
    };

    private Button.ClickListener notificationClickListener = new Button.ClickListener() {

        @Override
        public void buttonClick(Button.ClickEvent clickEvent) {
            final int delay = (int)Math.ceil(Math.random() * 10000.0);
            Thread thread = new Thread(new DelayedNotification(delay));
            thread.start();
        }
    };

    private class DelayedNotification implements Runnable {

        private int delayMs = 0;

        public DelayedNotification(int delayMs) {
            this.delayMs = delayMs;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(delayMs);
                if(VibrateDemoUI.this.isAttached()) {
                    VibrateDemoUI.this.access(new Runnable() {
                        @Override
                        public void run() {
                            notifications.showNotification(null, "Hello Vibrating World");
                            Vibrate.vibrate(300);
                        }
                    });
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
}