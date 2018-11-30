package research.roshanpaturkar.com.vollyintegration.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import research.roshanpaturkar.com.vollyintegration.R;
import research.roshanpaturkar.com.vollyintegration.api.Api;

public class RegistrationActivity extends AppCompatActivity {

    EditText FULLNAME;
    EditText EMAIL;
    EditText USERNAME;
    EditText PASSWORD;
    Button REGISTER;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        queue = Volley.newRequestQueue(this);

        FULLNAME=(EditText)findViewById(R.id.xmyfname);
        EMAIL=(EditText)findViewById(R.id.xmyemail);
        USERNAME=(EditText)findViewById(R.id.xmyusername);
        PASSWORD=(EditText)findViewById(R.id.xmypassword);
        REGISTER=(Button)findViewById(R.id.regbtn);

        REGISTER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(RegistrationActivity.this, "Registering You.....", Toast.LENGTH_SHORT).show();
                final String MYFULLNAME=FULLNAME.getText().toString().trim();
                final String MYEMAIL=EMAIL.getText().toString().trim();
                final String MYUSERNAME=USERNAME.getText().toString().trim();
                final String MYPASSWORD=PASSWORD.getText().toString().trim();
                StringRequest request = new StringRequest(Request.Method.POST, Api.REGISTRATION, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("Response >>> ", response);
                            Toast.makeText(RegistrationActivity.this, response, Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                            Log.d("Error >>> ", e.toString());
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.d("Volley Error >>> ", error.toString());
                        Toast.makeText(RegistrationActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                ){
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> data = new HashMap<String, String>();
                        data.put("fullname", MYFULLNAME);
                        data.put("email", MYEMAIL);
                        data.put("name", MYUSERNAME);
                        data.put("pass", MYPASSWORD);

                        return data;
                    }
                };
                queue.add(request);
            }
        });

    }
}
