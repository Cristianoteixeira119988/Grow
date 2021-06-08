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

public class QuizBdHelperQuizBoaSaudeEBemEstar extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QuizBoaSaudeEBemEstar.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizBdHelperQuizBoaSaudeEBemEstar(@Nullable Context context) {
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
        PerguntaQuiz P1 = new PerguntaQuiz("Qual o objetivo principal do ODS 3?", "Garantir uma boa educação e aprendizagem", "Garantir o acesso à saúde de qualidade e promover o bem-estar para todos, em todas as idades", "Garantir água potavel para todos", "Garantir uma produção e consumos sustentáveis", 2);
        AddPerguntas(P1);
        PerguntaQuiz P2 = new PerguntaQuiz("Qual destas metas pertence ao ODS 3?", "Até 2030, reduzir a taxa de mortalidade materna global para menos de 70 mortes por 100.000 nados-vivos", "Minimizar e enfrentar os impactos da acidificação dos oceanos, inclusive através do reforço da cooperação científica em todos os níveis", "Até 2030, assegurar o acesso universal, de confiança, moderno e a preços acessíveis aos serviços de energia", "Até 2030, duplicar a taxa global de melhoria da eficiência energética", 1);
        AddPerguntas(P2);
        PerguntaQuiz P3 = new PerguntaQuiz("Sendo o ODS 3 relacionado com a saúde, qual das seguintes pandemias foi uma das mais graves até ao momento?", "Peste Negra", "Acatalassemia", "Malária", "Bolsite", 1);
        AddPerguntas(P3);
        PerguntaQuiz P4 = new PerguntaQuiz("Qual o maior nímero de mortes causadas por uma pandemia?", "De 25 a 50 milhões", "De 200 a 250 milhões", "De 30 a 100 milhões", "De 75 a 200 milhões", 4);
        AddPerguntas(P4);
        PerguntaQuiz P5 = new PerguntaQuiz("Qual das seguintes atividades não tem sentido para ajudar a atingir o ODS 3?", "Realizadas ações como mutirões de saúde para a comunidade com especialistas", "Caminhadas e corridas em grupo", "Aulas sobre conceitos básicos de empreendedorismo", "Palestras e oficinas sobre prevenção de diversas doenças", 3);
        AddPerguntas(P5);
        PerguntaQuiz P6 = new PerguntaQuiz("Uma maneira de ajudar no ODS 3 é doar sangue, qual o seu objetivo?", "Para os médicos observarem se tem alguma doença", "Armazenar em um hemocentro para um uso subsequente em uma transfusão de sangue", "Apenas ficar armazedano", "Fazer analizes de sangue", 2);
        AddPerguntas(P6);
        PerguntaQuiz P7 = new PerguntaQuiz("Eu como cidadão, como posso ajudar de maneira simples a ajudar o ODS 3?", "Ir ao McDonals", "Descolar-me sempre de carro", "Deitar-me a ver Tv", "Praticar exercício ao ar livre", 4);
        AddPerguntas(P7);
        PerguntaQuiz P8 = new PerguntaQuiz("Qual destas metas nao pertence ao ODS 3?", " Até 2030, reduzir num terço a mortalidade prematura por doenças não transmissíveis via prevenção e tratamento, e promover a saúde mental e o bem-estar", "Até 2020, conservar pelo menos 10% das zonas costeiras e marinhas, de acordo com a legislação nacional e internacional, e com base na melhor informação científica disponível", "Reforçar a prevenção e o tratamento do abuso de substâncias, incluindo o abuso de drogas e o uso nocivo do álcool", "Até 2020, reduzir para metade, a nível global, o número de mortos e feridos devido a acidentes rodoviários", 2);
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