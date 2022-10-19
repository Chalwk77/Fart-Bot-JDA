// Copyright (c) 2022, Jericho Crosby <jericho.crosby227@gmail.com>

package com.chalwk;

import com.chalwk.Utilities.NewTimer;
import com.chalwk.commands.RenameThisCommand;
import com.chalwk.Listeners.CommandManager;
import com.chalwk.Listeners.EventListeners;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.json.JSONObject;

import javax.security.auth.login.LoginException;
import java.io.IOException;

import static com.chalwk.Utilities.Authentication.getToken;
import static com.chalwk.Utilities.FileIO.loadJSONObject;

public class Main {

    public static JSONObject settings;
    private static ShardManager shardManager;


    /**
     * Loads environment variables and builds the bot shard manager:
     *
     * @throws LoginException if the bot token is invalid.
     */
    public Main() throws LoginException, IOException {

        String token = getToken();
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.watching("YOU"));
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.enableIntents(
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_PRESENCES,
                GatewayIntent.MESSAGE_CONTENT
        );

        shardManager = builder.build();
        shardManager.addEventListener(new EventListeners());

        CommandManager manager = new CommandManager();
        manager.add(new RenameThisCommand());

        shardManager.addEventListener(manager);

        loadSettings();
        NewTimer.fartTimer();
    }

    public static void loadSettings() {
        try {
            settings = loadJSONObject("settings.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getSettings() {
        return settings;
    }

    public static JDA getJDA() {
        return shardManager.getShards().get(0);
    }

    /**
     * Main static method:
     *
     * @param args The arguments passed to the program.
     */
    public static void main(String[] args) {
        try {
            new Main();
        } catch (LoginException | IOException e) {
            System.out.println("ERROR: Provided bot token is invalid");
        }
    }

    /**
     * Retrieves the shard manager:
     *
     * @return The shardManager instance for the bot.
     */
    public static ShardManager getShardManager() {
        return shardManager;
    }
}
