package com.example.myapplicationtest1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    int brigade;

    String deviceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Button register = findViewById(R.id.registerButton);
        TextInputEditText deviceInput = findViewById(R.id.inputDeviceID);
        TextInputEditText phoneNumberInput = findViewById(R.id.inputPhoneNumber);

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        brigade = sh.getInt("brigade", 0);
        deviceId = sh.getString("device", "");
        TextView textView = findViewById(R.id.informationText);
        if (brigade == 0){
            textView.setText("Urządzenie niezalogowane");
        }
        else
            textView.setText("Urządzenie zalogowane, brygada nr: "+ brigade);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String device = String.valueOf(deviceInput.getText());
                String phoneNumber = String.valueOf(phoneNumberInput.getText());
                postRegister(device, phoneNumber);
            }
        });

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("10.0.2.2");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        factory.setPort(5672);
        statusCheck();

        try{
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            String queueName = "rabbitmq.queue";
            channel.exchangeDeclare("exchange", "direct");
            channel.queueBind(queueName, "exchange", "543");


            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" +
                delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
                sendLocation(brigade, deviceId);
                Gson gson = new Gson();
                GetLocationMessage glm = gson.fromJson(message, GetLocationMessage.class);
                System.out.println("Base:" + glm.getBaseId());
                System.out.println("Base:" + brigade);
                if(glm.getBaseId() == brigade) {
                    textView.setText(glm.getText());
                }
            };

            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });

        }catch (IOException e) {
            //Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        catch (TimeoutException e) {
            //Toast.makeText(MainActivity.this, "Timeout error!", Toast.LENGTH_LONG).show();
        }
    }


    public void statusCheck() {
        System.out.println("check location status");
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
            System.out.println("no gps");

        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        brigade = sh.getInt("brigade", 0);
        deviceId = sh.getString("device", "");

        TextInputEditText deviceInput = findViewById(R.id.inputDeviceID);

        TextView textView = findViewById(R.id.informationText);
        if (brigade == 0){
            textView.setText("Urządzenie niezalogowane");
        }
        else
            textView.setText("Urządzenie zalogowane, brygada nr: "+ brigade);

        deviceInput.setText(deviceId);

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        myEdit.putInt("brigade", brigade);
        myEdit.putString("device", deviceId);
        myEdit.apply();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission Granted!", Toast.LENGTH_LONG).show();
        }

    }

    private void sendLocation(int brigade, String deviceId) {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
            return;
        }

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            TextView textView = findViewById(R.id.locationText);
                            textView.setText("lon: " + location.getLongitude() + " lat: " +  + + location.getLatitude());
                            if(brigade != 0 && !deviceId.equals("none")) {
                                postLocation(deviceId, location.getLatitude(), location.getLongitude());
                            }
                        }
                    }
                });
    }

    private void postLocation(String device, Double lat, Double lon) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8082/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        DeviceLocation deviceLocation = new DeviceLocation(device, lat, lon);
        System.out.println("DeviceId: " + device);
        Call<String> call = retrofitAPI.location(deviceLocation);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String responseFromAPI = response.body();
                System.out.println("location check look here");
                System.out.println("location: " + responseFromAPI);
                Toast.makeText(MainActivity.this, responseFromAPI, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    private void postRegister(String device, String phoneNumber) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8082/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        DeviceRegister deviceRegister = new DeviceRegister(device, phoneNumber);
        Call<String> call = retrofitAPI.device(deviceRegister);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String responseFromAPI = response.body();
                System.out.println("register: " + responseFromAPI);
                TextView textView = findViewById(R.id.informationText);
                if(!responseFromAPI.equals("NoDevice") && !responseFromAPI.equals("NoUser")) {
                    brigade = Integer.parseInt(responseFromAPI);
                    deviceId = device;
                    textView.setText("Urządzenie zalogowane, brygada nr: "+ brigade);
                }

                else {
                    brigade = 0;
                    textView.setText("Urządzenie niezalogowane");
                    deviceId = "";
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }



}

