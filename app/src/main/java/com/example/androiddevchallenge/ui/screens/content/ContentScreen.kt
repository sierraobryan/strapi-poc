package com.example.androiddevchallenge.ui.screens.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.androiddevchallenge.data.model.ContentScreenState
import com.example.androiddevchallenge.data.model.Product

@Composable
fun ContentScreen(viewModel: ContentViewModel) {
    val state by viewModel.screenState.collectAsState()

    if (state.error) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Oh no, something went wrong!!")
        }
    } else {
        ListOfProducts(products = state.products)
    }
}

@Composable
fun ListOfProducts(products: List<Product>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(products) {
            Text(text = it.title)
        }
    }
}