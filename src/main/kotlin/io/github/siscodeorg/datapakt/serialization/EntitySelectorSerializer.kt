package io.github.siscodeorg.datapakt.serialization

import com.google.gson.*
import io.github.siscodeorg.datapakt.entity.*
import java.lang.UnsupportedOperationException
import java.lang.reflect.Type

class EntitySelectorSerializer : JsonSerializer<EntitySelector> {
    override fun serialize(src: EntitySelector?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        val currentSelector : String
        currentSelector = when(src?.type){
            SelectorType.NEAREST_PLAYER -> "@p"
            SelectorType.RANDOM_PLAYER -> "@r"
            SelectorType.ALL_PLAYERS -> "@a"
            SelectorType.ALL_ENTITIES -> "@e"
            SelectorType.ENTITY_EXECUTING_COMMAND -> "@s"
            null -> throw UnsupportedOperationException("Invalid/Not initialized Selector Type")
        }
        return if(src.args.isEmpty()) JsonPrimitive(currentSelector)
        else {
            val serializedArgs = src.args.map { it.serialize() }
            JsonPrimitive(currentSelector+serializedArgs.joinToString (",","[","]"))
        }
    }
}