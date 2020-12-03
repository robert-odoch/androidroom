package com.example.androidroom.ui.course;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidroom.CourseStatistics;
import com.example.androidroom.R;
import com.example.androidroom.data.entities.Course;

import java.util.List;


public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.CourseViewHolder> {

    private static final String TAG = "CourseListAdapter";

    private final LayoutInflater mInflater;
    private List<Course> mCourses;
    private Context mContext;

    public CourseListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.course_item, parent, false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        if (mCourses != null) {
            Course current = mCourses.get(position);
            holder.courseId = current.getCourseId();
            holder.courseName.setText(String.valueOf(current.getCourseName()));
        }
    }

    @Override
    public int getItemCount() {
        if (mCourses != null) return mCourses.size();

        return 0;
    }

    public void setCourses(List<Course> courses) {
        this.mCourses = courses;
        notifyDataSetChanged();
    }

    class CourseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView courseName;

        int courseId;

        public CourseViewHolder(View itemView) {
            super(itemView);
            courseName = itemView.findViewById(R.id.course_name);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, CourseStatistics.class);
            intent.putExtra("courseId", courseId);
            intent.putExtra("courseName", courseName.getText());
            mContext.startActivity(intent);
        }
    }
}
