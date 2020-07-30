package com.geling.view.gelingtv_XX_tongbu.utils;

import org.apache.commons.codec.binary.Base64;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/12/5------更新------
 * Base64Util
 */

public class Base64Util {
    public static String decodeStr(String paramString) {
//        new Base64();
        return new String(Base64.decodeBase64(new String(paramString).getBytes()));
    }

    public static String encodeStr(String paramString) {
//        new Base64();
//        return new String(Base64.encodeBase64Chunked(paramString.getBytes()));
        return new String(android.util.Base64.encodeToString(paramString.getBytes(),android.util.Base64.NO_WRAP));
    }
}
