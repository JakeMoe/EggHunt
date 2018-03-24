package me.JakeMoe.EggHunt;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {

  private Main plugin;

  Command(Main plugin) {
    this.plugin = plugin;
  }

  public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String s, String[] args) {

    if ((!(sender instanceof Player)) || (sender.hasPermission("EggHunt.eh"))) {
      // eh
      if (args.length == 0) {
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "EggHunt v" + plugin.getVersion() + ChatColor.WHITE + " by " + ChatColor.AQUA + plugin.getAuthor());
      // eh help
      } else if (args[0].equals("help")) {
        if (args.length == 1) {
          showSyntax(sender, "eh");
        } else {
          showSyntax(sender, args[1]);
        }
      // eh start
      } else if (args[0].equals("start")) {
        if (args.length == 1) {
          if (plugin.getGameTimer() == null) {
            plugin.getGameManager().start();
          } else {
            sender.sendMessage("The Hunt has already begun! Stop the Hunt if you want to start another.");
          }
        } else {
          showSyntax(sender, args[0]);
        }
      // eh stop
      } else if (args[0].equals("stop")) {
        if (args.length == 1) {
          if (plugin.getGameTimer() == null) {
            sender.sendMessage("No Hunt is in progress to stop.");
          } else {
            plugin.getGameManager().stop();
          }
        } else {
          showSyntax(sender, args[0]);
        }
      // eh configuration commands after this; don't reconfigure while a game is running!
      } else if (plugin.getGameTimer() != null) {
        sender.sendMessage(ChatColor.RED + "You can't configure the game while a game is in progress!");
      // eh game
      } else if (args[0].equals("game")) {
        // eh game help
        if ((args.length == 1) || (args[1].equals("help"))) {
          showSyntax(sender, args[0]);
        // eh game blockAddBase
        } else if (args[1].equals("blockAddBase")) {
          if (args.length == 2) {
            sender.sendMessage("Base seconds is currently " + plugin.getPluginConfig().getBlockAddBaseSeconds());
          // eh game blockAddBase help
          } else if (args[2].equals("help")) {
            showSyntax(sender, args[0] + args[1]);
          // eh game blockAddBase <seconds>
          } else if (args.length == 3) {
            plugin.getPluginConfig().setBlockAddBaseSeconds(Integer.parseInt(args[2]));
            sender.sendMessage("Base seconds is now " + plugin.getPluginConfig().getBlockAddBaseSeconds());
          } else {
            showSyntax(sender, args[0] + args[1]);
          }
        // eh game blockAddRand
        } else if (args[1].equals("blockAddRand")) {
          if (args.length == 2) {
            sender.sendMessage("Random seconds is currently " + plugin.getPluginConfig().getBlockAddRandSeconds());
          // eh game blockAddRand help
          } else if (args[2].equals("help")) {
            showSyntax(sender, args[0] + args[1]);
          // eh game blockAddRand <seconds>
          } else if (args.length == 3) {
            plugin.getPluginConfig().setBlockAddRandSeconds(Integer.parseInt(args[2]));
            sender.sendMessage("Random seconds is now " + plugin.getPluginConfig().getBlockAddRandSeconds());
          } else {
            showSyntax(sender, args[0] + args[1]);
          }
        // eh game blocks
        } else if (args[1].equals("blocks")) {
          if (args.length == 2) {
            sender.sendMessage("Number of blocks is currently " + plugin.getPluginConfig().getGameNumBlocks());
            // eh game blocks help
          } else if (args[2].equals("help")) {
            showSyntax(sender, args[0] + args[1]);
            // eh game blocks <number>
          } else if (args.length == 3) {
            plugin.getPluginConfig().setGameNumBlocks(Integer.parseInt(args[2]));
            sender.sendMessage("Number of blocks is now " + plugin.getPluginConfig().getGameNumBlocks());
          } else {
            showSyntax(sender, args[0] + args[1]);
          }
        // eh game duration
        } else if (args[1].equals("duration")) {
          if (args.length == 2) {
            sender.sendMessage("Game duration is currently " + plugin.getPluginConfig().getGameDuration());
          // eh game duration help
          } else if (args[2].equals("help")) {
            showSyntax(sender, args[0] + args[1]);
          // eh game duration <seconds>
          } else if (args.length == 3) {
            plugin.getPluginConfig().setGameDuration(Integer.parseInt(args[2]));
            sender.sendMessage("Game duration is now " + plugin.getPluginConfig().getGameDuration());
          } else {
            showSyntax(sender, args[0] + args[1]);
          }
        // eh game potionAddBase
        } else if (args[1].equals("potionAddBase")) {
          if (args.length == 2) {
            sender.sendMessage("Base seconds is currently " + plugin.getPluginConfig().getPotionAddBaseSeconds());
          // eh game potionAddBase help
          } else if (args[2].equals("help")) {
            showSyntax(sender, args[0] + args[1]);
          // eh game potionAddBase <seconds>
          } else if (args.length == 3) {
            plugin.getPluginConfig().setPotionAddBaseSeconds(Integer.parseInt(args[2]));
            sender.sendMessage("Base seconds is now " + plugin.getPluginConfig().getPotionAddBaseSeconds());
          } else {
            showSyntax(sender, args[0] + args[1]);
          }
        // eh game potionAddRand
        } else if (args[1].equals("potionAddRand")) {
          if (args.length == 2) {
            sender.sendMessage("Random seconds is currently " + plugin.getPluginConfig().getPotionAddRandSeconds());
          // eh game potionAddRand help
          } else if (args[2].equals("help")) {
            showSyntax(sender, args[0] + args[1]);
          // eh game potionAddRand <seconds>
          } else if (args.length == 3) {
            plugin.getPluginConfig().setPotionAddRandSeconds(Integer.parseInt(args[2]));
            sender.sendMessage("Random seconds is now " + plugin.getPluginConfig().getPotionAddRandSeconds());
          } else {
            showSyntax(sender, args[0] + args[1]);
          }
        // eh game region
        } else if (args[1].equals("region")) {
          if (args.length == 2) {
            sender.sendMessage("WorldGuard region for the game is currently " + plugin.getPluginConfig().getGameRegion());
          // eh game region help
          } else if (args[2].equals("help")) {
            showSyntax(sender, args[0] + args[1]);
          // eh game region <region>
          } else if (args.length == 3) {
            plugin.getPluginConfig().setGameRegion(args[2]);
            sender.sendMessage("WorldGuard region for the game is now " + plugin.getPluginConfig().getGameRegion());
          } else {
            showSyntax(sender, args[0] + args[1]);
          }
        } else if (args[1].equals("world")) {
          // eh game world
          if (args.length == 2) {
            sender.sendMessage("Bukkit world for the game is currently " + plugin.getPluginConfig().getGameWorld());
          // eh game world help
          } else if (args[2].equals("help")) {
            showSyntax(sender, args[0] + args[1]);
          // eh game world <world>
          } else if (args.length == 3) {
            plugin.getPluginConfig().setGameWorld(args[2]);
            sender.sendMessage(" Bukkit world for the game is now " + plugin.getPluginConfig().getGameWorld());
          } else {
            showSyntax(sender, args[0] + args[1]);
          }
        }
      // eh lobby
      } else if (args[0].equals("lobby")) {
        // eh lobby help
        if ((args.length == 1) || (args[1].equals("help"))) {
          showSyntax(sender, args[0]);
        // eh lobby duration
        } else if (args[1].equals("duration")) {
          if (args.length == 2) {
            sender.sendMessage("Lobby wait time is currently " + plugin.getPluginConfig().getLobbyDuration());
          // eh lobby duration help
          } else if (args[2].equals("help")) {
            showSyntax(sender, args[0] + args[1]);
          // eh lobby duration set <world>
          } else if (args.length == 3) {
            plugin.getPluginConfig().setLobbyDuration(Integer.parseInt(args[2]));
            sender.sendMessage("Lobby wait time is now " + plugin.getPluginConfig().getLobbyDuration());
          } else {
            showSyntax(sender, args[0] + args[1]);
          }
        } else if (args[1].equals("join")) {
          // eh lobby join
          if (args.length == 2) {
            sender.sendMessage("Lobby join is currently " + plugin.getPluginConfig().getLobbyJoinLocation().getBlockX() + "," + plugin.getPluginConfig().getLobbyJoinLocation().getBlockY() + "," + plugin.getPluginConfig().getLobbyJoinLocation().getBlockZ());
            // eh lobby set
          } else if (args[2].equals("set")) {
            sender.sendMessage("Click the block to use to join the lobby");
            plugin.setSettingLobbyJoin(true);
          } else {
            showSyntax(sender, args[0] + args[1]);
          }
        } else if (args[1].equals("max")) {
          // eh lobby max
          if (args.length == 2) {
            sender.sendMessage("Lobby max players is currently " + plugin.getPluginConfig().getLobbyMax());
          // eh lobby max help
          } else if (args[2].equals("help")) {
            showSyntax(sender, args[0] + args[1]);
          // eh lobby max #
          } else if (args.length == 3) {
            plugin.getPluginConfig().setLobbyMax(Integer.parseInt(args[2]));
            sender.sendMessage("Lobby max players is now " + plugin.getPluginConfig().getLobbyMax());
          } else {
            showSyntax(sender, args[0] + args[1]);
          }
        } else if (args[1].equals("min")) {
          // eh lobby min
          if (args.length == 2) {
            sender.sendMessage("Lobby min players is currently " + plugin.getPluginConfig().getLobbyMin());
          // eh lobby min help
          } else if (args[2].equals("help")) {
            showSyntax(sender, args[0] + args[1]);
          // eh lobby min #
          } else if (args.length == 3) {
            plugin.getPluginConfig().setLobbyMin(Integer.parseInt(args[2]));
            sender.sendMessage("Lobby min players is now " + plugin.getPluginConfig().getLobbyMin());
          } else {
            showSyntax(sender, args[0] + args[1]);
          }
        } else if (args[1].equals("region")) {
          // eh lobby region
          if (args.length == 2) {
            sender.sendMessage("WorldGuard region for the lobby is currently " + plugin.getPluginConfig().getLobbyRegion());
          // eh lobby region help
          } else if (args[2].equals("help")) {
            showSyntax(sender, args[0] + args[1]);
          // eh lobby region <region>
          } else if (args.length == 3) {
            plugin.getPluginConfig().setLobbyRegion(args[2]);
            sender.sendMessage("WorldGuard region for the lobby is now " + plugin.getPluginConfig().getLobbyRegion());
          } else {
            showSyntax(sender, args[0] + args[1]);
          }
        } else if (args[1].equals("world")) {
          // eh lobby world
          if (args.length == 2) {
            sender.sendMessage("Bukkit world for the lobby is currently " + plugin.getPluginConfig().getLobbyWorld());
          // eh lobby world help
          } else if (args[2].equals("help")) {
            showSyntax(sender, args[0] + args[1]);
          // eh lobby world <world>
          } else if (args.length == 3) {
            plugin.getPluginConfig().setLobbyWorld(args[2]);
            sender.sendMessage(" Bukkit world for the lobby is now " + plugin.getPluginConfig().getLobbyWorld());
          } else {
            showSyntax(sender, args[0] + args[1]);
          }
        }
      } else {
        switch (args[0]) {
          case "endMessage":
            // eh endMessage
            if (args.length == 1) {
              sender.sendMessage("Hunt End message is currently: " + plugin.getPluginConfig().getEndMessage());
            // eh endMessage help
            } else if (args[1].equals("help")) {
              showSyntax(sender, args[0]);
            // eh endMessage <message with spaces>
            } else {
              StringBuilder endMessage = new StringBuilder();
              for (int i = 1; i < args.length; i++) {
                endMessage.append((i == args.length - 1) ? args[i] : args[i] + " ");
              }
              plugin.getPluginConfig().setEndMessage(endMessage.toString());
              sender.sendMessage("Hunt End message is now: " + plugin.getPluginConfig().getEndMessage());
            }
            break;
          case "material":
            // eh material
            if (args.length == 1) {
              sender.sendMessage("Material to hunt is currently " + plugin.getPluginConfig().getMaterial());
            // eh material help
            } else if (args[1].equals("help")) {
              showSyntax(sender, args[0]);
           // eh material <material>
            } else if (args.length == 2) {
              for (Material m : Material.values()) {
                if (m.name().equals(args[1])) {
                  plugin.getPluginConfig().setMaterial(args[1]);
                  sender.sendMessage("Material to hunt is now " + plugin.getPluginConfig().getMaterial());
                  break;
                }
              }
              sender.sendMessage(ChatColor.RED + args[1] + " is not a valid Bukkit MATERIAL. See the Bukkit API reference for a list of materials");
            } else {
              showSyntax(sender, args[0]);
            }
            break;
          case "scoreboardTitle":
            // eh scoreboardTitle
            if (args.length == 1) {
              sender.sendMessage("Hunt scoreboard title is currently: " + plugin.getPluginConfig().getScoreboardTitle());
            // eh scoreboardTitle help
            } else if (args[1].equals("help")) {
              showSyntax(sender, args[0]);
            // eh scoreboardTitle <title with spaces>
            } else {
              StringBuilder scoreboardTitle = new StringBuilder();
              for (int i = 1; i < args.length; i++) {
                scoreboardTitle.append((i == args.length - 1) ? args[i] : args[i] + " ");
              }
              plugin.getPluginConfig().setScoreboardTitle(scoreboardTitle.toString());
              sender.sendMessage("Hunt scoreboard title is now: " + plugin.getPluginConfig().getScoreboardTitle());
            }
            break;
          case "startMessage":
            // eh startMessage
            if (args.length == 1) {
              sender.sendMessage("Hunt Start message is currently: " + plugin.getPluginConfig().getStartMessage());
            // eh startMessage help
            } else if (args[1].equals("help")) {
              showSyntax(sender, args[0]);
            // eh startMessage <message with spaces>
            } else {
              StringBuilder startMessage = new StringBuilder();
              for (int i = 1; i < args.length; i++) {
                startMessage.append((i == args.length - 1) ? args[i] : args[i] + " ");
              }
              plugin.getPluginConfig().setStartMessage(startMessage.toString());
              sender.sendMessage("Hunt Start message is now: " + plugin.getPluginConfig().getStartMessage());
            }
            break;
          case "stopMessage":
            // eh stopMessage
            if (args.length == 1) {
              sender.sendMessage("Hunt Stop message is currently: " + plugin.getPluginConfig().getStopMessage());
            // eh stopMessage help
            } else if (args[1].equals("help")) {
              showSyntax(sender, args[0]);
            // eh stopMessage <message with spaces>
            } else {
              StringBuilder stopMessage = new StringBuilder();
              for (int i = 1; i < args.length; i++) {
                stopMessage.append((i == args.length - 1) ? args[i] : args[i] + " ");
              }
              plugin.getPluginConfig().setStopMessage(stopMessage.toString());
              sender.sendMessage("Hunt Stop message is now: " + plugin.getPluginConfig().getStopMessage());
            }
            break;
          default:
            showSyntax(sender, "eh");
        }
      }
    } else {
      return false;
    }

    return true;

  }

  private void showSyntax(CommandSender sender, String command) {
    switch (command) {
      case "duration":
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eh duration" + ChatColor.YELLOW + " [seconds]" + ChatColor.WHITE + " - gets or" + ChatColor.YELLOW + " sets" + ChatColor.WHITE + " the length of the");
        sender.sendMessage("    Hunt in seconds");
        break;
      case "endmessage":
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eh endMessage" + ChatColor.YELLOW + " [message]" + ChatColor.WHITE + " - gets or" + ChatColor.YELLOW + " sets" + ChatColor.WHITE + " the message at");
        sender.sendMessage("    end of the Hunt");
        break;
      case "gameblocks":
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eh game blocks" + ChatColor.YELLOW + " [number]" + ChatColor.WHITE + " - gets or" + ChatColor.YELLOW + " sets" + ChatColor.WHITE + " the number of blocks generated per game");
        break;
      case "gameregion":
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eh game region" + ChatColor.YELLOW + " [name]" + ChatColor.WHITE + " - gets or" + ChatColor.YELLOW + " sets" + ChatColor.WHITE + " the WorldGuard region name for the game");
        break;
      case "gameworld":
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eh game world" + ChatColor.YELLOW + " [name]" + ChatColor.WHITE + " - gets or" + ChatColor.YELLOW + " sets" + ChatColor.WHITE + " the Bukkit world name for the game");
        break;
      case "lobbyjoin":
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eh lobby join" + ChatColor.YELLOW + " [set]" + ChatColor.WHITE + " - gets or" + ChatColor.YELLOW + " sets" + ChatColor.WHITE + " the block to click to join the lobby");
        break;
      case "lobbyregion":
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eh lobby region" + ChatColor.YELLOW + " [name]" + ChatColor.WHITE + " - gets or" + ChatColor.YELLOW + " sets" + ChatColor.WHITE + " the WorldGuard region name for the lobby");
        break;
      case "lobbyworld":
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eh lobby world" + ChatColor.YELLOW + " [name]" + ChatColor.WHITE + " - gets or" + ChatColor.YELLOW + " sets" + ChatColor.WHITE + " the Bukkit world name for the lobby");
        break;
      case "material":
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eh material" + ChatColor.YELLOW + " [material]" + ChatColor.WHITE + " - gets or" + ChatColor.YELLOW + " sets" + ChatColor.WHITE + " the block to search for");
        break;
      case "scoreboardTitle":
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eh scoreboardTitle" + ChatColor.WHITE + " - gets or sets the scoreboard title");
        break;
      case "start":
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eh start" + ChatColor.WHITE + " - start a new game");
        break;
      case "startMessage":
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eh startMessage" + ChatColor.YELLOW + " [message]" + ChatColor.WHITE + " - gets or" + ChatColor.YELLOW + " sets" + ChatColor.WHITE + " the message at");
        sender.sendMessage("    the end of the Hunt");
        break;
      case "stop":
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eh stop" + ChatColor.WHITE + " - cancel a Hunt in progress");
        break;
      case "stopMessage":
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "/eh stopMessage" + ChatColor.YELLOW + " [message]" + ChatColor.WHITE + " - gets or" + ChatColor.YELLOW + " sets" + ChatColor.WHITE + " the message at");
        sender.sendMessage("    the end of the Hunt");
        break;
      case "eh":
      default:
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "EggHunt Syntax:");
        sender.sendMessage("  " + ChatColor.LIGHT_PURPLE + "/eh" + ChatColor.WHITE + " - version info");
        sender.sendMessage("  " + ChatColor.LIGHT_PURPLE + "/eh endMessage" + ChatColor.YELLOW + " [message]" + ChatColor.WHITE + " - gets or" + ChatColor.YELLOW + " sets" + ChatColor.WHITE + " the message at");
        sender.sendMessage("      end of the game");
        sender.sendMessage("  " + ChatColor.LIGHT_PURPLE + "/eh help" + ChatColor.WHITE + " - shows this help message");
        sender.sendMessage("  " + ChatColor.LIGHT_PURPLE + "/eh game blocks" + ChatColor.YELLOW + " [number]" + ChatColor.WHITE + " - gets or" + ChatColor.YELLOW + " sets" + ChatColor.WHITE + " the number of blocks generated per game");
        sender.sendMessage("  " + ChatColor.LIGHT_PURPLE + "/eh game region" + ChatColor.YELLOW + " [name]" + ChatColor.WHITE + " - gets or" + ChatColor.YELLOW + " sets" + ChatColor.WHITE + " the WorldGuard region name for the game");
        sender.sendMessage("  " + ChatColor.LIGHT_PURPLE + "/eh game world" + ChatColor.YELLOW + " [name]" + ChatColor.WHITE + " - gets or" + ChatColor.YELLOW + " sets" + ChatColor.WHITE + " the Bukkit world name for the game");
        sender.sendMessage("  " + ChatColor.LIGHT_PURPLE + "/eh lobby join" + ChatColor.YELLOW + " [name]" + ChatColor.WHITE + " - gets or" + ChatColor.YELLOW + " sets" + ChatColor.WHITE + " the WorldGuard region name for the lobby");
        sender.sendMessage("  " + ChatColor.LIGHT_PURPLE + "/eh lobby region" + ChatColor.YELLOW + " [name]" + ChatColor.WHITE + " - gets or" + ChatColor.YELLOW + " sets" + ChatColor.WHITE + " the WorldGuard region name for the lobby");
        sender.sendMessage("  " + ChatColor.LIGHT_PURPLE + "/eh lobby world" + ChatColor.YELLOW + " [name]" + ChatColor.WHITE + " - gets or" + ChatColor.YELLOW + " sets" + ChatColor.WHITE + " the Bukkit world name for the lobby");
        sender.sendMessage("  " + ChatColor.LIGHT_PURPLE + "/eh game duration" + ChatColor.YELLOW + " [seconds]" + ChatColor.WHITE + " - gets or" + ChatColor.YELLOW + " sets" + ChatColor.WHITE + " the length of the");
        sender.sendMessage("      game in seconds");
        sender.sendMessage("  " + ChatColor.LIGHT_PURPLE + "/eh game region" + ChatColor.YELLOW + " [name]" + ChatColor.WHITE + " - gets or sets the WorldGuard region name for the game");
        sender.sendMessage("  " + ChatColor.LIGHT_PURPLE + "/eh game world" + ChatColor.YELLOW + " [name]" + ChatColor.WHITE + " - gets or sets the Bukkit world name for the game");
        sender.sendMessage("  " + ChatColor.LIGHT_PURPLE + "/eh lobby duration" + ChatColor.YELLOW + " [seconds]" + ChatColor.WHITE + " - gets or" + ChatColor.YELLOW + " sets" + ChatColor.WHITE + " the length of the");
        sender.sendMessage("      time the lobby waits for new players before starting the game");
        sender.sendMessage("  " + ChatColor.LIGHT_PURPLE + "/eh lobby region" + ChatColor.YELLOW + " [name]" + ChatColor.WHITE + " - gets or sets the WorldGuard region name for the game");
        sender.sendMessage("  " + ChatColor.LIGHT_PURPLE + "/eh lobby world" + ChatColor.YELLOW + " [name]" + ChatColor.WHITE + " - gets or sets the Bukkit world name for the game");
        sender.sendMessage("  " + ChatColor.LIGHT_PURPLE + "/eh material" + ChatColor.YELLOW + " [material]" + ChatColor.WHITE + " - gets or" + ChatColor.YELLOW + " sets" + ChatColor.WHITE + " the block to hunt to");
        sender.sendMessage("      MATERIAL");
        sender.sendMessage("  " + ChatColor.LIGHT_PURPLE + "/eh scoreboardTitle" + ChatColor.WHITE + " - gets or sets the scoreboard title");
        sender.sendMessage("  " + ChatColor.LIGHT_PURPLE + "/eh start" + ChatColor.WHITE + " - start a new Hunt");
        sender.sendMessage("  " + ChatColor.LIGHT_PURPLE + "/eh startMessage" + ChatColor.YELLOW + " [message]" + ChatColor.WHITE + " - gets or" + ChatColor.YELLOW + " sets" + ChatColor.WHITE + " the message at");
        sender.sendMessage("      the end of the Hunt");
        sender.sendMessage("  " + ChatColor.LIGHT_PURPLE + "/eh stop" + ChatColor.WHITE + " - cancel a Hunt in progress");
        sender.sendMessage("  " + ChatColor.LIGHT_PURPLE + "/eh stopMessage" + ChatColor.YELLOW + " [message]" + ChatColor.WHITE + " - gets or" + ChatColor.YELLOW + " sets" + ChatColor.WHITE + " the message at");
        sender.sendMessage("      the end of the Hunt");
        sender.sendMessage("  " + ChatColor.LIGHT_PURPLE + "/eh useNicky" + ChatColor.YELLOW + " [disabled/enabled/false/true]" + ChatColor.WHITE + " - gets or sets whether to use Nicky for names");
    }

  }

}
