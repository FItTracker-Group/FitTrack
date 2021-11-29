package com.codepath.fittrack.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.codepath.fittrack.ComposeActivity;
import com.codepath.fittrack.Post;
import com.codepath.fittrack.PostsAdapter;
import com.codepath.fittrack.R;
import com.codepath.fittrack.WeightDetail;
import com.codepath.fittrack.YogaDetail;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FeedFragment extends Fragment {

    public static final String TAG = "FeedFragment";
    private final int REQUEST_CODE = 20;
    private RecyclerView rvPosts;
    private SwipeRefreshLayout swipeContainer;
    protected PostsAdapter adapter;
    protected List<Post> allPosts;

    public FeedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ParseUser currentUser = ParseUser.getCurrentUser();
        System.out.println("UserName =" + currentUser.getUsername());
        System.out.println("UserName =" + currentUser.getEmail());

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(null);

        rvPosts = view.findViewById(R.id.rvPosts);
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);

        //Create the menu
        setHasOptionsMenu(true);

        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(TAG, "fetching new data");
                fetchMoreData(0);
                swipeContainer.setRefreshing(false);
            }
        });

        //Steps to use the recycler view;
        //0. create layout for one row in the list
        //1.create the adapter
        //2.create the data source
        //3.set the adapter on the recycler view
        //4.set the layout manager on the recycler view

        allPosts = new ArrayList<>();
        adapter = new PostsAdapter(getContext(), allPosts);
        rvPosts.setAdapter(adapter);
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        queryPosts();
    }


    private void fetchMoreData(int i) {
        adapter.clear();
        queryPosts();
    }

    protected void queryPosts() {
        // Specify which class to query
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        //include the for querying other database we want to include User
        query.include(Post.KEY_USER);
        query.setLimit(30);
        query.addDescendingOrder(Post.KEY_CREATED_KEY);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e != null){
                    Log.e(TAG, "Issue with getting Posts", e);
                }
                for(Post post : posts){
                    Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                }
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }
    
    /*----- Overhead Menu Bar -----*/

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.feed_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_post) {
            Intent intent = new Intent(getActivity(), ComposeActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}