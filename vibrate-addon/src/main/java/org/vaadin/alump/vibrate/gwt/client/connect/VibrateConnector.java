/**
 * VibrateConnector.java (Vibrate)
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

package org.vaadin.alump.vibrate.gwt.client.connect;

import com.google.gwt.core.client.Scheduler;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.shared.ui.Connect;
import org.vaadin.alump.vibrate.gwt.client.share.VibrateClientRpc;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Connect(org.vaadin.alump.vibrate.Vibrate.class)
public class VibrateConnector extends AbstractExtensionConnector {

    private static final boolean IS_SUPPORTED = isSupported();

    private final VibrateClientRpc clientRpc = new VibrateClientRpc() {

        @Override
        public void vibrate(int millisecs) {
            if(IS_SUPPORTED) {
                VibrateConnector.vibrate(millisecs);
            }
        }

        @Override
        public void vibratePattern(List<VibrateStep> steps) {
            if(IS_SUPPORTED) {
                vibrateStep(new LinkedList<VibrateStep>(steps));
            }
        }
    };

    protected void init() {
        super.init();

        registerRpc(VibrateClientRpc.class, clientRpc);
    }

    @Override
    protected void extend(ServerConnector serverConnector) {
        // Nothing to do
    }

    /**
     * Check if Vibrate API is supported by client
     * @return true if supported
     */
    private static native boolean isSupported()
    /*-{
        if(navigator.vibrate) {
            return true;
        } else {
            return false;
        }
    }-*/;

    /**
     * Call native
     * @param millisecs
     */
    private static native void vibrate(int millisecs)
    /*-{
        if(millisecs < 0) {
            navigator.vibrate();
        } else {
            navigator.vibrate(millisecs);
        }
    }-*/;

    private void vibrateStep(final Queue<VibrateClientRpc.VibrateStep> steps) {
        VibrateClientRpc.VibrateStep next = steps.remove();
        if(next.vibrate) {
            vibrate(next.millisecs);
        }
        if(!steps.isEmpty()) {
            Scheduler.get().scheduleFixedDelay(new Scheduler.RepeatingCommand() {
                @Override
                public boolean execute() {
                    vibrateStep(steps);
                    return false;
                }
            }, next.millisecs);
        }
    }

}
