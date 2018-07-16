/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metron.conf;

/**
 *
 * @author MichelSumbul <michelsumbul@gmail.com>
 */
public class Config {

    private String appName = "Reindexing ES data";
    private String sourcePath;
    private String indexName;
    private int numPartition;
    private String user;
    private String password;
    private String es_node;

    /**
     * @return the sourcePath
     */
    public String getSourcePath() {
        return sourcePath;
    }

    /**
     * @param sourcePath the sourcePath to set
     */
    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    /**
     * @return the indexName
     */
    public String getIndexName() {
        return indexName;
    }

    /**
     * @param indexName the indexName to set
     */
    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    /**
     * @return the numPartition
     */
    public int getNumPartition() {
        return numPartition;
    }

    /**
     * @param numPartition the numPartition to set
     */
    public void setNumPartition(int numPartition) {
        this.numPartition = numPartition;
    }

    /**
     * @return the appName
     */
    public String getAppName() {
        return appName;
    }

    /**
     * @param appName the appName to set
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEs_node() {
        return es_node;
    }

    public void setEs_node(String es_node) {
        this.es_node = es_node;
    }
}