package ru.minnullin

import com.minnullin.models.CounterType


class CounterChangeDto(
    val id:Int,
    val counter:Int,
    val counterType: CounterType
)