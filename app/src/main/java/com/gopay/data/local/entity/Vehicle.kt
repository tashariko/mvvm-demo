package com.gopay.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "vehicle")
data class Vehicle (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val pilots: ArrayList<String>? = null,
):Parcelable
