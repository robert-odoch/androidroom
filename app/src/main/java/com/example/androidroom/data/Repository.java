package com.example.androidroom.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.androidroom.data.daos.CourseDao;
import com.example.androidroom.data.daos.ScoreDao;
import com.example.androidroom.data.entities.Course;
import com.example.androidroom.data.entities.Score;

import java.util.List;
public class Repository {
    private ScoreDao mScoreDao;
    private CourseDao mCourseDao;
    private LiveData<List<Score>> mScores;
    private LiveData<List<Course>> mCourses;

    public Repository(Application application) {
        Database db = Database.getInstance(application);
        mScoreDao = db.scoreDao();

        mCourseDao = db.courseDao();
        mCourses = mCourseDao.getAll();
    }

    public LiveData<List<Score>> getScores(int courseId) {
        mScores = mScoreDao.getAll(courseId);
        return mScores;
    }

    public LiveData<List<Course>> getCourses() {
        return mCourses;
    }

    public void insertAll(Score... scores) {
        new InsertAsyncTask(mScoreDao).execute(scores);
    }

    private static class InsertAsyncTask extends AsyncTask<Score, Void, Void> {
        private ScoreDao mAsyncDao;

        InsertAsyncTask(ScoreDao dao) {
            mAsyncDao = dao;
        }

        @Override
        protected Void doInBackground(Score... scores) {
            mAsyncDao.insertAll(scores);
            return null;
        }
    }
}
