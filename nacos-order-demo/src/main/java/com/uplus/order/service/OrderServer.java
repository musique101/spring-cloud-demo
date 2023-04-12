package com.uplus.order.service;


import com.uplus.order.common.R;
import com.uplus.order.dao.AppendixDao;
import com.uplus.order.datasource.ExchangeDataSource;
import com.uplus.order.entity.Appendix;
import com.uplus.order.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author GYJ
 * @date 2022/5/10
 */
@Service
@RequiredArgsConstructor
public class OrderServer{

    @Autowired
    private AppendixDao appendixDao;

    @Autowired
    private FileMapper fileMapper;


    public R<?> getOne(Long id){
        Appendix one = appendixDao.getOne(id);
        return R.ok(one, "查询成功");
    }

    @ExchangeDataSource("slave")
    public R<?> getOneBatis(Long id){
        String one = fileMapper.getAppendix(id);
        return R.ok(one, "查询成功");
    }

    public R<?> getMeta(String key){
        return null;
    }
}
