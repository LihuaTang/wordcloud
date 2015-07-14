/** 
 * @Title: WordCloudColor.java 
 * @Package com.tang.wordcloud.Color 
 * @deprecated:
 * @author LihuaTang 
 * @date 2015年7月13日 下午6:09:03 
 * @version V1.0 
 */ /**
 * 
 */
package com.tang.wordcloud.Color;

import java.awt.Color;
import java.awt.Paint;

/** 
 * @deprecated:获取词云的颜色
 * @author LihuaTang 
 * @date 2015年7月13日 下午6:09:03  
 */

public class WordCloudColor {
    private static int[] COLORS = { 11682842, 14439168, 13081114,
        10300417, 14450176, 13052357, 11665801, 1739698, 101319, 84146,
        15580865, 1722823, 6664705, 11257601, 7582257, 11665680, 14425600,
        10768192, 14360836, 15515347, 111266, 49372, 15436032, 15436032 };

    
    public Paint getColor(double wordRatio,boolean colorsOrder)
    {
        if(colorsOrder)
        {
            int rgb = COLORS[((int) (wordRatio*(double)COLORS.length))];
            return new Color(rgb);
        }else
        {
            int rgb = COLORS[((int) (Math.random() * 10000.0D) % COLORS.length)];
            return new Color(rgb);
        }
    }
    
    /**
     * 
     * @deprecated:更改默认颜色配置
     * @param   
     * @return void 返回类型
     */
    public void setColors(int[] colors)
    {
        COLORS = colors;
    }

}
