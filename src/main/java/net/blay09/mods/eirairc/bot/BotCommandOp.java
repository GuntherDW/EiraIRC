package net.blay09.mods.eirairc.bot;

import net.blay09.mods.eirairc.api.base.IIRCChannel;
import net.blay09.mods.eirairc.api.base.IIRCUser;
import net.blay09.mods.eirairc.api.bot.IIRCBot;
import net.blay09.mods.eirairc.api.bot.IBotCommand;
import net.blay09.mods.eirairc.config.GlobalConfig;
import net.blay09.mods.eirairc.util.Utils;
import net.minecraft.server.MinecraftServer;

public class BotCommandOp implements IBotCommand {

	@Override
	public String getCommandName() {
		return "op";
	}

	@Override
	public boolean isChannelCommand() {
		return true;
	}

	@Override
	public void processCommand(IIRCBot bot, IIRCChannel channel, IIRCUser user, String[] args) {
		if(!GlobalConfig.interOpAuthList.contains(user.getAuthLogin())) {
			user.notice(Utils.getLocalizedMessage("irc.bot.noPermission"));
		}
		String message = Utils.joinArgs(args, 0).trim();
		if(message.isEmpty()) {
			user.notice("Usage: !op <command>");
			return;
		}
		bot.resetLog();
		MinecraftServer.getServer().getCommandManager().executeCommand(bot, message);
		user.notice("> " + bot.getLogContents());
	}
	
}