package com.a700apps.techmart.ui.screens.meeting;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.a700apps.techmart.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import butterknife.ButterKnife;

import static com.a700apps.techmart.ui.screens.meeting.MeetingFragment.PlaceholderFragment.newInstance;

/**
 * Created by samir salah on 8/17/2017.
 */

public class MeetingFragment extends Fragment {
    private MeetingActivity.SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_meeting, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

//        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) view.findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout)view. findViewById(R.id.tabs);
        viewPagerTab.setViewPager(mViewPager);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_meeting, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static MeetingActivity.PlaceholderFragment newInstance(int sectionNumber) {
            MeetingActivity.PlaceholderFragment fragment = new MeetingActivity.PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_meeting, container, false);
            RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.recyclerView);
            rv.setAdapter(new MessagesAdapter(getContext()));
            rv.setLayoutManager(new LinearLayoutManager(getContext()));
            return rootView;
        }

        private static class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {

            Context context;
            public MessagesAdapter(Context context) {
                this.context = context;
            }

            @Override
            public MessagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                Context context = parent.getContext();
                LayoutInflater inflater = LayoutInflater.from(context);
                View noteView = inflater.inflate(R.layout.meeting_recycler_item, parent, false);
                return new MessagesAdapter.ViewHolder(noteView);
            }

            @Override
            public void onBindViewHolder(MessagesAdapter.ViewHolder viewHolder, int position) {

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

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return
            newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "THIS DAY";
                case 1:
                    return "TOMORROW";
                case 2:
                    return "THIS WEEK";
                case 3:
                    return "THIS MONTH";
                case 4:
                    return "CUSTOM";
            }
            return null;
        }
    }

}
