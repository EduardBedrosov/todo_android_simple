package com.example.todoandroidsimple.presentation.book_search

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchDetailScreen(
    navController: NavController,
    bookId: String,
    viewModel: SearchDetailViewModel = hiltViewModel()
) {
    val book by viewModel.book.collectAsState()
    val uriHandler = LocalUriHandler.current

    LaunchedEffect(bookId) {
        viewModel.loadBook(bookId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Search Detail") },
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
                Text("Loading...")
                return@Column
            }
//            AsyncImage(
//                model = ImageRequest.Builder(LocalContext.current)
//                    .data(book!!.thumbnail)
//                    .crossfade(true)
//                    .build(),
//                contentDescription = "null",
//                contentScale = ContentScale.Inside,
//                modifier = Modifier
//                    .widthIn(max = 220.dp)
//                    .clip(RoundedCornerShape(8.dp))
//            )


//            LoadImageFromUrl(book!!.thumbnail)
            http vor baci
            online search anes, add anes favoritner, internet@ anjates app-@ baces gnas favorite, u cash-er@ maqreluc heto nklar@ baci
            nkar@ download vorpes base64, grum es baza
            LoadImageFromUrl("https://books.google.com/books/content?id=R_EgqMa7uMMC&printsec=frontcover&img=1&zoom=1&edge=curl&imgtk=AFLRE70hhL3uTI3mBWsZT6OJwNQfBYfhKh68OEGwL758c1cYIsD9iFVyoENhY8D-7L2n8uniJHD5HIi_VMMWKBspHLhdvhy0IwClwr9Mv4ht1B3yDdZyqCAVEN5YNC_ZzZJJtfuWvskY&source=gbs_api")


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

@Composable
fun LoadImageFromUrl(url: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = "Loaded Image",
        modifier = Modifier
            .size(200.dp)
            .padding(16.dp),
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewImage() {
    MaterialTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            LoadImageFromUrl("https://books.google.com/books/content?id=R_EgqMa7uMMC&printsec=frontcover&img=1&zoom=1&edge=curl&imgtk=AFLRE70hhL3uTI3mBWsZT6OJwNQfBYfhKh68OEGwL758c1cYIsD9iFVyoENhY8D-7L2n8uniJHD5HIi_VMMWKBspHLhdvhy0IwClwr9Mv4ht1B3yDdZyqCAVEN5YNC_ZzZJJtfuWvskY&source=gbs_api")
        }
    }
}