package cn.itcast.jd.dao;

import cn.itcast.jd.pojo.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @Author DC
 * @Date 2021-01-28
 */
@Repository
public interface ItemDao extends JpaRepository<Item, Long> {
}
