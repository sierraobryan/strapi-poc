package com.example.androiddevchallenge.ui.screens.content

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.data.ContentRepository
import com.example.androiddevchallenge.data.model.ContentScreenState
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

    val screenState = MutableStateFlow(ContentScreenState())

    init {
        getProducts()
    }

    private fun getProducts() {
        if (!fetched) {
            fetched = true
            viewModelScope.launch {
                val content = contentRepository.getContent()
                if (content.body() != null && content.body() != screenState.value.products) {
                    screenState.value = screenState.value.copy(
                        products = content.body() ?: emptyList()
                    )
                } else if (content.errorBody() != null) {
                    screenState.value = screenState.value.copy(
                        error = true
                    )
                }
            }
        }
    }
}