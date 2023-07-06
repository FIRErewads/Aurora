package dev.fire.mods.aurora;

import dev.fire.mods.aurora.page.WebPage;
import io.netty.handler.codec.http.FullHttpRequest;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

import javax.annotation.Nullable;

/**
 * @author LatvianModder
 */
@Cancelable
public class AuroraPageEvent extends Event
{
	private final AuroraServer server;
	private final FullHttpRequest request;
	private final String uri;
	private final String[] splitUri;
	private WebPage page;

	public AuroraPageEvent(AuroraServer s, FullHttpRequest r, String u)
	{
		server = s;
		request = r;
		uri = u;
		splitUri = uri.split("/");
	}

	public AuroraServer getAuroraServer()
	{
		return server;
	}

	public FullHttpRequest getRequest()
	{
		return request;
	}

	public String getUri()
	{
		return uri;
	}

	public String[] getSplitUri()
	{
		return splitUri;
	}

	@Nullable
	public WebPage getPage()
	{
		return page;
	}

	public void setPage(WebPage p)
	{
		page = p;
	}

	public void returnPage(WebPage p)
	{
		setPage(p);
		setCanceled(true);
	}

	public boolean checkPath(String... path)
	{
		if (path.length > splitUri.length)
		{
			return false;
		}

		for (int i = 0; i < path.length; i++)
		{
			if (!splitUri[i].equals(path[i]) && !path[i].equals("*"))
			{
				return false;
			}
		}

		return true;
	}
}