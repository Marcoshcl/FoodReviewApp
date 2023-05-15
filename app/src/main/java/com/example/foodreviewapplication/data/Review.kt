package com.example.foodreviewapplication.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat

@Entity(tableName = "review_table")
@Parcelize
data class Review(
    val nomeRestaurante: String,
    val nomePrato: String,
    val nota: String,
    val foto1: String,
    val foto2: String? = null,
    val foto3: String? = null,
    val foto4: String? = null,
    val foto5: String? = null,
    val localização: String,
    val created: Long = System.currentTimeMillis(),
    val comentario: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : Parcelable {
    val createdDateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(created)
}
