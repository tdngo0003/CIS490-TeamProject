package edu.louisville.cis490.memorygame;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

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
        GridView gridview = (GridView) rootView.findViewById(R.id.easy_mode);
        gridview.setAdapter(new ImageAdapter(getActivity()));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {


            }
        });

        return rootView;


    }
    public class ImageAdapter extends BaseAdapter {

        private Context mContext;
        private Integer gridsize = 16;
        public int score = 0;
        
        private ArrayList<Integer> Save = new ArrayList<>();
        private ArrayList<ImageView> view1 = new ArrayList<>();


        public Integer[] mThumbIds =
                {
                        R.drawable.abra,
                        R.drawable.aurorus,
                        R.drawable.bellossom,
                        R.drawable.chikorita,
                        R.drawable.chingling,
                        R.drawable.eevee,
                        R.drawable.deerling,
                        R.drawable.growlith,
                        R.drawable.jigglypuff,
                        R.drawable.ledian,
                        R.drawable.lillipup,
                        R.drawable.marill,
                        R.drawable.mienfoo,
                        R.drawable.pancham,
                        R.drawable.pichu,
                        R.drawable.shaymin,
                        R.drawable.skiddo,
                        R.drawable.togetic
                };
        //Constructor
        public ImageAdapter(Context c)
        {
            mContext = c;
            mThumbIds = java.util.Arrays.copyOf(mThumbIds, gridsize);
            Gameplay.EasyMode easyMode = new Gameplay.EasyMode(mThumbIds);
            mThumbIds = easyMode.imagelist;
        }

        public int getCount() {
            //Set the number of element we want on the grid

//        Gameplay.EasyMode easyMode = new Gameplay.EasyMode(16);

            return gridsize;}

        public Object getItem(int position) {
            return mThumbIds[position];
        }
        public long getItemId(int position) {
            return 0;
        }

        public View getView(final int position, final View convertView, final ViewGroup parent) {

            final ImageView imageView;

            if (convertView == null) {
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }

            final GameCountDownTimer initDisplay = new GameCountDownTimer(5000, 1000)
            {
                public void onTick(long millisUntilFinished){

                    imageView.setImageResource(mThumbIds[position]);
                }
                public void onFinish(){
                    imageView.setImageResource(R.drawable.ic_launcher);
                }
            };
            initDisplay.start();

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(getActivity().getApplicationContext(), getResources().getResourceEntryName(mThumbIds[position]), Toast.LENGTH_SHORT).show();
                    final ImageView view = (ImageView) v;
                    view.setImageResource(mThumbIds[position]);

                    if (Save.size() >= 0) {

                        Save.add(mThumbIds[position]);
                        view1.add(view);
                    }

                    if (Save.size() == 2) {

                        view.setImageResource(mThumbIds[position]);


                        if (Save.get(0) != Save.get(1)) {

                            view.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    view.setImageResource(R.drawable.ic_launcher);


                                }
                            }, 500);
                            view1.get(0).setImageResource(R.drawable.ic_launcher);
                            view.setImageResource(mThumbIds[position]);


                            Save.clear();
                            view1.clear();

                        } else {

                            view.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    view.setImageResource(R.drawable.abc_ab_share_pack_holo_dark);
                                }
                            }, 500);

                            view1.get(0).setImageResource(R.drawable.abc_ab_share_pack_holo_dark);
                            view.setImageResource(mThumbIds[position]);

                            Save.clear();
                            view1.clear();

                            view.setImageResource(mThumbIds[position]);

                            Toast.makeText(getActivity(), "Good Job!!!", Toast.LENGTH_SHORT);
                            score++;
                            TextView gameScore = (TextView) getActivity().findViewById(R.id.txt_score);
                            gameScore.setText("Score: " + score);



                        }


                        boolean exit = (score == 8);

                        if (exit) {



                        }

                    }


                }

            });

            imageView.setImageResource(R.drawable.ic_launcher);

            return imageView;
            }

        }
    }







