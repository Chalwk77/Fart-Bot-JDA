// Copyright (c) 2022, Jericho Crosby <jericho.crosby227@gmail.com>

package com.chalwk.commands;

import com.chalwk.Interfaces.CommandInterface;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public class RenameThisCommand implements CommandInterface {

    @Override
    public String getName() {
        return "command_name_here";
    }

    @Override
    public String getDescription() {
        return "command_description_here";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> data = new ArrayList<>();

        OptionData option = new OptionData(OptionType.STRING, "option_name_here", "option_description_here");
        option.addChoice("choice_name_here", "choice_value_here");

        data.add(option);

        return data;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {

    }
}
