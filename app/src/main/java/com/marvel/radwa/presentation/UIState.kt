//package com.marvel.radwa.presentation
//
//sealed class UIState<T>(
//    val data: T? = null,
//    val error: String? = null,
//    val loading: Boolean = false
//) {
//    class Success<T>(data: T?) : UIState<T>(data)
//    class Loading<T>(show: Boolean) : UIState<T>(loading = show)
//    class Error<T>(message: String?) : UIState<T>(error = message)
//}