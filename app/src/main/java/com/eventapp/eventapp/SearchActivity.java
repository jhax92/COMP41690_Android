package com.eventapp.eventapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by devin on 22/11/16.
 */

public class SearchActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Spinner spinner = (Spinner) findViewById(R.id.category_spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.preferences_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        Spinner spinner1 = (Spinner) findViewById(R.id.sortby_spinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.sortby_options, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter1);

        final Button button = (Button) findViewById(R.id.search_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                if (findViewById(R.id.location_editText).toString().equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(), "Please enter a location.", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    searchByDetails();
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Search");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch(id) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void searchByDetails() {
        EditText medit = (EditText)findViewById(R.id.location_editText);
        String location =  medit.getText().toString();
        Spinner spinner = (Spinner) findViewById(R.id.category_spinner);

        int position = spinner.getSelectedItemPosition();
        String[] selectedArray = getResources().getStringArray(R.array.preferences_values);
        String category = selectedArray[position];

        Spinner spinner1 = (Spinner) findViewById(R.id.sortby_spinner);
        String sortby = spinner1.getItemAtPosition(spinner1.getSelectedItemPosition()).toString();

        StringBuilder url = new StringBuilder("&");

        url.append("location=");
        url.append(location.replaceAll(" ", "+"));
        url.append("&");
        url.append("category=");
        url.append(category.toLowerCase());
        url.append("&");
        url.append("sort_order=");
        url.append(sortby);

        Intent intent = new Intent(this, SearchResultsActivity.class);
        intent.putExtra("url", url.toString());
        startActivity(intent);
    }




}
