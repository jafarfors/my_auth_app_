import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myauthapp.R
import com.example.myauthapp.presentation.LoginViewModel
import com.example.myauthapp.ui.theme.AuthBlack
import com.example.myauthapp.ui.theme.AuthGrayDark
import com.example.myauthapp.ui.theme.AuthOrange
import com.example.myauthapp.ui.theme.AuthWhite
import com.example.myauthapp.ui.theme.VkTint
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.collectAsState

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = koinViewModel()
) {
    val state = viewModel.uiState.collectAsState().value

    LaunchedEffect(state.error) {
        state.error?.let {
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AuthBlack)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Вход",
            color = AuthWhite,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Email поле
        OutlinedTextField(
            value = state.email,
            onValueChange = viewModel::onEmailChange,
            label = { Text("E-mail", fontSize = 16.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = if (state.emailError != null) Color.Red else AuthGrayDark,
                focusedBorderColor = if (state.emailError != null) Color.Red else AuthOrange,
                focusedLabelColor = if (state.emailError != null) Color.Red else AuthOrange,
                focusedContainerColor = AuthBlack,
                unfocusedContainerColor = AuthBlack,
                focusedTextColor = AuthWhite,
                unfocusedTextColor = AuthWhite,
                cursorColor = AuthOrange,
            ),
            textStyle = androidx.compose.ui.text.TextStyle(
                color = AuthWhite,
                fontSize = 18.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Medium),a
            isError = state.emailError != null,
            singleLine = true
        )

        // Ошибка email
        state.emailError?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                textAlign = TextAlign.Start
            )
        }

        // Password поле
        OutlinedTextField(
            value = state.password,
            onValueChange = viewModel::onPasswordChange,
            label = { Text("Пароль", fontSize = 16.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = if (state.passwordError != null) Color.Red else AuthGrayDark,
                focusedBorderColor = if (state.passwordError != null) Color.Red else AuthOrange,
                focusedLabelColor = if (state.passwordError != null) Color.Red else AuthOrange,
                focusedContainerColor = AuthBlack,
                unfocusedContainerColor = AuthBlack,
                focusedTextColor = AuthWhite,
                unfocusedTextColor = AuthWhite,
                cursorColor = AuthOrange
            ),
            textStyle = androidx.compose.ui.text.TextStyle(
                color = AuthWhite,
                fontSize = 18.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Medium),
            isError = state.passwordError != null,
            singleLine = true
        )

        // Ошибка password
        state.passwordError?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                textAlign = TextAlign.Start
            )
        }

        Text(
            text = "Забыли пароль?",
            color = AuthOrange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
                .clickable { },
            textAlign = TextAlign.Start,
            fontSize = 14.sp
        )

        Button(
            onClick = { viewModel.onLoginClick(onLoginSuccess) },
            enabled = state.isFormValid && !state.isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AuthOrange,
                disabledContainerColor = AuthGrayDark
            )
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = AuthWhite,
                    strokeWidth = 2.dp
                )
            } else {
                Text("Войти", fontSize = 16.sp)
            }
        }

        Text(
            text = "или",
            color = AuthWhite,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 80.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // VK
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(AuthWhite, shape = CircleShape)
                    .clickable(onClick = {}),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_vk),
                    contentDescription = "Vk",
                    tint = VkTint
                )
            }

            // Google
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(AuthWhite, shape = CircleShape)
                    .clickable(onClick = {}),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "Google"
                )
            }
        }
    }
}