package com.example.studentorganizerapp



import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.PendingActions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun DashboardScreen(
    assignments: List<Assignment>,
    onAssignmentToggle: (Int) -> Unit,
    onAddAssignment: () -> Unit
) {

    androidx.compose.material3.Scaffold(

        topBar = {

            AppBar(
                title = "Welcome back, Chiranthan B S Raj!",
                canNavigateBack = false,
                onBackClick = {}
            )
        },

        floatingActionButton = {

            FloatingActionButton(
                onClick = onAddAssignment
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Assignment"
                )
            }
        }

    ) { paddingValues ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),

            verticalArrangement =
                Arrangement.spacedBy(16.dp)
        ) {

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {

                StatCard(
                    title = "Active Courses",
                    value = "4",
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.CheckCircle
                )

                StatCard(
                    title = "Pending Tasks",
                    value = "7",
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.PendingActions
                )
            }

            Text(
                text = "Active Courses",
                style = MaterialTheme.typography.titleLarge
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(
                        rememberScrollState()
                    ),

                horizontalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {

                CourseInfo(
                    courseName = "Data Structures",
                    courseCode = "DSA",
                    instructor = "Dr. Kumar",
                    progress = 0.75f
                )

                CourseInfo(
                    courseName = "Database Management",
                    courseCode = "DBMS",
                    instructor = "Dr. Sharma",
                    progress = 0.65f
                )

                CourseInfo(
                    courseName = "Automata & Formal Languages",
                    courseCode = "AFLL",
                    instructor = "Dr. Rao",
                    progress = 0.80f
                )

                CourseInfo(
                    courseName = "Web Technologies",
                    courseCode = "WT",
                    instructor = "Prof. Mehta",
                    progress = 0.55f
                )
            }

            Text(
                text = "Urgent Deadlines",
                style = MaterialTheme.typography.titleLarge
            )

            val urgentAssignments =
                assignments.take(2)

            Column(
                verticalArrangement =
                    Arrangement.spacedBy(10.dp)
            ) {

                urgentAssignments.forEach { assignment ->

                    AssignmentCard(

                        title = assignment.title,

                        course = assignment.course,

                        dueDate = assignment.dueDate,

                        priority = assignment.priority,

                        isCompleted = assignment.isCompleted,

                        onToggleComplete = {
                            onAssignmentToggle(
                                assignment.id
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun StatCard(
    title: String,
    value: String,
    modifier: Modifier,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {

    Card(

        modifier = modifier,

        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Icon(
                imageVector = icon,
                contentDescription = null
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun CourseInfo(
    courseName: String,
    courseCode: String,
    instructor: String,
    progress: Float
) {

    Card(
        modifier = Modifier.width(230.dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = courseCode,
                style = MaterialTheme.typography.labelLarge
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = courseName,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = instructor,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            androidx.compose.material3.LinearProgressIndicator(

                progress = {
                    progress.coerceIn(0f, 1f)
                },

                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = "${(progress * 100).toInt()}% completed",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Composable
fun AssignmentCard(
    title: String,
    course: String,
    dueDate: String,
    priority: String,
    isCompleted: Boolean,
    onToggleComplete: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),

            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = course,
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "Due: $dueDate",
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    text = "Priority: $priority",
                    style = MaterialTheme.typography.labelMedium
                )
            }

            androidx.compose.material3.Checkbox(

                checked = isCompleted,

                onCheckedChange = {
                    onToggleComplete()
                }
            )
        }
    }
}