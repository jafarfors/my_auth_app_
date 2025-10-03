package com.example.myauthapp.auth
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myauthapp.presentation.ProfileViewModel
import com.example.myauthapp.ui.theme.AuthBlack
import com.example.myauthapp.ui.theme.AuthWhite
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadUser()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AuthBlack)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.user != null) {
            Text(
                text = "Сохраненные данные",
                color = AuthWhite,
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            Text(
                text = "Email: ${state.user!!.email}",
                color = AuthWhite,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Пароль: ${state.user!!.password}",
                color = AuthWhite,
                fontSize = 16.sp
            )
        } else {
            Text(
                text = "Нет сохраненных данных",
                color = AuthWhite,
                fontSize = 20.sp
            )
        }
    }
}