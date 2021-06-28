package com.aditya.scientificcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class TemperatureConverter extends AppCompatActivity {

    EditText editText;
    Spinner spinnerinput;
    Spinner spinneroutput;
    String Tag = "TemperatureConverter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_converter);

        Toolbar toolbar = findViewById(R.id.toolbarTemperature);
        setSupportActionBar(toolbar);
        //calling get support action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.i(Tag, "--on create--");
        editText = findViewById(R.id.Input11);
        editText.setShowSoftInputOnFocus(false); //this will disable the keyboard pop up of the device
        spinnerinput = findViewById(R.id.inputspinner12);
        spinneroutput = findViewById(R.id.outputspinner11);

        //Setting up the spinner item array that will display the items inside the spinner
        String[] input = {"Celsius", "Fahrenheit", "Kelvin"}; //for input spinner
        ArrayAdapter in = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, input);
        spinnerinput.setAdapter(in);

        String[] output = {"Celsius", "Fahrenheit", "Kelvin"}; //for output spinner
        ArrayAdapter out = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, output);
        spinneroutput.setAdapter(out);
    }

    // this event will enable the back activity action bar back button
    // function to the button on press activity action bar back button
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                overridePendingTransition(R.anim.nothing, R.anim.slide_out_right);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //exit animation of currently active activity
    @Override
    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    /*
                create a method updateEditText to update the text in the editText whenever we press a button in our application
                strToAdd = is a new string or text entered by the user that has to be added in the editText
                */
    private void updateEditText(String strToAdd) {
        String oldString = editText.getText().toString(); //this will grab any text that is previously entered by the user
        /*
        we need the position if the cursor because we want to enter the digit where the cursor is and not at the end of the String
        example = jkka|scbs -> jkka|0scbs zero is inserted in the place where cursor is and not at the end of the string
        so we had to split the string into two inorder to do that
        so we are gonna grab the cursor position and split the oldString into two different String
        editText.getSelectionStart(); will give you the cursor position in the editText field
        */
        int cursorPosition = editText.getSelectionStart();
        String leftOldString = oldString.substring(0, cursorPosition);
        String rightOldString = oldString.substring(cursorPosition); //this will create a subtring from cursor position to all the way to the end

        /*
        no we will check if the default hint is there in the editText or not
        if yes then set it to string to add
        if no then set it to editText.setText(String.format("%s%s%s",leftOldString,strToAdd,rightOldString));
         */
        if (editText.getText().toString() == "Input"|| editText.getText().toString() =="Input Field is Empty"|| editText.getText().toString() == "Error 2X00f_00#You!") {
            editText.setText(""); //clear the input field is the hint input is there when the user taps on it
        }
        else{
            //now we are going to update the editText with this new String
        /*
        editText.setText(String.format("%s%s%s",leftOldString,strToAdd,rightOldString)); will update the String in our edit Text
        according to our cursor's position
        String.format("%s%s%s",leftOldString,strToAdd,rightOldString) = tells the editText in which format it has to show the Strings
        first we have to print the leftString then we will print the String that we want to add at the cursor's position and then we will
        print the right string
         */
            editText.setText(String.format("%s%s%s", leftOldString, strToAdd, rightOldString));

        /*
        right now whenever we are entering string to our edit Text our cursor jumps to the left most position in EditText field
        so we need to fix that by fixing the position of our cursor in the EditText field
        editText.setSelection(cursorPosition+1); will fix the issue of cursor position in our editText field
         */
            editText.setSelection(cursorPosition + 1);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(Tag, "--on Restart--");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(Tag, "--on Start--");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(Tag, "--on Resume--");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(Tag, "--on Pause--");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(Tag, "--on Stop--");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(Tag, "--on Destroy--");
    }

    public void backspaceButton(View view) {
        //this will delete a digit from the entered number
        /*
        in order to that we need to get the cursor's position and grab the length of the string in the editText field
        we need to get the length of the String so that we can prevent the user from trying to backspace when there is nothing in the
        editText field
        editText.getText().length(); = will give us the length of the text that is being entered in the editText field
         */
        int cursorPosition = editText.getSelectionStart();
        int textLength = editText.getText().length();
        if(textLength!=0 && cursorPosition!=0){
            /*
            spannableString will allow us to replace different characters within our string
             */
            SpannableStringBuilder selection = (SpannableStringBuilder) editText.getText();
            selection.replace(cursorPosition-1,cursorPosition,""); //replacing the character with an empty string
            editText.setText(selection); //updating the text in the editText field
            editText.setSelection(cursorPosition-1); //update the cursor's position
        }
    }

    public void sevenButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        if (editText.getText().toString().equals("Input Field is Empty")|| editText.getText().toString().equals("Error 2X00f_00#You!")) {
            editText.setText(""); //clear the input field is the hint input is there when the user taps on it
        }
        updateEditText("7");
    }

    public void eightButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        if (editText.getText().toString().equals("Input Field is Empty")|| editText.getText().toString().equals("Error 2X00f_00#You!")) {
            editText.setText(""); //clear the input field is the hint input is there when the user taps on it
        }
        updateEditText("8");
    }

    public void nineButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        if (editText.getText().toString().equals("Input Field is Empty")|| editText.getText().toString().equals("Error 2X00f_00#You!")) {
            editText.setText(""); //clear the input field is the hint input is there when the user taps on it
        }
        updateEditText("9");
    }

    public void fourButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        if (editText.getText().toString().equals("Input Field is Empty")|| editText.getText().toString().equals("Error 2X00f_00#You!")) {
            editText.setText(""); //clear the input field is the hint input is there when the user taps on it
        }
        updateEditText("4");
    }

    public void fiveButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        if (editText.getText().toString().equals("Input Field is Empty")|| editText.getText().toString().equals("Error 2X00f_00#You!")) {
            editText.setText(""); //clear the input field is the hint input is there when the user taps on it
        }
        updateEditText("5");
    }

    public void sixButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        if (editText.getText().toString().equals("Input Field is Empty")|| editText.getText().toString().equals("Error 2X00f_00#You!")) {
            editText.setText(""); //clear the input field is the hint input is there when the user taps on it
        }
        updateEditText("6");
    }

    public void oneButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        if (editText.getText().toString().equals("Input Field is Empty")|| editText.getText().toString().equals("Error 2X00f_00#You!")) {
            editText.setText(""); //clear the input field is the hint input is there when the user taps on it
        }
        updateEditText("1");
    }

    public void twoButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        if (editText.getText().toString().equals("Input Field is Empty")|| editText.getText().toString().equals("Error 2X00f_00#You!")) {
            editText.setText(""); //clear the input field is the hint input is there when the user taps on it
        }
        updateEditText("2");
    }

    public void threeButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        if (editText.getText().toString().equals("Input Field is Empty")|| editText.getText().toString().equals("Error 2X00f_00#You!")) {
            editText.setText(""); //clear the input field is the hint input is there when the user taps on it
        }
        updateEditText("3");
    }

    public void dotButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        if (editText.getText().toString().equals("Input Field is Empty")|| editText.getText().toString().equals("Error 2X00f_00#You!")) {
            editText.setText(""); //clear the input field is the hint input is there when the user taps on it
        }
        updateEditText(".");
    }

    public void zeroButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        if (editText.getText().toString().equals("Input Field is Empty")|| editText.getText().toString().equals("Error 2X00f_00#You!")) {
            editText.setText(""); //clear the input field is the hint input is there when the user taps on it
        }
        updateEditText("0");
    }

    public void MinusButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        if (editText.getText().toString().equals("Input Field is Empty")|| editText.getText().toString().equals("Error 2X00f_00#You!")) {
            editText.setText(""); //clear the input field is the hint input is there when the user taps on it
        }
        updateEditText("-");
    }
    public void equalsButton(View view) {
        try {
            Double result;
            String toasttext;
            Double amount = Double.parseDouble(editText.getText().toString());
            //for celsius
            if (spinnerinput.getSelectedItem() == "Celsius" && spinneroutput.getSelectedItem() == "Celsius") {
                toasttext = "Error 2X00f_00#You!";
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Celsius" && spinneroutput.getSelectedItem() == "Fahrenheit") {
                result = (amount * 9 / 5) + 32;
                toasttext = "Celsius to Fahrenheit = " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Celsius" && spinneroutput.getSelectedItem() == "Kelvin") {
                result = ( amount + 273.15 );

                toasttext = "Celsius to Kelvin = " + result.toString();
                editText.setText(toasttext);
            }
            //for Fahrenheit
            if (spinnerinput.getSelectedItem() == "Fahrenheit" && spinneroutput.getSelectedItem() == "Celsius") {
                result = ((amount-32)*5)/9;
                toasttext = "Fahrenheit to Celsius = " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Fahrenheit" && spinneroutput.getSelectedItem() == "Fahrenheit") {
                toasttext = "Error 2X00f_00#You!";
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Fahrenheit" && spinneroutput.getSelectedItem() == "Kelvin") {
                result = 273.5 + ((amount - 32.0) * (5.0/9.0));
                toasttext = "Fahrenheit to Kelvin = " + result.toString();
                editText.setText(toasttext);
            }
            //for Kelvin
            if (spinnerinput.getSelectedItem() == "Kelvin" && spinneroutput.getSelectedItem() == "Celsius") {
                result = amount - 273;
                toasttext = "Kelvin to Celsius = " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kelvin" && spinneroutput.getSelectedItem() == "Fahrenheit") {
                result = (9.0 / 5) * (amount - 273.15) + 32;;
                toasttext = "Kelvin to Fahrenheit = " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kelvin" && spinneroutput.getSelectedItem() == "Kelvin") {
                toasttext = "Error 2X00f_00#You!";
                editText.setText(toasttext);
            }
        }
        catch (Exception e)
        {
            String messege="Input Field is Empty";
            editText.setText(messege);
        }
                /*
                   we will use try and catch to handel any unexpected errors in our application so that if something gos wrong our application does not crash
                   instead it can still function as normal with an error message like hey this is what went wrong to notify the user
                   try{
                     //the code here which you know might cause problem under a certain scenario
                     }
                     catch (Exception e)
                     {
                     //error message that you want to display to the user
                     }
                 */
    }

    public void ClearButton(View view) {
        editText.setText("");
    }
}