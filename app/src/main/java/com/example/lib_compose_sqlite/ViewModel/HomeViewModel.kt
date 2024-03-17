package com.example.lib_compose_sqlite.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {

        }

    }

    override fun onCleared() {
        super.onCleared()
    }
}