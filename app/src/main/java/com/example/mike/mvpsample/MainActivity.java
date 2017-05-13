package com.example.mike.mvpsample;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mike.mvpsample.classes.NotesPresenter;

public class MainActivity extends AppCompatActivity {


    // Step 1 - using sqllite: Define the database and names for database and table.
    SQLiteDatabase mvpSamplDatabase;
    final static String DB_NAME = "MvpSampleDb";
    final static String NOTES_TABLE = "Notes";
    final static String COLUMN_NAME_HEAD = "head";
    final static String COLUMN_NAME_CONTENT = "content";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        onCreateAndGetDb();

        final NotesPresenter notesPresenter = new NotesPresenter();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        NotesListFragment notesListFragment = new NotesListFragment();
        fragmentTransaction.add(R.id.fragmentContainer, notesListFragment);
        fragmentTransaction.commit();
    }

    private void onCreateAndGetDb() {
        mvpSamplDatabase = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        mvpSamplDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + NOTES_TABLE +
                "(id integer primary key," +
                COLUMN_NAME_HEAD + " varchar(100)," +
                COLUMN_NAME_CONTENT + " varchar(200));");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        else if (id == R.id.action_drop_table)
            Log.i("mvp","Deleting whole table...");
        try
        {
            mvpSamplDatabase.execSQL("DROP TABLE IF EXISTS " + NOTES_TABLE);
            Log.i("mvp","Deleted table successfully.");
        }
        catch(Exception exc)
        {
            Log.e("mvp","Deleting table failed. Reason: " + exc.getMessage());
        }

        return super.onOptionsItemSelected(item);
    }
}
