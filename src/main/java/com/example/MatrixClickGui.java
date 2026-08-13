package com;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class MatrixClickGui extends Screen {

    private static MatrixClickGui INSTANCE;

    public static MatrixClickGui getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MatrixClickGui();
        }
        return INSTANCE;
    }

    private static final int COLOR_BG = 0xEE0B0E14;
    private static final int COLOR_HEADER = 0xFF11151F;
    private static final int COLOR_ACCENT = 0xFF00FF66;
    private static final int COLOR_GLOW = 0x4400FF66;
    private static final int COLOR_CARD_ACTIVE = 0xFF14241B;
    private static final int COLOR_CARD_INACTIVE = 0xFF161A24;
    private static final int COLOR_TEXT_WHITE = 0xFFFFFFFF;
    private static final int COLOR_TEXT_MUTED = 0xFF708090;

    private final String[] categories = {"mace", "sword", "spear", "uhc", "nethpot", "smp", "cart", "crystal", "visual"};
    private String selectedCategory = "mace";

    private MatrixClickGui() {
        super(Text.literal("Matrix"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);

        final int winWidth = 760;
        final int winHeight = 480;
        final int x = (this.width - winWidth) / 2;
        final int y = (this.height - winHeight) / 2;

        context.fill(x, y, x + winWidth, y + winHeight, COLOR_BG);
        context.drawBorder(x, y, winWidth, winHeight, COLOR_ACCENT);

        context.fill(x, y, x + winWidth, y + 45, COLOR_HEADER);
        context.drawText(this.textRenderer, "MATRIX", x + 20, y + 18, COLOR_ACCENT, true);

        int catX = x + 120;
        for (String cat : categories) {
            boolean isSelected = cat.equalsIgnoreCase(selectedCategory);
            int catColor = isSelected ? COLOR_ACCENT : COLOR_TEXT_MUTED;

            if (isSelected) {
                context.fill(catX - 4, y + 12, catX + 64, y + 32, COLOR_GLOW);
                context.drawBorder(catX - 4, y + 12, 68, 20, COLOR_ACCENT);
            }

            context.drawText(this.textRenderer, cat.toUpperCase(), catX, y + 18, catColor, false);
            catX += 68;
        }

        int cardX = x + 20;
        int cardY = y + 60;
        final int cardW = 230;
        final int cardH = 55;
        int col = 0;

        for (int i = 0; i < ModuleManager.MODULES.size(); i++) {
            ModuleManager.Module m = ModuleManager.MODULES.get(i);
            if (!m.category.equalsIgnoreCase(selectedCategory)) continue;

            int bgCard = m.active ? COLOR_CARD_ACTIVE : COLOR_CARD_INACTIVE;
            int borderCard = m.active ? COLOR_ACCENT : 0xFF222834;

            context.fill(cardX, cardY, cardX + cardW, cardY + cardH, bgCard);
            context.drawBorder(cardX, cardY, cardW, cardH, borderCard);

            context.drawText(this.textRenderer, m.name, cardX + 12, cardY + 14, COLOR_TEXT_WHITE, true);

            String keyText = m.binding ? "PRESS..." : (m.key == GLFW.GLFW_KEY_UNKNOWN ? "[NONE]" : "[" + GLFW.glfwGetKeyName(m.key, 0) + "]");
            context.drawText(this.textRenderer, keyText, cardX + 12, cardY + 32, m.binding ? COLOR_ACCENT : COLOR_TEXT_MUTED, false);

            int switchX = cardX + cardW - 35;
            int switchY = cardY + 18;
            int switchColor = m.active ? COLOR_ACCENT : 0xFF2A3040;
            context.fill(switchX, switchY, switchX + 24, switchY + 14, switchColor);
            
            int knobX = m.active ? switchX + 12 : switchX + 2;
            context.fill(knobX, switchY + 2, knobX + 10, switchY + 12, COLOR_TEXT_WHITE);

            col++;
            cardX += cardW + 12;
            if (col >= 3) {
                col = 0;
                cardX = x + 20;
                cardY += cardH + 10;
            }
        }

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        final int winWidth = 760;
        final int winHeight = 480;
        final int x = (this.width - winWidth) / 2;
        final int y = (this.height - winHeight) / 2;

        int catX = x + 120;
        for (String cat : categories) {
            if (mouseX >= catX - 4 && mouseX <= catX + 64 && mouseY >= y + 12 && mouseY <= y + 32) {
                this.selectedCategory = cat;
                return true;
            }
            catX += 68;
        }

        int cardX = x + 20;
        int cardY = y + 60;
        final int cardW = 230;
        final int cardH = 55;
        int col = 0;

        for (int i = 0; i < ModuleManager.MODULES.size(); i++) {
            ModuleManager.Module m = ModuleManager.MODULES.get(i);
            if (!m.category.equalsIgnoreCase(selectedCategory)) continue;

            if (mouseX >= cardX && mouseX <= cardX + cardW && mouseY >= cardY && mouseY <= cardY + cardH) {
                if (mouseX >= cardX + 10 && mouseX <= cardX + 80 && mouseY >= cardY + 28 && mouseY <= cardY + 45) {
                    m.binding = !m.binding;
                } else {
                    m.active = !m.active;
                }
                return true;
            }

            col++;
            cardX += cardW + 12;
            if (col >= 3) {
                col = 0;
                cardX = x + 20;
                cardY += cardH + 10;
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        for (int i = 0; i < ModuleManager.MODULES.size(); i++) {
            ModuleManager.Module m = ModuleManager.MODULES.get(i);
            if (m.binding) {
                m.key = (keyCode == GLFW.GLFW_KEY_ESCAPE) ? GLFW.GLFW_KEY_UNKNOWN : keyCode;
                m.binding = false;
                return true;
            }
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
