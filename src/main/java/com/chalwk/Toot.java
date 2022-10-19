package com.chalwk;

import com.chalwk.Utilities.FartEmbed;
import com.chalwk.Utilities.Members;
import com.chalwk.Utilities.NewTimer;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.interactions.components.ItemComponent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.json.JSONObject;

import java.util.*;

import static com.chalwk.Main.getJDA;
import static com.chalwk.Main.getSettings;

public class Toot {

    public static HashMap votes;

    public static String embedID;
    static JDA jda = getJDA();
    static JSONObject settings = getSettings();
    static String channelID = settings.getString("fart_channel");
    static int candidates = settings.getInt("number_of_candidates");

    public static class Task extends TimerTask {

        @Override
        public void run() {

            // Get new embed from FartEmbed class:
            EmbedBuilder embed = new FartEmbed().newEmbed();

            // Get the JDA object:
            TextChannel channel = jda.getTextChannelById(channelID);

            // Create a new collection:
            Collection<ItemComponent> components = new ArrayList<>();

            // Get guild members:
            Member[] members = Members.getMembers();

            // Create hashmap that can be accessed outside this class:
            votes = Members.setVoteTable();

            // Add buttons:
            for (int i = 0; i < candidates; i++) {
                Member member = members[i];
                if (member != null) {

                    String id = member.getId();
                    String name = member.getEffectiveName();
                    Button button;

                    if (i == 0) {
                        button = Button.primary(id, name + " (0)");
                    } else if (i == 1) {
                        button = Button.secondary(id, name + " (0)");
                    } else if (i == 2) {
                        button = Button.success(id, name + " (0)");
                    } else if (i == 3) {
                        button = Button.danger(id, name + " (0)");
                    } else {
                        button = Button.secondary(id, name + " (0)");
                    }

                    votes.put(id, 0);
                    components.add(button);
                }
            }

            // Build and send embed message:
            assert channel != null;
            channel.sendMessageEmbeds(embed.build()).addActionRow(components).queue();

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    channel.retrieveMessageById(channel.getLatestMessageId()).queue(message -> {
                        embedID = message.getId();
                    });
                }
            }, 500);


            // Restart the timer:
            //NewTimer.fartTimer();
            NewTimer.voteTimer();
        }
    }
}