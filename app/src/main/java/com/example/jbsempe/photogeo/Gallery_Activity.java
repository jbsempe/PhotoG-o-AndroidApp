package com.example.jbsempe.photogeo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.example.jbsempe.photogeo.Model.Photo;


public class Gallery_Activity extends ActionBarActivity {

    private GridView gridView;
    private Grid_Photo_Gallery gridAdapter;
    private Photo_Handler db = new Photo_Handler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_activity);

        gridView = (GridView) findViewById(R.id.gridView);
        gridAdapter = new Grid_Photo_Gallery(this, R.layout.grid_photo_gallery, db.getAllPhotos());
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Photo photo = (Photo) parent.getItemAtPosition(position);

                Intent intent = new Intent(Gallery_Activity.this, Details_Gallery_Activity.class);
                intent.putExtra("title", photo.get_libelle());
                intent.putExtra("image", photo.get_photouri());
                intent.putExtra("id", Integer.toString(photo.get_id()));
                intent.putExtra("date", photo.get_date().toString());
                intent.putExtra("description",photo.get_commentaires());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_gallery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_carte) {
            Intent galleryIntent = new Intent(Gallery_Activity.this, Main_Activity.class);
            startActivity(galleryIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
