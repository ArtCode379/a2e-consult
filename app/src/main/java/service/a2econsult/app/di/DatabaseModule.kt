package service.a2econsult.app.di

import androidx.room.Room
import service.a2econsult.app.data.database.Database
import org.koin.dsl.module

private const val DB_NAME = "_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = Database::class.java,
        name = DB_NAME
        ).build()
    }

    single { get<Database>().bookingDao()}

}