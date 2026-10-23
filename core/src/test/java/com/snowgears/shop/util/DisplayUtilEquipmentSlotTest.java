package com.snowgears.shop.util;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockbukkit.mockbukkit.MockBukkitExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockBukkitExtension.class)
class DisplayUtilEquipmentSlotTest {

    @Test
    void blockItemsAreDisplayedInHandNotOnHead() {
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(new ItemStack(Material.DIRT)),
                "A plain block must be displayed in the hand, not on the head");
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(new ItemStack(Material.STONE)));
    }

    @Test
    void helmetsUseHeadSlot() {
        assertEquals(EquipmentSlot.HEAD, DisplayUtil.getEquipmentSlot(new ItemStack(Material.IRON_HELMET)));
        assertEquals(EquipmentSlot.HEAD, DisplayUtil.getEquipmentSlot(new ItemStack(Material.PLAYER_HEAD)));
    }

    @Test
    void armorUsesCorrectSlots() {
        assertEquals(EquipmentSlot.CHEST, DisplayUtil.getEquipmentSlot(new ItemStack(Material.IRON_CHESTPLATE)));
        assertEquals(EquipmentSlot.LEGS, DisplayUtil.getEquipmentSlot(new ItemStack(Material.IRON_LEGGINGS)));
        assertEquals(EquipmentSlot.FEET, DisplayUtil.getEquipmentSlot(new ItemStack(Material.IRON_BOOTS)));
    }

    @Test
    void elytraUsesChest() {
        assertEquals(EquipmentSlot.CHEST, DisplayUtil.getEquipmentSlot(new ItemStack(Material.ELYTRA)));
    }
}