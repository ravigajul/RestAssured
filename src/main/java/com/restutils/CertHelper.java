package com.restutils;

import java.io.FileInputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import io.restassured.config.SSLConfig;

public class CertHelper {

  public static SSLConfig getSslConfig(String jksPath) {
    String password = "dost1234";
    KeyStore keyStore = null;
    try {
      FileInputStream fis = new FileInputStream(jksPath);
      keyStore = KeyStore.getInstance("JKS");
      keyStore.load(
          fis,
          password.toCharArray());

    } catch (Exception ex) {
      System.out.println("Error while loading keystore >>>>>>>>>");
      ex.printStackTrace();
    }
    if (keyStore != null) {
      org.apache.http.conn.ssl.SSLSocketFactory clientAuthFactory = null;
      try {
        clientAuthFactory = new org.apache.http.conn.ssl.SSLSocketFactory(keyStore, password);
      } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
      } catch (KeyManagementException e) {
        e.printStackTrace();
      } catch (KeyStoreException e) {
        e.printStackTrace();
      } catch (UnrecoverableKeyException e) {
        e.printStackTrace();
      }
      return new SSLConfig().with().sslSocketFactory(clientAuthFactory).and().allowAllHostnames();
    }
    else return  null;
  }
}