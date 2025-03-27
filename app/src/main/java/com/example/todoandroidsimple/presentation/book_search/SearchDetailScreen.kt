package com.example.todoandroidsimple.presentation.book_search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.magnifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.todoandroidsimple.ui.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchDetailScreen(
    navController: NavController,
    bookId: String,
) {

    val parentEntry = remember(navController.currentBackStackEntry) {
        navController.getBackStackEntry(Screen.Search.route)
    }

    val viewModel: SearchViewModel = hiltViewModel(parentEntry)

    val books by viewModel.books.collectAsState()
    val book = books.find { it.id == bookId }
    val uriHandler = LocalUriHandler.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Search Detail" ) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            if (book == null) {
                Text("Book not found")
                return@Scaffold
            }

//            Image(
//                painter = rememberAsyncImagePainter(book.thumbnail),
//                contentDescription = "Book Cover",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(250.dp),
//                contentScale = ContentScale.Crop
//            )
            println("00000000005555555555");
            println("Thumbnail URL: ${book.thumbnail}")
            Spacer(modifier = Modifier.height(16.dp))

            Text(book.title, style = MaterialTheme.typography.headlineSmall)
            Text(book.authors, style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(8.dp))
            Text(book.description ?: "No Description", style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(16.dp))
            book.previewLink?.let { link ->
                Button(onClick = {
                    uriHandler.openUri(link)
                }) {
                    Text("Read Preview")
                }
            }
        }
    }
}
