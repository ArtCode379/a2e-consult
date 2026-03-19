package service.a2econsult.app.ui.composable.approot

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import service.a2econsult.app.R
import service.a2econsult.app.ui.composable.navigation.AppNavHost
import service.a2econsult.app.ui.composable.navigation.NavRoute
import kotlin.reflect.KClass

private val navigationItems: List<BottomNavItem> = listOf(
    BottomNavItem(
        titleRes = R.string.bottom_bar_nav_item_home_title,
        iconRes = R.drawable.home,
        route = NavRoute.Home
    ),
    BottomNavItem(
        titleRes = R.string.bottom_bar_nav_item_bookings_title,
        iconRes = R.drawable.book_open,
        route = NavRoute.Bookings
    ),
    BottomNavItem(
        titleRes = R.string.bottom_bar_nav_item_settings_title,
        iconRes = R.drawable.cog,
        route = NavRoute.Settings
    ),
)

private val topBarHiddenScreens: List<KClass<out NavRoute>> = listOf(
    NavRoute.Splash::class,
    NavRoute.Onboarding::class,
)

private val bottomBarHiddenScreens: List<KClass<out NavRoute>> = listOf(
    NavRoute.Splash::class,
    NavRoute.Onboarding::class,
    NavRoute.ServiceDetails::class,
    NavRoute.Checkout::class,
)

@Composable
fun AppRoot() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination

    val shouldShowBottomBar = !currentDestination.matchesAnyRoute(bottomBarHiddenScreens)
    val shouldShowTopBar = !currentDestination.matchesAnyRoute(topBarHiddenScreens)

    val onNavigateToRoute = { item: BottomNavItem ->
        navController.navigate(item.route) {
            popUpTo(NavRoute.Home) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    AppRootContent(
        navController = navController,
        currentDestination = currentDestination,
        shouldShowTopBar = shouldShowTopBar,
        shouldShowBottomBar = shouldShowBottomBar,
        onNavigateToRoute = onNavigateToRoute,
        onNavigateBack = { navController.popBackStack() }
    )
}

@Composable
private fun AppRootContent(
    navController: NavHostController,
    currentDestination: NavDestination?,
    shouldShowTopBar: Boolean,
    shouldShowBottomBar: Boolean,
    onNavigateToRoute: (BottomNavItem) -> Unit,
    onNavigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            if (shouldShowTopBar) {
                AppTopBar(
                    currentDestination = currentDestination,
                    onNavigateBack = onNavigateBack,
                )
            }
        },

        bottomBar = {
            if (shouldShowBottomBar) {
                AppBottomBar(
                    currentDestination = currentDestination,
                    navigationItems = navigationItems,
                    onNavigateToRoute = onNavigateToRoute,
                )
            }
        },

        containerColor = MaterialTheme.colorScheme.background,
    ) { paddingValues ->
        AppNavHost(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        )
    }
}

fun NavDestination?.matchesAnyRoute(routes: List<KClass<out NavRoute>>): Boolean {
    return this?.let { destination ->
        routes.any { route -> destination.hasRoute(route) }
    } == true
}