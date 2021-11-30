package com.codepath.fittrack.fragments;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.fittrack.EditUserInfoActivity;
import com.codepath.fittrack.LoginActivity;
import com.codepath.fittrack.R;
import com.codepath.fittrack.UserInfo;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.parse.Parse;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;

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
    private UserInfo currentUserInfo;
    private View globalView;
    private LineChart lcWeightTracker;

    private EditText etBMIWeight, etBMIHeight;
    private Button btnBMIEnter;
    private TextView tvBMIResult;


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
        globalView = view;
        lcWeightTracker = view.findViewById(R.id.lcWeightTracker);

        etBMIWeight = view.findViewById(R.id.etBMIWeight);
        etBMIHeight = view.findViewById(R.id.etBMIHeight);
        btnBMIEnter = view.findViewById(R.id.btnBMIEnter);
        tvBMIResult = view.findViewById(R.id.tvBMIResult);


        btnBMIEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvBMIResult.clearComposingText();
                String s1 = etBMIWeight.getText().toString();
                String s2 = etBMIHeight.getText().toString();

                if (etBMIHeight != null && !"".equals(etBMIHeight)
                        && etBMIWeight != null  &&  !"".equals(etBMIWeight)) {
                    float weightValue = Float.parseFloat(s1);
                    float heightValue = Float.parseFloat(s2) / 100;
                    weightValue = (float) (weightValue * 0.4536);
                    heightValue = (float) (heightValue * 2.54);
                    float bmi = weightValue / (heightValue * heightValue);


                    String bmiLabel = "";

                    if (Float.compare(bmi, 15f) <= 0) {
                        bmiLabel = "Very Severely Underweight\nYou need professional advice from your doctors";
                    } else if (Float.compare(bmi, 15f) > 0  &&  Float.compare(bmi, 16f) <= 0) {
                        bmiLabel = "Severely Underweight\nReplenish nutrients";
                    } else if (Float.compare(bmi, 16f) > 0  &&  Float.compare(bmi, 18.5f) <= 0) {
                        bmiLabel = "Underweight\nImprove muscle content";
                    } else if (Float.compare(bmi, 18.5f) > 0  &&  Float.compare(bmi, 25f) <= 0) {
                        bmiLabel = "Health\nKeep moving!";
                    } else if (Float.compare(bmi, 25f) > 0  &&  Float.compare(bmi, 30f) <= 0) {
                        bmiLabel = "Overweight\nGet more exercise";
                    } else if (Float.compare(bmi, 30f) > 0  &&  Float.compare(bmi, 35f) <= 0) {
                        bmiLabel = "Moderately Obese\nCut down on sugar and fat";
                    } else if (Float.compare(bmi, 35f) > 0  &&  Float.compare(bmi, 40f) <= 0) {
                        bmiLabel = "Severely Obese\nKeep on a diet";
                    } else {
                        bmiLabel = "Very Severely Obese\nYou need professional advice from your doctors";
                    }

                    tvBMIResult.setText(bmiLabel);
                }
            }
        });





        ArrayList<Entry> yValues = new ArrayList<>();
        yValues.add(new Entry(1, 73));
        yValues.add(new Entry(2, 74));
        yValues.add(new Entry(3, 75));
        yValues.add(new Entry(4, 74));
        yValues.add(new Entry(5, 74));
        yValues.add(new Entry(6, 75));
        yValues.add(new Entry(7, 75));
        yValues.add(new Entry(8, 74));
        yValues.add(new Entry(9, 74));
        yValues.add(new Entry(10, 73));
        yValues.add(new Entry(11, 74));
        yValues.add(new Entry(12, 73));
        yValues.add(new Entry(13, 73));


        lcWeightTracker.setDragEnabled(true);
        lcWeightTracker.setScaleEnabled(false);

        LineDataSet setWeight = new LineDataSet(yValues, "Weight");
        setWeight.setFillAlpha(110);
        setWeight.setColor(Color.BLACK);
        setWeight.setLineWidth(3f);
        setWeight.setValueTextSize(13f);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(setWeight);
        LineData data = new LineData(dataSets);
        lcWeightTracker.setData(data);


        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(null);
        //Create the menu
        setHasOptionsMenu(true);

        //gets user information
        DisplayUser();


    }

    private void DisplayUser() {
        ParseUser currentUser= ParseUser.getCurrentUser();
        tvProfileName.setText(currentUser.getString("displayName").toString());
        tvProfileDescription.setText(currentUser.getString("userDescription").toString());
        ParseFile image = currentUser.getParseFile("profileImage");
        if(image != null){
            Glide.with(this.getContext()).load(image.getUrl()).into(ivProfileImage);;
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
        if(item.getItemId() == R.id.action_logout) {
            goLoginActivity(globalView);
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