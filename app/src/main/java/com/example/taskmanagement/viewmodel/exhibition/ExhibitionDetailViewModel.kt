package com.example.taskmanagement.viewmodel.exhibition

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.exhibition.GetExhibitionByIdUseCase
import com.example.model.UIState
import com.example.model.exhibition.ExhibitionByIdResponse
import com.example.model.exhibition.ExhibitionData
import com.example.model.exhibition.ExhibitionResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExhibitionDetailViewModel @Inject constructor(
    private val getExhibitionByIdUseCase: GetExhibitionByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var exhibition: MutableState<UIState<ExhibitionByIdResponse?>> = mutableStateOf(UIState.Loading())
    val exhibitionId = savedStateHandle.get<Long>("exhibitionId")

    init {
        getExhibitions()
    }

    fun getExhibitions() {
        viewModelScope.launch {
            when (val result = getExhibitionByIdUseCase(
                id = exhibitionId ?: 0
            )) {
                is UIState.Success -> {
                    exhibition.value = UIState.Success(result.responseData)
                }
                is UIState.Error -> {
                    exhibition.value = UIState.Error(result.error)
                }
                is UIState.Empty -> {
                    exhibition.value =
                        UIState.Empty("No more data ", "there is no more exhibitions to show")
                }
                else -> {}
            }
        }
    }
}