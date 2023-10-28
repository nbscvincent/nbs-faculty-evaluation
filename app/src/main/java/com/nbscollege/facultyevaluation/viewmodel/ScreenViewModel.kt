import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nbscollege.facultyevaluation.model.Dashboard
import com.nbscollege.facultyevaluation.model.Login
import com.nbscollege.facultyevaluation.navigation.routes.MainScreen
import com.nbscollege.facultyevaluation.navigation.state.ScreenUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class ScreenViewModel : ViewModel() {
    private val _loading = MutableStateFlow(true)

    val loading = _loading.asStateFlow()

    private val _uiState = MutableStateFlow(ScreenUiState())

    fun runSplashScreen() {
        viewModelScope.launch {
            // run background task here
            delay(2000)
            _loading.value = false

        }
    }

     fun isUserSignedIn(): Boolean {
// User Authentication
        return true
    }
}