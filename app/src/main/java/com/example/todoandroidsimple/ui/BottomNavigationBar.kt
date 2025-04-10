package com.example.todoandroidsimple.ui

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    val screens = listOf(
        Screen.NoteList,
        Screen.Search,
        Screen.Saved,
        Screen.Profile
    )

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar {
        screens.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = { navController.navigate(screen.route) },
                icon = { screen.icon?.let { Icon(it, contentDescription = screen.title) } },
                label = { Text(screen.title) }
            )
        }
    }
}
