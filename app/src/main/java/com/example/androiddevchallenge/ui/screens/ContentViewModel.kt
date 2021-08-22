package com.example.androiddevchallenge.ui.screens

import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(contentRepository: ContentRepository) : ViewModel() {
}