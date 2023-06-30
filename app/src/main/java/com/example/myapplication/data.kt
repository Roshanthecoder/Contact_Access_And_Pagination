package com.example.myapplication

import java.io.Serializable

data class Movies(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
):Serializable
data class Result(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
):Serializable

class ContactsArray : ArrayList<ContactItem>()
data class ContactItem(
    val name: String,
    val mobile: String,
    val color: Int
) : Serializable

data class Contact(val name: String, val number: String)

