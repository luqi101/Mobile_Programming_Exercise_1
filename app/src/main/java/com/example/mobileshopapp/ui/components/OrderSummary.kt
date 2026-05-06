package com.example.mobileshopapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun OrderSummary(
    subtotalCents: Int,
    taxCents: Int,
    grandTotalCents: Int,
    onCheckout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        tonalElevation = 2.dp,
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Order Summary",
                style = MaterialTheme.typography.titleLarge
            )
            SummaryRow(label = "Subtotal", value = formatCents(subtotalCents))
            SummaryRow(label = "Tax (13%)", value = formatCents(taxCents))
            HorizontalDivider()
            SummaryRow(
                label = "Grand Total",
                value = formatCents(grandTotalCents),
                strong = true
            )
            Button(
                onClick = onCheckout,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("checkout_button")
            ) {
                Text("Checkout")
            }
        }
    }
}

@Composable
private fun SummaryRow(
    label: String,
    value: String,
    strong: Boolean = false
) {
    val style = if (strong) {
        MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
    } else {
        MaterialTheme.typography.bodyLarge
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, style = style)
        Text(text = value, style = style, color = MaterialTheme.colorScheme.primary)
    }
}
