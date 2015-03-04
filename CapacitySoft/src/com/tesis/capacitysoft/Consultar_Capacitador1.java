package com.tesis.capacitysoft;

import java.util.ArrayList;



import android.os.Bundle;

import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.ListView;
import android.widget.TextView;

import android.app.Activity;
import android.content.Intent;
public class Consultar_Capacitador1 extends Activity {
	TextView entrada;
	ListView capacitadores;
	Button cancelar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.consultar_capacitado_1);
		entrada=(TextView)findViewById(R.id.lbl_capacitador);
		capacitadores=(ListView)findViewById(R.id.list_capacitadores);
		cancelar=(Button)findViewById(R.id.btn_cancelarconsultarcapacitado);
		ArrayList<String> listaRec=new ArrayList<String>();
		 Bundle bolsa=getIntent().getExtras();
		 cancelar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				
			}
		});
		 //ArrayList<String> lista=new ArrayList<String>();
		 final ArrayAdapter<String> aa=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listaRec);
		capacitadores.setAdapter(aa);
		 
		 //lista=bolsa.getStringArrayList("Nombre");
	
		listaRec.addAll(bolsa.getStringArrayList("Nombre"));
		aa.notifyDataSetChanged();
		capacitadores.setOnItemClickListener(new ListView.OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(Consultar_Capacitador1.this, Enviar_Llamar.class );
			    Bundle bolsa =new Bundle();
				String listChoice = (String) ( capacitadores.getItemAtPosition(position)) ;
				String aux=listChoice.substring(0,10);
				bolsa.putString("Capacitador",aux);
				i.putExtras(bolsa);
				startActivity(i);
				// TODO Auto-generated method stub
				
			}});
	}
	

}
