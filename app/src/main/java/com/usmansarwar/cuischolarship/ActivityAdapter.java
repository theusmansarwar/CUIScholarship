package com.usmansarwar.cuischolarship;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.usmansarwar.cuischolarship.Model.Activity;

import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder> {

    private final List<Activity> activityList;
    private final Context context;

    public ActivityAdapter(List<Activity> activityList, Context context) {
        this.activityList = activityList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Activity activity = activityList.get(position);

        if (activity != null) {
            holder.titleTextView.setText(activity.getTitle());
            holder.descriptionTextView.setText(activity.getDescription());
            holder.eventDateTextView.setText(activity.getEventDate());
            holder.societyNameTextView.setText(activity.getSocietyName());
            holder.eventVenueTextView.setText(activity.getEventVenue());
            holder.linkTextView.setText("Apply");

            // Set up the link TextView click listener
            holder.linkTextView.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(activity.getApplyLink()));
                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return activityList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView titleTextView;
        final TextView descriptionTextView;
        final TextView eventDateTextView;
        final TextView societyNameTextView;
        final TextView eventVenueTextView;
        final TextView linkTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.textViewTitle);
            descriptionTextView = itemView.findViewById(R.id.textViewDescription);
            eventDateTextView = itemView.findViewById(R.id.textViewEventDate);
            societyNameTextView = itemView.findViewById(R.id.textViewSocietyName);
            eventVenueTextView = itemView.findViewById(R.id.textViewEventVenue);
            linkTextView = itemView.findViewById(R.id.link);
        }
    }
}
