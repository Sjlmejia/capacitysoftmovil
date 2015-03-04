package com.tesis.capacitysoft;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Informacion_capacitacion extends Activity {
	String aux;
	TextView tema;
	TextView lugar;
	TextView f_inicio;
	TextView f_fin;
	TextView h_inicio;
	TextView h_fin;
	Button cancelar;
	TextView observaciones;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.informacion_capacitacion);
		ArrayList<String> listaRec=new ArrayList<String>();
		Bundle bolsa=getIntent().getExtras();
		listaRec.addAll(bolsa.getStringArrayList("Capacitacion"));
		 tema=(TextView)findViewById(R.id.lbl_tema);
		 lugar=(TextView)findViewById(R.id.lbl_lugar);
		 f_inicio=(TextView)findViewById(R.id.lbl_inicio);
		 f_fin=(TextView)findViewById(R.id.lbl_fin);
		 h_inicio=(TextView)findViewById(R.id.lbl_hinicio);
		 h_fin=(TextView)findViewById(R.id.lbl_hfin);
		 observaciones=(TextView)findViewById(R.id.lbl_observaciones);
		 tema.setText(listaRec.get(0));
		 lugar.setText(listaRec.get(1));
		 f_inicio.setText(listaRec.get(2));
		 f_fin.setText(listaRec.get(3));
		 h_inicio.setText(listaRec.get(4));
		 h_fin.setText(listaRec.get(5));
		 observaciones.setText(listaRec.get(6));
		 cancelar=(Button)findViewById(R.id.btn_cancelarinformacioncpacitaciones);
		 cancelar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

}
