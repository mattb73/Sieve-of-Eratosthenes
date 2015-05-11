package ucsb.mattbao.sieveoferatosthenes;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;


public class DisplayPrimeNumberActivity extends ActionBarActivity {

    private PrimeGenerator mPrimeGenerator;

    private TextView limitDisplay;
    private ListView primeList;
    private GridView primeGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_prime_list);
        limitDisplay = (TextView) findViewById(R.id.display_upperLimit);
        primeList = (ListView) findViewById(R.id.prime_list);
        primeList.setDivider(null);
        primeGrid = (GridView) findViewById(R.id.prime_grid);


        Intent intent = getIntent();
        if(intent.hasExtra("upperLimit")){
            int upperLimit = intent.getIntExtra("upperLimit",0);
            limitDisplay.setText(String.valueOf(upperLimit));
            mPrimeGenerator = new PrimeGenerator(upperLimit);

            ArrayAdapter<Integer> mListAdapter = new ArrayAdapter<Integer>(getBaseContext(),
                                                                      R.layout.prime_list_model,
                                                                      R.id.prime_number,
                                                                      mPrimeGenerator.getPrimeList());

            primeList.setAdapter(mListAdapter);

            PrimeGridAdapter mGridAdapter = new PrimeGridAdapter(getBaseContext(),
                                                                 mPrimeGenerator.getNumberList());
            primeGrid.setAdapter(mGridAdapter);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_prime_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_list_view:
                primeList.setVisibility(View.VISIBLE);
                primeGrid.setVisibility(View.GONE);
                return true;
            case R.id.action_grid_view:
                primeList.setVisibility(View.GONE);
                primeGrid.setVisibility(View.VISIBLE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
