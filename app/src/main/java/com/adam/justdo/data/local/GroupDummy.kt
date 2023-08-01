package com.adam.justdo.data.local

import com.adam.justdo.domain.model.Group

object GroupDummy{
    var groups = listOf(
        Group(
            id = 0,
            groupName = "Home"
        ),
        Group(
            id = 1,
            groupName = "Office"
        ),
        Group(
            id = 2,
            groupName = "School"
        ),
    )
}