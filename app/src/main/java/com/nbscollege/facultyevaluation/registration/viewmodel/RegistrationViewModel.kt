package com.nbscollege.facultyevaluation.registration.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.nbscollege.facultyevaluation.user.model.LoginReq
import com.nbscollege.facultyevaluation.user.repository.UserRepository

class RegistrationViewModel(private val usersRepository: UserRepository) : ViewModel() {

    /**
     * Holds current user ui state
     */
    var userUiState by mutableStateOf(UserUiState())
        private set
    /**
     * Updates the [userUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(userDetails: UserDetails) {
        userUiState =
            UserUiState(userDetails = userDetails, isEntryValid = validateInput(userDetails))
    }
    /**
     * Inserts an [User] in the Room database
     */
    suspend fun saveUser() {
        if (validateInput()) {
            usersRepository.insertUser(userUiState.userDetails.toUser())
        }
    }
    private fun validateInput(uiState: UserDetails = userUiState.userDetails): Boolean {
        return with(uiState) {
            studentNo.isNotBlank() && password.isNotBlank()
        }
    }
}
/**
 * Represents Ui State for an User.
 */
data class UserUiState(
    var userDetails: UserDetails = UserDetails(),
    val isEntryValid: Boolean = false
)
data class UserDetails(
    val id: Int = 0,
    val studentNo: String = "",
    val password: String = "",
)
/**
 * Extension function to convert [UserUiState] to [User]. If the value of [UserDetails.price] is
 * not a valid [Double], then the price will be set to 0.0. Similarly if the value of
 * [UserUiState] is not a valid [Int], then the quantity will be set to 0
 */
fun UserDetails.toUser(): LoginReq = LoginReq(
    id = id,
    studentNo = studentNo,
    password = password
)
/**
 * Extension function to convert [Item] to [ItemUiState]
 */
fun LoginReq.toUserUiState(isEntryValid: Boolean = false): UserUiState = UserUiState(
    userDetails = this.toUserDetails(),
    isEntryValid = isEntryValid
)
/**
 * Extension function to convert [Item] to [ItemDetails]
 */
fun LoginReq.toUserDetails(): UserDetails = UserDetails(
    id = id,
    studentNo = studentNo,
    password  = password
)
