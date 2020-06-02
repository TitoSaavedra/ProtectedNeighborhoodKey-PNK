/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.pnk.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;

/**
 *
 * @author TitoS
 */
public class UtilidadesPrograma {

    /**
     *
     * @param bf
     * @return
     */
    public Image convertirImagen(BufferedImage bf) {
        WritableImage wr = null;
        wr = new WritableImage(bf.getWidth(), bf.getHeight());
        PixelWriter pw = wr.getPixelWriter();
        for (int x = 0; x < bf.getWidth(); x++) {
            for (int y = 0; y < bf.getHeight(); y++) {
                pw.setArgb(x, y, bf.getRGB(x, y));
            }
        }
        return new ImageView(wr).getImage();
    }

    public byte[] imagenAByte(Image imagen) {
        BufferedImage bufferimage = SwingFXUtils.fromFXImage(imagen, null);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferimage, "jpg", output);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        byte[] data = output.toByteArray();
        return data;
    }
}
