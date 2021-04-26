package com.anggarad.dev.academy.ui.detail

import androidx.lifecycle.ViewModel
import com.anggarad.dev.academy.data.CourseEntity
import com.anggarad.dev.academy.data.ModuleEntity
import com.anggarad.dev.academy.utils.DataDummy

class DetailCourseViewModel: ViewModel() {
    private lateinit var courseId: String

    fun setSelectedCourse(courseId: String){
        this.courseId = courseId
    }

    fun getCourse(): CourseEntity{
        lateinit var course: CourseEntity
        val courseEntities = DataDummy.generateDummyCourses()
        for(courseEntity in courseEntities){
            if(courseEntity.courseId == courseId){
                course = courseEntity
            }
        }
        return course
    }

    fun getModules(): List<ModuleEntity> = DataDummy.generateDummyModules(courseId)
}