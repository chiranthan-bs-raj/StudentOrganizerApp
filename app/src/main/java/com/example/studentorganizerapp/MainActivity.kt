package com.example.studentorganizerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


data class Assignment(
    val id: Int,
    val title: String,
    val course: String,
    val dueDate: String,
    val priority: String,
    val description: String = "",
    val reminderEnabled: Boolean = false,
    val isCompleted: Boolean = false
)

sealed class Screen(val route: String) {

    data object Dashboard : Screen("dashboard")

    data object Assignments : Screen("assignments")

    data object Profile : Screen("profile")

    data object Settings : Screen("settings")

    data object AddAssignment : Screen("add_assignment")
}

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StudentOrganizerApp()
        }
    }
}

@Composable
fun StudentOrganizerApp() {

    val navController = rememberNavController()

    var darkMode by remember {
        mutableStateOf(false)
    }

    var defaultStartScreen by remember {
        mutableStateOf(Screen.Dashboard.route)
    }

    val assignments = remember {
        mutableStateListOf(

            Assignment(
                id = 1,
                title = "Database Management Assignment",
                course = "DBMS",
                dueDate = "Oct 12, 2026",
                priority = "High",
                description = "Complete the transaction isolation assignment.",
                reminderEnabled = true
            ),

            Assignment(
                id = 2,
                title = "Data Structures Lab",
                course = "DSA",
                dueDate = "Oct 14, 2026",
                priority = "High",
                description = "Implement trees and traversal algorithms.",
                reminderEnabled = true
            ),

            Assignment(
                id = 3,
                title = "Automata Theory Assignment",
                course = "AFLL",
                dueDate = "Oct 18, 2026",
                priority = "Medium"
            ),

            Assignment(
                id = 4,
                title = "Web Technologies Project",
                course = "WT",
                dueDate = "Oct 20, 2026",
                priority = "Medium"
            ),

            Assignment(
                id = 5,
                title = "Mathematics Problem Set",
                course = "M1",
                dueDate = "Oct 22, 2026",
                priority = "Low"
            )
        )
    }

    MaterialTheme(
        colorScheme = if (darkMode) {
            androidx.compose.material3.darkColorScheme()
        } else {
            androidx.compose.material3.lightColorScheme()
        }
    ) {

        val backStackEntry by navController.currentBackStackEntryAsState()

        val currentRoute =
            backStackEntry?.destination?.route

        val showBottomBar =
            currentRoute != Screen.AddAssignment.route

        Scaffold(

            bottomBar = {

                if (showBottomBar) {

                    BottomBar(
                        navController = navController,
                        currentRoute = currentRoute
                    )
                }
            }

        ) { innerPadding ->

            NavHost(
                navController = navController,
                startDestination = Screen.Dashboard.route,
                modifier = Modifier.padding(innerPadding)
            ) {

                composable(Screen.Dashboard.route) {

                    DashboardScreen(

                        assignments = assignments,

                        onAssignmentToggle = { id ->

                            val index =
                                assignments.indexOfFirst {
                                    it.id == id
                                }

                            if (index != -1) {

                                assignments[index] =
                                    assignments[index].copy(
                                        isCompleted =
                                            !assignments[index].isCompleted
                                    )
                            }
                        },

                        onAddAssignment = {

                            navController.navigate(
                                Screen.AddAssignment.route
                            )
                        }
                    )
                }

                composable(Screen.Assignments.route) {

                    AssignmentsScreen(

                        assignments = assignments,

                        onAssignmentToggle = { id ->

                            val index =
                                assignments.indexOfFirst {
                                    it.id == id
                                }

                            if (index != -1) {

                                assignments[index] =
                                    assignments[index].copy(
                                        isCompleted =
                                            !assignments[index].isCompleted
                                    )
                            }
                        },

                        onAddAssignment = {

                            navController.navigate(
                                Screen.AddAssignment.route
                            )
                        }
                    )
                }

                composable(Screen.Profile.route) {

                    ProfileScreen()
                }

                composable(Screen.Settings.route) {

                    SettingsScreen(

                        darkMode = darkMode,

                        onDarkModeChange = {
                            darkMode = it
                        },

                        notificationsEnabled = true,

                        onNotificationsChange = {
                            // UI state only for assignment
                        },

                        defaultStartScreen = defaultStartScreen,

                        onDefaultStartScreenChange = {
                            defaultStartScreen = it
                        }
                    )
                }

                composable(Screen.AddAssignment.route) {

                    AddAssignmentScreen(

                        onBackClick = {

                            navController.popBackStack()
                        },

                        onSaveAssignment = { newAssignment ->

                            assignments.add(newAssignment)

                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String,
    canNavigateBack: Boolean,
    onBackClick: () -> Unit
) {

    TopAppBar(

        title = {
            Text(
                text = title
            )
        },

        navigationIcon = {

            if (canNavigateBack) {

                IconButton(
                    onClick = onBackClick
                ) {

                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}

@Composable
fun BottomBar(
    navController: NavHostController,
    currentRoute: String?
) {

    NavigationBar {

        NavigationBarItem(

            selected =
                currentRoute == Screen.Dashboard.route,

            onClick = {

                navController.navigate(
                    Screen.Dashboard.route
                ) {

                    popUpTo(
                        Screen.Dashboard.route
                    ) {
                        saveState = true
                    }

                    launchSingleTop = true
                    restoreState = true
                }
            },

            icon = {

                Icon(
                    Icons.Default.Dashboard,
                    contentDescription = "Dashboard"
                )
            },

            label = {
                Text("Dashboard")
            }
        )

        NavigationBarItem(

            selected =
                currentRoute == Screen.Assignments.route,

            onClick = {

                navController.navigate(
                    Screen.Assignments.route
                ) {

                    popUpTo(
                        Screen.Dashboard.route
                    ) {
                        saveState = true
                    }

                    launchSingleTop = true
                    restoreState = true
                }
            },

            icon = {

                Icon(
                    Icons.Default.Assignment,
                    contentDescription = "Assignments"
                )
            },

            label = {
                Text("Assignments")
            }
        )

        NavigationBarItem(

            selected =
                currentRoute == Screen.Profile.route,

            onClick = {

                navController.navigate(
                    Screen.Profile.route
                ) {

                    popUpTo(
                        Screen.Dashboard.route
                    ) {
                        saveState = true
                    }

                    launchSingleTop = true
                    restoreState = true
                }
            },

            icon = {

                Icon(
                    Icons.Default.Person,
                    contentDescription = "Profile"
                )
            },

            label = {
                Text("Profile")
            }
        )

        NavigationBarItem(

            selected =
                currentRoute == Screen.Settings.route,

            onClick = {

                navController.navigate(
                    Screen.Settings.route
                ) {

                    popUpTo(
                        Screen.Dashboard.route
                    ) {
                        saveState = true
                    }

                    launchSingleTop = true
                    restoreState = true
                }
            },

            icon = {

                Icon(
                    Icons.Default.Settings,
                    contentDescription = "Settings"
                )
            },

            label = {
                Text("Settings")
            }
        )
    }
}