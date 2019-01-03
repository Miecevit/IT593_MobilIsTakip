package com.it593.dev.mobilistakip;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener, GoogleMap.OnMapLongClickListener {
    private static final String TAG = "MapActivity";

    private GoogleMap mMap;
    private Boolean mLocationPermissionsGranted = false;
    private EditText mSearchText;
    private ImageView mGps;
    String service;
    private List<Task> taskLists = new ArrayList<>();

    // (5)

    private MyLocationListener mylistener;
    private LocationManager locationManager;
    private String provider;
    private Criteria criteria;
    private static final String[] INITIAL_PERMS = {
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private static final int INITIAL_REQUEST=1337;

    // (7)
    private static final double RADIUS_OF_EARTH_METERS = 6371009;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mSearchText = (EditText) findViewById(R.id.input_search);
        mGps = (ImageView) findViewById(R.id.ic_gps);



        new getTask().execute();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //userLocationFAB();
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
        mMap.setOnMarkerDragListener(this);
        mMap.setOnMapLongClickListener(this);

        // (1) Add a marker and move the camera
        LatLng latLngIsik = new LatLng(41.111661, 29.025288);
        mMap.addMarker(new MarkerOptions().position(latLngIsik).title("Marker in Isik University"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLngIsik));

        // (2) zoom to location
        zoomToPosition(latLngIsik, googleMap);

        // (3)
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        //mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        //mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        // (4)



        // (6)
        googleMap.getUiSettings().setZoomGesturesEnabled(true);

        // (7)

    }
    public class getTask extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            taskLists = TaskHelper.getAllTasks();
            if(taskLists != null)
                System.out.println("GETALLTasks!");
            System.out.println(taskLists);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (taskLists != null) {
                System.out.println("Tasks!");
                LoadTasks();
            }
            else
                System.out.println("Can't get Tasks!!");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(Void result) {
            super.onCancelled(result);
        }
    }
    private void LoadTasks() {
        //taskadapter = new TaskAdapter(this, taskLists);
        //listView.setAdapter(taskadapter);
    }
    private void moveCamera(LatLng latLng, float zoom, String title,String uripath)  {
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        Bitmap bm= null;
        if(uripath!=null){
            try {
                bm = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(uripath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (title!=null){
            if(!title.equals("My Location")){
                if(bm!=null){
                    MarkerOptions options = new MarkerOptions()
                            .position(latLng)
                            .title(title).icon(BitmapDescriptorFactory.fromBitmap(bm));
                    mMap.addMarker(options);
                }else {
                    MarkerOptions options = new MarkerOptions()
                            .position(latLng)
                            .title(title).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher));
                    mMap.addMarker(options);
                }

            }
        }


        hideSoftKeyboard();
    }

    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {
        onMarkerMoved(marker);
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        // We know the center, let's place the outline at a point 3/4 along the view.
        View view = getSupportFragmentManager().findFragmentById(R.id.map).getView();
        LatLng radiusLatLng = mMap.getProjection().fromScreenLocation(new Point(
                view.getHeight() * 3 / 4, view.getWidth() * 3 / 4));

    }

    // (7)
    private void onMarkerMoved(Marker marker) {

    }

    // (2)
    private void zoomToPosition(LatLng position, GoogleMap gMap) {
        //Build camera position
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(position)
                .zoom(15).build();
        //Zoom in and animate the camera.
        gMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private boolean hasPermission(String perm) {
        return(PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(MapsActivity.this, perm));
    }

   /* private void userLocationFAB(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.myLocationButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // (5)
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                criteria = new Criteria();
                criteria.setAccuracy(Criteria.ACCURACY_LOW);

                if (!hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    ActivityCompat.requestPermissions(MapsActivity.this, INITIAL_PERMS, INITIAL_REQUEST);
                }

                provider = locationManager.getBestProvider(criteria, true);
                // The last known location of this provider
                try {
                    Location location = locationManager.getLastKnownLocation(provider);
                    mylistener = new MyLocationListener();

                    if (location != null) {
                        mylistener.onLocationChanged(location);
                    } else {
                        // leads to the settings because there is no last known location
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                    // location updates: at least 1 meter and 200millsecs change
                    locationManager.requestLocationUpdates(provider, 200, 1, mylistener);
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
            }
        });
    } */


    // (5)
    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            Toast.makeText(MapsActivity.this,  "Latitude / Longitude: " +
                    String.valueOf(location.getLatitude()) + " / " +
                    String.valueOf(location.getLongitude()), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Toast.makeText(MapsActivity.this, provider + "'s status changed to "+status +"!",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText(MapsActivity.this, "Provider " + provider + " enabled!",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderDisabled(String provider) {
            Toast.makeText(MapsActivity.this, "Provider " + provider + " disabled!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
