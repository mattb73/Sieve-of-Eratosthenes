package ucsb.mattbao.sieveoferatosthenes;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class DisplayPrimeListActivity extends ActionBarActivity {

    private PrimeGenerator mPrimeGenerator;

    private ListView primeList;
    private TextView limitDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_prime_list);
        primeList = (ListView) findViewById(R.id.prime_list);
        limitDisplay = (TextView) findViewById(R.id.display_upperLimit);

        Intent intent = getIntent();
        if(intent.hasExtra("upperLimit")){
            int upperLimit = intent.getIntExtra("upperLimit",0);
            limitDisplay.setText(String.valueOf(upperLimit));
            mPrimeGenerator = new PrimeGenerator(intent.getIntExtra("upperLimit",0));
            ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(getBaseContext(),
                                                                      R.layout.prime_list_model,
                                                                      R.id.prime_number,
                                                                      mPrimeGenerator.getPrimeList());
            primeList.setAdapter(adapter);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_prime_list, menu);
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
