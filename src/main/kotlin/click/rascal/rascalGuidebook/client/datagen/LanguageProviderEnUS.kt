package click.rascal.rascalGuidebook.client.datagen

import click.rascal.rascalGuidebook.logger

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.registry.RegistryWrapper.WrapperLookup

import java.util.concurrent.CompletableFuture

class LanguageProviderEnUS(
    dataOutput: FabricDataOutput,
    registryLookup: CompletableFuture<WrapperLookup>
): FabricLanguageProvider(dataOutput, "en_us", registryLookup) {
    override fun generateTranslations(wrapperLookup: WrapperLookup, builder: TranslationBuilder) {
        logger.info("Generating translations for en_us.json...")
        builder.add("item.rascal.guidebook", "Rascal's Field Guide")
        builder.add("tooltip.rascal.guidebook.tooltip", "A guide to all things Rascal")
    }
}