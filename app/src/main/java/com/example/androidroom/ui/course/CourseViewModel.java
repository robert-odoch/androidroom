package com.example.androidroom.ui.course;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.androidroom.data.Repository;
import com.example.androidroom.data.entities.Course;

import java.util.List;

public class CourseViewModel extends AndroidViewModel {

    private Repository mRepository;
    private final LiveData<List<Course>> mCourses;


    public CourseViewModel(Application application){
        super(application);

        mRepository = new Repository(application);
        mCourses = mRepository.getCourses();
    }

    public LiveData<List<Course>> getCourses() {
        return mCourses;
    }
}
