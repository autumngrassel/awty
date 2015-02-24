package edu.washington.grassela.arewethereyet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context ctxt, Intent intent) {
        String message = intent.getStringExtra("number") + ": " + intent.getStringExtra("message");
        // For our recurring task, we'll just display a message
        Toast.makeText(ctxt, message, Toast.LENGTH_SHORT).show();

    }

}