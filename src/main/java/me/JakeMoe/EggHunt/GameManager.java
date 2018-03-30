package me.JakeMoe.EggHunt;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;

class GameManager {

  private Main plugin;
  private ArrayList<Location> blockLocations;
  private ArrayList<Item> potionsDropped;
  private BukkitRunnable potionTimer;
  private boolean stopTimer;

  class PotionTimer extends BukkitRunnable {
    @Override
    public void run() {
      if (stopTimer) {
        potionTimer.cancel();
        potionTimer = null;
      } else {
        dropPotion();
        potionTimer = new PotionTimer();
        potionTimer.runTaskLater(plugin, (new Random().nextInt(plugin.getPluginConfig().getPotionAddRandSeconds() * 20)) + plugin.getPluginConfig().getPotionAddBaseSeconds() * 20);
      }
    }
  }

  GameManager(Main plugin) {
    this.plugin = plugin;
    this.blockLocations = null;
    this.potionsDropped = null;
  }

  private void dropPotion() {

    ItemStack itemStack = new ItemStack(Material.POTION, 1);
    PotionMeta potionMeta = (PotionMeta) itemStack.getItemMeta();
    PotionEffect potionEffect;
    String potionName;

    int duration = 10;

    switch ((new Random()).nextInt(3)) {
      case 0:
        potionEffect = new PotionEffect(PotionEffectType.FAST_DIGGING, duration * 20, 1, true, true);
        potionName = "Haste II";
        break;
      case 1:
        potionEffect = new PotionEffect(PotionEffectType.JUMP, duration * 20, 2, true, true);
        potionName = "Jump Boost II";
        break;
      case 2:
        potionEffect = new PotionEffect(PotionEffectType.SPEED, duration * 20, 1, true, true);
        potionName = "Speed II";
        break;
      default:
        potionEffect = new PotionEffect(PotionEffectType.GLOWING, duration * 20, 0, true, true);
        potionName = "Glowing";
        break;
    }

    potionMeta.addCustomEffect(potionEffect, true);
    potionMeta.setDisplayName(potionName);
    itemStack.setItemMeta(potionMeta);

    Location randomLocation = plugin.getGameRegion().getRandomLocation();
    potionsDropped.add(plugin.getGameRegion().getWorld().dropItem(randomLocation, itemStack));

    Particle particle = plugin.getPluginConfig().getPotionParticleEffect();
    plugin.getGameRegion().getWorld().spawnParticle(particle, randomLocation, 10, 0.0D, 0.0D, 0.0D, 0.0D);

  }

  private void end() {

    stopTimer = true;
    plugin.clearGameTimer();

    plugin.getServer().getLogger().log(Level.INFO, "[EggHunt] The hunt has finished");
    plugin.getServer().broadcastMessage(plugin.getPluginConfig().getEndMessage());

    saveScores(plugin.getAllScores());

    Map<UUID, Integer> sortedMap = Util.sortByValue(plugin.getAllScores());
    UUID winner = null;
    for (Map.Entry<UUID, Integer> entry : sortedMap.entrySet()) {
      winner = entry.getKey();
    }

    String playerName = Bukkit.getOfflinePlayer(winner).getName();
    plugin.getServer().broadcastMessage(playerName + " has won the Hunt!");

    if (blockLocations != null) {
      for (Location blockLocation : blockLocations) {
        plugin.getGameRegion().getWorld().getBlockAt(blockLocation).setType(Material.AIR);
      }
    }

    if (potionsDropped != null) {
      for (Item potion : potionsDropped) {
        potion.remove();
      }
    }

    plugin.getScoreboard().clear();
    plugin.resetScore();

    plugin.getGameRegion().removePlayers();

  }

  ArrayList<Item> getPotionsDropped() {
    return potionsDropped;
  }

  private void saveScores(HashMap<UUID, Integer> scores) {
    File scoreFile = new File(plugin.getDataFolder() + "/scores/", (new SimpleDateFormat("yyMMdd-hhmmss")).format(new Date()) + ".yml");
    if (!scoreFile.exists()) {
      try {
        scoreFile.createNewFile();
      } catch (IOException e) {
        plugin.getLogger().log(Level.INFO, e.getMessage());
      }
    }

    YamlConfiguration yc = new YamlConfiguration();
    for (Object object : scores.entrySet()) {
      Map.Entry entry = (Map.Entry) object;
      yc.set(Bukkit.getOfflinePlayer(UUID.fromString(entry.getKey().toString())).getName(), entry.getValue());
    }

    try {
      yc.save(scoreFile);
    } catch (IOException e) {
      plugin.getLogger().log(Level.INFO, e.getMessage());
    }
  }

  void start() {
    if (plugin.getGameTimer() == null) {
      plugin.getScoreboard().reset();
      plugin.getScoreboard().refresh();

      blockLocations = new ArrayList<>();
      potionsDropped = new ArrayList<>();

      plugin.startGameTimer(new BukkitRunnable() {
        @Override
        public void run() {
          end();
        }
      });

      stopTimer = false;

      potionTimer = new PotionTimer();
      potionTimer.runTaskLater(plugin, (new Random().nextInt(100)) + 100);

    } else {
      plugin.getServer().broadcastMessage("A game is already in progress.");
    }
  }

  void stop() {
    if (plugin.getGameTimer() == null) {
      plugin.getServer().broadcastMessage("The game has not yet begun!");
    } else {

      stopTimer = true;
      plugin.clearGameTimer();

      plugin.getServer().getLogger().log(Level.INFO, "[EggHunt] Stopping the hunt");
      plugin.getServer().broadcastMessage(plugin.getPluginConfig().getStopMessage());
      plugin.getGameTimer().cancel();

      if (!(blockLocations== null)) {
        for (Location blockLocation : blockLocations) {
          plugin.getGameRegion().getWorld().getBlockAt(blockLocation).setType(Material.AIR);
        }
      }

      if (potionsDropped != null) {
        for (Item potion : potionsDropped) {
          potion.remove();
        }
      }

      plugin.getScoreboard().clear();
      plugin.resetScore();

      plugin.getGameRegion().removePlayers();
    }
  }

}
