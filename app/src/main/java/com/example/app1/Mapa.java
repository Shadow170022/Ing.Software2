package com.example.app1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Mapa extends FragmentActivity implements OnMapReadyCallback {

    private static final int PERMISSION_REQUEST_CODE = 1;
    public static String usuario = "nombre";
    LatLng myPosition;
    TextView textUser;
    EditText origen, destino;
    Button btnSolicitar;
    private GoogleMap mMap;
    private ImageView icoOpciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        textUser = findViewById(R.id.txtUsuario);
        origen = findViewById(R.id.textFieldOrigen);
        destino = findViewById(R.id.textFieldDestino);
        btnSolicitar = findViewById(R.id.botonSolicitar);
        icoOpciones = findViewById(R.id.icoOpciones);

        destino.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0 && origen.getText().toString().length() > 0) {
                    btnSolicitar.setEnabled(true);
                    Drawable buttonDrawable = btnSolicitar.getBackground();
                    buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                    DrawableCompat.setTint(buttonDrawable, Color.parseColor("#3E5286"));
                    btnSolicitar.setBackground(buttonDrawable);
                } else {
                    btnSolicitar.setEnabled(false);
                }
            }
        });

        btnSolicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMapa();
            }
        });

        String usuario = getIntent().getStringExtra("nombre");
        textUser.setText(String.format("%s", usuario));

        icoOpciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPanel();
            }
        });

        if (ContextCompat.checkSelfPermission(Mapa.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(Mapa.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(Mapa.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSION_REQUEST_CODE);


            }
        }


        // ------- GOOGLE MAPS (PREPARACION DE INICIO) -------------------------------------------------------------->

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // <------- GOOGLE MAPS (PREPARACION DE INICIO)--------------------------------------------------------------
    }


    // ------- GOOGLE MAPS (MAPA CARGADO) -------------------------------------------------------------->

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        float zoomLevel = 13.5f;

        // Add a marker in SLP and move the camera
        LatLng slp = new LatLng(22.1563641, -100.982904);
        mMap.addMarker(new MarkerOptions().position(slp).title("Marker in SLP"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(slp, zoomLevel));
        mMap.isMyLocationEnabled();
        mMap.getUiSettings().setZoomControlsEnabled(true);

        // Getting LocationManager object from System Service LOCATION_SERVICE
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Creating a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Getting the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);

        // Getting Current Location
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        Location location = locationManager.getLastKnownLocation(provider);

        if (location != null) {
            // Getting latitude of the current location
            double latitude = location.getLatitude();

            // Getting longitude of the current location
            double longitude = location.getLongitude();

            // Creating a LatLng object for the current location
            LatLng latLng = new LatLng(latitude, longitude);

            myPosition = new LatLng(latitude, longitude);

            googleMap.addMarker(new MarkerOptions().position(myPosition).title("Start"));

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition, zoomLevel));
        }
    }

    // <------- GOOGLE MAPS (MAPA CARGADO) --------------------------------------------------------------

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay!

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public void openMapa() {
        Intent intent = new Intent(this, Mapa.class);
        startActivity(intent);
    }

    public void loadPanel() {
        Intent intent = new Intent(this, Login_Sign.class);
        startActivity(intent);
    }
}
