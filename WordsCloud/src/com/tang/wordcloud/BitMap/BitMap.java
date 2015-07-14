package com.tang.wordcloud.BitMap;


public class BitMap {
    private byte[][] bitMap;
    private int width;
    private int height;
    private int byteWidth;

    /**
     * 
     * @deprecated:×Ö½ÚµØÍ¼Àà
     * @param @param width
     * @param @param height
     * @return
     */
    public BitMap(int width, int height) {
        if((width <= 0) || (height <= 0))
            throw new IllegalArgumentException();
        this.width = width;
        this.height = height;
        this.byteWidth = (int) Math.ceil(width / 8.0D);
        this.bitMap = new byte[height][this.byteWidth];
        for (int y = 0; y < height; ++y)
            for (int x = 0; x < this.byteWidth; ++x)
                this.bitMap[y][x] = 0;
    }

    public int getWidth()
    {
        return this.width;
    }

    public int getHeight()
    {
        return this.height;
    }

    public void updateArea(BitMap bm, int x, int y)
    {
        int y1 = (y >= 0) ? y : 0;
        for (int y2 = y1 - y; (y1 < this.height) && (y2 < bm.height);)
        {
            int x1 = (x >= 0) ? x : 0;
            for (int x2 = x1 - x; (x1 < this.byteWidth) && (x2 < bm.byteWidth);)
            {
                setUsed(x1, y1, bm.isUsed(x2, y2));

                ++x1;
                ++x2;
            }
            ++y1;
            ++y2;
        }
    }

    public boolean isOverlap(BitMap bm, int x, int y)
    {
        int y1 = (y >= 0) ? y : 0;
        for (int y2 = y1 - y; (y1 < this.height) && (y2 < bm.height);)
        {
            int x1 = (x >= 0) ? x : 0;
            for (int x2 = x1 - x; (x1 < this.width) && (x2 < bm.width);)
            {
                if((isUsed(x1, y1)) && (bm.isUsed(x2, y2)))
                    return true;
                ++x1;
                ++x2;
            }
            ++y1;
            ++y2;
        }

        return false;
    }

    public boolean isUsed(int x, int y)
    {
        int byteX = x / 8;
        if((byteX < 0) || (byteX >= this.byteWidth) || (y < 0)
                || (y >= this.height))
            throw new IllegalArgumentException();
        int mask = 1 << 7 - (x % 8 + 8) % 8;
        return ((this.bitMap[y][byteX] & mask) != 0);
    }

    public void setUsed(int x, int y, boolean used)
    {
        int byteX = x / 8;
        if((byteX < 0) || (byteX >= this.byteWidth) || (y < 0)
                || (y >= this.height))
            throw new IllegalArgumentException();
        int mask = 1 << 7 - (x % 8 + 8) % 8;
        if(used)
        {
            int tmp69_67 = byteX;
            byte[] tmp69_66 = this.bitMap[y];
            tmp69_66[tmp69_67] = (byte) (tmp69_66[tmp69_67] | mask);
        } else
        {
            int tmp87_85 = byteX;
            byte[] tmp87_84 = this.bitMap[y];
            tmp87_84[tmp87_85] = (byte) (tmp87_84[tmp87_85] & (mask ^ 0xFFFFFFFF));
        }
    }
}