package com.usmansarwar.cuischolarship;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.usmansarwar.cuischolarship.Model.Scholarship;
import java.util.List;

public class ScholarshipAdapter extends RecyclerView.Adapter<ScholarshipAdapter.ScholarshipViewHolder> {
    private List<Scholarship> scholarshipList;

    public ScholarshipAdapter(List<Scholarship> scholarshipList) {
        this.scholarshipList = scholarshipList;
    }

    // Add a method to update the scholarship list and notify the adapter about the data change
    public void updateScholarshipList(List<Scholarship> newScholarshipList) {
        this.scholarshipList = newScholarshipList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ScholarshipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scholarship, parent, false);
        return new ScholarshipViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScholarshipViewHolder holder, int position) {
        Scholarship scholarship = scholarshipList.get(position);
        if (scholarship != null) {
            holder.titleTextView.setText(scholarship.getScholarshipTitle());
            holder.criteriaTextView.setText(scholarship.getEligibilityCriteria());
            holder.amountTextView.setText(String.valueOf(scholarship.getScholarshipAmount()));
            holder.typeTextView.setText(scholarship.getScholarshipType());

            // Set the click listener for the "Apply Now" button
            holder.applyNowButton.setOnClickListener(v -> {
                // Create an intent to start the ApplyScholarshipActivity
                Intent intent = new Intent(v.getContext(), Applyscholarship.class);
                // Pass the desired item name as an extra to the intent
                intent.putExtra("scholarshipName", scholarship.getScholarshipTitle());
                // Start the ApplyScholarshipActivity
                v.getContext().startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return scholarshipList != null ? scholarshipList.size() : 0;
    }

    static class ScholarshipViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView criteriaTextView;
        TextView amountTextView;
        TextView typeTextView;
        Button applyNowButton;

        public ScholarshipViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_text_view);
            criteriaTextView = itemView.findViewById(R.id.criteria_text_view);
            amountTextView = itemView.findViewById(R.id.Amount_text_view);
            typeTextView = itemView.findViewById(R.id.type_text_view);
            applyNowButton = itemView.findViewById(R.id.Applynowbtn);
        }
    }
}
