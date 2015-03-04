package com.tesis.capacitysoft;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Informacion_capacitacionp extends Activity {
	String aux;
	TextView tema;
	TextView lugar;
	TextView f_inicio;
	TextView f_fin;
	TextView h_inicio;
	TextView h_fin;
	TextView calificacion;
	Button cancelar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.informacion_capacitacionpasada);
		ArrayList<String> listaRec=new ArrayList<String>();
		Bundle bolsa=getIntent().getExtras();
		Bundle bolsa1=getIntent().getExtras();
		listaRec.addAll(bolsa.getStringArrayList("Capacitacion"));
		 tema=(TextView)findViewById(R.id.lbl_temap);
		 lugar=(TextView)findViewById(R.id.lbl_lugarp);
		 f_inicio=(TextView)findViewById(R.id.lbl_iniciop);
		 f_fin=(TextView)findViewById(R.id.lbl_finp);
		 h_inicio=(TextView)findViewById(R.id.lbl_hiniciop);
		 h_fin=(TextView)findViewById(R.id.lbl_hfip);
		 String cal=bolsa1.getString("calificacion");
		 calificacion=(TextView)findViewById(R.id.lbl_calificacionh);
		 tema.setText(listaRec.get(0));
		 lugar.setText(listaRec.get(1));
		 f_inicio.setText(listaRec.get(2));
		 f_fin.setText(listaRec.get(3));
		 h_inicio.setText(listaRec.get(4));
		 h_fin.setText(listaRec.get(5));
		 calificacion.setText(cal);
		 cancelar=(Button)findViewById(R.id.btn_cancelari);
		 cancelar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				
			}
		});
	
	}

}
