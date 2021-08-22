package com.example.androiddevchallenge.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.androiddevchallenge.data.model.Product

@Composable
fun ContentScreen(viewModel: ContentViewModel) {

    val products by viewModel.productState.collectAsState()

    Content(list = products)
}

@Composable
fun Content(list: List<Product>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        itemsIndexed(list) { _, product ->
            Text(text = product.title)
        }
    }
}