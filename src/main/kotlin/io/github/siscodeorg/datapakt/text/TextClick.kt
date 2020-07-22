package io.github.siscodeorg.datapakt.text

import com.beust.klaxon.Json

sealed class TextClickAction (
    @Json(index = 1)
    val action: String,
    @Json(index = 2)
    val value: String
){}

class OpenUrlTextClickAction(val url: String)
    : TextClickAction("open_url", value = url)
class RunCommandTextClickAction(val command: String)
    : TextClickAction("run_command", value = command)
class SuggestCommandTextClickAction(val command: String)
    : TextClickAction("run_command", value = command)
class ChangePageTextClickAction(val page: String)
    : TextClickAction("run_command", value = page)
class CopyToClipboardTextClickAction(val text: String)
    : TextClickAction("run_command", value = text)