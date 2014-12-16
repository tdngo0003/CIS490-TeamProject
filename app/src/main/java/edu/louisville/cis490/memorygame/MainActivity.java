package edu.louisville.cis490.memorygame;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends FragmentActivity implements View.OnClickListener{

    DatabaseHandler db = new DatabaseHandler(this);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }


        findViewById(R.id.btn_new_game).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new EasyFragment())
                        .commit();

                GameCountDownTimer countDownTimer1 = new GameCountDownTimer(45000, 1000)
                {
                    public void onTick(long millisUntilFinished){
                        TextView timeRemainView = (TextView) findViewById(R.id.timeRemain);
                        timeRemainView.setText("Time Remain: " + millisUntilFinished/1000);
                    }
                    public void onFinish(){
                        TextView gameFinish = (TextView) findViewById(R.id.timeRemain);
                        gameFinish.setText("Time Up!!!");
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                        alertDialogBuilder.setTitle("Game Over");
//                        alertDialogBuilder.setMessage("Score: " + fragment.getScore().toString());
                        alertDialogBuilder.setCancelable(false);
                        alertDialogBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id) {
                            Intent done = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(done);
                            dialog.dismiss();

                            }

                        });
                        alertDialogBuilder.show();
                    }
                };

                countDownTimer1.start();


            }


        });

        findViewById(R.id.btn_leader_board).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               getFragmentManager().beginTransaction()
               .replace(R.id.container, new LeaderboardFragment())
               .commit();
            }
         });
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            listener.onClick(v);
            onClick(v);
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Button new_game = (Button) findViewById(R.id.btn_new_game);
        Button leader_board = (Button) findViewById(R.id.btn_leader_board);

        if(v.equals(new_game)) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, new EasyFragment())
                    .commit();


        }
        else if(v.equals(leader_board)){
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, new LeaderboardFragment())
                    .commit();
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);


            return rootView;
        }

        public void Onclick(View v)
        {

        }
    }
}
