package com.anggarad.dev.academy.ui.bookmark

import androidx.lifecycle.ViewModel
import com.anggarad.dev.academy.data.CourseEntity
import com.anggarad.dev.academy.utils.DataDummy

class BookmarkViewModel: ViewModel() {
    fun getBookmarks(): List<CourseEntity> = DataDummy.generateDummyCourses()
}