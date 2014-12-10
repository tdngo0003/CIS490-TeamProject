package edu.louisville.cis490.memorygame;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Tuan Ngo on 12/8/2014.
 */
public  class HardFragment extends Fragment {

    public HardFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hard, container, false);
        return rootView;
    }
}