/**
 /* This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License, version 3.0, as published by the
 * Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package vodka;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL12;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by lucy on 23.03.17.
 */
public abstract class Texture {
    public static int loadTexture(String f){
        int r = loadPNGTexture(f, GL13.GL_TEXTURE0);
        Trashman.texture_ids.add(r);
        System.out.println("loaded texture "+r);
        return r;
    }
    private static int loadPNGTexture(String filename, int textureUnit){
        int BYTES_PER_PIXEL = 4;

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] pixels = new int[image.getWidth() * image.getHeight()];
            image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

            ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * BYTES_PER_PIXEL); //4 for RGBA, 3 for RGB

            for(int y = 0; y < image.getHeight(); y++){
                for(int x = 0; x < image.getWidth(); x++){
                    int pixel = pixels[y * image.getWidth() + x];
                    buffer.put((byte) ((pixel >> 16) & 0xFF));     // Red component
                    buffer.put((byte) ((pixel >> 8) & 0xFF));      // Green component
                    buffer.put((byte) (pixel & 0xFF));               // Blue component
                    buffer.put((byte) ((pixel >> 24) & 0xFF));    // Alpha component. Only for RGBA
                }
            }

            buffer.flip(); //FOR THE LOVE OF GOD DO NOT FORGET THIS

            // You now have a ByteBuffer filled with the color data of each pixel.
            // Now just create a texture ID and bind it. Then you can load it using
            // whatever OpenGL method you want, for example:

            int textureID = glGenTextures(); //Generate texture ID
            glBindTexture(GL_TEXTURE_2D, textureID); //Bind texture ID

            //Setup wrap mode
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

            //Setup texture scaling filtering
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

            //Send texel data to OpenGL
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

            //Return the texture ID so we can bind it later again
            return textureID;
    }
    /*private static int loadPNGTexture(String filename, int textureUnit) {
        IntBuffer buf = null;
        int tWidth = 0;
        int tHeight = 0;

        try {
            // Open the PNG file as an InputStream
            InputStream in = new FileInputStream(filename);
            // Link the PNG decoder to this stream
            //PNGDecoder decoder = new PNGDecoder(in);

            // Get the width and height of the texture
            //tWidth = decoder.getWidth();
            //tHeight = decoder.getHeight();


            // Decode the PNG file in a ByteBuffer
            /buf = ByteBuffer.allocateDirect(
                    4 * decoder.getWidth() * decoder.getHeight());
            decoder.decode(buf, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
            buf.flip();/

            BufferedImage img = ImageIO.read(new File(filename));
            tWidth = img.getWidth();
            tHeight = img.getHeight();
            buf = BufferUtils.createIntBuffer(tWidth*tHeight*4);
            Color c;
            for(int x=0;x<tWidth;x++){
                for(int y=0;y<tHeight;y++){
                    c = new Color(img.getRGB(x, y));
                    if(c.getAlpha()!=0) {
                        buf.put((byte) c.getRed()).put((byte) c.getGreen()).put((byte) c.getBlue()).put((byte) 1);
                    } else {
                        buf.put((byte)Color.white.getRed()).put((byte)Color.white.getGreen()).put((byte)Color.white.getBlue()).put((byte)1);
                    }
                }
            }
            buf.flip();

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        // Create a new texture object in memory and bind it
        int texId = GL11.glGenTextures();
        GL13.glActiveTexture(textureUnit);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texId);

        // All RGB bytes are aligned to each other and each component is 1 byte
        GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);

        // Upload the texture data and generate mip maps (for scaling)
        /GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, tWidth, tHeight, 0,
                GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buf);/
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, tWidth, tHeight, 0,
                GL11.GL_RGBA, GL11.GL_UNSIGNED_INT, buf);
        GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);

        // Setup the ST coordinate system
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);

        // Setup what to do when the texture has to be scaled
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER,
                GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER,
                GL11.GL_LINEAR_MIPMAP_LINEAR);

        //this.exitOnGLError("loadPNGTexture");

        return texId;
    }*/
}
