package com.seventhnode.youtubecrawler.util;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Converter {
	public static boolean isUTF8(byte[] bytes){
	    try{
	        StandardCharsets.UTF_8.newDecoder()
	         .onMalformedInput(CodingErrorAction.REPORT)
	         .onUnmappableCharacter(CodingErrorAction.REPORT)
	         .decode(ByteBuffer.wrap(bytes)); 
	    }
	    catch (CharacterCodingException e){
	        return false;
	    }
	    return true;
	}
	
	public static  String removeAllEmojis(String text) {
	    String regex = "[^\\p{L}\\p{N}\\p{P}\\p{Z}]";
	    Pattern pattern = Pattern.compile(
	      regex, 
	      Pattern.UNICODE_CHARACTER_CLASS);
	    Matcher matcher = pattern.matcher(text);
	    String result = matcher.replaceAll("");
	    return result;
	}
	
}
