package com.shahbozbek.valyutakursi.presentation.ui.components

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.shahbozbek.valyutakursi.R
import com.shahbozbek.valyutakursi.core.Constants.CHAR_DOT
import com.shahbozbek.valyutakursi.core.Constants.COUNTRY_CODE_UZ
import com.shahbozbek.valyutakursi.core.Constants.EMPTY_TEXT
import com.shahbozbek.valyutakursi.core.Constants.UZS
import com.shahbozbek.valyutakursi.domain.model.Currency
import com.shahbozbek.valyutakursi.presentation.ui.main.screen.painter

@Composable
fun ConversionScreen(
    navController: NavController,
    rateJson: String?,
) {
    val myValue = remember { mutableStateOf(EMPTY_TEXT) }
    val rate = Gson().fromJson(rateJson, Currency::class.java)

    val isChanged = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFEF))
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .size(30.dp),
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.size(30.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Konvertatsiya",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Jonli tariflarni tekshiring, miqdorni kiriting va konvertatsiya natijasiga ega bo‘ling.",
            fontSize = 18.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(24.dp))
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text(text = "Miqdori", fontSize = 18.sp, color = Color(0xFF808080))
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    val countryCode = rate?.Ccy?.substring(0, 2)?.lowercase() ?: EMPTY_TEXT
                    Image(
                        painter = painter(countryCode = if (isChanged.value) countryCode else COUNTRY_CODE_UZ),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = if (isChanged.value) rate?.Ccy ?: EMPTY_TEXT else UZS,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF26278D)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    OutlinedTextField(
                        value = myValue.value,
                        onValueChange = { newText ->
                            if (newText.all { it.isDigit() || it == CHAR_DOT } &&
                                newText.count { it == CHAR_DOT } <= 1) {
                                myValue.value = newText
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 2,
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF333333)
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFEFEFEF),
                            unfocusedContainerColor = Color(0xFFEFEFEF),
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(8.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        )
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    HorizontalDivider(
                        color = Color(0xFFE7E7E7),
                        thickness = 1.dp,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Card(
                        shape = CircleShape,
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF26278D)),
                        modifier = Modifier.size(40.dp),
                        onClick = {
                            isChanged.value = !isChanged.value
                        }
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.swap_vert),
                                contentDescription = "Swap",
                                tint = Color.White,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    HorizontalDivider(
                        color = Color(0xFFE7E7E7),
                        thickness = 1.dp,
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Konvertatsiya qilingan miqdor", fontSize = 18.sp, color = Color(
                        0x80808080
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    val countryCode = rate?.Ccy?.substring(0, 2)?.lowercase() ?: EMPTY_TEXT
                    Image(
                        painter = painter(countryCode = if (isChanged.value) COUNTRY_CODE_UZ else countryCode),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = if (isChanged.value) UZS else rate?.Ccy ?: EMPTY_TEXT,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF26278D)
                    )
                    val result = if (isChanged.value) {
                        if (myValue.value.isNotEmpty() && rate != null) {
                            "%.4f".format(myValue.value.toDouble() * rate.Rate.toDouble())
                        } else {
                            EMPTY_TEXT
                        }
                    } else {
                        if (myValue.value.isNotEmpty() && rate != null) {
                            "%.4f".format(myValue.value.toDouble() / rate.Rate.toDouble())
                        } else {
                            EMPTY_TEXT
                        }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    OutlinedTextField(
                        value = result,
                        onValueChange = {

                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        maxLines = 1,
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0x99333333)
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFEFEFEF),
                            unfocusedContainerColor = Color(0xFFEFEFEF),
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(8.dp),
                        readOnly = true
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Indikativ valyuta kursi",
            fontSize = 16.sp,
            color = Color(0xFF808080),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "1 ${rate?.Ccy} = ${rate?.Rate} $UZS",
            fontSize = 18.sp,
            color = Color(0xFF333333),
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold
        )
    }
}
