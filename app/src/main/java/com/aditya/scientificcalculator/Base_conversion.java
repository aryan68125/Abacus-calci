package com.aditya.scientificcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.Expression;

import java.util.HashMap;

public class Base_conversion extends AppCompatActivity {

    EditText editText;
    String answer;
    Toolbar toolbar;
    Button button_A;
    Button button_B;
    Button button_C;
    Button button_D;
    Button button_E;
    Button button_F;
    Button button_seven_7;
    Button button_eight_8;
    Button button_nine_9;
    Button button_four_4;
    Button button_five_5;
    Button button_six_6;
    Button button_two_2;
    Button button_three_3;

    //flags for the menu
    int dec_bin = 0;
    int bin_dec = 0;
    int hex_bin = 0;
    int hex_dec = 0;
    int dec_hex = 0;
    int bin_hex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_conversion);
        editText = findViewById(R.id.answer_of_conversion);
        toolbar= findViewById(R.id.toolbarBaseConverter);
        setSupportActionBar(toolbar);
        //calling get support action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editText.setShowSoftInputOnFocus(false); //this will disable the keyboard pop up of the device

        //setting up all the buttons
        button_A = findViewById(R.id.button_A);
        button_B = findViewById(R.id.button_B);
        button_C = findViewById(R.id.button_C);
        button_D = findViewById(R.id.button_D);
        button_E = findViewById(R.id.button_E);
        button_F = findViewById(R.id.button_F);
        button_seven_7 = findViewById(R.id.button_seven_7);
        button_eight_8 = findViewById(R.id.button_eight_8);
        button_nine_9 = findViewById(R.id.button_nine_9);
        button_four_4 = findViewById(R.id.button_four_4);
        button_five_5 = findViewById(R.id.button_five_5);
        button_six_6 = findViewById(R.id.button_six_6);
        button_two_2 = findViewById(R.id.button_two_2);
        button_three_3 = findViewById(R.id.button_three_3);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString()=="Input"){
                    editText.setText(""); //clear the input field is the hint input is there when the user taps on it
                }
            }
        });
    }// on create closed

    //exit animation of currently active activity
    @Override
    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    //this function will allow us to set up menue item on the app title bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        //onCreateOptionsMenu(Menu menu object)
        //menuInflater.inflate(R.menu.menue xml file name, menu object);
        menuInflater.inflate(R.menu.base_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //this function is used to listen for click
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        //item.getItemId() will get the item id selected by the user
        switch (item.getItemId()){
            case R.id.dec_bin:
                button_A.setEnabled(false);
                button_B.setEnabled(false);
                button_C.setEnabled(false);
                button_D.setEnabled(false);
                button_E.setEnabled(false);
                button_F.setEnabled(false);

                button_seven_7.setEnabled(true);
                button_eight_8.setEnabled(true);
                button_nine_9.setEnabled(true);
                button_four_4.setEnabled(true);
                button_five_5.setEnabled(true);
                button_six_6.setEnabled(true);
                button_two_2.setEnabled(true);
                button_three_3.setEnabled(true);

                //setting up the flags
                dec_bin = 1;
                bin_dec = 0;
                hex_bin = 0;
                hex_dec = 0;
                dec_hex = 0;
                bin_hex = 0;
                Log.i("item","decimal number to binary");
                //return true; works same as a break statement it prevents the fall through of the switch case
                return true;
            case R.id.bin_dec:
                button_A.setEnabled(false);
                button_B.setEnabled(false);
                button_C.setEnabled(false);
                button_D.setEnabled(false);
                button_E.setEnabled(false);
                button_F.setEnabled(false);

                button_seven_7.setEnabled(false);
                button_eight_8.setEnabled(false);
                button_nine_9.setEnabled(false);
                button_four_4.setEnabled(false);
                button_five_5.setEnabled(false);
                button_six_6.setEnabled(false);
                button_two_2.setEnabled(false);
                button_three_3.setEnabled(false);

                //setting up the flags
                dec_bin = 0;
                bin_dec = 1;
                hex_bin = 0;
                hex_dec = 0;
                dec_hex = 0;
                bin_hex = 0;
                Log.i("item","binary to decimal");
                //return true; works same as a break statement it prevents the fall through of the switch case
                return true;
            case R.id.hex_bin:
                button_A.setEnabled(true);
                button_B.setEnabled(true);
                button_C.setEnabled(true);
                button_D.setEnabled(true);
                button_E.setEnabled(true);
                button_F.setEnabled(true);

                button_seven_7.setEnabled(true);
                button_eight_8.setEnabled(true);
                button_nine_9.setEnabled(true);
                button_four_4.setEnabled(true);
                button_five_5.setEnabled(true);
                button_six_6.setEnabled(true);
                button_two_2.setEnabled(true);
                button_three_3.setEnabled(true);
                //setting up the flags
                dec_bin = 0;
                bin_dec = 0;
                hex_bin = 1;
                hex_dec = 0;
                dec_hex = 0;
                bin_hex = 0;
                Log.i("item","hexadecimal to binary");
                //return true; works same as a break statement it prevents the fall through of the switch case
                return true;
            case R.id.hex_dec:
                button_A.setEnabled(true);
                button_B.setEnabled(true);
                button_C.setEnabled(true);
                button_D.setEnabled(true);
                button_E.setEnabled(true);
                button_F.setEnabled(true);

                button_seven_7.setEnabled(true);
                button_eight_8.setEnabled(true);
                button_nine_9.setEnabled(true);
                button_four_4.setEnabled(true);
                button_five_5.setEnabled(true);
                button_six_6.setEnabled(true);
                button_two_2.setEnabled(true);
                button_three_3.setEnabled(true);
                //setting up the flags
                dec_bin = 0;
                bin_dec = 0;
                hex_bin = 0;
                hex_dec = 1;
                dec_hex = 0;
                bin_hex = 0;
                Log.i("item","hexadecimal to decimal");
                return true;
            case R.id.dec_hex:
                button_A.setEnabled(false);
                button_B.setEnabled(false);
                button_C.setEnabled(false);
                button_D.setEnabled(false);
                button_E.setEnabled(false);
                button_F.setEnabled(false);

                button_seven_7.setEnabled(true);
                button_eight_8.setEnabled(true);
                button_nine_9.setEnabled(true);
                button_four_4.setEnabled(true);
                button_five_5.setEnabled(true);
                button_six_6.setEnabled(true);
                button_two_2.setEnabled(true);
                button_three_3.setEnabled(true);
                //setting up the flags
                dec_bin = 0;
                bin_dec = 0;
                hex_bin = 0;
                hex_dec = 0;
                dec_hex = 1;
                bin_hex = 0;
                Log.i("item","decimal to hexadecimal");
                return true;
            case R.id.bin_hex:
                button_A.setEnabled(false);
                button_B.setEnabled(false);
                button_C.setEnabled(false);
                button_D.setEnabled(false);
                button_E.setEnabled(false);
                button_F.setEnabled(false);

                button_seven_7.setEnabled(false);
                button_eight_8.setEnabled(false);
                button_nine_9.setEnabled(false);
                button_four_4.setEnabled(false);
                button_five_5.setEnabled(false);
                button_six_6.setEnabled(false);
                button_two_2.setEnabled(false);
                button_three_3.setEnabled(false);

                //setting up the flags
                dec_bin = 0;
                bin_dec = 0;
                hex_bin = 0;
                hex_dec = 0;
                dec_hex = 0;
                bin_hex = 1;
                Log.i("item","binary to hexadecimal");
                return true;
            default:
                return false;
        }
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
        if (editText.getText().toString() == "Input") {
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

    public void sevenButton(View view)
    {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("7");
    }
    public void eightButton(View view)
    {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("8");
    }
    public void nineButton(View view)
    {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("9");
    }
    public void hex_A(View view)
    {
        //now here we will use update text function that we just created to update the text in our editText
        updateEditText("A");
    }
    public void fourButton(View view)
    {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("4");
    }
    public void fiveButton(View view)
    {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("5");
    }
    public void sixButton(View view)
    {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("6");
    }
    public void hex_B(View view)
    {
        //now here we will use update text function that we just created to update the text in our editText
        updateEditText("B");
    }
    public void oneButton(View view)
    {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("1");
    }
    public void twoButton(View view)
    {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("2");
    }
    public void threeButton(View view)
    {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("3");
    }
    public void hex_C(View view)
    {
        //now here we will use update text function that we just created to update the text in our editText
        updateEditText("C");
    }
    public void hex_D(View view)
    {
        //now here we will use update text function that we just created to update the text in our editText
        updateEditText("D");
    }
    public void hex_E(View view)
    {
        //now here we will use update text function that we just created to update the text in our editText
        updateEditText("E");
    }
    public void hex_F(View view)
    {
        //now here we will use update text function that we just created to update the text in our editText
        updateEditText("F");
    }
    public void zeroButton(View view)
    {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("0");
    }

    public void clearButton(View view)
    {
        //now here we will use the update text function that we just created to update the text in our editText
        editText.setText("");
    }
    public void answerButton(View view) {
        if (answer==null) {
            editText.setText("0");
        } else {
            editText.setText(answer);
            //now we have to make sure to reposition the cursor to all the way to the end of the answer
            editText.setSelection(answer.length());
        }
    }
    public void backspaceButton(View view)
    {
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

    public void equalsButton(View view)
    {
        if(dec_bin==1)
        {
            try {
                //here logic for converting decimal number to binary will come
                String temp = "";
                int decimal = Integer.parseInt(editText.getText().toString());
                int binary[] = new int[40];
                int index = 0;
                while (decimal > 0) {
                    binary[index++] = decimal % 2;
                    decimal = decimal / 2;
                }
                // converting the binary result in array into a string
                for (int i = index - 1; i >= 0; i--) {
                    temp += Integer.toString(binary[i]);
                }
                answer = temp;
                //now after calculation we need to update the text in editText
                editText.setText(answer);
                //now we have to make sure to reposition the cursor to all the way to the end of the answer
                editText.setSelection(answer.length());
            }
            catch (Exception e)
            {
                Toast.makeText(this,"OOps somethng went wrong!",Toast.LENGTH_SHORT).show();
            }
        }
        else if(bin_dec==1)
        {
            try{
            String binaryString = editText.getText().toString();
            int decimal=Integer.parseInt(binaryString,2);
            answer = Integer.toString(decimal);
            //now after calculation we need to update the text in editText
            editText.setText(answer);
            //now we have to make sure to reposition the cursor to all the way to the end of the answer
            editText.setSelection(answer.length());
            }
            catch (Exception e)
            {
                Toast.makeText(this,"OOps somethng went wrong!",Toast.LENGTH_SHORT).show();
            }
        }
        else if(hex_bin==1)
        {
            try{
            // declaring the method to convert
            // Hexadecimal to Binary
            String hex = editText.getText().toString();

                // variable to store the converted
                // Binary Sequence
                String binary = "";

                // converting the accepted Hexadecimal
                // string to upper case
                hex = hex.toUpperCase();

                // initializing the HashMap class
                HashMap<Character, String> hashMap
                        = new HashMap<Character, String>();

                // storing the key value pairs
                //hashMap.put('key=0','value=00000');
                hashMap.put('0', "0000");
                hashMap.put('1', "0001");
                hashMap.put('2', "0010");
                hashMap.put('3', "0011");
                hashMap.put('4', "0100");
                hashMap.put('5', "0101");
                hashMap.put('6', "0110");
                hashMap.put('7', "0111");
                hashMap.put('8', "1000");
                hashMap.put('9', "1001");
                hashMap.put('A', "1010");
                hashMap.put('B', "1011");
                hashMap.put('C', "1100");
                hashMap.put('D', "1101");
                hashMap.put('E', "1110");
                hashMap.put('F', "1111");

                int i;
                char ch;

                // loop to iterate through the length
                // of the Hexadecimal String
                for (i = 0; i < hex.length(); i++) {
                    // extracting each character
                    ch = hex.charAt(i);

                    // checking if the character is
                    // present in the keys
                    if (hashMap.containsKey(ch))
                    {

                        // adding to the Binary Sequence
                        // the corresponding value of
                        // the key
                        binary += hashMap.get(ch);

                    // returning Invalid Hexadecimal
                    // String if the character is
                    // not present in the keys
                    }
                    else {
                        binary = "Invalid Hexadecimal String";
                        editText.setText(binary);
                    }
                }
            answer = binary;
            //now after calculation we need to update the text in editText
            editText.setText(answer);
            //now we have to make sure to reposition the cursor to all the way to the end of the answer
            editText.setSelection(answer.length());
            }
            catch (Exception e)
            {
                Toast.makeText(this,"OOps somethng went wrong!",Toast.LENGTH_SHORT).show();
            }
        }
        else if(hex_dec==1)
        {
            try{
            //logic to convert hexadecimal to decimal number
            String hex=editText.getText().toString();
            int decimal=Integer.parseInt(hex,16);
            answer = Integer.toString(decimal);
            //now after calculation we need to update the text in editText
            editText.setText(answer);
            //now we have to make sure to reposition the cursor to all the way to the end of the answer
            editText.setSelection(answer.length());
            }
            catch (Exception e)
            {
                Toast.makeText(this,"OOps somethng went wrong!",Toast.LENGTH_SHORT).show();
            }
        }
        else if(dec_hex==1)
        {
            try{
            int decimal = Integer.parseInt(editText.getText().toString());
            String hexadecimal = Integer.toHexString(decimal);
            answer = hexadecimal.toUpperCase();
            //now after calculation we need to update the text in editText
            editText.setText(answer);
            //now we have to make sure to reposition the cursor to all the way to the end of the answer
            editText.setSelection(answer.length());
            }
            catch (Exception e)
            {
                Toast.makeText(this,"OOps somethng went wrong!",Toast.LENGTH_SHORT).show();
            }
        }
        else if(bin_hex==1)
        {
            try{
            String binaryString = editText.getText().toString();
            int decimal=Integer.parseInt(binaryString,2);
            String hexadecimal = Integer.toHexString(decimal);
            answer = hexadecimal.toUpperCase();
            //now after calculation we need to update the text in editText
            editText.setText(answer);
            //now we have to make sure to reposition the cursor to all the way to the end of the answer
            editText.setSelection(answer.length());
            }
            catch (Exception e)
            {
                Toast.makeText(this,"OOps somethng went wrong!",Toast.LENGTH_SHORT).show();
            }
        }

    }
    public void answer_save_Button(View view) {
        if (answer==null) {
            editText.setText("NULL");
        } else {
            editText.setText(answer);
            //now we have to make sure to reposition the cursor to all the way to the end of the answer
            editText.setSelection(answer.length());
        }
    }
}