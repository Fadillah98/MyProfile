package sg.edu.rp.c346.myprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etGPA;
    RadioGroup rgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);
    }

    @Override
    protected void onPause() {
        super.onPause();

        //Step 1a: Get the user input from the EditText and store it in a variable
        String strName = etName.getText().toString();
        String strGPA = etGPA.getText().toString();
        int idGender = rgGender.getCheckedRadioButtonId();

        //Step 1b: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //Step 1c: Obtain an instance of the SharedPreference Editor for update later
        SharedPreferences.Editor prefEdit = prefs.edit();

        //Step 1d: Add the key-value pair
        prefEdit.putString("name", strName);
        prefEdit.putString("gpa", strGPA);
        prefEdit.putInt("gender", idGender);

        //Step 1e: Call commit() method to save the changes into the Shared Preferences
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Step 2a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //Step 2b: Retrieve the saved data from the SharedPreferences object
        String name = prefs.getString("name", "");
        String gpa = prefs.getString("gpa", "");
        rgGender.check(prefs.getInt("gender",0));

        Toast toast = Toast.makeText(getApplicationContext(), name + " with a gpa " + gpa, Toast.LENGTH_LONG);
        toast.show();
    }
}
