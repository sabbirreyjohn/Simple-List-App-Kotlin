package xyz.androidrey.basiclistapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

@Entity
data class Picture(
    @PrimaryKey
    @Json(name = "id") val picId: String,
    @Json(name = "title") val picTitle: String,
    @Json(name = "url") val picUrl: String,
    @Json(name = "thumbnailUrl") val picThumbnail: String
) : Serializable
