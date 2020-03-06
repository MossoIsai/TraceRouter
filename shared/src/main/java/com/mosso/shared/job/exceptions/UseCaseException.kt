package com.mosso.shared.job.exceptions

sealed class UseCaseException : kotlin.Exception() {
    class GenericException(errorMessage: String = "") : Exception(errorMessage)
}