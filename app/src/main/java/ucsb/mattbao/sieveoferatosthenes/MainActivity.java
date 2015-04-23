package ucsb.mattbao.sieveoferatosthenes;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;


public class MainActivity extends ActionBarActivity {
    private static int upperLimit;

    private Button goButton;
    private EditText upperLimitInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton = (Button) findViewById(R.id.main_button);
        upperLimitInput = (EditText) findViewById(R.id.input_upper_limit);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(upperLimitInput.getText().length()>0){ // If input is not empty
                    upperLimit = Integer.valueOf(upperLimitInput.getText().toString());
                    Intent intent = new Intent(getBaseContext(), DisplayPrimeListActivity.class);
                    intent.putExtra("upperLimit", upperLimit);
                    startActivity(intent);
                } else { // Displays alert dialog if input is empty
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle(R.string.alert_title).setMessage(R.string.alert_message);
                    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Automatically dismisses alert dialog
                        }
                    });
                    builder.setNeutralButton(R.string.generate_random, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Random random = new Random();
                            //Set input to be random number between 2 and 1000002
                            upperLimitInput.setText(String.valueOf(random.nextInt(1000000)+2));
                            dialog.dismiss();
                        }
                    });
                    builder.create().show();
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
