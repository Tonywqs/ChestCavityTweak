# ChestCavityTweak

[中文](README.md)

A Forge 1.19.2 add-on mod for [Chest Cavity - Forge Port](https://github.com/BoonelDanForever/ChestCavityForge) that allows configurable chest cavity inventory rows (4–6 rows, i.e. 36–54 slots).

## Important Notice

**This mod is specifically designed for the "脆骨症黯光" modpack v1.0.2-DIM.** This modpack is a [fork](https://github.com/Go-Camping/No-Flesh-Within-Chest/tree/version_all_dim) of [No Flesh Within Chest (脆骨症)](https://github.com/Go-Camping/No-Flesh-Within-Chest). It references GUI textures provided by that modpack at runtime (via KubeJS/Hotai) and **will not display correctly without the modpack installed**. This mod is not a standalone or general-purpose add-on.

**Version-specific disclaimer:** This mod is only compatible with 脆骨症黯光-1.0.2-DIM (No Flesh Within Chest-1.0.2-DIM). All other versions — including the original 脆骨症 releases and other branches/forks — have not been tested. Compatibility and stability on non-target versions are unknown. Any consequences resulting from use outside the target version are the sole responsibility of the user.

**This mod was generated with AI assistance.** The project code was generated using [QoderWork](https://qoder.com), an AI-powered programming assistant, including Mixin injections, configuration system, GUI rendering logic, and other core functionality.

## Features

- Configurable chest cavity inventory rows via server config (`cavityRows`: 4, 5, or 6), default: 6 rows (54 slots)
- This mod only supports 4, 5, or 6 rows. Setting other row counts may cause GUI misalignment or functional issues. For other row counts, please modify the source code accordingly

## Requirements

- Minecraft 1.19.2
- Forge 43.3.5
- [Chest Cavity - Forge Port](https://github.com/BoonelDanForever/ChestCavityForge) (Forge port of ChestCavity)
- **"脆骨症黯光" modpack v1.0.2-DIM** — required for GUI textures

## Configuration

After the first launch, a config file is generated at:
```
<world>/serverconfig/chestcavitytweak-server.toml
```

```toml
[chest_cavity_settings]
    # Number of rows in the chest cavity inventory. Valid values: 4, 5, 6.
    # Takes effect after world/server restart.
    # Reducing the size may cause items in excess slots to be lost.
    # Range: 4 ~ 6
    cavityRows = 6
```

## Building from Source

1. Place the ChestCavity Forge jar in the `libs/` directory (compile-only dependency)
2. Run:
   ```
   gradlew build
   ```
3. Output jar: `build/libs/chestcavitytweak-1.19.2-0.1.0.jar`

## License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.

## Modification & Derivative Works

You are free to modify, fork, or redistribute this mod's code under the terms of the MIT License. However, please observe the following requirements in any derivative work:

1. **Retain attribution to this mod** — clearly state the original mod name (ChestCavityTweak), author (Tonywqs), and source repository link.
2. **Retain attribution to prerequisite mods** — clearly state the attribution and links for ChestCavity (Tigereye504), Chest Cavity - Forge Port (BoonelDanForever), and the "脆骨症黯光" modpack (Go-Camping).
3. **Retain license notices** — include the original MIT License text and the Intellectual Property & Attribution section in your derivative work.

## Intellectual Property & Attribution

### ChestCavity / Chest Cavity - Forge Port

ChestCavity is a Fabric mod created and maintained by **Tigereye504**.
- Original source: [https://github.com/Tigereye504/chestcavity](https://github.com/Tigereye504/chestcavity)

Chest Cavity - Forge Port is a Forge port maintained by **BoonelDanForever**.
- Forge Port source: [https://github.com/BoonelDanForever/ChestCavityForge](https://github.com/BoonelDanForever/ChestCavityForge)

This add-on mod does **not** include, redistribute, or modify any ChestCavity or Chest Cavity - Forge Port source code, assets, or resources. It uses [SpongePowered Mixin](https://github.com/SpongePowered/Mixin) to inject bytecode at runtime, and depends on Chest Cavity - Forge Port as a `compileOnly` dependency (not packaged into the output jar).

### "脆骨症黯光" Modpack

"脆骨症黯光" is a [fork](https://github.com/Go-Camping/No-Flesh-Within-Chest/tree/version_all_dim) of [No Flesh Within Chest (脆骨症)](https://github.com/Go-Camping/No-Flesh-Within-Chest), maintained by **Go-Camping**.

The GUI textures referenced by this mod at runtime belong to the **"脆骨症黯光" modpack**. This mod does **not** include or redistribute any textures or assets from the modpack. The texture `chestcavity:chest_cavity.png` is provided at runtime by the modpack's KubeJS/Hotai resource overrides.

### Minecraft / Forge

Minecraft is a trademark of Mojang Studios / Microsoft Corporation. Forge is a free, open-source modding API. This project is not affiliated with, endorsed by, or sponsored by Mojang Studios, Microsoft Corporation, or the Forge team.

### Disclaimer

This mod is an independent, fan-made add-on. All rights to the original ChestCavity mod, Chest Cavity - Forge Port, and the "脆骨症黯光" modpack belong to their respective authors. This project makes no claim of ownership over any referenced assets, names, or trademarks.
