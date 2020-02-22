package com.larry.reactivechat.controller

data class CreateChatDto(val stuff: String, val channelId: Long)

data class UnReadDto(val channelId: Long, val count: Int)