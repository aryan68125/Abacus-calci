package com.aditya.scientificcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class TimesTableActivity extends AppCompatActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times_table);
        toolbar = findViewById(R.id.toolbarTimesTable);
        setSupportActionBar(toolbar);
        //calling get support action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        final ListView listView = (ListView) findViewById(R.id.listView);
        TextView textView2 = (TextView) findViewById(R.id.textViewTimesTables);
        int max = 20;
        seekBar.setMax(max); //setting up the max value of the seekBar
        int startingPosition = 1;
        seekBar.setProgress(startingPosition);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("seekbar", Integer.toString(progress));
                int min = 1;
                int timestablenumber;
                if(progress<min)
                {
                    timestablenumber =min;
                    seekBar.setProgress(min); //it will ensure that the value of seekBar never gets to zero
                    //so the seekBar will never go all the way to zero
                }
                else
                {
                    timestablenumber = progress;
                }
                Log.i("progress", Integer.toString(timestablenumber));

                //Logic for multiplication and arrayList comes below
                ArrayList<String> result = new ArrayList<String>(); //array of numbers after multiplication that will be shown in the ListView
                /*
                ArrayList numbers is an array that stores the results after multiplication
                the for loop below is used to calculate the times table and store the result inside the array that we just created above
                 */
                int j=1;
                for(int i=1;i<21;i++) //for loop to add the results inside the arrayList result
                {
                    /*
                    Integer.toString(i * timestablenumber) converts the int to string
                    numbers.add(the content here should be string) it will not accept anything else
                    numbers.add(Integer.toString(i * timestablenumber)); will add the result inside the array numbers
                     */
                    result.add(Integer.toString(timestablenumber) + " x " + Integer.toString(j++) + " = " + Integer.toString(i * timestablenumber));  //this is the method to add something inside an array using seekBar
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.row, result);
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}