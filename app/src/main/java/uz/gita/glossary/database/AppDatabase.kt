package uz.gita.glossary.database

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.glossary.model.Word


@Database(
    entities = [
        Word::class
    ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun wordDao(): WordDao

}