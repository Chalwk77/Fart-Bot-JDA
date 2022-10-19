package com.chalwk.Utilities;

import net.dv8tion.jda.api.EmbedBuilder;
import org.json.JSONObject;

public class FartEmbed {

    GettersAndSetters settings = new GettersAndSetters("fart_embed");

    public EmbedBuilder newEmbed() {

        EmbedBuilder embed = new EmbedBuilder();

        embed.setTitle(settings.getTitle());
        embed.setColor(settings.getColor());
        embed.setFooter(settings.getFooter());
        embed.setImage(settings.getImage());
        JSONObject fields = settings.getFields();

        for (String key : fields.keySet()) {

            JSONObject field = fields.getJSONObject(key);
            String name = field.getString("name");
            String value = field.getString("value");
            boolean inline = field.getBoolean("inline");
            embed.addField(name, value, inline);
        }

        return embed;
    }
}
