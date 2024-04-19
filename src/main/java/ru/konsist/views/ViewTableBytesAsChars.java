package ru.konsist.views;

import java.nio.charset.StandardCharsets;

public class ViewTableBytesAsChars {
    public ViewTableBytesAsChars() {
    }

    public String[] viewBytesAsChars(byte[] b) {
        String[] result = new String[]{"", ""};
        String message = new String(b, StandardCharsets.UTF_8);
        result[0] += message + "\n";
        result[1] += message + "\n";
        for (int i = 0; i < b.length; i++) {
            result[0] += "----";
        }
        result[0] += "\n";
        for (int i = 0; i < b.length; i++) {
            String space1 = (Math.ceil(Math.log10(i)) == 1 || i == 0 || i == 1) && i != 10 && i != 100 ? "  " : (Math.ceil(Math.log10(i)) == 2 || i == 10) && i != 100 ? " " : "";
            String text = "|" + space1 + i;
            result[0] += text;
            result[1] += i + ";";
        }
        result[0] += "\n";
        result[1] += "\n";
        for (int i = 0; i < b.length; i++) {
            String space2 = Math.ceil(Math.log10((int) b[i])) == 1 || (int) b[i] == 1 || (int) b[i] == 0 ? "  " : Math.ceil(Math.log10((int) b[i])) == 2 ? " " : "";
            String text = "|" + space2 + b[i];
            result[0] += text;
            result[1] += (int) b[i] + ";";
        }
        result[0] += "\n";
        result[1] += "\n";
        for (int i = 0; i < b.length; i++) {
            int charDec = b[i] == 10 ? 32 : b[i];
            String text = "|  " + (char)charDec;
            result[0] += text;
            result[1] += (char) charDec + ";";
        }
        result[0] += "\n";
        for (int i = 0; i < b.length; i++) {
            result[0] += "----";
        }
        result[0] += "\n";
        result[1] += "\n";
        return result;
    }
}
