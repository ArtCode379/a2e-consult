package service.a2econsult.app.ui.viewmodel

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import service.a2econsult.app.data.entity.BookingEntity
import service.a2econsult.app.data.repository.BookingRepository
import service.a2econsult.app.ui.state.DataUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class CheckoutViewModel(
    private val bookingRepository: BookingRepository,
) : ViewModel() {
    private val _bookingState = MutableStateFlow<DataUiState<BookingEntity>>(DataUiState.Empty)
    val orderState: StateFlow<DataUiState<BookingEntity>>
        get() = _bookingState.asStateFlow()

    private val _emailInvalidState = MutableStateFlow(false)
    val emailInvalidState: StateFlow<Boolean>
        get() = _emailInvalidState.asStateFlow()

    var customerFirstName by mutableStateOf("")
        private set

    var customerLastName by mutableStateOf("")
        private set

    var customerEmail by mutableStateOf("")
        private set

    fun updateCustomerFirstName(input: String) {
        customerFirstName = input
    }

    fun updateCustomerLastName(input: String) {
        customerLastName = input
    }

    fun updateCustomerEmail(input: String) {
        customerEmail = input
        if (_emailInvalidState.value) {
            _emailInvalidState.value = false
        }
    }

    fun placeBooking(serviceId: Int) {
        viewModelScope.launch {
            if (isEmailValid()) {
                _emailInvalidState.update { false }
                val booking = BookingEntity(
                    serviceId = serviceId,
                    bookingNumber = generateBookingsNumber(),
                    customerFirstName = customerFirstName,
                    customerLastName = customerLastName,
                    customerEmail = customerEmail,
                    timestamp = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),
                )

                bookingRepository.save(booking)
                _bookingState.update { DataUiState.Populated(data = booking) }
            } else {
                _emailInvalidState.update { true }
            }
        }
    }

    private fun isEmailValid(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(customerEmail).matches()
    }

    private fun generateBookingsNumber(): String {
        val chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
        return (1..8)
            .map { chars.random() }
            .joinToString("")
    }
}