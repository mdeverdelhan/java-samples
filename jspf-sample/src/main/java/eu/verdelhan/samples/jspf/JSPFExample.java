/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2016 Marc de Verdelhan
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
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

    /**
     * Plugin manager
     */
    private static final PluginManager pluginManager = PluginManagerFactory.createPluginManager();

    /**
     * Plugin manager utils
     */
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
