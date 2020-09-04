package io.github.siscodeorg.datapakt.serialization

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.github.siscodeorg.datapakt.entity.EntitySelector
import io.github.siscodeorg.datapakt.entity.SelectorType

class MainSerializer {
    companion object {
        var instance : Gson = GsonBuilder()
            .disableHtmlEscaping()
            .registerTypeAdapter(EntitySelector::class.java,EntitySelectorSerializer())
            .create()
    }
}