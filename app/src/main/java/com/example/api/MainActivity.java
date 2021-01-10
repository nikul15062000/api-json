package com.example.api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<Datum> listvalue = new ArrayList<>();
    List<Support> supportsvalue = new ArrayList<>();
TextView text1,text2,text3,text4,text5,text;
ImageView image;
RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1=findViewById(R.id.text1);
        text2=findViewById(R.id.text2);
        text3=findViewById(R.id.text3);
        text4=findViewById(R.id.text4);
        text5=findViewById(R.id.text5);
        text=findViewById(R.id.text);
//        image=findViewById(R.id.image);
        recyclerView=findViewById(R.id.recyclerview);
//        https://reqres.in/api/users?delay=3

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonApi jsonApi
                = retrofit.create(com.example.api.jsonApi.class);


        Call<PostModal> call = jsonApi.postlist(3);


        call.enqueue(new Callback<PostModal>() {
            @Override
            public void onResponse(Call<PostModal> call, Response<PostModal> response) {

                if(response.isSuccessful() && response.body() != null){
                    PostModal postModalList = response.body();

                    text1.setText(postModalList.getPage().toString());
                    text2.setText(postModalList.getPerPage().toString());
                    text3.setText(postModalList.getTotal().toString());
                    text4.setText(postModalList.getTotalPages().toString());


                    for(Datum datumList : postModalList.getData()){
                        listvalue.add(datumList);
                    }

                    postModalList.getSupport().getText();
                    text.setText(postModalList.getSupport().getText());
//                    image.setImageURI(Uri.parse(postModalList.getSupport().getUrl()));
                   /* Picasso.get().load(Uri.parse(postModalList.getSupport().getUrl())).into(image);*/
                }
            }

            @Override
            public void onFailure(Call<PostModal> call, Throwable t) {

            }
        });

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomAdapter customAdapter=new CustomAdapter(MainActivity.this,listvalue);
                RecyclerView.LayoutManager manager=new GridLayoutManager(MainActivity.this,1);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(customAdapter);


              /*  for(int i=0;i<listvalue.size();i++){

                }*/
            }
        });
    }
}