package com.anggarad.dev.academy.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.anggarad.dev.academy.ui.reader.CourseReaderActivity
import com.anggarad.dev.academy.R
import com.anggarad.dev.academy.data.CourseEntity
import com.anggarad.dev.academy.databinding.ActivityDetailCourseBinding
import com.anggarad.dev.academy.databinding.ContentDetailCourseBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class DetailCourseActivity : AppCompatActivity() {

    private lateinit var detailContentBinding: ContentDetailCourseBinding

    companion object {
        const val EXTRA_COURSE = "extra_course"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailCourseBinding = ActivityDetailCourseBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailCourseBinding.detailContent

        setContentView(activityDetailCourseBinding.root)
        setSupportActionBar(activityDetailCourseBinding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = DetailCourseAdapter()

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailCourseViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val courseId = extras.getString(EXTRA_COURSE)
            if (courseId != null) {
                viewModel.setSelectedCourse(courseId)
                val modules = viewModel.getModules()
                adapter.setModules(modules)
                populateCourse(viewModel.getCourse() as CourseEntity)
            }
        }

        with(detailContentBinding.rvModule) {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@DetailCourseActivity)
            setHasFixedSize(true)
            this.adapter = adapter
            val dividerItemDecoration =
                DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun populateCourse(courseEntity: CourseEntity) {
        with(detailContentBinding) {
            textTitle.text = courseEntity.title
            textDescription.text = courseEntity.description
            textDate.text = resources.getString(R.string.deadline_date)
        }

        Glide.with(this)
            .load(courseEntity.imagePath)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(detailContentBinding.imagePoster)

        detailContentBinding.btnStart.setOnClickListener {
            val intent = Intent(this@DetailCourseActivity, CourseReaderActivity::class.java)
            intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, courseEntity.courseId)
            startActivity(intent)
        }
    }
}