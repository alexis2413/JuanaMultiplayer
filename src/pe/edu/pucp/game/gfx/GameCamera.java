package pe.edu.pucp.game.gfx;

import java.io.Serializable;

import pe.edu.pucp.game.entities.Entity;


//@XmlRootElement(name = "GameCamera")
//@XmlType(propOrder = {"DEFAULT_CAMERA_OFFSET", "xOffset", "yOffset", "width","height"})
@SuppressWarnings("serial")
public class GameCamera implements Serializable{
	
	public static int DEFAULT_CAMERA_OFFSET=24;
	private double xOffset,yOffset;
	private int cameraWidth,cameraHeight;
	
	public GameCamera(){}
	
	public GameCamera(int width, int height,double xOffset,double yOffset){	
		this.cameraWidth=width;
		this.cameraHeight=height;
		this.xOffset=xOffset;
		this.yOffset=yOffset;
	}
	
	public void move(double xAmt,double yAmt){
		yOffset+=yAmt;
		xOffset+=xAmt;
	}
	
	public void centerOnEntity(Entity e){
		xOffset=e.getX()*DEFAULT_CAMERA_OFFSET-cameraWidth/2 +e.getWidth()/2;
		yOffset=e.getY()*DEFAULT_CAMERA_OFFSET-cameraHeight/2 +e.getHeight()/2;
	}
	
	public double getxOffset() {
		return xOffset;
	}
	public void setxOffset(double xOffset) {
		this.xOffset = xOffset;
	}
	public double getyOffset() {
		return yOffset;
	}
	
	public void setyOffset(double yOffset) {
		this.yOffset = yOffset;
	}
	
	public int getCameraWidth() {
		return cameraWidth;
	}

	public void setCameraWidth(int cameraWidth) {
		this.cameraWidth = cameraWidth;
	}

	public int getCameraHeight() {
		return cameraHeight;
	}

	public void setCameraHeight(int cameraHeight) {
		this.cameraHeight = cameraHeight;
	}

	public static int getDefaultCameraOffset() {
		return DEFAULT_CAMERA_OFFSET;
	}

	public static int getDEFAULT_CAMERA_OFFSET() {
		return DEFAULT_CAMERA_OFFSET;
	}

	public static void setDEFAULT_CAMERA_OFFSET(int dEFAULT_CAMERA_OFFSET) {
		DEFAULT_CAMERA_OFFSET = dEFAULT_CAMERA_OFFSET;
	}


}
