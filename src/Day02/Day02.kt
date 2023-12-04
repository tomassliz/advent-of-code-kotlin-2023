package Day02

import println
import readInput
import kotlin.math.max

fun main() {

    fun part1(input: List<String>): Int {
        val games = input.map { parseGame(it) }
        return games.fold(0) { acc, game ->
            var sum = acc
            if (game.turns.all { (it[Color.RED] ?: 0) <= 12 && (it[Color.GREEN] ?: 0) <= 13 && (it[Color.BLUE] ?: 0) <= 14 }) {
                sum += game.id
            }
            sum
        }
    }

    fun part2(input: List<String>): Int {
        val games = input.map { parseGame(it) }
        return games.fold(0) { acc, game ->
            var redMin = 0
            var greenMin = 0
            var blueMin = 0

            game.turns.forEach {
                redMin = max(redMin, it[Color.RED] ?: 0)
                greenMin = max(greenMin, it[Color.GREEN]?: 0)
                blueMin = max(blueMin, it[Color.BLUE]?: 0)
            }

            acc + (redMin * greenMin * blueMin)
        }
    }

    val testInput = """
        Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
        Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
        Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
        Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
    """
        .trimIndent()
        .split("\n")

    check(part1(testInput) == 8)
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

data class Game(val id: Int, val turns: List<Map<Color, Int>>)

enum class Color { RED, GREEN, BLUE }

fun parseGame(input: String): Game {
    val firstSplit = input.split(": ")
    val id = firstSplit.first().split(" ").last().toInt()
    val turns = firstSplit.last().split("; ").map {
        it.split(", ").map {
            val pairSplit = it.split(" ")
            Pair(Color.valueOf(pairSplit.last().uppercase()), pairSplit.first().toInt())
        }.toMap()
    }
    return Game(id, turns)
}