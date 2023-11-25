package com.nbscollege.facultyevaluation.model.data

data class QuestionData(
    val id: Int,
    val question: String
)

val questionList = listOf(
    QuestionData(id = 1, "1. The faculty has mastery of the subject matter and is prepared and organized in class presentation."),
    QuestionData(id = 2, "2. The faculty has mastery of the subject matter and is prepared and organized in class presentation."),
    QuestionData(id = 3, "3. The faculty has mastery of the subject matter and is prepared and organized in class presentation."),
    QuestionData(id = 4, "4. The faculty has mastery of the subject matter and is prepared and organized in class presentation."),
    QuestionData(id = 5, "5. The faculty has mastery of the subject matter and is prepared and organized in class presentation.")
)
