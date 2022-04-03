package neko.itskekoff.discordddosbot_default;

import com.diogonunes.jcolor.Attribute;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.entity.Message;
import neko.itskekoff.discordddosbot_default.commands.listeners.SlashCommandListener;
import neko.itskekoff.discordddosbot_default.manager.GlobalCommandRegistrar;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Main {

    public static void main(String[] args) {
        if (args.length == 1) {
            System.out.println(colorize("Загрузка бота", Attribute.GREEN_TEXT()));
            final GatewayDiscordClient client = DiscordClientBuilder.create(args[0]).build()
                    .login()
                    .block();

        
            List<String> commands = List.of("credits.json", "ddos.json");
            try {
                new GlobalCommandRegistrar(client.getRestClient()).registerCommands(commands);
            } catch (Exception ignored) {
            }

            client.on(ChatInputInteractionEvent.class, SlashCommandListener::handle)
                            .then(client.onDisconnect())
                                    .block();
            client.onDisconnect().block();
        }
        else {
            System.out.println(colorize("Вы не указали токен в аргументах!", Attribute.RED_TEXT()));
        }
    }
    private static Mono<Message> methodThatTakesALongTime(ChatInputInteractionEvent event) {
        return event.createFollowup("This took awhile!");
    }
}
