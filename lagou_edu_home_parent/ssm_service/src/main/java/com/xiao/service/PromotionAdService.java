package com.xiao.service;


import com.github.pagehelper.PageInfo;
import com.xiao.domain.PromotionAd;
import com.xiao.domain.PromotionAdVo;

public interface PromotionAdService {

    /**
     * 分页获取所有的广告列表
     * */
    public PageInfo findAllPromotionAdByPage(PromotionAdVo promotionAdVo);

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
     * 广告状态上下线
     */
    public void updatePromotionAdStatus(Integer id,Integer status);
}
