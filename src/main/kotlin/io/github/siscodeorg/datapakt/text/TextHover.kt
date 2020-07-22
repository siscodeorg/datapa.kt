package io.github.siscodeorg.datapakt.text

import com.beust.klaxon.Json


sealed class TextHoverAction(
    @Json(index = 1)
    val action: String,
    @Json(index = 2)
    val contents: HoverShowContents
) { }

sealed class HoverShowContents

data class ShowTextContents(var show_text: TextComponent) : HoverShowContents()

class PlaintextTextHoverAction(
    show_text: TextComponent
) : TextHoverAction("show_text",
contents = ShowTextContents(show_text)
)

data class ShowItemContents(
    val show_item: ShowItemInfo
) : HoverShowContents()

data class ShowItemInfo(
    var id: String,  // really a namespaced id (ResourceLocation)
    var count: Int = 1,
    var tag: String? = null  // really an SNBT value
)

class ItemHoverAction(
    id: String,  // really a namespaced id (ResourceLocation)
    count: Int = 1,
    tag: String? = null
) : TextHoverAction("show_item",
    contents = ShowItemContents(ShowItemInfo(id, count, tag))
)

data class ShowEntityContents(val show_entity: ShowEntityInfo) : HoverShowContents()

data class ShowEntityInfo(
    var type: String, // actually a ResourceLocation
    var id: String,  // actually a UUID
    var name: TextComponent? = null
)

class EntityHoverAction(
    type: String, // actually a ResourceLocation
    id: String,  // actually a UUID
    name: TextComponent? = null
) : TextHoverAction(
    "show_entity",
    contents = ShowEntityContents(ShowEntityInfo(type, id, name))
)

