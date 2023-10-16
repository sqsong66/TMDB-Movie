package com.tmdb.movie.ui.media

import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tmdb.movie.R
import com.tmdb.movie.data.CreateListParam
import com.tmdb.movie.ui.media.component.ListInputTextField
import com.tmdb.movie.ui.media.vm.CreateListUiState
import com.tmdb.movie.ui.media.vm.CreateListViewModel
import com.tmdb.movie.ui.theme.TMDBMovieTheme

@Composable
fun CreateListRoute(
    onBackClick: (Boolean) -> Unit,
    viewModel: CreateListViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val createListUiState by viewModel.createListUiState.collectAsStateWithLifecycle()

    LaunchedEffect(createListUiState) {
        when (createListUiState) {
            is CreateListUiState.Success -> {
                Toast.makeText(context, context.getString(R.string.key_create_list_success), Toast.LENGTH_SHORT).show()
                onBackClick(false)
            }

            is CreateListUiState.Error -> {
                Toast.makeText(context, context.getString(R.string.key_create_list_failed), Toast.LENGTH_SHORT).show()
                viewModel.resetUiState()
            }

            else -> {}
        }
    }

    CreateListPage(
        modifier = Modifier.fillMaxSize(),
        uiState = createListUiState,
        onBackClick = onBackClick,
        onCreate = { listName, listDescription ->
            if (listName.length > 20) {
                Toast.makeText(context, context.getString(R.string.key_list_name_exceeds_maximum), Toast.LENGTH_SHORT).show()
                return@CreateListPage
            }
            if (listDescription.length > 300) {
                Toast.makeText(context, context.getString(R.string.key_list_description_exceeds_maximum), Toast.LENGTH_SHORT).show()
                return@CreateListPage
            }
            viewModel.createList(CreateListParam(listName, listDescription))
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateListPage(
    modifier: Modifier = Modifier,
    uiState: CreateListUiState,
    onBackClick: (Boolean) -> Unit,
    onCreate: (String, String) -> Unit,
) {

    var listName by rememberSaveable { mutableStateOf("") }
    var listDescription by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = stringResource(id = R.string.key_create_list),
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    onBackClick(false)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                        contentDescription = ""
                    )
                }
            },
        )

        Text(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp),
            text = stringResource(R.string.key_name),
            style = MaterialTheme.typography.titleMedium
        )
        ListInputTextField(
            modifier = Modifier.padding(top = 16.dp),
            textValue = listName,
            labelTextValue = stringResource(R.string.key_list_name),
            maxLines = 1,
            minLines = 1,
            maxCharacterCount = 20,
            onValueChange = {
                listName = it
            },
            imeAction = ImeAction.Next,
            readOnly = uiState is CreateListUiState.Loading
        )
        Text(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp),
            text = stringResource(R.string.key_description),
            style = MaterialTheme.typography.titleMedium
        )
        ListInputTextField(
            modifier = Modifier.padding(top = 16.dp),
            textValue = listDescription,
            labelTextValue = stringResource(R.string.key_list_description),
            minLines = 5,
            maxLines = 5,
            maxCharacterCount = 300,
            onValueChange = {
                listDescription = it
            },
            imeAction = ImeAction.Done,
            readOnly = uiState is CreateListUiState.Loading
        )

        Button(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 36.dp)
                .height(56.dp)
                .fillMaxWidth(),
            enabled = listName.isNotEmpty() && listDescription.isNotEmpty(),
            onClick = {
                onCreate(listName, listDescription)
            },
        ) {
            if (uiState is CreateListUiState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    strokeCap = StrokeCap.Round,
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = stringResource(R.string.key_create),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCreateListPage() {
    TMDBMovieTheme {
        CreateListPage(
            modifier = Modifier.fillMaxSize(),
            onBackClick = {},
            onCreate = { _, _ -> },
            uiState = CreateListUiState.Loading
        )
    }
}