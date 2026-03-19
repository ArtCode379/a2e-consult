package service.a2econsult.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import service.a2econsult.app.data.repository.BookingRepository
import service.a2econsult.app.data.repository.ServiceRepository
import service.a2econsult.app.ui.state.BookingUiState
import service.a2econsult.app.ui.state.DataUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter

class BookingViewModel(
    private val bookingRepository: BookingRepository,
    private val serviceRepository: ServiceRepository,
) : ViewModel() {
    private val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")

    private val _bookingsState =
        MutableStateFlow<DataUiState<List<BookingUiState>>>(DataUiState.Initial)
    val bookingsState: StateFlow<DataUiState<List<BookingUiState>>>
        get() = _bookingsState.asStateFlow()

    init {
        observeBookings()
    }

    private fun observeBookings() {
        viewModelScope.launch {
            bookingRepository.observeAll().collect { bookings ->
                val mapped = bookings.mapNotNull { booking ->
                    serviceRepository.getById(booking.serviceId)?.let { service ->
                        BookingUiState(
                            serviceName = service.name,
                            bookingNumber = booking.bookingNumber,
                            customerFirstName = booking.customerFirstName,
                            customerLastName = booking.customerLastName,
                            timestamp = booking.timestamp.format(formatter)
                        )
                    }
                }

                _bookingsState.update {
                    DataUiState.from(mapped)
                }
            }
        }
    }

    fun cancelBooking(bookingNumber: String){
        viewModelScope.launch {
            bookingRepository.deleteByBookingNumber(bookingNumber)
        }
    }
}