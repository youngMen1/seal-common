package com.seal.util.util;

import com.vdurmont.emoji.EmojiParser;
/**
 * @author zhiqiang.feng
 * @version 1.0c
 * @date-time 2019/3/15 16:00
 * @description 微信表情转换
 **/
public abstract class EmojiUtil {
	
	/**
	 * 转换emoji <br>
	 * Example: <code>🍀</code> 将转变为
     * <code>&amp;#x1f340;</code><br>
	 * @param emoji_str emoji_str
	 * @return emoji_result
	 */
	public static String parseToHtmlHexadecimal(String emoji_str){
		return EmojiParser.parseToHtmlHexadecimal(emoji_str);
	}
	
	/**
	 * 转换emoji <br>
	 * Example: <code>🍀</code> 将转变为
     * &lt;span class='emoji emoji1f340'&gt;&lt;/span&gt;<br>
	 * @param emoji_str emoji_str
	 * @return emoji_result
	 */
	public static String parseToHtmlTag(String emoji_str){
		if(emoji_str != null){
			String str = EmojiParser.parseToHtmlHexadecimal(emoji_str);
			return htmlHexadecimalToHtmlTag(str);
		}
		return null;
	}
	
	/**
	 * 转换emoji <br>
	 * Example: <code>🍀</code> 将转变为
     * :four_leaf_clover:<br>
	 * @param emoji_str emoji_str
	 * @return emoji_result
	 */
	public static String parseToAliases(String emoji_str){
		return EmojiParser.parseToAliases(emoji_str);
	}
	
	/**
	 * 
	 * @param emoji_str emoji_str
	 * @return emoji_result
	 */
	public static String parseToHtmlDecimal(String emoji_str){
		return EmojiParser.parseToHtmlDecimal(emoji_str);
	}
	
	/**
	 * 纯文本 删除表情
	 * @param emoji_str emoji_str
	 * @return emoji_result
	 */
	public static String removeAllEmojis(String emoji_str){
		return EmojiParser.removeAllEmojis(emoji_str);
	}
	
	/**
	 * 
	 * @param emoji_str emoji_str
	 * @return emoji_result
	 */
	public static String htmlHexadecimalToHtmlTag(String emoji_str){
		if(emoji_str != null){
			return emoji_str.replaceAll("&#x([^;]*);","<span class='emoji emoji$1'></span>");
		}
		return null;
	}
	
	/**
	 * 解析emoji
	 * @param emoji_str emoji_str nickName
	 *
	 * @param type emoji 表情解析方式<br>
	 * 	 * 0 		  不设置 <br>
	 * 	 * 1 HtmlHex 格式<br>
	 * 	 * 2 HtmlTag 格式<br>
	 * 	 * 3 Alias  格式<br>
	 * 	 * 4 HtmlDec 格式<br>
	 * 	 * 5 PureText 纯文本<br>
	 *
	 * @return emoji_result
	 */
	public static String parse(String emoji_str,int type){
		switch (type) {
		case 1:
			return parseToHtmlHexadecimal(emoji_str);
		case 2:
			return parseToHtmlTag(emoji_str);
		case 3:
			return parseToAliases(emoji_str);
		case 4:
			return parseToHtmlDecimal(emoji_str);
		case 5:
			return removeAllEmojis(emoji_str);
		default:
			return null;
		}
	}
	
}
