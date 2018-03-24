package me.JakeMoe.EggHunt;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

class Config {

  private Main plugin;
  private FileConfiguration fileConfig;

  Config(Main plugin) {
    this.plugin = plugin;
  }

  void loadConfig() {

    boolean booChanged = false;

    fileConfig = plugin.getConfig();

    if (!fileConfig.contains("EggHunt.settings.game.blockAddBaseSeconds")) {
      fileConfig.set("EggHunt.settings.game.blockAddBaseSeconds", 10);
      booChanged = true;
    }

    if (!fileConfig.contains("EggHunt.settings.game.blockAddRandSeconds")) {
      fileConfig.set("EggHunt.settings.game.blockAddRandSeconds", 10);
      booChanged = true;
    }

    if (!fileConfig.contains("EggHunt.settings.game.duration")) {
      fileConfig.set("EggHunt.settings.game.duration", "30");
      booChanged = true;
    }

    if (!fileConfig.contains("EggHunt.settings.game.numBlocks")) {
      fileConfig.set("EggHunt.settings.game.numBlocks", "10");
      booChanged = true;
    }

    if (!fileConfig.contains("EggHunt.settings.game.potionAddBaseSeconds")) {
      fileConfig.set("EggHunt.settings.game.potionAddBaseSeconds", 10);
      booChanged = true;
    }

    if (!fileConfig.contains("EggHunt.settings.game.potionAddRandSeconds")) {
      fileConfig.set("EggHunt.settings.game.potionAddRandSeconds", 10);
      booChanged = true;
    }

    if (!fileConfig.contains("EggHunt.areas.game.Region")) {
      fileConfig.set("EggHunt.areas.game.Region", "EggHuntGame");
      booChanged = true;
    }

    if (!fileConfig.contains("EggHunt.areas.game.World")) {
      fileConfig.set("EggHunt.areas.game.World", "world");
      booChanged = true;
    }

    if (!fileConfig.contains("EggHunt.areas.lobby.Join.X")) {
      fileConfig.set("EggHunt.areas.lobby.Join.X", 0);
      booChanged = true;
    }

    if (!fileConfig.contains("EggHunt.areas.lobby.Join.Y")) {
      fileConfig.set("EggHunt.areas.lobby.Join.Y", 0);
      booChanged = true;
    }

    if (!fileConfig.contains("EggHunt.areas.lobby.Join.Z")) {
      fileConfig.set("EggHunt.areas.lobby.Join.Z", 0);
      booChanged = true;
    }

    if (!fileConfig.contains("EggHunt.areas.lobby.Max")) {
      fileConfig.set("EggHunt.areas.lobby.Max", 5);
      booChanged = true;
    }

    if (!fileConfig.contains("EggHunt.areas.lobby.Min")) {
      fileConfig.set("EggHunt.areas.lobby.Min", 1);
      booChanged = true;
    }

    if (!fileConfig.contains("EggHunt.areas.lobby.Region")) {
      fileConfig.set("EggHunt.areas.lobby.Region", "EggHuntLobby");
      booChanged = true;
    }

    if (!fileConfig.contains("EggHunt.areas.lobby.Timer")) {
      fileConfig.set("EggHunt.areas.lobby.Timer", 10);
      booChanged = true;
    }

    if (!fileConfig.contains("EggHunt.areas.lobby.World")) {
      fileConfig.set("EggHunt.areas.lobby.World", "world");
      booChanged = true;
    }

    if (!fileConfig.contains("EggHunt.messages.End")) {
      fileConfig.set("EggHunt.messages.End", "The Hunt has ended!");
      booChanged = true;
    }

    if (!fileConfig.contains("EggHunt.messages.ScoreboardTitle")) {
      fileConfig.set("EggHunt.messages.ScoreboardTitle", "TOP HUNTERS");
      booChanged = true;
    }

    if (!fileConfig.contains("EggHunt.messages.Start")) {
      fileConfig.set("EggHunt.messages.Start", "The Hunt has begun!");
      booChanged = true;
    }

    if (!fileConfig.contains("EggHunt.messages.Stop")) {
      fileConfig.set("EggHunt.messages.Stop", "The Hunt has been stopped!");
      booChanged = true;
    }

    if (!fileConfig.contains("EggHunt.settings.materialToFind")) {
      fileConfig.set("EggHunt.settings.materialToFind", "SKULL_ITEM");
      booChanged = true;
    }

    if (booChanged) {
      this.plugin.saveConfig();
    }

  }

  void saveConfig() {
    plugin.saveConfig();
  }

  int getBlockAddBaseSeconds() {
    return fileConfig.getInt("EggHunt.settings.game.blockAddBaseSeconds");
  }

  void setBlockAddBaseSeconds(int seconds) {
    fileConfig.set("EggHunt.settings.game.blockAddBaseSeconds", seconds);
    plugin.saveConfig();
  }

  int getBlockAddRandSeconds() {
    return fileConfig.getInt("EggHunt.settings.game.blockAddRandSeconds");
  }

  void setBlockAddRandSeconds(int seconds) {
    fileConfig.set("EggHunt.settings.game.blockAddRandSeconds", seconds);
    plugin.saveConfig();
  }

  String getEndMessage() {
    return fileConfig.getString("EggHunt.messages.End");
  }

