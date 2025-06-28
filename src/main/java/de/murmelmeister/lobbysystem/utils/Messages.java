package de.murmelmeister.lobbysystem.utils;

public enum Messages {
    PREFIX("Prefix", "<#999999>[<#4200ff>LobbySystem</#4200ff>] <reset>"),
    PERMISSION_BUILD_USE("Permission.Build.Use", "lobbysystem.command.build.use"),
    PERMISSION_BUILD_OTHER("Permission.Build.Other", "lobbysystem.command.build.other"),
    PERMISSION_DEATH_HEIGHT_GET("Permission.DeathHeight.Get", "lobbysystem.command.deathheight.get"),
    PERMISSION_DEATH_HEIGHT_SET("Permission.DeathHeight.Set", "lobbysystem.command.deathheight.set"),
    PERMISSION_SPAWN_SET("Permission.Spawn.Set", "lobbysystem.command.spawn.set"),
    PERMISSION_SPAWN_GET("Permission.Spawn.Get", "lobbysystem.command.spawn.get"),
    PERMISSION_LOBBY_SYSTEM_RELOAD("Permission.LobbySystemReload", "lobbysystem.command.reload"),
    PERMISSION_LOBBY_ITEMS_BOOTS("Permission.LobbyItems.Boots", "lobbysystem.lobbyitem.boots"),
    MESSAGE_NO_PERMISSION("Message.NoPermission", "<#ff0000>Du hast keine Berechtigung, diesen Befehl zu benutzen."),
    MESSAGE_NO_CONSOLE("Message.NoConsole", "<#ff0000>Dieser Befehl kann nicht von der Konsole ausgeführt werden."),
    MESSAGE_PLAYER_IS_NOT_ONLINE("Message.PlayerIsNotOnline", "<#ff0000>Der Spieler <#ffb500>[PLAYER]</#ffb500> ist nicht online."),
    MESSAGE_LOBBY_SYSTEM_RELOAD("Message.LobbySystem.ReloadMessage", "<#00ff00>Die Konfiguration wurde erfolgreich neu geladen."),
    MESSAGE_BUILD_USE_DISABLED("Message.Build.UseDisabled", "<#999999>Das hast den Bau-Modus <#ff0000>deaktiviert</#ff0000>."),
    MESSAGE_BUILD_USE_ENABLED("Message.Build.UseEnabled", "<#999999>Du hast den Bau-Modus <#00ff00>aktiviert</#00ff00>."),
    MESSAGE_BUILD_OTHER_DISABLED("Message.Build.OtherDisabled", "<#999999>Du hast den Spieler <#ffb500>[PLAYER]</#ffb500> den Bau-Modus <#ff0000>deaktiviert</#ff0000>."),
    MESSAGE_BUILD_OTHER_ENABLED("Message.Build.OtherEnabled", "<#999999>Du hast den Spieler <#ffb500>[PLAYER]</#ffb500> den Bau-Modus <#00ff00>aktiviert</#00ff00>."),
    MESSAGE_BUILD_SYNTAX("Message.Build.Syntax", "<#ff0000>Syntax: /build [player]"),
    MESSAGE_DEATH_HEIGHT_GET("Message.DeathHeight.Get", "<#999999>Die Todeshöhe ist auf <#ffb500>[HEIGHT]</#ffb500> gesetzt."),
    MESSAGE_DEATH_HEIGHT_SET("Message.DeathHeight.Set", "<#999999>Die Todeshöhe wurde auf <#ffb500>[HEIGHT]</#ffb500> gesetzt."),
    MESSAGE_DEATH_HEIGHT_NOT_SET("Message.DeathHeight.NotSet", "<#ff0000>Die Todeshöhe ist nicht gesetzt."),
    MESSAGE_SPAWN_SET("Message.Spawn.Set", "<#999999>Der Spawn wurde auf die aktuelle Position gesetzt."),
    MESSAGE_SPAWN_GET("Message.Spawn.Get", "<#999999>Du hast dich zum <#00ff00>Spawn</#00ff00> teleportiert."),
    MESSAGE_SPAWN_NOT_SET("Message.Spawn.NotSet", "<#ff0000>Der Spawn ist nicht gesetzt.");
    public static final Messages[] VALUES = values();

    private final String path;
    private final String fallback;

    Messages(String path, String fallback) {
        this.path = path;
        this.fallback = fallback;
    }

    public String getPath() {
        return path;
    }

    public String getFallback() {
        return fallback;
    }
}
