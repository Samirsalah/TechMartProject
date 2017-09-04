package com.a700apps.techmart.ui.screens.timeline;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a700apps.techmart.R;
import com.a700apps.techmart.ui.screens.groupmemberdetails.GroupActivity;
import com.a700apps.techmart.ui.screens.grouptimeline.GroupTimeLineActivity;
import com.a700apps.techmart.ui.screens.home.HomeActivity;
import com.a700apps.techmart.ui.screens.meeting.MeetingActivity;
import com.a700apps.techmart.ui.screens.notification.NotificationActivity;
import com.a700apps.techmart.ui.screens.profile.EditProfileActivity;
import com.a700apps.techmart.ui.screens.timelinedetails.DetailsActivity;
import com.a700apps.techmart.utils.ActivityUtils;

import java.util.Calendar;

import butterknife.ButterKnife;

/**
 * Created by samir salah on 8/17/2017.
 */

public class TimelineFragment extends Fragment implements View.OnClickListener {

    ImageView mProfileImageView, mNotificationImageView, imageView4;
    TextView mGroup, mTimline;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private LinearLayout mTabContainer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_timeline, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) view.findViewById(R.id.vp_timeline);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mTabContainer = (LinearLayout) view.findViewById(R.id.lin_category);
        for (int i = 0; i < mTabContainer.getChildCount(); i++) {
            mTabContainer.getChildAt(i).setOnClickListener(this);
        }
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                mTabContainer.getChildAt(position).setBackground(getResources().getDrawable(R.drawable.bt_1));
                for (int i = 0; i < mTabContainer.getChildCount(); i++) {
                    if (i != position)
                        mTabContainer.getChildAt(i).setBackground(null);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mProfileImageView = (ImageView) view.findViewById(R.id.new_message);
        mNotificationImageView = (ImageView) view.findViewById(R.id.new_profile);
        imageView4 = (ImageView) view.findViewById(R.id.imageView4);

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity) getActivity()).openDrawer();
            }
        });
        mGroup = (TextView) view.findViewById(R.id.tv_group);
        mTimline = (TextView) view.findViewById(R.id.tv_timeline);
        mTimline.setBackground(getResources().getDrawable(R.drawable.bt_1));

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId){
            case R.id.tv_timeline:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.tv_event:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.tv_news:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.tv_group:
                mViewPager.setCurrentItem(3);
                break;
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
            switch (position) {
                case 0:
                    return new TimeLineMainFragment();
                case 1:
                    return new GroupTimeLineFragment();
                case 2:
                    return new GroupTimeLineFragment();
                case 3:
//                    return new GroupTimeLineFragment();
                ActivityUtils.openActivity(getActivity(), GroupTimeLineActivity.class,false);
                default:
                    return new GroupTimeLineFragment();
            }
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Timeline";
                case 1:
                    return "Events";
                case 2:
                    return "News";
                case 3:
                    return "Groups";
            }
            return null;
        }
    }

}
