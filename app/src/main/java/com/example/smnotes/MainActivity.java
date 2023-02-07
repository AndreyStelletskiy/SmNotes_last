package com.example.smnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.FileInputStream;

import android.widget.TextView;

import java.io.IOException;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public int isShow = 1;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isShow = 1;
        getSupportActionBar().hide();

        findViewById(R.id.btchange).setVisibility(View.GONE);
        findViewById(R.id.btDel).setVisibility(View.VISIBLE);
        findViewById(R.id.noshowall).setVisibility(View.GONE);
    }


    // переход на страницу добавления новой записи
    public void go_to_CreatNotes(View v) {
        Intent intCreate = new Intent(this, Creat_Note.class);
        startActivity(intCreate);
        EditText readname = findViewById(R.id.ReadNoteName);
        String nname = readname.getText().toString();
        intCreate.putExtra("nname", nname);
        startActivity(intCreate);
        isShow =1;
        if (isShow == 1) {findViewById(R.id.btchange).setVisibility(View.GONE); findViewById(R.id.btDel).setVisibility(View.VISIBLE);
        } else {findViewById(R.id.btchange).setVisibility(View.VISIBLE); findViewById(R.id.btDel).setVisibility(View.GONE);}
    }

    //Переход на активность изменения заметки++++

    public void go_to_ChangeNotes(View v) {
        EditText readname = findViewById(R.id.ReadNoteName);
        String sname = readname.getText().toString();
        TextView textView = findViewById(R.id.ChangeNote);
        String snote = textView.getText().toString();
        Intent intChange = new Intent(this, ChangeNote.class);
        intChange.putExtra("nn", sname);
        intChange.putExtra("snn", snote);
        startActivity(intChange);
        isShow = 1;
        if (isShow == 1) {findViewById(R.id.btchange).setVisibility(View.GONE); findViewById(R.id.btDel).setVisibility(View.VISIBLE);
        } else {findViewById(R.id.btchange).setVisibility(View.VISIBLE); findViewById(R.id.btDel).setVisibility(View.GONE);}
    }



    //Открытие заметки
    public void openNote(View view) {
        isShow = 0;

        EditText readname = findViewById(R.id.ReadNoteName);
        String sname = readname.getText().toString();

        FileInputStream fin = null;
        TextView textView = findViewById(R.id.ChangeNote);
        try {
            fin = openFileInput(sname);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            textView.setText(text);
        } catch (IOException ex) {
            isShow = 1;

            Toast.makeText(this, " Заметка не найдена ", Toast.LENGTH_SHORT).show();
        } finally {

            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        if (isShow == 1) {findViewById(R.id.btchange).setVisibility(View.GONE); findViewById(R.id.btDel).setVisibility(View.VISIBLE);
        } else {findViewById(R.id.btchange).setVisibility(View.VISIBLE); findViewById(R.id.btDel).setVisibility(View.GONE);}
    }


    //Удоление заметкм
    public void NoteDell(View view){
        EditText readname = findViewById(R.id.ReadNoteName);
        EditText readNote = findViewById(R.id.ChangeNote);
        String sname = readname.getText().toString();
        deleteFile(sname);
        readname.setText("");
        readNote.setText("");
        Toast.makeText(this, "Заметка удалена", Toast.LENGTH_SHORT).show();
    }

    //Показ и скрытие имён всех заметок
    public void showall (View view){
        findViewById(R.id.showall).setVisibility(View.GONE);
    }
    public void noshowall (View view){
        findViewById(R.id.noshowall).setVisibility(View.GONE);

    }




}