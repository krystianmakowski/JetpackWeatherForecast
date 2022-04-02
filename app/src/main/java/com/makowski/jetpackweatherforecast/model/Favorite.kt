package com.makowski.jetpackweatherforecast.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_table")
data class Favorite(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "city")
    val city: String,
    @ColumnInfo
    val country: String)
