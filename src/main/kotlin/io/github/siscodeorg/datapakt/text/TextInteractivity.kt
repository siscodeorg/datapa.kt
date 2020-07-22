package io.github.siscodeorg.datapakt.text

class TextClickAction {
    var action: ClickAction? = null
    var value: String? = null
}

sealed class TextHoverAction(val action: String) { }

data class ShowTextContents(var show_text: TextComponent)

class PlaintextTextHoverAction(
    show_text: TextComponent
) : TextHoverAction("show_text") {
    val contents = ShowTextContents(show_text)
}

data class ShowItemContents(
    val show_item: ShowItemInfo
)

data class ShowItemInfo(
    var id: String,  // really a namespaced id (ResourceLocation)
    var count: Int = 1,
    var tag: String? = null  // really an SNBT value
)

class ItemHoverAction(
    id: String,  // really a namespaced id (ResourceLocation)
    count: Int = 1,
    tag: String? = null
) : TextHoverAction("show_item") {
    val contents = ShowItemContents(ShowItemInfo(id, count, tag))
}

data class ShowEntityContents(val show_entity: ShowEntityInfo)

data class ShowEntityInfo(
    var type: String, // actually a ResourceLocation
    var id: String,  // actually a UUID
    var name: TextComponent? = null
)

class EntityHoverAction(
    type: String, // actually a ResourceLocation
    id: String,  // actually a UUID
    name: TextComponent? = null
) : TextHoverAction("show_entity") {
    val contents = ShowEntityContents(ShowEntityInfo(type, id, name))
}


enum class ClickAction {
    openUrl,openFile,runCommand,suggestCommand,changePage,copyToClipboard
}
