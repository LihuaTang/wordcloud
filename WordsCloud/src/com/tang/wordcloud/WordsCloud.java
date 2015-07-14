/** 
 * @Title: WordCloud.java 
 * @Package com.tang.wordcloud 
 * @deprecated:
 * @author LihuaTang 
 * @date 2015年7月9日 下午12:04:58 
 * @version V1.0 
 */
/**
* 
*/
package com.tang.wordcloud;

import java.awt.AlphaComposite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.tang.util.EntryComparator;
import com.tang.wordcloud.BitMap.BitMap;
import com.tang.wordcloud.Color.WordCloudColor;

/** 
 * @deprecated:生成词云图片类
 * @author LihuaTang 
 * @date 2015年7月9日 下午12:04:58  
 */
public class WordsCloud {
    private static final int MIN_WIDTH = 320;
    private static final int MIN_HEIGHT = 240;
    private static final int MAX_WIDTH = 2560;
    private static final int MAX_HEIGHT = 1920;
    private static final int BLOCK = 4;
    private static final String DEF_FONT = "微软雅黑";
    private static final int MIN_FONT_SIZE = 12;
    private static WordCloudColor cloudColor = new WordCloudColor();

    /**
     * 
     * @deprecated:生成词云图
     * @param  words 关键词列表
     * @param  width 图片宽度(320~2560，超出边界则默认为边界值)
     * @param  height 图片高度(240~1920，超出边界则默认为边界值)
     * @param  fontName 设置的字体：如不输入该参数，默认为微软雅黑字体
     * @param  maxFontSize:最大字体大小（如小于最小字体，则默认等于最小字体）
     * @param  minFontSize：最小字体大小(如输入小于12，则默认为12)
     * @return BufferedImage 返回类型
     */
    public BufferedImage createWordsCloud(Map<String, Integer> words,
            int width, int height, String fontName, Integer maxFontSize,
            Integer minFontSize)
    {
        return createWordsCloudInner(words,width,height,fontName,maxFontSize,
                minFontSize,null,false);
    }
    /**
     * 
     * @deprecated:生成词云图
     * @param  words 关键词列表
     * @param  width 图片宽度(320~2560，超出边界则默认为边界值)
     * @param  height 图片高度(240~1920，超出边界则默认为边界值)
     * @param  fontName 设置的字体：如不输入该参数，默认为微软雅黑字体
     * @param  maxFontSize:最大字体大小（如小于最小字体，则默认等于最小字体）
     * @param  minFontSize：最小字体大小(如输入小于12，则默认为12)
     * @param  colors:字体颜色
     * @return BufferedImage 返回类型
     */
    public BufferedImage createWordsCloud(Map<String, Integer> words,
            int width, int height, String fontName, Integer maxFontSize,
            Integer minFontSize,int[] colors)
    {
        return createWordsCloudInner(words,width,height,fontName,maxFontSize,
                minFontSize,colors,false);
    }
    /**
     * 
     * @deprecated:生成词云图
     * @param  words 关键词列表
     * @param  width 图片宽度(320~2560，超出边界则默认为边界值)
     * @param  height 图片高度(240~1920，超出边界则默认为边界值)
     * @param  fontName 设置的字体：如不输入该参数，默认为微软雅黑字体
     * @param  maxFontSize:最大字体大小（如小于最小字体，则默认等于最小字体）
     * @param  minFontSize：最小字体大小(如输入小于12，则默认为12)
     * @param  colors:字体颜色
     * @param  colorsOrder:字体颜色按词频排序
     * @return BufferedImage 返回类型
     */
    public BufferedImage createWordsCloud(Map<String, Integer> words,
            int width, int height, String fontName, Integer maxFontSize,
            Integer minFontSize,int[] colors,boolean colorsOrder)
    {
        return createWordsCloudInner(words,width,height,fontName,maxFontSize,
                minFontSize,colors,colorsOrder);
    }
    private BufferedImage createWordsCloudInner(Map<String, Integer> words,
            int width, int height, String fontName, Integer maxFontSize,
            Integer minFontSize,int[] colors,boolean colorsOrder)
    {
        if(width<MIN_WIDTH)
        {
            width=MIN_WIDTH;
        } else if(width>MAX_WIDTH)
        {
            width=MAX_WIDTH;
        }
        if(height<MIN_HEIGHT)
        {
            height=MIN_HEIGHT;
        }else if(height>MAX_HEIGHT)
        {
            height=MAX_HEIGHT;
        }
        if(colors!=null)
        {
            cloudColor.setColors(colors);;
        }
        if((words == null) || (words.size() < 1) )
        {
            return null;
        }
        fontName = validateFontName(fontName, DEF_FONT);
        List<Entry<String, Integer>> sortedWords = sortWordsMap(words);

        BufferedImage bi = createBufferedImage(width, height);
        if(bi == null)
            return null;

        if(maxFontSize == null)
            maxFontSize = Integer.valueOf(Math.min(width, height) / 5);

        if(minFontSize == null)
            minFontSize = Integer.valueOf(Math.min(width, height) / 40);

        if(minFontSize.intValue() < MIN_FONT_SIZE)
            minFontSize = Integer.valueOf(MIN_FONT_SIZE);
        if(maxFontSize.intValue() < minFontSize.intValue())
            maxFontSize = minFontSize;

        if(paintWords(bi, sortedWords, fontName, maxFontSize.intValue(),
                minFontSize.intValue(),colorsOrder) < 1)
            return null;

        return bi;
    }

