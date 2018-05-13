package ppcc.jhordyabonia.com.petropresidentecapturaaloscorruptos;

import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;

public class Store 
{
	public static final String STORE = "com.jhordyabonia.mb.store",_LEVEL="level",
	_VELOCIDAD="velocidad",_NAME="name",_IMG="img",_LIVES="lives";
		
	JSONArray store;
	public Store(Context t)
	{
		try
		{
			store=new JSONArray();
			JSONObject obj0= new JSONObject();
			obj0.put(_NAME,"Cesar Mauricio Velásquez");
			obj0.put(_VELOCIDAD,4000);
			obj0.put(_IMG,R.mipmap.c0);
			obj0.put(_LIVES,3);
			store.put(obj0);

			JSONObject obj1= new JSONObject();
			obj1.put(_NAME,"José Obdulio Gaviria");
			obj1.put(_VELOCIDAD,3500);
			obj1.put(_IMG,R.mipmap.c2);
			obj1.put(_LIVES,3);
			store.put(obj1);

			JSONObject obj2= new JSONObject();
			obj2.put(_NAME,"Edmundo del Castillo");
			obj2.put(_VELOCIDAD,3000);
			obj2.put(_IMG,R.mipmap.c3);
			obj2.put(_LIVES,3);
			store.put(obj2);
			JSONObject obj3= new JSONObject();
			obj3.put(_NAME,"Oscar Ivan Zuluaga");
			obj3.put(_VELOCIDAD,2500);
			obj3.put(_IMG,R.mipmap.c4);
			obj3.put(_LIVES,2);
			store.put(obj3);

			JSONObject obj4= new JSONObject();
			obj4.put(_NAME,"Luis Alfonso Hoyos");
			obj4.put(_VELOCIDAD,2000);
			obj4.put(_IMG,R.mipmap.c5);
			obj4.put(_LIVES,2);
			store.put(obj4);

			JSONObject obj5= new JSONObject();
			obj5.put(_NAME,"Luis Carlos Restrepo");
			obj5.put(_VELOCIDAD,1500);
			obj5.put(_IMG,R.mipmap.c6);
			obj5.put(_LIVES,3);
			store.put(obj5);
			JSONObject obj6= new JSONObject();
			obj6.put(_NAME,"Favio Buitrago");
			obj6.put(_VELOCIDAD,1000);
			obj6.put(_IMG,R.mipmap.c7);
			obj6.put(_LIVES,4);
			store.put(obj6);

			JSONObject obj7= new JSONObject();
			obj7.put(_NAME,"Luis Guillermo Giraldo");
			obj7.put(_VELOCIDAD,500);
			obj7.put(_IMG,R.mipmap.c8);
			obj7.put(_LIVES,4);
			store.put(obj7);

			JSONObject obj8= new JSONObject();
			obj7.put(_NAME,"Alvaro Uribe");
			obj7.put(_VELOCIDAD,300);
			obj7.put(_IMG,R.mipmap.c1);
			obj7.put(_LIVES,7);
			store.put(obj7);
		}catch (JSONException e){}
	}
	public JSONArray get(){return store;}
	public JSONObject get(int id) throws JSONException
	{return  store.getJSONObject(id);}
}
