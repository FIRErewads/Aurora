package dev.fire.mods.aurora;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;

@Mod(
		modid = Aurora.MOD_ID,
		name = Aurora.MOD_NAME,
		version = Aurora.VERSION,
		acceptableRemoteVersions = "*"
)
public class Aurora
{
	public static final String MOD_ID = "notaurora";
	public static final String MOD_NAME = "NotAurora";
	public static final String VERSION = "0.0.0.notaurora";

	private static AuroraServer server;

	@Mod.EventHandler
	public void onServerStarting(FMLServerStartingEvent event)
	{
		start(event.getServer());
	}

	@Mod.EventHandler
	public void onServerStopping(FMLServerStoppingEvent event)
	{
		stop();
	}

	public static void start(MinecraftServer s)
	{
		if (server == null)
		{
			server = new AuroraServer(s, AuroraConfig.port);
			server.start();
		}
	}

	public static void stop()
	{
		if (server != null)
		{
			server.shutdown();
			server = null;
		}
	}
}