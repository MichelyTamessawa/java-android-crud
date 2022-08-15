package com.example.newcrudproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroAluno extends AppCompatActivity {
    private EditText nome;
    private EditText email;
    private AlunoDAO dao;
    private Aluno aluno = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        nome = findViewById(R.id.editTextNome);
        email = findViewById(R.id.editTextEmail);

        dao = new AlunoDAO(this);

        // chamada pelo edit
        Intent it = getIntent();
        if (it.hasExtra("aluno")) {
            aluno = (Aluno) it.getSerializableExtra("aluno");
            nome.setText(aluno.getNome());
            email.setText(aluno.getEmail());
        }
    }

    public void ClickSalvar(View view) {
        if(aluno == null) {
            Aluno novo = new Aluno();
            novo.setNome(nome.getText().toString());
            novo.setEmail(email.getText().toString());

            String retorno = dao.insert(novo);
            Toast.makeText(this, retorno, Toast.LENGTH_LONG).show();
        }
        else {
            aluno.setNome(nome.getText().toString());
            aluno.setEmail(email.getText().toString());

            String retorno = dao.update(aluno);
            Toast.makeText(this, retorno, Toast.LENGTH_LONG).show();
        }
        finish();
    }
}