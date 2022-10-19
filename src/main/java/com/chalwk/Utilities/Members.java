package com.chalwk.Utilities;

import com.chalwk.Toot;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;

import java.util.Collection;
import java.util.HashMap;

import static com.chalwk.Main.getJDA;

public class Members {

    static JDA jda = getJDA();

    public static HashMap<String, Integer> setVoteTable() {
        return new HashMap<>();
    }

    // Create getter for votes:
    public static HashMap getVotes() {
        return Toot.votes;
    }

    private static Member[] shuffle(Member[] members) {
        for (int j = 0; j < members.length; j++) {
            int randomIndex = (int) (Math.random() * members.length);
            Member temp = members[j];
            members[j] = members[randomIndex];
            members[randomIndex] = temp;
        }
        return members;
    }


    // Create getter object that returns something:
    public static Member[] getMembers() {

        // Get the members from the JDA object:
        Collection<Member> guildMembers = jda.getGuilds().get(0).getMembers();

        // Create new array of members:
        Member[] members = new Member[guildMembers.size()];

        // Add members to array:
        int i = 0;
        for (Member member : guildMembers) {
            if (member != null && !member.getUser().isBot()) {
                members[i] = member;
                i++;
            }
        }

        return shuffle(members);
    }
}
