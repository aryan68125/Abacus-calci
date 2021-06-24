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
import android.widget.EditText;
import android.widget.Spinner;

public class ForceActivity extends AppCompatActivity {

    EditText editText;
    Spinner spinnerinput;
    Spinner spinneroutput;
    String Tag = "PowerConverter";
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_force);

        toolbar = findViewById(R.id.toolbarForceConverter);
        setSupportActionBar(toolbar);
        //calling get support action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Log.i(Tag, "--on create--");
        editText = findViewById(R.id.Input22);
        editText.setShowSoftInputOnFocus(false); //this will disable the keyboard pop up of the device
        spinnerinput = findViewById(R.id.inputspinner22);
        spinneroutput = findViewById(R.id.outputspinner22);

        //setting up spinner item array that will be displayed inside the spinner
        String[] from = {"Newton", "Kilo newton", "Gram-force", "Kilogram-force", "Ton-force", "Giga newton", "Mega newton"};
        ArrayAdapter ad = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, from);
        spinnerinput.setAdapter(ad); //input spinner

        String[] to = {"Newton", "Kilo newton", "Gram-force", "Kilogram-force", "Ton-force", "Giga newton", "Mega newton"};
        ArrayAdapter ad1 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, to);
        spinneroutput.setAdapter(ad1); //output spinner
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
    public void ClearButton(View view)
    {

        editText.setText("");
    }

    public void equalsButton(View view) {
        try {
            Double result;
            String toasttext;
            Double amount = Double.parseDouble(editText.getText().toString());

            //applying conditions for = Newton
            if (spinnerinput.getSelectedItem() == "Newton" && spinneroutput.getSelectedItem() == "Newton") {
                toasttext = "Error 2X00f_00#You!";
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Newton" && spinneroutput.getSelectedItem() == "Kilo newton") {
                result = 0.001 * amount;
                toasttext = "Newton to Kilo newton " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Newton" && spinneroutput.getSelectedItem() == "Gram-force") {
                result = 101.9716213 * amount;
                toasttext = "Newton to Gram-force " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Newton" && spinneroutput.getSelectedItem() == "Kilogram-force") {
                result = 0.1019716213 * amount;
                toasttext = "Newton to Kilogram-force " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Newton" && spinneroutput.getSelectedItem() == "Ton-force") {
                result = 0.0001019716213 * amount;
                toasttext = "Newton to Ton-force " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Newton" && spinneroutput.getSelectedItem() == "Giga newton") {
                result = 1e-9 * amount;
                toasttext = "Newton to Giga newton " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Newton" && spinneroutput.getSelectedItem() == "Mega newton") {
                result = 1e-6 * amount;
                toasttext = "Newton to Mega newton " + result.toString();
                editText.setText(toasttext);
            }

            //applying conditions from = Kilo newton
            if (spinnerinput.getSelectedItem() == "Kilo newton" && spinneroutput.getSelectedItem() == "Newton") {
                result = 1000 * amount;
                toasttext = "Kilo newton to Newton " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilo newton" && spinneroutput.getSelectedItem() == "Kilo newton") {
                toasttext = "Error 2X00f_00#You!";
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilo newton" && spinneroutput.getSelectedItem() == "Gram-force") {
                result = 101971.6213 * amount;
                toasttext = "Kilo newton to Gram-force " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilo newton" && spinneroutput.getSelectedItem() == "Kilogram-force") {
                result = 101.9716213 * amount;
                toasttext = "Kilo newton to Kilogram-force " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilo newton" && spinneroutput.getSelectedItem() == "Ton-force") {
                result = 0.1019716213 * amount;
                toasttext = "Kilo newton to Ton-force " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilo newton" && spinneroutput.getSelectedItem() == "Giga newton") {
                result = 1e-6 * amount;
                toasttext = "Kilo newton to Giga newton " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilo newton" && spinneroutput.getSelectedItem() == "Mega newton") {
                result = 0.001 * amount;
                toasttext = "Kilo newton to Mega newton " + result.toString();
                editText.setText(toasttext);
            }

            //applying conditions from = Gram-force
            if (spinnerinput.getSelectedItem() == "Gram-force" && spinneroutput.getSelectedItem() == "Newton") {
                result = 0.00980665 * amount;
                toasttext = "Gram-force to Newton " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Gram-force" && spinneroutput.getSelectedItem() == "Kilo newton") {
                result = 0.0000098067 * amount;
                toasttext = "Gram-force to Kilo newton " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Gram-force" && spinneroutput.getSelectedItem() == "Gram-force") {
                toasttext = "Error 2X00f_00#You!";
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Gram-force" && spinneroutput.getSelectedItem() == "Kilogram-force") {
                result = 0.001 * amount;
                toasttext = "Gram-force to Kilogram-force " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Gram-force" && spinneroutput.getSelectedItem() == "Ton-force") {
                result = 1e-6 * amount;
                toasttext = "Gram-force to Ton-force " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Gram-force" && spinneroutput.getSelectedItem() == "Giga newton") {
                result = 9.80665e-12 * amount;
                toasttext = "Gram-force to Giga newton " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Gram-force" && spinneroutput.getSelectedItem() == "Mega newton") {
                result = 9.80665e-9 * amount;
                toasttext = "Gram-force to Mega newton " + result.toString();
                editText.setText(toasttext);
            }

            //applying conditions from = Kilogram-force
            if (spinnerinput.getSelectedItem() == "Kilogram-force" && spinneroutput.getSelectedItem() == "Newton") {
                result = 9.80665 * amount;
                toasttext = "Kilogram-force to Newton " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilogram-force" && spinneroutput.getSelectedItem() == "Kilo newton") {
                result = 0.00980665 * amount;
                toasttext = "Kilogram-force to Kilo newton " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilogram-force" && spinneroutput.getSelectedItem() == "Gram-force") {
                result = 1000 * amount;
                toasttext = "Kilogram-force to Gram-force " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilogram-force" && spinneroutput.getSelectedItem() == "Kilogram-force") {
                toasttext = "Error 2X00f_00#You!";
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilogram-force" && spinneroutput.getSelectedItem() == "Ton-force") {
                result = 0.001 * amount;
                toasttext = "Kilogram-force to Ton-force " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilogram-force" && spinneroutput.getSelectedItem() == "Giga newton") {
                result = 9.80665e-9 * amount;
                toasttext = "Kilogram-force to Giga newton " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilogram-force" && spinneroutput.getSelectedItem() == "Mega newton") {
                result = 9.80665e-6 * amount;
                toasttext = "Kilogram-force to Mega newton " + result.toString();
                editText.setText(toasttext);
            }

            //applying conditions from = Ton-force
            if (spinnerinput.getSelectedItem() == "Ton-force" && spinneroutput.getSelectedItem() == "Newton") {
                result = 9806.65 * amount;
                toasttext = "Ton-force to Newton " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Ton-force" && spinneroutput.getSelectedItem() == "Kilo newton") {
                result = 9.80665 * amount;
                toasttext = "Ton-force to Kilo newton " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Ton-force" && spinneroutput.getSelectedItem() == "Gram-force") {
                result = 1e+6 * amount;
                toasttext = "Ton-force to Gram-force " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Ton-force" && spinneroutput.getSelectedItem() == "Kilogram-force") {
                result = 1000 * amount;
                toasttext = "Ton-force to Kilogram-force " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Ton-force" && spinneroutput.getSelectedItem() == "Ton-force") {
                toasttext = "Error 2X00f_00#You!";
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Ton-force" && spinneroutput.getSelectedItem() == "Giga newton") {
                result = 9.8067e-6 * amount;
                toasttext = "Ton-force to Giga newton " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Ton-force" && spinneroutput.getSelectedItem() == "Mega newton") {
                result = 0.00980665 * amount;
                toasttext = "Ton-force to Mega newton " + result.toString();
                editText.setText(toasttext);
            }

            //applying conditions from = Giga newton
            if (spinnerinput.getSelectedItem() == "Giga newton" && spinneroutput.getSelectedItem() == "Newton") {
                result = 1e+9 * amount;
                toasttext = "Giga newton to Newton " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Giga newton" && spinneroutput.getSelectedItem() == "Kilo newton") {
                result = 1e+6 * amount;
                toasttext = "Giga newton to Kilo newton " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Giga newton" && spinneroutput.getSelectedItem() == "Gram-force") {
                result = 1.01971621e+11 * amount;
                toasttext = "Giga newton to Gram-force " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Giga newton" && spinneroutput.getSelectedItem() == "Kilogram-force") {
                result = 101971621.3 * amount;
                toasttext = "Giga newton to Kilogram-force " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Giga newton" && spinneroutput.getSelectedItem() == "Ton-force") {
                result = 101971.6213 * amount;
                toasttext = "Giga newton to Ton-force " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Giga newton" && spinneroutput.getSelectedItem() == "Giga newton") {
                toasttext = "Error 2X00f_00#You!";
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Giga newton" && spinneroutput.getSelectedItem() == "Mega newton") {
                result = 1000 * amount;
                toasttext = "Giga newton to Mega newton " + result.toString();
                editText.setText(toasttext);
            }

            //applying conditions from = Mega newton
            if (spinnerinput.getSelectedItem() == "Mega newton" && spinneroutput.getSelectedItem() == "Newton") {
                result = 1e+6 * amount;
                toasttext = "Mega newton to Newton " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Mega newton" && spinneroutput.getSelectedItem() == "Kilo newton") {
                result = 1000 * amount;
                toasttext = "Mega newton to Kilo newton " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Mega newton" && spinneroutput.getSelectedItem() == "Gram-force") {
                result = 101971621.3 * amount;
                toasttext = "Mega newton to Gram-force " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Mega newton" && spinneroutput.getSelectedItem() == "Kilogram-force") {
                result = 101971.6213 * amount;
                toasttext = "Mega newton to Kilogram-force " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Mega newton" && spinneroutput.getSelectedItem() == "Ton-force") {
                result = 101.9716213 * amount;
                toasttext = "Mega newton to Ton-force " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Mega newton" && spinneroutput.getSelectedItem() == "Giga newton") {
                result = 0.001 * amount;
                toasttext = "Mega newton to Giga newton " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Mega newton" && spinneroutput.getSelectedItem() == "Mega newton") {
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
}