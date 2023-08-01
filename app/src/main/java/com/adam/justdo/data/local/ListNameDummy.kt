package com.adam.justdo.data.local

import com.adam.justdo.domain.model.ListName

object ListNameDummy{
    var listName = listOf(
        ListName(
            id = 0,
            listName = "Home"
        ),
        ListName(
            id = 1,
            listName = "Office"
        ),
        ListName(
            id = 2,
            listName = "School"
        ),
    )
}