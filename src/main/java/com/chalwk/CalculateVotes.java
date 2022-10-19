package com.chalwk;

import com.chalwk.Utilities.ResultsEmbed;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.util.HashMap;
import java.util.TimerTask;

import static com.chalwk.Listeners.EventListeners.member_votes;
import static com.chalwk.Main.getJDA;
import static com.chalwk.Toot.channelID;
import static com.chalwk.Toot.embedID;
import static com.chalwk.Utilities.Members.getVotes;

public class CalculateVotes {

    static JDA jda = getJDA();

    public static class Task extends TimerTask {

        @Override
        public void run() {

            TextChannel channel = jda.getTextChannelById(channelID);
            if (channel != null) {

                HashMap voteResults = getVotes();

                Object winner = null;
                int winnerVotes = 0;
                for (Object memberID : voteResults.keySet()) {
                    int votes = (int) voteResults.get(memberID);
                    if (votes > winnerVotes) {
                        winnerVotes = votes;
                        winner = memberID;
                    }
                }

                if (winner != null) {

                    Object finalWinner = winner;
                    channel.retrieveMessageById(embedID).queue(message -> {
                        message.delete().queue();

                        Member member = jda.getGuilds().get(0).getMemberById(finalWinner.toString());
                        String name = member.getEffectiveName();

                        EmbedBuilder embed = ResultsEmbed.newEmbed(name);
                        channel.sendMessageEmbeds(embed.build()).queue();
                    });
                }

                voteResults.clear();
                member_votes.clear();
            }
        }
    }
}