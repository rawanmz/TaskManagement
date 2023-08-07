package com.example.taskmanagement.viewmodel.art

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.art.GetArtWorksUseCase
import com.example.model.UIState
import com.example.model.art.ArtWorksResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtWorksViewModel @Inject constructor(
    private val getArtWorksUseCase: GetArtWorksUseCase
) : ViewModel() {
    var artWork: MutableState<UIState<ArtWorksResponse>> = mutableStateOf(UIState.Loading())

    init {
        getArtWorks()
    }

    fun getArtWorks() {
        viewModelScope.launch {
            when (val response = getArtWorksUseCase.invoke()) {
                is UIState.Success -> artWork.value = UIState.Success(response.responseData!!)
                is UIState.Error -> artWork.value = UIState.Error(response.error)
                is UIState.Empty -> artWork.value =
                    UIState.Empty(title = response.title, iconRes = response.iconRes)
                else -> {}
            }
        }
    }
}