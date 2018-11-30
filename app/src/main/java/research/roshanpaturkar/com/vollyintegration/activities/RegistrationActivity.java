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

    EditText fullName;
    EditText email ;
    EditText userName;
    EditText password;
    Button register;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        queue = Volley.newRequestQueue(this);

        fullName = (EditText)findViewById(R.id.xmyfname);
        email = (EditText)findViewById(R.id.xmyemail);
        userName = (EditText)findViewById(R.id.xmyusername);
        password = (EditText)findViewById(R.id.xmypassword);
        register = (Button)findViewById(R.id.regbtn);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(RegistrationActivity.this, "Registering You.....", Toast.LENGTH_SHORT).show();
                final String myFullName = fullName.getText().toString().trim();
                final String myEmail = email.getText().toString().trim();
                final String myUserName = userName.getText().toString().trim();
                final String myPassword = password.getText().toString().trim();
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
                        data.put("fullname", myFullName);
                        data.put("email", myEmail);
                        data.put("name", myUserName);
                        data.put("pass", myPassword);

                        return data;
                    }
                };
                queue.add(request);
            }
        });

    }
}
