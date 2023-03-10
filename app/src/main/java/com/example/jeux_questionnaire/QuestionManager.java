package com.example.jeux_questionnaire;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jeux_questionnaire.Models.Jeux_questionnaireSQLite;

import java.util.ArrayList;

public class QuestionManager {

    public ArrayList<Question> mesQuestions;
    private int index = 0;

    // Rajoute les questions dans la liste
    public QuestionManager(Context context)  { mesQuestions = initQuestionList(context);}

    // Retourne la question
    public String getQuestion() {
        return mesQuestions.get(index).getQuestion();
    }

    // Retourne la réponse
    public int getReponse() {
        return mesQuestions.get(index).getReponse();
    }

    // Passe à la prochaine question
    public void prochaineQuestion() {
        this.index++;
    }

    // Retourne si c'est la dernière question
    public boolean isLastQuestion() {
        int finListe = mesQuestions.size() -1;
        return index == finListe;
    }
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Initialise la liste de question
     * @param context contexte de l'application
     * @return liste de question
     */
    private ArrayList<Question> initQuestionList(Context context){
        ArrayList<Question> InitQuestion = new ArrayList<>();// crée une nouvel liste
        Jeux_questionnaireSQLite helper = new Jeux_questionnaireSQLite(context);// Instancie la base de données
        SQLiteDatabase db = helper.getReadableDatabase();//Accède à la base de données en lecture seul

        //la base de donnée retourne les information en cursor
        //Crée une requete avec tout les parametres
        Cursor cursor = db.query(true,"quiz",new String[]{"idQuiz", "question","reponse"}
                ,null,null,null,null,"idQuiz",null);

        while (cursor.moveToNext()){
            InitQuestion.add(new Question(cursor));
        }

        cursor.close();
        db.close();
        return InitQuestion;
    }

    /**
     * Ajoute une question à la base de donnée
     * @param context contexte de l'application
     * @param question question à ajouter à la base de donnée
     * @param reponse réponse à la question
     */
    public static void addQuestion(Context context, String question, int reponse){
        SQLiteDatabase db = new Jeux_questionnaireSQLite(context).getWritableDatabase();
        db.execSQL("INSERT INTO quiz VALUES(null,\" " + question + "\", " + reponse + ");");
        db.close();
    }
}


