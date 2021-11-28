import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


public class Main extends JComponent  {

    final String PATH = "D:\\VIsualizationDataset\\output_image.jpeg";

    public static void main(String[] args) {
        //create an object for the contents of the window
        Main main = new Main();
        //creating a window object
        JFrame f = new JFrame("DatasetVisualization");
        f.setSize(960, 540);
        f.setVisible(true);
        f.setLocation(40, 40);
        f.add(main);
    }

    @Override
    public void paint(Graphics gr) {
        BufferedImage bImg = new BufferedImage(960, 540, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphy = (Graphics2D)gr;
        Graphics2D grapImage = bImg.createGraphics();
        //change the origin
        graphy.translate(0, getHeight() - 1);
        graphy.scale(1, -1);
        grapImage.translate(0, getHeight() - 1);
        grapImage.scale(1, -1);
        //set background color
        grapImage.setColor(Color.WHITE);
        //creating a background
        grapImage.fillRect(0, 0, 960, 540);
        //set the color of the points
        grapImage.setColor(Color.BLACK);
        String line = null;
        String [] string;
        try {
            //create creating a point object
            Rectangle point = new Rectangle(1, -1, 1, 1);
            BufferedReader reader = new BufferedReader(new FileReader("DS6.txt"));

            //loop, to read points from the dataset and display them
            while ((line = reader.readLine()) != null) {
                string = line.split(" ");
                point.x = Integer.parseInt(string[1]);
                point.y = Integer.parseInt(string[0]);
                graphy.fill(point);
                grapImage.draw(point);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //save in graphic format
            ImageIO.write(bImg, "jpeg", new File(PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




