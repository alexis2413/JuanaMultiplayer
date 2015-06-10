package pe.edu.pucp.game.gfx;

import java.awt.image.BufferedImage;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Assets implements Serializable{
	
	public Assets(){}
	public static BufferedImage player1, player2, player3, player4,player5,player6,player7,player8,player9,player10,player11,player12;
	public static BufferedImage grass,rock,door,ground;
	public static BufferedImage chicken;
	public static BufferedImage juanaBackground, juanaCombat, chickenCombat;
	public static BufferedImage angryJuana, angryChicken, fireChicken;
	public static BufferedImage boulder;
	public static BufferedImage npc1;
	public static BufferedImage berry;
	public static BufferedImage load,save;
        public static BufferedImage sea1,sea2,sea3,sea4;
        public static BufferedImage sea1Juana,sea2Juana,sea3Juana,sea4Juana;
        
	private static final int width =32, height = 48;
	public static void init(int i){
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/juana.png"));
		SpriteSheet sheet2 = new SpriteSheet(ImageLoader.loadImage("/textures/textures.jpg"));
		SpriteSheet sheet3 = new SpriteSheet(ImageLoader.loadImage("/textures/pollo.gif"));
		SpriteSheet sheet4 = new SpriteSheet(ImageLoader.loadImage("/textures/pokemon_sprites.png"));
		SpriteSheet sheet5 = new SpriteSheet(ImageLoader.loadImage("/textures/items.png"));
                
		int j;
		if(i>=1&&i<=4)j=1;
		else j=2;
		if(i==5) i=1;
		if(i==6) i=2;
		if(i==7) i=3;
		if(i==8) i=4;
		
		player1=sheet.crop(3*i*width-3*width,4*j*height-4*height,width,height);
		player2=sheet.crop(3*i*width-2*width,4*j*height-4*height,width,height);
		player3=sheet.crop(3*i*width-width,4*j*height-4*height,width,height);
		
		player4=sheet.crop(3*i*width-3*width,4*j*height-3*height,width,height);
		player5=sheet.crop(3*i*width-2*width,4*j*height-3*height,width,height);
		player6=sheet.crop(3*i*width-width, 4*j*height-3*height, width, height);
		
		player7=sheet.crop(3*i*width-3*width,4*j*height-2*height,width,height);
		player8=sheet.crop(3*i*width-2*width,4*j*height-2*height,width,height);
		player9=sheet.crop(3*i*width-width,4*j*height-2*height,width,height);
		
		player10=sheet.crop(3*i*width-3*width,4*j*height-height,width,height);
		player11=sheet.crop(3*i*width-2*width,4*j*height-height,width,height);
		player12=sheet.crop(3*i*width-width,4*j*height-height,width,height);

		
		grass=sheet2.crop(0,800,64,64);
		rock=sheet2.crop(0,400,64,64);
		door=sheet2.crop(300,550,64,64);
		ground=sheet2.crop(300,912,64,64);
                
                sea1=ImageLoader.loadImage("/textures/sea1.png");
                sea2=ImageLoader.loadImage("/textures/sea2.png");
                sea3=ImageLoader.loadImage("/textures/sea3.png");
                sea4=ImageLoader.loadImage("/textures/sea4.png");
                sea1Juana=ImageLoader.loadImage("/textures/sea1Juana.png");
                sea2Juana=ImageLoader.loadImage("/textures/sea1Juana1.jpg");
                sea3Juana=ImageLoader.loadImage("/textures/sea1Juana2.jpg");
                sea4Juana=ImageLoader.loadImage("/textures/sea1Juana.png");
		
		boulder=sheet2.crop(650,10,64,64);
                
		chicken=sheet3.crop(0,0,20,20);
		
		npc1=sheet4.crop(0,0,20,25);
		
		berry=sheet5.crop(0,0,22,22);
		
		juanaBackground=ImageLoader.loadImage("/textures/portada.jpg");
		juanaCombat=ImageLoader.loadImage("/textures/juana_combate.png");
		chickenCombat=ImageLoader.loadImage("/textures/chicken.jpg");
		angryJuana=ImageLoader.loadImage("/textures/angry_juana.png");
		angryChicken=ImageLoader.loadImage("/textures/angry_chicken.jpg");
		//fireChicken=ImageLoader.loadImage("/textures/chicken_fire.gif");
		
		load=ImageLoader.loadImage("/textures/folder.png");
		save=ImageLoader.loadImage("/textures/save.png");
	}
}
