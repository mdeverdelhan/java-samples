package eu.verdelhan.samples.jspf;

import eu.verdelhan.samples.jspf.plugins.MyPlugin;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import net.xeoh.plugins.base.PluginManager;
import net.xeoh.plugins.base.impl.PluginManagerFactory;
import net.xeoh.plugins.base.util.PluginManagerUtil;

/**
 * @see https://code.google.com/p/jspf/
 */
public class JSPFExample {

	/** Plugin manager */
	private static final PluginManager pluginManager = PluginManagerFactory.createPluginManager();

	/** Plugin manager utils */
	private static final PluginManagerUtil pluginManagerUtil = new PluginManagerUtil(pluginManager);

    public static void main(String[] args) throws URISyntaxException {

		// Loads all plugins from the provided URI.
		// (--> Plugins in other packages are not loaded)
		pluginManager.addPluginsFrom(new URI("classpath://eu.verdelhan.samples.jspf.plugins.loaded.*"));

		// Checking for loaded plugins
		Collection<MyPlugin> myPlugins = pluginManagerUtil.getPlugins(MyPlugin.class);
		for (MyPlugin myPlugin : myPlugins) {
			myPlugin.doSomething();
		}
    }

}