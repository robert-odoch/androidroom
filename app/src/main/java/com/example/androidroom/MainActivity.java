package com.example.androidroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.androidroom.data.entities.Course;
import com.example.androidroom.ui.course.CourseViewModel;
import com.example.androidroom.ui.course.CourseListAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private CourseViewModel mCourseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add recycler view
        RecyclerView recyclerView = findViewById(R.id.courses_recyclerview);
        final CourseListAdapter adapter = new CourseListAdapter(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Display the courses
        mCourseViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(CourseViewModel.class);
        mCourseViewModel.getCourses().observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                adapter.setCourses(courses);
            }
        });
    }
}