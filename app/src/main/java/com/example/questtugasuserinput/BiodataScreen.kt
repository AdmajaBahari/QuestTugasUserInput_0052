package com.example.questtugasuserinput

import androidx.annotation.DimenRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.questtugasuserinput.ui.theme.QuestTugasUserInputTheme

@Composable
private fun textSize(@DimenRes id: Int) = dimensionResource(id).value.sp

@Composable
fun BiodataScreen() {
    Scaffold(
        containerColor = colorResource(R.color.screen_background)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Header image
            Image(
                painter = painterResource(R.drawable.bg_spider_neon),
                contentDescription = stringResource(R.string.cd_background),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.header_image_height))
            )

            // Page indicator dots
            Row(
                modifier = Modifier
                    .padding(
                        top = dimensionResource(R.dimen.dot_padding_top),
                        start = dimensionResource(R.dimen.dot_padding_start)
                    ),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.dot_gap))
            ) {
                Dot(active = true)
                Dot(active = false)
                Dot(active = false)
                Dot(active = false)
            }

            // Box yang mengapung di atas Scaffold
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(top = dimensionResource(R.dimen.header_image_height) / 2)
                    .padding(bottom = dimensionResource(R.dimen.card_bottom_gap))
                    .shadow(
                        elevation = dimensionResource(R.dimen.card_elevation),
                        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner))
                    )
                    .background(
                        color = colorResource(R.color.card_background),
                        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner))
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = dimensionResource(R.dimen.card_padding_horizontal),
                            end = dimensionResource(R.dimen.card_padding_horizontal),
                            top = dimensionResource(R.dimen.card_padding_top),
                            bottom = dimensionResource(R.dimen.card_padding_bottom)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.form_title),
                        color = colorResource(R.color.purple_primary),
                        fontSize = textSize(R.dimen.text_title),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(dimensionResource(R.dimen.subtitle_gap)))
                    Text(
                        text = stringResource(R.string.form_subtitle),
                        color = colorResource(R.color.text_subtitle),
                        fontSize = textSize(R.dimen.text_subtitle),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
private fun Dot(active: Boolean) {
    Box(
        modifier = Modifier
            .size(dimensionResource(R.dimen.dot_size))
            .background(
                color = if (active) colorResource(R.color.purple_dot_active)
                else colorResource(R.color.dot_inactive),
                shape = CircleShape
            )
    )
}

@Preview(showBackground = true)
@Composable
fun BiodataScreenPreview() {
    QuestTugasUserInputTheme {
        BiodataScreen()
    }
}