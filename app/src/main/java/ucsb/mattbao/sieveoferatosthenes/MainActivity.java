package ucsb.mattbao.sieveoferatosthenes;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.Random;


public class MainActivity extends ActionBarActivity {
    private static int upperLimit;

    private Button goButton;
    private Button generateRandomButton;
    private ImageButton clearButtonX;
    private EditText upperLimitInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton = (Button) findViewById(R.id.main_button);
        generateRandomButton = (Button) findViewById(R.id.generate_random_button);
        upperLimitInput = (EditText) findViewById(R.id.input_upper_limit);
        clearButtonX = (ImageButton) findViewById(R.id.clear_button_x);

        //The following is for toggling the visibility of the clear-input image button on
        //the right side of the input box: when not empty, display the button which will
        //clear the contents when clicked.
        upperLimitInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(upperLimitInput.getText().length()>0){
                    clearButtonX.setVisibility(View.VISIBLE);
                } else {
                    clearButtonX.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Performs a series of input validity checks before starting DisplayPrimeListActivity.
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(upperLimitInput.getText().length() > 0){ // If input is not empty
                    // First (roughly) check if number is over integer limit
                    if(upperLimitInput.getText().length() > 9){
                        overLimitAlert();
                    }else{
                        upperLimit = Integer.valueOf(upperLimitInput.getText().toString());
                        // Check upperLimit to make sure it's not over 15,000,000 (will result in "out
                        // of memory" error if over) or less than 2
                        if(upperLimit > 15000000 ||upperLimit < 2){
                            overLimitAlert();
                        } else {
                            Intent intent = new Intent(getBaseContext(), DisplayPrimeNumberActivity.class);
                            intent.putExtra("upperLimit", upperLimit);
                            startActivity(intent);
                        }
                    }
                } else { // If input is empty display an alert
                    emptyInputAlert();
                }
            }
        });

        //Generates a random number as the limit for user.
        generateRandomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                //Set input to be random number between 2 and 1002
                upperLimitInput.setText(String.valueOf(random.nextInt(1000)+2));
            }
        });

        //Clears the input box.
        clearButtonX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upperLimitInput.setText("");
            }
        });

    }

    // Displays alert if user input is too high (over 15,000,000) or too low (less than 2)
    private void overLimitAlert(){
        OverLimitAlertFragment overLimitAlert = new OverLimitAlertFragment();
        overLimitAlert.setAlertButtonListener(new OverLimitAlertFragment.AlertButtonListener() {
            @Override
            public void onPositiveClick() {
                upperLimitInput.setText(""); // Clears input
            }
        });
        overLimitAlert.show(getSupportFragmentManager(),"Over Limit Alert");
    }

    // Displays alert if user input is empty
    private void emptyInputAlert(){
        EmptyInputAlertFragment emptyAlert = new EmptyInputAlertFragment();
        emptyAlert.show(getSupportFragmentManager(), "Empty Input Alert");
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
