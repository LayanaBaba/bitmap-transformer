package bitmap.transformer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bitmap {
    File inputImage;
    File outputImage;
    String transformName;

    public Bitmap(String transformName , String outputPath, String inputPath){
        this.inputImage= new File(inputPath);
        this.outputImage=new File(outputPath+"/"+transformName+".bmp");
        this.transformName=transformName;
    }

    public void outputFile(BufferedImage image){
        try{
            ImageIO.write(image, "bmp", this.outputImage);
            System.out.println(this.outputImage.getPath());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public  Bitmap cyanBorder() throws  IOException{
        try{
            BufferedImage inputImage=ImageIO.read(this.inputImage);

            int width=2;

            for (int w = 0; w <inputImage.getWidth() ; w++) {
                for (int h = 0; h < inputImage.getHeight(); h++) {
                    if(h<width || h+width>=inputImage.getHeight() || w<width || w+width>=inputImage.getWidth()){
                        inputImage.setRGB(w,h,Color.CYAN.getRGB());
                    }
                }
            }
            this.outputFile(inputImage);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}


