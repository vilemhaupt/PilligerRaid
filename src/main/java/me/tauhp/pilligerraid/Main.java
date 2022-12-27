package me.tauhp.pilligerraid;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;
import java.util.UUID;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        // Plugin startup logic
        System.out.println("Plugin PillageRaider has been enabled");
        this.saveDefaultConfig();
        this.getCommand("raider").setExecutor((CommandExecutor) new MainCommand(this));

        int interval = getConfig().getInt("raidtimeout"); // Read interval from config in default is 1 minute


        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

            public void run(){

                for (UUID player : MainCommand.enabled){

                    Random r = new Random();

                    Player nick = Bukkit.getPlayer(player);

                    double Px = Bukkit.getPlayer(player).getLocation().getX();
                    double Py = Bukkit.getPlayer(player).getLocation().getY();
                    double Pz = Bukkit.getPlayer(player).getLocation().getZ();

                    World world = Bukkit.getWorld("world");

                    Location location = new Location(world, Px, Py, Pz);

                    for (int i = 0; i < getConfig().getInt("raidamout"); i++){

                        double randomx = Px + r.nextInt(getConfig().getInt("x"));
                        double randomz = Pz + r.nextInt(getConfig().getInt("z"));


                        Location spawnLoc = new Location(world, randomx, Py, randomz);


                        world.spawnEntity(spawnLoc, EntityType.PILLAGER);

                    }

                    nick.sendMessage((ChatColor.translateAlternateColorCodes(
                            '&',getConfig().getString("raidstart_message")))
                    );

                }

            }

        }, 0, interval);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Plugin PilliageRaider has been disabled");
    }

}
