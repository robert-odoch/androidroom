package com.example.androidroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.androidroom.data.entities.Score;
import com.example.androidroom.ui.score.ScoreViewModel;
import com.example.androidroom.ui.score.ScoreListAdapter;

import java.util.List;

public class CourseStatistics extends AppCompatActivity {

    private String courseName;
    private ScoreViewModel mScoreViewModel;
    private TextView tvCourseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_statistics);

        // Change the text view name to that of the selected course
        tvCourseName = findViewById(R.id.course_title);
        courseName = getIntent().getStringExtra("courseName");
        tvCourseName.setText(courseName);

        // Add recycler view
        RecyclerView recyclerView = findViewById(R.id.scores_recyclerview);
        final ScoreListAdapter adapter = new ScoreListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create and store the scores
        int courseId = getIntent().getIntExtra("courseId", 0);
        mScoreViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ScoreViewModel.class);
        mScoreViewModel.getScores(courseId).observe(this, new Observer<List<Score>>() {
            @Override
            public void onChanged(List<Score> scores) {
                adapter.setScores(scores);
                updateStatistics(scores);
            }
        });
    }

    private void updateStatistics(List<Score> scores) {
        int[] max = {0, 0, 0, 0, 0};
        int[] min = {101, 101, 101, 101, 101};
        int[] sum = {0, 0, 0, 0, 0};
        int[] avg = {0, 0, 0, 0, 0};
        for (Score score: scores) {
            // Determine maximums
            if (score.getQ1() > max[0])
                max[0] = score.getQ1();
            if (score.getQ2() > max[1])
                max[1] = score.getQ2();
            if (score.getQ3() > max[2])
                max[2] = score.getQ3();
            if (score.getQ4() > max[3])
                max[3] = score.getQ4();
            if (score.getQ5() > max[4])
                max[4] = score.getQ5();

            // Determine minimums
            if (score.getQ1() < min[0])
                min[0] = score.getQ1();
            if (score.getQ2() < min[1])
                min[1] = score.getQ2();
            if (score.getQ3() < min[2])
                max[2] = score.getQ3();
            if (score.getQ4() < min[3])
                min[3] = score.getQ4();
            if (score.getQ5() < min[4])
                min[4] = score.getQ5();

            // Determine the average
            sum[0] += score.getQ1();
            sum[1] += score.getQ2();
            sum[2] += score.getQ3();
            sum[3] += score.getQ4();
            sum[4] += score.getQ5();
        }

        for (int i = 0; i < sum.length; ++i) {
            avg[i] = sum[i] / 3;
        }

        // Set the maximums
        ((TextView) findViewById(R.id.quiz_one_high)).setText(max[0] + "");
        ((TextView) findViewById(R.id.quiz_two_high)).setText(max[1] + "");
        ((TextView) findViewById(R.id.quiz_three_high)).setText(max[2] + "");
        ((TextView) findViewById(R.id.quiz_four_high)).setText(max[3] + "");
        ((TextView) findViewById(R.id.quiz_five_high)).setText(max[4] + "");

        // Set the minimums
        ((TextView) findViewById(R.id.quiz_one_low)).setText(min[0] + "");
        ((TextView) findViewById(R.id.quiz_two_low)).setText(min[1] + "");
        ((TextView) findViewById(R.id.quiz_three_low)).setText(min[2] + "");
        ((TextView) findViewById(R.id.quiz_four_low)).setText(min[3] + "");
        ((TextView) findViewById(R.id.quiz_five_low)).setText(min[4] + "");

        // Set the averages
        ((TextView) findViewById(R.id.quiz_one_avg)).setText(avg[0] + "");
        ((TextView) findViewById(R.id.quiz_two_avg)).setText(avg[1] + "");
        ((TextView) findViewById(R.id.quiz_three_avg)).setText(avg[2] + "");
        ((TextView) findViewById(R.id.quiz_four_avg)).setText(avg[3] + "");
        ((TextView) findViewById(R.id.quiz_five_avg)).setText(avg[4] + "");
    }
}