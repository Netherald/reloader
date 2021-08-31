package net.projecttl.reloader.commands

import net.projecttl.api.reloader.ReloaderAPI
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class ReloaderCommand implements CommandExecutor, TabCompleter {

    @Override
    boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.name == "reloader") {
            switch (args.length) {
                case 0: return false
                case 2:
                    switch (args[0]) {
                        case "reload":
                            def manager = new ReloaderAPI()
                            manager.reloadPlugin()

                            return true

                        case "reloadall":
                            def manager = new ReloaderAPI()
                            if (args[1] != "confirm") {
                                sender.sendMessage("${ChatColor.RED}${ChatColor.BOLD}This is Dangerous command! You must type /rl reloadall confirm")
                            } else {
                                manager.reloadPluginAll()
                            }

                            return true

                        case "load":
                            def manager = new ReloaderAPI()
                            manager.loadPlugin(args[1])

                            return true

                        case "unload":
                            def manager = new ReloaderAPI()
                            manager.unloadPlugin(args[1])

                            return true
                    }
            }
        }

        return false
    }

    @Override
    List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.name == "reloader") {
            def list = Arrays.asList()
            switch (args.length) {
                case 1:
                    list.add("reload")
                    list.add("reloadall")
                    list.add("reloadreg")
                    list.add("load")
                    list.add("unload")

                    return list as List<String>

                case 2:
                    if (args[0] == "reload" || args[0] == "load" || args[0] == "unload") {
                        for (i in Bukkit.getPluginManager().getPlugins()) {
                            list.add(i.name)
                        }

                        return list as List<String>
                    }
            }
        }
        return null
    }
}
