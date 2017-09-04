package com.a700apps.techmart.ui.screens.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.a700apps.techmart.R;
import com.a700apps.techmart.ui.screens.chat.ChatActivity;
import com.a700apps.techmart.ui.screens.newchat.NewChatActivity;

public class MessagesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        ImageView newBtn = (ImageView) findViewById(R.id.new_message);
        newBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MessagesActivity.this, NewChatActivity.class));
            }
        });
        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerView);
        rv.setAdapter(new MessagesAdapter(this));
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    private static class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {

        Context context;
        public MessagesAdapter(Context context) {
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View noteView = inflater.inflate(R.layout.message_list_item, parent, false);
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
                context.startActivity(new Intent(context, ChatActivity.class));
            }
        }
    }
}
