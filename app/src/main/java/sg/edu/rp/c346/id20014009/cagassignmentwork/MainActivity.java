package sg.edu.rp.c346.id20014009.cagassignmentwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button btnlink;
Button btnlanuch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnlanuch=findViewById(R.id.buttonlaunch);
        btnlink=findViewById(R.id.buttonlink);

        btnlanuch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent i =new Intent (MainActivity.this,ItemListActivity.class);
                startActivity(i);
            }
        });

        btnlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(("https://github.com/MasalaTea123")));
                startActivity(intent);
            }
        });
    }
}