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

public class RegistationActivity extends AppCompatActivity {

    private TextInputEditText UserNameEdit,PswrdEdit,CnfrmPswrdEdit;
    private Button Registrbtn;
    private ProgressBar prgbarloding;
    private FirebaseAuth mAuth;
    private TextView loginTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registation);

        UserNameEdit = findViewById(R.id.idEditUserName);
        PswrdEdit = findViewById(R.id.idEditCnfrmPwd);
        CnfrmPswrdEdit = findViewById(R.id.idEditCnfrmPwd);
        Registrbtn = findViewById(R.id.idRegistrbtn);
        prgbarloding = findViewById(R.id.idprgbarloding);
        loginTextview = findViewById(R.id.idTetVlogin);
        mAuth = FirebaseAuth.getInstance();

        loginTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(RegistationActivity.this,LoginActivity.class);
                startActivity(i);

            }
        });

        Registrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                prgbarloding.setVisibility(View.VISIBLE);
                String userName = UserNameEdit.getText().toString();
                String pswrd = PswrdEdit.getText().toString();
                String CanfrmPaaswrd = CnfrmPswrdEdit.getText().toString();

                if (!pswrd.equals(CanfrmPaaswrd)){
                    Toast.makeText(RegistationActivity.this, "Passwords are not same", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(userName) && TextUtils.isEmpty(CanfrmPaaswrd) ){
                    Toast.makeText(RegistationActivity.this, "Please add your credentials", Toast.LENGTH_SHORT).show();
                }else{
                    mAuth.createUserWithEmailAndPassword(userName,pswrd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){

                                prgbarloding.setVisibility(View.GONE);
                                Toast.makeText(RegistationActivity.this, "User Registration Done", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(RegistationActivity.this,LoginActivity.class);
                                startActivity(i);
                                finish();
                            }else{

                                prgbarloding.setVisibility(View.GONE);
                                Toast.makeText(RegistationActivity.this, "Fail to Register Please Try Again!!!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

            }
        });
    }
}

