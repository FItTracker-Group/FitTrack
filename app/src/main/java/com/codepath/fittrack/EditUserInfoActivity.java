package com.codepath.fittrack;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.codepath.fittrack.fragments.UserFragment;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.util.List;

public class EditUserInfoActivity extends AppCompatActivity {
    public static final String TAG = "EditUserInfoActivity";
    public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 42;

    private String userObjectID;
    private Button btnCaptureImage;
    private ImageView ivPostImage;
    private Button btnSubmit;
    private EditText etTextPersonName;
    private EditText etTextDescription;

    private File photoFile;
    public String photoFileName = "photo.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_info);

        etTextPersonName = findViewById(R.id.etTextPersonName);
        etTextDescription = findViewById(R.id.etTextDescription);
        btnCaptureImage = findViewById(R.id.btnCaptureImage);
        ivPostImage = findViewById(R.id.ivPostImage);
        btnSubmit = findViewById(R.id.btnSubmit);
        // queryPosts();
        btnCaptureImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchCamera();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newdescription = etTextDescription.getText().toString();
                String newName = etTextPersonName.getText().toString();
                //Prepare data intent
                Intent data = new Intent();

                saveChange(newName,newdescription,photoFile);
                //Activity finished
                //setResult(RESULT_OK, data); //set result code and bundle data for response
                finish(); //close the activity, pass the data back to feed
            }
        });
    }

    ActivityResultLauncher<Intent> someAcitivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    // int resultCode = result.getResultCode();
                    if (result.getResultCode() == RESULT_OK) {
                        // by this point we have the camera photo on disk
                        Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                        // RESIZE BITMAP, see section below
                        // Load the taken image into a preview
                        ivPostImage.setImageBitmap(takenImage);
                    } else { // Result was a failure
                        Toast.makeText(EditUserInfoActivity.this, "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    private void launchCamera() {
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Create a File reference for future access
        photoFile = getPhotoFileUri(photoFileName);

        // wrap File object into a content provider
        // required for API >= 24
        // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
        // "com.codepath.fileprovider"
        Uri fileProvider = FileProvider.getUriForFile(EditUserInfoActivity.this, "com.codepath.fileproviders", photoFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(EditUserInfoActivity.this.getPackageManager()) != null) {
            // Start the image capture intent to take photo
            //startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            someAcitivityResultLauncher.launch(intent);
        }
    }

    // Returns the File for a photo stored on disk given the fileName
    public File getPhotoFileUri(String fileName) {
        // Get safe storage directory for photos
        // Use `getExternalFilesDir` on Context to access package-specific directories.
        // This way, we don't need to request external read/write runtime permissions.
        File mediaStorageDir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), TAG);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
            Log.d(TAG, "failed to create directory");
        }
        // Return the file target for the photo based on filename
        return new File(mediaStorageDir.getPath() + File.separator + fileName);
    }

    public void saveChange (String newName,String newDescription, File photoFile) {
        ParseUser currentUser = ParseUser.getCurrentUser();
        if(!newName.isEmpty()){
            currentUser.put("displayName", newName);
        }
        if(!newDescription.isEmpty()){
            currentUser.put("userDescription", newDescription);
        }
        if(photoFile!=null){
            ParseFile newFile=new ParseFile(photoFile, "photo.jpg");
            currentUser.put("profileImage", newFile);
        }
        currentUser.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null){
                    Log.e(TAG, "Error with uploading", e);
                    return;
                }
                Toast.makeText(getApplicationContext(), "Uploaded Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
