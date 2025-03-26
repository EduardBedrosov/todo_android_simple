package com.example.todoandroidsimple.ui


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    data object NoteList : Screen("note_list", "Home", Icons.Filled.Home)
    data object Search : Screen("search", "Search", Icons.Filled.Search)
    data object Books : Screen("books", "Saved", Icons.Filled.Favorite)
    data object Profile : Screen("profile", "Profile", Icons.Filled.Person)
    data object NoteDetail : Screen("note_detail/{noteId}", "Book Detail", Icons.Filled.Info) {
        fun createRoute(noteId: Int) = "note_detail/$noteId"
    }
}
