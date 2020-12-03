package com.example.androidroom.ui.score;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidroom.data.Repository;
import com.example.androidroom.data.entities.Score;

import java.util.List;

public class ScoreViewModel extends AndroidViewModel {
    private Repository mRepository;

    private LiveData<List<Score>> mScores;
    private int courseId;

    public ScoreViewModel(Application application) {
        super(application);
        mRepository = new Repository(application);

    }

    public LiveData<List<Score>> getScores(int courseId) {
        mScores = mRepository.getScores(courseId);

        return mScores;
    }

    public void insert(Score... scores) {
        mRepository.insertAll(scores);
    }
}
