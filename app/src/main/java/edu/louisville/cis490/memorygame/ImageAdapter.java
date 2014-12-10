package edu.louisville.cis490.memorygame;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.LogRecord;

/**
 * Created by Tuan Ngo on 12/7/2014.
 */
public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private Integer[] pImages;
    private List imageViews;
    private int piece_up = -1;

    public ImageAdapter(View.OnClickListener c) {
        mContext = (Context) c;
        List IpImages = new ArrayList();
        for(int i=0; i<12; i++){
            IpImages.add(i);
            IpImages.add(i);
        }
        Collections.shuffle(IpImages);
        pImages = (Integer[]) IpImages.toArray(new Integer[0]);
        _createImageView();
    }

    private void _createImageView() {
        imageViews = new ArrayList();
        for (int position = 0; position <getCount(); position++){
            ImageView imageView;
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(60, 80));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageViews.add(imageView);
            installClick(position);

        }
    }

    public int getCount() { return 24;}

    public Object getItem(int position) { return imageViews.get(position);}

    public long getItemId(int position) { return pImages[position].longValue();}

    public synchronized View getView(int position, View convertView, ViewGroup parent){
        return (ImageView) imageViews.get(position);
    }

    public void installClick(final int position) {
        final ImageAdapter self = this;
        Log.d("ImageAdapter", "click *" + Integer.toString(position));
        final ImageView imageView = (ImageView) imageViews.get(position);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = imageViews.indexOf((v));
                Log.d("ImageAdapter", "click!" + Integer.toString(pos));
                if (piece_up == -1 || piece_up == pos){
                    Toast.makeText(mContext, "good!", Toast.LENGTH_SHORT).show();
                    removeClick(pos);
                    removeClick(piece_up);
                }
                else
                {
                    int aux[] = {piece_up, pos};
                    SHide update = new SHide(mContext, self, aux);
                    final Handler mHandler = new Handler() {

                        public void close() {

                        }


                        public void flush() {

                        }


                        public void publish(LogRecord record) {

                        }
                    };
                    mHandler.postDelayed(update, 2000);

                }
                piece_up = -1;
            }
        });
    }

    public void removeClick(int position){

        ImageView aux;
        aux = (ImageView) imageViews.get(position);
        aux.setOnClickListener(null);
    }

    public void hide(int position){
       ImageView img;
        img = (ImageView) imageViews.get(position);
        int image = pImages[position];
        img.setImageResource(R.drawable.ic_launcher);
    }

    public void show(int position)
    {
        ImageView img;
        img = (ImageView) imageViews.get(position);
        int pImage = pImages[position];
        img.setImageResource(mThumbIds[pImage]);
    }

    private Integer[] mThumbIds =
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
}
