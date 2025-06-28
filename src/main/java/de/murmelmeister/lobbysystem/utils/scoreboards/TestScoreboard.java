package de.murmelmeister.lobbysystem.utils.scoreboards;

import de.murmelmeister.lobbysystem.LobbySystem;
import de.murmelmeister.lobbysystem.api.Economy;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class TestScoreboard extends ScoreboardBuilder {

    private final Economy economy = LobbySystem.getInstance().getEconomy();

    public TestScoreboard(Player player, Component displayName) {
        super(player, displayName);
        update();
    }

    @Override
    public void createScoreboard() {
        setScoreContent("" + economy.getMoney(player.getUniqueId()), 1);
    }

    @Override
    public void update() {

    }

}
