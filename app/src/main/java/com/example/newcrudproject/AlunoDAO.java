package com.example.newcrudproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private DBConnection dbConnection;
    private SQLiteDatabase database;

    public AlunoDAO (Context context) {
        dbConnection = new DBConnection(context);
        database = dbConnection.getWritableDatabase();
    }

    public String insert(Aluno aluno) {
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("email", aluno.getEmail());

        long res = database.insert("ALUNO", null, values);

        if (res == -1)
            return "Erro ao cadastrar aluno";
        else
            return "Aluno cadastrado com sucesso";
    }

    public List<Aluno> list() {
        List<Aluno> alunosList = new ArrayList<>();

        String[] projection = {"id","nome", "email"};

        Cursor cursor = database.query("ALUNO", projection, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Aluno aluno = new Aluno();
            aluno.setId(cursor.getInt(0));
            aluno.setNome(cursor.getString(1));
            aluno.setEmail(cursor.getString(2));
            alunosList.add(aluno);
        }

        return alunosList;
    }

    public String update(Aluno aluno) {
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("email", aluno.getEmail());

        String selection = "ID = ?";
        String[] selectionArgs = {aluno.getId().toString()};

        long res = database.update("ALUNO", values, selection, selectionArgs);

        if (res == -1)
            return "Erro ao atualizar aluno";
        else
            return "Aluno atualizado com sucesso";
    }

    public String delete(Aluno aluno) {
        String selection = "ID = ?";
        String[] selectionArgs = {aluno.getId().toString()};

        int res = database.delete("ALUNO", selection, selectionArgs);

        if (res == 0)
            return "Erro ao excluir aluno";
        else
            return "Aluno excluído com sucesso";

    }
}
