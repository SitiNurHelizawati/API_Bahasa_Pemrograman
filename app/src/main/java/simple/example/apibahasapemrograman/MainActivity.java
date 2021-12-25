package simple.example.apibahasapemrograman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.disklrucache.DiskLruCache;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.Iterator;
public class MainActivity extends AppCompatActivity {
    ArrayList<DataBahasa> dataBahasaPemrogramans = new ArrayList();
    protected final String urlAPI = "https://ewinsutriandi.github.io/mockapi/bahasa_pemrograman.json";
    JSONObject dataBahasaPemrograman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDataBahasaPemrograman();
    }

    void setupListview() {
        ListView listView = findViewById(R.id.listView);
        CustomAdapter adapter = new CustomAdapter(this, dataBahasaPemrogramans);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onItemClick);
    }

    private AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            DataBahasa fSELECTED = dataBahasaPemrogramans.get(position);
            Toast.makeText(MainActivity.this, fSELECTED.getReadMore(), Toast.LENGTH_LONG).show();
            toLink(fSELECTED);
        }
    };


    void getDataBahasaPemrograman() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, urlAPI, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dataBahasaPemrograman = response;
                        refresView();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("erorr", error.toString());
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }

    void refresView() {
        Iterator<String> key = dataBahasaPemrograman.keys();
        while(key.hasNext()) {
            String nameBahasaPemrograman = key.next();
            try {
                JSONObject data = dataBahasaPemrograman.getJSONObject(nameBahasaPemrograman);
                String description = data.getString("description");
                String logo_url= data.getString("logo_url");
                String helloWord= data.getString("helloWord");
                String readMore= data.getString("readMore");

                dataBahasaPemrogramans.add(new DataBahasa(nameBahasaPemrograman,description, logo_url, readMore, helloWord));
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }

        setupListview();
    }

    void toLink(DataBahasa data) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(data.getReadMore()));
        startActivity(intent);
    }
}

