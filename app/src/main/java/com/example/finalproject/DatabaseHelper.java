package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="BeSmart";/*Name of our database*/
    private static final int DB_VERSION=3;/*version of the database*/

    DatabaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDataBase(db,0,DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDataBase(db,oldVersion,newVersion);
    }

    private static void insertQuiz(SQLiteDatabase db,String question,String choice_1,String choice_2,String choice_3,String choice_4,String correct){
        ContentValues quizValues=new ContentValues();
        quizValues.put("QUESTION",question);
        quizValues.put("CHOICE1",choice_1);
        quizValues.put("CHOICE2",choice_2);
        quizValues.put("CHOICE3",choice_3);
        quizValues.put("CHOICE4",choice_4);
        quizValues.put("CORRECT",correct);
        db.insert("QUIZ",null,quizValues);
    }
    private static void insertArticle(SQLiteDatabase db,String name,String type,String description,int resourceId){
        ContentValues articleValues=new ContentValues();
        articleValues.put("NAME",name);
        articleValues.put("TYPE",type);
        articleValues.put("DESCRIPTION",description);
        articleValues.put("IMAGE_RESOURCE_ID",resourceId);
        db.insert("ARTICLE",null,articleValues);
    }
    private static void insertProfile(SQLiteDatabase db,String name,String email,String phone,int resourceId){
        ContentValues profileValues=new ContentValues();
        profileValues.put("NAME",name);
        profileValues.put("EMAIL",email);
        profileValues.put("PHONE",phone);
        profileValues.put("IMAGE_RESOURCE_ID",resourceId);
        db.insert("PROFILE",null,profileValues);
    }
    private void updateMyDataBase(SQLiteDatabase db,int oldVersion,int newVersion){
        if(newVersion<4){
            //Table for STORE
//            db.execSQL("CREATE TABLE QUIZ(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
//                    +"QUESTION TEXT,"+
//                    "CHOICE1 TEXT,"+
//                    "CHOICE2 TEXT,"+
//                    "CHOICE3 TEXT,"+
//                            "CHOICE4 TEXT,"+
//                            "CORRECT TEXT);"
//                    );
            //Table for ARTICLE
//            db.execSQL("CREATE TABLE ARTICLE(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
//                    +"NAME TEXT,"+
//                    "TYPE TEXT,"+
//                    "DESCRIPTION TEXT,"+
//                    "IMAGE_RESOURCE_ID INTEGER);");
            //Table for PROFILE
//            db.execSQL("CREATE TABLE PROFILE(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
//                    +"NAME TEXT,"+
//                    "EMAIL TEXT,"+
//                    "PHONE TEXT,"+
//                    "IMAGE_RESOURCE_ID INTEGER);");
            insertArticle(db,"Python is the Future","Programming","In contemporary times, 126,424 websites are made using the python programming language. Many top-notch companies have developed successful apps by using it. This is why it is considered the language of today and the future.\n" +
                    "Python might not had bright years in the past since its launch in 1991 but it has seen a constant and remarkable trend of growth in the 21st century. This stack overflow graph of major programming languages' growth clearly depicts the steady upliftment of the PYTHON!",R.drawable.python);
            insertArticle(db,"HTML is not a Programming Language","Programming"," No, HTML is not a programming language. The \"M\" stands for \"Markup\". Generally, a programming language allows you to describe some sort of process of doing something, whereas HTML is a way of adding context and structure to text. If youre looking to add more alphabet soup to your CV, don't classify them at all.",R.drawable.html);
            insertArticle(db,"Carpe Diem","Life Lesson","I went into the woods because I wanted to live deliberately. I wanted to live deep and suck out all the marrow of life… to put to rout all that was not life; and not, when I came to die, discover that I had not lived. ~Henry David Thoreau in \\'Walden\\', quoted by the Character Neil in the Movie \\'Dead Poets Society\\'",R.drawable.carpediem);
            insertQuiz(db,"What will be the output of the following code :\n" +
                    "print type(type(int))","type int","type 'type'","Error","0","type 'type'");
            insertQuiz(db,"What is the output of the following code :\n" +
                    "L = ['a','b','c','d']\n" +
                    "print \"\".join(L)","Error","None","abcd","['a','b','c','d']","abcd");
            insertQuiz(db,"What is the output of the following program :\n" +
                    "y = 8\n" +
                    "z = lambda x : x * y\n" +
                    "print z(6)","48","14","64","None of the above","48");
            insertQuiz(db,"What is called when a function is defined inside a class?","Module","Class","Another Function","Method","Method");
            insertQuiz(db,"Which of the following is the use of id() function in python?","Id returns the identity of the object","Every object doesnt have a unique id","All of the above","None of the above","Id returns the identity of the object");
            insertProfile(db,"Dummy","dummy@gmail.com","02214532456",R.drawable.carpediem);
        }
    }

}
