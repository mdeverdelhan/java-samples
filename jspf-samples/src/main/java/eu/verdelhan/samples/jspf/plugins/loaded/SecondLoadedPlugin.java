package eu.verdelhan.samples.jspf.plugins.loaded;

import eu.verdelhan.samples.jspf.plugins.MyPlugin;
import net.xeoh.plugins.base.annotations.PluginImplementation;

/**
 * Second plugin to be loaded.
 */
@PluginImplementation
public class SecondLoadedPlugin implements MyPlugin {

	public void doSomething() {
		System.out.println("This is a second plugin.");
	}
}
