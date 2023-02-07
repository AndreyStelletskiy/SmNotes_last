package com.example.smnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import java.io.FileOutputStream;
import java.io.IOException;

public class Creat_Note extends AppCompatActivity {
    EditText CNName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_note);
        CNName =  findViewById(R.id.NameNote);
        CNName.setText(getIntent().getStringExtra("nname"));
        getSupportActionBar().hide();
    }

    //переход на главную страницу
    public void go_to_MainActivity(View v) {
        Intent intMain = new Intent(this, MainActivity.class);
        startActivity(intMain);
    }


    //Сохранение новой заметки
    public void saveNewNote(View view){

        FileOutputStream fos = null;
        try {
            EditText editText = findViewById(R.id.NameNote);
            String name = editText.getText().toString();
            MultiAutoCompleteTextView note = findViewById(R.id.Note);
            String text = note.getText().toString();

            fos = openFileOutput(name, MODE_PRIVATE);
            fos.write(text.getBytes());
            Toast.makeText(this, "Заметка сохранена", Toast.LENGTH_SHORT).show();
        }
        catch(IOException ex) {

            Toast.makeText(this, "Не удалось сохранить заметку,\n Введите название заметки", Toast.LENGTH_SHORT).show();
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

}