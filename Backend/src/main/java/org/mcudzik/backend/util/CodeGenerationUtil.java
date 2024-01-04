package org.mcudzik.backend.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.SecureRandom;

@Component
public class CodeGenerationUtil {
    private static Environment environment;

    @Autowired
    public void setEnvironment(Environment environment){
        CodeGenerationUtil.environment = environment;
    }


    private static final String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    public static String generateRandomCode(int length) {

        StringBuilder sb = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for(int i = 0; i < length; i++){
            sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
        }

        return sb.toString();
    }

    public static byte[] generateQRCode(String content) throws IOException, WriterException {
        return generateQRCode(content, 400);
    }

    public static byte[] generateQRCode(String content, int size) throws WriterException, IOException {

        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix =
                barcodeWriter.encode(content, BarcodeFormat.QR_CODE, size, size);


        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "png", stream);

        return stream.toByteArray();
    }

    public static byte[] generateQRCodeFromCode(String code) throws WriterException, IOException {
        return generateQRCode(environment.getProperty("base.address") + environment.getProperty("qrcode.address.suffix") + code);
    }
}
