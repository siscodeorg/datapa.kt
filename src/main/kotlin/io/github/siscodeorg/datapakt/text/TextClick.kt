package io.github.siscodeorg.datapakt.text


sealed class TextClickAction (
    val action: String,
    val value: String
){}

class OpenUrlClickAction(val url: String)
    : TextClickAction("open_url", value = url)
class RunCommandClickAction(val command: String)
    : TextClickAction("run_command", value = command)
class SuggestCommandClickAction(val command: String)
    : TextClickAction("suggest_command", value = command)
class ChangePageClickAction(val page: String)
    : TextClickAction("change_page", value = page)
class CopyToClipboardClickAction(val text: String)
    : TextClickAction("copy_to_clipboard", value = text)