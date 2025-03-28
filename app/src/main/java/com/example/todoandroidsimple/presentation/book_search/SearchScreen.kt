package com.example.todoandroidsimple.presentation.book_search

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.todoandroidsimple.data.BookItem
import com.example.todoandroidsimple.ui.Screen
import kotlinx.coroutines.delay

@Composable
fun SearchScreen(navController: NavController, viewModel: SearchViewModel = hiltViewModel()) {

    val searchQuery by viewModel.searchValue.collectAsStateWithLifecycle()
    val books by viewModel.books.collectAsState()

    SearchContent(
        books = books,
        searchQuery = searchQuery,
        onClick = { bookId ->
            navController.navigate(Screen.SearchDetail.createRoute(bookId))
        },
        onSaveClick = { bookId ->
            viewModel.saveBookById(bookId)
        },
        onUpdate = {
            viewModel.updateSearchValue(it)
        }
    )
}

@Composable
fun SearchContent(
    books: List<BookItem>,
    searchQuery: String,
    onClick: (String) -> Unit,
    onSaveClick: (String) -> Unit,
    onUpdate: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onUpdate,
            label = { Text("Search Books") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions.Default,

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))


        Text("Search")


        Spacer(modifier = Modifier.height(16.dp))

        if (books.isEmpty()) {
            Text("No books found", color = Color.Gray)
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {

                items(books) { book ->

                    BookItem(
                        book = book,
                        onSaveClick = onSaveClick,
                        onClick = onClick
                    )
                }
            }
        }
    }
}

@Composable
fun BookItem(book: BookItem, onSaveClick: (String) -> Unit, onClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(book.bookId) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {


            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(book.title, style = MaterialTheme.typography.titleMedium)
                Text(book.authors, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                Button(onClick = { onSaveClick(book.bookId) }) {
                    println("666666")
                    Text("Save")
                }
            }
        }
    }
}

@Preview(device = "id:pixel_4", showSystemUi = true)
@Composable
fun SearchContentPreview() {
    SearchContent(
        books = listOf(BookItem("gdhsg", title = "tutle", authors = "authors")),
        searchQuery = "",
        onClick = {},
        onUpdate = {},
        onSaveClick = {},
    )
}


