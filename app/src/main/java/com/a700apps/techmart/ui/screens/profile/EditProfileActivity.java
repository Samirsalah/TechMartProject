package com.a700apps.techmart.ui.screens.profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.a700apps.techmart.R;
import com.a700apps.techmart.ui.screens.notification.NotificationActivity;
import com.a700apps.techmart.utils.ActivityUtils;

public class EditProfileActivity extends AppCompatActivity {
    ImageView mProfileImageView, mNotificationImageView;
    ImageView imageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        imageView4 = (ImageView) findViewById(R.id.imageView4);

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mProfileImageView = (ImageView) findViewById(R.id.new_message);
        mNotificationImageView = (ImageView) findViewById(R.id.new_profile);

        mProfileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.openActivity(EditProfileActivity.this, EditProfileActivity.class, false);
            }
        });

        mNotificationImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.openActivity(EditProfileActivity.this, NotificationActivity.class, false);


            }
        });
    }
}
