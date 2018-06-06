package com.gangster.cms.admin.util;

import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.WebFile;
import com.gangster.cms.dao.mapper.SettingEntryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.Objects;

@Component
public class FileTool {
    @Autowired
    private SettingEntryMapper settingEntryMapper;

    public static void doDeleteEmptyDir(String dir) {
        boolean success = (new File(dir)).delete();
        if (success) {
            System.out.println("Successfully deleted empty directory: " + dir);
        } else {
            System.out.println("Failed to delete empty directory: " + dir);
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (String aChildren : Objects.requireNonNull(children)) {
                boolean success = deleteDir(new File(dir, aChildren));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    /**
     * 删除多个文件
     */
    public void deleteFiles(List<WebFile> files) {
        files.stream().map(e ->
                settingEntryMapper.selectByPrimaryKey(CmsConst.FILE_PATH).getSysValue() + e.getFileName().split("/")[2])
                .forEach(realFilePath -> deleteDir(new File(realFilePath)));
    }


}
