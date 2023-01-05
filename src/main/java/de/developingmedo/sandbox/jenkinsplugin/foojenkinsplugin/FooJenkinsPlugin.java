package de.developingmedo.sandbox.jenkinsplugin.foojenkinsplugin;

import de.developingmedo.sandbox.jenkinsplugin.foojenkinsplugin.command.HelloCommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class FooJenkinsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getLogger().info("HEY HAVE A NOICE TIME");
        this.getCommand("hello").setExecutor(new HelloCommandExecutor());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.getLogger().info("BYEE HAVE A GREAT TIME");
    }
}
