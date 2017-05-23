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

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;

/**
 * Created by luciano.willems on 11.05.2017.
 */
public class SystemUtils {
    private static KeyPair key;
    private static Cipher cipher;
    private static boolean init = false;
    public static void init(){
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            TimeKeeper.mark("initKeyPair");
            System.out.println("keySize = "+(1024*4));
            keyGen.initialize(1024*4);//16384
            key = keyGen.generateKeyPair();
            cipher = Cipher.getInstance("RSA");
            TimeKeeper.print("initKeyPair");
            init = true;
        } catch (Exception e2){
            e2.printStackTrace();
        }

    }
    private static String decrypt(String msg){
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key.getPrivate());
            return Base64.encodeBase64String(cipher.doFinal(msg.getBytes("UTF-8")));
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    private static String encrypt(String msg){
        try {
            cipher.init(Cipher.DECRYPT_MODE, key.getPublic());
            return new String(cipher.doFinal(Base64.decodeBase64(msg)), "UTF-8");
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}