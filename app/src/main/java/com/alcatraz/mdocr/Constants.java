package com.alcatraz.mdocr;
import java.util.*;
import com.alcatraz.mdocr.beans.*;

public class Constants
{
	private List<OSP> osps;
	public Constants(){
		osps=new LinkedList<OSP>();
		_initOsp();
	}
	private void _initOsp(){
		//FabSpeedDia
		OSP o1=new OSP();
		o1.setAuthor("yavski");
		o1.setUrl("https://github.com/yavski/fab-speed-dial");
		o1.setintro("A simple library marrying together [FAB] ");
		o1.setLicense("Apache 2.0");
		o1.setName("fab-speed-dial");
		//uCrop
		OSP o2=new OSP();
		o2.setAuthor("Yalantis");
		o2.setUrl("https://github.com/Yalantis/uCrop");
		o2.setintro("Image Cropping Library for Android");
		o2.setLicense("Apache 2.0");
		o2.setName("uCrop");
		//OkHttp
		OSP o3=new OSP();
		o3.setAuthor("square");
		o3.setUrl("https://github.com/square/okhttp");
		o3.setintro("An HTTP & HTTP/2 client for Android and Java applications. ");
		o3.setLicense("Apache 2.0");
		o3.setName("OkHttp");
		//Okio
		OSP o4=new OSP();
		o4.setAuthor("square");
		o4.setUrl("https://github.com/square/okio");
		o4.setintro("A modern I/O API for Java ");
		o4.setLicense("Apache 2.0");
		o4.setName("Okio");
		
		
		//Adding Process
		osps.add(o1);
		osps.add(o2);
		osps.add(o3);
		osps.add(o4);
	}
	public List<OSP> getOsps(){
		return osps;
	}
}
