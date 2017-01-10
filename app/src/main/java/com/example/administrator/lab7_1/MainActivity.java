package com.example.administrator.lab7_1;


import android.app.FragmentManager;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.logging.Logger;

import static java.sql.Types.NULL;

public class MainActivity extends AppCompatActivity {

    ArrayList <WebFragment> list;
    FragmentManager fm;
    int currentIndex;
    EditText editText;
    String input;
    Logger log = Logger.getAnonymousLogger();
    WebFragment receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        fm = getFragmentManager();
        fm.beginTransaction().commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.newPage:
                receiver = new WebFragment();
                list.add(receiver);
                currentIndex = list.size()-1;

                fm.beginTransaction().replace(R.id.fragment_web,list.get(currentIndex)).commit();
                fm.executePendingTransactions();
                log.info("List size is: "+String.valueOf(list.size()));
                log.info("Current index is: "+String.valueOf(currentIndex));
                return true;

            case R.id.nextPage:

                if (currentIndex < list.size()-1) {
                    currentIndex++;
                    fm.beginTransaction().replace(R.id.fragment_web,list.get(currentIndex)).commit();

                    fm.executePendingTransactions();
                    receiver = list.get(currentIndex);
                    receiver.changeUrl("");
                }
                log.info("List size is: "+String.valueOf(list.size()));
                log.info("Current index is: "+String.valueOf(currentIndex));
                return true;

            case R.id.prePage:

                if (currentIndex > 0) {
                    currentIndex--;
                    fm.beginTransaction().replace(R.id.fragment_web,list.get(currentIndex)).commit();

                    fm.executePendingTransactions();
                    receiver = list.get(currentIndex);
                    receiver.changeUrl("");
                }
                log.info("List size is: "+String.valueOf(list.size()));
                log.info("Current index is: "+String.valueOf(currentIndex));
                return true;

            case R.id.goPage:

                editText = (EditText) findViewById(R.id.webAddress);
                input = editText.getText().toString();
                receiver = list.get(currentIndex);
                receiver.changeUrl(input);

                log.info("List size is: "+String.valueOf(list.size()));
                log.info("Current index is: "+String.valueOf(currentIndex));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
