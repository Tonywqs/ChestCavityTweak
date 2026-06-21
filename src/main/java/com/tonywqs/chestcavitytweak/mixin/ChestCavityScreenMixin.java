package com.tonywqs.chestcavitytweak.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.tonywqs.chestcavitytweak.ChestCavityTweak;
import com.tonywqs.chestcavitytweak.config.ModConfig;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.tigereye.chestcavity.ui.ChestCavityScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Client GUI Mixin for ChestCavityScreen:
 * 1. Adjust imageHeight in init() for configured row count
 * 2. In renderBg() TAIL: extend the background below the original
 *    166px texture by drawing extra slot rows + gap + player section
 *    from the SAME chest_cavity.png texture.
 *
 * This approach keeps the original renderBg intact, so the UV mapping
 * for the first 3 rows is guaranteed correct. Extra content is sampled
 * from the same texture at matching positions.
 */
@Mixin(ChestCavityScreen.class)
public abstract class ChestCavityScreenMixin extends AbstractContainerScreen<AbstractContainerMenu> {

    /** Original chest_cavity.png texture path (set by Hotai patch) */
    @Unique
    private static final ResourceLocation CCT_ORIG_TEX =
            new ResourceLocation("chestcavity", "textures/gui/container/chest_cavity.png");

    /** Slot row strip in texture: y=18-35 (18px) */
    @Unique
    private static final int CCT_ROW_TEX_Y = 18;

    /** Player section in texture: y=83-165 (83px, includes sep + inv + hotbar + bottom) */
    @Unique
    private static final int CCT_PLAYER_TEX_Y = 83;

    @Unique
    private static final int CCT_PLAYER_H = 83;

    /** Container bg color for gap fill: dark brown (34,10,4) */
    @Unique
    private static final int CCT_BG_COLOR = 0xFF220A04;

    @Unique
    private boolean cct_logged = false;

    public ChestCavityScreenMixin(AbstractContainerMenu menu, Inventory playerInv, Component title) {
        super(menu, playerInv, title);
    }

    /**
     * init() TAIL: adjust imageHeight for row count AND update topPos.
     * super.init() already set topPos using the ORIGINAL imageHeight,
     * so we MUST recalculate topPos after changing imageHeight,
     * otherwise the background and slot positions will be misaligned.
     */
    @Inject(method = "init()V", at = @At("TAIL"))
    private void adjustScreenHeight(CallbackInfo ci) {
        int rows = ModConfig.getCavityRows();
        this.imageHeight = rows * 18 + 112;
        this.topPos = (this.height - this.imageHeight) / 2;
        ChestCavityTweak.LOGGER.info("[CCT-Screen] init: rows={}, imageHeight={}, topPos={}", rows, this.imageHeight, this.topPos);
    }

    /**
     * renderBg() TAIL: after the original draws chest_cavity.png (166px),
     * extend the background for extra rows by sampling from the SAME texture.
     *
     * Original renderBg does: blit(x, y, 0, 0, imageWidth, imageHeight)
     * With original chest_cavity.png (content up to y=166), this covers:
     *   - Title (y=0-17)
     *   - 3 cavity slot rows (y=18-71)
     *   - Gap + player + bottom (y=72-165)
     *
     * For rows > 3, imageHeight > 166, so the area below y=166 is
     * transparent. We fill it with extra rows from the same texture.
     */
    @Inject(
            method = "renderBg(Lcom/mojang/blaze3d/vertex/PoseStack;FII)V",
            at = @At("TAIL")
    )
    private void extendBackground(PoseStack matrices, float delta, int mouseX, int mouseY, CallbackInfo ci) {
        int rows = ModConfig.getCavityRows();
        if (rows <= 3) return;

        int guiX = this.leftPos;
        int guiY = this.topPos;
        int extraRows = rows - 3;

        if (!cct_logged) {
            cct_logged = true;
            ChestCavityTweak.LOGGER.info("[CCT-Screen] renderBg TAIL: rows={}, extraRows={}, imageHeight={}, topPos={}, leftPos={}",
                    rows, extraRows, this.imageHeight, this.topPos, this.leftPos);
        }

        // Ensure the original chest_cavity.png is bound
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, CCT_ORIG_TEX);

        // Extra cavity slot rows: sample row strip (y=18-35) from texture
        // and draw at screen positions below the original 3 rows
        // Original 3 rows occupy texture y=18-71 (screen guiY+18 to guiY+71)
        // Extra row r (0-indexed) goes at screen Y = guiY + 72 + r*18
        for (int i = 0; i < extraRows; i++) {
            int screenY = guiY + 72 + i * 18;
            this.blit(matrices, guiX, screenY, 0, CCT_ROW_TEX_Y, this.imageWidth, 18);
        }

        // Positions
        int extraEndScreenY = guiY + 72 + extraRows * 18;  // end of extra rows
        int playerScreenY = guiY + rows * 18 + 30;         // handler player start

        // Fill gap between extra rows and player section with bg color
        if (playerScreenY > extraEndScreenY) {
            GuiComponent.fill(matrices,
                    guiX + 7, extraEndScreenY,
                    guiX + 169, playerScreenY,
                    CCT_BG_COLOR);
        }

        // Player section from original texture at correct screen position
        this.blit(matrices, guiX, playerScreenY, 0, CCT_PLAYER_TEX_Y, this.imageWidth, CCT_PLAYER_H);
    }
}
