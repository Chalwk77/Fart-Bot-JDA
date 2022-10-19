package com.chalwk.Utilities;

import net.dv8tion.jda.api.EmbedBuilder;

public class ResultsEmbed {

    static GettersAndSetters settings = new GettersAndSetters("results_embed");

    public static EmbedBuilder newEmbed(String winner) {

        EmbedBuilder embed = new EmbedBuilder();

        String title = settings.getTitle();
        String description = settings.getDescription();

        description = description.replace("$member", winner);

        embed.setTitle(title);
        embed.setDescription(description);

        return embed;
    }
}
