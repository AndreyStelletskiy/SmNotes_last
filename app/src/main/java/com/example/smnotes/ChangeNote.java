package com.example.smnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

public class ChangeNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_note);
        getSupportActionBar().hide();
        EditText Changename = findViewById(R.id.ChangeNoteName);
        EditText Changenote = findViewById(R.id.ChangeNote);
        Changename.setText(getIntent().getStringExtra("nn"));
        Changenote.setText(getIntent().getStringExtra("snn"));

    }


    //Сохранение изменений
    public void saveChNote(View view){
        FileOutputStream fos = null;
        try {
            EditText editText = findViewById(R.id.ChangeNoteName);
            //Toast.makeText(this, "1 сохранен", Toast.LENGTH_SHORT).show();
            String Cname = editText.getText().toString();
            //Toast.makeText(this, "2 сохранен", Toast.LENGTH_SHORT).show();
            EditText cnote = findViewById(R.id.ChangeNote);
            //Toast.makeText(this, "3 сохранен", Toast.LENGTH_SHORT).show();
            String text = cnote.getText().toString();
            //Toast.makeText(this, "4 сохранен", Toast.LENGTH_SHORT).show();

            fos = openFileOutput(Cname, MODE_PRIVATE);
            //Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
            fos.write(text.getBytes());
            Toast.makeText(this, "Заметка сохранен", Toast.LENGTH_SHORT).show();
        }
        catch(IOException ex) {

            Toast.makeText(this, "Заметка не найдена", Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    //Удоление заметкм
    public void NoteDell(View view){
        EditText readname = findViewById(R.id.ChangeNoteName);
        EditText readnote = findViewById(R.id.ChangeNote);
        String sname = readname.getText().toString();
        deleteFile(sname);
        Toast.makeText(this, "Заметка удалена", Toast.LENGTH_SHORT).show();
        readname.setText("");
        readnote.setText("");
        Intent intMain = new Intent(this, MainActivity.class);
        startActivity(intMain);
    }


    //переход на главную страницу
    public void go_to_MainActivity(View v) {
        Intent intMain = new Intent(this, MainActivity.class);
        startActivity(intMain);
    }
}