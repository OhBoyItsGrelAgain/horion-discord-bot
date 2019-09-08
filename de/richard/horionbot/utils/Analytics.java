package de.richard.horionbot.utils;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

class Analytics {

    private static JSONObject Analytics;

    static int getCurrentUserCount() {
        update();
        return Analytics.getInt("unique_users");
    }

    private static void update() {
        try {
            InputStream is = new URL("http://hbob.ml/horion/status.php").openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            String jsonText = sb.toString();
            Analytics = new JSONObject(jsonText);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
