package dongyicheng;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin

public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    // 获取所有商品
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 根据ID获取商品
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // 创建商品
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // 更新商品
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product product = productRepository.findById(id).orElse(null);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        product.setName(productDetails.getName());
        product.setRedeemPoints(productDetails.getRedeemPoints());
        product.setMinimumLevel(productDetails.getMinimumLevel());

        return ResponseEntity.ok(productRepository.save(product));
    }

    // 删除商品
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
 // 根据积分数额查找商品
    @GetMapping("/redeemPoints/{points}")
    public List<Product> getProductsByRedeemPoints(@PathVariable int points) {
        return productRepository.findByRedeemPointsLessThanEqual(points);
    }

    
 // 根据会员等级查找商品
    @GetMapping("/minimumLevel/{level}")
    public List<Product> getProductsByMinimumLevel(@PathVariable String level) {
        // 定义等级顺序
        String[] levels = {"普通会员", "银卡会员", "金卡会员", "钻石会员"};
        int[] levelIndex = {-1}; // 使用数组来存储索引

        for (int i = 0; i < levels.length; i++) {
            if (levels[i].equals(level)) {
                levelIndex[0] = i; // 更新数组中的值
                break;
            }
        }

        if (levelIndex[0] == -1) {
            return List.of(); // 如果等级无效，返回空列表
        }

        // 查找当前等级及以下的所有商品
        List<Product> allProducts = productRepository.findAll();
        return allProducts.stream()
                .filter(product -> {
                    int productLevelIndex = java.util.Arrays.asList(levels).indexOf(product.getMinimumLevel());
                    return productLevelIndex != -1 && productLevelIndex <= levelIndex[0];
                })
                .toList();
    }
}