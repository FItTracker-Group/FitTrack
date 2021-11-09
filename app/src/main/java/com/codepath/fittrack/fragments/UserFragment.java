package com.codepath.fittrack.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.fittrack.LoginActivity;
import com.codepath.fittrack.R;
import com.codepath.fittrack.UserInfo;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {

    public static final String TAG= "UserFragment";

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

        //gets user information
        queryUser();

        //onclick listener to log user out not complete
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLoginActivity(view);
            }
        });


    }
    private void queryUser() {
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
                        tvProfileName.setText(user.getUser().getUsername());
                        tvProfileDescription.setText(user.getUserDescription());
                        ParseFile image = user.getImage();
                        if(image!=null){
                            Glide.with(getContext()).load(user.getImage().getUrl()).into(ivProfileImage);
                        }
                        return;
                    }
                }
            }
        });
    }

    private void goLoginActivity(View view) {
        ParseUser.logOut();
        Intent i= new Intent(view.getContext(), LoginActivity.class);
        startActivity(i);
        getActivity().finish();
    }
}