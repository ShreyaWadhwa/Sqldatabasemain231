package com.example.shreya.sqldatabasemain;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
     Button update,view;
    EditText name,hotness;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        update=(Button)findViewById(R.id.bUpdate);
        view=(Button)findViewById(R.id.bView);
        name=(EditText)findViewById(R.id.edName);
        hotness=(EditText)findViewById(R.id.edHot);
        update.setOnClickListener(this);
        view.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.bUpdate:
                boolean didItWork= true;
                try {
                    String n = name.getText().toString();
                    String h = hotness.getText().toString();
                    HotOrNot entry = new HotOrNot(MainActivity.this);
                    entry.open();
                    entry.createEntry(n, h);
                    entry.close();
                }
                catch (Exception e)
                {
                    didItWork= false;
                    String error= e.toString();
                    Dialog d= new Dialog(this);
                    d.setTitle("Dang it");
                    TextView tv= new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();


                }
                finally
                {
                    if(didItWork)
                    {
                        Dialog d= new Dialog(this);
                        d.setTitle("heck yea");
                        TextView tv= new TextView(this);
                        tv.setText("success");
                        d.setContentView(tv);
                        d.show();

                    }

                }
                break;

            case R.id.bView:


                break;

        }

    }
}
