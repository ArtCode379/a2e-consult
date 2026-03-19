package service.a2econsult.app.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import service.a2econsult.app.data.entity.BookingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookingDao {

    @Query("SELECT * FROM bookings")
    fun observeAll(): Flow<List<BookingEntity>>

    @Query("SELECT * FROM bookings WHERE serviceId = :serviceId")
    fun observeByServiceId(serviceId: Int): Flow<BookingEntity?>

    @Query("SELECT * FROM bookings WHERE serviceId = :serviceId")
    suspend fun getByServiceId(serviceId: Int): BookingEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun save(bookingEntity: BookingEntity)

    @Query("DELETE FROM bookings WHERE booking_number = :bookingNumber")
    suspend fun deleteByBookingNumber(bookingNumber: String)
}