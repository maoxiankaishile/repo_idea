package com.xiao.dao;

import com.xiao.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {

    /**
     * 分页获取所有的广告列表
     */
    public List<PromotionAd> findAllPromotionAdByPage();

    /**
     * 保存广告
     */
    public void savePromotionAd(PromotionAd promotionAd);


    /**
     * 修改广告
     */
    public void updatePromotionAd(PromotionAd promotionAd);

    /**
     * 根据id查询广告
     */
    public PromotionAd findPromotionAdById(Integer id);

    /**
     * 修改广告上下线
     */
    public void updatePromotionAdStatus(PromotionAd promotionAd);
}
