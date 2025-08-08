package hard.rock;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {
    public static void main(String[] args) {
        String token = System.getenv("BOT_TOKEN");

        JDABuilder builder = JDABuilder.createDefault(token)
        .addEventListeners(new ReadyListener(), new MessageListener());

        //builder.setBulkDeleteSplittingEnabled(false);
        builder.setActivity(Activity.customStatus("Being Talented"));
        builder.disableIntents(GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_MESSAGE_TYPING);
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        builder.setLargeThreshold(50);

        builder.build();
    }
}

