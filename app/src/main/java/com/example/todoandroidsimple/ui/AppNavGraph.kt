package com.example.todoandroidsimple.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.todoandroidsimple.presentation.book.SearchScreen
import com.example.todoandroidsimple.presentation.book.BookViewModel
import com.example.todoandroidsimple.presentation.ProfileScreen
import com.example.todoandroidsimple.presentation.book.BooksScreen
import com.example.todoandroidsimple.presentation.note.NoteDetailScreen
import com.example.todoandroidsimple.presentation.note.NoteListScreen
import com.example.todoandroidsimple.presentation.note.NoteViewModel

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.NoteList.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.NoteList.route) {
                val viewModel: NoteViewModel = hiltViewModel()
                NoteListScreen(navController, viewModel)
            }
            composable(Screen.Search.route) {
                val viewModel: BookViewModel = hiltViewModel()
                SearchScreen(navController, viewModel)
            }
            composable(Screen.Books.route) { BooksScreen(navController) }
            composable(Screen.Profile.route) { ProfileScreen(navController) }
            composable(
                route = Screen.NoteDetail.route,
                arguments = listOf(navArgument("noteId") { type = NavType.IntType })
            ) { backStackEntry ->
                val noteId = backStackEntry.arguments?.getInt("noteId") ?: 0
                NoteDetailScreen(navController, noteId)
            }
        }
    }
}
