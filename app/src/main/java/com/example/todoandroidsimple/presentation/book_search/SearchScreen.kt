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
import com.example.todoandroidsimple.data.local.book.BookEntity
import com.example.todoandroidsimple.ui.Screen
import kotlinx.coroutines.delay

@Composable
fun SearchScreen(navController: NavController, viewModel: SearchViewModel = hiltViewModel()) {


    val searchQuery by viewModel.searchValue.collectAsStateWithLifecycle()
    val books by viewModel.books.collectAsState()


    var debouncedQuery by remember { mutableStateOf("") }
//    var debouncedQuery by remember { mutableStateOf(TextFieldValue("")) }

    LaunchedEffect(searchQuery) {
//        if (searchQuery.isNotBlank() && searchQuery.isNotEmpty()) {
//            viewModel.searchBooks(searchQuery)
//        }
        delay(1500) // Debounce delay of 500ms
        debouncedQuery = searchQuery
    }

    LaunchedEffect(debouncedQuery) {
        if (debouncedQuery.isNotBlank()) {
            viewModel.searchBooks(debouncedQuery)
        }
    }
    SearchContent(onClickItem = {
        navController.navigate(Screen.SearchDetail.createRoute(book.id))
    })
}

@Composable
fun SearchContent(
    onClickItem:(String) -> Unit,

) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search Books") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                if (searchQuery.isNotBlank() && searchQuery.isNotEmpty()) {
                    viewModel.searchBooks(searchQuery)
                }
            }),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (searchQuery.isNotBlank()) {
                    println("111111");
                    viewModel.searchBooks(searchQuery)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Search")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (books.isEmpty()) {
            Text("No books found", color = Color.Gray)
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {

                items(books) { book ->
                    println("0000000000");
                    println("All book IDs1: ${books.map { it.id }}")
                    println("Requested bookId1: $books.id")
                    BookItem(
                        book = book,
                        onSaveClick = { viewModel.saveBook(book) },
                        onClick = {
                            onClickItem(book)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun BookItem(book: BookEntity, onSaveClick: () -> Unit, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            println("00000000004444444");
            println("Thumbnail URL: ${book.thumbnail}")
//            Image(
//                painter = rememberAsyncImagePainter(book.thumbnail),
//                contentDescription = "Book Cover",
//                modifier = Modifier.size(100.dp),
//                contentScale = ContentScale.Crop
//            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(book.title, style = MaterialTheme.typography.titleMedium)
                Text(book.authors, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                Button(onClick = onSaveClick) {
                    println("666666")
                    Text("Save")
                }
            }
        }
    }
}
//
//@Preview(
//    device = "id:pixel_6_pro"
//)
//@Composable
//fun SearchScreenPreview() {
//
//}
