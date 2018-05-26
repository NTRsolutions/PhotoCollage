package com.example.user.photocollage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity implements ImgAdapter.ItemClickListener{

    public static final String TAG = "Main";
    public static final String BASE_URL = "https://api.imgur.com";
    public static final String CLIENT_ID = "7e32cc7b8923f7b";
    public static final String ALBUM_ID = "pVqtv1r";
    public static final int numberOfColumns = 3;

    private ArrayList<ImgItem> imgList;
    private Retrofit retrofit;
    private Retrofit.Builder retrofitBuilder;
    private OkHttpClient.Builder httpClient;
    private ImgAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgList = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.imgsGrid);
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        adapter = new ImgAdapter(this, imgList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        Button getImgsButton = findViewById(R.id.GetButton);
        getImgsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                retrofitBuilder = new Retrofit.Builder().baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create());
                httpClient = new OkHttpClient.Builder();
                retrofit = retrofitBuilder.client(httpClient.build()).build();
                ImgurService imgurService = retrofit.create(ImgurService.class);

                Call<ImgurResponse> call = imgurService.getAlbumImages(ALBUM_ID);
                call.enqueue(new Callback<ImgurResponse>() {

                    @Override
                    public void onResponse(Call<ImgurResponse> call,
                                           Response<ImgurResponse> response) {

                        ImgurResponse data = response.body();
                        if (data != null) {
                            for (ImgurResponse.Image image : data.data.images) {
                                imgList.add(new ImgItem(image.link));
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ImgurResponse> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public void onItemClick(View view, int position)
    {
        Log.i(TAG, "onItemClick");
        Log.i(TAG, String.valueOf(position));

    }

    public interface ImgurService {

        @Headers("Authorization: Client-ID " + CLIENT_ID)
        @GET("/3/album/{albumHash}")
        Call<ImgurResponse> getAlbumImages(@Path("albumHash") String albumHash);
    }

}
