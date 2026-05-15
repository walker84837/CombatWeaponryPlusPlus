package org.winlogon.combatweaponryplus;

import net.kyori.adventure.text.minimessage.MiniMessage;

import org.bukkit.plugin.java.JavaPlugin;
import org.winlogon.combatweaponryplus.items.WeaponAbilityRegistry;
import org.winlogon.combatweaponryplus.items.registry.PhantomWingedElytraAbility;
import org.winlogon.combatweaponryplus.items.registry.SpringStepElytraAbility;
import org.winlogon.combatweaponryplus.items.registry.TridentBowAbility;
import org.winlogon.combatweaponryplus.recipes.RecipeProvider;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.ConfigMigrator;
import org.winlogon.combatweaponryplus.util.Format;
import org.winlogon.combatweaponryplus.util.Recipes;
import org.winlogon.retrohue.RetroHue;

public class CombatWeaponryPlus extends JavaPlugin {

    private MiniMessage mm;
    private RetroHue rh;
    private WeaponAbilityRegistry weaponAbilityRegistry;

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

        this.weaponAbilityRegistry = new WeaponAbilityRegistry();
        registerAbilities();

        var serverListeners = new Listeners(this, configHelper, cooldown, weaponAbilityRegistry);

        Format.initialize(mm, rh);
        Recipes.init(this, configHelper);

        getServer().getPluginManager().registerEvents(serverListeners, this);

        var recipeProvider = new RecipeProvider(configHelper);
        recipeProvider.registerRecipes();
    }

    @Override
    public void onDisable() {
    }

    public MiniMessage getMiniMessage() {
        return this.mm;
    }

    private void registerAbilities() {
        weaponAbilityRegistry.register("trident_bow", new TridentBowAbility());
        weaponAbilityRegistry.register("phantom_winged_elytra", new PhantomWingedElytraAbility(this));
        weaponAbilityRegistry.register("spring_step_elytra", new SpringStepElytraAbility());
    }
}
