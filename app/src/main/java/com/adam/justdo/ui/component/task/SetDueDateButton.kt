package com.adam.justdo.ui.component.task

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.outlined.Today
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun SetDueDateButton(
    date: LocalDate?,
    onClickTrailingIcon: () -> Unit,
    onClick: () -> Unit,
) {
    val dateFormat =
        if (date?.year == LocalDate.now().year) "E, MMM dd" else "E, MMM dd, yyyy"
    val formattedDate = date?.format(DateTimeFormatter.ofPattern(dateFormat))
    ElevatedAssistChip(
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Today,
                contentDescription = "Due date icon"
            )
        },
        trailingIcon = {
            if (date != null) {
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
            if (formattedDate.isNullOrBlank()) {
                Text(text = "Set due date")
            } else {
                Text(text = formattedDate)
            }
        },
        onClick = { onClick() }
    )
}