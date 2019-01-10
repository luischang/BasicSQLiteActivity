package com.example.lchang.basicsqliteactivity;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListadoActivity extends AppCompatActivity {

    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;
    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.PAIS, DatabaseHelper.MONEDA };
    final int[] to = new int[] { R.id.r_id, R.id.r_pais, R.id.r_moneda};
    private FloatingActionButton fbtAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        setTitle("Listado de Divisas");

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = findViewById(R.id.lsvPais);
        //listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_ver_registro, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // setOnItemClickListener For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView tvId = view.findViewById(R.id.r_id);
                TextView tvPais = view.findViewById(R.id.r_pais);
                TextView tvMoneda = view.findViewById(R.id.r_moneda);

                String id = tvId.getText().toString();
                String pais = tvPais.getText().toString();
                String moneda = tvMoneda.getText().toString();

                Intent intent = new Intent(getApplicationContext(), ModificaActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("pais", pais);
                intent.putExtra("moneda", moneda);

                startActivity(intent);
            }
        });

        fbtAgregar = findViewById(R.id.fbtAgregar);
        fbtAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistroActivity.class);
                startActivity(intent);
            }
        });
    }
}
