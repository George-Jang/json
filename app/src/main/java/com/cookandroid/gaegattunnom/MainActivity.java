package com.cookandroid.gaegattunnom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;



import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final  String TAG = getClass().getSimpleName();

    // server의 url을 적어준다
    private final String BASE_URL = "http://ec2-13-125-58-81.ap-northeast-2.compute.amazonaws.com:8080";
    private MyAPI mMyAPI;

    private TextView mListTv;

    private Button mGetButton;
    private Button mPostButton;
    private Button mPatchButton;
    private Button mDeleteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListTv = findViewById(R.id.result1);

        mGetButton = findViewById(R.id.button1);
        mGetButton.setOnClickListener(this);
        mPostButton = findViewById(R.id.button2);
        mPostButton.setOnClickListener(this);
        mPatchButton = findViewById(R.id.button3);
        mPatchButton.setOnClickListener(this);
        mDeleteButton = findViewById(R.id.button4);
        mDeleteButton.setOnClickListener(this);

        initMyAPI(BASE_URL);
    }

    private void initMyAPI(String baseUrl){

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.addInterceptor(chain -> {
            Request request = chain.request().newBuilder().addHeader("password","1111").build();
            return chain.proceed(request);
        });

        OkHttpClient client = builder.build();


        Log.d(TAG,"initMyAPI : " + baseUrl);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        mMyAPI = retrofit.create(MyAPI.class);
    }


    @Override
    public void onClick(View v) {
        if( v == mGetButton){
            Log.d(TAG,"GET");
            Call<List<PostItem>> getCall = mMyAPI.get_posts(4);
            getCall.enqueue(new Callback<List<PostItem>>() {
                @Override
                public void onResponse(Call<List<PostItem>> call, Response<List<PostItem>> response) {
                    if( response.isSuccessful()){
                        List<PostItem> mList = response.body();
                        String result ="";
                        for( PostItem item : mList){
                            result += "title : " + item.getUserId() + " text: " + item.getAccountPassword() + "\n";
                        }
                        mListTv.setText(result);
                        Toast.makeText(getApplicationContext(),"에라이",Toast.LENGTH_LONG).show();
                    }else {
                        Log.d(TAG,"Status Code : " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<PostItem>> call, Throwable t) {
                    Log.d(TAG,"Fail msg : " + t.getMessage());
                    Toast.makeText(getApplicationContext(),"에라이2",Toast.LENGTH_LONG).show();
                }
            });
        }else if(v == mPostButton){
            Log.d(TAG,"POST");


            PostItem item = new PostItem();
            item.setUserId("Andoid");
            item.setAccountPassword("1111");
            Call<PostItem> postCall = mMyAPI.post_posts(item);
            postCall.enqueue(new Callback<PostItem>() {
                @Override
                public void onResponse(Call<PostItem> call, Response<PostItem> response) {
                    if(response.isSuccessful()){
                        Log.d(TAG,"등록 완료");
                    }else {
                        Log.d(TAG,"Status Code : " + response.code());
                        Log.d(TAG,response.errorBody().toString());
                        Log.d(TAG,call.request().body().toString());
                    }
                }

                @Override
                public void onFailure(Call<PostItem> call, Throwable t) {
                    Log.d(TAG,"Fail msg : " + t.getMessage());
                }
            });
        }else if( v == mPatchButton){
            Log.d(TAG,"PATCH");
            PostItem item = new PostItem();
            item.setUserId("android patch title");
            item.setAccountPassword("android patch text");
            //pk 값은 임의로 하드코딩하였지만 동적으로 setting 해서 사용가능
            Call<PostItem> patchCall = mMyAPI.patch_posts(1,item);
            patchCall.enqueue(new Callback<PostItem>() {
                @Override
                public void onResponse(Call<PostItem> call, Response<PostItem> response) {
                    if(response.isSuccessful()){
                        Log.d(TAG,"patch 성공");
                    }else{
                        Log.d(TAG,"Status Code : " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<PostItem> call, Throwable t) {
                    Log.d(TAG,"Fail msg : " + t.getMessage());
                }
            });


        }else if( v == mDeleteButton){
            Log.d(TAG,"DELETE");
            // pk 값은 임의로 변경가능
            Call<PostItem> deleteCall = mMyAPI.delete_posts(2);
            deleteCall.enqueue(new Callback<PostItem>() {
                @Override
                public void onResponse(Call<PostItem> call, Response<PostItem> response) {
                    if(response.isSuccessful()){
                        Log.d(TAG,"삭제 완료");
                    }else {
                        Log.d(TAG,"Status Code : " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<PostItem> call, Throwable t) {
                    Log.d(TAG,"Fail msg : " + t.getMessage());
                }
            });
        }
    }
}