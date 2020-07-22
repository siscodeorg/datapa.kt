package io.github.siscodeorg.datapakt.text

sealed class TextHoverAction(
    val action: String,
    val contents: Any
) { }


data class ShowTextContents(var show_text: TextComponent)

class PlaintextTextHoverAction(
    contents: TextComponent
) : TextHoverAction("show_text", contents)

data class ShowItemContents(
    var id: String,  // really a namespaced id (ResourceLocation)
    var count: Int = 1,
    var tag: String? = null  // really an SNBT value
)

class ItemHoverAction(
    id: String,  // really a namespaced id (ResourceLocation)
    count: Int = 1,
    tag: String? = null
) : TextHoverAction("show_item",
    contents = ShowItemContents(id, count, tag)
)

data class ShowEntityContents(
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
    contents = ShowEntityContents(type, id, name)
)

