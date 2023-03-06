package uz.gita.glossary.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "eng_uz")
data class Word(
    @PrimaryKey
    @ColumnInfo(name = "Id")
    val id: Int,
    @ColumnInfo(name = "en_word")
    val engWord: String?,
    val type: String?,
    val transcript: String?,
    val translation: String?,
    val description: String?,
    var countable: String?,
    var favourite: Int?

) : Parcelable