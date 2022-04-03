package neko.itskekoff.discordddosbot_default.commands.listeners;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import neko.itskekoff.discordddosbot_default.commands.SlashCommand;
import neko.itskekoff.discordddosbot_default.commands.impl.CreditsCommand;
import neko.itskekoff.discordddosbot_default.commands.impl.DdosCommand;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class SlashCommandListener {
    private final static List<SlashCommand> commands = new ArrayList<>();

    static {
        commands.add(new CreditsCommand());
        commands.add(new DdosCommand());
    }

    public static Mono<Void> handle(ChatInputInteractionEvent event) {
        return Flux.fromIterable(commands)
                .filter(command -> command.getname().equals(event.getCommandName()))
                .next()
                .flatMap(command -> command.handle(event));
    }
}
