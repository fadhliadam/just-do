package com.adam.justdo.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "groups")
data class Group(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "group_name") val groupName: String,
)
