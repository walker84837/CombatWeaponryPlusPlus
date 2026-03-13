package org.winlogon.combatweaponryplus;

import net.kyori.adventure.text.minimessage.MiniMessage;

import org.bukkit.plugin.java.JavaPlugin;
import org.winlogon.combatweaponryplus.recipes.RecipeProvider;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.ConfigMigrator;
import org.winlogon.combatweaponryplus.util.Format;
import org.winlogon.combatweaponryplus.util.Recipes;
import org.winlogon.retrohue.RetroHue;

public class CombatWeaponryPlus extends JavaPlugin {

    private MiniMessage mm;
    private RetroHue rh;

    private static CombatWeaponryPlus instance;

    public static CombatWeaponryPlus getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
        this.mm = MiniMessage.miniMessage();
        this.rh = new RetroHue(mm);
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        var cooldown = new Cooldown();

        ConfigMigrator.migrate(this);
        var configHelper = new ConfigHelper(getConfig());
        var serverListeners = new Listeners(this, configHelper, cooldown);

        Format.initialize(mm, rh);
        Recipes.init(this, configHelper);

        getServer().getPluginManager().registerEvents(serverListeners, this);

        var recipeProvider = new RecipeProvider(configHelper);
        recipeProvider.registerRecipes();
    }

    @Override
    public void onDisable() {
        // config = null;
    }

    public MiniMessage getMiniMessage() {
        return this.mm;
    }
}
