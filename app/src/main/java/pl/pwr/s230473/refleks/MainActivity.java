package pl.pwr.s230473.refleks;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Kolo kolo;
    private ActivityThread activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //Wymuszenie trybu portretowego bez możliwości zmiany
        setContentView(R.layout.activity_main);

        kolo = findViewById(R.id.kolo);
        kolo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Przycisk", "tak");
            }
        });



        //Odczyt parametru
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        int players = sharedPref.getInt(getString(R.string.ilosc_graczy), 2);
        if(players < 2) players = 2;
        if(players > 6) players = 6;

        //zapis parametru
        /*SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.saved_high_score_key), 11);
        editor.commit();*/

        players = 3;

        activity = new ActivityThread(players, kolo, new Handler());
        Thread thread = new Thread(activity);
        thread.start();
    }
}
