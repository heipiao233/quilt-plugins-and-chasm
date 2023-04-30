package net.heipiao.qpac.plugin;

import org.quiltmc.loader.api.LoaderValue;
import org.quiltmc.loader.api.plugin.QuiltLoaderPlugin;
import org.quiltmc.loader.api.plugin.QuiltPluginContext;

import java.util.Map;

public class QPACPlugin implements QuiltLoaderPlugin {
	@Override
	public void load(QuiltPluginContext context, Map<String, LoaderValue> previousData) {
		System.out.println("Hello from QPAC plugin!");
	}

	@Override
	public void unload(Map<String, LoaderValue> data) {
		System.out.println("Goodbye from QPAC plugin!");
	}
}
