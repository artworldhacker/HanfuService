package  com.dexingworld.hanfu.repository.dao;


import com.dexingworld.hanfu.repository.MyBatisRepository;
import com.dexingworld.hanfu.repository.entity.AppConfig;

import java.util.List;

@MyBatisRepository
public interface AppConfigDao {

    /**
     * Reason: Repository 层API省略.
     */
    AppConfig getById(Integer id);

    /**
     * Reason: Repository 层API省略.
     */
    List<AppConfig> query(AppConfig query);

    /**
     * Reason: Repository 层API省略.
     */
    Integer insert(AppConfig user);

    /**
     * Reason: Repository 层API省略.
     */
    Integer update(AppConfig user);

    /**
     * Reason: Repository 层API省略.
     */
    Integer delete(Integer id);
}
