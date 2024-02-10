package com.pdrake.netflixclone.controllers

import com.pdrake.netflixclone.entities.tvshow.TvShow
import com.pdrake.netflixclone.repos.TvShowRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tv-show")
class TvShowController(private val tvShowRepository: TvShowRepository) {
    @GetMapping("/fetch-all")
    fun fetchAll(): List<TvShow> {
        return tvShowRepository.findAll()
    }
}
