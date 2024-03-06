package com.ucne.prioridadesapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ucne.prioridadesapp.ui.consultaPrioridad.ConsultaPrioridadScreen
import com.ucne.prioridadesapp.ui.home.HomeScreen
import com.ucne.prioridadesapp.ui.registroPrioridad.RegistroPrioridadScreen
import com.ucne.prioridadesapp.ui.theme.PrioridadesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrioridadesAppTheme(darkTheme = true) {
                val navController = rememberNavController()

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "PrioridadApp")
                            },
                            actions = {
                                IconButton(
                                    onClick = {
                                        navController.navigate("registro")
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = "Agregar Prioridad"
                                    )
                                }
                                IconButton(
                                    onClick = {
                                        navController.navigate("Consulta")
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.List,
                                        contentDescription = "Consulta Prioridades"
                                    )
                                }
                            }
                        )
                    }
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.fillMaxSize()
                    ) {
                        composable("home") {
                            HomeScreen()
                        }
                        composable("registro") {
                            RegistroPrioridadScreen()
                        }
                        composable("Consulta") {
                            ConsultaPrioridadScreen()
                        }
                    }
                }
            }
        }
    }
}

