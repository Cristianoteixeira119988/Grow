package grow.plus.app.BasesDeDadosDasPerguntas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import grow.plus.app.Perguntas.PerguntaQuiz;
import grow.plus.app.QuizBd.QuizBd;

import java.util.ArrayList;
import java.util.List;

public class QuizBdHelperQuizEnergiasRenovaveisEAcessiveis extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QuizEnergiasRenovaveisEAcessiveis.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizBdHelperQuizEnergiasRenovaveisEAcessiveis(@Nullable Context context) {
        super(context, DATABASE_NAME,null,  DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db= db;
        final String SQL_CREATE_TABELA_PERGUNTAS = " CREATE TABLE " +
                QuizBd.TabelaPerguntas.NOME_DA_TABELA + "(" +
                QuizBd.TabelaPerguntas._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizBd.TabelaPerguntas.COLUNA_PERGUNTA + " TEXT, " +
                QuizBd.TabelaPerguntas.COLUNA_OPCAO1 + " TEXT, " +
                QuizBd.TabelaPerguntas.COLUNA_OPCAO2 + " TEXT, " +
                QuizBd.TabelaPerguntas.COLUNA_OPCAO3 + " TEXT, " +
                QuizBd.TabelaPerguntas.COLUNA_OPCAO4 + " TEXT, " +
                QuizBd.TabelaPerguntas.COLUNA_RESPOSTA_NR + " INTEGER " +
                ")";

        db.execSQL(SQL_CREATE_TABELA_PERGUNTAS);
        PreencherTabelaPerguntas();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + QuizBd.TabelaPerguntas.NOME_DA_TABELA);
        onCreate(db);
    }

    public void PreencherTabelaPerguntas(){
        PerguntaQuiz P1 = new PerguntaQuiz("Qual o foco do objetivo número 7 dos ODS?", "Erradicar a pobreza extrema em todos os lugares", "Energias renováveis e acessíveis", "Eliminar as disparidades de género na educação e garantir a igualdade de acesso a todos os níveis de educação e formação profissional para os mais vulneráveis", "Construir e melhorar instalações físicas para educação", 2);
        AddPerguntas(P1);
        PerguntaQuiz P2 = new PerguntaQuiz("Uma das metas até 2030 do ODS número 7 é?", "Adotar políticas, especialmente ao nível fiscal, salarial e de proteção social, e alcançar progressivamente uma maior igualdade", "Reduzir pelo menos para metade a proporção de homens, mulheres e crianças, de todas as idades, que vivem na pobreza, em todas as suas dimensões", "Garantir uma repartição justa e equitativa dos benefícios derivados da utilização dos recursos genéticos e promover o acesso adequado aos recursos genéticos", "Duplicar a taxa global de melhoria da eficiência energética", 4);
        AddPerguntas(P2);
        PerguntaQuiz P3 = new PerguntaQuiz("Porque as energias renováveis são importantes para o mundo?", "Porque ajudam a combater a poluição", "Porque aumentão a puluição", "Porque são mais baratas", "Porque todos precisam", 1);
        AddPerguntas(P3);
        PerguntaQuiz P4 = new PerguntaQuiz("O que é concretamente as energias renováveis?", "Energia poluente", "Energia que é gerada pela natureza ", "Energia gerada por petroleo ", "Energia gerada pela queima de combustiveis fosseis", 2);
        AddPerguntas(P4);
        PerguntaQuiz P5 = new PerguntaQuiz("Qual destes é um dos recursos natural usado para obter energia renovável?", "Petroleo", "Gasolina", "Sol", "Gás", 3);
        AddPerguntas(P5);
        PerguntaQuiz P6 = new PerguntaQuiz("Quantos recursos naturais existem apra obter energia renovável?", "1", "2", "3", "Mais de 10", 4);
        AddPerguntas(P6);
        PerguntaQuiz P7 = new PerguntaQuiz("Qual destas fontes de energia pertence a recursos nao-renováveis?", "Energia eólica", "Energia geotérmica", "Energia nuclear", "Energia maremotriz", 3);
        AddPerguntas(P7);
        PerguntaQuiz P8 = new PerguntaQuiz("Em que consiste a energia solar?", "Extrair energia do sol","Extrair energia do vento" , "Extrair energia da água", "Extrair energia apartir de urânio", 1);
        AddPerguntas(P8);
    }

    public void AddPerguntas(PerguntaQuiz pergunta){
        ContentValues cv = new ContentValues();
        cv.put(QuizBd.TabelaPerguntas.COLUNA_PERGUNTA, pergunta.getPergunta());
        cv.put(QuizBd.TabelaPerguntas.COLUNA_OPCAO1, pergunta.getOpcao1());
        cv.put(QuizBd.TabelaPerguntas.COLUNA_OPCAO2, pergunta.getOpcao2());
        cv.put(QuizBd.TabelaPerguntas.COLUNA_OPCAO3, pergunta.getOpcao3());
        cv.put(QuizBd.TabelaPerguntas.COLUNA_OPCAO4, pergunta.getOpcao4());
        cv.put(QuizBd.TabelaPerguntas.COLUNA_RESPOSTA_NR, pergunta.getRespostaNr());
        db.insert(QuizBd.TabelaPerguntas.NOME_DA_TABELA, null, cv);
    }

    public List<PerguntaQuiz> getAllPerguntas(){
        List<PerguntaQuiz> perguntaList = new ArrayList<>();
        db= getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizBd.TabelaPerguntas.NOME_DA_TABELA, null);

        if (c.moveToFirst()){
            do{
                PerguntaQuiz pergunta =  new PerguntaQuiz();
                pergunta.setPergunta(c.getString(c.getColumnIndex(QuizBd.TabelaPerguntas.COLUNA_PERGUNTA)));
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