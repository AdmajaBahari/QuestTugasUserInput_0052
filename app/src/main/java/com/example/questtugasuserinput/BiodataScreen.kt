package com.example.questtugasuserinput

import androidx.annotation.DimenRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.questtugasuserinput.ui.theme.QuestTugasUserInputTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@Composable
private fun textSize(@DimenRes id: Int) = dimensionResource(id).value.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BiodataScreen() {
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var birthDate by rememberSaveable { mutableStateOf("") }
    var showDatePicker by rememberSaveable { mutableStateOf(false) }

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
                    Spacer(Modifier.height(dimensionResource(R.dimen.section_gap)))

                    // Nama depan & belakang
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.field_gap))
                    ) {
                        FormField(
                            value = firstName,
                            onValueChange = { firstName = it },
                            hint = stringResource(R.string.hint_first_name),
                            modifier = Modifier.weight(1f)
                        )
                        FormField(
                            value = lastName,
                            onValueChange = { lastName = it },
                            hint = stringResource(R.string.hint_last_name),
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(Modifier.height(dimensionResource(R.dimen.field_gap)))

                    // Email
                    FormField(
                        value = email,
                        onValueChange = { email = it },
                        hint = stringResource(R.string.hint_email),
                        modifier = Modifier.fillMaxWidth(),
                        keyboardType = KeyboardType.Email
                    )
                    Spacer(Modifier.height(dimensionResource(R.dimen.field_gap)))

                    // Tgl lahir (kolom kanan diisi dropdown di langkah berikutnya)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.field_gap))
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            FormField(
                                value = birthDate,
                                onValueChange = {},
                                hint = stringResource(R.string.hint_birth_date),
                                modifier = Modifier.fillMaxWidth(),
                                readOnly = true,
                                trailingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.DateRange,
                                        contentDescription = stringResource(R.string.cd_pick_date),
                                        tint = colorResource(R.color.text_hint)
                                    )
                                }
                            )
                            // Overlay agar seluruh field bisa diklik
                            Box(
                                modifier = Modifier
                                    .matchParentSize()
                                    .clip(RoundedCornerShape(dimensionResource(R.dimen.field_corner)))
                                    .clickable { showDatePicker = true }
                            )
                        }
                        Spacer(Modifier.weight(1f))
                    }
                }
            }
        }
    }

    // Date picker dialog
    if (showDatePicker) {
        val datePickerState = rememberDatePickerState()
        val pattern = stringResource(R.string.date_pattern)
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
                        formatter.timeZone = TimeZone.getTimeZone("UTC")
                        birthDate = formatter.format(Date(millis))
                    }
                    showDatePicker = false
                }) { Text(stringResource(R.string.date_picker_ok)) }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text(stringResource(R.string.date_picker_cancel))
                }
            }
        ) {
            DatePicker(state = datePickerState)
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

@Composable
private fun FormField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        readOnly = readOnly,
        singleLine = true,
        placeholder = {
            Text(text = hint, fontSize = textSize(R.dimen.text_field))
        },
        textStyle = LocalTextStyle.current.copy(fontSize = textSize(R.dimen.text_field)),
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        shape = RoundedCornerShape(dimensionResource(R.dimen.field_corner)),
        colors = fieldColors(),
        modifier = modifier
    )
}

@Composable
private fun fieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = colorResource(R.color.field_border_focused),
    unfocusedBorderColor = colorResource(R.color.field_border),
    focusedPlaceholderColor = colorResource(R.color.text_hint),
    unfocusedPlaceholderColor = colorResource(R.color.text_hint),
    focusedTextColor = colorResource(R.color.text_body),
    unfocusedTextColor = colorResource(R.color.text_body),
    cursorColor = colorResource(R.color.purple_primary)
)

@Preview(showBackground = true)
@Composable
fun BiodataScreenPreview() {
    QuestTugasUserInputTheme {
        BiodataScreen()
    }
}