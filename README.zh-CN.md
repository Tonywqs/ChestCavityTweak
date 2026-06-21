# ChestCavityTweak

[English](README.md)

一个基于 [ChestCavity](https://github.com/Tigereye504/chestcavity) 的 Forge 1.19.2 附属模组，可配置胸腔背包的行数（3–6 行，即 27–54 格）。

## 重要声明

**本模组专为"脆骨症"(No Flesh Within Chest) 整合包 v1.0.2-DIM 设计。** 它在运行时通过 KubeJS/Hotai 引用该整合包提供的 GUI 纹理，**未安装该整合包时将无法正确显示**。本模组不是独立的或通用的附属模组。

## 功能特性

- 通过服务端配置调整胸腔背包大小（`cavityRows`：3、4、5 或 6 行）
- 自动扩展 GUI 背景以适配额外行数（像素级精确对齐）
- "物品栏"标签正确重定位到胸腔行与玩家背包之间的间隔区
- 槽位选中高亮和物品提示渲染不受影响
- 默认值：6 行（54 格）

## 运行环境要求

- Minecraft 1.19.2
- Forge 43.3.5
- [ChestCavity](https://github.com/Tigereye504/chestcavity)（Forge 移植版）
- **"脆骨症"(No Flesh Within Chest) 整合包 v1.0.2-DIM** — 提供 GUI 纹理

## 配置

首次启动后，配置文件生成于：
```
<世界>/serverconfig/chestcavitytweak-server.toml
```

```toml
[chest_cavity_settings]
    # 胸腔背包行数。有效值：3、4、5、6。
    # 世界/服务器重启后生效。
    # 减小大小可能导致多余槽位中的物品丢失。
    # 范围：3 ~ 6
    cavityRows = 6
```

## 从源码构建

1. 将 ChestCavity Forge jar 放入 `libs/` 目录（仅编译依赖）
2. 运行：
   ```
   gradlew build
   ```
3. 输出 jar：`build/libs/chestcavitytweak-1.19.2-1.0.0.jar`

## 许可证

本项目基于 **MIT 许可证** 开源 — 详见 [LICENSE](LICENSE) 文件。

## 知识产权与署名归属

### ChestCavity

ChestCavity 是由 **Tigereye504** 创作并维护的模组。
- 源码仓库：[https://github.com/Tigereye504/chestcavity](https://github.com/Tigereye504/chestcavity)

本附属模组**不**包含、再分发或修改任何 ChestCavity 的源代码、素材或资源。它使用 [SpongePowered Mixin](https://github.com/SpongePowered/Mixin) 在运行时注入字节码，并以 `compileOnly` 方式依赖 ChestCavity（不打包进输出 jar）。

### "脆骨症"(No Flesh Within Chest) 整合包

本模组在运行时引用的 GUI 纹理归属于 **"脆骨症"(No Flesh Within Chest) 整合包**。本模组**不**包含或再分发该整合包的任何纹理或素材。纹理 `chestcavity:chest_cavity.png` 由整合包的 KubeJS/Hotai 资源覆盖在运行时提供。

### Minecraft / Forge

Minecraft 是 Mojang Studios / Microsoft Corporation 的商标。Forge 是免费开源的模组开发 API。本项目与 Mojang Studios、Microsoft Corporation 或 Forge 团队无关，未获其认可或赞助。

### 免责声明

本模组是一个独立的、由爱好者制作的附属模组。ChestCavity 原版模组和"脆骨症"整合包的所有权利归其各自作者所有。本项目不对任何引用的素材、名称或商标主张所有权。
