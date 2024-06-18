package com.example.newfacultyevaluation.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Question(
    val id: Int,
    val question: String
)

//val questions = listOf(
//    Question(1,
//        "The faculty has mastery of the subject matter and is prepared and organized in class presentation.") ,
//    Question(2,
//        "The faculty fosters critical thinking by asking open-ended and probing questions; responds to questions in a constructive manner."),
//    Question(3,
//        "Explains difficult terms, concepts or problems in more than one way; gives real life examples/ exercises"),
//    Question(4,
//        "Uses standard English as a medium of instruction"),
//    Question(5,
//        "The online class delivery is sufficiently effective."),
//
//    Question(6,
//        "The faculty starts and ends class on time, optimizes the use of time, and covers the topics as scheduled") ,
//    Question(7,
//        "The faculty responds in a timely manner to give individual help regarding the course content."),
//    Question(8,
//        "Engages students in class discussions and activities and encourages students to interact with one another and with the faculty"),
//    Question(9,
//        "The faculty is reasonable in understanding the students’ online limitations."),
//
//    Question(10,
//        "The materials used in class are useful and relevant to the course.") ,
//    Question(11,
//        "Consistently provides and uploads in Google classroom relevant materials, Powerpoint presentations used, and class recordings of synchronous sessions"),
//    Question(12,
//        "Cites and provides references, and other useful online materials that may be accessed by students."),
//
//    Question(13,
//        "The faculty returns assignments, exercises, and exam results in a timely manner to provide appropriate feedback about my performance throughout the course.") ,
//    Question(14,
//        "The faculty clearly explains the grading criteria at the beginning of the course."),
//    Question(15,
//        "The faculty follows the announced grading criteria."),
//
//    Question(16,
//        "The faculty integrates NBS College core values and other universal values to the course content as deemed relevant; and enhances the image and reputation of NBS College"),
//
//    Question(17,
//        "List any issues you perceived with the course and/ or the faculty."),
//    Question(18,
//        "You are free to suggest here, if you wish, anything to improve on the class or the subject or the faculty member? Thank you."),
//
//)