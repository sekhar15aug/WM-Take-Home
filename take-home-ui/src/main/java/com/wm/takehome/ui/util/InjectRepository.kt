package com.wm.takehome.ui.util

import com.wm.takehome.domain.repositories.ListRepository

// TODO: should be able to avoid with dependency injection
object InjectRepository {
    lateinit var listRepository: ListRepository
}