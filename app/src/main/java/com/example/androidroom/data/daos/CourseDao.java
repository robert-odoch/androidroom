package com.example.androidroom.data.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.androidroom.data.entities.Course;
import java.util.List;

@Dao
public interface CourseDao {
    @Query("SELECT * FROM course")
    LiveData<List<Course>> getAll();

    @Insert
    void insertAll(Course... courses);

    @Query("DELETE FROM course")
    void deleteAll();
}
