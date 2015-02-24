package edu.washington.grassela.arewethereyet;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

    private PendingIntent pendingIntent;
    private AlarmManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Retrieve a PendingIntent that will perform a broadcast
        final Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        //alarmIntent.putExtra("message", "yest");
        //pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);

        final Button start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (start.getText().equals("Start")) { // starting messages
                    String message = getMessage();
                    String phoneNumber = getPhone();
                    String minutes = ((EditText) findViewById(R.id.minutes)).getEditableText().toString();
                    int minutesApart;
                    try {
                        minutesApart = Integer.parseInt(minutes);
                    } catch (NumberFormatException nfe) {
                        minutesApart = 0;
                    }
                    if (minutesApart > 0 && !message.isEmpty() && !phoneNumber.isEmpty()
                            && phoneNumber.matches("[(]\\d{3}[)][ ]\\d{3}-\\d{4}")) {
                        alarmIntent.putExtra("message", message);
                        alarmIntent.putExtra("number", phoneNumber);
                        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, 0);
                        start.setText(R.string.stop);
                        startAlarm(minutesApart);

                    }
                } else { // stopping messages
                    start.setText(R.string.start);
                    cancelAlarm();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startAlarm(int minutesApart) {
        manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        long interval = minutesApart * 60 * 1000;

        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
    }

    public void cancelAlarm() {
        if (manager != null) {
            manager.cancel(pendingIntent);
        }

    }

    public String getMessage() {
        return ((EditText) findViewById(R.id.message)).getEditableText().toString();
    }

    public String getPhone() {
        return ((EditText) findViewById(R.id.phoneNumber)).getEditableText().toString();
    }

}
