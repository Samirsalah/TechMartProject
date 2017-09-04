package com.a700apps.techmart.ui.screens.mygroup;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a700apps.techmart.R;

public class MyGroubListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_groub_list);
        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerView);
        rv.setAdapter(new GroupsAdapter());
        rv.setLayoutManager(new LinearLayoutManager(this));

    }

    private static class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.ViewHolder> {

        public GroupsAdapter() {

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View noteView = inflater.inflate(R.layout.my_groub_list_item, parent, false);

            return new ViewHolder(noteView);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {

        }

        @Override
        public int getItemCount() {
            return 25;
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public ViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
            }
        }
    }
}
