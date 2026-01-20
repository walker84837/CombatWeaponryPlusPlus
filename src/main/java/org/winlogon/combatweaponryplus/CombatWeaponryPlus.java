package org.winlogon.combatweaponryplus;

import net.kyori.adventure.text.minimessage.MiniMessage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.winlogon.combatweaponryplus.recipes.RecipeProvider;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.Format;
import org.winlogon.retrohue.RetroHue;

import java.util.Random;

public class CombatWeaponryPlus extends JavaPlugin {

    private Random rand = new Random();
    private ConfigHelper configHelper;

    private MiniMessage mm = MiniMessage.miniMessage();
    private RetroHue rh = new RetroHue(mm);

    private static CombatWeaponryPlus instance;

    public static CombatWeaponryPlus getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        var cooldown = new Cooldown();

        this.configHelper = new ConfigHelper(getConfig());
        var serverListeners = new Listeners(this, configHelper, cooldown);

        Format.initialize(mm, rh);

        getServer().getPluginManager().registerEvents(serverListeners, this);
        saveDefaultConfig();

        var recipeProvider = new RecipeProvider(this, this.configHelper);
        recipeProvider.registerRecipes();
    }

    @Override
    public void onDisable() {
        // config = null;
    }

}