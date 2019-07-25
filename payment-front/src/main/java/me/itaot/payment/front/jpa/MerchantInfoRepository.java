package me.itaot.payment.front.jpa;

import me.itaot.payment.beans.PageInfo;
import me.itaot.payment.front.beans.MerchantInfo;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author:itaot
 * CreateTime:2018/7/7 21:00
 * Email:itaot.me@gmail.com
 * Description:
 */
@Repository
public interface MerchantInfoRepository extends JpaRepository<MerchantInfo, Long> {

    /**
     * 查询商户信息清单
     */
    //public List<MerchantInfo> queryMerchantInfos(int index, int limits);

    /**
     * 查询商户信息清单
     */
    public List<MerchantInfo> findAll();

    /**
     * 根据条件查询商户信息
     */
    //public List<MerchantInfo> queryMerchantInfoByCondition(MerchantInfo merchantInfo);
}
