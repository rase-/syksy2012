import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;

class Image implements Comparable<Image> {
  public double[] vec;
  public int characterClass;

  public Image() {}

  public int compareTo(Image i1) {
    return this.characterClass - i1.characterClass;
  }
}

public class Perceptron {
  static int targetChar = 1;    // tama on plus-luokka
  static int oppositeChar = 8;  // tama on miinus-luokka
  static Vector<Image> I = new Vector<Image>();

  // lukee x- ja y-tiedostot
  static void readImages(String xfilename, String yfilename) {
    try {
      Scanner xscanner = new Scanner(new File(xfilename));
      Scanner yscanner = new Scanner(new File(yfilename));
      while(xscanner.hasNextLine()) {
        Image i = new Image();
        String line = xscanner.nextLine();
        int characterClass = yscanner.nextInt();
        String splitarr[] = line.split(",");
        i.vec = new double[28*28];
        int j = 0;
        for(String s : splitarr) {
          if(s.equals("1")) i.vec[j++] = 1.0;
          else i.vec[j++] = -1.0;
        }
        i.characterClass = characterClass;
        I.addElement(i);
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  // toteuta tahan perseptronialgoritmi, joka palauttaa painovektorin
  // kayttaen opetusdatana 5000 ensimmaista x,y -paria.
  static double[] train(int steps) {
    // Tässä otetaan vain asiaankuuluvat kuvat
    Vector<Image> images = new Vector<Image>();
    for (Image i : I) {
      if (i.characterClass == targetChar || i.characterClass == oppositeChar) {
        images.add(i);
      }
    }

    Random rand = new Random();
    double[] w = new double[28*28];

    for (int step = 0; step < steps;) {
	    step++;
      int r = rand.nextInt(images.size());
      Image x = images.get(r);
      int z = 0;

      for (int i = 0; i < w.length; i++) {
        z += w[i] * x.vec[i];
      }
      if (z >= 0 && x.characterClass == oppositeChar) {
        for (int i = 0; i < w.length; i++) { // vector subtraction
          w[i] = w[i] - x.vec[i];
        }
      }

      if (z < 0 && x.characterClass == targetChar) {
        for (int i = 0; i < w.length; i++) { // vector addition
          w[i] = w[i] + x.vec[i];
        }
      }

    }
    return w;
  }

  // testaa opittua perseptronia (painovektorilla w) viimeiseen 1000
  // x,y -pariin. (laskee vain ne, jotka kuuluvat joko plus- tai miinus-luokkaan
  static double test(double[] w) {
    int success = 0;
    int trials = 0;

    for (int example = 5000; example < (int)I.size(); example++) {

	    // valitetaan vain + ja - -luokista.
	    if (I.elementAt(example).characterClass != targetChar &&
          I.elementAt(example).characterClass != oppositeChar)
        continue;

	    // laske z
	    double z = 0.0;
	    for (int j = 0; j < 28*28; j++)
        z += I.elementAt(example).vec[j] * w[j];

	    // oliko luokitus oikein?
	    if ((z >= 0 && I.elementAt(example).characterClass == targetChar) ||
          (z <  0 && I.elementAt(example).characterClass == oppositeChar))
        success++;
	    trials++;
    }

    return (double)(trials-success)/trials;
  }

  static void testInput() {
    // otetaan sata ensimmaista, jarjestetaan characterClassin
    // mukaan, ja piirretaan iso kuva, josta voi tarkistaa, etta
    // samat numeroa esittavat kuvat tulevat perakkain.

    Vector<Image> I100 = new Vector<Image>();
    for(int i = 0; i < 100; ++i) I100.addElement(I.elementAt(i));

    Collections.sort(I100);

    BufferedImage bi = new BufferedImage(28*100,28,
                                         BufferedImage.TYPE_3BYTE_BGR);

    for(int i = 0; i < 100; ++i) {
      for(int y = 0; y < 28; ++y) {
        for(int x = 0; x < 28; ++x) {
          int ind = y*28+x;
          bi.setRGB(x+i*28,y,
                    (I100.elementAt(i).vec[ind]>0.5?
                     0:0xffffff));
        }
      }
    }
    try {
      ImageIO.write(bi,"BMP",new File("test100.bmp"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  static void visualizeWeights(double[] w) {
    // piirretaan kuva, jossa 28 x 28 painovektorin arvot
    // kuvataan harmaasavyiksi.

    BufferedImage bi = new BufferedImage(28,28,
                                         BufferedImage.TYPE_3BYTE_BGR);

    for(int y = 0; y < 28; ++y) {
	    for(int x = 0; x < 28; ++x) {
        int ind = y*28+x;
        float w01 = .01f+.98f/(1.0f+(float)Math.exp(-w[ind]));
        bi.setRGB(x,y,(new Color(w01,w01,w01)).getRGB());
	    }
    }
    try {
      ImageIO.write(bi,"BMP",new File("weights.bmp"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    if (args.length < 3) {
	    System.err.println("usage: java Perceptron <xfile> <yfile> <training-steps>");
	    return;
    }

    System.out.println("Learning to classify " + targetChar + " vs " + oppositeChar);
    readImages(args[0],args[1]);
    testInput();
    int steps = Integer.parseInt(args[2]);
    System.out.print("Perceptron learning algorithm, " + steps + " iterations...");
    double[] w = train(steps);
    visualizeWeights(w);
    System.out.println(" complete");
    System.out.println("Failure rate: "+100*test(w)+" %");
  }
};
