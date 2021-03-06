/**
 * VibrateClientRpc.java (Vibrate)
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

package org.vaadin.alump.vibrate.gwt.client.share;

import com.vaadin.shared.communication.ClientRpc;

import java.util.List;

/**
 * ClientRpc of Vibrate
 */
public interface VibrateClientRpc extends ClientRpc {

    void vibrate(int millisecs);

    void vibratePattern(List<VibrateStep> steps);

    public static class VibrateStep {
        public boolean vibrate = true;
        public int millisecs = 100;
    }
}
