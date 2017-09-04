package com.a700apps.techmart.ui.screens.mygroup;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.a700apps.techmart.R;
import com.a700apps.techmart.ui.screens.home.HomeActivity;
import com.a700apps.techmart.ui.screens.notification.NotificationActivity;
import com.a700apps.techmart.ui.screens.profile.EditProfileActivity;
import com.a700apps.techmart.utils.ActivityUtils;

import butterknife.ButterKnife;

/**
 * Created by samir salah on 8/16/2017.
 */

public class MyGroupFragment extends Fragment {

    ImageView imageView4;
    ImageView  mNotificationImageView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_my_groub_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recyclerView);
        rv.setAdapter(new GroupsAdapter(getContext()));
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mNotificationImageView = (ImageView)view. findViewById(R.id.new_profile);
        mNotificationImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.openActivity(getActivity(), NotificationActivity.class, false);


            }
        });
        imageView4 = (ImageView) view.findViewById(R.id.imageView4);
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity) getActivity()).openDrawer();
            }
        });
    }


    private static class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.ViewHolder> {

        private final Context context;

        public GroupsAdapter(Context context) {
            this.context = context;
        }

        @Override
        public GroupsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View noteView = inflater.inflate(R.layout.my_groub_list_item, parent, false);

            return new GroupsAdapter.ViewHolder(noteView);
        }

        @Override
        public void onBindViewHolder(GroupsAdapter.ViewHolder viewHolder, int position) {

        }

        @Override
        public int getItemCount() {
            return 25;
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public ViewHolder(View itemView) {
                super(itemView);
                itemView.findViewById(R.id.view_detail_btn).setOnClickListener(this);
            }

            @Override
            public void onClick(View v)
            {
                int position = getAdapterPosition();
                ActivityUtils.openActivity(context, EditProfileActivity.class,false);
            }
        }
    }
}
