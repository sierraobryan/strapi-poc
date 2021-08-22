package com.example.androiddevchallenge.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.ContentRepository
import com.example.androiddevchallenge.data.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    private val contentRepository: ContentRepository
) : ViewModel() {

    private var fetched = false

    private val _productState: MutableStateFlow<List<Product>> = MutableStateFlow(emptyList())
    val productState: StateFlow<List<Product>> = _productState

    init {
        getProducts()
    }

    private fun getProducts() {
        if (!fetched) {
            fetched = true
            viewModelScope.launch {
                val content = contentRepository.getContent()
                if (content.body() != null && content.body() != _productState.value) {
                    _productState.value = content.body() ?: emptyList()
                }
            }
        }
    }
}