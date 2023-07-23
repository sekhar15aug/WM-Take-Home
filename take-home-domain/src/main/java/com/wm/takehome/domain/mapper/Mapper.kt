package com.wm.takehome.domain.mapper

/**
 * Mapper interface for I to O
 */
interface Mapper<I, O> {
    fun map(input: I): O
}