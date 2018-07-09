package com.alcatraz.mdocr.beans;
import java.util.*;

public class Bean
{
	public static String FROM_CAMERA="0";
	public static String FROM_GALLERY="1";
	private List<String> res=new LinkedList<String>();
	private String dir;
	private String from;
	private String time;
	public List<String> getList(){
		return res;
	}
	public void clear(){
		res.clear();
	}
	public String getDir(){
		return dir;
	}
	public void setDir(String dir){
		this.dir=dir;
	}
	public void add(String a){
		res.add(a);
	}
	public void setTime(String t){
		time=t;
	}
	public void setFrom(String f){
		from=f;
	}
	public String getFrom(){
		return from;
	}
	public String getTime(){
		return time;
	}
}
