
package com.example.bktso2;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {
    Context context = SignInActivity.this;
    EditText userNameET, passET, confirmET;
    Button signInBT, loginBT;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        AnhXa();
        mAuth = FirebaseAuth.getInstance();
        signInBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = userNameET.getText().toString();
                String pass = passET.getText().toString();
                String confirm = confirmET.getText().toString();
                if(userName.isEmpty()){
                    userNameET.setError("Không thể để trống");
                    userNameET.requestFocus();
                    Toast.makeText(SignInActivity.this, "User Name is Empty", Toast.LENGTH_SHORT).show();
                }
                else
                    if(pass.isEmpty() || confirm.isEmpty()){
                        passET.setError("Không thể để trống");
                        passET.requestFocus();
                        Toast.makeText(SignInActivity.this, "Empty Pass", Toast.LENGTH_SHORT).show();
                    }
                    else
                        if(pass.equals(confirm))
                            DangKy(userName,pass);
                        else
                            Toast.makeText(context, "Pass is not match", Toast.LENGTH_SHORT).show();
            }
        });
        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConvertFromSignUptoLogIn();
            }
        });
    }
    public void AnhXa(){
        userNameET = findViewById(R.id.nameET);
        passET = findViewById(R.id.passET);
        confirmET = findViewById(R.id.passConfirmET);
        signInBT = findViewById(R.id.signUpBT);
        loginBT = findViewById(R.id.logInBT);
    }
    public void DangKy(String userName, String pass){
        mAuth.createUserWithEmailAndPassword(userName, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    System.out.println("Dang ky thanh cong");
                    Toast.makeText(SignInActivity.this, "Thanh cong", Toast.LENGTH_SHORT).show();
                    ConvertFromSignUptoLogIn();
                }
                else{
                    System.out.println("Dang ky that bai");
                    Toast.makeText(SignInActivity.this, "That bai", Toast.LENGTH_SHORT).show();
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                }
            }
        });
    }
    public void ConvertFromSignUptoLogIn(){
        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(intent);
    }
}