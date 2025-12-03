package com.example.api_android;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    Button getButton;
    EditText mathInput, rusInput, itkInput, chemInput, socInput, physInput, enInput;
    TextView errorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        getButton = findViewById(R.id.getButton);

        mathInput = findViewById(R.id.mathInput);
        rusInput = findViewById(R.id.rusInput);
        itkInput = findViewById(R.id.itkInput);
        chemInput = findViewById(R.id.chemInput);
        socInput = findViewById(R.id.socInput);
        physInput = findViewById(R.id.physInput);
        enInput = findViewById(R.id.enInput);

        errorTextView = findViewById(R.id.errorTextView);

        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mathValue = mathInput.getText().toString();
                final String rusValue = rusInput.getText().toString();
                final String itkValue = itkInput.getText().toString();
                final String chemValue = chemInput.getText().toString();
                final String socValue = socInput.getText().toString();
                final String physValue = physInput.getText().toString();
                final String enValue = enInput.getText().toString();

                if ((!mathValue.isEmpty() &&
                        !rusValue.isEmpty() &&
                        !itkValue.isEmpty() &&
                        !chemValue.isEmpty() &&
                        !socValue.isEmpty() &&
                        !physValue.isEmpty() &&
                        !enValue.isEmpty()
                ) && (Integer.parseInt(mathValue) > 0 && Integer.parseInt(mathValue) <= 100 &&
                        Integer.parseInt(rusValue) > 0 && Integer.parseInt(rusValue) <= 100 &&
                        Integer.parseInt(itkValue) >= 0 && Integer.parseInt(itkValue) <= 100 &&
                        Integer.parseInt(chemValue) >= 0 && Integer.parseInt(chemValue) <= 100 &&
                        Integer.parseInt(socValue) >= 0 && Integer.parseInt(socValue) <= 100 &&
                        Integer.parseInt(physValue) >= 0 && Integer.parseInt(physValue) <= 100 &&
                        Integer.parseInt(enValue) >= 0 && Integer.parseInt(enValue) <= 100
                )) {
                    OkHttpHandler handler = new OkHttpHandler(
                            mathValue,
                            rusValue,
                            itkValue,
                            chemValue,
                            socValue,
                            physValue,
                            enValue);
                    handler.execute();
                } else {
                    errorTextView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public class OkHttpHandler extends AsyncTask<Void, Void, List<Item>> {
        private final String mathValue;
        private final String rusValue;
        private final String itkValue;
        private final String chemValue;
        private final String socValue;
        private final String physValue;
        private final String enValue;

        public OkHttpHandler(String mathValue, String rusValue, String itkValue, String chemValue, String socValue, String physValue, String enValue) {
            this.mathValue = mathValue;
            this.rusValue = rusValue;
            this.itkValue = itkValue;
            this.chemValue = chemValue;
            this.socValue = socValue;
            this.physValue = physValue;
            this.enValue = enValue;
        }

        @Override
        protected List<Item> doInBackground(Void... voids) {
            Request.Builder builder = new Request.Builder();

            MediaType JSON = MediaType.get("application/json; charset=utf-8");
            String req = "{\"math\":%s,\"inform\":%s,\"social\":%s,\"rus\":%s,\"chemistry\":%s,\"physics\":%s,\"eng\":%s,\"geo\":0}";
            String jsonRequest = String.format(req, mathValue, itkValue, socValue, rusValue, chemValue, physValue, enValue);
            RequestBody body = RequestBody.create(jsonRequest, JSON);

            Request request = builder.url("https://nti.urfu.ru/ege-calc/json").post(body).build();
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