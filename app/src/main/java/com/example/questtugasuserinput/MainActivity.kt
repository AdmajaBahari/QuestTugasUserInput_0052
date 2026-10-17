package com.example.questtugasuserinput

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.questtugasuserinput.ui.theme.QuestTugasUserInputTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuestTugasUserInputTheme {
                BiodataScreen()
            }
        }
    }
}

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
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BiodataScreenPreview() {
    QuestTugasUserInputTheme {
        BiodataScreen()
    }
}