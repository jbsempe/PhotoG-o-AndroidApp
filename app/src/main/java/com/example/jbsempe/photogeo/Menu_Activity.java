package com.example.jbsempe.photogeo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;



public class Menu_Activity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_gallery);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_container, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_carte) {
            return true;
        }
        if (id == R.id.action_galerie) {
            return true;
        }
        if (id == R.id.action_photo) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
