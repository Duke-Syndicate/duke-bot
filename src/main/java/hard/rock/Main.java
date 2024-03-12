package hard.rock;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        String token = new String(Main.class.getResourceAsStream("/token.txt").readAllBytes(), StandardCharsets.UTF_8);

        JDABuilder builder = JDABuilder.createDefault(token)
        .addEventListeners(new ReadyListener(), new MessageListener());

        //builder.setBulkDeleteSplittingEnabled(false);
        builder.setActivity(Activity.watching("Duke Sex"));
        builder.disableIntents(GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_MESSAGE_TYPING);
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        builder.setLargeThreshold(50);

        builder.build();
    }
}

