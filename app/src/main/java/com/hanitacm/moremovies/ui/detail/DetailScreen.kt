package com.hanitacm.moremovies.ui.detail


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hanitacm.moremovies.R
import com.hanitacm.moremovies.ui.theme.MoreMoviesTheme

@Composable
fun CountryDateMovie(countryDate: String, rating: Double) {
    Row(
        modifier = Modifier
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
        CountryDateMovie("EN | 2022-12-07", 8.3)
    }
}