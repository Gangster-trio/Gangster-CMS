package com.gangster.cms.admin.util;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import java.io.*;
import java.util.Enumeration;
import java.util.Objects;

public class ZipUtils {
    /**
     * 使用GBK编码可以避免压缩中文文件名乱码
     */
    private static final String CHINESE_CHARSET = "GBK";

    /**
     * 文件读取缓冲区大小
     */
    private static final int CACHE_SIZE = 1024;


    /**
     * <p>
     * 压缩文件
     * </p>
     *
     * @param sourceFolder 压缩文件夹
     * @param zipFilePath  压缩文件输出路径
     */
    public static void zip(String sourceFolder, String zipFilePath) throws Exception {
        OutputStream out = new FileOutputStream(zipFilePath);
        BufferedOutputStream bos = new BufferedOutputStream(out);
        ZipOutputStream zos = new ZipOutputStream(bos);
        // 解决中文文件名乱码
        zos.setEncoding(CHINESE_CHARSET);
        File file = new File(sourceFolder);
        String basePath = null;
        if (file.isDirectory()) {
            basePath = file.getPath();
        } else {
            basePath = file.getParent();
        }
        zipFile(file, basePath, zos);
        zos.closeEntry();
        zos.close();
        bos.close();
        out.close();
    }

    /**
     * 递归压缩文件
     */
    private static void zipFile(File parentFile, String basePath, ZipOutputStream zos) throws Exception {
        File[] files;
        if (parentFile.isDirectory()) {
            files = parentFile.listFiles();
        } else {
            files = new File[1];
            files[0] = parentFile;
        }
        String pathName;
        InputStream is;
        BufferedInputStream bis;
        byte[] cache = new byte[CACHE_SIZE];
        for (File file : Objects.requireNonNull(files)) {
            if (file.isDirectory()) {
                zipFile(file, basePath, zos);
            } else {
                pathName = file.getPath().substring(basePath.length() + 1);
                is = new FileInputStream(file);
                bis = new BufferedInputStream(is);
                zos.putNextEntry(new ZipEntry(pathName));
                int nRead = 0;
                while ((nRead = bis.read(cache, 0, CACHE_SIZE)) != -1) {
                    zos.write(cache, 0, nRead);
                }
                bis.close();
                is.close();
            }
        }
    }


    public static boolean unZip(String zipFilePath, String skinName, String staticPath, String templatesPath) {
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(zipFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        Enumeration<?> emu = zipFile.getEntries();
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        File file, parentFile;
        ZipEntry entry;
        byte[] cache = new byte[CACHE_SIZE];

        String skinTempaltesPath = templatesPath + skinName;
        String skinStaticPath = staticPath + skinName;
        new File(skinTempaltesPath).mkdir();
        new File(skinStaticPath).mkdir();


        while (emu.hasMoreElements()) {
            try {
                entry = (ZipEntry) emu.nextElement();
                // 如果文件夹是templates/或者static/就continue
                if (entry.getName().equals("templates/") || (entry.getName().equals("static/"))) {
                    continue;
                }

                if (entry.isDirectory()) {
                    // 0/1/2/3
                    String newDir = entry.getName().split("/", 2)[1];
                    if (entry.getName().startsWith("templates/")) {
                        new File(skinTempaltesPath + File.separator + newDir).mkdirs();
                    } else if (entry.getName().startsWith("static/")) {
                        new File(skinStaticPath + File.separator + newDir).mkdir();
                    }
                } else {
                    bis = new BufferedInputStream(zipFile.getInputStream(entry));
                    if (entry.getName().startsWith("templates/")) {
                        file = new File(skinTempaltesPath + File.separator + entry.getName().split("/", 2)[1]);
                    } else {
                        file = new File(skinStaticPath + File.separator + entry.getName().split("/", 2)[1]);
                    }
                    // 处理文件
                    parentFile = file.getParentFile();
                    if (parentFile != null && (!parentFile.exists())) {
                        parentFile.mkdirs();
                    }
                    fos = new FileOutputStream(file);
                    bos = new BufferedOutputStream(fos, CACHE_SIZE);
                    int nRead = 0;
                    while ((nRead = bis.read(cache, 0, CACHE_SIZE)) != -1) {
                        fos.write(cache, 0, nRead);
                    }
                    bos.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } finally {
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (bis != null) {
                        bis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        FileTool.deleteDir(new File(zipFilePath));
        try {
            zipFile.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
