package com.ucne.prioridadesapp.ui.registroPrioridad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.prioridadesapp.data.entity.PrioridadEntity
import com.ucne.prioridadesapp.data.repository.PrioridadRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrioridadViewModel @Inject constructor(
    private val prioridadRepository: PrioridadRepository
) : ViewModel() {
    private val _state = MutableStateFlow(PrioridadState())
    val state = _state.asStateFlow()
    val prioridades: Flow<List<PrioridadEntity>> = prioridadRepository.getPrioridad()

    fun onEvent(event: PrioridadEvent) {
        when (event) {
            is PrioridadEvent.IdPrioridad -> {
                _state.update {
                    it.copy(
                        prioridades = it.prioridades.copy(
                            idPrioridad = event.idPrioridad.toIntOrNull() ?: 0
                        )
                    )
                }
            }

            is PrioridadEvent.Nombre -> {
                _state.update {
                    it.copy(
                        prioridades = it.prioridades.copy(nombre = event.nombre)
                    )
                }
            }

            is PrioridadEvent.Descripcion -> {
                _state.update {
                    it.copy(
                        prioridades = it.prioridades.copy(descripcion = event.descripcion)
                    )
                }
            }

            is PrioridadEvent.Plazo -> {
                _state.update {
                    it.copy(
                        prioridades = it.prioridades.copy(plazo = event.plazo.toIntOrNull() ?: 0)
                    )
                }
            }


            PrioridadEvent.onSave -> {
                val nombre = state.value.prioridades.nombre
                val descripcion = state.value.prioridades.descripcion
                val plazo = state.value.prioridades.plazo

                if (nombre.isBlank() || descripcion.isBlank() || plazo == 0) {
                    _state.update {
                        it.copy(
                            error = "Por favor, complete todos los campos."
                        )
                    }
                    return
                }

                val prioridad = PrioridadEntity(
                    nombre = nombre,
                    descripcion = descripcion,
                    plazo = plazo
                )

                _state.update {
                    it.copy(
                        isLoading = true,
                        error = null,
                        succesMessage = null
                    )
                }

                viewModelScope.launch {
                    try {
                        prioridadRepository.upsert(prioridad)
                        _state.update {
                            it.copy(
                                isLoading = false,
                                succesMessage = "Se guardó correctamente"
                            )
                        }
                    } catch (e: Exception) {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = "Error al guardar: ${e.message}"
                            )
                        }
                    }
                }

                _state.update {
                    it.copy(
                        prioridades = PrioridadEntity()
                    )
                }
            }

            PrioridadEvent.onNew -> {
                _state.update {
                    it.copy(
                        succesMessage = null,
                        error = null,
                        prioridades = PrioridadEntity()
                    )
                }
            }

            is PrioridadEvent.onDelete -> {
                viewModelScope.launch {
                    prioridadRepository.delete(event.prioridad)
                }
            }
        }
    }
}

data class PrioridadState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val prioridades: PrioridadEntity = PrioridadEntity(),
    val succesMessage: String? = null,
)

sealed interface PrioridadEvent {
    data class IdPrioridad(val idPrioridad: String) : PrioridadEvent
    data class Nombre(val nombre: String) : PrioridadEvent
    data class Descripcion(val descripcion: String) : PrioridadEvent
    data class Plazo(val plazo: String) : PrioridadEvent

    data class onDelete(val prioridad: PrioridadEntity) : PrioridadEvent

    object onSave : PrioridadEvent
    object onNew : PrioridadEvent
}