package com.example.api_android;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class MainActivity extends AppCompatActivity {
    Button getButton;
    EditText
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        getButton = findViewById(R.id.getButton);
        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpHandler handler = new OkHttpHandler();
                handler.execute();
            }
        });
    }

    public class OkHttpHandler extends AsyncTask<Void, Void, List<Item>> {
        @Override
        protected List<Item> doInBackground(Void... voids) {
            Request.Builder builder = new Request.Builder();

            MediaType JSON = MediaType.get("application/json; charset=utf-8");
            String jsonRequest = "{\n" +
                    "  \"math\": 100,\n" +
                    "  \"inform\": 100,\n" +
                    "  \"social\": 100,\n" +
                    "  \"rus\": 100,\n" +
                    "  \"chemistry\": 100,\n" +
                    "  \"physics\": 100,\n" +
                    "  \"eng\": 100,\n" +
                    "  \"geo\": 100\n" +
                    "}";
            RequestBody body = RequestBody.create(jsonRequest, JSON);

            Request request = builder.url("https://nti.urfu.ru/ege-calc/json")
                    .post(body)
                    .build();
            OkHttpClient client = new OkHttpClient().newBuilder().build();

            try {
                Response response = client.newCall(request).execute();
                System.out.println(response);
                if (!response.isSuccessful()) {
                    return null;
                }
                String responseBody = response.body().string();
                System.out.println(responseBody);
                JSONArray jsonArray = new JSONArray(responseBody);

                List<Item> itemList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);
                    String specialty_name = item.getString("specialty_name");

                    int math_point = item.getInt("math_point");
                    int rus_point = item.getInt("rus_point");
                    int itk_point = item.getInt("itk_point");
                    int chem_point = item.getInt("chem_point");
                    int soc_point = item.getInt("soc_point");
                    int phys_point = item.getInt("phys_point");
                    int en_point = item.getInt("en_point");

                    itemList.add(new Item(specialty_name, math_point, rus_point, itk_point, chem_point, soc_point, phys_point, en_point));
                }

                return itemList;

            } catch (IOException | JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Item> items) {
            super.onPostExecute(items);
            if (items != null) {
                Intent intent = new Intent(MainActivity.this, EGEResult.class);
                intent.putParcelableArrayListExtra("items", (ArrayList<Item>) items);
                startActivity(intent);
            }
        }
    }
}