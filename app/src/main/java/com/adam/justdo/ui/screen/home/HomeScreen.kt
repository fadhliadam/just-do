package com.adam.justdo.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adam.justdo.ui.component.home.HomeBottomBar
import com.adam.justdo.ui.component.home.HomeTopBar

@Composable
fun HomeScreen() {
    Scaffold(
        bottomBar = {
            HomeBottomBar(
                onClickIcon1 = { /*TODO*/ },
                onClickIcon2 = { /*TODO*/ },
                onClickIconFAB = { /*TODO*/ }
            )
        },
        topBar = {
            HomeTopBar(
                onClickSearch = { /*TODO*/ }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
        ) {
            Row {

            }
        }
    }
}