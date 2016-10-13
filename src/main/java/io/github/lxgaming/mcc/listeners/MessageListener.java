package io.github.lxgaming.mcc.listeners;

import org.spacehq.mc.protocol.data.message.Message;
import org.spacehq.mc.protocol.data.message.TextMessage;
import org.spacehq.mc.protocol.data.message.TranslationMessage;
import org.spacehq.mc.protocol.packet.ingame.server.ServerChatPacket;
import org.spacehq.packetlib.event.session.PacketReceivedEvent;
import org.spacehq.packetlib.event.session.SessionAdapter;

import io.github.lxgaming.mcc.util.LogManager;

public class MessageListener extends SessionAdapter {
	
	@Override
	public void packetReceived(PacketReceivedEvent event) {		
		if (event.getPacket() instanceof ServerChatPacket) {
			Message message = event.<ServerChatPacket>getPacket().getMessage();
			if (message.getFullText().equalsIgnoreCase("chat.cannotsend")) {
				LogManager.info("Cannot Send Message!");
				return;
			}
			if (message instanceof TranslationMessage) {
				Message[] msg = ((TranslationMessage) message).getTranslationParams();
				LogManager.info("[" + msg[0] + "]: " + msg[1]);
			}
			if (message instanceof TextMessage) {
				LogManager.info(message.getFullText());
			}
		}
		return;
	}
}