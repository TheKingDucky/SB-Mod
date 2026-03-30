package com.example.commands;

import com.example.ClientInit;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public final class PandaCommands {

    private PandaCommands() {}

    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                    ClientCommandManager.literal("pandaon")
                            .executes(ctx -> {
                                FabricClientCommandSource src = ctx.getSource();
                                ClientInit.setPandaEnabled(true);
                                if (src.getPlayer() != null) {
                                    src.getPlayer().displayClientMessage(Component.literal("§l§aPanda tracking enabled."), false);
                                } else {
                                    Minecraft.getInstance().player.displayClientMessage(Component.literal("§l§aPanda tracking enabled."), false);
                                }
                                return 1;
                            })
            );

            dispatcher.register(
                    ClientCommandManager.literal("pandaoff")
                            .executes(ctx -> {
                                FabricClientCommandSource src = ctx.getSource();
                                ClientInit.setPandaEnabled(false);
                                if (src.getPlayer() != null) {
                                    src.getPlayer().displayClientMessage(Component.literal("§l§cPanda tracking disabled."), false);
                                } else {
                                    Minecraft.getInstance().player.displayClientMessage(Component.literal("§l§cPanda tracking disabled."), false);
                                }
                                return 1;
                            })
            );
        });
    }
}