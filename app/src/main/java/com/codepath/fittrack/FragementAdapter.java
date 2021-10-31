package com.codepath.fittrack;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.codepath.fittrack.fragments.LoginFragment;
import com.codepath.fittrack.fragments.RegisterFragment;

public class FragementAdapter extends FragmentStateAdapter {
    public FragementAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 1:
                return new RegisterFragment();
        }

        return new LoginFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
