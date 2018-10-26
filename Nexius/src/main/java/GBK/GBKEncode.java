/**
 * FileName: GBKEncode
 * Author:   Administrator
 * Date:     2018/7/24 13:24
 * Description:
 */
package GBK;

import java.io.UnsupportedEncodingException;

public class GBKEncode
{
    /**
     * 16进制字符串转换为字符串
     *
     * @param s
     * @return
     */
    public static String hexStringToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(
                        s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "gbk");
            new String();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        System.out.println(s);
        return s;
    }

    /**
     * utf-8转hex
     * @param s
     * @return
     */

    public static String convertStringToUTF8(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        try {
            char c;
            for (int i = 0; i < s.length(); i++) {
                c = s.charAt(i);
                if (c >= 0 && c <= 255) {
                    sb.append(c);
                } else {
                    byte[] b;

                    b = Character.toString(c).getBytes("utf-8");

                    for (int j = 0; j < b.length; j++) {
                        int k = b[j];
                        if (k < 0)
                            k += 256;
                        sb.append(Integer.toHexString(k).toUpperCase());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return sb.toString();
    }

    public static String convertUTF8ToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }

        try {
            s = s.toUpperCase();

            int total = s.length() / 2;
            int pos = 0;

            byte[] buffer = new byte[total];
            for (int i = 0; i < total; i++) {

                int start = i * 2;

                buffer[i] = (byte) Integer.parseInt(
                        s.substring(start, start + 2), 16);
                pos++;
            }

            return new String(buffer, 0, pos, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "50 4F 53 54 20 68 74 74 70 3A 2F 2F 3A 30 2F 41 " +
                "6D 73 53 75 62 73 63 72 69 62 65 41 6C 61 72 6D " +
                "4D 65 73 73 61 67 65 20 48 54 54 50 2F 31 2E 31 " +
                "0D 0A 43 6F 6E 74 65 6E 74 2D 4C 65 6E 67 74 68 " +
                "3A 20 31 33 30 0D 0A 43 6F 6E 74 65 6E 74 2D 54 " +
                "79 70 65 3A 20 61 70 70 6C 69 63 61 74 69 6F 6E " +
                "2F 6F 63 74 65 74 2D 73 74 72 65 61 6D 0D 0A 44 " +
                "61 74 61 5F 50 61 72 61 6D 3A 20 76 65 72 73 69 " +
                "6F 6E 3D 31 2E 30 2E 30 2E 30 26 6F 70 65 72 61 " +
                "74 69 6F 6E 3D 41 6D 73 53 75 62 73 63 72 69 62 " +
                "65 41 6C 61 72 6D 4D 65 73 73 61 67 65 26 73 65 " +
                "73 73 69 6F 6E 3D 30 26 73 65 71 75 65 6E 63 65 " +
                "3D 30 26 75 6E 69 74 3D 55 6E 4B 6E 6F 77 6E 26 " +
                "69 64 3D 30 26 64 65 76 69 63 65 49 64 3D 30 0D " +
                "0A 0D 0A 64 6F 6D 61 69 6E 3D 31 26 64 65 76 69 " +
                "63 65 3D 31 31 30 30 30 30 31 30 30 30 32 31 33 " +
                "26 63 68 61 6E 6E 65 6C 3D 30 26 6C 65 76 65 6C " +
                "3D 31 26 74 79 70 65 3D 32 38 26 6F 70 65 72 61 " +
                "74 69 6F 6E 3D 31 26 74 69 6D 65 3D 31 35 33 32 " +
                "34 30 38 31 31 33 26 61 6C 61 72 6D 49 44 3D 30 " +
                "26 61 6C 61 72 6D 4D 65 73 73 61 67 65 3D 33 37 " +
                "30 30 30 2C 42 32 4E 35 30 36 2C C2 B3 42 32 4E " +
                "35 30 36 2C 31 ";

        String str2 = "50 4F 53 54 20 68 74 74 70 3A 2F 2F 3A 30 2F 41 " +
                "6D 73 53 75 62 73 63 72 69 62 65 41 6C 61 72 6D " +
                "4D 65 73 73 61 67 65 20 48 54 54 50 2F 31 2E 31 " +
                "0D 0A 43 6F 6E 74 65 6E 74 2D 4C 65 6E 67 74 68 " +
                "3A 20 31 33 30 0D 0A 43 6F 6E 74 65 6E 74 2D 54 " +
                "79 70 65 3A 20 61 70 70 6C 69 63 61 74 69 6F 6E " +
                "2F 6F 63 74 65 74 2D 73 74 72 65 61 6D 0D 0A 44 " +
                "61 74 61 5F 50 61 72 61 6D 3A 20 76 65 72 73 69 " +
                "6F 6E 3D 31 2E 30 2E 30 2E 30 26 6F 70 65 72 61 " +
                "74 69 6F 6E 3D 41 6D 73 53 75 62 73 63 72 69 62 " +
                "65 41 6C 61 72 6D 4D 65 73 73 61 67 65 26 73 65 " +
                "73 73 69 6F 6E 3D 30 26 73 65 71 75 65 6E 63 65 " +
                "3D 30 26 75 6E 69 74 3D 55 6E 4B 6E 6F 77 6E 26 " +
                "69 64 3D 30 26 64 65 76 69 63 65 49 64 3D 30 0D " +
                "0A 0D 0A 64 6F 6D 61 69 6E 3D 31 26 64 65 76 69 " +
                "63 65 3D 31 31 30 30 30 30 31 30 30 30 32 31 33 " +
                "26 63 68 61 6E 6E 65 6C 3D 30 26 6C 65 76 65 6C " +
                "3D 31 26 74 79 70 65 3D 32 38 26 6F 70 65 72 61 " +
                "74 69 6F 6E 3D 31 26 74 69 6D 65 3D 31 35 33 32 " +
                "34 31 39 38 32 37 26 61 6C 61 72 6D 49 44 3D 30 " +
                "26 61 6C 61 72 6D 4D 65 73 73 61 67 65 3D 33 32 " +
                "30 30 30 2C 45 30 35 45 56 38 2C CB D5 45 30 35 " +
                "45 56 38 2C 31 ";

        String str3 = "485454502f312e3120323030204f4b0d0a436f6e74656e742d4c656e6774683a2038300d0a436f6e74656e742d547970653a20746578742f786d6c0d0a446174615f506172616d3a2076657273696f6e3d312e302e302e30266f7065726174696f6e3d63754865617274626561742673657373696f6e3d302673657175656e63653d31373726756e69743d4f505f434c49454e542669643d302664657669636549643d300d0a0d0a3c3f786d6c2076657273696f6e3d22312e302220656e636f64696e673d225554462d3822203f3e0a3c626f647920756e69743d2230222074696d653d22302220657870697265733d22333022202f3e0a\n" +
                "485454502f312e3120323030204f4b0d0a436f6e74656e742d4c656e6774683a2038300d0a436f6e74656e742d547970653a20746578742f786d6c0d0a446174615f506172616d3a2076657273696f6e3d312e302e302e30266f7065726174696f6e3d63754865617274626561742673657373696f6e3d302673657175656e63653d31373826756e69743d4f505f434c49454e542669643d302664657669636549643d300d0a0d0a3c3f786d6c2076657273696f6e3d22312e302220656e636f64696e673d225554462d3822203f3e0a3c626f647920756e69743d2230222074696d653d22302220657870697265733d22333022202f3e0a\n" +
                "485454502f312e3120323030204f4b0d0a436f6e74656e742d4c656e6774683a2038300d0a436f6e74656e742d547970653a20746578742f786d6c0d0a446174615f506172616d3a2076657273696f6e3d312e302e302e30266f7065726174696f6e3d63754865617274626561742673657373696f6e3d302673657175656e63653d31383126756e69743d4f505f434c49454e542669643d302664657669636549643d300d0a0d0a3c3f786d6c2076657273696f6e3d22312e302220656e636f64696e673d225554462d3822203f3e0a3c626f647920756e69743d2230222074696d653d22302220657870697265733d22333022202f3e0a\n" +
                "504f535420687474703a2f2f3a302f416d73537562736372696265416c61726d4d65737361676520485454502f312e310d0a436f6e74656e742d4c656e6774683a203133300d0a436f6e74656e742d547970653a206170706c69636174696f6e2f6f637465742d73747265616d0d0a446174615f506172616d3a2076657273696f6e3d312e302e302e30266f7065726174696f6e3d416d73537562736372696265416c61726d4d6573736167652673657373696f6e3d302673657175656e63653d3026756e69743d556e4b6e6f776e2669643d302664657669636549643d300d0a0d0a646f6d61696e3d31266465766963653d31313030303031303030323133266368616e6e656c3d30266c6576656c3d3126747970653d3238266f7065726174696f6e3d312674696d653d3135333234323332333726616c61726d49443d3026616c61726d4d6573736167653d33373030302c42324e3530362cc2b342324e3530362c31\n" +
                "485454502f312e3120323030204f4b0d0a436f6e74656e742d4c656e6774683a2038300d0a436f6e74656e742d547970653a20746578742f786d6c0d0a446174615f506172616d3a2076657273696f6e3d312e302e302e30266f7065726174696f6e3d63754865617274626561742673657373696f6e3d302673657175656e63653d31383926756e69743d4f505f434c49454e542669643d302664657669636549643d300d0a0d0a3c3f786d6c2076657273696f6e3d22312e302220656e636f64696e673d225554462d3822203f3e0a3c626f647920756e69743d2230222074696d653d22302220657870697265733d22333022202f3e0a";

        String str4 = "8181b995a42f99\n" +
                "8130\n" +
                "7b22666c6167223a2236222c2264617461223a7b7d2c226d6163223a2238633a31343a37643a64303a63643a3062227d\n" +
                "8181fc604f2bdc";
        String msgStr = hexStringToString(str);
        String msgStr2 = hexStringToString(str2);
        String msgStr4 = hexStringToString(str4);
        //String msgStr3 = hexStringToString(str3);
        String errorStr = new String(msgStr.getBytes("gbk"),"utf-8");

        String bakStr = new String(errorStr.getBytes("utf-8"),"gbk");

        String errorStr2 = new String(msgStr2.getBytes("gbk"),"utf-8");

        String bakStr2 = new String(errorStr2.getBytes("utf-8"),"gbk");



        System.out.println();

    }
}
