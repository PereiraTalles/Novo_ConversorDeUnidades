package com.example.novo_conversor

import com.example.novo_conversor.ui.theme.MeuTema
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.novo_conversor.ui.theme.CinzaClaro
import com.example.novo_conversor.ui.theme.CinzaEscuro
import kotlin.system.exitProcess
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.novo_conversor.ui.theme.Creme
import com.example.novo_conversor.ui.theme.Laranja

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeuTema {
                AppNavigator()
            }
        }
    }
}
@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "inicio") {
        composable("inicio") { TelaInicial(navController) }
        composable("ConversorTela") { Conversor_tela(navController) }
    }

}

@Composable
fun TelaInicial(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize().background(CinzaEscuro),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "CONVERSOR", fontSize = 40.sp, color = Laranja)
            Text(text = "DE", fontSize = 40.sp, color = Laranja)
            Text(text = "UNIDADES", fontSize = 40.sp, color = Laranja)
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = { navController.navigate("ConversorTela") }) {
                Text(text = "Iniciar", color = CinzaClaro, fontSize = 25.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { exitProcess(0) }) {
                Text(text = "Sair", color = CinzaClaro, fontSize = 25.sp)
            }
        }
        Text(text = "Talles de Lima Pereira - 2326201",
            fontSize = 15.sp,

            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 24.dp, end = 8.dp),
            color = Creme)
    }
}

