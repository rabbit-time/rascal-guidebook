package click.rascal.rascalGuidebook.item

import click.rascal.rascalGuidebook.logger

import io.wispforest.lavender.book.LavenderBookItem
import net.minecraft.item.ItemStack
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.Identifier

class GuidebookItem(settings: Settings, bookId: Identifier): LavenderBookItem(settings, bookId) {
    companion object {
        val ID: Identifier = Identifier.of("rascal", "guidebook")
        val GUIDEBOOK: GuidebookItem = register(Settings().maxCount(1), ID)
        fun init() = Unit
        fun register(settings: Settings, id: Identifier): GuidebookItem {
            val guidebookItem: GuidebookItem = Registry.register(
                Registries.ITEM,
                id,
                GuidebookItem(settings, id)
            )
            logger.info("Registered guidebook as ${Registries.ITEM.getId(guidebookItem)}")
            return registerForBook(guidebookItem) as GuidebookItem
        }
    }
    override fun getName(stack: ItemStack): Text {
        return (super.getName(stack) as MutableText).styled {
            it.withColor(Formatting.RED)
        }
    }
    override fun appendTooltip(stack: ItemStack, context: TooltipContext, tooltip: MutableList<Text>, type: TooltipType) {
        tooltip.add(Text.translatable("tooltip.rascal.guidebook.tooltip").styled {
            it.withColor(Formatting.GRAY)
        })
        super.appendTooltip(stack, context, tooltip, type)
    }
}