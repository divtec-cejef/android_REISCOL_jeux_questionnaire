package com.example.jeux_questionnaire;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.jeux_questionnaire.Models.Jeux_questionnaireSQLite;
import com.google.android.material.slider.Slider;

public class Parametres extends AppCompatActivity {

    String question;
    int reponse = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametre);

        Slider timeSlider = findViewById(R.id.param_slider);
        Button BT_Quitter = findViewById(R.id.parametre_bouton_quitter);
        Button BT_Ajouter = findViewById(R.id.parametre_bouton_ajouter_question);
        EditText Question = findViewById(R.id.parametre_question_et);
        CheckBox checkBox = findViewById(R.id.parametre_checkbox);
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;



        BT_Quitter.setOnClickListener(view -> {
            jeux.temps = (long) timeSlider.getValue() * 1000 + 1000;
            finish();
        });

        BT_Ajouter.setOnClickListener(view -> {
            question = Question.getText().toString();
            CharSequence text;

            if (checkBox.isChecked()) {
                reponse = 1;
            }

            if (!question.isEmpty()) {
                text = getText(R.string.Add_question);
            } else {
                text = getText(R.string.Add_question_error);
            }
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            QuestionManager.addQuestion(this, question, reponse);
        });
    }

}