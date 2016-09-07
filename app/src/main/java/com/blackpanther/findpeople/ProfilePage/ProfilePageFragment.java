package com.blackpanther.findpeople.ProfilePage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blackpanther.findpeople.R;

/**
 * Created by ubuntu on 7/9/16.
 */
public class ProfilePageFragment extends Fragment {
    public ProfilePageFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profile_page_fragment_layout,container,false);
        return v;
    }
}
