package com.tesis.capacitysoft;




import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;


import com.tesis.capacitysoft.servidor.HttpGetData;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
public class MainActivity extends Activity  {
	EditText correo;
	EditText contrasenia;
	ImageView logo;
	Button Login;
	Button Registrar;
	Button Recuperar;
	TextView inicio;
	JSONArray ja;
	JSONArray jas;
	JSONArray jas2;
	String data;
	JSONArray jas3;
	String data3;
	JSONArray jas4;
	String data4;
	String data1;
	String data2;
	String cedula;
	Intent i;
	String prueba;
	String capacita;
	Handler h = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			Toast.makeText(getApplicationContext(), "Campos vacios", 3000).show();
		}
		
	};
	
  	Handler h1 = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			Toast.makeText(getApplicationContext(), "Datos Incorrectos", 3000).show();
		}
		
	};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        correo =(EditText)findViewById(R.id.txt_correoca);
        contrasenia =(EditText)findViewById(R.id.txt_contrasenia);
        Login =(Button)findViewById(R.id.btn_login);
        Recuperar=(Button)findViewById(R.id.btn_recuperarCon);
       Registrar=(Button)findViewById(R.id.btn_registro);
       logo=(ImageView)findViewById(R.id.imageView1);
       ObjectAnimator.ofFloat(logo, "rotationY", 0, 360).setDuration( 2 * 1000).start();
      // ObjectAnimator.ofFloat(Registrar, "rotationY", 0, 360).setDuration( 2 * 1000).start();
       //ObjectAnimator.ofFloat(Recuperar, "rotationY", 0, 360).setDuration( 2 * 1000).start();
       final HttpGetData hgd=new HttpGetData();
      
      Login.setOnClickListener(new OnClickListener()  {
    	  
			@Override
			public void onClick(View v)  {
				// TODO Auto-generated method stub
				//final Capacitaciones ca=new Capacitaciones();
				//Login.setBackground("#D8D8D8");
			
				new Thread(new Runnable() {
					
					@Override
					public void run()  {
						// TODO Auto-generated method stub
						
						 jas = null;
						 
						 data1 = hgd.httpGetData("http://192.168.43.20/ejemplo/login.php?correo="+correo.getText()+"&contrasenia="+contrasenia.getText());
							
						if(correo.getText().equals("")||contrasenia.getText().equals("")){
							h.sendEmptyMessage(1);
						}else{
						if (data1.length()>0){

									try {
							jas=new JSONArray(data1);
							
							cedula=jas.getString(0);

						    i= new Intent(MainActivity.this, Menu_Capacitaciones.class );
							Bundle bolsa=new Bundle();
					
							 bolsa.putString("Capacitado",cedula);
							
							 i.putExtras(bolsa);
						
						    startActivity(i);

									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
						}else{
						    h1.sendEmptyMessage(1);
						}
						}
						
						
						}

					
				}).start();
				
			}
		});
        Registrar.setOnClickListener(new View.OnClickListener(){
       
        	public void onClick(View view){
        		
        		new Thread(new Runnable() {
        			
        			@Override
        			public void run() {
        				// TODO Auto-generated method stub
        				HttpGetData vd=new HttpGetData();
        				ArrayList<String> lista =new ArrayList<String>();
        				ArrayList<String> lista2 =new ArrayList<String>();
        				//String capacitacion;
        		
        			data3= 	vd.httpGetData("http://192.168.43.20/ejemplo/recuperaSectores.php");
        			if(data3.length()>0){
        				try {
        					jas3=new JSONArray(data3);
        					for(int i=0;i<=jas3.length()-1;i++){
        						lista.add(jas3.getString(i));
        					}
        				} catch (JSONException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        				}
        			
        			}
        			data4= 	vd.httpGetData("http://192.168.43.20/ejemplo/recuperaTiposC.php");
        			if(data4.length()>0){
        				try {
        					jas4=new JSONArray(data4);
        					for(int i=0;i<=jas4.length()-1;i++){
        						lista2.add(jas4.getString(i));
        					}
        				} catch (JSONException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        				}
        			
        			}
        			Bundle bolsa =new Bundle();
        			bolsa.putStringArrayList("Nombre",lista);
        			Bundle bolsa2 =new Bundle();
        			bolsa.putStringArrayList("Tipo",lista2);
        			Intent i = new Intent(MainActivity.this, Registro.class );
        			i.putExtras(bolsa2);
					i.putExtras(bolsa);
                    startActivity(i);
            		
        			}
        		}).start();
        		
        	
        	}
        													});
        Recuperar.setOnClickListener(new View.OnClickListener(){
            
        	public void onClick(View view){
        		Intent i = new Intent(MainActivity.this, Recuperar_Contrasenia.class );
                startActivity(i);
        		
        	}
        													});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


	

}
