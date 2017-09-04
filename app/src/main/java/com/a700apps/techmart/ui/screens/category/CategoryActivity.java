package com.a700apps.techmart.ui.screens.category;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.a700apps.techmart.R;
import com.a700apps.techmart.ui.screens.listgroup.GroubListActivity;

public class CategoryActivity extends AppCompatActivity {

    boolean isCountry = false, isBussines = false, isGroup = false;
    private static final int STATE_IS_COUNTRY = 1;
    private static final int STATE_IS_BUSINESS = 2;
    private static final int STATE_IS_GROUP = 3;
    ImageView countryBtn, businessBtn, groupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Button nextBtn = (Button) findViewById(R.id.nextBtn);
        countryBtn = (ImageView) findViewById(R.id.iv_country_council);
        businessBtn = (ImageView) findViewById(R.id.iv_business_associates);
        groupBtn = (ImageView) findViewById(R.id.iv_groups);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CategoryActivity.this, GroubListActivity.class));
            }
        });
        countryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateIcons(STATE_IS_COUNTRY);
            }
        });
        businessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateIcons(STATE_IS_BUSINESS);
            }
        });

        groupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateIcons(STATE_IS_GROUP);
            }
        });
    }

    private void updateIcons(int state) {
        switch (state) {
            case STATE_IS_COUNTRY:
                isCountry = true;
                isBussines = isGroup = false;
                countryBtn.setImageResource(R.drawable.ic_1_active);
                businessBtn.setImageResource(R.drawable.ic_2);
                groupBtn.setImageResource(R.drawable.ic_3);
                break;
            case STATE_IS_BUSINESS:
                isBussines = true;
                isCountry = isGroup = false;
                countryBtn.setImageResource(R.drawable.ic_1);
                businessBtn.setImageResource(R.drawable.ic_2_active);
                groupBtn.setImageResource(R.drawable.ic_3);
                break;
            case STATE_IS_GROUP:
                isGroup = true;
                isBussines = isCountry = false;
                countryBtn.setImageResource(R.drawable.ic_1);
                businessBtn.setImageResource(R.drawable.ic_2);
                groupBtn.setImageResource(R.drawable.ic_3_active);
                break;
            default:
                isCountry = isBussines = isGroup = false;
                countryBtn.setImageResource(R.drawable.ic_1);
                businessBtn.setImageResource(R.drawable.ic_2);
                groupBtn.setImageResource(R.drawable.ic_3);
                break;
        }
    }
}
