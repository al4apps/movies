package com.al4apps.movies.presentation.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.al4apps.movies.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {

    private val viewModel by viewModel<MoviesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MoviesFragmentScreen()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel
    }

    @Composable
    private fun MoviesFragmentScreen() {


    }



    @Composable
    private fun GenresTitleItem() {
        Row(
            modifier = Modifier.apply {
                fillMaxWidth()
                padding(start = 16.dp)
            }
        ) {
            Text(
                text = stringResource(R.string.movies_genres_title),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

    @Composable
    private fun GenreItem(genre: String) {
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
}

/**         Preview             **/

@Composable
fun GenresTitleItem() {
    Row(
        modifier = Modifier.apply {
            fillMaxWidth()
            padding(start = 16.dp)
        }
    ) {
        Text(
            text = stringResource(R.string.movies_genres_title),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun Preview() {
    GenresTitleItem()
}