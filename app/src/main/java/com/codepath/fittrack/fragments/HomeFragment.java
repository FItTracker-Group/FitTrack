package com.codepath.fittrack.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.codepath.fittrack.CardioDetail;
import com.codepath.fittrack.R;
import com.codepath.fittrack.StretchDetail;
import com.codepath.fittrack.WeightDetail;
import com.codepath.fittrack.YogaDetail;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {


    private TextView tvWeight, tvYoga;
    private ImageView ivLogo, ivWeight, ivWeight2, ivYoga, ivCardio, ivStretch;
    private ConstraintLayout clWeight, clYoga, clCardio, clStretch;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
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
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity(). getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);


        ivLogo = view.findViewById(R.id.ivLogo);

        clWeight = view.findViewById(R.id.clWeight);
        clYoga = view.findViewById(R.id.clYoga);
        clCardio = view.findViewById(R.id.clCardio);
        clStretch = view.findViewById(R.id.clStretch);

        ivWeight = view.findViewById(R.id.ivWeight);
        ivYoga = view.findViewById(R.id.ivYoga);
        ivCardio = view.findViewById(R.id.ivCardio);
        ivStretch = view.findViewById(R.id.ivStretch);

        ivLogo.setImageResource(R.drawable.fittracklogo);
        ivWeight.setImageResource(R.drawable.weight);
        ivYoga.setImageResource(R.drawable.yoga);
        ivCardio.setImageResource(R.drawable.cardio);
        ivStretch.setImageResource(R.drawable.stretching);




        clWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goWeightDetail();
            }
        });

        clYoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goYogaDetail();
            }
        });

        clCardio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goCardioDetail();
            }
        });

        clStretch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goStretchDetail();
            }
        });
    }

    private void goWeightDetail() {

        Intent i = new Intent(getActivity(), WeightDetail.class);
        startActivity(i);

    }

    private void goYogaDetail() {

        Intent i = new Intent(getActivity(), YogaDetail.class);
        startActivity(i);

    }

    private void goCardioDetail() {

        Intent i = new Intent(getActivity(), CardioDetail.class);
        startActivity(i);

    }

    private void goStretchDetail() {

        Intent i = new Intent(getActivity(), StretchDetail.class);
        startActivity(i);

    }
}