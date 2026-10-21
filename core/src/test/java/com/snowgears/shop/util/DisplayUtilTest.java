package com.snowgears.shop.util;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Regression tests for {@link DisplayUtil#getEquipmentSlot(ItemStack)} (issue #909).
 *
 * <p>The old implementation inferred the armor-stand slot from the material name: anything matching
 * {@code "_HELMET"} or {@code Material#isBlock()} went to HEAD, so every block item — dirt, stone,
 * logs — rendered on the stand's head, and any helmet-like material whose name merely contained
 * that sequence was misclassified. This class pins the slot each material type belongs to.
 *
 * <p>Item stacks are mocked rather than constructed: {@code getEquipmentSlot} only ever reads
 * {@code getType()}, and a real {@code new ItemStack(...)} pulls in the server's item registry,
 * which is unavailable in a pure unit test and which rejects skull materials outright.
 */
@Tag("unit")
public class DisplayUtilTest {

    private static ItemStack item(Material material) {
        ItemStack item = mock(ItemStack.class);
        when(item.getType()).thenReturn(material);
        return item;
    }

    @Test
    void plainBlockGoesToHandNotHead() {
        // The bug: Material.isBlock() routed every block to the HEAD slot.
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(item(Material.DIRT)),
                "Dirt is a block, but it is held, not worn");
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(item(Material.STONE)),
                "Stone is a block, but it is held, not worn");
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(item(Material.OAK_WOOD)),
                "Wood is a block, but it is held, not worn");
    }

    @Test
    void heldNonBlockItemsGoToHand() {
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(item(Material.DIAMOND)));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(item(Material.EMERALD)));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(item(Material.BREAD)));
    }

    @Test
    void armorPiecesGoToTheirCorrectSlots() {
        assertEquals(EquipmentSlot.HEAD, DisplayUtil.getEquipmentSlot(item(Material.IRON_HELMET)),
                "Iron helmet -> HEAD");
        assertEquals(EquipmentSlot.HEAD, DisplayUtil.getEquipmentSlot(item(Material.DIAMOND_HELMET)),
                "Diamond helmet -> HEAD");
        assertEquals(EquipmentSlot.HEAD, DisplayUtil.getEquipmentSlot(item(Material.NETHERITE_HELMET)),
                "Netherite helmet -> HEAD");
        assertEquals(EquipmentSlot.HEAD, DisplayUtil.getEquipmentSlot(item(Material.TURTLE_HELMET)),
                "Turtle helmet -> HEAD");
        assertEquals(EquipmentSlot.HEAD, DisplayUtil.getEquipmentSlot(item(Material.LEATHER_HELMET)),
                "Leather helmet -> HEAD");
        assertEquals(EquipmentSlot.HEAD, DisplayUtil.getEquipmentSlot(item(Material.CARVED_PUMPKIN)),
                "Carved pumpkin -> HEAD");
        assertEquals(EquipmentSlot.HEAD, DisplayUtil.getEquipmentSlot(item(Material.PLAYER_HEAD)),
                "Player head -> HEAD");

        assertEquals(EquipmentSlot.CHEST, DisplayUtil.getEquipmentSlot(item(Material.IRON_CHESTPLATE)),
                "Iron chestplate -> CHEST");
        assertEquals(EquipmentSlot.CHEST, DisplayUtil.getEquipmentSlot(item(Material.DIAMOND_CHESTPLATE)),
                "Diamond chestplate -> CHEST");
        assertEquals(EquipmentSlot.CHEST, DisplayUtil.getEquipmentSlot(item(Material.ELYTRA)),
                "Elytra -> CHEST");

        assertEquals(EquipmentSlot.LEGS, DisplayUtil.getEquipmentSlot(item(Material.IRON_LEGGINGS)),
                "Iron leggings -> LEGS");
        assertEquals(EquipmentSlot.LEGS, DisplayUtil.getEquipmentSlot(item(Material.DIAMOND_LEGGINGS)),
                "Diamond leggings -> LEGS");

        assertEquals(EquipmentSlot.FEET, DisplayUtil.getEquipmentSlot(item(Material.IRON_BOOTS)),
                "Iron boots -> FEET");
        assertEquals(EquipmentSlot.FEET, DisplayUtil.getEquipmentSlot(item(Material.DIAMOND_BOOTS)),
                "Diamond boots -> FEET");
    }

    @Test
    void skullItemsGoToHead() {
        assertEquals(EquipmentSlot.HEAD, DisplayUtil.getEquipmentSlot(item(Material.SKELETON_SKULL)),
                "Skeleton skull -> HEAD");
        assertEquals(EquipmentSlot.HEAD, DisplayUtil.getEquipmentSlot(item(Material.WITHER_SKELETON_SKULL)),
                "Wither skeleton skull -> HEAD");
        assertEquals(EquipmentSlot.HEAD, DisplayUtil.getEquipmentSlot(item(Material.ZOMBIE_HEAD)),
                "Zombie head -> HEAD");
        assertEquals(EquipmentSlot.HEAD, DisplayUtil.getEquipmentSlot(item(Material.CREEPER_HEAD)),
                "Creeper head -> HEAD");
        assertEquals(EquipmentSlot.HEAD, DisplayUtil.getEquipmentSlot(item(Material.DRAGON_HEAD)),
                "Dragon head -> HEAD");
    }

    @Test
    void unknownMaterialFallsBackToHand() {
        // A fallback, not a wildcard — the slot must be explicit or unknown items stay in hand.
        // An item that is neither armor nor a held block must never silently land on a body slot,
        // which is what the old isBlock() branch did for every block material.
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(item(Material.DIAMOND)));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(item(Material.EMERALD)));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(item(Material.BREAD)));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(item(Material.GOLD_INGOT)));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(item(Material.STRING)));
        assertEquals(EquipmentSlot.HAND, DisplayUtil.getEquipmentSlot(item(Material.BUCKET)));
    }
}