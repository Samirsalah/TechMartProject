package com.a700apps.techmart.ui.screens.timelinedetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.a700apps.techmart.R;
import com.a700apps.techmart.ui.screens.home.HomeActivity;
import com.a700apps.techmart.ui.screens.notification.NotificationActivity;
import com.a700apps.techmart.ui.screens.profile.EditProfileActivity;
import com.a700apps.techmart.utils.ActivityUtils;

/**
 * Created by samir salah on 8/17/2017.
 */

public class DetailsActivity extends AppCompatActivity {
    ImageView imageView4;
    ImageView mProfileImageView, mNotificationImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline_details);
        mProfileImageView = (ImageView) findViewById(R.id.new_message);
        mNotificationImageView = (ImageView) findViewById(R.id.new_profile);
        imageView4 = (ImageView) findViewById(R.id.imageView4);

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mProfileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.openActivity(DetailsActivity.this, EditProfileActivity.class, false);
            }
        });

        mNotificationImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.openActivity(DetailsActivity.this, NotificationActivity.class, false);


            }
        });
    }
}