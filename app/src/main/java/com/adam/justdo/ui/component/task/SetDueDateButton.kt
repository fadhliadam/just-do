package com.adam.justdo.ui.component.task

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.outlined.Today
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetDueDateButton(
    modifier: Modifier = Modifier,
    selected: Boolean,
    dateFormatted: String?,
    onClickTrailingIcon: () -> Unit,
    onClick: () -> Unit,
) {
    FilterChip(
        modifier = modifier,
        selected = selected,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Today,
                contentDescription = "Due date icon"
            )
        },
        trailingIcon = {
            if (!dateFormatted.isNullOrBlank()) {
                Icon(
                    modifier = Modifier
                        .size(18.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .clickable { onClickTrailingIcon() },
                    imageVector = Icons.Filled.Cancel,
                    contentDescription = "cancel"
                )
            }
        },
        label = {
            if (dateFormatted.isNullOrBlank()) {
                Text(text = "Set due date")
            } else {
                Text(text = dateFormatted)
            }
        },
        onClick = { onClick() }
    )
}