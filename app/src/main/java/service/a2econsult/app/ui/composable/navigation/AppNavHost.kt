package service.a2econsult.app.ui.composable.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import service.a2econsult.app.ui.composable.screen.bookings.BookingsScreen
import service.a2econsult.app.ui.composable.screen.checkout.CheckoutScreen
import service.a2econsult.app.ui.composable.screen.onboarding.OnboardingScreen
import service.a2econsult.app.ui.composable.screen.home.HomeScreen
import service.a2econsult.app.ui.composable.screen.servicedetails.ServiceDetailsScreen
import service.a2econsult.app.ui.composable.screen.settings.SettingsScreen
import service.a2econsult.app.ui.composable.screen.splash.SplashScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = NavRoute.Splash,
        modifier = modifier,
    ) {
        composable<NavRoute.Splash> {
            SplashScreen(
                onNavigateToHomeScreen = {
                    navController.navigate(route = NavRoute.Home) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onNavigateToOnboarding = {
                    navController.navigate(route = NavRoute.Onboarding) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<NavRoute.Onboarding> {
            OnboardingScreen(
                onNavigateToHomeScreen = {
                    navController.navigate(route = NavRoute.Home) {
                        popUpTo(NavRoute.Onboarding) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<NavRoute.Home> {
            HomeScreen(
                onNavigateToServiceDetails = { serviceId: Int ->
                    navController.navigate(
                        route = NavRoute.ServiceDetails(serviceId = serviceId)
                    )
                }
            )
        }

        composable<NavRoute.ServiceDetails> { backStackEntry ->
            val serviceDetails: NavRoute.ServiceDetails = backStackEntry.toRoute()
            ServiceDetailsScreen(
                serviceId = serviceDetails.serviceId,
                onNavigateToCheckout = { serviceId: Int ->
                    navController.navigate(
                        route = NavRoute.Checkout(serviceId = serviceId)
                    )
                }
            )
        }

        composable<NavRoute.Checkout> { backStackEntry ->
            val checkout: NavRoute.Checkout = backStackEntry.toRoute()
            CheckoutScreen(
                serviceId = checkout.serviceId,
                onNavigateToBookingsScreen = {
                    navController.navigate(NavRoute.Bookings) {
                        popUpTo(NavRoute.Home) { inclusive = false }
                    }
                }
            )
        }

        composable<NavRoute.Bookings> {
            BookingsScreen()
        }

        composable<NavRoute.Settings> {
            SettingsScreen()
        }
    }
}