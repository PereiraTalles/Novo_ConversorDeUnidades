package com.example.novo_conversor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.novo_conversor.ui.theme.CinzaEscuro
import com.example.novo_conversor.ui.theme.Creme
import com.example.novo_conversor.ui.theme.Laranja

@Composable
fun Conversor_tela(navController: NavController) {
    var unidadeEntrada by remember { mutableStateOf("Centímetros") }
    var unidadeSaida by remember { mutableStateOf("Metros") }
    val opcoes = listOf("Centímetros", "Metros", "Kilômetros", "Milhas")
    var valorEntrada by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CinzaEscuro)
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text("Conversor de Unidades",
                fontSize = 35.sp,
                color = Laranja,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            DropdownMenuBox("De", unidadeEntrada, opcoes) {
                unidadeEntrada = it
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = valorEntrada,
                onValueChange = { valorEntrada = it },
                label = { Text("Digite o valor", fontSize = 25.sp) },
                modifier = Modifier.fillMaxWidth(),
                textStyle = LocalTextStyle.current.copy(fontSize = 25.sp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            DropdownMenuBox("Para", unidadeSaida, opcoes) {
                unidadeSaida = it
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = {
                val valor = valorEntrada.toDoubleOrNull()
                if (valor != null) {
                    val emMetros = when (unidadeEntrada) {
                        "Centímetros" -> valor / 100
                        "Metros" -> valor
                        "Kilômetros" -> valor * 1000
                        "Milhas" -> valor * 1609.34
                        else -> 0.0
                    }

                    val convertido = when (unidadeSaida) {
                        "Centímetros" -> emMetros * 100
                        "Metros" -> emMetros
                        "Kilômetros" -> emMetros / 1000
                        "Milhas" -> emMetros / 1609.34
                        else -> 0.0
                    }

                    resultado = if (convertido % 1.0 == 0.0) {
                        "%.0f %s".format(convertido, unidadeSaida)
                    } else {
                        "%.4f %s".format(convertido, unidadeSaida)
                    }
                } else {
                    resultado = "Valor inválido"
                }
            }) {
                Text("Converter", fontSize = 25.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Resultado:", fontSize = 25.sp, color = Creme)
            Text(" $resultado", fontSize = 35.sp, color = Creme)

            Spacer(modifier = Modifier.weight(1f))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = {
                    valorEntrada = ""
                    resultado = ""
                }) {
                    Text("Nova Conversão", fontSize = 25.sp, color = Creme)
                }
                Button(onClick = { navController.navigate("inicio") }) {
                    Text("Voltar", fontSize = 25.sp, color = Creme)
                }
            }
        }
    }
}

@Composable
fun DropdownMenuBox(
    label: String,
    selectedOption: String,
    options: List<String>,
    fontSize: androidx.compose.ui.unit.TextUnit = 25.sp,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label, fontSize = fontSize, color = CinzaEscuro)
        Box {
            Button(onClick = { expanded = true }) {
                Text(selectedOption, fontSize = fontSize)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option, fontSize = fontSize) },
                        onClick = {
                            onOptionSelected(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
