package grow.plus.app.ui.Grow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import grow.plus.app.PaginasIniciaisDosQuizzes.MostraPontuacao_quiz_energias_renovaveis;
import grow.plus.app.PaginasIniciaisDosQuizzes.MostraPontuacaoQuizBoaSaudeEBemEstar;
import grow.plus.app.PaginasIniciaisDosQuizzes.MostraPontuacaoQuizErradicarPobreza;
import grow.plus.app.PaginasIniciaisDosQuizzes.MostraPontuacaoQuizEstatuas;
import grow.plus.app.PaginasIniciaisDosQuizzes.MostraPontuacaoQuizHistoriaDoMundo;
import grow.plus.app.PaginasIniciaisDosQuizzes.MostraPontuacaoQuizMonumentosFamosos;
import grow.plus.app.PaginasIniciaisDosQuizzes.MostraPontuacaoQuizODS;
import grow.plus.app.R;


public class GrowAppFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grow_app, container, false);


        view.findViewById(R.id.QuizODS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outraActivity = new Intent(getContext(), MostraPontuacaoQuizODS.class);
                startActivity(outraActivity);
            }
        });
        view.findViewById(R.id.QuizMonumentosFamosos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outraActiviti = new Intent(getContext(), MostraPontuacaoQuizMonumentosFamosos.class);
                startActivity(outraActiviti);
            }
        });
        view.findViewById(R.id.QuizObejetivo1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outraActivity = new Intent(getContext(), MostraPontuacaoQuizErradicarPobreza.class);
                startActivity(outraActivity);
            }
        });
        view.findViewById(R.id.QuizHistoria).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outraActivity = new Intent(getContext(), MostraPontuacaoQuizHistoriaDoMundo.class);
                startActivity(outraActivity);
            }
        });
        view.findViewById(R.id.QuizBoaSaudeEBemEstar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outraActivity = new Intent(getContext(), MostraPontuacaoQuizBoaSaudeEBemEstar.class);
                startActivity(outraActivity);
            }
        });
        view.findViewById(R.id.QuizEstatuasFamosas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outraActivity = new Intent(getContext(), MostraPontuacaoQuizEstatuas.class);
                startActivity(outraActivity);
            }
        });
        view.findViewById(R.id.QuizEnergiaAcessivelELimpa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outraActivity = new Intent(getContext(), MostraPontuacao_quiz_energias_renovaveis.class);
                startActivity(outraActivity);
            }
        });


        return view;

    }
}
