package c.mars.alarmsex;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends ActionBarActivity {

    private static final int RC = 1;
    private static final String A = "A";
    @Bind(R.id.t)
    TextView t;
    private InReceiver receiver = new InReceiver();

    @OnClick(R.id.b)
    void b() {
//        sendBroadcast(new Intent(A));
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(A);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, RC, intent, 0);
        alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 5000, pendingIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        registerReceiver(receiver, new IntentFilter(A));
    }

    @Override
    protected void onPause() {
        unregisterReceiver(receiver);
        super.onPause();
    }

    class InReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            t.setText("received:" + intent.getAction());
        }
    }
}
