package com.example.studentorganizerapp



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AssignmentsScreen(
    assignments: List<Assignment>,
    onAssignmentToggle: (Int) -> Unit,
    onAddAssignment: () -> Unit
) {

    var selectedFilter by remember {
        mutableStateOf("All")
    }

    val filteredAssignments = when (selectedFilter) {

        "Pending" ->
            assignments.filter {
                !it.isCompleted
            }

        "Completed" ->
            assignments.filter {
                it.isCompleted
            }

        else ->
            assignments
    }

    Scaffold(

        topBar = {

            AppBar(
                title = "My Assignments",
                canNavigateBack = false,
                onBackClick = {}
            )
        },

        floatingActionButton = {

            FloatingActionButton(
                onClick = onAddAssignment
            ) {

                androidx.compose.material3.Icon(
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
                .padding(horizontal = 16.dp)
        ) {

            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),

                horizontalArrangement =
                    Arrangement.SpaceEvenly,

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                FilterOption(
                    text = "All",
                    selected = selectedFilter == "All",
                    onSelected = {
                        selectedFilter = "All"
                    }
                )

                FilterOption(
                    text = "Pending",
                    selected = selectedFilter == "Pending",
                    onSelected = {
                        selectedFilter = "Pending"
                    }
                )

                FilterOption(
                    text = "Completed",
                    selected = selectedFilter == "Completed",
                    onSelected = {
                        selectedFilter = "Completed"
                    }
                )
            }

            LazyColumn(

                modifier = Modifier.fillMaxSize(),

                verticalArrangement =
                    Arrangement.spacedBy(10.dp)
            ) {

                items(
                    items = filteredAssignments,
                    key = {
                        it.id
                    }
                ) { assignment ->

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
fun FilterOption(
    text: String,
    selected: Boolean,
    onSelected: () -> Unit
) {

    Row(
        verticalAlignment =
            Alignment.CenterVertically
    ) {

        RadioButton(
            selected = selected,
            onClick = onSelected
        )

        Text(
            text = text
        )
    }
}