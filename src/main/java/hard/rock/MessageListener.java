package hard.rock;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

public class MessageListener implements EventListener {
    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if (event instanceof MessageReceivedEvent mevent) {
            handleMessageEvent(mevent.getMessage(), mevent.getChannel());
        } else if (event instanceof MessageUpdateEvent muevent) {
            handleMessageEvent(muevent.getMessage(), muevent.getChannel());
        }
    }

    private void handleMessageEvent(Message message, MessageChannelUnion channel) {
        if(channel.getIdLong() == 1217221104289058896L) {
            String mCL = message.getContentRaw().toLowerCase();
            if(!(mCL.equals("he's so talented") || mCL.equals("he's so talented.") || mCL.equals("hes so talented") || mCL.equals("hes so talented."))) {
                message.delete().reason("Ruining the fun").queue();
            }
        }
    }
}
