package com.example.thampotter.quanlynhac.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.thampotter.quanlynhac.R;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String url = "https://androidzing.000webhostapp.com/Server/dangnhap.php";
    TextView tvDangKyMoi;
    EditText edusername, edpassword;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
        onClick();
    }

    private void onClick() {
        tvDangKyMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DangKyThanhVien.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edusername.getText().toString().trim();
                String pass = edpassword.getText().toString().trim();
                if (user.isEmpty() || pass.isEmpty()){
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    dangNhap(url);
                }
            }
        });
    }

    private void dangNhap(String url){
        RequestQueue  requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")){
                            Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, NhacActivity.class));
                        }else {
                            Toast.makeText(MainActivity.this, "Vui lòng kiểm tra lại tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Vui lòng kiểm tra lại kết nối internet của bạn và thử lại!", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username",edusername.getText().toString().trim());
                params.put("password",edpassword.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


    private void anhXa() {
        tvDangKyMoi = findViewById(R.id.tvdangkymoi);
        edusername = findViewById(R.id.edusername);
        edpassword = findViewById(R.id.edpassword);
        btnLogin = findViewById(R.id.btnlogin);
    }
}
