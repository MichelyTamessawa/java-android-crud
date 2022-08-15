package com.example.newcrudproject;


import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AdapterPersonalizado extends BaseAdapter {
    private List<Aluno> alunos;
    private final AlunoDAO dao;
    private final Activity activity;

    public AdapterPersonalizado(List<Aluno> alunos, AlunoDAO dao, Activity activity) {
        this.alunos = alunos;
        this.dao = dao;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.item_aluno, parent, false);
        Aluno aluno = alunos.get(position);

        TextView nome = view.findViewById(R.id.textViewNome);
        TextView email = view.findViewById(R.id.textViewEmail);
        ImageButton editButton = view.findViewById(R.id.imageButtonEdit);
        ImageButton deleteButton = view.findViewById(R.id.imageButtonDelete);

        nome.setText(aluno.getNome());
        email.setText(aluno.getEmail());

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(view.getContext(), CadastroAluno.class);
                it.putExtra("aluno", aluno);
                view.getContext().startActivity(it);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String retorno = dao.delete(aluno);
                Toast.makeText(view.getContext(), retorno, Toast.LENGTH_LONG).show();

                alunos = dao.list();
                notifyDataSetChanged();
            }
        });

        return view;
    }

}
