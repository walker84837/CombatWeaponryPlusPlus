package org.winlogon.combatweaponryplus.util;

/**
 * Represents an operation that can be applied to a configuration value.
 * These operations are typically used in conjunction with values retrieved from
 * a {@link ConfigHelper} to modify them in a specific way.
 */
public enum ConfigValueOperation {
    /**
     * Subtracts the operation value from the base configuration value.
     */
    SUBTRACT,
    /**
     * Divides the base configuration value by the operation value.
     */
    DIVIDE,
    /**
     * Applies no operation; the base configuration value is used as is.
     */
    NONE,
    /**
     * Adds the operation value to the base configuration value.
     */
    ADD
}
