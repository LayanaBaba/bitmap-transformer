package bitmap.transformer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Bitmap {
    public String inputPath;
    public String outputPath;
    public String transformName;
    public int height;
    public int width;


    public Bitmap(){
        inputPath= null;
        outputPath=null;
        transformName=null;
    }

    public Bitmap(String inputPath){
        this.inputPath= inputPath;
        this.outputPath=null;
        this.transformName=null;

    }

    public Bitmap(String inputPath, String outputPath){
        this.inputPath= inputPath;
        this.outputPath=outputPath;
        this.transformName=null;
        write();
    }

    public Bitmap(String inputPath, String outputPath, String transformName){
        this.inputPath= inputPath;
        this.outputPath=outputPath;
        this.transformName=transformName;
        write();
    }


    public void read(){
        BufferedImage image;
        try{
            image= ImageIO.read(new File(this.inputPath));
            this.height =  image.getHeight();
            this.width=image.getWidth();
            imageColor(image);

        }catch (IOException e){
            System.out.println("");
        }

    }

    public void imageColor(BufferedImage image){
        ArrayList<Color>bits = new ArrayList<>();

        for (int i = 1; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                int rgb=image.getRGB(j,i);
                Color color=new Color(rgb);
                bits.add(color);
            }
            
        }
        getRGBA(bits);
    }

    public static ArrayList<ArrayList<Integer>> getRGBA(ArrayList<Color> input){
        ArrayList <ArrayList<Integer>> result = new ArrayList<>();

        for (Color binary: input){
            ArrayList<Integer> degree = new ArrayList<>();
            degree.add(binary.getRed());
            degree.add(binary.getBlue());
            degree.add(binary.getGreen());
            result.add(degree);
        }
        return result;
    }

    void write() {
        BufferedImage bi;

        if(this.outputPath!=null){
            File outputFile = new File(this.outputPath);

            try{
                bi = ImageIO.read(new File(this.inputPath));
                height=bi.getHeight();
                width=bi.getWidth();

                for (int i = 0; i <height ; i++) {
                    for (int j = 0; j < width; j++) {
                        Color color1=new Color(bi.getRGB(j,i));
                        int blue= (int) (color1.getBlue()*0.114);
                        int green= (int) (color1.getGreen()*0.587);
                        int red= (int) (color1.getRed()*0.299);
                        Color color2=new Color(red,green,blue);

                        if (this.transformName.equals("gray")) {
                            color2=grayScale(red,green,blue);
                        }else if (this.transformName.equals("invert")) {
                            color2= invert(red,green,blue);
                        }else if(this.transformName.equals("random")){
                            color2 = randomize(red,green,blue);
                        }
                        bi.setRGB(j, i, color2.getRGB());
                    }
                }
                ImageIO.write(bi,"bmp",outputFile);
            } catch (IOException e) {
                System.out.println("Missing an outputFile");
            }
        } else{
            System.out.println("NO FILEPATH!!");
        }
    }

    public Color grayScale(int red, int green,int blue){
        return new Color(red + green + blue,
                red + green + blue, red + green + blue);
    }

    public Color invert(int red, int green,int blue){
        return new Color(blue /2 + green / 2 + red/2);
    }

    public Color randomize(int red, int green,int blue){
        Random rand = new Random();
        int redd = rand.nextInt(red + 50);
        int greenn = rand.nextInt(green + 50);
        int bluee = rand.nextInt(blue + 50);
        System.out.println("Red: "+redd+"Green: "+greenn + "Blue: "+bluee);
        return new Color(redd,greenn,bluee);
    }


}


