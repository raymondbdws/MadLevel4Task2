package com.rayray.madlevel4task2.model
import androidx.annotation.DrawableRes
import java.util.*

data class Game (
    @DrawableRes var computer: Int,
    @DrawableRes var user: Int,
    var date: Date,
    var result: String
)