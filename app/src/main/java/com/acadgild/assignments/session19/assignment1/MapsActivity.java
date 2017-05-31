package com.acadgild.assignments.session19.assignment1;

import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;


import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapClickListener, GoogleMap.OnMapLongClickListener{

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    Marker marker;
    Geocoder geocoder;
    List<Address> addresses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }


        }
    }

    private void setUpMap() {
        marker = mMap.addMarker(new MarkerOptions().position(new LatLng(33.602, -111.888)).title("ACADGILD"));

        CameraUpdate center=
                CameraUpdateFactory.newLatLng(new LatLng(33.602,
                        -111.888));
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);
        mMap.moveCamera(center);
        mMap.animateCamera(zoom);

    }

    @Override
    public void onMapClick(LatLng latLng) {

        if (marker != null) {
            marker.remove();
        }

        // extract latitude & longitude on clicked point
        int latitude = (int) latLng.latitude;
        int longitude = (int) latLng.longitude;

        geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

        // get the address
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // display custom marker
        MarkerOptions options = new MarkerOptions()
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                .position(latLng);
        marker = mMap.addMarker(options) ;
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {

                View v = getLayoutInflater().inflate(R.layout.info_bubble, null);


                // setup address
                TextView add1 = (TextView) v.findViewById(R.id.add1);
                add1.setText(addresses.get(0).getAddressLine(0));

                TextView add2 = (TextView) v.findViewById(R.id.add2);
                add2.setText(addresses.get(0).getAdminArea());

                TextView add3 = (TextView) v.findViewById(R.id.add3);
                add3.setText(addresses.get(0).getCountryName() + " - " + addresses.get(0).getCountryCode());



                return v;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });
    }


    @Override
    public void onMapLongClick(LatLng latLng) {

        if (marker != null) {
            marker.remove();
        }

        // extract latitude & longitude on clicked point
        int latitude = (int) latLng.latitude;
        int longitude = (int) latLng.longitude;

        geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

        // get the address
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // display custom marker
        MarkerOptions options = new MarkerOptions()
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                .position(latLng);
        marker = mMap.addMarker(options) ;
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {

                View v = getLayoutInflater().inflate(R.layout.info_bubble, null);


                // setup address
                TextView add1 = (TextView) v.findViewById(R.id.add1);
                add1.setText(addresses.get(0).getAddressLine(0));

                TextView add2 = (TextView) v.findViewById(R.id.add2);
                add2.setText(addresses.get(0).getAdminArea());

                TextView add3 = (TextView) v.findViewById(R.id.add3);
                add3.setText(addresses.get(0).getCountryName() + " - " + addresses.get(0).getCountryCode());



                return v;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });
    }
}