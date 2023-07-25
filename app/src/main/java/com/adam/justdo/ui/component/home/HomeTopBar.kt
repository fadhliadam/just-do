package com.adam.justdo.ui.component.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.adam.justdo.R

@Composable
fun HomeTopBar(onClickSearch: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 14.dp, vertical = 18.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(
                            shape = CircleShape,
                            color = Color.White
                        )
                        .clip(CircleShape),
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.baseline_person_24),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop
                    )
                }
                Text(
                    modifier = Modifier.padding(horizontal = 14.dp),
                    text = "Hi! John Doe",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            IconButton(
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                onClick = { onClickSearch() },
            ) {
                Icon(
                    modifier = Modifier
                        .size(28.dp)
                        .padding(2.dp),
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            }
        }
    }
}