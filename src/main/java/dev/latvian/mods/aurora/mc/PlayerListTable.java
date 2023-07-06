package dev.fire.mods.aurora.mc;

import dev.fire.mods.aurora.AuroraConfig;
import dev.fire.mods.aurora.PageType;
import dev.fire.mods.aurora.page.HTTPWebPage;
import dev.fire.mods.aurora.tag.Tag;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

/**
 * @author LatvianModder
 */
public class PlayerListTable extends HTTPWebPage
{
	private final MinecraftServer server;

	public PlayerListTable(MinecraftServer s)
	{
		server = s;
	}

	@Override
	public String getTitle()
	{
		return "Minecraft";
	}

	@Override
	public String getDescription()
	{
		return "Online Players";
	}

	@Override
	public PageType getPageType()
	{
		return AuroraConfig.player_list_table;
	}

	@Override
	public String getStylesheet()
	{
		return "";
	}

	@Override
	public boolean addBackButton()
	{
		return false;
	}

	@Override
	public void body(Tag body)
	{
		Tag playerTable = body.table().addClass("player_table");

		for (EntityPlayerMP player : server.getPlayerList().getPlayers())
		{
			String id = player.getUniqueID().toString().replace("-", "");
			Tag row = playerTable.tr();
			row.td().img("https://crafatar.com/avatars/" + id + "?size=16");
			row.td().a(player.getName(), "https://mcuuid.net/?q=" + id);
		}
	}
}