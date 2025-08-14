package dongyicheng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 积分日志控制器
@RestController
@RequestMapping("/api/users")
public class PointsLogController {

    @Autowired
    private PointsLogRepository pointsLogRepository;

    // 根据用户名获取积分记录
    @GetMapping("/{username}/points")
    public ResponseEntity<Map<String, Object>> getUserPointsByUsername(@PathVariable String username) {
        List<PointsLog> pointsLogs; // 积分记录列表
        Map<String, Object> response = new HashMap<>(); // 响应数据

        try {
            // 查找用户名的积分记录
            pointsLogs = pointsLogRepository.findByUsername(username);

            // 将积分记录放入响应中
            response.put("pointsLogs", pointsLogs);
            return ResponseEntity.ok(response); // 返回成功响应
        } catch (Exception e) {
            // 处理异常并返回错误信息
            response.put("error", "加载积分记录失败: " + e.getMessage());
            return ResponseEntity.status(500).body(response); // 返回500错误响应
        }
    }
}