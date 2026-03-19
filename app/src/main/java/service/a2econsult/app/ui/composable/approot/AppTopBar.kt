package service.a2econsult.app.ui.composable.approot

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import service.a2econsult.app.R
import service.a2econsult.app.ui.composable.navigation.NavRoute
import kotlin.reflect.KClass

private val canNavigateBackRoutes: List<KClass<out NavRoute>> = listOf(
    NavRoute.ServiceDetails::class,
    NavRoute.Checkout::class,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    currentDestination: NavDestination?,
    onNavigateBack: () -> Unit,
) {
    val canNavigateBack = currentDestination.matchesAnyRoute(canNavigateBackRoutes)

    TopAppBar(
        title = {
            Text(
                text = getTitle(currentDestination)?.let { stringResource(it) }.orEmpty()
            )
        },

        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Navigate Back",
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
    )
}

private fun getTitle(currentDestination: NavDestination?): Int? {
    return when {
        currentDestination == null -> null

        currentDestination.hierarchy.any { it.hasRoute(NavRoute.Home::class) } -> {
            R.string.top_bar_home_title
        }

        currentDestination.hierarchy.any { it.hasRoute(NavRoute.ServiceDetails::class) } -> {
            R.string.top_bar_service_details_title
        }

        currentDestination.hierarchy.any { it.hasRoute(NavRoute.Checkout::class) } -> {
            R.string.top_bar_checkout_title
        }

        currentDestination.hierarchy.any { it.hasRoute(NavRoute.Bookings::class) } -> {
            R.string.top_bar_bookings_title
        }

        currentDestination.hierarchy.any { it.hasRoute(NavRoute.Settings::class) } -> {
            R.string.top_bar_settings_title
        }

        else -> null
    }
}