    private String validateFontName(String fontName, String defFont)
    {
        if(fontName != null)
        {
            Font font = new Font(fontName, 0, 16);
            if(font.getName().equals(fontName))
                return fontName;
        }
        return defFont;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private List<Map.Entry<String, Integer>> sortWordsMap(
            Map<String, Integer> words)
    {
        if(words == null)
            return null;
        ArrayList wordsList = new ArrayList(words.entrySet());
        Collections.sort(wordsList, new EntryComparator());
        return wordsList;
    }

    private BufferedImage createBufferedImage(int width, int height)
    {
        BufferedImage bi = new BufferedImage(width, height, 2);
        Graphics2D g2 = bi.createGraphics();
        bi = g2.getDeviceConfiguration()
                .createCompatibleImage(width, height, 3);
        g2.dispose();
        return bi;
    }

    @SuppressWarnings("rawtypes")
    private int paintWords(BufferedImage bi,
            List<Map.Entry<String, Integer>> words, String fontName,
            int maxFontSize, int minFontSize,boolean colorsOrder)
    {
        BitMap bitMap = initBitMap(bi);
        Graphics2D g2 = bi.createGraphics();
        int wordCount = 0;
        int fontSizeAdjust = 0;
        int maxFrequency = ((Integer) ((Map.Entry) words.get(0)).getValue())
                .intValue();
        int minFrequency = ((Integer) ((Map.Entry) words.get(words.size() - 1))
                .getValue()).intValue();
        for (Map.Entry entry : words)
        {
            while (true)
            {
                Font font = initFont(g2, fontName,
                        Integer.valueOf(maxFontSize),
                        Integer.valueOf(minFontSize),
                        (Integer) entry.getValue(),
                        Integer.valueOf(maxFrequency),
                        Integer.valueOf(minFrequency),
                        Integer.valueOf(fontSizeAdjust));
                if(font.getSize() < minFontSize)
                {
                    return wordCount;
                }
                Rectangle2D bounds = getStringBounds(g2, font,
                        (String) entry.getKey());
                int direction = (int) (Math.random() * 10000.0D % 4.0D % 3.0D);
                Rectangle2D rect = bounds.getBounds();
                if(direction != 0)
                {
                    rect.setRect(rect.getX(), rect.getY(), rect.getHeight(),
                            rect.getWidth());
                }

                rect = findSpace(bitMap, rect);
                if(rect == null)
                {
                    rect = bounds.getBounds();
                    if(direction == 0)
                    {
                        direction = (int) (Math.random() * 10000.0D % 2.0D + 1.0D);
                        rect.setRect(rect.getX(), rect.getY(),
                                rect.getHeight(), rect.getWidth());
                    } else
                    {
                        direction = 0;
                    }
                    rect = findSpace(bitMap, rect);

                }

                if(rect != null)
                {
                    paintOneWord(bi, g2, (String) entry.getKey(), direction,
                            rect, bounds,colorsOrder,(double)wordCount/(double)words.size());
                    updateBitMap(bitMap, bi, rect);
                    wordCount++;
                    break;
                }
                fontSizeAdjust--;
            }
        }

        return wordCount;
    }

    private BitMap initBitMap(BufferedImage bi)
    {
        int w = (int) Math.ceil(bi.getWidth() / 4.0D);
        int h = (int) Math.ceil(bi.getHeight() / 4.0D);
        return new BitMap(w, h);
    }

    private Font initFont(Graphics2D g2, String fontName, Integer maxFontSize,
            Integer minFontSize, Integer frequency, Integer maxFrequency,
            Integer minFrequency, Integer fontSizeAdjust)
    {
        int fs = 24;
        if(maxFrequency.intValue() > minFrequency.intValue())
            fs = (int) ((frequency.intValue() - minFrequency.intValue())
                    * (maxFontSize.intValue() - minFontSize.intValue())
                    / (maxFrequency.intValue() - minFrequency.intValue()) + minFontSize
                    .intValue());

        if(fontSizeAdjust != null)
            fs += fontSizeAdjust.intValue();

        Font font = new Font(fontName, 1, fs);
        g2.setFont(font);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        return font;
    }

    private Rectangle2D getStringBounds(Graphics2D g2, Font font, String key)
    {
        FontRenderContext frc = g2.getFontRenderContext();

        Rectangle2D rc = font.getStringBounds(key, frc);

        return rc;
    }

    private Rectangle2D findSpace(BitMap bitMap, Rectangle2D rect)
    {
        int w = pixel2bitMap((int) rect.getWidth());
        int h = pixel2bitMap((int) rect.getHeight());
        int boundW = bitMap.getWidth() - w;
        int boundH = bitMap.getHeight() - h;
        int start_x = boundW / 2;
        int start_y = boundH / 2;

        int maxBound = Math.max(boundW, boundH);
        double wRatio = (double) boundW / (double) maxBound;
        double hRatio = (double) boundH / (double) maxBound;

        double max_r = Math.sin(0.78539816339744828D) * maxBound;
        double r = 1D;
        double a = 0D;
        double a_tmp = 0D;
        double s = 1D;
        double step = 1D;
        int x = start_x;
        int y = start_y;
        while (r < max_r)
        {
            if(isClean(bitMap, x, y, w, h))
            {
                int targetX = bit2pixel(x);
                int targetY = bit2pixel(y);
                return new Rectangle(targetX, targetY, (int) rect.getWidth(),
                        (int) rect.getHeight());
            }
            a_tmp = (s / r > 0.78539816339744828D) ? 0.78539816339744828D : s
                    / r;
            a += a_tmp;
            r += step * a_tmp / 6.2831853071795862D;
            x = (int) (Math.sin(a) * r * wRatio + start_x);
            y = (int) (Math.cos(a) * r * hRatio + start_y);
        }
        return null;
    }

    private int pixel2bitMap(int v)
    {
        return (int) Math.ceil(v / 4.0D);
    }

    private boolean isClean(BitMap bitMap, int x, int y, int w, int h)
    {
        if((x < 0) || (x + w >= bitMap.getWidth()) || (y < 0)
                || (y + h >= bitMap.getHeight()))
        {
            return false;
        }
        for (int i = x; i < x + w; i++)
        {
            for (int j = y; j < y + h; j++)
            {
                if(bitMap.isUsed(i, j))
                {
                    return false;
                }
            }

        }

        return true;
    }

    private int bit2pixel(int v)
    {
        return (v * BLOCK);
    }

    private void paintOneWord(BufferedImage bi, Graphics2D g2, String word,
            int direction, Rectangle2D rect, Rectangle2D orgBounds,boolean colorsOrder,double wordRatio)
    {
        g2.setPaint(cloudColor.getColor(wordRatio, colorsOrder));

        if(direction == 1)
        {
            g2.rotate(1.5707963267948966D, rect.getX(), rect.getY());
            g2.drawString(word, (int) rect.getX(),
                    (int) (rect.getY() - rect.getWidth() - orgBounds.getY()));
            g2.rotate(-1.5707963267948966D, rect.getX(), rect.getY());
        } else if(direction == 2)
        {
            g2.rotate(-1.5707963267948966D, rect.getX(), rect.getY());
            g2.drawString(word, (int) (rect.getX() - rect.getHeight()),
                    (int) (rect.getY() - orgBounds.getY()));
            g2.rotate(1.5707963267948966D, rect.getX(), rect.getY());
        } else
        {
            g2.drawString(word, (int) rect.getX(),
                    (int) (rect.getY() - orgBounds.getY()));
        }

        bi.flush();
    }


    private void updateBitMap(BitMap bitMap, BufferedImage bi, Rectangle2D rect)
    {
        int l = (int) rect.getX();
        int t = (int) rect.getY();
        int r = l + (int) rect.getWidth();
        int b = t + (int) rect.getHeight();
        if(l < 0)
            l = 0;
        if(t < 0)
            t = 0;
        if(r > bi.getWidth())
            r = bi.getWidth();
        if(b > bi.getHeight())
            b = bi.getHeight();
        if((r <= l) || (b <= t))
            return;

        for (int y = t; y < b + 4 - 1; y += 4)
            for (int x = l; x < r + 4 - 1; x += 4)
                for (int i = 0; i < 16; ++i)
                {
                    int x2 = x + i % 4;
                    int y2 = y + i / 4;
                    if(x2 < bi.getWidth())
                    {
                        if(y2 >= bi.getHeight())
                            continue;
                        if(bi.getRGB(x + i % 4, y + i / 4) != 0)
                        {
                            bitMap.setUsed(pixel2bitMap(x), pixel2bitMap(y),
                                    true);
                            break;
                        }
                    }
                }
    }

    public BufferedImage mixImages(BufferedImage[] images, Float[] alphas,
            int width, int height)
    {
        return mixImages(images, alphas, createBufferedImage(width, height));
    }

    public BufferedImage mixImages(BufferedImage[] images, Float[] alphas,
            BufferedImage targetImage)
    {
        if((images == null) || (images.length < 1))
            return targetImage;
        if(targetImage == null)
        {
            for (int i = 0; i < images.length; ++i)
                if(images[i] != null)
                {
                    targetImage = createBufferedImage(images[0].getWidth(),
                            images[0].getHeight());
                    break;
                }

            if(targetImage == null)
                return null;
        }
        Graphics2D g2 = targetImage.createGraphics();
        float alpha = 1F;
        for (int i = 0; i < images.length; ++i)
        {
            if(images[i] == null)
                continue;
            if((alphas != null) && (i < alphas.length) && (alphas[i] != null))
                alpha = alphas[i].floatValue();
            else
                alpha = 1F;

            g2.setComposite(AlphaComposite.getInstance(3, alpha));
            g2.drawImage(images[i], 0, 0, targetImage.getWidth(),
                    targetImage.getHeight(), 0, 0, images[i].getWidth(),
                    images[i].getHeight(), null);
        }
        g2.dispose();
        targetImage.flush();
        return targetImage;
    }
}
