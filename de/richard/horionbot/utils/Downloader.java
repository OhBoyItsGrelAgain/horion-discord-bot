package de.richard.horionbot.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

public class Downloader
{
    public static String webpage(String urlText)
    {
        StringBuilder webpageText = new StringBuilder();
        URL url;
        InputStream is = null;
        BufferedReader br;
        String line;

        try
        {
            url = new URL(urlText);
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null)
            {
                webpageText.append(line);
            }
        } catch (IOException mue)
        {
            mue.printStackTrace();
            return null;
        } finally
        {
            try
            {
                if (is != null) is.close();
            }
            catch (IOException ioe)
            {
                // nothing to see here
            }
        }
        return webpageText.toString();
    }

    static BufferedImage image(String urlText)
    {
        try
        {
            return ImageIO.read(new URL(urlText));
        }
        catch (IOException e)
        {
            System.out.printf("Could not find image at URL: %s\n", urlText);
            e.printStackTrace();
            return null;
        }
    }

    public static File file(String urlText, String fileName) throws IOException
    {
        File file = new File(fileName);
        file.getParentFile().mkdirs();

        URL link = new URL(urlText); //The file that you want to download

        //Code to download
        InputStream in = new BufferedInputStream(link.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n;
        while (-1!=(n=in.read(buf)))
        {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();
        byte[] response = out.toByteArray();

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(response);
        fos.close();
        return file;
    }
}
