package click.rascal.rascalGuidebook.client.datagen

import click.rascal.rascalGuidebook.item.GuidebookItem
import click.rascal.rascalGuidebook.logger

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider
import net.minecraft.advancement.Advancement
import net.minecraft.advancement.AdvancementEntry
import net.minecraft.advancement.AdvancementFrame
import net.minecraft.advancement.criterion.InventoryChangedCriterion
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import net.minecraft.text.Text
import net.minecraft.util.Identifier

import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

class AdvancementProvider(
    output: FabricDataOutput,
    registryLookup: CompletableFuture<WrapperLookup>
): FabricAdvancementProvider(output, registryLookup) {
    override fun generateAdvancement(wrapperLookup: WrapperLookup, consumer: Consumer<AdvancementEntry>) {
        logger.info("Generating advancements...")
        Advancement.Builder.create()
            .display(
                GuidebookItem.GUIDEBOOK,  // The display icon
                Text.literal("Received Rascal's Field Guide"),  // The title
                Text.literal("Read it to your heart's content"),  // The description
                Identifier.ofVanilla("textures/gui/advancements/backgrounds/adventure.png"),
                AdvancementFrame.TASK,
                false,
                false,
                true
            )
            .criterion(
                "received_guidebook",
                InventoryChangedCriterion.Conditions.items(GuidebookItem.GUIDEBOOK)
            )
            .build(consumer,  "rascal/received_guidebook")
    }
}