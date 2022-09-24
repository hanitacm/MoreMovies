package com.hanitacm.data.datasource.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "releaseDate") val releaseDate: String,
    @ColumnInfo(name = "posterPath") val posterPath: String,
    @ColumnInfo(name = "backdropPath") val backdropPath: String,
    @ColumnInfo(name = "originalLanguage") val originalLanguage: String,
    @ColumnInfo(name = "originalTitle") val originalTitle: String,
    @ColumnInfo(name = "popularity") val popularity: Double,
    @ColumnInfo(name = "voteAverage") val voteAverage: Double
)


