package com.mosso.shared.job.exceptions

sealed class UseCaseException : kotlin.Exception() {
    class GenericException(code: Int = 0, errorMessage: String = "") : Exception(errorMessage)
}