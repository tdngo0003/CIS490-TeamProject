package edu.louisville.cis490.memorygame;

import android.content.Context;
import android.util.Log;

/**
 * Created by Tuan Ngo on 12/8/2014.
 */
public class SHide implements Runnable {
    private Context context;
    private int[] position;
    private ImageAdapter adapter;

    public SHide(Context c, ImageAdapter b, int[] pos){
        context = c;
        adapter = b;
        position = pos;

        Log.d("SHide", "click!");
        adapter.removeClick(pos[0]);
        adapter.removeClick(pos[1]);
    }

    public void run(){
        Log.d("SHide", "run!");
        adapter.hide(position[0]);
        adapter.hide(position[1]);
        adapter.installClick(position[0]);
        adapter.installClick(position[1]);
    }
}
