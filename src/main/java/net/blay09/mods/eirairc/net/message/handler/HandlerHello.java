// Copyright (c) 2014, Christopher "blay09" Baker
// All rights reserved.

package net.blay09.mods.eirairc.net.message.handler;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.blay09.mods.eirairc.EiraIRC;
import net.blay09.mods.eirairc.net.EiraPlayerInfo;
import net.blay09.mods.eirairc.net.message.MessageHello;
import net.minecraft.entity.player.EntityPlayer;

public class HandlerHello implements IMessageHandler<MessageHello, IMessage> {

	@Override
	public IMessage onMessage(MessageHello packet, MessageContext ctx) {
		EntityPlayer entityPlayer = ctx.getServerHandler().playerEntity;
		
		EiraPlayerInfo playerInfo = EiraIRC.instance.getNetHandler().getPlayerInfo(entityPlayer.getName());
		playerInfo.modInstalled = true;
		playerInfo.modVersion = packet.getVersion();

		return null;
	}

}
