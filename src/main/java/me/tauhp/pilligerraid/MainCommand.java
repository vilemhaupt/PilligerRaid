package me.tauhp.pilligerraid;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.UUID;

public class MainCommand implements CommandExecutor {

    public static ArrayList<UUID> enabled = new ArrayList<>();
    private final Main plugin;



    public  MainCommand(Main plugin){
        this.plugin = plugin;
    }






    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull  Command cmd, @NotNull  String label, @NotNull String[] args) {

        if (sender instanceof Player){

            if (args.length == 1){

                if (args[0].equalsIgnoreCase("reload")){

                    if (sender.hasPermission("raider.reload")){


                        plugin.saveConfig();
                        plugin.reloadConfig();

                        sender.sendMessage((ChatColor.translateAlternateColorCodes(
                                '&',plugin.getConfig().getString("reload_message")))
                        );

                    } else {
                        sender.sendMessage((ChatColor.translateAlternateColorCodes(
                                '&',plugin.getConfig().getString("noperms_message")))
                        );
                    }

                }

                if (args[0].equalsIgnoreCase("start")){
                    enabled.add(((Player) sender).getUniqueId());
                    sender.sendMessage((ChatColor.translateAlternateColorCodes(
                            '&',plugin.getConfig().getString("added_message")))
                    );
                }

                if (args[0].equalsIgnoreCase("stop")){
                    enabled.remove(((Player) sender).getUniqueId());
                    sender.sendMessage((ChatColor.translateAlternateColorCodes(
                            '&',plugin.getConfig().getString("removed_message")))
                    );
                }

            }

        }


        return false;



    }
}
