package dongyicheng;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// 积分日志数据访问层接口
@Repository
public interface PointsLogRepository extends JpaRepository<PointsLog, Long> {

    // 根据用户名查找积分记录
    List<PointsLog> findByUsername(String username);
}