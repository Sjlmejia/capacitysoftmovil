package com.tesis.capacitysoft;

import java.util.ArrayList;

import org.json.JSONArray;

import com.tesis.capacitysoft.servidor.HttpGetData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Recursos  extends Activity {
	ListView recursosob;
	String data;
	JSONArray ja;
	Button cancelar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recursos);
		recursosob=(ListView)findViewById(R.id.listarecursos);
		cancelar=(Button)findViewById(R.id.btn_cancelarrecursos);
		ArrayList<String> listaRec=new ArrayList<String>();
		 Bundle bolsa=getIntent().getExtras();
		 //ArrayList<String> lista=new ArrayList<String>();
		 final ArrayAdapter<String> aa=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listaRec);
		recursosob.setAdapter(aa);
		 
		 //lista=bolsa.getStringArrayList("Nombre");
	
		listaRec.addAll(bolsa.getStringArrayList("Nombre"));
		aa.notifyDataSetChanged();
		final HttpGetData h=new HttpGetData();
		cancelar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		recursosob.setOnItemClickListener(new ListView.OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(Recursos.this, Recusos_dos.class );
			    Bundle bolsa =new Bundle();
				String listChoice = (String) ( recursosob.getItemAtPosition(position)) ; 
				//data = h.httpGetData("http://192.168.1.7/ejemplo/recuperaRecurso.php?capacitacion="+listChoice);
				bolsa.putString("Recurso",listChoice);
				i.putExtras(bolsa);
				startActivity(i);
				// TODO Auto-generated method stub
				
			}});
		
	}
	

}