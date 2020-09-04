package io.github.siscodeorg.datapakt.entity

enum class SelectorType {
    NEAREST_PLAYER,RANDOM_PLAYER,ALL_PLAYERS,ALL_ENTITIES,ENTITY_EXECUTING_COMMAND
}

class EntitySelector (var type: SelectorType, var args : List<SelectorArgument>)
{
    constructor(type: SelectorType, vararg args: SelectorArgument) : this(type, args.asList())
}

fun nearestPlayerSelector(vararg args : SelectorArgument) : EntitySelector  =
    EntitySelector(type = SelectorType.NEAREST_PLAYER, args = *args)

fun nearestPlayerSelector(args: List<SelectorArgument>) : EntitySelector =
    EntitySelector(type = SelectorType.NEAREST_PLAYER, args = args)

sealed class SelectorArgument {
    abstract fun serialize(): String
}

class CoordinateArgument(var x : String, var y: String, var z: String) : SelectorArgument()
{
    override fun serialize() : String { return "x=${x},y=${y},z=${z}" }
}

//Distance is actually an Int (12) or Range (12..14)
//So String was chosen until a better implementation is found
class DistanceArgument(var distance: String) : SelectorArgument()
{
    override fun serialize(): String { return "distance=${distance}" }
}

class VolumeDimensionArgument (var dx : String, var dy: String, var dz: String) : SelectorArgument()
{
    override fun serialize(): String { return "dx=${dx},dy=${dy},dz=${dz}" }
}

//TODO: Implement Scores Argument
//class ScoresArgument : SelectorArgument() { //scores={Score1=12,Score2=12..33}
//
//}

