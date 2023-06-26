package com.example.news.ui.screens.search.view.component

import android.text.format.DateFormat
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.news.R
import java.util.Calendar
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerDialog(
    validatorRange: LongRange = LongRange(
        start = Calendar.getInstance().apply { add(Calendar.MONTH, -1) }.timeInMillis,
        endInclusive = Date().time
    ),
    selectedRange: LongRange = validatorRange,
    onSave: (startMills: Long, endMills: Long) -> Unit,
    onDismiss: () -> Unit
) {
    val state = rememberDateRangePickerState(
        initialDisplayedMonthMillis = selectedRange.last,
        initialSelectedStartDateMillis = selectedRange.first,
        initialSelectedEndDateMillis = selectedRange.last,
    )
    DatePickerDialog(
        modifier = Modifier.padding(16.dp),
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                modifier = Modifier.padding(end = 16.dp),
                onClick = {
                    onSave(
                        state.selectedStartDateMillis!!,
                        state.selectedEndDateMillis!!
                    )
                },
                enabled = state.selectedEndDateMillis != null
            ) {
                Text(text = stringResource(id = R.string.Save))
            }
        },
        dismissButton = {
            TextButton(
                modifier = Modifier.padding(end = 16.dp),
                onClick = onDismiss,
            ) {
                Text(text = stringResource(id = R.string.cancel))
            }
        }) {
        DateRangePicker(
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp),
            state = state,
            dateValidator = { it in validatorRange },
            headline = {
                val start = "${
                    state.selectedStartDateMillis?.let {
                        DateFormat.format(
                            ManageSearchState.DATE_PATTERN,
                            it
                        )
                    } ?: stringResource(id = R.string.start_date)
                }"
                val end = "${
                    state.selectedEndDateMillis?.let {
                        DateFormat.format(
                            ManageSearchState.DATE_PATTERN,
                            it
                        )
                    } ?: stringResource(id = R.string.end_date)
                }"

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "$start - $end",
                    textAlign = TextAlign.Center
                )
            }
        )
    }
}