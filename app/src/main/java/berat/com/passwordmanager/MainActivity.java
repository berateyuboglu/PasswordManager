package berat.com.passwordmanager;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    String text;
    EditText editText,loadarea;
    Button save,load,crypt,decrypt;


    String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/pass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        loadarea = (EditText) findViewById(R.id.loadarea);

        save = (Button) findViewById(R.id.save);
        crypt = (Button) findViewById(R.id.crypt);
        decrypt = (Button) findViewById(R.id.decrypt);
        load = (Button) findViewById(R.id.load);


        File dir = new File(path);
        dir.mkdirs();

    save.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            File file = new File(path + "/pass.txt");
            String[] saveText = String.valueOf(editText.getText()).split(System.getProperty("line.separator"));
            editText.setText("");
            Toast.makeText(getApplicationContext(), "Kayıt Edildi", Toast.LENGTH_SHORT).show();
            Save(file, saveText);

        }
    });

        crypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = editText.getText().toString();
               text = shift(text, 1);
                editText.setText(text);
            }
        });

        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = loadarea.getText().toString();
                text = shift(text,-1);
                loadarea.setText(text);

            }
        });


    load.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            File file = new File(path+ "/pass.txt");
            String [] loadText = Load(file);

            String finalString = "";

            for (int i = 0; i < loadText.length; i++)
            {
                finalString += loadText[i] + System.getProperty("line.separator");
            }

            loadarea.setText(finalString);
        }
    });


    }



    public static void Save(File file, String[] data)
    {
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(file);
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
        try
        {
            try
            {
                for (int i = 0; i<data.length; i++)
                {
                    fos.write(data[i].getBytes());
                    if (i < data.length-1)
                    {
                        fos.write("\n".getBytes());
                    }
                }
            }
            catch (IOException e) {e.printStackTrace();}
        }
        finally
        {
            try
            {
                fos.close();
            }
            catch (IOException e) {e.printStackTrace();}
        }
    }


    public static String[] Load(File file)
    {
        FileInputStream fis = null;
        try
        {
            fis = new FileInputStream(file);
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        String test;
        int anzahl=0;
        try
        {
            while ((test=br.readLine()) != null)
            {
                anzahl++;
            }
        }
        catch (IOException e) {e.printStackTrace();}

        try
        {
            fis.getChannel().position(0);
        }
        catch (IOException e) {e.printStackTrace();}

        String[] array = new String[anzahl];

        String line;
        int i = 0;
        try
        {
            while((line=br.readLine())!=null)
            {
                array[i] = line;
                i++;
            }
        }
        catch (IOException e) {e.printStackTrace();}
        return array;
    }

    String shift(String msg, int shift){
        String s = "";
        int len = msg.length();
        for(int x = 0; x < len; x++){
            char c = (char)(msg.charAt(x) + shift);
            if (c > 'z')
                s += (char)(msg.charAt(x) - (26-shift));
            else
                s += (char)(msg.charAt(x) + shift);
        }
        return s;
    }





}
