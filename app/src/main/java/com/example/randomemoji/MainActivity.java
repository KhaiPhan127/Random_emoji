package com.example.randomemoji;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    GridView myGridView;
    List myList = new ArrayList();
    List<Integer> emoji = Arrays.asList(
            8986, 0x1F603, 0x1F605, 0x1F60D, 0x1F60F,
            0x1F618, 0x1F621, 0x1F625, 0x1F628, 0x1F62D,
            0x1F637, 0x1F61D, 0x1F616, 0x1F609, 0x1F60B,
            0x1F635, 0x1F633, 0x1F624, 0x1F61C, 0x1F60A);
    String mainIconSt;
    List myListTmp ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myGridView = (GridView) findViewById(R.id.myGrid);



        for (int i = 0; i < emoji.size(); i++) {
            myList.add(new String(Character.toChars(emoji.get(i))));
        }
        IconActivity iconAc = new IconActivity(MainActivity.this, R.layout.icon_design, myList);
        myGridView.setAdapter(iconAc);
        myListTmp=new ArrayList(myList);
        mainIconSt = RandomIcon();
        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView iconClicked = (TextView) view;
                if (iconClicked.getText().toString() == mainIconSt) {
                    iconClicked.setText("");
                    myListTmp.remove(mainIconSt);
                    mainIconSt = RandomIcon();
                    if (myListTmp.size()==0) {
                        Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String RandomIcon(){
        Random rd =new Random();
        String mainIcon = myListTmp.get(rd.nextInt(myListTmp.size())).toString();
        TextView textViewTmp = findViewById(R.id.mainIcon);
        textViewTmp.setText(mainIcon);
        return mainIcon;
    }
}