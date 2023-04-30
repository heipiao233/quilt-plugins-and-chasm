package net.heipiao.qpac.plugin;

import org.quiltmc.loader.api.LoaderValue;
import org.quiltmc.loader.api.plugin.QuiltLoaderPlugin;
import org.quiltmc.loader.api.plugin.QuiltPluginContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class QPACPlugin implements QuiltLoaderPlugin {
	public static final Logger LOGGER = LoggerFactory.getLogger("QPAC Plugin");

	@Override
	public void load(QuiltPluginContext context, Map<String, LoaderValue> previousData) {
		LOGGER.info("Hello from QPAC plugin!");
	}

	@Override
	public void unload(Map<String, LoaderValue> data) {
		LOGGER.info("Goodbye from QPAC plugin!");
	}
}
