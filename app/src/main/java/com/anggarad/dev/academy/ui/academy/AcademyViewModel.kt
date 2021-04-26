package com.anggarad.dev.academy.ui.academy

import androidx.lifecycle.ViewModel
import com.anggarad.dev.academy.data.CourseEntity
import com.anggarad.dev.academy.utils.DataDummy

class AcademyViewModel : ViewModel() {

    fun getCourses(): List<CourseEntity> = DataDummy.generateDummyCourses()
}