  void setEndMessage(String message) {
    fileConfig.set("EggHunt.messages.End", message);
    plugin.saveConfig();
  }

  int getGameDuration() {
    return Integer.valueOf(fileConfig.getString("EggHunt.settings.game.duration"));
  }

  void setGameDuration(int seconds) {
    fileConfig.set("EggHunt.settings.game.duration", seconds);
    plugin.saveConfig();
  }

  int getGameNumBlocks() {
    return Integer.valueOf(fileConfig.getString("EggHunt.settings.game.numBlocks"));
  }

  void setGameNumBlocks(int numBlocks) {
    fileConfig.set("EggHunt.settings.game.numBlocks", numBlocks);
    plugin.saveConfig();
  }

  String getGameRegion() {
    return fileConfig.getString("EggHunt.areas.game.Region");
  }

  void setGameRegion(String region) {
    fileConfig.set("EggHunt.areas.game.Region", region);
    plugin.saveConfig();
    plugin.getGameRegion().updateRegion();
  }

  String getGameWorld() {
    return fileConfig.getString("EggHunt.areas.game.World");
  }

  void setGameWorld(String world) {
    fileConfig.set("EggHunt.areas.game.World", world);
    plugin.saveConfig();
    plugin.getLobbyRegion().updateRegion();
  }

  int getLobbyDuration() {
    return fileConfig.getInt("EggHunt.areas.lobby.Timer");
  }

  void setLobbyDuration(int seconds) {
    fileConfig.set("EggHunt.areas.lobby.Timer", seconds);
    plugin.saveConfig();
  }

  Location getLobbyJoinLocation() {
    int x = Integer.valueOf(fileConfig.getString("EggHunt.areas.lobby.Join.X"));
    int y = Integer.valueOf(fileConfig.getString("EggHunt.areas.lobby.Join.Y"));
    int z = Integer.valueOf(fileConfig.getString("EggHunt.areas.lobby.Join.Z"));
    return new Location(plugin.getServer().getWorld(getLobbyWorld()), x, y, z);
  }

  void setLobbyJoinLocation(Location location) {
    fileConfig.set("EggHunt.areas.lobby.Join.X", location.getBlockX());
    fileConfig.set("EggHunt.areas.lobby.Join.Y", location.getBlockY());
    fileConfig.set("EggHunt.areas.lobby.Join.Z", location.getBlockZ());
    plugin.saveConfig();
  }

  int getLobbyMax() {
    return fileConfig.getInt("EggHunt.areas.lobby.Max");
  }

  void setLobbyMax(int max) {
    fileConfig.set("EggHunt.areas.lobby.Max", max);
    plugin.saveConfig();
  }

  int getLobbyMin() {
    return fileConfig.getInt("EggHunt.areas.lobby.Min");
  }

  void setLobbyMin(int min) {
    fileConfig.set("EggHunt.areas.lobby.Min", min);
    plugin.saveConfig();
  }

  String getLobbyRegion() {
    return fileConfig.getString("EggHunt.areas.lobby.Region");
  }

  void setLobbyRegion(String region) {
    fileConfig.set("EggHunt.areas.lobby.Region", region);
    plugin.saveConfig();
    plugin.getLobbyRegion().updateRegion();
  }

  String getLobbyWorld() {
    return fileConfig.getString("EggHunt.areas.lobby.World");
  }

  void setLobbyWorld(String world) {
    fileConfig.set("EggHunt.areas.lobby.World", world);
    plugin.saveConfig();
    plugin.getLobbyRegion().updateRegion();
  }

  Material getMaterial() {
    return Material.valueOf(fileConfig.getString("EggHunt.settings.materialToFind"));
  }

  void setMaterial(String material) {
    fileConfig.set("EggHunt.settings.materialToFind", material);
    plugin.saveConfig();
  }

  int getPotionAddBaseSeconds() {
    return fileConfig.getInt("EggHunt.settings.game.potionAddBaseSeconds");
  }

  void setPotionAddBaseSeconds(int seconds) {
    fileConfig.set("EggHunt.settings.game.potionAddBaseSeconds", seconds);
    plugin.saveConfig();
  }

  int getPotionAddRandSeconds() {
    return fileConfig.getInt("EggHunt.settings.game.potionAddRandSeconds");
  }

  void setPotionAddRandSeconds(int seconds) {
    fileConfig.set("EggHunt.settings.game.potionAddRandSeconds", seconds);
    plugin.saveConfig();
  }

  String getScoreboardTitle() {
    return fileConfig.getString("EggHunt.messages.ScoreboardTitle");
  }

  void setScoreboardTitle(String scoreboardTitle) {
    fileConfig.set("EggHunt.messages.ScoreboardTitle", scoreboardTitle);
    plugin.saveConfig();
  }

  String getStartMessage() {
    return fileConfig.getString("EggHunt.messages.Start");
  }

  void setStartMessage(String message) {
    fileConfig.set("EggHunt.messages.Start", message);
    plugin.saveConfig();
  }

  String getStopMessage() {
    return fileConfig.getString("EggHunt.messages.Stop");
  }

  void setStopMessage(String message) {
    fileConfig.set("EggHunt.messages.Stop", message);
    plugin.saveConfig();
  }

}