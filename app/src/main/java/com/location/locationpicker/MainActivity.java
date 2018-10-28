package com.location.locationpicker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity {

    private ImageButton address;
    int PLACE_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        address= (ImageButton) findViewById(R.id.locationPickButton);
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isConnected = Util.checkConnectivity(getApplicationContext());
                if (isConnected == true) {


                    PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                    Intent intent;

                    try {

                        intent = builder.build(MainActivity.this);
                        startActivityForResult(intent, PLACE_PICKER_REQUEST);

                    } catch (GooglePlayServicesRepairableException e) {
                        e.printStackTrace();
                    } catch (GooglePlayServicesNotAvailableException e) {
                        e.printStackTrace();
                    }

                }else {

                            Toast.makeText(MainActivity.this, "No Internet!",
                                    Toast.LENGTH_LONG).show();
                }

            }
        });


    }


    protected void onActivityResult(int requestCode,int resultCode,Intent data){

        if (requestCode == PLACE_PICKER_REQUEST){

            if (resultCode == RESULT_OK){

                Place place = PlacePicker.getPlace(data,this);
                String adLocation = String.format("Place: %s",place.getAddress());


//                LatLng loc = place.getLatLng();
                     String loc = place.getLatLng().toString().replace("lat/lng:","");


                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "http://maps.google.com/?q="+loc);
                sendIntent.setType("text/plain");

                startActivity(Intent.createChooser(sendIntent,"Share link!"));

            }

        }

    }
}
