package grow.plus.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import grow.plus.app.R;

import com.google.firebase.auth.FirebaseAuth;

public class BemVindo extends AppCompatActivity {

    private Button botaologin;
    private TextView textviewregistar;
    private TextView textviewnaoemembro;
    FirebaseAuth fAuth;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_bem_vindo);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

            botaologin=(Button) findViewById(R.id.buttonLoginBemvindo);
            textviewnaoemembro = (TextView) findViewById(R.id.textView5);
            textviewregistar = (TextView) findViewById(R.id.textViewRegistar);

            fAuth = FirebaseAuth.getInstance();

            if(fAuth.getCurrentUser() != null){
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }

            textviewregistar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent outraActivity = new Intent(BemVindo.this, Registar_se.class);
                    startActivity(outraActivity);
                }
            });

            botaologin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent outraActivity = new Intent(BemVindo.this, Login.class);
                    startActivity(outraActivity);
                }
            });
    }
}