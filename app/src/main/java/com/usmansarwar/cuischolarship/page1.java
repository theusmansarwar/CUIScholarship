package com.usmansarwar.cuischolarship;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.usmansarwar.cuischolarship.Model.News;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;


public class page1 extends AppCompatActivity  {
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private List<News> newsList = new ArrayList<>();
    private DatabaseReference databaseReference;
private CardView scholarshipcardview,statuscardview,Societycardview,Activitycardview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);




            recyclerView = findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            newsAdapter = new NewsAdapter(newsList);
            recyclerView.setAdapter(newsAdapter);

            databaseReference = FirebaseDatabase.getInstance().getReference("news");

            // Fetch data from the database
            fetchNewsData();


        scholarshipcardview= findViewById(R.id.card1);
        scholarshipcardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(page1.this, Scholarships.class);
                startActivity(intent);
            }
        });
        statuscardview= findViewById(R.id.card2);
        statuscardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(page1.this, Status.class);
                startActivity(intent);
            }
        });
        Societycardview= findViewById(R.id.card3);
        Societycardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(page1.this, SocietyListActivity.class);
                startActivity(intent);
            }
        });
        Activitycardview= findViewById(R.id.card4);
        Activitycardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(page1.this, Activities.class);
                startActivity(intent);
            }
        });




    }

        private void fetchNewsData() {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    newsList.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        News news = snapshot.getValue(News.class);
                        newsList.add(news);
                    }
                    newsAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e("MainActivity", "Failed to read data from the database", databaseError.toException());
                }
            });
        }
    }