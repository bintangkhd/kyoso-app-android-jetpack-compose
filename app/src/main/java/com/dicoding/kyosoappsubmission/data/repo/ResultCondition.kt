package com.dicoding.kyosoappsubmission.data.repo

sealed class ResultCondition<out T: Any?> {
    object LoadingState : ResultCondition<Nothing>()
    data class SuccessState<out T: Any>(val data: T) : ResultCondition<T>()
    data class ErrorState(val message: String) : ResultCondition<Nothing>()
}