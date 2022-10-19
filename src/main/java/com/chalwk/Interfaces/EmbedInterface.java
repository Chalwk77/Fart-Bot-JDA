package com.chalwk.Interfaces;

import org.json.JSONObject;

import java.awt.*;

public interface EmbedInterface {

    JSONObject setEmbedTable(String type);

    String getTitle();

    String getDescription();

    Color getColor();

    String getFooter();

    String getImage();

    JSONObject getFields();
}