package com.codepath.fittrack.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.fittrack.EditUserInfoActivity;
import com.codepath.fittrack.LoginActivity;
import com.codepath.fittrack.R;
import com.codepath.fittrack.UserInfo;
import com.parse.ParseFile;
import com.parse.ParseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {

    public static final String TAG= "UserFragment";
    public static final String KEY_DESCRIPTION= "userDescription";
    public static final String KEY_PROFILE_IMAGE="profileImage";
    private final int REQUEST_CODE = 20;
    private TextView tvProfileName;
    private TextView tvProfileDescription;
    private ImageView ivProfileImage;
    private Button btnLogout;
    private UserInfo currentUserInfo;

    public UserFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvProfileName = view.findViewById(R.id.tvProfileName);
        tvProfileDescription = view.findViewById(R.id.tvProfileDescription);
        ivProfileImage = view.findViewById(R.id.ivProfileImage);
        btnLogout = view.findViewById(R.id.btnLogout);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(null);
        //Create the menu
        setHasOptionsMenu(true);

        //gets user information
        displayUser();
        //TODO:set userinformation when user signsup
        //onclick listener to log user out not complete
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLoginActivity(view);
            }
        });
    }

    private void displayUser() {
        ParseUser currentUser= ParseUser.getCurrentUser();
        tvProfileName.setText(currentUser.getUsername());
        tvProfileDescription.setText(currentUser.getString(KEY_DESCRIPTION));
        ParseFile image = currentUser.getParseFile(KEY_PROFILE_IMAGE);
        if(image!=null){
            if(getContext()==null){
                return;
            }
            Glide.with(getContext()).load(image.getUrl()).into(ivProfileImage);
        }
    }

    private void goLoginActivity(View view) {
        ParseUser.logOut();
        Intent i= new Intent(view.getContext(), LoginActivity.class);
        startActivity(i);
        getActivity().finish();
    }

    /*----- Overhead Menu Bar -----*/

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.user_edit_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_user_edit) {
            Intent intent = new Intent(getActivity(), EditUserInfoActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /* query user using UserInfo no longer used
    private void queryCurrentUser() {
        ParseQuery<UserInfo> query = ParseQuery.getQuery(UserInfo.class);
        query.include(UserInfo.KEY_USER);
        query.findInBackground(new FindCallback<UserInfo>() {
            @Override
            public void done(List<UserInfo> usersinfo, ParseException e) {
                if(e!=null){
                    Log.e(TAG, "Issue with getting users", e);
                    return;
                }
                //Toast.makeText(getActivity(), "hello", Toast.LENGTH_SHORT).show();
                for(UserInfo user : usersinfo){
                    if (user.getUser().getUsername().equals(ParseUser.getCurrentUser().getUsername())){
                        //changing front end
                        Log.i(TAG, user.getUsername());
                        tvProfileName.setText(user.getUsername());
                        tvProfileDescription.setText(user.getUserDescription());
                        ParseFile image = user.getImage();
                        if(image!=null){
                            if(getContext()==null){
                                return;
                            }
                            Glide.with(getContext()).load(user.getImage().getUrl()).into(ivProfileImage);
                        }
                        return;
                    }
                }
            }
        });
    }*/
}