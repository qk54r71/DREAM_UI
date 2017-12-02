package com.plan2.example_rest_api.ParkSDK.Data;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.plan2.example_rest_api.ParkSDK.Debug.Loging;

/**
 * Created by park on 2017-01-30.
 */

public class GPS_Location_data {

    private static GPS_Location_data gps_location_data;
    private GPS_data data;

    public static Double smLatitude;
    public static Double smLongitude;


    private static final String LOG_NAME = "GPS_Location_data";

    public GPS_Location_data(Context context) {
        data = startGPS(context);
    }

    public synchronized static GPS_Location_data newInstance(Context context) {

        if (gps_location_data == null) {
            gps_location_data = new GPS_Location_data(context);
        }

        return gps_location_data;
    }

    public void setGPS_finish() {
        if (data != null) {
            data.getLocationManager().removeUpdates(data.getLocationListener());
        }
    }

    private GPS_data startGPS(final Context context) {

        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // GPS 프로바이더 사용가능여부
        Boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 네트워크 프로바이더 사용가능여부
        Boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                Loging.i(LOG_NAME, "onLocationChanged(Location location) Location : " + location);
                double lat = location.getLatitude();
                double lng = location.getLongitude();

                smLatitude = lat;
                smLongitude = lng;

                if (context instanceof GPS_Data_receive) {
                    ((GPS_Data_receive) context).onReceiveData(lat, lng);
                }

            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };


        // Register the listener with the Location Manager to receive location updates
        if (
                ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        &&
                        ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 0, locationListener);

        String locationProvider = LocationManager.GPS_PROVIDER;
        Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);

        Loging.i(LOG_NAME, "lastKnownLocation : " + lastKnownLocation);

        if (lastKnownLocation != null) {
            smLatitude = lastKnownLocation.getLatitude();
            smLongitude = lastKnownLocation.getLongitude();
        }


        return new GPS_data(locationManager, locationListener);
    }

    public interface GPS_Data_receive {
        void onReceiveData(Double lat, Double lng);
    }

    class GPS_data {
        private LocationManager locationManager;
        private LocationListener locationListener;

        public GPS_data(LocationManager locationManager, LocationListener locationListener) {
            this.locationManager = locationManager;
            this.locationListener = locationListener;
        }

        public LocationManager getLocationManager() {
            return locationManager;
        }

        public void setLocationManager(LocationManager locationManager) {
            this.locationManager = locationManager;
        }

        public LocationListener getLocationListener() {
            return locationListener;
        }

        public void setLocationListener(LocationListener locationListener) {
            this.locationListener = locationListener;
        }
    }
}

