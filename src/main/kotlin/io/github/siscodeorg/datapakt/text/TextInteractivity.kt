package io.github.siscodeorg.datapakt.text

class TextClickAction {
    var action: ClickAction? = null
    var value: String? = null
}

class TextHoverAction {
    var action: HoverAction? = null
    var text: PlainTextComponent? = null
    var item: String? = null //TODO: SNBT for ItemStack
    var entity: String? = null //TODO : SNBT for Entity
}
enum class ClickAction {
    openUrl,openFile,runCommand,suggestCommand,changePage,copyToClipboard
}
enum class HoverAction {
    showText,showItem,showEntity
}
