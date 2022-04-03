package neko.itskekoff.discordddosbot_default.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandExecutor {
    private static String OS = System.getProperty("os.name").toLowerCase();
    public static void clear() throws IOException, InterruptedException {
        if(OS.contains("win")){
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
            Process startProcess = pb.inheritIO().start();
            startProcess.waitFor();
        } else {
            ProcessBuilder pb = new ProcessBuilder("clear");
            Process startProcess = pb.inheritIO().start();

            startProcess.waitFor();
        }

    }
    public static void exec(String cmd) throws IOException, InterruptedException {
        if (OS.contains("win")) {
            Runtime run = Runtime.getRuntime();
            Process pr = run.exec("cmd /c " + cmd);
            pr.waitFor();
            BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line = "";
            while ((line=buf.readLine())!=null) {
                System.out.println(line);
            }
        } else {
            Runtime run = Runtime.getRuntime();
            Process pr = run.exec("bash /c" + cmd);
            pr.waitFor();
            BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line = "";
            while ((line=buf.readLine())!=null) {
                System.out.println(line);
            }
        }

    }
}
