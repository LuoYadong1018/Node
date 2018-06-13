package lyd.dao;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

import java.util.List;

import lyd.model.Tb_outaccount;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class OutaccountDAO {
private DBOpenHelper helper;
private SQLiteDatabase db;
public OutaccountDAO(Context context){
	helper=new DBOpenHelper(context);	
}
public void add(Tb_outaccount tb_outaccount){
	db=helper.getWritableDatabase();
	db.execSQL("insert into tb_outaccount(_id,money,time,type,address,mark) values (?,?,?,?,?,?)",new Object[]{tb_outaccount.getid(),tb_outaccount.getMoney(),
			tb_outaccount.getTime(),tb_outaccount.getType(),tb_outaccount.getAddress(),
			tb_outaccount.getMark()});
}
public void update(Tb_outaccount tb_outaccount){
	db=helper.getWritableDatabase();
	db.execSQL("update tb_outaccount set money=?,time=?,type=?,address=?,mark=? where _id=?",
					new Object[]{tb_outaccount.getMoney(),
				tb_outaccount.getTime(),tb_outaccount.getType(),tb_outaccount.getAddress(),
				tb_outaccount.getMark(),tb_outaccount.getid()});
}
public Tb_outaccount find(int id){
	db=helper.getWritableDatabase();
	Cursor cursor =db.rawQuery("select _id,money,time,type,address,mark from tb_outaccount where _id=?",
				new String[]{String.valueOf(id)});
	if (cursor.moveToNext()) {
		return new Tb_outaccount(cursor.getInt(cursor.getColumnIndex("_id")),
				cursor.getDouble(cursor.getColumnIndex("money")),
					cursor.getString(cursor.getColumnIndex("time")),
							cursor.getString(cursor.getColumnIndex("type")),
					cursor.getString(cursor.getColumnIndex("address")),
							cursor.getString(cursor.getColumnIndex("mark")));
	}
	cursor.close();
	return null;
	}
public void delete(Integer...ids){
	db=helper.getWritableDatabase();
	if(ids.length>0){
		StringBuffer sb=new StringBuffer();
		for (int i=0;i<ids.length;i++){
			sb.append('?').append(',');
		}
	sb.deleteCharAt(sb.length()-1);
	db.execSQL("delete from tb_outaccount", (Object[])
			ids);
	}
}
public List<Tb_outaccount> getScrollData(int start ,int count){
	db=helper.getWritableDatabase();
	List<Tb_outaccount>tb_inaccounts=new ArrayList<Tb_outaccount>();
	Cursor cursor=db.rawQuery("select * from tb_outaccount limit ?, ?",
			new String []{String.valueOf(start),String.valueOf(count)});
	while (cursor.moveToNext()){
		tb_inaccounts.add(new
				Tb_outaccount(cursor.getInt(cursor.getColumnIndex("_id")),
						cursor.getDouble(cursor.getColumnIndex("money")),
						cursor.getString(cursor.getColumnIndex("time")),
						cursor.getString(cursor.getColumnIndex("type")),
						cursor.getString(cursor.getColumnIndex("address")),
						cursor.getString(cursor.getColumnIndex("mark"))));
	}
	cursor.close();
	return tb_inaccounts;
}
public long getCount(){
	db=helper.getWritableDatabase();
	Cursor cursor = db.rawQuery("select count(_id)from tb_outaccount", null);
	if(cursor.moveToNext()){
		return cursor.getLong(0);
	}
	cursor.close();
	return 0;
}
public int getMaxId(){
	db=helper.getWritableDatabase();
	Cursor cursor=db.rawQuery("select max(_id) from tb_outaccount", null);
	while(cursor.moveToLast()){
		return cursor.getInt(0);
	}
	cursor.close();
	return 0; 
}
public Map<String, Float> getTotal(){
	
	db=helper.getWritableDatabase();
	
	Cursor cursor=db.rawQuery("select type,sum(money)"
			+"from tb_outaccount group by type",null);
	int count=0;
	count=cursor.getCount();
	Map<String, Float> map=new HashMap<String,Float>();
	cursor.moveToFirst();
	for(int i=0;i<count;i++){
		map.put(cursor.getString(0), cursor.getFloat(1));
		cursor.moveToNext();
	}
	return map;
}
}
