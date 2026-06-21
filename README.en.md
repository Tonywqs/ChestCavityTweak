# ChestCavityTweak

[中文](README.md)

A Forge 1.19.2 add-on mod for [ChestCavity](https://github.com/Tigereye504/chestcavity) that allows configurable chest cavity inventory rows (3–6 rows, i.e. 27–54 slots).

## Important Notice

**This mod is specifically designed for the "脆骨症" (No Flesh Within Chest) modpack v1.0.2-DIM.** It references GUI textures provided by that modpack at runtime (via KubeJS/Hotai) and **will not display correctly without the modpack installed**. This mod is not a standalone or general-purpose add-on.

## Features

- Configurable chest cavity inventory size via server config (`cavityRows`: 3, 4, 5, or 6)
- Automatic GUI background extension for extra rows (pixel-perfect alignment)
- Proper "物品栏" label repositioning in the gap between cavity rows and player inventory
- Slot highlight and tooltip rendering unaffected
- Default: 6 rows (54 slots)

## Requirements

- Minecraft 1.19.2
- Forge 43.3.5
- [ChestCavity](https://github.com/Tigereye504/chestcavity) (Forge port)
- **"脆骨症" (No Flesh Within Chest) modpack v1.0.2-DIM** — required for GUI textures

## Configuration

After the first launch, a config file is generated at:
```
<world>/serverconfig/chestcavitytweak-server.toml
```

```toml
[chest_cavity_settings]
    # Number of rows in the chest cavity inventory. Valid values: 3, 4, 5, 6.
    # Takes effect after world/server restart.
    # Reducing the size may cause items in excess slots to be lost.
    # Range: 3 ~ 6
    cavityRows = 6
```

## Building from Source

1. Place the ChestCavity Forge jar in the `libs/` directory (compile-only dependency)
2. Run:
   ```
   gradlew build
   ```
3. Output jar: `build/libs/chestcavitytweak-1.19.2-1.0.0.jar`

## License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.

## Intellectual Property & Attribution

### ChestCavity

ChestCavity is a mod created and maintained by **Tigereye504**.
- Source: [https://github.com/Tigereye504/chestcavity](https://github.com/Tigereye504/chestcavity)

This add-on mod does **not** include, redistribute, or modify any ChestCavity source code, assets, or resources. It uses [SpongePowered Mixin](https://github.com/SpongePowered/Mixin) to inject bytecode at runtime, and depends on ChestCavity as a `compileOnly` dependency (not packaged into the output jar).

### "脆骨症" (No Flesh Within Chest) Modpack

The GUI textures referenced by this mod at runtime belong to the **"脆骨症" (No Flesh Within Chest) modpack**. This mod does **not** include or redistribute any textures or assets from the modpack. The texture `chestcavity:chest_cavity.png` is provided at runtime by the modpack's KubeJS/Hotai resource overrides.

### Minecraft / Forge

Minecraft is a trademark of Mojang Studios / Microsoft Corporation. Forge is a free, open-source modding API. This project is not affiliated with, endorsed by, or sponsored by Mojang Studios, Microsoft Corporation, or the Forge team.

### Disclaimer

This mod is an independent, fan-made add-on. All rights to the original ChestCavity mod and the "脆骨症" modpack belong to their respective authors. This project makes no claim of ownership over any referenced assets, names, or trademarks.
