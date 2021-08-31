package net.projecttl.reloader

import net.projecttl.reloader.commands.ReloaderCommand
import org.bukkit.plugin.java.JavaPlugin

class ReloaderPlugin extends JavaPlugin {

    @Override
    void onEnable() {
        getCommand("reloader").setExecutor(new ReloaderCommand())
    }

    @Override
    void onDisable() {
    }
}
