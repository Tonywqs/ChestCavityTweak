package com.tonywqs.chestcavitytweak.mixin;

import com.tonywqs.chestcavitytweak.ChestCavityTweak;
import com.tonywqs.chestcavitytweak.config.ModConfig;
import net.minecraft.world.SimpleContainer;
import net.tigereye.chestcavity.chestcavities.ChestCavityInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

/**
 * 修改胸腔背包的默认大小
 * <p>
 * 使用两种方式确保注入成功：
 * 1. @ModifyConstant 直接替换构造函数中的硬编码常量 27
 * 2. @ModifyArg 拦截传给 SimpleContainer 构造函数的参数
 */
@Mixin(ChestCavityInventory.class)
public abstract class ChestCavityInventoryMixin extends SimpleContainer {

    /**
     * 方式一：直接替换构造函数中的常量 27
     */
    @ModifyConstant(method = "<init>()V", constant = @Constant(intValue = 27))
    private static int modifyCavitySizeConstant(int original) {
        int newSize = ModConfig.getCavityRows() * 9;
        ChestCavityTweak.LOGGER.info("[ChestCavityTweak] @ModifyConstant fired: {} -> {}", original, newSize);
        return newSize;
    }

    /**
     * 方式二：拦截传给 SimpleContainer(int) 的参数（备选方案）
     */
    @ModifyArg(
            method = "<init>()V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/SimpleContainer;<init>(I)V")
    )
    private static int replaceDefaultCavitySize(int originalSize) {
        int newSize = ModConfig.getCavityRows() * 9;
        ChestCavityTweak.LOGGER.info("[ChestCavityTweak] @ModifyArg fired: {} -> {}", originalSize, newSize);
        return newSize;
    }
}
