package io.github.siscodeorg.datapakt.text

import io.github.siscodeorg.datapakt.scoreboard.ScoreSelector

sealed class TextComponent(
    var color: String? = null,
    var font: String? = null, // TODO: Add a class for a resource location (ResourceLocation)
    var bold: Boolean? = null,
    var italic: Boolean? = null,
    var underline: Boolean? = null,
    var strikethrough: Boolean? = null,
    var obfuscated: Boolean? = null,

    var extra: MutableList<TextComponent>? = null,

    var insertion: String? = null,  // Insert this text into the chat bar when shift-clicked
    var clickEvent: TextClickAction? = null,
    var hoverEvent: TextHoverAction? = null
)

class PlainTextComponent(
    var text: String
) : TextComponent()

class TranslatedTextComponent(
    var translate: String,
    var with: MutableList<TextComponent> = mutableListOf()
) : TextComponent()

class ScoreTextComponent(
    val score: ScoreSelector
) : TextComponent()

class EntitySelectorTextComponent(
    selector: String // TODO: Add a class for an entity selector (EntitySelector)
) : TextComponent()

class KeybindTextComponent(
    var keybind: String
) : TextComponent()


sealed class NbtReaderTextComponent(
    var nbt: String,  // The NBT path. TODO: Add a class to represent NBT paths (NbtPath)
    var interpret: Boolean = false
) : TextComponent()

class BlockNbtTextComponent(
    var block: String,  // Absolute or relative coordinates of a block.
    // TODO: Add a class for coordinate selectors (TargetCoordinates)

    nbt: String
) : NbtReaderTextComponent(nbt)

class EntityNbtTextComponent(
    var entity: String,  // Really an EntitySelector

    nbt: String
) : NbtReaderTextComponent(nbt)

class StoredNbtTextComponent(
    var storage: String,  // Namespaced ID of an NBT storage. really a ResourceLocation

    nbt: String
) : NbtReaderTextComponent(nbt)
