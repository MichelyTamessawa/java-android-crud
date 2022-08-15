package com.example.newcrudproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private AlunoDAO dao;
    private List<Aluno> alunos;
    private FloatingActionButton button;
    private AdapterPersonalizado adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        button = findViewById(R.id.ButtonAdd);

        dao = new AlunoDAO(this);
        alunos = dao.list();

        // adaptar a lista de alunos para preencher a list view
        adapter = new AdapterPersonalizado(alunos, dao, this);
        listView.setAdapter(adapter);

        // chamar a tela de cadastro de alunos ao clicar no botão +
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, CadastroAluno.class);
                startActivity(it);
            }
        });
    }

    // atualizar a lista após cadastrar ou editar um aluno
    @Override
    public void onResume() {
        super.onResume();

        alunos.clear();
        alunos.addAll(dao.list());
        listView.invalidateViews();
    }
}