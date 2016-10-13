package io.github.lxgaming.mcc.listeners;

import org.spacehq.mc.protocol.data.message.Message;
import org.spacehq.mc.protocol.packet.ingame.client.ClientChatPacket;
import org.spacehq.mc.protocol.packet.ingame.server.ServerJoinGamePacket;
import org.spacehq.packetlib.event.session.DisconnectedEvent;
import org.spacehq.packetlib.event.session.PacketReceivedEvent;
import org.spacehq.packetlib.event.session.SessionAdapter;

import io.github.lxgaming.mcc.Configuration;
import io.github.lxgaming.mcc.util.Environment;
import io.github.lxgaming.mcc.util.LogManager;

public class ConnectionListener extends SessionAdapter {
	
	@Override
	public void packetReceived(PacketReceivedEvent event) {
		if (event.getPacket() instanceof ServerJoinGamePacket) {
			LogManager.info("Connected to " + event.getSession().getHost());
			if (Configuration.CONNECTMESSAGE.equalsIgnoreCase("true")) {
				event.getSession().send(new ClientChatPacket("Connected with MCC(Minecraft Chat) on " + Environment.getOSName() + "."));
			}
		}
		return;
	}
	
	@Override
	public void disconnected(DisconnectedEvent event) {
		LogManager.info("Disconnected: " + Message.fromString(event.getReason()).getFullText());
		if (event.getCause() != null) {
			event.getCause().printStackTrace();
		}
		return;
	}
}