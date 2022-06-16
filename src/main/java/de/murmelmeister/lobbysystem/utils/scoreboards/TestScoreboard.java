package de.murmelmeister.lobbysystem.utils.scoreboards;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class TestScoreboard extends ScoreboardBuilder {

    public TestScoreboard(Player player, Component displayName) {
        super(player, displayName);
    }

    @Override
    public void createScoreboard() {

    }

    @Override
    public void update() {

    }

}
