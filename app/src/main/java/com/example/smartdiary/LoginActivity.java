package com.example.smartdiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText UserNameEdit,PswrdEdit;
    private Button Loginbtn;
    private ProgressBar prgbarloding;
    private FirebaseAuth mAuth;
    private TextView RegisterTextview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UserNameEdit = findViewById(R.id.idEditUserName);
        PswrdEdit = findViewById(R.id.idEditCnfrmPwd);
        Loginbtn = findViewById(R.id.idLoginbtn);
        prgbarloding = findViewById(R.id.idprgbarloding);
        RegisterTextview = findViewById(R.id.idTetRegister);
        mAuth = FirebaseAuth.getInstance();

        RegisterTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,RegistationActivity.class);
                startActivity(i);
            }
        });

        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prgbarloding.setVisibility(view.VISIBLE);
                String userName = UserNameEdit.getText().toString();
                String Password = PswrdEdit.getText().toString();
                if(TextUtils.isEmpty(userName) && TextUtils.isEmpty(Password)){


                    Toast.makeText(LoginActivity.this, "Please Enter your credentials", Toast.LENGTH_SHORT).show();
                    return;
                }else{

                    mAuth.signInWithEmailAndPassword(userName,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){

                                prgbarloding.setVisibility(view.GONE);
                                Toast.makeText(LoginActivity.this, "Loginn Succesful", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(i);
                                finish();

                            }else{
                                prgbarloding.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, "Fail to login", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user!=null){

            Intent i = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(i);
            this.finish();
        }
    }
}

