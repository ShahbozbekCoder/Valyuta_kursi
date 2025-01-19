package com.shahbozbek.valyutakursi

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson


@Composable
fun CurrencyItem(
    navController: NavController,
    rate: ConverterDataItem
) {
    Column(
        modifier = Modifier.clickable {
            val rateJson = Gson().toJson(rate)
            navController.navigate("conversionScreen/$rateJson")
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val countryCode = rate.Ccy.substring(0, 2).lowercase()
            Image(
                painter = painter(countryCode), // Replace with actual flag resource
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = rate.Ccy, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = rate.CcyNm_UZ, fontSize = 14.sp, color = Color(0xFF808080))
            }
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(text = rate.Rate + " UZS", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = rate.Diff + "%",
                        fontSize = 16.sp,
                        color = Color(0xFF808080)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        painter = if (rate.Diff.startsWith("-")) painterResource(id = R.drawable.arrow_down)
                        else painterResource(id = R.drawable.arrow_outward),
                        contentDescription = null,
                        tint = if (rate.Diff.startsWith("-")) Color.Red else Color.Green
                    )
                }
            }
        }
        HorizontalDivider(modifier = Modifier.padding(horizontal = 48.dp))
    }
}
