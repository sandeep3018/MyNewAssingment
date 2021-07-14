package com.example.mynewassingment.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mynewassingment.R;
import com.example.mynewassingment.adapter.DatabaseClient;
import com.example.mynewassingment.adapter.Register;

import java.util.List;

public class SignInActivity extends AppCompatActivity {
    EditText editRegEmail,editTextPassword;
    Button login;
    TextView textViewsignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        editRegEmail=findViewById(R.id.siginedtxtEmail);
        editTextPassword=findViewById(R.id.siginedtxtpassword1);
        login=findViewById(R.id.login);
        textViewsignup=findViewById(R.id.signup);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTasks(editRegEmail.getText().toString().trim(),editTextPassword.getText().toString().trim());
            }
        });

        textViewsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this,SignUpActivity.class));
            }
        });




    }
    private void getTasks(String sEmail,String sPassword) {



        if (sEmail.isEmpty()) {
            editRegEmail.setError("Email required");
            editRegEmail.requestFocus();
            return;
        }

        if (sPassword.isEmpty()) {
            editTextPassword.setError("Password required");
            editTextPassword.requestFocus();
            return;
        }


        class GetTasks extends AsyncTask<Void, Void, List<Register>> {

            @Override
            protected List<Register> doInBackground(Void... voids) {
                List<Register> registerList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .taskDao()
                        .getAll();
                return registerList;
            }

            @Override
            protected void onPostExecute(List<Register> registers) {
                super.onPostExecute(registers);
                if(editRegEmail.getText().toString().trim().equals(registers.get(0).getEmail())&&editTextPassword.getText().toString().trim().equals(registers.get(0).getPassword()))
                {

                    startActivity(new Intent(SignInActivity.this,MainActivity2.class));
                    Toast.makeText(SignInActivity.this, registers.get(0).getName()+",You are successfully login", Toast.LENGTH_LONG).show();
                }
                else if(!editRegEmail.getText().toString().trim().equals(registers.get(0).getEmail()))
                {
                    Toast.makeText(SignInActivity.this, "Email are enter Wrong", Toast.LENGTH_SHORT).show();
                }
                else if(!editTextPassword.getText().toString().trim().equals(registers.get(0).getPassword()))
                {
                    Toast.makeText(SignInActivity.this, "Password are enter Wrong"+registers.get(0).getPassword(), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(SignInActivity.this, "Something Went  Wrong"+registers.get(0).getEmail()+"//"+registers.get(0).getPassword(), Toast.LENGTH_SHORT).show();
                }

            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }
}