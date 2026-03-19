package service.a2econsult.app

import android.app.Application
import service.a2econsult.app.di.dataModule
import service.a2econsult.app.di.dispatcherModule
import service.a2econsult.app.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class AECNLTApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val appModules = dataModule + viewModule + dispatcherModule

        startKoin {
            androidLogger()
            androidContext(this@AECNLTApp)
            modules(appModules)
        }
    }
}
