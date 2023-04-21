package com.hanitacm.moremovies.ui.detail


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import coil.compose.AsyncImage
import com.hanitacm.moremovies.R
import com.hanitacm.moremovies.ui.theme.MoreMoviesTheme

@Composable
fun MovieDetail(
    image: String? = null,
    title: String,
    countryDate: String,
    rating: Double,
    overview: String
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            modifier = Modifier.height(250.dp),
            model = image,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        MovieDescription(title, countryDate, rating, overview, Modifier.padding(horizontal = 16.dp))
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
    MoreMoviesTheme {
        MovieDetail(
            title = "Puss in Boots: The Last Wish",
            countryDate = "EN | 2022-12-07",
            rating = 8.3,
            overview = "While working underground to fix a water main, Brooklyn plumbers—and brothers—Mario and Luigi are transported down a mysterious pipe and wander into a magical new world. But when the brothers are separated, Mario embarks on an epic quest to find Luigi."
        )
    }
}