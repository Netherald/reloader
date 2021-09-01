package net.projecttl.reloader

import net.projecttl.api.reloader.RemoteReload
import net.projecttl.reloader.commands.ReloaderCommand
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

class ReloaderPlugin extends JavaPlugin {

    @Override
    void onEnable() {
        getCommand("reloader").setExecutor(new ReloaderCommand())

        if (config.getBoolean("remote_reload")) {
            Bukkit.scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
                @Override
                void run() {
                    RemoteReload reload = new RemoteReload(this as Plugin, config.getInt("port"), config.getString("secret_message"))
                    reload.getFromSocket()
                }
            }, 1L, 20L)
        }
    }

    @Override
    void onDisable() {
    }
}
