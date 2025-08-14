package dongyicheng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// 标记该类为一个 RESTful 风格的控制器，用于处理 HTTP 请求
@RestController
// 定义该控制器的基础路径为 /api/users
@RequestMapping("/api/users")
// 允许跨域请求，解决前端跨域问题
@CrossOrigin
public class UserController {

    // 自动注入 UserRepository 依赖，用于数据库操作
    @Autowired
    private UserRepository userRepository;

    /**
     * 处理用户注册的 HTTP POST 请求。
     *
     * @param user 包含用户注册信息的 User 对象，通过请求体传递
     * @return 注册成功后返回创建的用户对象，若用户 ID 已存在则返回冲突状态码
     */
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        // 检查用户 ID 是否已存在
        if (userRepository.existsById(user.getId())) {
            // 如果存在，返回冲突状态码（409），表示资源冲突
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        // 新用户积分初始化为 0
        user.setPoints(0);
        // 将用户信息保存到数据库
        User savedUser = userRepository.save(user);
        // 返回保存后的用户对象，状态码为 200（OK）
        return ResponseEntity.ok(savedUser);
    }

    /**
     * 返回注册页面（GET 方法）。
     *
     * @param model 用于向视图传递数据的 Model 对象
     * @return 注册页面的名称 "register"
     */
    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        return "register";
    }

    /**
     * 处理用户登录的 HTTP POST 请求。
     *
     * @param user 包含用户名和密码的 User 对象，通过请求体传递
     * @return 登录成功返回 "Login successful"，否则返回未授权状态码及错误信息
     */
    @PostMapping("/auth/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        // 根据用户名查找用户
        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        // 检查用户是否存在且密码是否正确
        if (!optionalUser.isPresent() ||!user.getPassword().equals(optionalUser.get().getPassword())) {
            // 如果不存在或密码错误，返回未授权状态码（401）及错误信息
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        // 登录成功，返回成功信息，状态码为 200（OK）
        return ResponseEntity.ok("Login successful");
    }

    /**
     * 处理获取所有用户的 HTTP GET 请求。
     *
     * @return 包含所有用户的列表
     */
    @GetMapping
    public List<User> getAllUsers() {
        // 从数据库中获取所有用户
        return userRepository.findAll();
    }

    /**
     * 根据用户名查找用户的 HTTP GET 请求。
     *
     * @param username 要查找的用户名
     * @return 找到用户则返回该用户对象，未找到则返回 404 状态码
     */
    @GetMapping("/search")
    public ResponseEntity<User> getUserByUsername(@RequestParam String username) {
        // 根据用户名查找用户
        Optional<User> optionalUser = userRepository.findByUsername(username);
        // 如果用户不存在
        if (!optionalUser.isPresent()) {
            // 返回 404 状态码，表示资源未找到
            return ResponseEntity.notFound().build();
        }
        // 返回找到的用户对象，状态码为 200（OK）
        return ResponseEntity.ok(optionalUser.get());
    }

    //    @GetMapping("/{id}")
    //    public ResponseEntity<User> getUserById(@PathVariable Long id) {
    //        Optional<User> optionalUser = userRepository.findById(id);
    //        if (!optionalUser.isPresent()) {
    //            return ResponseEntity.notFound().build(); // 404 Not Found
    //        }
    //        return ResponseEntity.ok(optionalUser.get()); // 200 OK
    //    }

    /**
     * 处理更新用户信息的 HTTP PUT 请求。
     *
     * @param id 用户的 ID
     * @param userDetails 包含更新后用户信息的 User 对象，通过请求体传递
     * @return 更新成功返回更新后的用户对象，未找到用户则返回 404 状态码
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        // 根据 ID 查找用户
        Optional<User> optionalUser = userRepository.findById(id);
        // 如果用户不存在
        if (!optionalUser.isPresent()) {
            // 返回 404 状态码，表示资源未找到
            return ResponseEntity.notFound().build();
        }
        // 获取找到的用户
        User user = optionalUser.get();
        // 更新用户名和邮箱
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        // 将更新后的用户保存到数据库
        User updatedUser = userRepository.save(user);
        // 返回更新后的用户对象，状态码为 200（OK）
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * 处理删除用户的 HTTP DELETE 请求。
     *
     * @param id 用户的 ID
     * @return 删除成功返回无内容状态码（204），未找到用户则返回 404 状态码
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        // 检查用户是否存在
        if (!userRepository.existsById(id)) {
            // 如果不存在，返回 404 状态码，表示资源未找到
            return ResponseEntity.notFound().build();
        }
        // 删除用户
        userRepository.deleteById(id);
        // 返回无内容状态码（204），表示操作成功但没有返回内容
        return ResponseEntity.noContent().build();
    }

    /**
     * 处理用户获取积分的 HTTP POST 请求。
     *
     * @param id 用户的 ID
     * @param points 要获取的积分值，通过请求体传递
     * @return 获取积分成功返回更新后的用户对象，未找到用户则返回 404 状态码
     */
    @PostMapping("/{id}/points/earn")
    public ResponseEntity<User> earnPoints(@PathVariable Long id, @RequestBody int points) {
        // 根据 ID 查找用户
        Optional<User> optionalUser = userRepository.findById(id);
        // 如果用户不存在
        if (!optionalUser.isPresent()) {
            // 返回 404 状态码，表示资源未找到
            return ResponseEntity.notFound().build();
        }
        // 获取找到的用户
        User user = optionalUser.get();
        // 增加用户积分
        user.setPoints(user.getPoints() + points);
        // 将更新后的用户保存到数据库
        User updatedUser = userRepository.save(user);
        // 返回更新后的用户对象，状态码为 200（OK）
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * 处理用户消费积分的 HTTP POST 请求。
     *
     * @param id 用户的 ID
     * @param points 要消费的积分值，通过请求体传递
     * @return 消费积分成功返回更新后的用户对象，积分不足则返回 400 状态码，未找到用户则返回 404 状态码
     */
    @PostMapping("/{id}/points/spend")
    public ResponseEntity<User> spendPoints(@PathVariable Long id, @RequestBody int points) {
        // 根据 ID 查找用户
        Optional<User> optionalUser = userRepository.findById(id);
        // 如果用户不存在
        if (!optionalUser.isPresent()) {
            // 返回 404 状态码，表示资源未找到
            return ResponseEntity.notFound().build();
        }
        // 获取找到的用户
        User user = optionalUser.get();
        // 检查用户积分是否足够
        if (user.getPoints() < points) {
            // 如果积分不足，返回 400 状态码（BAD_REQUEST），表示请求错误
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 减少用户积分
        user.setPoints(user.getPoints() - points);
        // 将更新后的用户保存到数据库
        User updatedUser = userRepository.save(user);
        // 返回更新后的用户对象，状态码为 200（OK）
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * 处理分页获取用户列表的 HTTP GET 请求。
     *
     * @param page 页码，默认值为 0
     * @param size 每页大小，默认值为 10
     * @return 包含分页用户列表的 ResponseEntity
     */
    @GetMapping("/api/users")
    public ResponseEntity<Page<User>> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        // 创建 PageRequest 对象，指定页码和每页大小
        PageRequest pageable = PageRequest.of(page, size);
        // 根据 PageRequest 对象从数据库中获取分页的用户列表
        Page<User> userPage = userRepository.findAll(pageable);
        // 返回包含分页用户列表的 ResponseEntity，状态码为 200（OK）
        return ResponseEntity.ok(userPage);
    }

    //    // 根据用户名查找积分记录
    //    @GetMapping("/{username}/points")
    //    public ResponseEntity<Map<String, Object>> getUserPointsByUsername(@PathVariable String username) {
    //        Optional<User> optionalUser = userRepository.findByUsername(username);
    //        if (!optionalUser.isPresent()) {
    //            return ResponseEntity.notFound().build(); // 用户未找到
    //        }
    //
    //        User user = optionalUser.get();
    //        Optional<PointsLog> pointsLogs = null;
    //		try {
    //			pointsLogs = PointsLogRepository.findById(user.getId());
    //		catch (Exception e) {
    //			// TODO Auto-generated catch block
    //			e.printStackTrace();
    //		} // 获取积分记录
    //
    //        Map<String, Object> response = new HashMap<>();
    //        response.put("pointsLogs", pointsLogs); // 将积分记录放入响应
    //
    //        return ResponseEntity.ok(response); // 返回积分记录
    //    }

    /**
     * 根据用户 ID 获取会员等级的 HTTP GET 请求。
     *
     * @param id 用户的 ID
     * @return 包含会员等级的 ResponseEntity，未找到用户则返回 404 状态码
     */
    @GetMapping("/{id}/level")
    public ResponseEntity<String> getMembershipLevel(@PathVariable Long id) {
        // 根据 ID 查找用户
        Optional<User> optionalUser = userRepository.findById(id);
        // 如果用户不存在
        if (!optionalUser.isPresent()) {
            // 返回 404 状态码，表示资源未找到
            return ResponseEntity.notFound().build();
        }
        // 获取找到的用户
        User user = optionalUser.get();
        // 根据用户积分获取会员等级
        if (user.getPoints() < 100) {
            return ResponseEntity.ok("普通会员");
        } else if (user.getPoints() < 500) {
            return ResponseEntity.ok("银卡会员");
        } else if (user.getPoints() < 1000) {
            return ResponseEntity.ok("金卡会员");
        } else {
            return ResponseEntity.ok("钻石会员");
        }
    }
}