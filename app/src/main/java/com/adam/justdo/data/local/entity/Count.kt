package com.adam.justdo.data.local.entity

import androidx.room.ColumnInfo

data class Count(
    @ColumnInfo(name = "all_count")
    val allCount: Int,

    @ColumnInfo(name = "important_count")
    val importantCount: Int,

    @ColumnInfo(name = "today_count")
    val todayCount: Int
)