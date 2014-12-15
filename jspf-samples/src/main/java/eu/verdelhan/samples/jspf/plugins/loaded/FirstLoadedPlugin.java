package eu.verdelhan.samples.jspf.plugins.loaded;

import eu.verdelhan.samples.jspf.plugins.MyPlugin;
import net.xeoh.plugins.base.annotations.PluginImplementation;

/**
 * First plugin to be loaded.
 */
@PluginImplementation
public class FirstLoadedPlugin implements MyPlugin {

	public void doSomething() {
		System.out.println("This is a first plugin.");
	}
}
