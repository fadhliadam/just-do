package com.adam.justdo.data

import com.adam.justdo.data.local.room.AppDatabase
import com.adam.justdo.domain.repository.IRepository

class Repository(
    private val taskDatabase: AppDatabase
) : IRepository {

}