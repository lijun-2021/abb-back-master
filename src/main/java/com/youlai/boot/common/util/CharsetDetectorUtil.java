package com.youlai.boot.common.util;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;

import java.io.*;
import java.nio.charset.Charset;

public class CharsetDetectorUtil {

    public static Charset detect(File file) throws IOException {
        try (BufferedInputStream bis =
                     new BufferedInputStream(new FileInputStream(file))) {

            CharsetDetector detector = new CharsetDetector();
            detector.setText(bis);

            CharsetMatch match = detector.detect();
            if (match != null) {
                return Charset.forName(match.getName());
            }
        }

        // 兜底
        return Charset.forName("GBK");
    }
}


