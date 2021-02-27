package com.macedos.motivation.model

import java.io.Serializable

data class Message(
    val text: String,
    val isRemovable: Boolean = true
) : Serializable
