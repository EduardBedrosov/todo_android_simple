package com.example.todoandroidsimple.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.todoandroidsimple.presentation.book_search.SearchDetailScreen
import com.example.todoandroidsimple.presentation.book_search.SearchScreen
import com.example.todoandroidsimple.presentation.book_search.SearchViewModel
import com.example.todoandroidsimple.presentation.profile.ProfileScreen
import com.example.todoandroidsimple.presentation.note.NoteDetailScreen
import com.example.todoandroidsimple.presentation.note.NoteListScreen
import com.example.todoandroidsimple.presentation.note.NoteViewModel
import com.example.todoandroidsimple.presentation.saved.SavedScreen
import com.example.todoandroidsimple.presentation.saved.SavedViewModel

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
                val viewModel: SearchViewModel = hiltViewModel()
                SearchScreen(navController, viewModel)
            }

            composable(Screen.Saved.route) {
                val viewModel: SavedViewModel = hiltViewModel()
                SavedScreen(navController, viewModel)
            }

            composable(Screen.Profile.route) { ProfileScreen(navController) }

            composable(
                route = Screen.NoteDetail.route,
                arguments = listOf(navArgument("noteId") { type = NavType.IntType })
            ) { backStackEntry ->
                val noteId = backStackEntry.arguments?.getInt("noteId") ?: 0
                NoteDetailScreen(navController, noteId)
            }

//            composable(
//                route = Screen.SearchDetail.route,
//                arguments = listOf(navArgument("bookId") { type = NavType.StringType })
//            ) { backStackEntry ->
//                val bookId = backStackEntry.arguments?.getString("bookId") ?: ""
//                SearchDetailScreen(navController, bookId)
//            }

            composable(
                route = Screen.SearchDetail.route,
                arguments = listOf(navArgument("bookId") { type = NavType.StringType })
            ) { backStackEntry ->
                val bookId = backStackEntry.arguments?.getString("bookId") ?: return@composable
                SearchDetailScreen(navController, bookId)
            }

        }
    }
}
