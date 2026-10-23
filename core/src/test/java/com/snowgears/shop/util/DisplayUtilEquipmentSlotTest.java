package com.snowgears.shop.util;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DisplayUtilEquipmentSlotTest {

    @Test
    void blocksGoToHand() {
        // Regression test for issue #909: block items must go to HAND, not HEAD.
        // The buggy code had `type.isBlock()` in the HEAD condition, causing all blocks
        // (dirt, stone, logs, etc.) to be displayed on the armor stand's head.
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(new ItemStack(Material.DIRT)));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(new ItemStack(Material.STONE)));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(new ItemStack(Material.OAK_LOG)));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(new ItemStack(Material.GLASS)));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(new ItemStack(Material.COBBLESTONE)));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(new ItemStack(Material.SAND)));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(new ItemStack(Material.GRAVEL)));
    }

    @Test
    void armorGoesToCorrectSlot() {
        assertEquals(EquipmentSlot.HEAD, DisplayUtil.getEquipmentSlot(new ItemStack(Material.DIAMOND_HELMET)));
        assertEquals(EquipmentSlot.CHEST, DisplayUtil.getEquipmentSlot(new ItemStack(Material.IRON_CHESTPLATE)));
        assertEquals(EquipmentSlot.LEGS, DisplayUtil.getEquipmentSlot(new ItemStack(Material.GOLDEN_LEGGINGS)));
        assertEquals(EquipmentSlot.FEET, DisplayUtil.getEquipmentSlot(new ItemStack(Material.LEATHER_BOOTS)));
        // Netherite armor (added in 1.16) follows same naming convention
        assertEquals(EquipmentSlot.HEAD, DisplayUtil.getEquipmentSlot(new ItemStack(Material.NETHERITE_HELMET)));
        assertEquals(EquipmentSlot.CHEST, DisplayUtil.getEquipmentSlot(new ItemStack(Material.NETHERITE_CHESTPLATE)));
        assertEquals(EquipmentSlot.LEGS, DisplayUtil.getEquipmentSlot(new ItemStack(Material.NETHERITE_LEGGINGS)));
        assertEquals(EquipmentSlot.FEET, DisplayUtil.getEquipmentSlot(new ItemStack(Material.NETHERITE_BOOTS)));
        // Chainmail armor
        assertEquals(EquipmentSlot.HEAD, DisplayUtil.getEquipmentSlot(new ItemStack(Material.CHAINMAIL_HELMET)));
        assertEquals(EquipmentSlot.CHEST, DisplayUtil.getEquipmentSlot(new ItemStack(Material.CHAINMAIL_CHESTPLATE)));
        assertEquals(EquipmentSlot.LEGS, DisplayUtil.getEquipmentSlot(new ItemStack(Material.CHAINMAIL_LEGGINGS)));
        assertEquals(EquipmentSlot.FEET, DisplayUtil.getEquipmentSlot(new ItemStack(Material.CHAINMAIL_BOOTS)));
        // Turtle helmet
        assertEquals(EquipmentSlot.HEAD, DisplayUtil.getEquipmentSlot(new ItemStack(Material.TURTLE_HELMET)));
    }

    @Test
    void headsGoToHead() {
        assertEquals(EquipmentSlot.HEAD, DisplayUtil.getEquipmentSlot(new ItemStack(Material.PLAYER_HEAD)));
        assertEquals(EquipmentSlot.HEAD, DisplayUtil.getEquipmentSlot(new ItemStack(Material.ZOMBIE_HEAD)));
        assertEquals(EquipmentSlot.HEAD, DisplayUtil.getEquipmentSlot(new ItemStack(Material.CREEPER_HEAD)));
        assertEquals(EquipmentSlot.HEAD, DisplayUtil.getEquipmentSlot(new ItemStack(Material.SKELETON_SKULL)));
        assertEquals(EquipmentSlot.HEAD, DisplayUtil.getEquipmentSlot(new ItemStack(Material.WITHER_SKELETON_SKULL)));
        assertEquals(EquipmentSlot.HEAD, DisplayUtil.getEquipmentSlot(new ItemStack(Material.DRAGON_HEAD)));
        assertEquals(EquipmentSlot.HEAD, DisplayUtil.getEquipmentSlot(new ItemStack(Material.CARVED_PUMPKIN)));
        assertEquals(EquipmentSlot.HEAD, DisplayUtil.getEquipmentSlot(new ItemStack(Material.JACK_O_LANTERN)));
    }

    @Test
    void elytraGoesToChest() {
        // Elytra is worn on chest but is not a chestplate - explicit check in code
        assertEquals(EquipmentSlot.CHEST, DisplayUtil.getEquipmentSlot(new ItemStack(Material.ELYTRA)));
    }

    @Test
    void heldBlocksGoToHand() {
        // Items explicitly listed in isHeldBlock() should go to hand even though they are blocks
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(new ItemStack(Material.OAK_SAPLING)));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(new ItemStack(Material.GLASS_PANE)));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(new ItemStack(Material.TORCH)));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(new ItemStack(Material.LADDER)));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(new ItemStack(Material.RAIL)));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(new ItemStack(Material.LEVER)));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(new ItemStack(Material.REDSTONE_TORCH)));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(new ItemStack(Material.IRON_BARS)));
    }

    @Test
    void toolsAndWeaponsGoToHand() {
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(new ItemStack(Material.DIAMOND_SWORD)));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(new ItemStack(Material.DIAMOND_PICKAXE)));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(new ItemStack(Material.DIAMOND_AXE)));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(new ItemStack(Material.DIAMOND_SHOVEL)));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(new ItemStack(Material.DIAMOND_HOE)));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(new ItemStack(Material.SHIELD)));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(new ItemStack(Material.BOW)));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(new ItemStack(Material.CROSSBOW)));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(new ItemStack(Material.FISHING_ROD)));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(new ItemStack(Material.STICK)));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(new ItemStack(Material.BLAZE_ROD)));
    }

    @Test
    void nullAndAirGoToHand() {
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(null));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(new ItemStack(Material.AIR)));
    }
}