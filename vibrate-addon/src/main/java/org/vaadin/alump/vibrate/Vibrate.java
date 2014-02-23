package org.vaadin.alump.vibrate;

import com.vaadin.server.AbstractExtension;
import com.vaadin.server.Extension;
import com.vaadin.ui.UI;
import org.vaadin.alump.vibrate.gwt.client.share.VibrateClientRpc;

import java.util.LinkedList;
import java.util.List;

/**
 * Vibrate extension. Offers access to Vibrate API of client.
 */
public class Vibrate extends AbstractExtension {

    /**
     * Get instance of Vibrate
     * @return
     */
    public static Vibrate get() {
        for (Extension extension : UI.getCurrent().getExtensions()) {
            if(extension instanceof Vibrate) {
                return (Vibrate)extension;
            }
        }

        Vibrate vibrate = new Vibrate();
        vibrate.extend(UI.getCurrent());
        return vibrate;
    }

    /**
     * Vibrate Pattern
     */
    public static class Pattern {
        private final List<VibrateClientRpc.VibrateStep> steps = new LinkedList<VibrateClientRpc.VibrateStep>();

        public Pattern() {

        }

        /**
         * Adds vibrate step to pattern
         * @param millisecs Length of vibrate in milliseconds
         * @return Pattern (for piping commands)
         */
        public Pattern vibrate(int millisecs) {
            return add(millisecs, true);
        }

        /**
         * Adds delay step to pattern
         * @param millisecs Length of delay in milliseconds
         * @return Pattern (for piping command)
         */
        public Pattern delay(int millisecs) {
            return add(millisecs, false);
        }

        /**
         * Adds new step to pattern
         * @param millisecs Length of step
         * @param vibrate true if vibrate, false if delay
         * @return Pattern (for piping command)
         */
        public Pattern add(int millisecs, boolean vibrate) {
            VibrateClientRpc.VibrateStep step = new VibrateClientRpc.VibrateStep();
            step.millisecs = millisecs;
            step.vibrate = vibrate;
            steps.add(step);

            return this;
        }

        /**
         * Get steps in list
         * @return
         */
        protected List<VibrateClientRpc.VibrateStep> getSteps() {
            return steps;
        }
    }

    //public static void startVibrate() {
    //    get().requestVibrate(-1);
    //}

    /**
     * Vibrate given milliseconds
     * @param millisecs Milliseconds vibrated
     */
    public static void vibrate(int millisecs) {
        if(millisecs < 0) {
            throw new IllegalArgumentException("Negative milliseconds value " + millisecs + " not accepted");
        }
        get().requestVibrate(millisecs);
    }

    /**
     * Creates new pattern to be defined and given to vibrate()
     * @return
     */
    public static Pattern createPattern() {
        return new Pattern();
    }

    /**
     * Vibrate the given pattern
     * @param pattern Pattern vibrated
     */
    public static void vibrate(Pattern pattern) {
        if(!pattern.getSteps().isEmpty()) {
            get().requestVibrate(pattern.getSteps());
        }
    }

    /**
     * Stop vibrating
     */
    public static void stopVibrate() {
        get().requestVibrate(0);
    }

    /**
     * Request client to vibrate
     * @param millisecs
     */
    protected void requestVibrate(int millisecs) {
        getRpcProxy(VibrateClientRpc.class).vibrate(millisecs);
    }

    /**
     * Request client to vibrate
     * @param steps
     */
    protected void requestVibrate(List<VibrateClientRpc.VibrateStep> steps) {
        getRpcProxy(VibrateClientRpc.class).vibratePattern(steps);
    }
}