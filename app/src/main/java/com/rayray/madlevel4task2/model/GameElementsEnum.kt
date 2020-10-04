package com.rayray.madlevel4task2.model

import com.rayray.madlevel4task2.R

/**
 * @author Raymond Chang
 *
 * Dit is een enum class, verbonden met het plaatje.
 */
enum class GameElementsEnum(val element: Int) {
    ROCK(R.drawable.rock),
    PAPER(R.drawable.paper),
    SCISSER(R.drawable.scissors)
}
