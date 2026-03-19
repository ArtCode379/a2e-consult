package service.a2econsult.app.di

import service.a2econsult.app.ui.viewmodel.BookingViewModel
import service.a2econsult.app.ui.viewmodel.CheckoutViewModel
import service.a2econsult.app.ui.viewmodel.OnboardingViewModel
import service.a2econsult.app.ui.viewmodel.ServiceDetailsViewModel
import service.a2econsult.app.ui.viewmodel.ServiceViewModel
import service.a2econsult.app.ui.viewmodel.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        SplashViewModel(
            onboardingRepository = get()
        )
    }

    viewModel {
        OnboardingViewModel(
            onboardingRepository = get()
        )
    }

    viewModel {
        ServiceViewModel(
            serviceRepository = get()
        )
    }

    viewModel {
        ServiceDetailsViewModel(
            serviceRepository = get()
        )
    }

    viewModel {
        BookingViewModel(
            bookingRepository = get(),
            serviceRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            bookingRepository = get(),
        )
    }
}