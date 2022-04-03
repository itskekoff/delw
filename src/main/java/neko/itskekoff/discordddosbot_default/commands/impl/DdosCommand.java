package neko.itskekoff.discordddosbot_default.commands.impl;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;
import neko.itskekoff.discordddosbot_default.commands.SlashCommand;
import neko.itskekoff.discordddosbot_default.manager.impl.run;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class DdosCommand implements SlashCommand {

    @Override
    public String getname() {
        return "attack";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent e) {
        Pattern regex = Pattern.compile("\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?):\\d{1,5}\\b");
        String server = e.getOption("serverip")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .get();
        EmbedCreateSpec emb = EmbedCreateSpec.builder()
                .color(Color.BROWN)
                .title("Started!")
                .description(server)
                .timestamp(Instant.now())
                .footer("View /credits to get additional info!", "https://i.ytimg.com/vi/HJ9Hq5wu-xw/maxresdefault.jpg")
                .build();
        if (server.matches(regex.pattern())) {
            (new Thread(() -> {
                try {
                    new run().launch(server, 120);
                } catch (IOException | InterruptedException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            })).start();
            return e.reply()
                    .withEmbeds(emb);
        } else {
            return e.reply()
                    .withContent("Неверный формат ip! Пример: 127.0.0.1:25565")
                    .withEphemeral(true);
        }
    }
}
