package com.example.jbsempe.photogeo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class Details_Gallery_Activity extends ActionBarActivity {

    ImageView imageView;
    TextView titleTextView;
    TextView idTextView;
    TextView commentTextView;

    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_gallery_activity);

        String title = getIntent().getStringExtra("title");
        String imageUri = getIntent().getStringExtra("image");
        String id = getIntent().getStringExtra("id");
        String description = getIntent().getStringExtra("description");

        idTextView=(TextView) findViewById(R.id.id);
        idTextView.setText(id);

        titleTextView = (TextView) findViewById(R.id.title);
        titleTextView.setText(title);

        commentTextView = (TextView) findViewById(R.id.description);
        commentTextView.setText(description);

        imageView = (ImageView) findViewById(R.id.image);
        imageView.setImageURI(Uri.parse(imageUri));


    }
    //Menu dans la page Gallerie
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_carte) {
            Intent galleryIntent = new Intent(Details_Gallery_Activity.this, Main_Activity.class);
            startActivity(galleryIntent);
            return true;
        }
        if (id == R.id.action_galerie) {
            Intent galleryIntent = new Intent(Details_Gallery_Activity.this, Gallery_Activity.class);
            startActivity(galleryIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
