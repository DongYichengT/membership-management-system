package dongyicheng;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductRepository extends JpaRepository<Product, Long> {
    // 根据商品名称查找商品
    List<Product> findByName(String name);
    // 根据积分数额查找商品
    List<Product> findByRedeemPointsLessThanEqual(int redeemPoints);
}