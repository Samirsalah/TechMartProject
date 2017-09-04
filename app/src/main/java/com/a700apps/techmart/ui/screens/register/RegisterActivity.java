package com.a700apps.techmart.ui.screens.register;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.a700apps.techmart.R;
import com.a700apps.techmart.TechMartApp;
import com.a700apps.techmart.ui.screens.category.CategoryActivity;
import com.a700apps.techmart.ui.screens.login.LoginActivity;
import com.a700apps.techmart.ui.screens.login.LoginPresenter;
import com.a700apps.techmart.utils.ActivityUtils;
import com.a700apps.techmart.utils.AppUtils;
import com.a700apps.techmart.utils.Validator;

import java.io.File;
import java.io.InputStream;


/**
 * Created by samir salah on 8/16/2017.
 */

public class RegisterActivity extends Activity implements RegisterView, View.OnClickListener {

    EditText mFullNameEditText, mPhoneNumberEditText, mEmailEditText, mPasswordEditText;
    private static final int SELECT_PICTURE = 1;
    private long selectedImageSize;
    private String selectedImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViews();
//        presenter = new LoginPresenter();
//        presenter.attachView(this);
        ActivityUtils.hideKeyboard(this);
    }

    private void findViews() {
        Button SignButton = ActivityUtils.findView(this, R.id.bt_register, Button.class);
        Button attachButton = ActivityUtils.findView(this, R.id.bt_upload, Button.class);
        mFullNameEditText = ActivityUtils.findView(this, R.id.et_name, EditText.class);
        mPhoneNumberEditText = ActivityUtils.findView(this, R.id.et_mobile, EditText.class);
        mEmailEditText = ActivityUtils.findView(this, R.id.et_email, EditText.class);
        mPasswordEditText = ActivityUtils.findView(this, R.id.et_pass, EditText.class);
        SignButton.setOnClickListener(this);
        attachButton.setOnClickListener(this);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                int dataSize = 0;
                File f = null;
                Uri selectedImageUri = data.getData();
                String scheme = selectedImageUri.getScheme();
                selectedImagePath = getPath(selectedImageUri);
                if (scheme.equals(ContentResolver.SCHEME_CONTENT)) {
                    try {
                        InputStream fileInputStream = getApplicationContext()
                                .getContentResolver().openInputStream(selectedImageUri);
                        dataSize = fileInputStream.available();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    selectedImageSize = dataSize;

                } else if (scheme.equals(ContentResolver.SCHEME_FILE)) {
                    String path = selectedImagePath;
                    try {
                        f = new File(path);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    selectedImageSize = f.length();
                }
                Toast.makeText(this, "Size:" + String.valueOf(selectedImageSize), Toast.LENGTH_SHORT).show();
            }
        }
    }


    /**
     * helper to retrieve the path of an image URI
     */
    Cursor cursor;

    public String getPath(Uri uri) {
        // just some safety built in
        if (uri == null) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = {MediaStore.Images.Media.DATA};
        cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        }
        cursor.close();
        // this is our fallback here
        return uri.getPath();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (cursor != null) {
            cursor.close();
        }
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.bt_upload:
                selectedImagePath = null;
                selectedImageSize = 0;
                // select a file
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), SELECT_PICTURE);
                break;
            case R.id.bt_register:
                String fullName = ActivityUtils.getViewTextValue(mFullNameEditText);
                String password = ActivityUtils.getViewTextValue(mPasswordEditText);
                String email = ActivityUtils.getViewTextValue(mEmailEditText);
                String mobile = ActivityUtils.getViewTextValue(mPhoneNumberEditText);

                if (selectedImagePath != null) {
                    if (accept(selectedImagePath)) {
                        if (selectedImageSize > 1013290){
                            Snackbar snackbar1 = Snackbar.make(v, R.string.large_image, Snackbar.LENGTH_SHORT);
                            snackbar1.show();
                            return;
                        }
                    } else {
                        Snackbar snackbar1 = Snackbar.make(v, R.string.not_supported_image, Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                        return;
                    }
                } else {
                    Snackbar snackbar1 = Snackbar.make(v, R.string.no_image, Snackbar.LENGTH_SHORT);
                    snackbar1.show();
                    return;
                }

                boolean emptyName = Validator.isTextEmpty(fullName);
                if (emptyName) {
                    mFullNameEditText.setError(getResources().getString(R.string.name_length_not_valid));
                    return;
                }
                boolean validEmail = Validator.validEmail(email);
                if (!validEmail) {
                    mEmailEditText.setError(getResources().getString(R.string.invalid_email));
                    return;
                }
                boolean validPassword = Validator.validPassword(password);
                if (!validPassword) {
                    mPasswordEditText.setError(getResources().getString(R.string.invalid_password));
                    return;
                }

                boolean validMobileNumber = Validator.validMobileNumber(mobile.replaceFirst("\\+", ""));
                if (!validMobileNumber) {
                    mPhoneNumberEditText.setError(getResources().getString(R.string.invalid_mobile_number));
                    return;
                }


                if (!AppUtils.isInternetAvailable(RegisterActivity.this)) {
                    Snackbar snackbar1 = Snackbar.make(v, R.string.no_internet_connection, Snackbar.LENGTH_SHORT);
                    snackbar1.show();
                    return;
                }

                openCouncilActivity();
                break;
            default:
                break;
        }
    }


    private final String[] okFileExtensions = new String[]{"jpg", "jpeg"};

    public boolean accept(String file) {
        for (String extension : okFileExtensions) {
            if (file.toLowerCase().endsWith(extension)) {
                Toast.makeText(this, file, Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return false;
    }

    @Override
    public void openCouncilActivity() {
        ActivityUtils.openActivity(RegisterActivity.this, CategoryActivity.class, false);
    }
}
