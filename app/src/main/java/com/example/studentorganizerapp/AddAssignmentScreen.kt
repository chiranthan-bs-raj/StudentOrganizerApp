package com.example.studentorganizerapp


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAssignmentScreen(
    onBackClick: () -> Unit,
    onSaveAssignment: (Assignment) -> Unit
) {

    var title by remember {
        mutableStateOf("")
    }

    var course by remember {
        mutableStateOf("")
    }

    var dueDate by remember {
        mutableStateOf("")
    }

    var priority by remember {
        mutableStateOf("Medium")
    }

    var description by remember {
        mutableStateOf("")
    }

    var reminderEnabled by remember {
        mutableStateOf(false)
    }

    val isFormValid =
        title.isNotBlank() &&
                course.isNotBlank() &&
                dueDate.isNotBlank()

    Scaffold(

        topBar = {

            androidx.compose.material3.TopAppBar(

                title = {
                    Text(
                        text = "Add New Assignment"
                    )
                },

                navigationIcon = {

                    IconButton(
                        onClick = onBackClick
                    ) {

                        Icon(
                            imageVector =
                                Icons.Default.ArrowBack,

                            contentDescription = "Back"
                        )
                    }
                }
            )
        }

    ) { paddingValues ->

        AssignmentForm(

            title = title,
            course = course,
            dueDate = dueDate,
            priority = priority,
            description = description,
            reminderEnabled = reminderEnabled,

            onTitleChange = {
                title = it
            },

            onCourseChange = {
                course = it
            },

            onDueDateChange = {
                dueDate = it
            },

            onPriorityChange = {
                priority = it
            },

            onDescriptionChange = {
                description = it
            },

            onReminderChange = {
                reminderEnabled = it
            },

            isFormValid = isFormValid,

            onCancel = onBackClick,

            onSave = {

                val newAssignment =
                    Assignment(

                        id =
                            (System.currentTimeMillis() % 100000).toInt(),

                        title = title.trim(),

                        course = course.trim(),

                        dueDate = dueDate.trim(),

                        priority = priority,

                        description =
                            description.trim(),

                        reminderEnabled =
                            reminderEnabled,

                        isCompleted = false
                    )

                onSaveAssignment(
                    newAssignment
                )
            },

            modifier = Modifier.padding(
                paddingValues
            )
        )
    }
}

@Composable
fun AssignmentForm(
    title: String,
    course: String,
    dueDate: String,
    priority: String,
    description: String,
    reminderEnabled: Boolean,

    onTitleChange: (String) -> Unit,
    onCourseChange: (String) -> Unit,
    onDueDateChange: (String) -> Unit,
    onPriorityChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onReminderChange: (Boolean) -> Unit,

    isFormValid: Boolean,

    onCancel: () -> Unit,
    onSave: () -> Unit,

    modifier: Modifier = Modifier
) {

    LazyColumn(

        modifier = modifier.padding(16.dp),

        verticalArrangement =
            Arrangement.spacedBy(14.dp)
    ) {

        item {

            OutlinedTextField(

                value = title,

                onValueChange =
                    onTitleChange,

                label = {
                    Text("Assignment Title")
                },

                placeholder = {
                    Text("Enter assignment title")
                },

                singleLine = true,

                modifier = Modifier.fillMaxWidth()
            )
        }

        item {

            OutlinedTextField(

                value = course,

                onValueChange =
                    onCourseChange,

                label = {
                    Text("Course Name / Code")
                },

                placeholder = {
                    Text("Example: CS101")
                },

                singleLine = true,

                modifier = Modifier.fillMaxWidth()
            )
        }

        item {

            OutlinedTextField(

                value = dueDate,

                onValueChange =
                    onDueDateChange,

                label = {
                    Text("Due Date")
                },

                placeholder = {
                    Text("Example: Oct 20, 2026")
                },

                trailingIcon = {

                    Icon(
                        imageVector =
                            Icons.Default.DateRange,

                        contentDescription =
                            "Calendar"
                    )
                },

                singleLine = true,

                modifier = Modifier.fillMaxWidth()
            )
        }

        item {

            Text(
                text = "Priority Level"
            )

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween,

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                PriorityOption(
                    text = "Low",
                    selected = priority == "Low",
                    onSelected = {
                        onPriorityChange("Low")
                    }
                )

                PriorityOption(
                    text = "Medium",
                    selected = priority == "Medium",
                    onSelected = {
                        onPriorityChange("Medium")
                    }
                )

                PriorityOption(
                    text = "High",
                    selected = priority == "High",
                    onSelected = {
                        onPriorityChange("High")
                    }
                )
            }
        }

        item {

            OutlinedTextField(

                value = description,

                onValueChange =
                    onDescriptionChange,

                label = {
                    Text("Description / Notes")
                },

                placeholder = {
                    Text(
                        "Enter additional information"
                    )
                },

                minLines = 3,

                maxLines = 5,

                modifier = Modifier.fillMaxWidth()
            )
        }

        item {

            Row(

                modifier = Modifier.fillMaxWidth(),

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Checkbox(

                    checked =
                        reminderEnabled,

                    onCheckedChange =
                        onReminderChange
                )

                Text(
                    text =
                        "Enable Reminder Notification"
                )
            }
        }

        item {

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {

                OutlinedButton(

                    onClick = onCancel,

                    modifier = Modifier.weight(1f)
                ) {

                    Text("Cancel")
                }

                Button(

                    onClick = onSave,

                    enabled = isFormValid,

                    modifier = Modifier.weight(1f)
                ) {

                    Text("Save Assignment")
                }
            }
        }
    }
}

@Composable
fun PriorityOption(
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