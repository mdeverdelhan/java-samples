package eu.verdelhan.samples.jspf.plugins.notloaded;

import eu.verdelhan.samples.jspf.plugins.MyPlugin;
import net.xeoh.plugins.base.annotations.PluginImplementation;

/**
 * This plugin is only here to show it's not loaded.
 */
@PluginImplementation
public class NotLoadedPlugin implements MyPlugin {

	public void doSomething() {
		System.out.println("This plugin should not be loaded.");
	}
}
