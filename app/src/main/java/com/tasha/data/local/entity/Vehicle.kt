package com.tasha.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "vehicle")
data class Vehicle(

    val name: String,
    val manufacturer: String? = null,
    val model: String? = null,
    @ColumnInfo("hyperdrive_rating")
    @SerializedName("hyperdrive_rating")
    val hyperdriveRating: String? = null,

    @SerializedName("starship_class")
    @ColumnInfo("starship_class")
    val starshipClass: String? = null,
    val pilots: ArrayList<String>? = null,
    val url:String? = null
):Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

}