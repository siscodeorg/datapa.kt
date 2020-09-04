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

fun randomPlayerSelector(vararg args: SelectorArgument) : EntitySelector =
    EntitySelector(type = SelectorType.RANDOM_PLAYER, args = *args)

fun randomPlayerSelector(args: List<SelectorArgument>) : EntitySelector =
    EntitySelector(type = SelectorType.RANDOM_PLAYER, args = args)

fun allPlayersSelector(vararg args: SelectorArgument) : EntitySelector =
    EntitySelector(type = SelectorType.ALL_PLAYERS, args = *args)

fun allPlayersSelector(args: List<SelectorArgument>) : EntitySelector =
    EntitySelector(type = SelectorType.ALL_PLAYERS, args = args)

fun allEntitiesSelector(vararg args: SelectorArgument) : EntitySelector =
    EntitySelector(type = SelectorType.ALL_ENTITIES, args = *args)

fun allEntitiesSelector(args: List<SelectorArgument>) : EntitySelector =
    EntitySelector(type = SelectorType.ALL_ENTITIES, args = args)

fun entityExecutingCommandSelector(vararg args: SelectorArgument) : EntitySelector =
    EntitySelector(type = SelectorType.ENTITY_EXECUTING_COMMAND, args = *args)

fun entityExecutingCommandSelector(args: List<SelectorArgument>) : EntitySelector =
    EntitySelector(type = SelectorType.ENTITY_EXECUTING_COMMAND, args = args)

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

class ScoresArgument (var scores : List<Score>) : SelectorArgument () {
    constructor(vararg scores : Score) : this(scores.asList())

    override fun serialize(): String {
        return "scores={${scores.joinToString(","){"${it.objective}=${it.value}"}}}"
    }
}

data class Score (var objective: String, var value : String)

enum class TeamType {
    TEAMLESS,HAS_TEAM, NOT_IN_TEAM, IN_TEAM
}

class TeamArgument (var type : TeamType ,var team : String) : SelectorArgument() {
    constructor(type: TeamType) : this(type, "")
    override fun serialize(): String {
        return when(type) {
            TeamType.TEAMLESS -> "team="
            TeamType.HAS_TEAM -> "team=!"
            TeamType.NOT_IN_TEAM -> "team=!${team}"
            TeamType.IN_TEAM -> "team=${team}"
        }
    }
}

fun teamlessSelector() : TeamArgument =
    TeamArgument(TeamType.TEAMLESS)

fun hasTeamSelector() : TeamArgument =
    TeamArgument(TeamType.HAS_TEAM)

fun inTeamSelector(name : String) =
    TeamArgument(TeamType.IN_TEAM, name)

fun notInTeamSelector(name: String) = 
    TeamArgument(TeamType.NOT_IN_TEAM, name)

enum class SortingType {
    NEAREST,FURTHEST,RANDOM,ARBITRARY,NO_SORT
}

class LimitArgument(var limit: Int,var type: SortingType) : SelectorArgument() {
    constructor(limit: Int) : this(limit,SortingType.NO_SORT)
    override fun serialize(): String {
        return when (type){
            SortingType.NEAREST -> "limit=${limit},sort=nearest"
            SortingType.FURTHEST -> "limit=${limit},sort=furthest"
            SortingType.RANDOM -> "limit=${limit},sort=random"
            SortingType.ARBITRARY -> "limit=${limit},sort=arbitrary"
            SortingType.NO_SORT -> "limit=${limit}"
        }
    }
}

fun nearestLimitSelector(limit: Int) : LimitArgument =
    LimitArgument(limit,SortingType.NEAREST)

fun furthestLimitSelector(limit: Int) : LimitArgument =
    LimitArgument(limit,SortingType.FURTHEST)

fun randomLimitSelector(limit: Int) : LimitArgument =
    LimitArgument(limit,SortingType.RANDOM)

fun arbitraryLimitSelector(limit: Int) : LimitArgument =
    LimitArgument(limit,SortingType.ARBITRARY)

fun limitSelector(limit: Int) : LimitArgument =
    LimitArgument(limit)

//Another one which is either an int or an range.
class LevelArgument (var value : String) : SelectorArgument(){
    override fun serialize(): String {
        return "level=${value}"
    }
}

enum class Gamemode {
    SPECTATOR,ADVENTURE,CREATIVE,SURVIVAL
}

class GamemodeArgument(var gamemode: Gamemode, var flag : Boolean) : SelectorArgument(){
    override fun serialize(): String {
        var current = "gamemode"
        current += if(!flag) "=!" else "="

        return when(gamemode){
            Gamemode.SPECTATOR -> "${current}spectator"
            Gamemode.ADVENTURE -> "${current}adventure"
            Gamemode.CREATIVE -> "${current}creative"
            Gamemode.SURVIVAL -> "${current}survival"
        }
    }
}

fun spectatorSelector() : GamemodeArgument =
    GamemodeArgument(Gamemode.SPECTATOR, true)

fun notSpectatorSelector() : GamemodeArgument =
    GamemodeArgument(Gamemode.SPECTATOR, false)

fun adventureSelector() : GamemodeArgument =
    GamemodeArgument(Gamemode.ADVENTURE, true)

fun notAdventureSelector() : GamemodeArgument =
    GamemodeArgument(Gamemode.ADVENTURE, false)

fun creativeSelector() : GamemodeArgument =
    GamemodeArgument(Gamemode.CREATIVE, true)

fun notCreativeSelector() : GamemodeArgument =
    GamemodeArgument(Gamemode.CREATIVE, false)

fun survivalSelector() : GamemodeArgument =
    GamemodeArgument(Gamemode.SURVIVAL, true)

fun notSurvivalSelector() : GamemodeArgument =
    GamemodeArgument(Gamemode.SURVIVAL, false)

class EntityNameArgument(var name: String, var flag: Boolean) : SelectorArgument() {
    override fun serialize(): String {
        return when(flag){
            true -> "name=${name}"
            false -> "name=!${name}"
        }
    }
}

fun withNameSelector(name : String) : EntityNameArgument =
    EntityNameArgument(name,true)

fun withoutNameSelector(name : String) : EntityNameArgument =
    EntityNameArgument(name, false)
