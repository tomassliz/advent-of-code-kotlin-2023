package Day01

import println
import readInput

fun main() {
    fun getNumber(input: String): Int {
        return "${input.first { it.isDigit() }}${input.last { it.isDigit() }}".toInt()
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { getNumber(it) }
    }

    fun part2(input: List<String>): Int {
        val digits = mapOf(
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9
        )

        return input.sumOf { line ->
            getNumber(
                line.mapIndexedNotNull { index, c ->
                    if (c.isDigit()) c
                    else {
                        (3..5).map {
                            line.substring(index, (index + it).coerceAtMost(line.length))
                        }.firstNotNullOfOrNull {
                            digits[it]
                        }
                    }
                }.joinToString()
            )
        }
    }

    val testInput1 = """
        1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet
    """
        .trimIndent()
        .split("\n")
    check(part1(testInput1) == 142)

    val testInput2 = """
        two1nine
        eightwothree
        abcone2threexyz
        xtwone3four
        4nineeightseven2
        zoneight234
        7pqrstsixteen
    """
        .trimIndent()
        .split("\n")
    check(part2(testInput2) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}