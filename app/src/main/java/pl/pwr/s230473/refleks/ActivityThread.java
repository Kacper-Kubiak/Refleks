package pl.pwr.s230473.refleks;

import android.os.Handler;

public class ActivityThread implements Runnable {

    Kolo kolo;
    Handler handler;
    int players;

    public ActivityThread(int p, Kolo k, Handler h) {
        kolo = k;
        handler = h;
        players = p;
    }

    @Override
    public void run() {
        try
        {
            Thread.sleep(1000);
            kolo.setCenterPos();
            kolo.setPlayers(players);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true)
        {
            try {
                Thread.sleep(100);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        kolo.invalidate();
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
