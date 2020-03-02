package com.sarah.assignment2_s3667123.google_map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sarah.assignment2_s3667123.R;
import com.sarah.assignment2_s3667123.model.LatLngClass;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.LatLngBounds.Builder;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;


public class MapActivity extends FragmentActivity implements  LocationListener,OnMapReadyCallback {

    private GoogleMap googleMap;
    private ArrayList<LatLng> listLatLng;
    private RelativeLayout relativeLayout;
    HashMap<Marker, LatLngClass> hashMapMarker = new HashMap<Marker, LatLngClass>();
    double latitude;
    double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //return;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        longitude = location.getLongitude();
        latitude = location.getLatitude();

        relativeLayout = (RelativeLayout) findViewById(R.id.rlMapLayout);

        setUpMapIfNeeded();


    }

    private void setData() {
        ArrayList<LatLngClass> arrayList = new ArrayList<LatLngClass>();
        LatLngClass latLngClass = new LatLngClass();
        latLngClass.setTitle("University of Melbourne");
        latLngClass.setSnippet("University Of melbourne");
        latLngClass.setLatitude("-37.8014837");
        latLngClass.setLongitude("144.9569873");
        arrayList.add(latLngClass);

        LatLngClass latLngClass1 = new LatLngClass();
        latLngClass1.setTitle("Hoyts Dockland");
        latLngClass1.setSnippet("Hoyts cinema");
        latLngClass1.setLatitude("-37.8116177");
        latLngClass1.setLongitude("144.9347677");
        arrayList.add(latLngClass1);


        LoadingGoogleMap(arrayList);
    }

    @SuppressLint("MissingPermission")
    private void setUpMapIfNeeded() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

        // Google Play Services are not available
        if (status != ConnectionResult.SUCCESS) {
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();

        } else {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);


        }
    }

    @SuppressLint("MissingPermission")
    void LoadingGoogleMap(ArrayList<LatLngClass> arrayList) {
        if (googleMap != null) {
            googleMap.clear();
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            //googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setZoomControlsEnabled(true);

            if (arrayList.size() > 0) {
                try {
                    listLatLng = new ArrayList<LatLng>();
                    for (int i = 0; i < arrayList.size(); i++) {
                        LatLngClass latLngClass = arrayList.get(i);
                        if (latLngClass.getLatitude().length() > 0 && latLngClass.getLongitude().length() > 0) {
                            double lat = Double.parseDouble(latLngClass.getLatitude());
                            double lon = Double.parseDouble(latLngClass.getLongitude());

                            Marker marker = googleMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(lat, lon))
                                    .title(latLngClass.getTitle())
                                    .snippet(latLngClass.getSnippet())
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                            //Add Marker to Hashmap
                            hashMapMarker.put(marker, latLngClass);

                            //Set Zoom Level of Map pin
                            LatLng object = new LatLng(lat, lon);
                            listLatLng.add(object);
                        }
                    }
                    SetZoomLevel(listLatLng);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

                    @Override
                    public void onInfoWindowClick(Marker position) {
                        LatLngClass latLngClass = hashMapMarker.get(position);
                        Toast.makeText(getApplicationContext(), latLngClass.getTitle(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        } else {
            Toast.makeText(getApplicationContext(), "Sorry! unable to create maps", Toast.LENGTH_SHORT).show();
        }
    }


    public void SetZoomLevel(ArrayList<LatLng> listLatLng) {
        if (listLatLng != null && listLatLng.size() == 1) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(listLatLng.get(0), 10));
        } else if (listLatLng != null && listLatLng.size() > 1) {
            final Builder builder = LatLngBounds.builder();
            for (int i = 0; i < listLatLng.size(); i++) {
                builder.include(listLatLng.get(i));
            }

            final ViewTreeObserver treeObserver = relativeLayout.getViewTreeObserver();
            treeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @SuppressWarnings("deprecation")
                @Override
                public void onGlobalLayout() {
                    if (googleMap != null) {
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), findViewById(R.id.map)
                                .getWidth(), findViewById(R.id.map).getHeight(), 80));
                        relativeLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                }
            });

        }
    }

    @Override
    public void onMapReady(GoogleMap mGoogleMap) {
        googleMap = mGoogleMap;
        if (googleMap != null) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            //googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            googleMap.getUiSettings().setZoomControlsEnabled(true);

        }
        setData();
        CalculateDistanceTime distance_task = new CalculateDistanceTime(this);

        distance_task.getDirectionsUrl(String.valueOf(latitude), String.valueOf(longitude), "22.3000", "73.2000");


        distance_task.setLoadListener(new CalculateDistanceTime.taskCompleteListener() {
            @Override
            public void taskCompleted(String[] time_distance) {
                Toast.makeText(MapActivity.this, time_distance[1], Toast.LENGTH_SHORT).show();
                // approximate_time.setText("" + time_distance[1]);
                //approximate_diatance.setText("" + time_distance[0]);
            }

        });
    }

    @Override
    public void onLocationChanged(Location location) {
        longitude = location.getLongitude();
        latitude = location.getLatitude();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}

