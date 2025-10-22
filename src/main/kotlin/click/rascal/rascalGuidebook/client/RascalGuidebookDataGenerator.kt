package click.rascal.rascalGuidebook.client

import click.rascal.rascalGuidebook.client.datagen.AdvancementProvider
import click.rascal.rascalGuidebook.client.datagen.LanguageProviderEnUS
import click.rascal.rascalGuidebook.client.datagen.RecipeProvider
import click.rascal.rascalGuidebook.logger

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

class RascalGuidebookDataGenerator : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
        logger.info("Initializing data generators...")
        val pack: FabricDataGenerator.Pack = fabricDataGenerator.createPack()
        pack.addProvider(::AdvancementProvider)
        pack.addProvider(::LanguageProviderEnUS)
        pack.addProvider(::RecipeProvider)
    }
}
