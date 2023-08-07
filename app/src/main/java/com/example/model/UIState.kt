package com.example.model

sealed class UIState<T>(
    val responseData: T? = null,
    open val message: String? = null
) {
    class Loading<T> : UIState<T>()
    class Success<T>(data: T) : UIState<T>(responseData = data)
    class Error<T>(val error: String) : UIState<T>(message = error)

    data class Empty<T>(
        val title: String? = "",
        override val message: String? = "",
        val iconRes: Int = -1
    ) : UIState<T>()
}