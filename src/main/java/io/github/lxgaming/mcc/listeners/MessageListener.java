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
