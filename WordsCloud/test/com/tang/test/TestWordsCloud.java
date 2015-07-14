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
        BufferedImage bi =  wt.createWordsCloud(words, 400, 300, "΢���ź�", 28, 4,colors,true);
        Graphics2D g = (Graphics2D) bi.getGraphics();
//      AddIconImage.addIcon(g, "d:/wc_icon.png");
//        Color bgcolor = new Color(0, 0, 0);
        g.drawImage(bi, 0, 0, null, null);
//      bi.getGraphics().drawString("֪΢", 320, 30);
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
        words.put("Ц̸��������", 28);
        words.put("����", 20);
        words.put("��Ӱ", 16);
        words.put("������̬", 12);
        words.put("����", 10);
        words.put("���", 10);
        words.put("�α�", 10);
        words.put("���", 10);
        words.put("����", 10);
        words.put("����", 10);
        words.put("����", 10);
        words.put("��Ȼ", 10);
        words.put("�й�", 10);
        words.put("̨��", 10);
        words.put("����", 10);
        words.put("����", 10);
        words.put("���", 10);
        words.put("����", 10);
        words.put("ʧȥ", 10);
        words.put("����", 10);
        words.put("����", 10);
        words.put("Ȩ��", 10);
        words.put("����", 10);
        words.put("����", 10);
        words.put("����", 10);
        words.put("��Ϣ", 10);
        words.put("����", 10);
        words.put("��Ʒ", 10);
        words.put("�¼�", 10);
        words.put("ʱ��", 10);
        words.put("�ռ�", 10);
        words.put("�յ�", 10);
        words.put("����", 10);
        words.put("����", 10);
        words.put("����", 10);
        words.put("���¾�", 10);
        words.put("ׯ��", 10);
        words.put("������", 10);
        words.put("����", 10);
        words.put("��ɽ", 10);
        words.put("��ˮ", 10);
        words.put("������", 10);
        words.put("����", 10);
        words.put("����", 10);
        words.put("����", 10);
        words.put("��ԣ", 10);
        words.put("�츳", 10);
        words.put("�ȸ�", 10);
        words.put("�ٶ�", 10);
        words.put("������", 10);
        words.put("û��Ǯ", 10);
        words.put("��Happy", 10);
        words.put("������", 10);
        words.put("��ô", 10);
        words.put("�Է�", 10);
        words.put("�Ի�", 8);
        words.put("֪΢", 8);
        words.put("��͸", 6);
        words.put("��͸", 3);
        words.put("ʧ��", 3);
        words.put("����", 3);
        words.put("����", 3);
        words.put("����", 3);
        words.put("����", 3);
        words.put("����", 3);
        words.put("����", 3);
        words.put("��ʳ", 3);
        return words;
        
    }


}
