package com.a700apps.techmart.ui.screens.chat;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.a700apps.techmart.R;

import static com.a700apps.techmart.R.id.message_edit_text;
import static com.a700apps.techmart.R.id.recyclerView;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        final RecyclerView rv = (RecyclerView) findViewById(recyclerView);
        final EditText editComment = (EditText) findViewById(message_edit_text);
        final ChatAdapter chatAdapter = new ChatAdapter(this);
        rv.setAdapter(chatAdapter);
        rv.smoothScrollToPosition(chatAdapter.getItemCount()-1);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.getLayoutManager().smoothScrollToPosition(rv,null, chatAdapter.getItemCount()-1);
        rv.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                if (i3 < i7) {
                    rv.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            rv.smoothScrollToPosition(rv.getAdapter().getItemCount() - 1);
                        }
                    }, 100);
                }
            }
        });

        editComment.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (editComment.getRight() - editComment.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        chatAdapter.addChatItem();
                        rv.smoothScrollToPosition(rv.getAdapter().getItemCount() - 1);
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private static class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

        Context context;
        private static final int CHAT_TYPE_ME = 0;
        private static final int CHAT_TYPE_OTHER = 1;
        private int ITEM_COUNT = 25;

        public ChatAdapter(Context context) {
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View noteView;
            switch (viewType){
                case CHAT_TYPE_ME:
                    noteView = inflater.inflate(R.layout.chat_me_list, parent, false);
                    break;
                case CHAT_TYPE_OTHER:
                    noteView = inflater.inflate(R.layout.chat_you_item, parent, false);
                    break;
                default:
                    noteView = inflater.inflate(R.layout.chat_me_list, parent, false);
                    break;
            }
            return new ViewHolder(noteView);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            final int itemType = getItemViewType(position);
            switch (itemType){
                case CHAT_TYPE_ME:
                case CHAT_TYPE_OTHER:
            }
        }

        void addChatItem(){
            ITEM_COUNT++;
            notifyDataSetChanged();
        }

        @Override
        public int getItemViewType(int position) {
            int type = position;
            switch (type) {
                case 0:
                    return CHAT_TYPE_ME;
                case 1:
                    return CHAT_TYPE_OTHER;
                default:
                    return position%2 == 0 ?CHAT_TYPE_ME:CHAT_TYPE_OTHER;
            }
        }

        @Override
        public int getItemCount() {
            return ITEM_COUNT;
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
