package dev.fire.mods.aurora.mc;

import dev.fire.mods.aurora.AuroraConfig;
import dev.fire.mods.aurora.PageType;
import dev.fire.mods.aurora.page.HTTPWebPage;
import dev.fire.mods.aurora.tag.Tag;
import net.minecraftforge.fml.common.ModContainer;

/**
 * @author LatvianModder
 */
public class ModPage extends HTTPWebPage
{
	private final ModContainer mod;

	public ModPage(ModContainer m)
	{
		mod = m;
	}

	@Override
	public String getTitle()
	{
		return "Minecraft";
	}

	@Override
	public String getDescription()
	{
		return "Mod List";
	}

	@Override
	public PageType getPageType()
	{
		return AuroraConfig.modlist_page;
	}

	@Override
	public void body(Tag body)
	{
		body.h1(mod.getName()).text(" ").span(mod.getDisplayVersion(), "other");

		if (!mod.getMetadata().description.isEmpty())
		{
			body.h3("").paired("i", mod.getMetadata().description);
		}

		if (!mod.getMetadata().url.isEmpty())
		{
			body.h3("").a(mod.getMetadata().url, mod.getMetadata().url);
		}

		if (!mod.getMetadata().authorList.isEmpty())
		{
			if (mod.getMetadata().authorList.size() == 1)
			{
				body.h3("Author: ").span(mod.getMetadata().authorList.get(0), "other");
			}
			else
			{
				body.h3("Authors:").style("margin-bottom", "0");

				Tag ul = body.ul().style("margin-top", "0");

				for (String s : mod.getMetadata().authorList)
				{
					ul.li().paired("h4").style("margin", "0").span(s, "other");
				}
			}
		}

		if (!mod.getMetadata().credits.isEmpty())
		{
			body.h3("").paired("i", mod.getMetadata().credits);
		}
	}
}