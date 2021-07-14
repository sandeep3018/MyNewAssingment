package com.example.mynewassingment.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.mynewassingment.R;
import com.example.mynewassingment.adapter.DatabaseClient;
import com.example.mynewassingment.adapter.Register;

import java.util.Calendar;
import java.util.Date;

public class SignUpActivity extends AppCompatActivity {
    EditText editTextName,editTextEmail,editTextPassword,editTextcnfpassword;
    ImageView signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileRegister(editTextName.getText().toString().trim(),editTextEmail.getText().toString().trim(),editTextPassword.getText().toString().trim(),editTextcnfpassword.getText().toString().trim());

            }
        });
          }
    private void ProfileRegister(String sName,String sEmail,String sPassword,String sCnfpassword) {

        // final String sFinishBy = "Time: "+currentTime;
        if (sName.isEmpty()) {
            editTextName.setError("Name required");
            editTextName.requestFocus();
            return;
        }

        if (sEmail.isEmpty()) {
            editTextEmail.setError("Email required");
            editTextEmail.requestFocus();
            return;
        }

        if (sPassword.isEmpty()) {
            editTextPassword.setError("Password required");
            editTextPassword.requestFocus();
            return;
        }
        if (sCnfpassword.isEmpty()&&!sCnfpassword.equals(sPassword)) {
            editTextcnfpassword.setError("Password Mismatch");
            editTextcnfpassword.requestFocus();
            return;
        }

        class RegisterProfile extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                Register register = new Register();
                register.setName(sName);
                register.setEmail(sEmail);
                register.setPassword(sPassword);


                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .insert(register);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(SignUpActivity.this,SignInActivity.class));
                // Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        RegisterProfile rp = new RegisterProfile();
        rp.execute();
    }
    public void init()
    {
        editTextName=findViewById(R.id.edtxtName);
        editTextEmail=findViewById(R.id.edtxtEmail);
        editTextPassword=findViewById(R.id.edtxtpassword1);
        editTextcnfpassword=findViewById(R.id.edtxt_confirm_password);
        signup=findViewById(R.id.signup);
    }
}