package com.batmorell.fueltracker.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.batmorell.fueltracker.theme.AppTheme

/**
 * A UI that displays 4 key stats and 2 large buttons (+Food, +Drink).
 * You might call this from your MainActivity or any other Composable.
 */

@Composable
fun FuelTrackingScreen() {
    // For demo purposes, we store these stats in local state (remember).
    // In a real app, you might retrieve these from a ViewModel or extension logic.
    var avgBurn by remember { mutableStateOf(79) }
    var burn by remember { mutableStateOf(147) }
    var avgIntake by remember { mutableStateOf(46) }
    var depletion by remember { mutableStateOf(122) }

    // The screen’s root column
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Top part: a 2×2 grid of stats
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // First row: "Avg burn" & "Burn"
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatBlock(
                    title = "Avg burn",
                    value = "$avgBurn g/hr"
                )
                StatBlock(
                    title = "Burn",
                    value = "$burn g/hr"
                )
            }

            // Second row: "Avg intake" & "Depletion"
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatBlock(
                    title = "Avg intake",
                    value = "$avgIntake g/hr"
                )

                // A special block for depletion, showing a mini progress bar
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    // Customize max depletion threshold as you like (e.g., 200 or 300)
                    val maxDepletion = 200f
                    val progress = depletion.coerceAtMost(maxDepletion.toInt()) / maxDepletion

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Depletion",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        // Put a row with the progress bar + numeric depletion side by side
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            LinearProgressIndicator(
                                progress = progress,
                                modifier = Modifier
                                    .width(60.dp)
                                    .clip(MaterialTheme.shapes.small)
                            )
                            Text("$depletion g")
                        }
                    }
                }
            }
        }

        // Bottom part: row with two big buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // "Food" button
            Button(
                onClick = {
                    // For demo, increment avgIntake or reduce depletion, etc.
                    // E.g., let's assume each press adds 20 g to avgIntake:
                    avgIntake += 20
                    depletion -= 20
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(text = "+ Food")
            }

            // "Drink" button
            Button(
                onClick = {
                    // For demo, let's say each press lowers depletion by 10
                    depletion -= 10
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF009FE3) // some custom color
                )
            ) {
                Text(text = "+ Drink")
            }
        }
    }
}

/** A simple reusable composable for a labeled numeric stat. */
@Composable
fun StatBlock(
    title: String,
    value: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(
    widthDp = 300,
    heightDp = 500,
    showBackground = true
)
@Composable
fun FuelTrackingScreenPreview() {
    AppTheme {
        FuelTrackingScreen()
    }
}
