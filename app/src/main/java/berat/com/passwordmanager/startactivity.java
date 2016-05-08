package berat.com.passwordmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class startactivity extends AppCompatActivity {

    Button btn;
    EditText edtid,edtpas;
    String id,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startactivity);

        btn = (Button) findViewById(R.id.button);
        edtid = (EditText) findViewById(R.id.editText2);
        edtpas = (EditText) findViewById(R.id.editText3);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = edtid.getText().toString();
                pass = edtpas.getText().toString();

                if (id.equals("berat")){
                   if (pass.equals("berat123")){
                        Toast.makeText(getApplicationContext(),"SUCCES",Toast.LENGTH_SHORT).show();
                       Intent intent = new Intent(startactivity.this,MainActivity.class);
                        startActivity(intent);


                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Wrong ID or PASS",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"Wrong ID or PASS",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
