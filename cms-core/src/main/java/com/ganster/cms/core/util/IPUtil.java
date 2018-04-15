package com.ganster.cms.core.util;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPUtil {
    private static final InputStream db_city = IPUtil.class.getClassLoader().getResourceAsStream("GeoLite2-City.mmdb");
    private static DatabaseReader cityReader;

    static {
        try {
            cityReader = new DatabaseReader.Builder(db_city).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private IPUtil() {
    }

    public static String getAddr(String ip) {
        InetAddress ipAddr;
        try {
            ipAddr = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            return null;
        }
        CityResponse response;
        try {
            response = cityReader.city(ipAddr);
        } catch (IOException | GeoIp2Exception e) {
            return null;
        }
        String ret = response.getContinent().getNames().get("zh-CN") + "-"
                + response.getCountry().getNames().get("zh-CN") + "-"
                + response.getCity().getNames().get("zh-CN") + "-("
                + response.getLocation().getLatitude() + "," + response.getLocation().getLongitude() + ")";
        System.out.println(ret);
        return ret;
    }

    class IPInfo{
        String country;
        String city;
        String county;
        String isp;

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public String getIsp() {
            return isp;
        }

        public void setIsp(String isp) {
            this.isp = isp;
        }
    }
}
