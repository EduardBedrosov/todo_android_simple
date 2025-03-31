package com.example.todoandroidsimple.presentation.saved

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler

import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import java.io.File


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailedScreen(
    navController: NavController,
    bookId: String,
    viewModel: BookDetailedViewModel = hiltViewModel()
) {
    val book by viewModel.book.collectAsState()
    val uriHandler = LocalUriHandler.current

    LaunchedEffect(bookId) {
        viewModel.loadBook(bookId)
    }

    println("11111 121231233 $book")
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Book Detail") },
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
                println("11111 123 null")
                Text("Loading...")
                return@Column
            }

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(File(book!!.thumbnailLocalUri))
                    .crossfade(true)
                    .build(),
                contentDescription = "Book Cover",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .widthIn(max = 400.dp)
                    .height(250.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(16.dp))


            Text(book!!.title, style = MaterialTheme.typography.headlineSmall)
            Text(book!!.authors, style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(8.dp))
            Text(book!!.description, style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { uriHandler.openUri(book!!.previewLink) }) {
                Text("Read Preview")
            }
        }
    }
}

