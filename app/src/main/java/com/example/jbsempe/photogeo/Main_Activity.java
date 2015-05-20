package com.example.jbsempe.photogeo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.jbsempe.photogeo.Model.Photo;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;


public class Main_Activity extends ActionBarActivity implements OnMapReadyCallback {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    MapFragment mMapFragment;
    GoogleMap googleMap;
    Uri imgURI;
    Photo_Handler dbhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        dbhandler = new Photo_Handler(getApplicationContext());

        mMapFragment = MapFragment.newInstance();
        getFragmentManager().beginTransaction()
                .add(R.id.main, mMapFragment)
                .commit();
        mMapFragment.getMapAsync(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_photo) {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent,REQUEST_IMAGE_CAPTURE);
            return true;
        }
        if (id == R.id.action_galerie) {
            Intent galleryIntent = new Intent(Main_Activity.this, Gallery_Activity.class);
            startActivity(galleryIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int reqCode, int resCode, Intent data){
        super.onActivityResult(reqCode, resCode, data);
        if(resCode==-1 && reqCode ==REQUEST_IMAGE_CAPTURE && data!=null){
            Uri pic_uri = data.getData();
            if(pic_uri!=null){
                imgURI=pic_uri;
            }else{
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imgURI=getImageUri(getApplicationContext(),imageBitmap);
            }

            GPSTracker gps = new GPSTracker(this);



            Date _date=new Date();
            Photo photo = new Photo(1,gps.getLatitude(), gps.getLongitude(),
                    0,_date,"IMG_","commentaires",String.valueOf(imgURI));
            dbhandler.createPhoto(photo)
            ;
            Toast.makeText(getApplicationContext(),"photo",Toast.LENGTH_SHORT);
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(photo.get_latitude(),photo.get_longitude()))
                    .title(photo.get_libelle() + photo.get_id()));
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public void afficherMarker(){
        if(googleMap!=null){
            List<Photo> photos = dbhandler.getAllPhotos();
            for (int i =0; i<photos.size();i++){
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(photos.get(i).get_latitude(),photos.get(i).get_longitude()))
                        .title(photos.get(i).get_libelle() + photos.get(i).get_id()));
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap=map;
        afficherMarker();
    }
}
