package com.example.taskmanagement.viewmodel.exhibition

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.data.repository.ArtWorkRepository
import com.example.domain.exhibition.GetExhibitionByIdUseCase
import com.example.model.UIState
import com.example.model.exhibition.ExhibitionData
import com.example.model.exhibition.ExhibitionResponse
import com.example.paging.ExhibitionsPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

const val PAGE_SIZE = 100
const val STATE_KEY_PAGE = "page_key"

@HiltViewModel
class ExhibitionsViewModel @Inject constructor(
//    private val getExhibitionsUseCase: GetExhibitionsUseCase,
    private val artWorkRepository: ArtWorkRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val loading = mutableStateOf(false)
    val page = mutableStateOf(1)
    private val filterQuery = mutableStateOf<String?>(null)
    var neverLoadMore: Boolean = false
    private val _exhibitionsState =
        MutableStateFlow<UIState<ExhibitionResponse?>>(UIState.Loading())
    val exhibitionsState = _exhibitionsState.asStateFlow()

    var exhibitionsList: MutableState<List<ExhibitionData>> = mutableStateOf(ArrayList())

    val getExhibitions = Pager(
        PagingConfig(10)){
        ExhibitionsPagingSource(artWorkRepository)
    }.flow.cachedIn(viewModelScope)

//    fun getExhibitions(): Flow<PagingData<ExhibitionData>> =
//        getExhibitionsUseCase.invoke(PAGE_SIZE).cachedIn(viewModelScope)

//    fun getExhibitions() {
//        if (neverLoadMore) {
//            return
//        }
//        viewModelScope.launch {
//
//            loading.value = true
//            when (val result = getExhibitionsUseCase(
//                pageNumber = page.value,
//                pageLimitSize = PAGE_SIZE,
//            )) {
//                is UIState.Success -> {
//                    _exhibitionsState.value = UIState.Success(result.responseData)
//                    if (getNextPageNumber(result.responseData?.pagination?.nextUrl.orEmpty()).toIntOrNull() == page.value) {
//                        neverLoadMore = true
//                    }
//
//                    incrementPage()
////                    if(page.value==4 || page.value==6 ){
////                        page.value=page.value+1
////                    }
//                    if (page.value > 1) {
//                        _exhibitionsState.value = UIState.Success(result.responseData)
//                        appendMessages(result.responseData?.data.orEmpty())
//                        loading.value = false
//                    }
//                }
//                is UIState.Error -> {
//                    page.value=page.value+1
//                    getExhibitions()
//                    //_exhibitionsState.value = UIState.Error(result.error)
//                }
//                is UIState.Empty -> {
//                    _exhibitionsState.value =
//                        UIState.Empty("No more data ", "there is no more exhibitions to show")
//                }
//                else -> {}
//            }
//        }
//    }

    private fun setPage(page: Int) {
        this.page.value = page
        savedStateHandle[STATE_KEY_PAGE] = page
    }

    fun incrementPage() {
        setPage(page.value + 1)
    }

    fun getNextPageNumber(url: String): String {
        return url.substringAfterLast("=")
    }

    private fun appendMessages(messages: List<ExhibitionData>) {
        val current = ArrayList(this.exhibitionsList.value)
        current.addAll(messages)
        exhibitionsList.value = current
    }
}