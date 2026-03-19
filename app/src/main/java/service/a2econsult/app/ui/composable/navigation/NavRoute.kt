package service.a2econsult.app.ui.composable.navigation

import kotlinx.serialization.Serializable

sealed class NavRoute {
    @Serializable
    object Splash : NavRoute()

    @Serializable
    object Onboarding : NavRoute()

    @Serializable
    object Home : NavRoute()

    @Serializable
    object Bookings : NavRoute()

    @Serializable
    object Settings : NavRoute()

    @Serializable
    data class ServiceDetails(val serviceId: Int) : NavRoute()

    @Serializable
    data class Checkout(val serviceId: Int) : NavRoute()
}