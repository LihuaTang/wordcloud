package com.tang.test;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.tang.wordcloud.WordsCloud;


public class TestWordsCloud {

    
    /**
     * @param args
     */
    public static void main(String[] args) {
        String path = "d:/wordcloud2.png";
        new TestWordsCloud().creatWc(path);

    }
    
    public void creatWc(String path)
    {
        Map<String,Integer> words = initWordsMap();
        WordsCloud wt = new WordsCloud();
        int[] colors = { 11682842, 14439168, 13081114};
        BufferedImage bi =  wt.createWordsCloud(words, 400, 300, "微软雅黑", 28, 4,colors,true);
        Graphics2D g = (Graphics2D) bi.getGraphics();
//      AddIconImage.addIcon(g, "d:/wc_icon.png");
//        Color bgcolor = new Color(0, 0, 0);
        g.drawImage(bi, 0, 0, null, null);
//      bi.getGraphics().drawString("知微", 320, 30);
        g.dispose();
        try {
            ImageIO.write(bi,"png",new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
    }
    
    private Map<String,Integer> initWordsMap()
    {
        Map<String,Integer> words = new HashMap<String,Integer>();
        words.put("笑谈浮生流年", 28);
        words.put("音乐", 20);
        words.put("电影", 16);
        words.put("艺术形态", 12);
        words.put("表现", 10);
        words.put("真棒", 10);
        words.put("何必", 10);
        words.put("真好", 10);
        words.put("白云", 10);
        words.put("蓝天", 10);
        words.put("舍弃", 10);
        words.put("必然", 10);
        words.put("中国", 10);
        words.put("台湾", 10);
        words.put("政治", 10);
        words.put("放弃", 10);
        words.put("坚持", 10);
        words.put("加油", 10);
        words.put("失去", 10);
        words.put("独立", 10);
        words.put("公正", 10);
        words.put("权威", 10);
        words.put("梦想", 10);
        words.put("激情", 10);
        words.put("闯祸", 10);
        words.put("信息", 10);
        words.put("服务", 10);
        words.put("产品", 10);
        words.put("事件", 10);
        words.put("时间", 10);
        words.put("空间", 10);
        words.put("空荡", 10);
        words.put("孔子", 10);
        words.put("孟子", 10);
        words.put("论语", 10);
        words.put("道德经", 10);
        words.put("庄子", 10);
        words.put("韩非子", 10);
        words.put("倾诉", 10);
        words.put("青山", 10);
        words.put("绿水", 10);
        words.put("大数据", 10);
        words.put("数据", 10);
        words.put("福气", 10);
        words.put("富翁", 10);
        words.put("富裕", 10);
        words.put("天赋", 10);
        words.put("谷歌", 10);
        words.put("百度", 10);
        words.put("互联网", 10);
        words.put("没有钱", 10);
        words.put("好Happy", 10);
        words.put("好天气", 10);
        words.put("怎么", 10);
        words.put("吃饭", 10);
        words.put("吃货", 8);
        words.put("知微", 8);
        words.put("唐透", 6);
        words.put("唐透", 3);
        words.put("失望", 3);
        words.put("懊恼", 3);
        words.put("创意", 3);
        words.put("创造", 3);
        words.put("生活", 3);
        words.put("理想", 3);
        words.put("漫画", 3);
        words.put("美食", 3);
        return words;
        
    }


}
