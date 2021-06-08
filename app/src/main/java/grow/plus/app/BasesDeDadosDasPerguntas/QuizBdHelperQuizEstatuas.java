package grow.plus.app.BasesDeDadosDasPerguntas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import grow.plus.app.Perguntas.PerguntasQuizComImagem;
import grow.plus.app.QuizBd.QuizBd;
import grow.plus.app.QuizBd.QuizBdComImagem;
import grow.plus.app.R;

import java.util.ArrayList;
import java.util.List;

public class QuizBdHelperQuizEstatuas extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QuizEstatuas.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizBdHelperQuizEstatuas(@Nullable Context context) {
        super(context, DATABASE_NAME,null,  DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db= db;
        final String SQL_CREATE_TABELA_PERGUNTAS = " CREATE TABLE " +
                QuizBdComImagem.TabelaPerguntas.NOME_DA_TABELA + "(" +
                QuizBdComImagem.TabelaPerguntas._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizBdComImagem.TabelaPerguntas.COLUNA_IMAGEM + " IMAGE, " +
                QuizBdComImagem.TabelaPerguntas.COLUNA_PERGUNTA + " TEXT, " +
                QuizBdComImagem.TabelaPerguntas.COLUNA_OPCAO1 + " TEXT, " +
                QuizBdComImagem.TabelaPerguntas.COLUNA_OPCAO2 + " TEXT, " +
                QuizBdComImagem.TabelaPerguntas.COLUNA_OPCAO3 + " TEXT, " +
                QuizBdComImagem.TabelaPerguntas.COLUNA_OPCAO4 + " TEXT, " +
                QuizBdComImagem.TabelaPerguntas.COLUNA_RESPOSTA_NR + " INTEGER " +
                ")";

        db.execSQL(SQL_CREATE_TABELA_PERGUNTAS);
        PreencherTabelaPerguntas();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + QuizBdComImagem.TabelaPerguntas.NOME_DA_TABELA);
        onCreate(db);
    }

    public void PreencherTabelaPerguntas(){
        PerguntasQuizComImagem P1 = new PerguntasQuizComImagem(R.drawable.estatua1, "Onde se localiza a imagem acima?", "Rio de Janeiro", "Nova York", "Lisboa", "Paris", 2);
        AddPerguntas(P1);
        PerguntasQuizComImagem P2 = new PerguntasQuizComImagem(R.drawable.estatua2, "Quem está representado na imagem acima?", "Sócrates", "Platão", "Augusto de Prima Porta", "Luís de Camões", 3);
        AddPerguntas(P2);
        PerguntasQuizComImagem P3 = new PerguntasQuizComImagem(R.drawable.estatua3, "Qual o nome da escultura famosa representada na imagem acima?", "Discóbolo", "Platão", "O Pensador", "Apolo", 1);
        AddPerguntas(P3);
        PerguntasQuizComImagem P4 = new PerguntasQuizComImagem(R.drawable.estatua4, "Qual o nome da escultura famosa representada na imagem?", "Zeus", "Deuses da Arábia", "Epísquiro", "Monte Rushmore", 4);
        AddPerguntas(P4);
        PerguntasQuizComImagem P5 = new PerguntasQuizComImagem(R.drawable.estatua5, "Qual o nome da estátua famosa representada na imagem?", "Zeus", "Buda do Templo da Primavera", "Epísquiro", "Buda Apolo", 2);
        AddPerguntas(P5);
        PerguntasQuizComImagem P6 = new PerguntasQuizComImagem(R.drawable.estatua6, "Qual o nome da estátua famosa representada na imagem?", "Zeus", "O Pensador", "Platão", "Epísquiro", 2);
        AddPerguntas(P6);
        PerguntasQuizComImagem P7 = new PerguntasQuizComImagem(R.drawable.estatua7, "Qual o nome da estátua famosa representada na imagem?", "Luís de Camões", "O Pensador", "Platão", "Discóbolo", 1);
        AddPerguntas(P7);
        PerguntasQuizComImagem P8 = new PerguntasQuizComImagem(R.drawable.estatua8, "Qual o nome da estátua famosa representada na imagem?", "Ananke", "Cleópatra", "Vênus de Milo", "Discóbolo", 3);
        AddPerguntas(P8);
        PerguntasQuizComImagem P9 = new PerguntasQuizComImagem(R.drawable.estatua9, "Qual o nome da escultura famosa representada na imagem?", "Busto de Ananke", "Busto de Cleópatra", "Busto de Nefertiti", "Busto de Hera", 3);
        AddPerguntas(P9);
        PerguntasQuizComImagem P10 = new PerguntasQuizComImagem(R.drawable.estatua10, "Qual o nome da estátua famosa representada na imagem?", "Zeus", "Nemesis", "Deus", "Cristo Redentor", 4);
        AddPerguntas(P10);
    }


    public void AddPerguntas(PerguntasQuizComImagem pergunta){
        ContentValues cv = new ContentValues();
        cv.put(QuizBdComImagem.TabelaPerguntas.COLUNA_PERGUNTA, pergunta.getPergunta());
        cv.put(QuizBdComImagem.TabelaPerguntas.COLUNA_IMAGEM, pergunta.getImage());
        cv.put(QuizBdComImagem.TabelaPerguntas.COLUNA_OPCAO1, pergunta.getOpcao1());
        cv.put(QuizBdComImagem.TabelaPerguntas.COLUNA_OPCAO2, pergunta.getOpcao2());
        cv.put(QuizBdComImagem.TabelaPerguntas.COLUNA_OPCAO3, pergunta.getOpcao3());
        cv.put(QuizBdComImagem.TabelaPerguntas.COLUNA_OPCAO4, pergunta.getOpcao4());
        cv.put(QuizBdComImagem.TabelaPerguntas.COLUNA_RESPOSTA_NR, pergunta.getRespostaNr());
        db.insert(QuizBdComImagem.TabelaPerguntas.NOME_DA_TABELA, null, cv);
    }

    public List<PerguntasQuizComImagem> getAllPerguntas(){
        List<PerguntasQuizComImagem> perguntaList = new ArrayList<>();
        db= getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizBdComImagem.TabelaPerguntas.NOME_DA_TABELA, null);

        if (c.moveToFirst()){
            do{
                PerguntasQuizComImagem pergunta = new PerguntasQuizComImagem();
                pergunta.setPergunta(c.getString(c.getColumnIndex(QuizBdComImagem.TabelaPerguntas.COLUNA_PERGUNTA)));
                pergunta.setImage(c.getInt(c.getColumnIndex(QuizBdComImagem.TabelaPerguntas.COLUNA_IMAGEM)));
                pergunta.setOpcao1(c.getString(c.getColumnIndex(QuizBd.TabelaPerguntas.COLUNA_OPCAO1)));
                pergunta.setOpcao2(c.getString(c.getColumnIndex(QuizBd.TabelaPerguntas.COLUNA_OPCAO2)));
                pergunta.setOpcao3(c.getString(c.getColumnIndex(QuizBd.TabelaPerguntas.COLUNA_OPCAO3)));
                pergunta.setOpcao4(c.getString(c.getColumnIndex(QuizBd.TabelaPerguntas.COLUNA_OPCAO4)));
                pergunta.setRespostaNr(c.getInt(c.getColumnIndex(QuizBd.TabelaPerguntas.COLUNA_RESPOSTA_NR)));
                perguntaList.add(pergunta);

            }while (c.moveToNext());
        }

        c.close();
        return perguntaList;
    }
}

