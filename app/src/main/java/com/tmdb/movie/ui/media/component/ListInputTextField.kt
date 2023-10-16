package com.tmdb.movie.ui.media.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tmdb.movie.ui.theme.TMDBMovieTheme

@Composable
fun ListInputTextField(
    modifier: Modifier = Modifier,
    textValue: String = "",
    labelTextValue: String = "",
    onValueChange: (String) -> Unit = {},
    maxCharacterCount: Int = 10,
    maxLines: Int = 1,
    minLines: Int = 1,
    imeAction: ImeAction,
    readOnly: Boolean,
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        value = textValue,
        label = {
            Text(
                text = labelTextValue,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        supportingText = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    modifier = Modifier,
                    text = textValue.length.toString(),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = if (textValue.length > maxCharacterCount) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
                    ),
                )
                Text(
                    modifier = Modifier,
                    text = "/$maxCharacterCount",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        },
        onValueChange = onValueChange,
        minLines = minLines,
        maxLines = maxLines,
        singleLine = maxLines == 1,
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction
        ),
        readOnly = readOnly
    )
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ListInputTextFieldPreview() {
    TMDBMovieTheme {
        ListInputTextField(
            modifier = Modifier.fillMaxWidth(),
            labelTextValue = "List Name",
            maxCharacterCount = 50,
            imeAction = ImeAction.Done,
            readOnly = false
        )
    }
}