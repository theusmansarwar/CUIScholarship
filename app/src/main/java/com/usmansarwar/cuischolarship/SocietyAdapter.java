package com.usmansarwar.cuischolarship;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.usmansarwar.cuischolarship.Model.Society;
import com.usmansarwar.cuischolarship.R;

import java.util.List;

public class SocietyAdapter extends RecyclerView.Adapter<SocietyAdapter.SocietyViewHolder> {

    private List<Society> societyList;

    public SocietyAdapter(List<Society> societyList) {
        this.societyList = societyList;
    }

    @NonNull
    @Override
    public SocietyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.society_item, parent, false);
        return new SocietyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SocietyViewHolder holder, int position) {
        Society society = societyList.get(position);

        // Bind the data to the views
        holder.titleTextView.setText("Title: " + society.getTitle());
        holder.descriptionTextView.setText("Description: " + society.getDescription());
        holder.presidentNameTextView.setText("President Name: " + society.getPresidentName());
        holder.presidentRegNoTextView.setText("President RegNo: " + society.getPresidentRegNo());
        holder.presidentEmailTextView.setText("President Email: " + society.getPresidentEmail());

        // Add click listener for the "View Events" button (if needed)
        holder.viewEventsButton.setOnClickListener(v -> {
            // Handle button click (e.g., navigate to another activity to show society events)
        });
    }

    @Override
    public int getItemCount() {
        return societyList != null ? societyList.size() : 0;
    }

    static class SocietyViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;
        TextView presidentNameTextView;
        TextView presidentRegNoTextView;
        TextView presidentEmailTextView;
        Button viewEventsButton;

        public SocietyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.textViewTitle);
            descriptionTextView = itemView.findViewById(R.id.textViewDescription);
            presidentNameTextView = itemView.findViewById(R.id.textViewPresidentName);
            presidentRegNoTextView = itemView.findViewById(R.id.textViewPresidentRegNo);
            presidentEmailTextView = itemView.findViewById(R.id.textViewPresidentEmail);
            viewEventsButton = itemView.findViewById(R.id.buttonViewEvents);
        }
    }
}
