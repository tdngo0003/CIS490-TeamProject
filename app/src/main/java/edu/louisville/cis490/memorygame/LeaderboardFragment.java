package edu.louisville.cis490.memorygame;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

/**
 * Created by Tuan Ngo on 12/6/2014.
 */
public  class LeaderboardFragment extends Fragment {

    public LeaderboardFragment() {
    }

    TableLayout table_layout;
    TextView username, score;

    SQLController sqlcon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_leaderboard, container, false);

        table_layout = (TableLayout) getView().findViewById(R.id.leader_table);

        BuildTable();

        return rootView;
    }

    private void BuildTable() {
        Cursor c = sqlcon.getAllScores();
        int rows = c.getCount();
        int cols = c.getColumnCount();
        c.moveToFirst();

        for (int i = 0; i < rows; i++) {

            TableRow row;
            row = new TableRow(getActivity());
            row.setLayoutParams(new TableRow.LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));

            // inner for loop
            for (int j = 0; j < cols; j++) {

                TextView tv;
                tv = new TextView(getActivity());
                tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT));
                tv.setBackgroundResource(R.drawable.cell_shape);
                tv.setGravity(Gravity.CENTER);
                tv.setTextSize(18);
                tv.setPadding(0, 5, 0, 5);

                tv.setText(c.getString(j));

                row.addView(tv);
            }
            c.moveToNext();
            table_layout.addView(row);
        }
        sqlcon.close();
    }

}