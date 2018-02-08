//package com.ganster.cms.auth.util;
//
//import com.ganster.cms.auth.Exception.InformationException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class PInformationUtil {
//
//    private static final Logger logger = LoggerFactory.getLogger(PInformationUtil.class);
//
//    private String id;
//    private String permissionname;
//
//    public PInformationUtil(String id, String permissionname) {
//        this.id = id;
//        this.permissionname = permissionname;
//    }
//
//    public PInformationUtil() {
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getPermissionname() {
//        return permissionname;
//    }
//
//    public void setPermissionname(String permissionname) {
//        this.permissionname = permissionname;
//    }
//
//    public void dealInfromation(String permissionInformation) throws InformationException {
//        PInformationUtil pInformationUtil = new PInformationUtil();
//        String[] dealResult = permissionInformation.split(":");
//        if (dealResult.length <= 2) {
//            permissionname = dealResult[0];
//            logger.info("---------------------------------------------------"+permissionname+"-----------------------------------");
//            id = dealResult[1];
//            logger.info("----------------------------------"+id+"-------------------------------------------");
//            pInformationUtil.setId(id);
//            pInformationUtil.setPermissionname(permissionname);
//        } else throw new InformationException();
//    }
//}
