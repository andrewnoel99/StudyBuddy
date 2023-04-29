 package com.example.studybuddy20

import android.os.Parcel
import android.os.Parcelable

data class Technique(val title: String, val steps: List<String>) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.createStringArrayList()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeStringList(steps)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Technique> {
        override fun createFromParcel(parcel: Parcel): Technique {
            return Technique(parcel)
        }

        override fun newArray(size: Int): Array<Technique?> {
            return arrayOfNulls(size)
        }
    }
}


object Techniques {
    val myTechniques = mapOf(
        "SQ3R" to listOf(
            "Survey \n\n Skimming the chapter and taking notes" to 1,
            "Break" to 1,
            "Question \n\n Formulate questions around the chapter's content" to 1,
            "Break" to 1,
            "Read \n\n Read full chapter and look for answers to the questions you made" to 1,
            "Break" to 1,
            "Recite \n\n Summarize what you just read, recall and identify major points" to 1,
            "Break" to 1,
            "Review\n\n Review material, quiz yourself" to 1
        ),
        "Pomodoro" to listOf(
            "Work \n\n Focus on a task" to 25,
            "Break" to 5,
            "Work \n" +
                    "\n" +
                    " Focus on a task" to 25,
            "Break" to 5,
            "Work \n" +
                    "\n" +
                    " Focus on a task" to 25,
            "Break" to 5,
            "Work \n" +
                    "\n" +
                    " Focus on a task" to 25,
            "Break" to 5
        ),
        "Feynman" to listOf(
            "Learn" to 30,
            "Break" to 5,
            "Explain" to 30,
            "Break" to 5,
            "Review" to 30
        )
    )
}

