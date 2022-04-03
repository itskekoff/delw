package neko.itskekoff.discordddosbot_default.manager;


import java.io.IOException;
import java.net.URISyntaxException;

public interface AttackManager {
    void launch(String ip, int time) throws IOException, InterruptedException, URISyntaxException;
}
