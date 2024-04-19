package ru.konsist.services.supports;

import ru.konsist.services.logger.LoggerService;
import ru.konsist.views.ViewTableBytesAsChars;

import java.util.HexFormat;
import java.util.List;


public class SupportService {
    private SupportService() {
    }

    private static class SupportServiceHolder {
        public static final SupportService HOLDER_INSTANCE = new SupportService();
    }

    public static SupportService getInstance() {
        return SupportServiceHolder.HOLDER_INSTANCE;
    }
    public void hexToBytesAndCharsTable(String hexString) {
        byte[] bytes = HexFormat.of().parseHex(hexString);
        ViewTableBytesAsChars tableBytesAsChars =new ViewTableBytesAsChars();
        String[] table = tableBytesAsChars.viewBytesAsChars(bytes);
        LoggerService.getInstance().writeLog(table[1]);
        System.out.println(table[0]);
    }
    public void bytesToBytesAndCharsTable(byte[] bytes) {
        ViewTableBytesAsChars tableBytesAsChars =new ViewTableBytesAsChars();
        String[] table = tableBytesAsChars.viewBytesAsChars(bytes);
        LoggerService.getInstance().writeLog(table[1]);
        System.out.println(table[0]);
    }
    public void bytesToBytesAndCharsTable(List<Byte> bytes) {
        byte[] bytesArray = new byte[bytes.size()];
        int index = 0;
        for (byte item: bytes) {
            bytesArray[index] = item;
            index++;
        }
        ViewTableBytesAsChars tableBytesAsChars =new ViewTableBytesAsChars();
        String[] table = tableBytesAsChars.viewBytesAsChars(bytesArray);
        LoggerService.getInstance().writeLog(table[1]);
        System.out.println(table[0]);
    }
}
