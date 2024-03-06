package com.ucne.prioridadesapp.ui.consultaPrioridad

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucne.prioridadesapp.data.entity.PrioridadEntity
import com.ucne.prioridadesapp.ui.registroPrioridad.PrioridadEvent
import com.ucne.prioridadesapp.ui.registroPrioridad.PrioridadViewModel

@Composable
fun ConsultaPrioridadScreen(
    viewModel: PrioridadViewModel = hiltViewModel()
) {
    val prioridades by viewModel.prioridades.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Text(text = "Consulta de Prioridades",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        LazyColumn{
            items(prioridades) { prioridad ->
                Card(prioridad = prioridad, onDeleteClick = {
                    viewModel.onEvent(PrioridadEvent.onDelete(prioridad))
                })
            }
        }
    }
}

@Composable
fun Card(
    prioridad: PrioridadEntity,
    onDeleteClick: () -> Unit

) {
    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column{
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("ID: ")
                            }
                            withStyle(style = SpanStyle()) {
                                append("${prioridad.idPrioridad}")
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Nombre: ")
                            }
                            withStyle(style = SpanStyle()) {
                                append("${prioridad.nombre}")
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Descripcion: ")
                            }
                            withStyle(style = SpanStyle()) {
                                append("${prioridad.descripcion}")
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Plazo: ")
                            }
                            withStyle(style = SpanStyle()) {
                                append("${prioridad.plazo}")
                            }
                        }
                    )
                }
                IconButton(
                    onClick = {
                        showDialog = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Eliminar"
                    )
                }
                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = {
                            showDialog = false
                        },
                        icon = { Icon(Icons.Default.Warning, contentDescription = null) },
                        text = {
                            Text("¿Estás seguro de que quieres eliminar esta prioridad?")
                        },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    onDeleteClick()
                                    showDialog = false
                                }
                            ) {
                                Text("Eliminar")
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = {
                                    showDialog = false
                                }
                            ) {
                                Text("Cancelar")
                            }
                        }
                    )
                }
            }
        }
    }
}


