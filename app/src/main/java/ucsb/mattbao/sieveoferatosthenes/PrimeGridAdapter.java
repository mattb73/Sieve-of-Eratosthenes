package ucsb.mattbao.sieveoferatosthenes;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bo "Matt" Bao on 4/24/2015.
 * Custom adapter to display grid of numbers, highlighting the prime numbers.
 */
public class PrimeGridAdapter extends BaseAdapter{

    private Context context;
    private boolean[] numberList;

    public PrimeGridAdapter(Context context, boolean[] numberList){
        this.context = context;
        this.numberList = numberList;
    }

    @Override
    public int getCount() {
        return numberList.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView number;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.prime_grid_model, parent, false);
        }
        number = (TextView) convertView.findViewById(R.id.grid_number);
        number.setText(String.valueOf(position));
        if(!numberList[position]){ // If prime number, highlight background
            //number.setBackgroundColor(Color.rgb(255, 148, 148));
            number.setBackgroundResource(R.drawable.button_background);
            number.setTextColor(Color.WHITE);
        } else {
            number.setBackgroundColor(Color.TRANSPARENT);
            number.setTextColor(Color.BLACK);
        }
        return convertView;
    }
}
