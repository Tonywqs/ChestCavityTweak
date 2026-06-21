package com.tonywqs.chestcavitytweak.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tonywqs.chestcavitytweak.ChestCavityTweak;
import com.tonywqs.chestcavitytweak.config.ModConfig;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.tigereye.chestcavity.ui.ChestCavityScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin on AbstractContainerScreen to intercept renderLabels().
 * For ChestCavityScreen with rows > 3:
 *   - Cancel the original renderLabels (draws inventory label at wrong position)
 *   - Redraw title at original position
 *   - Redraw inventory label in the gap between cavity rows and player inventory
 *
 * This avoids using opaque cover rectangles that hide slot highlights.
 *
 * title/font are inherited from Screen; accessed via cast to avoid
 * @Shadow issues with parent-class fields in ForgeGradle mixin.
 * playerInventoryTitle is private on AbstractContainerScreen, needs @Shadow.
 */
@Mixin(AbstractContainerScreen.class)
public abstract class AbstractContainerScreenMixin {

    @Shadow(aliases = "f_169604_")
    private Component playerInventoryTitle;

    @Unique
    private boolean cct_labelLogged = false;

    @Inject(method = "renderLabels(Lcom/mojang/blaze3d/vertex/PoseStack;II)V", at = @At("HEAD"), cancellable = true)
    private void cct_redirectLabels(PoseStack matrices, int mouseX, int mouseY, CallbackInfo ci) {
        Object self = this;
        if (!(self instanceof ChestCavityScreen)) return;

        int rows = ModConfig.getCavityRows();
        if (rows <= 3) return;

        ci.cancel();

        // Access title and font through Screen cast (protected fields, accessible via inheritance)
        Screen screen = (Screen) self;

        // Title with shadow at original position (guiX+8, guiY+6)
        screen.getMinecraft().font.drawShadow(matrices, screen.getTitle(), 8, 6, 0x404040);

        // Inventory label in the gap between cavity rows and player inventory
        // Gap: from (rows*18+18) to (rows*18+30), 12px total
        // Text at rows*18+20: 2px below last cavity row, 1px above player section
        int labelY = rows * 18 + 20;

        if (!cct_labelLogged) {
            cct_labelLogged = true;
            ChestCavityTweak.LOGGER.info("[CCT-Labels] redirecting: rows={}, labelY={}, title={}, invLabel={}",
                    rows, labelY, screen.getTitle().getString(), this.playerInventoryTitle.getString());
        }

        screen.getMinecraft().font.draw(matrices, this.playerInventoryTitle, 8, labelY, 0x404040);
    }
}
