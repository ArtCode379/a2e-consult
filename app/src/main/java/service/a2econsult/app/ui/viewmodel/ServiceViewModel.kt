package service.a2econsult.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import service.a2econsult.app.data.model.ServiceModel
import service.a2econsult.app.data.repository.ServiceRepository
import service.a2econsult.app.ui.state.DataUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ServiceViewModel(
    private val serviceRepository: ServiceRepository
) : ViewModel() {
    private val _servicesState =
        MutableStateFlow<DataUiState<List<ServiceModel>>>(DataUiState.Initial)
    val servicesState: StateFlow<DataUiState<List<ServiceModel>>>
        get() = _servicesState.asStateFlow()

    init {
        observeServices()
    }

    private fun observeServices() {
        viewModelScope.launch {
            serviceRepository.observeAll().collect { services ->
                _servicesState.update {
                    DataUiState.from(services)
                }
            }
        }
    }
}