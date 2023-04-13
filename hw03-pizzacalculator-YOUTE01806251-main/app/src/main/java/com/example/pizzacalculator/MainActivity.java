/***********************************************
 Author: <John E Youte>
 Date: <Sun, sept 25>
 Purpose: <Calculating Pizza for an certain amount of guests>
 What Learned: <I have learned how to nested layouts in android etc>
 Sources of Help: <Android dev, stack overflow(Debugging)>
 Time Spent: <about 5H>
 ***********************************************/

/*
COMP.2010 HW 03 F22 - Dr. Lin
Mobile App Development I -- COMP.4630 Honor Statement
The practice of good ethical behavior is essential for
maintaining good order in the classroom, providing an
enriching learning experience for students, and training as
a practicing computing professional upon graduation. This
practice is manifested in the University's Academic
Integrity policy. Students are expected to strictly avoid
academic dishonesty and adhere to the Academic Integrity
policy as outlined in the course catalog. Violations will
be dealt with as outlined therein. All programming
assignments in this class are to be done by the student
alone unless otherwise specified. No outside help is
permitted except the instructor and approved tutors.
I certify that the work submitted with this assignment is
mine and was generated in a manner consistent with this
document, the course academic policy on the course website
on Blackboard, and the UMass Lowell academic code.
Date:Sun, September 25
Name: John Ersen Youte
*/

package com.example.pizzacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Spinner hungerLevelSpinner;
    private EditText userPromptEditText;
    private int userPromptEditTextInt;
    private TextView guest_Amount, hungerLevel, pizzaNeeded;
    private String spinnerText;
    Button calculatebutton;
    static List<String> listHunger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //finding the view for all of the components
        hungerLevelSpinner = findViewById(R.id.hungerLevel);
        userPromptEditText = findViewById(R.id.editUserPromp);
        guest_Amount = findViewById(R.id.detailsResult);
        hungerLevel = findViewById(R.id.PizzaNeededResult);
        pizzaNeeded = findViewById(R.id.AmountOfPizzaResult);
        calculatebutton = findViewById(R.id.calculateButton);
        listHunger = Arrays.asList(getResources().getStringArray(R.array.hungerLevel));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        //Spinner selected on listener, this the value inside of the spinner.
        hungerLevelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerText = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Do nothing
            }
        });

        //button setOnClickListener, this method checks if there is the default string inside of the Spinner is active
        //if it is active, the program will tell the user to make a better choice. just a way i think to help the user
        // else if the user chose the right option, the app will calculate and output the values
        //and eventually the method calculate Pizza is called.
        //Add 0 text inside of the edit text and something will happen.
        calculatebutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                /////////////////////////Making a little toast////////////////////////////
                Context context = getApplicationContext();
                CharSequence text = getText(R.string.enjoy);
                int duration = Toast.LENGTH_LONG;
                /////////////////////////Making a little toast////////////////////////////
                if (userPromptEditText.getText().toString().trim().length() <= 0) {
                    userPromptEditTextInt = 0;
                    userPromptEditText.setError(getText(R.string.error2));
                } else if (userPromptEditText.getText().toString().trim().length() > 0) {
                    if (spinnerText.equalsIgnoreCase(getString(R.string.User_question))) {
                        hungerLevel.setText(R.string.error);
                        guest_Amount.setText(R.string.error);
                        pizzaNeeded.setText(R.string.error);
                        hungerLevel.setTextColor(Color.RED);
                        guest_Amount.setTextColor(Color.RED);
                        pizzaNeeded.setTextColor(Color.RED);
                    } else {
                        userPromptEditTextInt = Integer.parseInt(userPromptEditText.getText().toString());
                        guest_Amount.setText(userPromptEditText.getText().toString());
                        hungerLevel.setText(spinnerText);
                        pizzaNeeded.setText(pizzaCal(userPromptEditTextInt, spinnerText) + "");
                        hungerLevel.setTextColor(Color.WHITE);
                        guest_Amount.setTextColor(Color.WHITE);
                        pizzaNeeded.setTextColor(Color.WHITE);
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
//

            }
        });


    }

    //method with 4 if statement, verifying the user input inside of the spinner
    //the method takes two parameters, guest amount and hunger type
    //it has also some local variable.
    //The Logic is take the amount of guest and multiply it by the the number chose inside of the spinner
    //(light=2, medium=3, ravenous=4), and it will devide it by pizzaSlices=12, and check if there rest, it will make it equal with
    //one whole pizza. bc the pizzeria does not sell slices.
    public static int pizzaCal(int guestAmount, String hungerType) {
        final int pizzaSlices = 12;
        int multiplier = 0;
        int devid = 0;
        int pizzaAmount = 0;

        if (hungerType.equalsIgnoreCase(listHunger.get(1))) {
            multiplier = 2 * guestAmount;
            devid = multiplier % pizzaSlices;
            if (devid == 0) {
                pizzaAmount = multiplier / pizzaSlices;
            } else {
                pizzaAmount = (multiplier / pizzaSlices) + 1;
            }
        } else if (hungerType.equalsIgnoreCase(listHunger.get(2))) {
            multiplier = 3 * guestAmount;
            devid = multiplier % pizzaSlices;
            if (devid == 0) {
                pizzaAmount = multiplier / pizzaSlices;
            } else {
                pizzaAmount = (multiplier / pizzaSlices) + 1;
            }
        } else if (hungerType.equalsIgnoreCase(listHunger.get(3))) {
            multiplier = 4 * guestAmount;
            devid = multiplier % pizzaSlices;
            if (devid == 0) {
                pizzaAmount = multiplier / pizzaSlices;
            } else {
                pizzaAmount = (multiplier / pizzaSlices) + 1;
            }
        }
        return pizzaAmount;
    }


}