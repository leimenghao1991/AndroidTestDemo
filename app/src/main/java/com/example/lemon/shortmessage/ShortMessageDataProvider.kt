package com.example.lemon.shortmessage

/**
 * Created by lemon on 2017/12/24.
 */
class ShortMessageDataProvider {
    lateinit var shortMessages : List<ShortMessage>

    constructor() {
        shortMessages = ArrayList();
    }

    public fun getItem(position: Int): ShortMessage {
        return shortMessages[position]
    }
}