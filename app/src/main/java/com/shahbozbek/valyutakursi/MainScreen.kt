package com.shahbozbek.valyutakursi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@Composable
fun MainScreen(navController: NavController, viewModel: MyViewModel = viewModel()) {
    val currencyList by viewModel.currencyData.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)
    val error by viewModel.error.observeAsState(null)

    LaunchedEffect(Unit) {
        viewModel.fetchData()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Valyuta kurslari",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF161513)
            )
            if (currencyList.isNotEmpty()) {
                Text(
                    text = currencyList[0].Date,
                    fontSize = 18.sp,
                    color = Color(0xFF333333),
                    fontWeight = FontWeight.Bold
                )
            } else {
                Text(
                    text = "15.09.2024", fontSize = 16.sp, color = Color(0xFF333333),
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        when {
            isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    CircularProgressIndicator()
                }
            }

            error != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    Text(text = error ?: "Unknown error", color = Color.Red)
                }
            }

            else -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(currencyList) { rates ->// Replace 10 with your dynamic data count
                        CurrencyItem(
                            navController = navController,
                            rate = rates
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun painter(countryCode: String) = rememberAsyncImagePainter(
    model = "https://flagcdn.com/w20/${countryCode.lowercase()}.png",
)

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(navController = NavController(LocalContext.current))
}