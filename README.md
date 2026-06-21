# ChestCavityTweak

[English](README.en.md)

一个基于 [Chest Cavity - Forge Port](https://github.com/BoonelDanForever/ChestCavityForge) 的 Forge 1.19.2 附属模组，可配置胸腔背包的行数（4–6 行，即 36–54 格）。

## 重要声明

**本模组专为"脆骨症黯光"整合包 v1.0.2-DIM 设计。** 该整合包是 [脆骨症 (No Flesh Within Chest)](https://github.com/Go-Camping/No-Flesh-Within-Chest) 的一个 [分支](https://github.com/Go-Camping/No-Flesh-Within-Chest/tree/version_all_dim)。本模组在运行时通过 KubeJS/Hotai 引用该整合包提供的 GUI 纹理，**未安装该整合包时将无法正确显示**。本模组不是独立的或通用的附属模组。

**版本限定声明：** 本模组仅适用于脆骨症黯光-1.0.2-DIM（No Flesh Within Chest-1.0.2-DIM）版本。其余所有版本（包括原始脆骨症版本及该分支的其他版本）均未经测试，适配度及稳定性未知。在非目标版本中使用所造成的一切后果由使用者自行承担。

**本模组由 AI 辅助生成。** 项目代码通过 [QoderWork](https://qoder.com) AI 编程助手自动生成，包括 Mixin 注入、配置系统、GUI 渲染逻辑等核心功能。

## 功能特性

- 通过服务端配置调整胸腔背包行数（`cavityRows`：4、5 或 6 行），默认 6 行（54 格）
- 本模组仅支持 4、5、6 行胸腔背包，设置为其他行数可能导致 GUI 错位或功能异常。如有其他行数需求，请自行修改源码适配

## 运行环境要求

- Minecraft 1.19.2
- Forge 43.3.5
- [Chest Cavity - Forge Port](https://github.com/BoonelDanForever/ChestCavityForge)（ChestCavity 的 Forge 移植版）
- **"脆骨症黯光"整合包 v1.0.2-DIM** — 提供 GUI 纹理

## 配置

首次启动后，配置文件生成于：
```
<世界>/serverconfig/chestcavitytweak-server.toml
```

```toml
[chest_cavity_settings]
    # 胸腔背包行数。有效值：4、5、6。
    # 世界/服务器重启后生效。
    # 减小大小可能导致多余槽位中的物品丢失。
    # 范围：4 ~ 6
    cavityRows = 6
```

## 从源码构建

1. 将 ChestCavity Forge jar 放入 `libs/` 目录（仅编译依赖）
2. 运行：
   ```
   gradlew build
   ```
3. 输出 jar：`build/libs/chestcavitytweak-1.19.2-0.1.0.jar`

## 许可证

本项目基于 **MIT 许可证** 开源 — 详见 [LICENSE](LICENSE) 文件。

## 二次修改与衍生声明

你可以在 MIT 许可证的条款下自由修改、衍生或重新分发本模组的代码。但请在你的衍生作品中遵守以下要求：

1. **保留本模组署名** — 明确注明原模组名称 (ChestCavityTweak)、作者 (Tonywqs) 及源码仓库链接。
2. **保留前置模组署名** — 明确注明 ChestCavity (Tigereye504)、Chest Cavity - Forge Port (BoonelDanForever) 及"脆骨症黯光"整合包 (Go-Camping) 的署名与链接。
3. **保留许可声明** — 在你的衍生作品中包含本项目的 MIT 许可证原文及知识产权与署名归属章节的内容。

## 知识产权与署名归属

### ChestCavity / Chest Cavity - Forge Port

ChestCavity 是由 **Tigereye504** 创作并维护的 Fabric 模组。
- 原版源码仓库：[https://github.com/Tigereye504/chestcavity](https://github.com/Tigereye504/chestcavity)

Chest Cavity - Forge Port 是由 **BoonelDanForever** 维护的 Forge 移植版。
- Forge Port 源码仓库：[https://github.com/BoonelDanForever/ChestCavityForge](https://github.com/BoonelDanForever/ChestCavityForge)

本附属模组**不**包含、再分发或修改任何 ChestCavity 或 Chest Cavity - Forge Port 的源代码、素材或资源。它使用 [SpongePowered Mixin](https://github.com/SpongePowered/Mixin) 在运行时注入字节码，并以 `compileOnly` 方式依赖 Chest Cavity - Forge Port（不打包进输出 jar）。

### "脆骨症黯光"整合包

"脆骨症黯光"是 [脆骨症 (No Flesh Within Chest)](https://github.com/Go-Camping/No-Flesh-Within-Chest) 的一个[分支](https://github.com/Go-Camping/No-Flesh-Within-Chest/tree/version_all_dim)，由 **Go-Camping** 维护。

本模组在运行时引用的 GUI 纹理归属于**"脆骨症黯光"整合包**。本模组**不**包含或再分发该整合包的任何纹理或素材。纹理 `chestcavity:chest_cavity.png` 由整合包的 KubeJS/Hotai 资源覆盖在运行时提供。

### Minecraft / Forge

Minecraft 是 Mojang Studios / Microsoft Corporation 的商标。Forge 是免费开源的模组开发 API。本项目与 Mojang Studios、Microsoft Corporation 或 Forge 团队无关，未获其认可或赞助。

### 免责声明

本模组是一个独立的、由爱好者制作的附属模组。ChestCavity 原版模组、Chest Cavity - Forge Port 和"脆骨症黯光"整合包的所有权利归其各自作者所有。本项目不对任何引用的素材、名称或商标主张所有权。
