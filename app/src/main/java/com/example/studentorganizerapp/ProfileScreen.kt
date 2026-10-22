package com.example.studentorganizerapp



import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen() {

    val context = LocalContext.current

    androidx.compose.material3.Scaffold(

        topBar = {

            AppBar(
                title = "Student Profile",
                canNavigateBack = false,
                onBackClick = {}
            )
        }

    ) { paddingValues ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),

            horizontalAlignment =
                Alignment.CenterHorizontally,

            verticalArrangement =
                Arrangement.spacedBy(16.dp)
        ) {

            StudentProfile(

                studentName = "Chiranthan B S Raj",

                studentId = "PES1UG24CS134",

                major = "Computer Science Engineering",

                avatarRes = 0
            )

            Card(

                modifier = Modifier.fillMaxWidth(),

                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        text = "Academic Statistics",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    AcademicRow(
                        label = "GPA",
                        value = "3.82"
                    )

                    AcademicRow(
                        label = "Completed Credits",
                        value = "84"
                    )

                    AcademicRow(
                        label = "Current Semester",
                        value = "Fall 2026"
                    )
                }
            }


            Text(
                text = "Quick Actions",
                style = MaterialTheme.typography.titleLarge
            )

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.spacedBy(10.dp)
            ) {

                OutlinedButton(

                    onClick = {

                        Toast.makeText(
                            context,
                            "Edit Profile selected",
                            Toast.LENGTH_SHORT
                        ).show()
                    },

                    modifier = Modifier.weight(1f)
                ) {

                    Text("Edit Profile")
                }

                Button(

                    onClick = {

                        Toast.makeText(
                            context,
                            "Academic summary exported",
                            Toast.LENGTH_SHORT
                        ).show()
                    },

                    modifier = Modifier.weight(1f)
                ) {

                    Text("Export Summary")
                }
            }
        }
    }
}

@Composable
fun StudentProfile(
    studentName: String,
    studentId: String,
    major: String,
    avatarRes: Int
) {

    Card(

        modifier = Modifier.fillMaxWidth(),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            androidx.compose.material3.Surface(

                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),

                shape = CircleShape,

                color = MaterialTheme.colorScheme.primaryContainer
            ) {

                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Student Avatar",
                    modifier = Modifier.padding(20.dp)
                )
            }

            Spacer(
                modifier = Modifier.size(16.dp)
            )

            Column {

                Text(
                    text = studentName,
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = studentId,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = major,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun AcademicRow(
    label: String,
    value: String
) {

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),

        horizontalArrangement =
            Arrangement.SpaceBetween
    ) {

        Text(
            text = label
        )

        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium
        )
    }
}