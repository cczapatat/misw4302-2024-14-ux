package com.uniandes.edu.uxalarm.dtos

data class AlarmItem(
    var title: String,
    var time: String,
    var disable: Boolean,
    var clockIndex: Int,
    var images: IntArray,
)
