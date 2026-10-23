package com.snowgears.shop.util;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DisplayUtilEquipmentSlotTest {

    @Test
    void blocksGoToHand() {
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(new ItemStack(Material.DIRT)));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(new ItemStack(Material.STONE)));
    }

    @Test
    void armorGoesToCorrectSlot() {
        assertEquals(EquipmentSlot.HEAD, DisplayUtil.getEquipmentSlot(new ItemStack(Material.DIAMOND_HELMET)));
        assertEquals(EquipmentSlot.CHEST, DisplayUtil.getEquipmentSlot(new ItemStack(Material.IRON_CHESTPLATE)));
        assertEquals(EquipmentSlot.LEGS, DisplayUtil.getEquipmentSlot(new ItemStack(Material.GOLDEN_LEGGINGS)));
        assertEquals(EquipmentSlot.FEET, DisplayUtil.getEquipmentSlot(new ItemStack(Material.LEATHER_BOOTS)));
    }

    @Test
    void headsGoToHead() {
        assertEquals(EquipmentSlot.HEAD, DisplayUtil.getEquipmentSlot(new ItemStack(Material.PLAYER_HEAD)));
        assertEquals(EquipmentSlot.HEAD, DisplayUtil.getEquipmentSlot(new ItemStack(Material.ZOMBIE_HEAD)));
        assertEquals(EquipmentSlot.HEAD, DisplayUtil.getEquipmentSlot(new ItemStack(Material.CARVED_PUMPKIN)));
    }
}