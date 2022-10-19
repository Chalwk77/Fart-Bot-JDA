package com.chalwk.Utilities;

import com.chalwk.CalculateVotes;
import com.chalwk.Toot;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import static com.chalwk.Main.getSettings;

public class NewTimer {

    static JSONObject settings = getSettings();
    static JSONArray min_max = settings.getJSONArray("time_between_farts");
    static int time_to_vote = settings.getInt("time_to_vote");

    /**
     * Creates a new timer that will run the Toot class after a random amount of time.
     * The time is determined by the min and max values in the settings.json file.
     * The timer is cancelled after it runs.
     * The timer is then restarted with a new min/max time.
     * This is a recursive method.
     */
    public static void fartTimer() {

        Timer timer = new Timer();
        TimerTask task = new Toot.Task();

        int min = min_max.getInt(0);
        int max = min_max.getInt(1);
        int random = (int) (Math.random() * (max - min + 1) + min);

        timer.schedule(task, 1000L * random);
    }

    public static void voteTimer() {
        Timer timer = new Timer();
        TimerTask task = new CalculateVotes.Task();
        timer.schedule(task, time_to_vote * 1000L);
    }
}
