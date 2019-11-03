package iim.jetpack.dorianmarques.fr.iima4test

import org.json.JSONArray

interface GameInterface {
    val game: Array<Game>

    fun open(game: Game)
}