package com.example.androidroom.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.androidroom.data.daos.CourseDao;
import com.example.androidroom.data.daos.ScoreDao;
import com.example.androidroom.data.entities.Course;
import com.example.androidroom.data.entities.Score;

import java.util.LinkedHashMap;
import java.util.Random;

@androidx.room.Database(entities = {Score.class, Course.class}, version = 1)
public abstract class Database extends RoomDatabase {
    private static volatile Database INSTANCE;

    public abstract ScoreDao scoreDao();
    public abstract CourseDao courseDao();

    static Database getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Database.class, "score_database")
                                    .addCallback(sRoomDatabaseCallback)
                                    .build();
                }
            }
        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateDatabaseAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate the database in the background
     */
    private static class PopulateDatabaseAsync extends AsyncTask<Void, Void, Void> {

        private final ScoreDao mScoreDao;
        private final CourseDao mCourseDao;

        Random random = new Random();

        Course[] courses = {
                new Course(117, "Design Patterns for SmartPhone Development"),
                new Course(223, "Applied Computer Vision"),
                new Course(245, "Energy Project Development")
        };

        int[] students = { 1234, 2134, 1852 };

        LinkedHashMap<Integer, Score[]> courseScores = new LinkedHashMap<>();

        PopulateDatabaseAsync(Database db) {
            mScoreDao = db.scoreDao();
            mCourseDao = db.courseDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            for(Course course : courses){

                Score[] marks = new Score[3];

                int i = 0;
                for(int studentId : students) {
                    Score score = new Score(studentId);
                    score.setCourseId(course.getCourseId());
                    score.setQ1(random.nextInt(101));
                    score.setQ2(random.nextInt(101));
                    score.setQ3(random.nextInt(101));
                    score.setQ4(random.nextInt(101));
                    score.setQ5(random.nextInt(101));

                    marks[i++] = score;
                }
                courseScores.put(course.getCourseId(), marks);
            }

            mCourseDao.insertAll(courses);

            for(Course course : courses){
                mScoreDao.insertAll(courseScores.get(course.getCourseId()));
            }

            return null;
        }
    }
}       
