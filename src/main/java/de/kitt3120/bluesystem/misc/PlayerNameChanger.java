package de.kitt3120.bluesystem.misc;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 * Created by kitt3120 on 08.09.2017 at 20:04.
 */
public class PlayerNameChanger {

    public static void setPrefix(Player player, String prefix) {
        if(!prefix.endsWith(" ")) prefix = prefix + " ";

        Scoreboard scoreboard = player.getScoreboard();

        Team team = getTeam(scoreboard, player.getName());
        team.setPrefix(prefix);
        team.addEntry(player.getName());

        updateDisplayName(player);
    }

    public static void setSuffix(Player player, String suffix) {
        if(!suffix.startsWith(" ")) suffix = " " + suffix;
        Scoreboard scoreboard = player.getScoreboard();

        Team team = getTeam(scoreboard, player.getName());
        team.setSuffix(suffix);
        if(!team.hasEntry(player.getName())) team.addEntry(player.getName());

        updateDisplayName(player);
    }

    public static void remove(Player player) {
        Scoreboard scoreboard = player.getScoreboard();

        Team team = getTeam(scoreboard, player.getName());
        team.unregister();
        player.setDisplayName(player.getName());
    }

    private static Team getTeam(Scoreboard scoreboard, String name) {
        return scoreboard.getTeam(name) == null ? scoreboard.registerNewTeam(name) : scoreboard.getTeam(name);
    }

    public static void updateDisplayName(Player player) {
        String displayName = player.getName();
        Scoreboard scoreboard = player.getScoreboard();
        Team team = getTeam(scoreboard, player.getName());
        if(team.getPrefix() != null && team.getPrefix().length() > 0) displayName = team.getPrefix() + displayName;
        if(team.getSuffix() != null && team.getSuffix().length() > 0) displayName = displayName + team.getSuffix();
        player.setDisplayName(displayName);
    }

}
