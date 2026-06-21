package com.tonywqs.chestcavitytweak.config;

import net.minecraftforge.common.ForgeConfigSpec;

/**
 * ChestCavityTweak 配置类
 * 使用 ForgeConfigSpec 定义服务端配置，控制胸腔背包的行数
 */
public class ModConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    // 胸腔背包行数：只能是 3、4、5、6
    public static final ForgeConfigSpec.IntValue CAVITY_ROWS;

    static {
        BUILDER.push("chest_cavity_settings");

        CAVITY_ROWS = BUILDER
                .comment("Number of rows in the chest cavity inventory. Valid values: 3, 4, 5, 6.")
                .comment("Takes effect after world/server restart. Reducing the size may cause items in excess slots to be lost.")
                .defineInRange("cavityRows", 6, 3, 6);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    /**
     * 获取胸腔背包的行数（3~6）
     * 在配置尚未加载时（如ChestCavity类静态初始化期间）回退到3（原版默认值），防止崩溃
     */
    public static int getCavityRows() {
        try {
            if (CAVITY_ROWS == null) return 3;
            int val = CAVITY_ROWS.get();
            if (val < 3 || val > 6) return 3;
            return val;
        } catch (Exception e) {
            // 配置尚未加载，回退到原版3行
            return 3;
        }
    }
}
