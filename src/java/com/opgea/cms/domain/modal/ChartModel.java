/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.domain.modal;

/**
 *
 * @author Ramesh
 */
public class ChartModel implements Comparable<ChartModel> {
 
    private String x;
    private String y;
    private Integer orderId;

    public ChartModel(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public ChartModel(String x, String y, Integer orderId) {
        this.x = x;
        this.y = y;
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ChartModel other = (ChartModel) obj;
        if ((this.x == null) ? (other.x != null) : !this.x.equals(other.x)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.x != null ? this.x.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "ChartModel{" + "x=" + x + ", y=" + y + '}';
    }

    @Override
    public int compareTo(ChartModel o) {
        return this.orderId.compareTo(o.orderId);
    }
    
    
    
}
