package research.roshanpaturkar.com.vollyintegration.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity {

    EditText emailEditText, passwordEditText;
    Button loginButton;

    TextView signUpTextView;

    RequestQueue queue;

    String userName;
    String passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(this);

        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        loginButton = (Button) findViewById(R.id.loginButton);
        signUpTextView = (TextView) findViewById(R.id.registerTextView);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "Login in progress.....", Toast.LENGTH_SHORT).show();

                userName = emailEditText.getText().toString().trim();
                passWord = passwordEditText.getText().toString().trim();

                StringRequest request = new StringRequest(Request.Method.POST, Api.LOGIN, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("Response >>> ", response);

                            if (response.equals("true")){
                                emailEditText.setText("");
                                passwordEditText.setText("");
                                startActivity(new Intent(MainActivity.this, Dashboard.class));
                                finish();
                            } else {
                                emailEditText.setError("Invalid Username or Password");
                                passwordEditText.setError("Invalid Username or Password");
                            }

                        }catch (Exception e){
                            Log.d("Error >>> ", e.toString());
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Volley Error >>> ", error.toString());
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> data = new HashMap<String, String>();
                        data.put("name", userName);
                        data.put("pass", passWord);

                        return data;
                    }
                };

                queue.add(request);
            }
        });

        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });
    }
}
