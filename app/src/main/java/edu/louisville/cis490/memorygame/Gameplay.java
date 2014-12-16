package edu.louisville.cis490.memorygame;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Admin on 12/12/2014.
 */
public class Gameplay
{
    public static class EasyMode
    {
        public Integer[] imagelist;
        public Integer ID;

        public EasyMode(Integer[] thumbnails)
        {
            imagelist = randomize(thumbnails);
        }

        //get the position ID
        public EasyMode(Integer position)
        {

        }

        public EasyMode()
        {

        }

        //Given an android view, randomize the pictures
        public Integer [] randomize(Integer[] rand)
        {

            Integer [] newRandom = new Integer[rand.length];

            ArrayList<Integer> intList = new ArrayList<Integer>();


            for(int x = 0; x < newRandom.length; x++)
            {
                if(intList.size() == rand.length) {
                    x = rand.length;
                    continue;
                }

                intList.add(rand[x]);
                intList.add(rand[x]);
            }

            Collections.shuffle(intList);

            for(int y = 0; y < newRandom.length; y++)
            {
                newRandom[y] = intList.get(y);
            }

            return newRandom;
        }
    }
}

