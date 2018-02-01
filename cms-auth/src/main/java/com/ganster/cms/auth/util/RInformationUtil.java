package com.ganster.cms.auth.util;

import com.ganster.cms.auth.Exception.InformationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RInformationUtil {

    private static final Logger logger = LoggerFactory.getLogger(RInformationUtil.class);

    private String id;
    private String rolename;

    public RInformationUtil(String id, String rolename) {
        this.id = id;
        this.rolename = rolename;
    }

    public RInformationUtil() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public void dealInfromation(String roleInformation) throws InformationException {
        RInformationUtil rInformationUtil = new RInformationUtil();
        String[] dealResult = roleInformation.split(":");
        if (dealResult.length <= 2) {
            rolename = dealResult[0];
            id = dealResult[1];
            rInformationUtil.setId(id);
            rInformationUtil.setRolename(rolename);
        } else throw new InformationException();
    }
}
