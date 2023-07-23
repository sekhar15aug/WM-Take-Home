package com.wm.takehome.domain.util

sealed class DomainResource<out R> {
    data class Success<out T>(val data: T) : DomainResource<T>()
    object Loading : DomainResource<Nothing>()
    object Error : DomainResource<Nothing>()
}
