package com.dc.ybjoRabbitMQ.service;

import com.dc.ybjoRabbitMQ.dao.ProductDao;
import com.dc.ybjoRabbitMQ.entity.Product;
import com.dc.ybjoRabbitMQ.entity.ProductRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jay.zhou
 * @date 2019/4/26
 * @time 14:58
 */
@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private static final String PRODUCT_NO = "No123321";

    @Autowired
    private ProductDao productDao;

    /**
     * 抢单方法实现
     *
     * @param userId 抢单用户id
     */
    @Override
    public void robbingProduct(int userId) {
        //v1.0，有线程安全问题。需要修改SQL与代码
      /*  Product product = productDao.selectProductByNo(PRODUCT_NO);  //先查询商品
        if (product != null && product.getTotal() > 0) {

            // 原因：多个线程可能同时进入此方法体
            // A线程从数据库查到的Product的库存数量是1，B线程同样是查到了是1。
            // 结果两个线程都执行了更新库存减一的操作，那么库存量就变成了 -1 了。

            //再更新库存表
            productDao.updateProduct(PRODUCT_NO);
            //插入记录
            productDao.insertProductRecord(new ProductRecord(PRODUCT_NO, userId));
            //发送短信
            LOGGER.info("用户{}抢单成功", userId);
        } else {
            LOGGER.error("用户{}抢单失败", userId);
        }*/


       /* //v2.0， 修改SQL与代码后 解决超卖问题
        //如果并发量实在太大，会给我的应用程序带来非常大的压力
        //首先因为频繁创建对象，对我们的堆内存造成压力。GC需要频繁销毁对象，对GC的压力也很大。
        // 其次对数据库的压力也很大。
        // 我将线程数由 5000 改为 50000时 电脑几乎卡到崩溃

        Product product = productDao.selectProductByNo(PRODUCT_NO);  //先查询商品
        if (product != null && product.getTotal() > 0) {
            //更新库存表，库存量减少1。返回1说明更新成功。返回0说明库存已经为0
            int updateResult = productDao.updateProductNew(PRODUCT_NO);
            //如果商品没被抢光
            if (updateResult > 0) {
                //插入记录
                productDao.insertProductRecord(new ProductRecord(PRODUCT_NO, userId));
                //发送短信
                LOGGER.info("用户{}抢单成功", userId);
            } else {
                LOGGER.error("用户{}抢单失败", userId);
            }
        } else {
            LOGGER.error("用户{}抢单失败", userId);
        }*/


        //v3.0， 使用RabbitMQ分担并发压力
        //把用户的抢单请求发送到RabbitMQ消息中间件中。因为RabbitMQ是一个消息队列，队列会按照先进先出的特点进行操作。
        // RabbitMQ服务器一般部署在另外一个电脑上，所以就把这个并发压力转移到了另外电脑的RabbitMQ服务器上，
        // 而不是我们的抢单应用程序。

        try {
            Product product = productDao.selectProductByNo(PRODUCT_NO);
            if (product != null && product.getTotal() > 0) {
                //更新库存表，库存量减少1。返回1说明更新成功。返回0说明库存已经为0
                int updateResult = productDao.updateProductNew(PRODUCT_NO);
                if (updateResult > 0) {
                    //插入记录
                    productDao.insertProductRecord(new ProductRecord(PRODUCT_NO, userId));
                    //发送短信
                    LOGGER.info("用户{}抢单成功", userId);
                } else {
                    LOGGER.error("用户{}抢单失败", userId);
                }
            } else {
                LOGGER.error("用户{}抢单失败", userId);
            }
        } catch (Exception e) {
            LOGGER.error("处理抢单业务出现异常 :{}", e.getMessage());
        }


        // 使用RabbitMQ的最主要变化就是：以前抢单操作请求直接由我们抢单应用程序执行，现在请求被转移到了RabbitMQ服务器中。
        // RabbitMQ服务器把接收到的抢单请求进行排队，最后由RabbitMQ服务器把抢单请求转发到我们的抢单应用程序，
        // 这样的好处就是避免我们的抢单应用程序短时间直接处理大量请求。RabbitMQ服务器主要作用是减缓抢单应用程序的并发压力，
        // 相当于在我们的抢单程序之前加了一道请求缓冲区。

    }
}
