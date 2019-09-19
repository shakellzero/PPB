package com.example.googlemaps;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment);
        mapFragment.getMapAsync(this);

        Button go = findViewById(R.id.idGo);
        go.setOnClickListener(op);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.normal : mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); break;
            case R.id.terrain: mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);break;
            case R.id.sattelite: mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);break;
            case R.id.hybrid: mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);break;
            case R.id.none:mMap.setMapType(GoogleMap.MAP_TYPE_NONE);break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng ITS = new LatLng(-7.2819705, 112.795323);
        mMap.addMarker(new MarkerOptions().position(ITS).title("Marker in ITS"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ITS, 15));
    }

    View.OnClickListener op = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.idGo:sembunyikanKeyBoard(view);
                    gotoLokasi();break; }
        }
    };

    private void gotoLokasi()  {
        EditText lat = findViewById(R.id.idLokasiLat);
        EditText lng = findViewById(R.id.idLokasiLng);
        EditText zoom = findViewById(R.id.idZoom);

        Double dbllat = Double.parseDouble(lat.getText().toString());
        Double dbllng = Double.parseDouble(lng.getText().toString());
        Float dblzoom = Float.parseFloat(zoom.getText().toString());

        Toast.makeText(this,"Move to Lat:" +dbllat + " Long:" +dbllng, Toast.LENGTH_LONG).show();
        gotoPeta(dbllat,dbllng,dblzoom);
    }


    private void sembunyikanKeyBoard(View v){
        InputMethodManager a = (InputMethodManager)
                getSystemService(INPUT_METHOD_SERVICE);
        a.hideSoftInputFromWindow(v.getWindowToken(),0);
    }

    private void gotoPeta(Double lat, Double lng, float z){
        LatLng Lokasibaru = new LatLng(lat,lng);
        mMap.addMarker(new MarkerOptions().
                position(Lokasibaru).
                title("Marker in  " +lat +":" +lng));
        mMap.moveCamera(CameraUpdateFactory.
                newLatLngZoom(Lokasibaru,z));

    }
}

