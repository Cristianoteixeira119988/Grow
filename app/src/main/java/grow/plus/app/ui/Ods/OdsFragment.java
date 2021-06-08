package grow.plus.app.ui.Ods;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import grow.plus.app.R;

public class OdsFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_ods, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.imageViewOds1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outraActivity = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ods.pt/objectivos/1-erradicar-a-pobreza/?portfolioCats=24"));
                startActivity(outraActivity);
            }
        });
        view.findViewById(R.id.imageViewOds2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outraActivity = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ods.pt/objectivos/2-acabar-com-a-fome/?portfolioCats=24"));
                startActivity(outraActivity);
            }
        });
        view.findViewById(R.id.imageViewOds3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outraActivity = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ods.pt/objectivos/3-vida-saudavel/?portfolioCats=24"));
                startActivity(outraActivity);
            }
        });
        view.findViewById(R.id.imageViewOds4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outraActivity = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ods.pt/objectivos/4-educacao-de-qualidade/?portfolioCats=24"));
                startActivity(outraActivity);
            }
        });
        view.findViewById(R.id.imageViewOds5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outraActivity = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ods.pt/objectivos/5-igualidade-de-genero/?portfolioCats=24"));
                startActivity(outraActivity);
            }
        });
        view.findViewById(R.id.imageViewOds6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outraActivity = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ods.pt/objectivos/6-agua-e-saneamento/?portfolioCats=24"));
                startActivity(outraActivity);
            }
        });
        view.findViewById(R.id.imageViewOds7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outraActivity = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ods.pt/objectivos/7-energias-renovaveis/?portfolioCats=24"));
                startActivity(outraActivity);
            }
        });
        view.findViewById(R.id.imageViewOds8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outraActivity = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ods.pt/objectivos/8-trabalho-e-crescimento-economico/?portfolioCats=24"));
                startActivity(outraActivity);
            }
        });
        view.findViewById(R.id.imageViewOds9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outraActivity = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ods.pt/objectivos/9-inovacao-e-infraestruturas/?portfolioCats=24"));
                startActivity(outraActivity);
            }
        });
        view.findViewById(R.id.imageViewOds10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outraActivity = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ods.pt/objectivos/10-reduzir-as-desigualdades/?portfolioCats=24"));
                startActivity(outraActivity);
            }
        });view.findViewById(R.id.imageViewOds11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outraActivity = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ods.pt/objectivos/11-cidades-e-comunidades-sustentaveis/?portfolioCats=24"));
                startActivity(outraActivity);
            }
        });
        view.findViewById(R.id.imageViewOds12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outraActivity = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ods.pt/objectivos/12-producao-e-consumo-sustentaveis/?portfolioCats=24"));
                startActivity(outraActivity);
            }
        });
        view.findViewById(R.id.imageViewOds13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outraActivity = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ods.pt/objectivos/13-combater-as-alteracoes-climatericas/?portfolioCats=24"));
                startActivity(outraActivity);
            }
        });
        view.findViewById(R.id.imageViewOds14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outraActivity = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ods.pt/objectivos/14-oceanos-mares-e-recursos-marinhos/?portfolioCats=24"));
                startActivity(outraActivity);
            }
        });
        view.findViewById(R.id.imageViewOds15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outraActivity = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ods.pt/objectivos/15-ecosistemas-terrestres-biodiversidade/?portfolioCats=24"));
                startActivity(outraActivity);
            }
        });
        view.findViewById(R.id.imageViewOds16).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outraActivity = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ods.pt/objectivos/16-paz-e-justica/?portfolioCats=24"));
                startActivity(outraActivity);
            }
        });
        view.findViewById(R.id.imageViewOds17).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outraActivity = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ods.pt/objectivos/17-parcerias-para-o-desenvolvimento/?portfolioCats=24"));
                startActivity(outraActivity);
            }
        });

    }
}