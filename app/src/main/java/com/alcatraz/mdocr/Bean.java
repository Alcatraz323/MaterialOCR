package com.alcatraz.mdocr;
import java.util.*;

public class Bean
{
	private List<String> res;
	private String dir;
	public Bean(List<String> res,String dir){
		this.res=res;
		this.dir=dir;
	}
	public List<String> getList(){
		return res;
	}
	public String getDir(){
		return dir;
	}
	public void setDir(String dir){
		this.dir=dir;
	}
}
