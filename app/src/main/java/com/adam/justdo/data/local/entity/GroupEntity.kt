package com.adam.justdo.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "group")
data class GroupEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "group_name") val groupName: String,
)