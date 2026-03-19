package service.a2econsult.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import service.a2econsult.app.data.dao.BookingDao
import service.a2econsult.app.data.database.converter.Converters
import service.a2econsult.app.data.entity.BookingEntity

@Database(
    entities = [BookingEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {

    abstract fun bookingDao(): BookingDao
}

