package com.al4apps.movies.presentation.movies

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun GenreItem(genre: String) {
    Row(
        modifier = Modifier.apply {
            fillMaxWidth()
            padding(start = 16.dp)
        }
    ) {
        Text(
            text = genre,
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewItems() {
    MaterialTheme {
        GenreItem("Приключения")
    }
}