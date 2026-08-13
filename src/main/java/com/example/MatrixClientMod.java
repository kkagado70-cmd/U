package com;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class MatrixClientMod implements ClientModInitializer {

    private static KeyBinding guiKey;

    @Override
    public void onInitializeClient() {
        // Pressione a tecla 'P' para abrir no jogo
        guiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.matrix.open_gui",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_P,
                "category.matrix.general"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (guiKey.wasPressed()) {
                if (client.player != null) {
                    client.setScreen(MatrixClickGui.getInstance());
                }
            }
        });
    }
}
