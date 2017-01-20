/*
 * Copyright 2017 Alex Thomson
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
