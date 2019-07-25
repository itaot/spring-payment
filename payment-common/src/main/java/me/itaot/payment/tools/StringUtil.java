package me.itaot.payment.tools;

import java.text.StringCharacterIterator;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author:itaot
 * CreateTime:2018/7/13 18:16
 * Email:itaot.me@gmail.com
 * Description:字符串工具类
 */
public class StringUtil {
    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final String ABC_CHARSET = "GB2312";

    public static String escape(String src, HashMap<String, String> hashMap) {
        if ((src == null) || (src.trim().length() == 0)) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        StringCharacterIterator sci = new StringCharacterIterator(src);
        for (char c = sci.first(); c != 65535; c = sci.next()) {
            String ch = String.valueOf(c);
            if (hashMap.containsKey(ch)) {
                ch = (String) hashMap.get(ch);
            }
            sb.append(ch);
        }
        return sb.toString();
    }

    public static String escapeSQL(String input) {
        HashMap hashMap = new HashMap();
        hashMap.put("'", "''");
        return escape(input, hashMap);
    }

    public static String escapeXML(String input) {
        HashMap hashMap = new HashMap();
        hashMap.put("<", "&lt;");
        hashMap.put(">", "&gt;");
        hashMap.put("'", "&apos;");
        hashMap.put("\"", "&quot;");
        hashMap.put("&", "&amp;");
        return escape(input, hashMap);
    }

    public static String removeComma(String string) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < string.length(); ++i) {
            if (',' != string.charAt(i)) {
                sb.append(string.charAt(i));
            }
        }
        return sb.toString();
    }

    public static String toLetterOrDigit(String string) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < string.length(); ++i) {
            if (Character.isLetterOrDigit(string.charAt(i)))
                sb.append(string.charAt(i));
            else {
                sb.append("X");
            }
        }
        return sb.toString();
    }

    public static String toLetter(String string) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < string.length(); ++i) {
            if (Character.isLetter(string.charAt(i)))
                sb.append(string.charAt(i));
            else if (Character.isDigit(string.charAt(i))) {
                switch (string.charAt(i)) {
                    case '0':
                        sb.append("A");
                        break;
                    case '1':
                        sb.append("B");
                        break;
                    case '2':
                        sb.append("C");
                        break;
                    case '3':
                        sb.append("D");
                        break;
                    case '4':
                        sb.append("E");
                        break;
                    case '5':
                        sb.append("F");
                        break;
                    case '6':
                        sb.append("G");
                        break;
                    case '7':
                        sb.append("H");
                        break;
                    case '8':
                        sb.append("I");
                        break;
                    case '9':
                        sb.append("J");
                }

            } else {
                sb.append("M");
            }
        }
        return sb.toString();
    }

    public static String bytes2hex(byte[] bytes) {
        String result = "";
        String b = "";
        if (null == bytes) {
            return null;
        }
        for (int i = 0; i < bytes.length; ++i) {
            b = Integer.toHexString(bytes[i] & 0xFF);
            if (b.length() == 1) {
                b = new StringBuilder().append("0").append(b).toString();
            }
            result = new StringBuilder().append(result).append(b).toString();
        }
        return result.toUpperCase();
    }

    public static byte[] hex2bytes(String hexString) {
        hexString = hexString.toUpperCase();

        char[] chars = hexString.toCharArray();
        byte[] bytes = new byte[chars.length / 2];

        int index = 0;

        for (int i = 0; i < chars.length; i += 2) {
            byte newByte = 0;

            newByte = (byte) (newByte | char2byte(chars[i]));
            newByte = (byte) (newByte << 4);

            newByte = (byte) (newByte | char2byte(chars[(i + 1)]));

            bytes[index] = newByte;

            ++index;
        }
        return bytes;
    }

    public static byte char2byte(char ch) {
        switch (ch) {
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            case 'A':
                return 10;
            case 'B':
                return 11;
            case 'C':
                return 12;
            case 'D':
                return 13;
            case 'E':
                return 14;
            case 'F':
                return 15;
            case ':':
            case ';':
            case '<':
            case '=':
            case '>':
            case '?':
            case '@':
        }
        return 0;
    }

    private static void byte2hex(byte b, StringBuffer sb) {
        char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };
        int high = (b & 0xF0) >> 4;
        int low = b & 0xF;
        sb.append(hexChars[high]);
        sb.append(hexChars[low]);
    }

    public static String toHexString(byte[] bytes, char c) {
        StringBuffer sb = new StringBuffer();
        int len = bytes.length;
        for (int i = 0; i < len; ++i) {
            byte2hex(bytes[i], sb);
            if (i < len - 1) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String toHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        int len = bytes.length;
        for (int i = 0; i < len; ++i) {
            byte2hex(bytes[i], sb);
        }
        return sb.toString();
    }

    public static boolean isEmpty(String str) {
        return ((null == str) || ("".equals(str.trim())));
    }

    public static boolean isNotEmpty(String str) {
        return ((str != null) && (!("".equals(str.trim()))));
    }

    public static String replace(String string, String replacement) {
        if (string != null) {
            return string.replaceAll(replacement, "");
        }
        return null;
    }

    public static String trim(String string) {
        if (isEmpty(string)) {
            return "";
        }
        return string.trim();
    }

    public static String trimNum(String string) {
        if (isEmpty(string)) {
            return "0";
        }
        return string.trim();
    }

    public static boolean validate(String string) {
        Pattern pattern = Pattern
                .compile("^[\\\\\\(\\)\\（\\）\\[\\]\\{\\}\\『\\』\\【\\】\\·\\!\\w\\.\\@\\?\\+\\_\\—\\|\\－\\-\\=\\/\\:\\：\\,\\s一-龻㐀-䶵豈-頻-\\#〇—]*$");
        Matcher m = pattern.matcher(string);
        return m.matches();
    }

    public static byte[] byteConcat(byte[] b1, byte[] b2) {
        if ((null == b1) || (null == b2)) {
            return null;
        }

        int b1Len = b1.length;
        int b2Len = b2.length;
        byte[] b = new byte[b1Len + b2Len];

        for (int i = 0; i < b1Len; ++i) {
            b[i] = b1[i];
        }
        for (int i = 0; i < b2Len; ++i) {
            b[(i + b1Len)] = b2[i];
        }

        return b;
    }

    public static byte[] byteXORbyte(byte[] b1, byte[] b2) {
        int b1Len = b1.length;
        int b2Len = b2.length;
        if ((null == b1) || (null == b2)) {
            return null;
        }
        if (b1Len != b2Len) {
            return null;
        }

        byte[] byteXOR = new byte[b1Len];
        for (int i = 0; i < b1Len; ++i) {
            byteXOR[i] = (byte) (b1[i] ^ b2[i]);
        }

        return byteXOR;
    }

    public static String rightPad(String str, char stuff, int size) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(size);
        sb.append(str);
        while (sb.length() < size) {
            sb.append(stuff);
        }
        return sb.toString();
    }

    public static String leftPad(String str, char stuff, int size) {
        if (str == null) {
            return null;
        }
        int pads = size - str.length();
        if (pads <= 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder(size);
        while (sb.length() < pads) {
            sb.append(stuff);
        }
        sb.append(str);
        return sb.toString();
    }
}
