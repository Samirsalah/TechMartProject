package com.a700apps.techmart.ui.screens.timeline;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.a700apps.techmart.R;
import com.a700apps.techmart.ui.screens.timelinedetails.DetailsActivity;
import com.a700apps.techmart.utils.ActivityUtils;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimeLineMainFragment extends Fragment {


    public TimeLineMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_time_line_main, container, false);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recyclerView);
        rv.setAdapter(new TimelineAdapter(getActivity()));
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    private static class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {

        Context context;
        private static final int NOTIF_TYPE_ALARM = 0;
        private static final int NOTIF_TYPE_CONNECT = 1;
        private static final int NOTIF_TYPE_FOLLOW = 2;
        private static final int NOTIF_TYPE_POST = 3;
        private static final int NOTIF_TYPE_VIDEO = 4;


        public TimelineAdapter(Context context) {
            this.context = context;
        }

        @Override
        public TimelineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View noteView;
            switch (viewType) {
                case NOTIF_TYPE_ALARM:
                    noteView = inflater.inflate(R.layout.timeline_first_item, parent, false);
                    break;
                case NOTIF_TYPE_CONNECT:
                    noteView = inflater.inflate(R.layout.timeline_first_item, parent, false);
                    break;
                case NOTIF_TYPE_FOLLOW:
                    noteView = inflater.inflate(R.layout.timeline_first_item, parent, false);
                    break;
                case NOTIF_TYPE_POST:
                    noteView = inflater.inflate(R.layout.timeline_first_item, parent, false);
                    break;
                case NOTIF_TYPE_VIDEO:
                    noteView = inflater.inflate(R.layout.timeline_first_item, parent, false);
                    break;
                default:
                    noteView = inflater.inflate(R.layout.timeline_first_item, parent, false);
                    break;
            }
            return new TimelineAdapter.ViewHolder(noteView);
        }

        @Override
        public void onBindViewHolder(TimelineAdapter.ViewHolder viewHolder, int position) {
            final int itemType = getItemViewType(position);
            switch (itemType) {
                case NOTIF_TYPE_ALARM:
                case NOTIF_TYPE_CONNECT:
                case NOTIF_TYPE_FOLLOW:
                case NOTIF_TYPE_POST:
                case NOTIF_TYPE_VIDEO:
            }
        }

        @Override
        public int getItemViewType(int position) {
            int type = position;
            switch (type) {
                case 0:
                    return NOTIF_TYPE_ALARM;
                case 1:
                    return NOTIF_TYPE_CONNECT;
                case 2:
                    return NOTIF_TYPE_FOLLOW;
                case 3:
                    return NOTIF_TYPE_POST;
                case 4:
                    return NOTIF_TYPE_VIDEO;
                default:
            }
            return super.getItemViewType(position);
        }

        @Override
        public int getItemCount() {
            return 5;
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            ImageView shareBtn, inviteBtn, addCalenderBtn;

            public ViewHolder(View itemView) {
                super(itemView);
                shareBtn = (ImageView) itemView.findViewById(R.id.iv_share);
                inviteBtn = (ImageView) itemView.findViewById(R.id.iv_invite);
                addCalenderBtn = (ImageView) itemView.findViewById(R.id.iv_add_calender);
                itemView.setOnClickListener(this);
                shareBtn.setOnClickListener(this);
                inviteBtn.setOnClickListener(this);
                addCalenderBtn.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                int viewId = v.getId();
                switch (viewId) {
                    case R.id.iv_share:
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                        sendIntent.setType("text/plain");
                        context.startActivity(sendIntent);
                        break;
                    case R.id.iv_invite:
                        break;
                    case R.id.iv_add_calender:
                        Calendar cal = Calendar.getInstance();
                        Intent intent = new Intent(Intent.ACTION_EDIT);
                        intent.setType("vnd.android.cursor.item/event");
                        intent.putExtra(CalendarContract.Events.TITLE, "Event");
                        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                                cal.getTime().getTime());
                        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                                cal.getTime().getTime());
                        intent.putExtra(CalendarContract.Events.ALL_DAY, false);// periodicity
                        intent.putExtra(CalendarContract.Events.DESCRIPTION, "Tech Mart Event");
                        context.startActivity(intent);
                        break;
                    default:
                        openDetails(context);
                        break;
                }
            }
        }


    }

    static void openDetails(Context context) {
        ActivityUtils.openActivity(context, DetailsActivity.class, false);
    }

}
