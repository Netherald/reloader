package net.projecttl.api.reloader

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.plugin.Plugin

class ReloaderAPI {
    private Plugin mainPlugin = Bukkit.server.pluginManager.getPlugin("Reloader")

    private String listPath = "plugins"
    private ArrayList pluginList = mainPlugin.config.getStringList(listPath)

    void loadPlugin(String name) {
        def plugin = Bukkit.getServer().getPluginManager().getPlugin(name)
        Bukkit.pluginManager.enablePlugin(plugin)

        Bukkit.broadcastMessage("[Reloader] ${ChatColor.GREEN}$name is loaded")
    }

    void unloadPlugin(String name) {
        def plugin = Bukkit.getServer().getPluginManager().getPlugin(name)
        Bukkit.pluginManager.disablePlugin(plugin)

        Bukkit.broadcastMessage("[Reloader] ${ChatColor.GREEN}$name is unloaded")
    }

    void reloadPlugin() { // Registered plugin only
        for (String i in pluginList) {
            unloadPlugin(i)
            loadPlugin(i)
        }
    }

    void reloadPluginAll() { // All plugin
        for (Plugin i in Bukkit.pluginManager.getPlugins()) {
            Bukkit.pluginManager.disablePlugin(i)
            Bukkit.pluginManager.enablePlugin(i)
        }

        Bukkit.broadcastMessage("[Reloader] ${ChatColor.GREEN}All plugin reload complete!")
    }

    void registerPlugin(Plugin plugin) {
        if (!pluginList.contains(plugin.name)) {
            pluginList.add(plugin.name)
            plugin.config.set(listPath, pluginList)

            println("${ChatColor.GREEN}${plugin.name} is successful registed!")
        } else {
            println("${ChatColor.GOLD}${plugin.name} is already registed!")
        }
    }

    void unregisterPlugin(Plugin plugin) {
        if (!pluginList.contains(plugin.name)) {
            println("${ChatColor.RED}${plugin.name} is not registed!")
        } else {
            pluginList.remove(plugin.name)
            plugin.config.set(listPath, pluginList)

            println("${ChatColor.GREEN}${plugin.name} is successful unregisted!")
        }
    }
}
