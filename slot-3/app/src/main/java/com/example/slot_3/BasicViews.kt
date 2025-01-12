package com.example.slot_3

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BasicViewsDemo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WelcomeText()

        PartDivider()

        ButtonGroup()

        PartDivider()

        CheckboxGroup()

        PartDivider()

        RadiobuttonGroup()

        PartDivider()
    }
}

@Composable
fun WelcomeText(){
    Text(
        text = "Slot 3 - Code Demo \n\n \uD83E\uDDA5 Group 1",
        fontSize = 35.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.error,
        modifier = Modifier.padding(bottom = 16.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
fun PartDivider(){
    HorizontalDivider(
        color = Color.Gray,
        thickness = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 50.dp, top = 50.dp)
    )
}

@Composable
fun ButtonGroup(){
    var buttonName by remember { mutableStateOf("None") }

    Text("Buttons Demo", fontSize = 20.sp, fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.height(16.dp))

    Button(onClick = {
        buttonName = "Filled Button"
    }) {
        Text("Filled Button")
    }
    Spacer(modifier = Modifier.height(16.dp))
    FilledTonalButton(onClick = {
        buttonName = "Filled Tonal Button"
    }) {
        Text("Filled Tonal Button")

    }
    Spacer(modifier = Modifier.height(16.dp))
    OutlinedButton(onClick = {
        buttonName = "Outlined Button"
    }) {
        Text("Outlined Button")
    }
    Spacer(modifier = Modifier.height(16.dp))
    ElevatedButton(onClick = {
        buttonName = "Elevated Button"
    }) {
        Text("Elevated Button")
    }
    Spacer(modifier = Modifier.height(16.dp))
    TextButton(onClick = {
        buttonName = "Text Button"
    }) {
        Text("Text Button")
    }

    Spacer(modifier = Modifier.height(16.dp))
    Text("Clicked button: $buttonName")
}

@Composable
fun CheckboxGroup(){
    val childCheckedState = remember { mutableStateListOf(false, false, false) }

    val parentState = when {
        childCheckedState.all { it } -> ToggleableState.On
        childCheckedState.none { it } -> ToggleableState.Off
        else -> ToggleableState.Indeterminate
    }

    Column (horizontalAlignment = Alignment.CenterHorizontally) {

        Text("Checkbox Demo", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        // Parent Checkbox
        Row (verticalAlignment = Alignment.CenterVertically) {
            Text("Select all")
            TriStateCheckbox(
                state = parentState,
                onClick = {
                    val newState = parentState != ToggleableState.On
                    childCheckedState.forEachIndexed {
                        index, _ -> childCheckedState[index] = newState
                    }
                }
            )
        }

        // Child Checkboxes
        childCheckedState.forEachIndexed { index, checked ->
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text( "Option ${index + 1}")
                Checkbox(
                    checked = checked,
                    onCheckedChange = { isChecked ->
                        childCheckedState[index] = isChecked

                    }
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    val selectedOptions = when {
        childCheckedState.all { it } -> "All"
        childCheckedState.none { it } -> "None"
        else -> childCheckedState.mapIndexedNotNull { index, checked ->
            if (checked) "Option ${index + 1}" else null
        }.joinToString(", ")
    }

    Text("Options selected: $selectedOptions")
}

@Composable
fun RadiobuttonGroup(){
    val radioOptions = listOf("Option 1", "Option 2", "Option 3")

    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    Column (
        modifier = Modifier.selectableGroup(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text ("Radio Button Demo", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        radioOptions.forEach { option ->
            Row (
                modifier = Modifier
                    .height(48.dp)
                    .selectable(
                        selected = (option == selectedOption),
                        onClick = { onOptionSelected(option) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (option == selectedOption),
                    onClick = null
                )
                Text(
                    text = option,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Selected option: $selectedOption")
    }
}

