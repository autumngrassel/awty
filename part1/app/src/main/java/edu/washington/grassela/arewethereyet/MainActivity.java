package edu.washington.grassela.arewethereyet;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (start.getText().equals("Start")) { // starting messages
                    String message = ((EditText) findViewById(R.id.message)).getEditableText().toString();
                    String phoneNumber = ((EditText) findViewById(R.id.phoneNumber)).getEditableText().toString();
                    String minutes = ((EditText) findViewById(R.id.minutes)).getEditableText().toString();
                    int minutesApart;
                    try {
                        minutesApart = Integer.parseInt(minutes);
                    } catch (NumberFormatException nfe) {
                        minutesApart = 0;
                    }
                    if (minutesApart > 0 && !message.isEmpty() && !phoneNumber.isEmpty()) {
                        start.setText(R.string.stop);

                    }
                } else { // stopping messages

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
}
