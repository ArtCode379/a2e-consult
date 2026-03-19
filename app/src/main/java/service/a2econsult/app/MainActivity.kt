package service.a2econsult.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import service.a2econsult.app.ui.composable.approot.AppRoot
import service.a2econsult.app.ui.theme.ServiceSkeletonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ServiceSkeletonTheme {
                AppRoot()
            }
        }
    }
}