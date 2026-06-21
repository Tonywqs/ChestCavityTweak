package com.tonywqs.chestcavitytweak;

import com.tonywqs.chestcavitytweak.config.ModConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixins;

@Mod(ChestCavityTweak.MOD_ID)
public class ChestCavityTweak {
    public static final String MOD_ID = "chestcavitytweak";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public ChestCavityTweak() {
        // 编程方式注册 mixin 配置作为兜底（mods.toml 声明优先）
        Mixins.addConfiguration("mixins.chestcavitytweak.json");

        // 注册服务端配置
        ModLoadingContext.get().registerConfig(Type.SERVER, ModConfig.SPEC);
        MinecraftForge.EVENT_BUS.register(this);
        LOGGER.info("[ChestCavityTweak] Mod initialized. Cavity rows = {} ({} slots)",
                ModConfig.getCavityRows(), ModConfig.getCavityRows() * 9);
    }
}
