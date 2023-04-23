package com.hanitacm.moremovies.ui.detail


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.hanitacm.moremovies.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.Locale

@SuppressLint("CoroutineCreationDuringComposition", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieDetail(viewModel: DetailViewModel, movieId: Int) {

    viewModel.getMovieDetail(movieId)

    val uiState: DetailViewModelState by viewModel.viewState.collectAsStateWithLifecycle()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState) {
        when (uiState) {
            is DetailViewModelState.DetailLoadFailure -> {
                (uiState as DetailViewModelState.DetailLoadFailure).error.message?.let {
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = it
                        )
                    }
                }
            }

            is DetailViewModelState.DetailLoaded -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    with(uiState as DetailViewModelState.DetailLoaded) {
                        AsyncImage(
                            modifier = Modifier.height(250.dp),
                            model = "https://image.tmdb.org/t/p/w780${movie.backdropPath}",
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )

                        MovieDescription(
                            title = movie.title,
                            countryDate = "${movie.originalLanguage.uppercase(Locale.getDefault())} | ${movie.releaseDate}",
                            rating = movie.voteAverage,
                            overview = movie.overview,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }

                }

            }

            DetailViewModelState.Loading ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) { CircularProgressIndicator() }
        }
    }
}


@Composable
private fun MovieDescription(
    title: String,
    countryDate: String,
    rating: Double,
    overview: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        text = title,
        style = TextStyle(fontSize = 24.sp)
    )
    CountryDateMovie(countryDate, rating, modifier)
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        text = overview,
        lineHeight = 25.sp,
        fontSize = 16.sp
    )
}

@Composable
fun CountryDateMovie(countryDate: String, rating: Double, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                fontSize = 16.sp,
                text = countryDate,
            )
        }
        RatingElement(rating)
    }
}

@Composable
private fun RatingElement(rating: Double) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.ic_star), contentDescription = null)
        Text(
            modifier = Modifier
                .padding(top = 8.dp),
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = 17.sp, fontWeight = FontWeight.Bold)) {
                    append(rating.toString())
                }
                append("/10")
            },
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
        )
    }
}

@Composable
@Preview(device = "id:pixel_3")
private fun CountryDateMoviePreview() {
    CircularProgressIndicator()
}