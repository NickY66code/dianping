package com.noah.dianping.recommend;

/**
 * @Author yanghaiqiang
 * @Date 2021/03/30 16:19
 * @ClassName dianping
 * @Description
 **/
public class ShopSortModel {
    private Integer shopId;

    private double score;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
