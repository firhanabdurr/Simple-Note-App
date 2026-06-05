package com.firhan.simplenoteapp.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}