package com.codepath.fittrack.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.codepath.fittrack.LoginActivity;
import com.codepath.fittrack.R;
import com.parse.ParseUser;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {

    private TextView tvProfileName;
    private TextView tvProfileDescription;
    private TextView ivProfileImage;
    private Button btnLogout;

    public UserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TestFragment.
     */
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

        //variables that have not been used due to not having a backend holder/variable/info
        tvProfileDescription = view.findViewById(R.id.tvProfileDescription);
        ivProfileImage = view.findViewById(R.id.ivProfileImage);

        //sets the profile name to username
        tvProfileName.setText(ParseUser.getCurrentUser().getUsername());

        //onclick listener to log user out not complete
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLoginActivity(view);
            }
        });


    }

    private void goLoginActivity(View view) {
        //TODO:figure out why finish isn't working
        ParseUser.logOut();
        Intent i= new Intent(view.getContext(), LoginActivity.class);
        startActivity(i);
        //finish();
    }
}