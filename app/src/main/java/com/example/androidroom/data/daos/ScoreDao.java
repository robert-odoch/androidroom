package com.example.androidroom.data.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.androidroom.data.entities.Score;

import java.util.List;

@Dao
public interface ScoreDao {
    @Query("SELECT * FROM score where courseId = :courseId")
    LiveData<List<Score>> getAll(int courseId);

    @Insert
    void insertAll(Score... scores);

    @Query("DELETE FROM score")
    void deleteAll();
}
