package com.adam.justdo.ui.component.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarView
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetDueDateDialog(
    date: LocalDate?,
    onDone: (LocalDate) -> Unit,
    onDismissRequest: () -> Unit,
) {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    if (date != null) selectedDate = date
    val useCaseState = rememberUseCaseState(visible = true)
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Surface(Modifier.clip(RoundedCornerShape(20.dp))) {
            Column {
                CalendarView(
                    useCaseState = useCaseState,
                    selection = CalendarSelection.Date(
                        withButtonView = false,
                        selectedDate = selectedDate,
                    ) { newDate -> selectedDate = newDate },
                    config = CalendarConfig(
                        yearSelection = true,
                        monthSelection = true,
                        style = CalendarStyle.MONTH
                    )
                )
                Row(
                    modifier = Modifier
                        .padding(bottom = 16.dp, end = 16.dp)
                        .align(Alignment.End)
                ) {
                    TextButton(onClick = { onDismissRequest() }) {
                        Text(text = "Cancel")
                    }
                    TextButton(onClick = {
                        onDone(selectedDate)
                        onDismissRequest()
                    }) {
                        Text(text = "Done")
                    }
                }
            }
        }
    }
}