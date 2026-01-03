package org.winlogon.combatweaponryplus.items.builders;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.jetbrains.annotations.NotNull;
import org.winlogon.combatweaponryplus.util.ConfigHelper;
import org.winlogon.combatweaponryplus.util.ConfigValueOperation;

/**
 * A builder for creating and configuring weapon {@link org.bukkit.inventory.ItemStack} objects.
 * This class provides specific methods for setting weapon-related attributes like attack damage,
 * attack speed, movement speed, and knockback resistance. It also includes functionality to apply
 * configured attribute values from a {@link ConfigHelper}.
 */
public class WeaponBuilder extends ItemBuilder<WeaponBuilder> {
    private final ConfigHelper configHelper;
    private final boolean customValues;

    /**
     * Constructs a new WeaponBuilder with the specified material and configuration helper.
     *
     * @param material The {@link Material} of the weapon. Must not be null.
     * @param configHelper The {@link ConfigHelper} instance for accessing plugin configuration. Must not be null.
     */
    public @NotNull WeaponBuilder(@NotNull Material material, @NotNull ConfigHelper configHelper) {
        super(material);
        this.configHelper = configHelper;
        this.customValues = configHelper.raw().getBoolean("UseCustomValues");
    }

    /**
     * Sets the attack damage attribute for the weapon.
     *
     * @param attackDamage The attack damage value to set.
     * @return This WeaponBuilder instance.
     */
    public @NotNull WeaponBuilder attackDamage(double attackDamage) {
        super.attribute(Attribute.ATTACK_DAMAGE, attackDamage, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        return this;
    }

    /**
     * Sets the attack speed attribute for the weapon.
     *
     * @param attackSpeed The attack speed value to set.
     * @return This WeaponBuilder instance.
     */
    public @NotNull WeaponBuilder attackSpeed(double attackSpeed) {
        super.attribute(Attribute.ATTACK_SPEED, attackSpeed, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        return this;
    }

    /**
     * Sets the movement speed attribute for the weapon.
     *
     * @param movementSpeed The movement speed value to set.
     * @return This WeaponBuilder instance.
     */
    public @NotNull WeaponBuilder movementSpeed(double movementSpeed) {
        super.attribute(Attribute.MOVEMENT_SPEED, movementSpeed, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        return this;
    }

    /**
     * Sets the knockback resistance attribute for the weapon.
     *
     * @param knockbackResistance The knockback resistance value to set.
     * @return This WeaponBuilder instance.
     */
    public @NotNull WeaponBuilder knockbackResistance(double knockbackResistance) {
        super.attribute(Attribute.KNOCKBACK_RESISTANCE, knockbackResistance, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        return this;
    }

    // Applies a specified operation to a value.
    private double applyOperation(double value, ConfigValueOperation op, double opValue) {
        return switch (op) {
            case SUBTRACT -> value - opValue;
            case DIVIDE -> value / opValue;
            case ADD -> value + opValue;
            default -> value;
        };
    }

    /**
     * Configures the attack damage attribute based on plugin settings.
     * If "UseCustomValues" is true in the config, it retrieves the damage value
     * from the specified config path and applies the given operation.
     * Otherwise, it uses the provided default damage.
     *
     * @param configPath The configuration path to the custom damage value (e.g., "aDiamondKnife.damage").
     * @param defaultDamage The default damage value to use if custom values are not enabled or not found.
     * @param op The {@link ConfigValueOperation} to apply to the retrieved config value.
     * @param opValue The operand value for the operation.
     * @return This WeaponBuilder instance.
     */
    public @NotNull WeaponBuilder withConfiguredDamage(String configPath, double defaultDamage, ConfigValueOperation op, double opValue) {
        double dmg = defaultDamage;
        if (this.customValues) {
            double rawDmg = configHelper.getDouble(configPath, defaultDamage);
            dmg = applyOperation(rawDmg, op, opValue);
        }
        return this.attackDamage(dmg);
    }

    /**
     * Configures the attack speed attribute based on plugin settings.
     * If "UseCustomValues" is true in the config, it retrieves the speed value
     * from the specified config path and applies the given operation.
     * Otherwise, it uses the provided default speed.
     *
     * @param configPath The configuration path to the custom speed value (e.g., "aDiamondKnife.speed").
     * @param defaultSpeed The default speed value to use if custom values are not enabled or not found.
     * @param op The {@link ConfigValueOperation} to apply to the retrieved config value.
     * @param opValue The operand value for the operation.
     * @return This WeaponBuilder instance.
     */
    public @NotNull WeaponBuilder withConfiguredSpeed(String configPath, double defaultSpeed, ConfigValueOperation op, double opValue) {
        double spd = defaultSpeed;
        if (this.customValues) {
            double rawSpd = configHelper.getDouble(configPath, defaultSpeed);
            spd = applyOperation(rawSpd, op, opValue);
        }
        return this.attackSpeed(spd);
    }
}
