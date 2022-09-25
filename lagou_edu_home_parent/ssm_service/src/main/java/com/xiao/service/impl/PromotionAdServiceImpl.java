package com.xiao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiao.dao.PromotionAdMapper;
import com.xiao.domain.PromotionAd;
import com.xiao.domain.PromotionAdVo;
import com.xiao.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PromotionAdServiceImpl implements PromotionAdService {

    @Autowired
    private PromotionAdMapper adMapper;

    @Override
    public PageInfo findAllPromotionAdByPage(PromotionAdVo promotionAdVo) {

        PageHelper.startPage(promotionAdVo.getCurrentPage(),promotionAdVo.getPageSize());

        List<PromotionAd> allAdByPage = adMapper.findAllPromotionAdByPage();

        PageInfo<PromotionAd> adPageInfo = new PageInfo<>(allAdByPage);

        return adPageInfo;

    }

    @Override
    public void savePromotionAd(PromotionAd promotionAd) {


        adMapper.savePromotionAd(promotionAd);
    }

    @Override
    public void updatePromotionAd(PromotionAd promotionAd) {

        adMapper.updatePromotionAd(promotionAd);
    }

    @Override
    public PromotionAd findPromotionAdById(Integer id) {
        PromotionAd promotionAdById = adMapper.findPromotionAdById(id);
        return promotionAdById;
    }

    @Override
    public void updatePromotionAdStatus(Integer id, Integer status) {

        PromotionAd promotionAd = new PromotionAd();
        promotionAd.setStatus(status);
        promotionAd.setId(id);
        promotionAd.setUpdateTime(new Date());

        adMapper.updatePromotionAdStatus(promotionAd);
    }


}
