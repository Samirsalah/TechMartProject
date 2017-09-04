package com.a700apps.techmart.ui.screens.listgroup;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.a700apps.techmart.R;
import com.a700apps.techmart.ui.screens.home.HomeActivity;
import com.a700apps.techmart.ui.screens.mygroup.MyGroubListActivity;
import com.a700apps.techmart.utils.ActivityUtils;

public class GroubListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groub_list);
        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerView);
        rv.setAdapter(new GroupsAdapter(this));
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    private static class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.ViewHolder> {

        Context context;
        public GroupsAdapter(Context context) {
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View noteView = inflater.inflate(R.layout.groub_list_item, parent, false);
            return new ViewHolder(noteView);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            Button enrollBtn = (Button) viewHolder.itemView.findViewById(R.id.button);
            enrollBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // create an instance of Dialog
                    final Dialog dialog= new Dialog(context);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    //inflate a layout
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
                    View root = inflater.inflate(R.layout.group_join_dialog, null);
                    LinearLayout indLinear = (LinearLayout) root.findViewById(R.id.linearLayout4);
                    indLinear.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ActivityUtils.openActivity(context,HomeActivity.class,true);
//                            context.startActivity(new Intent(context, HomeActivity.class));
                            dialog.dismiss();
                        }
                    });
                    // set the layout for the Dialog
                    dialog.setContentView(root);
                    dialog.show();
                }
            });
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
