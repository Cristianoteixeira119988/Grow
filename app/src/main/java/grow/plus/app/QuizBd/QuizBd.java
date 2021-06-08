package grow.plus.app.QuizBd;

import android.provider.BaseColumns;

public class QuizBd {

    private QuizBd(){

    }
    public static class TabelaPerguntas implements BaseColumns {
        public static final String NOME_DA_TABELA = "quiz_perguntas";
        public static final String COLUNA_PERGUNTA = "pergunta";
        public static final String COLUNA_OPCAO1 = "opcao1";
        public static final String COLUNA_OPCAO2 = "opcao2";
        public static final String COLUNA_OPCAO3 = "opcao3";
        public static final String COLUNA_OPCAO4 = "opcao4";
        public static final String COLUNA_RESPOSTA_NR = "resposta_nr";
    }
}
