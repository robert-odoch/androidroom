package com.example.androidroom.ui.score;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidroom.R;
import com.example.androidroom.data.entities.Score;

import java.util.List;


public class ScoreListAdapter extends RecyclerView.Adapter<ScoreListAdapter.ScoreViewHolder> {
    private final LayoutInflater mInflater;
    private List<Score> mScores;

    public ScoreListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.score_item, parent, false);
        return new ScoreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        if (mScores != null) {
            Score current = mScores.get(position);
            holder.studentId.setText(String.valueOf(current.getStudentId()));
            holder.scoreOne.setText(String.valueOf(current.getQ1()));
            holder.scoreTwo.setText(String.valueOf(current.getQ2()));
            holder.scoreThree.setText(String.valueOf(current.getQ3()));
            holder.scoreFour.setText(String.valueOf(current.getQ4()));
            holder.scoreFive.setText(String.valueOf(current.getQ5()));
        }
    }

    @Override
    public int getItemCount() {
        if (mScores != null) return mScores.size();

        return 0;
    }

    public void setScores(List<Score> scores) {
        this.mScores = scores;
        notifyDataSetChanged();
    }

    class ScoreViewHolder extends RecyclerView.ViewHolder {
        final TextView studentId;
        final TextView scoreOne;
        final TextView scoreTwo;
        final TextView scoreThree;
        final TextView scoreFour;
        final TextView scoreFive;

        public ScoreViewHolder(View itemView) {
            super(itemView);
            studentId = itemView.findViewById(R.id.student_id);
            scoreOne = itemView.findViewById(R.id.quiz_one_score);
            scoreTwo = itemView.findViewById(R.id.quiz_two_score);
            scoreThree = itemView.findViewById(R.id.quiz_three_score);
            scoreFour = itemView.findViewById(R.id.quiz_four_score);
            scoreFive = itemView.findViewById(R.id.quiz_five_score);
        }
    }
}
