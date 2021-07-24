package com.paparazziteam.retrofit_;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.paparazziteam.retrofit_.interfaces.MyAPICall;
import com.paparazziteam.retrofit_.models.UserModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewMessage = findViewById(R.id.textViewMessage);

        //Retrofit Builder

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://run.mocky.io/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //instance for interface
        MyAPICall myAPICall = retrofit.create(MyAPICall.class);

        Call<UserModel> call = myAPICall.getData();

        call.enqueue(new Callback<UserModel>()
        {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                //Checking for the response
                if(response.code() != 200)
                {
                    textViewMessage.setText("Check the conecction");

                }else
                {
                    if(response.code() == 200)
                    {
                        String json = "";

                        json = "ID= " + response.body().getId() + "\n "+
                                "userID"+ response.body().getUserId()+
                                "\n title= " + response.body().getTitle()+
                                "\n Completed= "+response.body().isStatus();

                        textViewMessage.setText(json);
                    }
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });


    }


}