package edu.louisville.cis490.memorygame;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Tuan Ngo on 12/8/2014.
 */
public class EasyFragment extends Fragment {

    public EasyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_easy, container, false);
        return rootView;
    }
}
