package com;

import org.lwjgl.glfw.GLFW;
import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    public static class Module {
        public final String name;
        public final String category;
        public boolean active;
        public int key;
        public boolean binding;

        public Module(String name, String category, boolean active, int key) {
            this.name = name;
            this.category = category;
            this.active = active;
            this.key = key;
            this.binding = false;
        }
    }

    public static final List<Module> MODULES = new ArrayList<>();

    static {
        // Mace (12 Módulos do seu print /mace/)
        MODULES.add(new Module("Auto Elytra", "mace", false, GLFW.GLFW_KEY_UNKNOWN));
        MODULES.add(new Module("Auto Grapple", "mace", false, GLFW.GLFW_KEY_UNKNOWN));
        MODULES.add(new Module("Auto Mace", "mace", true, GLFW.GLFW_KEY_UNKNOWN));
        MODULES.add(new Module("Auto Totem", "mace", true, GLFW.GLFW_KEY_UNKNOWN));
        MODULES.add(new Module("Breach Swap", "mace", true, GLFW.GLFW_KEY_UNKNOWN));
        MODULES.add(new Module("Horizontal Pearl Catch", "mace", false, GLFW.GLFW_KEY_UNKNOWN));
        MODULES.add(new Module("Key Pearl", "mace", false, GLFW.GLFW_KEY_UNKNOWN));
        MODULES.add(new Module("Key Wind Charge", "mace", false, GLFW.GLFW_KEY_UNKNOWN));
        MODULES.add(new Module("Lunge Macro", "mace", false, GLFW.GLFW_KEY_UNKNOWN));
        MODULES.add(new Module("Pearl Catch Macro", "mace", false, GLFW.GLFW_KEY_UNKNOWN));
        MODULES.add(new Module("Pearl Charge", "mace", false, GLFW.GLFW_KEY_UNKNOWN));
        MODULES.add(new Module("Pearl Chase", "mace", false, GLFW.GLFW_KEY_UNKNOWN));

        // Sword (2)
        MODULES.add(new Module("Auto Sword", "sword", true, GLFW.GLFW_KEY_R));
        MODULES.add(new Module("Shield Breaker", "sword", true, GLFW.GLFW_KEY_UNKNOWN));

        // Spear (1)
        MODULES.add(new Module("Spear Aura", "spear", true, GLFW.GLFW_KEY_UNKNOWN));

        // UHC (2)
        MODULES.add(new Module("Auto Gapple", "uhc", true, GLFW.GLFW_KEY_UNKNOWN));
        MODULES.add(new Module("Fast Eat", "uhc", true, GLFW.GLFW_KEY_UNKNOWN));

        // Nethpot (1)
        MODULES.add(new Module("Auto Pot", "nethpot", true, GLFW.GLFW_KEY_X));

        // SMP (2)
        MODULES.add(new Module("Auto Totem", "smp", true, GLFW.GLFW_KEY_UNKNOWN));
        MODULES.add(new Module("Chest Stealer", "smp", false, GLFW.GLFW_KEY_K));

        // Cart (1)
        MODULES.add(new Module("Auto Cart", "cart", true, GLFW.GLFW_KEY_C));

        // Crystal (2)
        MODULES.add(new Module("Auto Crystal", "crystal", true, GLFW.GLFW_KEY_Z));
        MODULES.add(new Module("Anchor Aura", "crystal", true, GLFW.GLFW_KEY_UNKNOWN));

        // Visual (Os 6 Módulos do seu print /visual/)
        MODULES.add(new Module("Crosshair", "visual", true, GLFW.GLFW_KEY_UNKNOWN));
        MODULES.add(new Module("ElytraPath", "visual", false, GLFW.GLFW_KEY_UNKNOWN));
        MODULES.add(new Module("ESP", "visual", true, GLFW.GLFW_KEY_UNKNOWN));
        MODULES.add(new Module("FullBright", "visual", true, GLFW.GLFW_KEY_UNKNOWN));
        MODULES.add(new Module("MaceVisualizer", "visual", true, GLFW.GLFW_KEY_UNKNOWN));
        MODULES.add(new Module("Trajectories", "visual", false, GLFW.GLFW_KEY_UNKNOWN));
    }
}
