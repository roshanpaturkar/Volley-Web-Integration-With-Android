package research.roshanpaturkar.com.vollyintegration.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import research.roshanpaturkar.com.vollyintegration.R;
import research.roshanpaturkar.com.vollyintegration.adapters.PhoneListAdapter;
import research.roshanpaturkar.com.vollyintegration.api.Api;
import research.roshanpaturkar.com.vollyintegration.models.PhoneDetails;

public class Dashboard extends AppCompatActivity {

    private RecyclerView phoneDetailsRecyclerView;
    private RecyclerView.Adapter adapter;
    private List<PhoneDetails> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        phoneDetailsRecyclerView = (RecyclerView) findViewById(R.id.phoneDetailsRecyclerView);

        phoneDetailsRecyclerView.setHasFixedSize(true);
        phoneDetailsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = new ArrayList<>();

        loadRecycyler();
    }

    private void loadRecycyler() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data..");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, Api.GETPHONEDETAILS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i=0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        PhoneDetails phoneDetails = new PhoneDetails(
                                jsonObject.getString("phonename"),
                                jsonObject.getString("model"),
                                jsonObject.getString("date"),
                                jsonObject.getString("phonetype"),
                                jsonObject.getString("image")
                        );
                        listItems.add(phoneDetails);
                    }
                    adapter = new PhoneListAdapter(listItems, getApplicationContext());
                    phoneDetailsRecyclerView.setAdapter(adapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Volley Error >>> ", error.toString());
                Toast.makeText(Dashboard.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}
