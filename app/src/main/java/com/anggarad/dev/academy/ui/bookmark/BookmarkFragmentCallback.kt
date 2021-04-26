package com.anggarad.dev.academy.ui.bookmark

import com.anggarad.dev.academy.data.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}
