package Day02

import org.testng.Assert.*
import org.testng.annotations.Test

internal class Day02Test {
    @Test
    fun `parse game`() {
        val input = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"
        val game = parseGame(input)
        assertEquals(
            game,
            Game(
                1,
                listOf(
                    mapOf(Color.RED to 4, Color.BLUE to 3),
                    mapOf(Color.RED to 1, Color.BLUE to 6, Color.GREEN to 2),
                    mapOf(Color.GREEN to 2)
                )
            )
        )
    }
}