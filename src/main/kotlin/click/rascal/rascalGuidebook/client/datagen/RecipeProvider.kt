package click.rascal.rascalGuidebook.client.datagen

import click.rascal.rascalGuidebook.item.GuidebookItem
import click.rascal.rascalGuidebook.logger

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder
import net.minecraft.item.Items
import net.minecraft.recipe.book.RecipeCategory
import net.minecraft.registry.RegistryWrapper.WrapperLookup

import java.util.concurrent.CompletableFuture

class RecipeProvider(
    dataOutput: FabricDataOutput,
    registriesFuture: CompletableFuture<WrapperLookup>
): FabricRecipeProvider(dataOutput, registriesFuture) {
    override fun generate(exporter: RecipeExporter) {
        logger.info("Generating recipes...")
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, GuidebookItem.GUIDEBOOK, 1)
            .input(Items.BOOK)
            .input(Items.COOKIE)
            .criterion(hasItem(Items.BOOK), conditionsFromItem(Items.BOOK))
            .offerTo(exporter, GuidebookItem.ID)
    }
}