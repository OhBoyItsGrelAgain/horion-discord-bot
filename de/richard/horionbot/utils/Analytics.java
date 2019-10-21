package de.richard.horionbot.utils;

import org.json.JSONObject;

class Analytics {

    private static JSONObject Analytics;

    static int getCurrentUserCount() {
        update();
        return Analytics.getInt("unique_users");
    }

    private static void update() {
        Analytics = BotUtil.retrieveJsonObject("http://hbob.ml/horion/status.php");
    }
}
