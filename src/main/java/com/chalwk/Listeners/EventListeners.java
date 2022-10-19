// Copyright (c) 2022, Jericho Crosby <jericho.crosby227@gmail.com>

package com.chalwk.Listeners;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.HashMap;

import static com.chalwk.Utilities.Members.getVotes;

public class EventListeners extends ListenerAdapter {

    public static HashMap<String, String> member_votes = new HashMap<>();


    @Override
    public void onGuildReady(@Nonnull GuildReadyEvent event) {
        System.out.println("Guild ready: " + event.getGuild().getName());
        System.out.println("Bot name: " + event.getJDA().getSelfUser().getName());
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {

        Member member = event.getMember();
        String id = member.getId();

        if (member_votes.containsKey(id)) {
            event.reply("You have already voted!").setEphemeral(true).queue();
            return;
        }

        // Get the votes:
        HashMap votes = getVotes();

        // Get the button ID (member ID):
        String buttonID = event.getComponentId();

        // Get the button label (member name):
        String buttonLabel = event.getComponent().getLabel();

        if (votes.containsKey(buttonID)) {

            int value = (int) votes.get(buttonID);
            value++;

            votes.put(buttonID, value);

            String newLabel = buttonLabel.replaceAll("\\(\\d+\\)", "(" + value + ")");
            event.editButton(event.getComponent().withLabel(newLabel)).queue();

            member_votes.put(id, buttonLabel);
        }
    }
}