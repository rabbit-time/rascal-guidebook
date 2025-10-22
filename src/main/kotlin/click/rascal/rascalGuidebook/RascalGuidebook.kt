package click.rascal.rascalGuidebook

import click.rascal.rascalGuidebook.item.GuidebookItem

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
import net.minecraft.advancement.AdvancementEntry
import net.minecraft.advancement.AdvancementProgress
import net.minecraft.item.ItemStack
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayNetworkHandler
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.Identifier

import org.slf4j.Logger
import org.slf4j.LoggerFactory

val logger: Logger = RascalGuidebook.logger

class RascalGuidebook : ModInitializer {
    companion object {
        const val MOD_ID: String = "rascal-guidebook"
        val logger: Logger = LoggerFactory.getLogger(MOD_ID)
    }
    private fun getAdvancementProgress(id: String, player: ServerPlayerEntity, server: MinecraftServer): AdvancementProgress? {
        val advancementID: Identifier = Identifier.of(id)
        val advancementEntry: AdvancementEntry? = server.advancementLoader.get(advancementID)
        return player.advancementTracker.getProgress(advancementEntry)
    }
    override fun onInitialize() {
        logger.info("Initializing Rascal Guidebook...")
        GuidebookItem.init()
        ServerPlayConnectionEvents.JOIN.register { handler: ServerPlayNetworkHandler, _, server: MinecraftServer ->
            val player: ServerPlayerEntity = handler.player
            logger.info("Checking if ${player.name.string} received guidebook...")

            val advancementID: String = "rascal/received_guidebook"
            val receivedGuidebook: AdvancementProgress = getAdvancementProgress(advancementID, player, server) ?: run {
                logger.info("Could not find $advancementID")
                return@register
            }
            if (!receivedGuidebook.isDone) {
                player.giveItemStack(ItemStack(GuidebookItem.GUIDEBOOK))
                logger.info("Gave ${player.name.string} the guidebook")
            } else {
                logger.info("${player.name.string} already received guidebook. Skipping...")
            }
        }
    }
}