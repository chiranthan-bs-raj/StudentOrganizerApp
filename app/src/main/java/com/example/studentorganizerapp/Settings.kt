package com.example.studentorganizerapp



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(
    darkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit,

    notificationsEnabled: Boolean,
    onNotificationsChange: (Boolean) -> Unit,

    defaultStartScreen: String,
    onDefaultStartScreenChange: (String) -> Unit
) {

    Scaffold(

        topBar = {

            AppBar(
                title = "Settings",
                canNavigateBack = false,
                onBackClick = {}
            )
        }

    ) { paddingValues ->

        Column(

            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),

            verticalArrangement =
                Arrangement.spacedBy(16.dp)
        ) {

            Row(

                modifier = Modifier.fillMaxWidth(),

                verticalAlignment =
                    Alignment.CenterVertically,

                horizontalArrangement =
                    Arrangement.SpaceBetween
            ) {

                Column {

                    Text(
                        text = "Dark Mode"
                    )

                    Text(
                        text = "Use dark appearance"
                    )
                }

                Switch(

                    checked = darkMode,

                    onCheckedChange =
                        onDarkModeChange
                )
            }

            HorizontalDivider()

            Row(

                modifier = Modifier.fillMaxWidth(),

                verticalAlignment =
                    Alignment.CenterVertically,

                horizontalArrangement =
                    Arrangement.SpaceBetween
            ) {

                Column {

                    Text(
                        text = "Push Notifications"
                    )

                    Text(
                        text = "Receive assignment reminders"
                    )
                }

                Checkbox(

                    checked =
                        notificationsEnabled,

                    onCheckedChange =
                        onNotificationsChange
                )
            }

            HorizontalDivider()

            Text(
                text = "Default Start Screen"
            )

            Row(
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                RadioButton(

                    selected =
                        defaultStartScreen ==
                                Screen.Dashboard.route,

                    onClick = {

                        onDefaultStartScreenChange(
                            Screen.Dashboard.route
                        )
                    }
                )

                Text(
                    text = "Dashboard"
                )
            }

            Row(
                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                RadioButton(

                    selected =
                        defaultStartScreen ==
                                Screen.Assignments.route,

                    onClick = {

                        onDefaultStartScreenChange(
                            Screen.Assignments.route
                        )
                    }
                )

                Text(
                    text = "Assignments"
                )
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Preferences are stored for the current app session.",
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}