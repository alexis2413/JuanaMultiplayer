package pe.edu.pucp.game.utils;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("serial")
@XmlRootElement(name = "Data")
@XmlType(propOrder = {"files"})
public class Data implements Serializable{
	private ArrayList<String> files = new ArrayList<String> (8);
	public Data(){
		//files=new ArrayList<String>();
	}
	public ArrayList<String> getFiles() {return files;}
	public void setFiles(ArrayList<String> files) {this.files = files;}
}
