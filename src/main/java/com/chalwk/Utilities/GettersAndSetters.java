package com.chalwk.Utilities;

import com.chalwk.Interfaces.EmbedInterface;
import org.json.JSONObject;

import java.awt.*;

import static com.chalwk.Main.getSettings;

public class GettersAndSetters implements EmbedInterface {

    static JSONObject embedSettings;

    public GettersAndSetters(String type) {
        embedSettings = setEmbedTable(type);
    }

    @Override
    public JSONObject setEmbedTable(String type) {
        return getSettings().getJSONObject(type);
    }

    @Override
    public String getTitle() {
        return embedSettings.getString("title");
    }

    @Override
    public String getDescription() {
        return embedSettings.getString("description");
    }

    @Override
    public Color getColor() {
        return Color.decode(embedSettings.getString("color"));
    }

    @Override
    public String getFooter() {
        return embedSettings.getJSONObject("footer").getString("text");
    }

    @Override
    public String getImage() {
        return embedSettings.getString("image");
    }

    @Override
    public JSONObject getFields() {
        return embedSettings.getJSONObject("fields");
    }
}
