package com.bikcrum.firebasedatabasewithauthexample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.view_recycler);

        setupUI();
    }

    private void setupUI() {
        Query query = FirebaseDatabase.getInstance()
                .getReference("patients").limitToLast(100);

        FirebaseRecyclerOptions<PatientModel> options =
                new FirebaseRecyclerOptions.Builder<PatientModel>()
                        .setLifecycleOwner(this)
                        .setQuery(query, PatientModel.class)
                        .build();

        FirebaseRecyclerAdapter<PatientModel, PatientHolder> adapter = new FirebaseRecyclerAdapter<PatientModel, PatientHolder>(options) {
            @Override
            @NonNull
            public PatientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.view_patient, parent, false);

                return new PatientHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull PatientHolder holder, int position, @NonNull PatientModel model) {
                holder.tvName.setText(getString(R.string.name, model.getName()));
                holder.tvAddress.setText(getString(R.string.address, model.getAddress()));
            }
        };

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    public class PatientHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvAddress;

        PatientHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAddress = itemView.findViewById(R.id.tv_address);
        }
    }

    public void addPatient(View view) {
        FirebaseDatabase.getInstance().getReference("patients").push().setValue(new PatientModel(UUID.randomUUID().toString(), UUID.randomUUID().toString()));
    }
}
