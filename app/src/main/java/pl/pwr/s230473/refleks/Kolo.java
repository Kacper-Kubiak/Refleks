package pl.pwr.s230473.refleks;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class Kolo extends View {

    private Paint p;
    int pos_x = 0;
    int pos_y = 0;
    private final static int koloSize = 400; // stały rozmiar naszej kulki
    RectF rectF; //zmienna pomocnicza do rysowania pola
    int start = 0; // ustawiamy poczatek rysowania obszaru zamknietego
    int sweep = 360; // i ile stopni ma ten obszar mieć. Im więcej tym większy obszar zamkniety
    // musi być mniejszy niż 360 stopni. jeżeli damy 340 to oznacza, że obszar do wygranej to tylko 20 stopni bo 360-340 = 20
    int players = 2;
    int max_players = 6;
    int[] playercolor = new int[max_players];

    public Kolo(Context context, AttributeSet attrs) {
        super(context, attrs);
        p = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    // Wyśrodkowanie pola gry na środek ekrany
    public void setCenterPos() {
        pos_x = getWidth()/2; // pobieramy szerokość ekrany dzielimy przez 2 czyli mamy środek i tam  ustawiamy nasza kulke
        pos_y = getHeight()/2;// pobieramy szerokość ekrany dzielimy przez 2 czyli mamy środek i tam  ustawiamy nasza kulke
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rectF = new RectF(pos_x-koloSize/2, pos_y-koloSize/2, koloSize/2+pos_x, koloSize/2+pos_y);
        p.setStrokeWidth(10);
        p.setStyle(Paint.Style.FILL);
        for(int i=0; i<=players; i++) {
            p.setColor(playercolor[i]);
            canvas.drawArc(rectF, start, sweep/players, true, p);
            start += sweep/players;
            if(start > 360) start = 0;
        }
    }

    public void setPlayers(int players) {
        this.players = players;
        Random rnd = new Random();
        for(int i=0; i<=players; i++) {
            playercolor[i] = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        }
    }
}
